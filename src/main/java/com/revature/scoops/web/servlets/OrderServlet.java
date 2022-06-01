package com.revature.scoops.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.scoops.exceptions.InvalidRequestException;
import com.revature.scoops.exceptions.ResourcePersistanceException;
import com.revature.scoops.models.CreditCard;
import com.revature.scoops.models.Menu;
import com.revature.scoops.models.Orders;
import com.revature.scoops.service.CreditCardServices;
import com.revature.scoops.service.OrderServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends HttpServlet {
    private final OrderServices orderServices;
    private final ObjectMapper mapper;


    public OrderServlet(OrderServices orderServices, ObjectMapper mapper) {
        this.orderServices=orderServices;
        this.mapper=mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        if (req.getParameter("id") != null) {
            Orders order;
            try {
                order = orderServices.readById(req.getParameter("id")); // EVERY PARAMETER RETURN FROM A URL IS A STRING
            } catch (ResourcePersistanceException e) {
                resp.setStatus(404);
                resp.getWriter().write(e.getMessage());
                return;
            }
            String payload = mapper.writeValueAsString(order);
            resp.getWriter().write(payload);
            return;
        }

        List<Orders> orders = orderServices.readAll();
        String payload = mapper.writeValueAsString(orders);

        resp.getWriter().write(payload);
    }
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        //Ordersif(!checkAuth(req, resp)) return;
        Orders authOrders = (Orders) req.getSession().getAttribute("authOrders");

        Orders reqOrders = mapper.readValue(req.getInputStream(), Orders.class);

        //if(authOrders.getUsername().equals(reqOrders.getUsername())) {

        Orders updatedOrders = orderServices.update(reqOrders);

        String payload = mapper.writeValueAsString(updatedOrders);
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

        Orders persistedOrders;
        try {
            Orders order = mapper.readValue(req.getInputStream(), Orders.class);
            persistedOrders = orderServices.create(order);
        } catch (InvalidRequestException e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(404);
            return;
        }
        String payload = mapper.writeValueAsString(persistedOrders);

        resp.getWriter().write("Persisted the provided Orders as show below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        //if(!checkAuth(req,resp)) return;
        if(req.getParameter("id") == null){
            resp.getWriter().write("In order to delete, please provide your user id as a verification into the url with ?id=your@id.here");
            resp.setStatus(401);
            return;
        }

        String username = req.getParameter("id");
        Orders authOrders = (Orders) req.getSession().getAttribute("authOrders");

//        if(!authOrdersCard.getUsername().equals(username)){
//            resp.getWriter().write("username provided does not match the user logged in, double check for confirmation of deletion");
//            return;
//        }

        try {
            orderServices.delete(username);
            resp.getWriter().write("Delete Orders from the database");
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
