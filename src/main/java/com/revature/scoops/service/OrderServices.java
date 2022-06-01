package com.revature.scoops.service;

import com.revature.scoops.daos.OrderDao;
import com.revature.scoops.exceptions.ResourcePersistanceException;
import com.revature.scoops.models.Orders;
import com.revature.scoops.util.logging.Logger;

import java.util.List;


public class OrderServices {
    private OrderDao orderDao;
    private Logger logger = Logger.getLogger();

    //private Logger logger = Logger.getLogger();
    public OrderServices(OrderDao orderDao) {
        this.orderDao=orderDao;
    }

    public List<Orders> readAll(){
        // TODO: What MenuDao intellisense telling me?
        List<Orders> orders = orderDao.findAll();
        return orders;
    }


    public Orders readById(String id) throws ResourcePersistanceException {

        Orders order = orderDao.findById(id);
        return order;
    }

    public Orders update(Orders updatedOrders) {
        if (!orderDao.update(updatedOrders)) {
            return null;
        }

        return updatedOrders;
    }

    public boolean delete(String username) {
        return orderDao.delete(username);
    }

    public boolean validateIdNotUsed(String username) {
        return orderDao.checkId(username);
    }

    public Orders create(Orders newOrders) {
        System.out.println("Orders trying to be registered: " + newOrders);
        logger.info("Orders trying to be registered: ");

        System.out.println("before issue?");
        Orders persistedOrders = orderDao.create(newOrders);
        System.out.println("before issue????");

        if (persistedOrders == null) {
            throw new ResourcePersistanceException("Orders was not persisted to the database upon registration");
        }
//        logger.info("Orders has been persisted: " + newCustomer);
        return persistedOrders;
    }
}
