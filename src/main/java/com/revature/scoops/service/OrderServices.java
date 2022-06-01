package com.revature.scoops.service;

import com.revature.scoops.daos.OrderDao;
import com.revature.scoops.exceptions.ResourcePersistanceException;
import com.revature.scoops.models.Customer;
import com.revature.scoops.models.Order;
import com.revature.scoops.util.logging.Logger;

import java.util.List;


public class OrderServices {
    private OrderDao orderDao;

    //private Logger logger = Logger.getLogger();
    public OrderServices(OrderDao orderDao) {
    }

    public List<Order> readAll() {
        // TODO: What OrderDao intellisense telling me?
        List<Order> orders = orderDao.findAll();
        return orders;
    }
    public Order readById(String username) throws ResourcePersistanceException{

        Order order = orderDao.findById(username);
        return order;
    }
}
