package com.revature.scoops.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.scoops.exceptions.ResourcePersistanceException;
import com.revature.scoops.models.Order;
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
        this.orderServices = orderServices;
        this.mapper=mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        if(req.getParameter("id") != null && req.getParameter("email") != null){
            resp.getWriter().write("Hey you have the follow id and email " + req.getParameter("id") + " " + req.getParameter("email") );
            return;
        }

        if(req.getParameter("id") != null){
            System.out.println("test 1");
            Order order;
            try {
                order = orderServices.readById(req.getParameter("id")); // EVERY PARAMETER RETURN FROM A URL IS A STRING
            } catch (ResourcePersistanceException e){
                resp.setStatus(404);
                resp.getWriter().write(e.getMessage());
                return;
            }
            String payload = mapper.writeValueAsString(order);
            resp.getWriter().write(payload);
            return;
        }
        System.out.println("test 2");
        List<Order> orders = orderServices.readAll();
        System.out.println("test 4");
        String payload = mapper.writeValueAsString(orders);

        resp.getWriter().write(payload);
    }


}