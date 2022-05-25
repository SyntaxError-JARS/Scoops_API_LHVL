package com.revature.scoops.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.scoops.daos.CreditCardDao;
import com.revature.scoops.daos.CustomerDao;
import com.revature.scoops.daos.MenuDao;
import com.revature.scoops.daos.OrderDao;
import com.revature.scoops.models.CreditCard;
import com.revature.scoops.models.Order;
import com.revature.scoops.service.CreditCardServices;
import com.revature.scoops.service.CustomerServices;
import com.revature.scoops.service.MenuServices;
import com.revature.scoops.service.OrderServices;
import com.revature.scoops.web.servlets.AuthServlet;
import com.revature.scoops.web.servlets.CustomerServlet;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ObjectMapper mapper = new ObjectMapper();


        CustomerDao customerDao = new CustomerDao();
        CustomerServices customerServices = new CustomerServices(customerDao);
        OrderDao orderDao = new OrderDao();
        OrderServices orderServices = new OrderServices(orderDao);
        MenuDao menuDao = new MenuDao();
        MenuServices menuServices = new MenuServices(menuDao);
        CreditCardDao creditCardDao = new CreditCardDao();
        CreditCardServices creditCardServices = new CreditCardServices(creditCardDao);

        AuthServlet authServlet = new AuthServlet(customerServices, mapper);
        CustomerServlet customerServlet = new CustomerServlet(customerServices, mapper);



        ServletContext context = sce.getServletContext();
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
        //context.addServlet("CustomerServlet", customerServlet).addMapping("/customers/*");
        context.addServlet("CustomerServlet", customerServlet).addMapping("/customers/*");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}

