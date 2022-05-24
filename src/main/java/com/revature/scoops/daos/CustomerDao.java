package com.revature.scoops.daos;

import com.revature.scoops.models.Customer;
import com.revature.scoops.util.Crudable;

import java.io.IOException;
import java.util.List;

public class CustomerDao implements Crudable<Customer> {
    @Override
    public Customer create(Customer newObject) {
        return null;
    }

    @Override
    public List<Customer> findAll() throws IOException {
        return null;
    }

    @Override
    public Customer findById(String id) {
        return null;
    }

    @Override
    public boolean update(Customer updatedObj) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
