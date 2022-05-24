package com.revature.scoops.daos;

import com.revature.scoops.models.Order;
import com.revature.scoops.util.Crudable;

import java.io.IOException;
import java.util.List;

public class OrderDao implements Crudable<Order> {
    @Override
    public Order create(Order newObject) {
        return null;
    }

    @Override
    public List<Order> findAll() throws IOException {
        return null;
    }

    @Override
    public Order findById(String id) {
        return null;
    }

    @Override
    public boolean update(Order updatedObj) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
