package com.revature.scoops.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.scoops.exceptions.InvalidRequestException;
import com.revature.scoops.exceptions.ResourcePersistanceException;
import com.revature.scoops.models.Customer;
import com.revature.scoops.service.CustomerServices;
import com.revature.scoops.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static com.revature.scoops.web.servlets.Authable.checkAuth;

public class CustomerServlet extends HttpServlet {

    private final CustomerServices CustomerServices;
    private final ObjectMapper mapper;

    public CustomerServlet(CustomerServices CustomerServices, ObjectMapper mapper) {
        this.CustomerServices = CustomerServices;
        this.mapper = mapper;
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doOptions(req, resp);
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");


        if(req.getParameter("username") != null){
            Customer Customer;
            try {
                Customer = CustomerServices.readById(req.getParameter("username")); // EVERY PARAMETER RETURN FROM A URL IS A STRING
            } catch (ResourcePersistanceException e){
                resp.setStatus(404);
                resp.getWriter().write(e.getMessage());
                return;
            }
            String payload = mapper.writeValueAsString(Customer);
            resp.getWriter().write(payload);
            return;
        }

        List<Customer> Customers = CustomerServices.readAll();
        String payload = mapper.writeValueAsString(Customers);

        resp.getWriter().write(payload);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        //if(!checkAuth(req, resp)) return;
        Customer authCustomer = (Customer) req.getSession().getAttribute("authCustomer");

        Customer reqCustomer = mapper.readValue(req.getInputStream(), Customer.class);

        //if(authCustomer.getUsername().equals(reqCustomer.getUsername())) {

            Customer updatedCustomer = CustomerServices.update(reqCustomer);

            String payload = mapper.writeValueAsString(updatedCustomer);
            resp.getWriter().write(payload);
        //} else {
//            resp.getWriter().write("id provided does not match the user currently logged in");
//            resp.setStatus(403);
        //}

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        Customer persistedCustomer;
        try {
            Customer Customer = mapper.readValue(req.getInputStream(), Customer.class);
            persistedCustomer = CustomerServices.create(Customer);
        } catch (InvalidRequestException e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(404);
            return;
        }
        String payload = mapper.writeValueAsString(persistedCustomer);

        resp.getWriter().write("Persisted the provided Customer as show below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        //if(!checkAuth(req,resp)) return;
        if(req.getParameter("username") == null){
            resp.getWriter().write("In order to delete, please provide your user id as a verification into the url with ?id=your@id.here");
            resp.setStatus(401);
            return;
        }

        String username = req.getParameter("username");
        Customer authCustomer = (Customer) req.getSession().getAttribute("authCustomer");

//        if(!authCustomer.getUsername().equals(username)){
//            resp.getWriter().write("username provided does not match the user logged in, double check for confirmation of deletion");
//            return;
//        }

        try {
            CustomerServices.delete(username);
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
