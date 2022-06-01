package com.revature.scoops.service;

import com.revature.scoops.daos.CreditCardDao;
import com.revature.scoops.daos.MenuDao;
import com.revature.scoops.daos.OrderDao;
import com.revature.scoops.exceptions.ResourcePersistanceException;
import com.revature.scoops.models.Menu;
import com.revature.scoops.models.Order;
import com.revature.scoops.util.Logger;

import java.util.List;


public class OrderServices {
    private OrderDao orderDao;
    private Logger logger = Logger.getLogger();
    public OrderServices(OrderDao orderDao) {
    }
    public List<Order> readAll(){
        System.out.println("Made it to readAll()");
        List<Order> orders = orderDao.findAll();
        return orders;
    }


    public Order readById(String id) throws ResourcePersistanceException {

        Order order = orderDao.findById(id);
        return order;
    }

    public Order update(Order updatedOrder) {
        if (!orderDao.update(updatedOrder)){
            return null;
        }

        return updatedOrder;
    }

    public boolean delete(String email) {
        return orderDao.delete(email);
    }

}
