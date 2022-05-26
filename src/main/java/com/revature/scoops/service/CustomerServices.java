package com.revature.scoops.service;

import com.revature.scoops.daos.CustomerDao;
import com.revature.scoops.models.Customer;

public class CustomerServices {
    private CustomerDao customerDao = new CustomerDao();
    public CustomerServices(CustomerDao customerDao) {
    }

    public Customer authenticateUser(String email, String password) {
        return null;
    }
}
