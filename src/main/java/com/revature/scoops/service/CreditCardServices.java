package com.revature.scoops.service;

import com.revature.scoops.daos.CreditCardDao;
import com.revature.scoops.exceptions.AuthenticationException;
import com.revature.scoops.exceptions.InvalidRequestException;
import com.revature.scoops.exceptions.ResourcePersistanceException;
import com.revature.scoops.models.CreditCard;
import com.revature.scoops.util.logging.Logger;

import java.util.List;

public class CreditCardServices{

    private CreditCardDao creditCardDao;
    private Logger logger = Logger.getLogger();

    public CreditCardServices(CreditCardDao creditCardDao) {
        this.creditCardDao = creditCardDao;
    }

    public List<CreditCard> readAll(){

        List<CreditCard> creditCards = creditCardDao.findAll();
        return creditCards;
    }

    public CreditCard readById(String id) throws ResourcePersistanceException {

        CreditCard creditCard = creditCardDao.findById(id);
        return creditCard;
    }


    public CreditCard update(CreditCard updatedCreditCard) {
        if (!creditCardDao.update(updatedCreditCard)){
            return null;
        }

        return updatedCreditCard;
    }


    public boolean delete(String email) {
        return creditCardDao.delete(email);
    }

    public boolean validateEmailNotUsed(String email){
        return creditCardDao.checkEmail(email);
    }

    public CreditCard create(CreditCard newCreditCard){
        logger.info("CreditCard trying to be registered: " + newCreditCard);
        if(!validateInput(newCreditCard)){ // checking if false
            // TODO: throw - what's this keyword?
            throw new InvalidRequestException("User input was not validated, either empty String or null values");
        }

        // TODO: Will implement with JDBC (connecting to our database)
        if(validateEmailNotUsed(newCreditCard.getCustomer_username())){
            throw new InvalidRequestException("User email is already in use. Please try again with another email or login into previous made account.");
        }

        CreditCard persistedCreditCard = creditCardDao.create(newCreditCard);

        if(persistedCreditCard == null){
            throw new ResourcePersistanceException("Credit card was not persisted to the database upon registration");
        }
        logger.info("CreditCard has been persisted: " + newCreditCard);
        return persistedCreditCard;
    }


    public boolean validateInput(CreditCard newCreditCard) {
        logger.debug("Validating CreditCard: " + newCreditCard);
        if(newCreditCard == null) return false;
        if(newCreditCard.getCc_number() == null || newCreditCard.getCc_number().trim().equals("")) return false;
        if(newCreditCard.getCc_name() == null || newCreditCard.getCc_name().trim().equals("")) return false;
        if(newCreditCard.getCvv() == 0) return false;
        if(newCreditCard.getExp_date() == null || newCreditCard.getExp_date().trim().equals("")) return false;
        if(newCreditCard.getZip() == 0) return false;
        if(newCreditCard.getCustomer_username() == null || newCreditCard.getCustomer_username().trim().equals("")) return false;
        return (newCreditCard.getLimit() == 0);
    }

    public CreditCard authenticateCreditCardr(String email, String password){

        if(password == null || password.trim().equals("") || email == null || email.trim().equals("")) {
            throw new InvalidRequestException("Either email or password is an invalid entry. Please try logging in again");
        }

        CreditCard authenticatedCreditCard = creditCardDao.authenticateCreditCard(email, password);

        if (authenticatedCreditCard == null){
            throw new AuthenticationException("Unauthenticated user, information provided was not consistent with our database.");
        }

        return authenticatedCreditCard;

    }
}