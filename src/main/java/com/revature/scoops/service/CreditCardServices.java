package com.revature.scoops.service;

import com.revature.scoops.daos.CreditCardDao;
import com.revature.scoops.exceptions.ResourcePersistanceException;
import com.revature.scoops.models.CreditCard;
import com.revature.scoops.models.Customer;
import com.revature.scoops.util.logging.Logger;

import java.util.List;

public class CreditCardServices {
    private CreditCardDao creditCardDao;
    private Logger logger = Logger.getLogger();

    public CreditCardServices(CreditCardDao creditCardDao) {
        this.creditCardDao = creditCardDao;
    }

    public List<CreditCard> readAll() {
        // TODO: What CreditCardDao intellisense telling me?
        List<CreditCard> creditCards = creditCardDao.findAll();
        return creditCards;
    }


    public CreditCard readById(String id) throws ResourcePersistanceException {

        CreditCard creditCard = creditCardDao.findById(id);
        return creditCard;
    }

    public CreditCard update(CreditCard updatedCreditCard) {
        if (!creditCardDao.update(updatedCreditCard)) {
            return null;
        }

        return updatedCreditCard;
    }

    public boolean delete(String username) {
        return creditCardDao.delete(username);
    }

    public boolean validateIdNotUsed(String username) {
        return creditCardDao.checkEmail(username);
    }

    public CreditCard create(CreditCard newCreditCard) {
        System.out.println("CreditCard trying to be registered: " + newCreditCard);
        logger.info("CreditCard trying to be registered: ");

        System.out.println("before issue?");
        CreditCard persistedCreditCard = creditCardDao.create(newCreditCard);
        System.out.println("before issue????");

        if (persistedCreditCard == null) {
            throw new ResourcePersistanceException("CreditCard was not persisted to the database upon registration");
        }
//        logger.info("CreditCard has been persisted: " + newCustomer);
        return persistedCreditCard;
    }
}
