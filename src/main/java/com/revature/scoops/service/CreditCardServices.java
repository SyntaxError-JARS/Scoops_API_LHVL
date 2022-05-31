package com.revature.scoops.service;

import com.revature.scoops.daos.CreditCardDao;
import com.revature.scoops.exceptions.ResourcePersistanceException;
import com.revature.scoops.models.CreditCard;
import com.revature.scoops.util.Logger;

import java.util.List;

public class CreditCardServices {
    private CreditCardDao creditCardDao;
    private Logger logger = Logger.getLogger();
    public CreditCardServices(CreditCardDao creditCardDao) {
        this.creditCardDao = creditCardDao;
    }

    public List<CreditCard> readAll(){
        // TODO: What CreditCardDao intellisense telling me?
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

}
