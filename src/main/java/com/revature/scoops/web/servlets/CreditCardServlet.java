package com.revature.scoops.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.scoops.exceptions.InvalidRequestException;
import com.revature.scoops.exceptions.ResourcePersistanceException;
import com.revature.scoops.models.CreditCard;
import com.revature.scoops.service.CreditCardServices;
import com.revature.scoops.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;



// @WebServlet("/trainers")
public class CreditCardServlet extends HttpServlet{

    private final CreditCardServices creditCardServices;
    private final ObjectMapper mapper;
    private final Logger logger = Logger.getLogger();

    public CreditCardServlet(CreditCardServices creditCardServices, ObjectMapper mapper) {
        this.creditCardServices = creditCardServices;
        this.mapper = mapper;
    }


    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doOptions(req, resp);
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
    }


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        // The below code allows to split information from the endpoint after the /trainers/. Reminder the first element is empty because it takes the value from before the first /
//        String pathInfo = req.getPathInfo();
//        String[] pathParts = pathInfo.split("/");
//        System.out.println(pathParts[0] + pathParts[1] + pathParts[2]);

        // Handling the query params in the /trainers?id=x&email=y
        if(req.getParameter("id") != null && req.getParameter("username") != null){
            resp.getWriter().write("Hey you have the follow id and username " + req.getParameter("id") + " " + req.getParameter("username") );
            return;
        }

        // Handling the query params in the endpoint /trainers?id=x
        if(req.getParameter("id") != null){
            CreditCard creditCard;
            try {
                creditCard = creditCardServices.readById(req.getParameter("id")); // EVERY PARAMETER RETURN FROM A URL IS A STRING
            } catch (ResourcePersistanceException e){
                logger.warn(e.getMessage());
                resp.setStatus(404);
                resp.getWriter().write(e.getMessage());
                return;
            }
            String payload = mapper.writeValueAsString(creditCard);
            resp.getWriter().write(payload);
            return;
        }

        List<CreditCard> creditCards =creditCardServices.readAll();
        String payload = mapper.writeValueAsString(creditCards);

        resp.getWriter().write(payload);
    }


    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        if(!checkAuth(req, resp)) return;
        CreditCard authCreditCard = (CreditCard) req.getSession().getAttribute("authCreditCard");

        CreditCard reqCreditCard = mapper.readValue(req.getInputStream(), CreditCard.class);

        if(authCreditCard.getCustomer_username().equals(reqCreditCard.getCustomer_username())) {

            CreditCard updatedCreditCard = creditCardServices.update(reqCreditCard);

            String payload = mapper.writeValueAsString(updatedCreditCard);
            resp.getWriter().write(payload);
        } else {
            resp.getWriter().write("Username provided does not match the user currently logged in");
            resp.setStatus(403);
        }

    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        CreditCard persistedCreditCard;
        try {
            CreditCard creditCard = mapper.readValue(req.getInputStream(), CreditCard.class); // from JSON to Java Object (Pokemon)
            persistedCreditCard = creditCardServices.create(creditCard);
        } catch (InvalidRequestException e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(404);
            return;
        }
        String payload = mapper.writeValueAsString(persistedCreditCard); // Mapping from Java Object (Pokemon) to JSON

        resp.getWriter().write("Persisted the provided credit card as show below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        if(!checkAuth(req,resp)) return;
        if(req.getParameter("customer_username") == null){
            resp.getWriter().write("In order to delete, please provide your username as a verification into the url with ?username=yourusername.here");
            resp.setStatus(401);
            return;
        }

        String username = req.getParameter("username");
        CreditCard authCreditCard = (CreditCard) req.getSession().getAttribute("authCreditCard");

        if(!authCreditCard.getCustomer_username().equals(username)){
            resp.getWriter().write("Username provided does not match the user logged in, double check for confirmation of deletion");
            return;
        }

        try {
            creditCardServices.delete(username);
            resp.getWriter().write("Delete user from the database");
            req.getSession().invalidate();
        } catch (ResourcePersistanceException e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(404);
        } catch (Exception e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(500);
        }
    }
}
