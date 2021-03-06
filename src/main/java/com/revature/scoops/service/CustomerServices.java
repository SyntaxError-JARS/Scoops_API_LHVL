package com.revature.scoops.service;

import com.revature.scoops.exceptions.AuthenticationException;
import com.revature.scoops.exceptions.InvalidRequestException;
import com.revature.scoops.exceptions.ResourcePersistanceException;
import com.revature.scoops.daos.CustomerDao;
import com.revature.scoops.models.Customer;
import com.revature.scoops.util.logging.Logger;

import java.util.List;



public class CustomerServices {
    private CustomerDao customerDao;
    private Logger logger = Logger.getLogger();
    public CustomerServices(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }


    public List<Customer> readAll(){
        // TODO: What CustomerDao intellisense telling me?
        List<Customer> customers = customerDao.findAll();
        return customers;
    }


    public Customer readById(String username) throws ResourcePersistanceException{

        Customer customer = customerDao.findByUsername(username);
        return customer;
    }

    public Customer update(Customer updatedCustomer) {
        if (!customerDao.update(updatedCustomer)){
            return null;
        }

        return updatedCustomer;
    }

    public boolean delete(String username) {
        return customerDao.delete(username);
    }

    public boolean validateIdNotUsed(String username){
        return customerDao.checkUsername(username);
    }

    public Customer create(Customer newCustomer){
        System.out.println("Customer trying to be registered: " + newCustomer);
        logger.info("Customer trying to be registered: ");
//        if(!validateInput(newCustomer)){ // checking if false
//            // TODO: throw - what's this keyword?
//            throw new InvalidRequestException("User input was not validated, either empty String or null values");
//        }
//
//        // TODO: Will implement with JDBC (connecting to our database)
//        if(validateIdNotUsed(newCustomer.getUsername())){
//            throw new InvalidRequestException("User username is already in use. Please try again with another username or login into previous made account.");
//        }

        System.out.println("before issue?");
        Customer persistedCustomer = customerDao.create(newCustomer);
        System.out.println("before issue????");

        if(persistedCustomer == null){
            throw new ResourcePersistanceException("Customer was not persisted to the database upon registration");
        }
//        logger.info("Customer has been persisted: " + newCustomer);
        return persistedCustomer;
    }

    public boolean validateInput(Customer newCustomer) {
        logger.debug("Validating Customer: " + newCustomer);
        if(newCustomer == null) return false;
        if(newCustomer.getUsername() == null || newCustomer.getUsername().trim().equals("")) return false;
        if(newCustomer.getfName() == null || newCustomer.getfName().trim().equals("")) return false;
        if(newCustomer.getlName() == null || newCustomer.getlName().trim().equals("")) return false;
        return newCustomer.getPassword() == null || newCustomer.getPassword().trim().equals("");
    }

    public Customer authenticateCustomer(String username, String password){

        if(password == null || password.trim().equals("") || username == null || username.trim().equals("")) {
            throw new InvalidRequestException("Either username or password is an invalid entry. Please try logging in again");
        }

        Customer authenticatedCustomer = customerDao.authenticateCustomer(username, password);

        if (authenticatedCustomer == null){
            throw new AuthenticationException("Unauthenticated user, information provided was not consistent with our database.");
        }

        return authenticatedCustomer;

    }
}
