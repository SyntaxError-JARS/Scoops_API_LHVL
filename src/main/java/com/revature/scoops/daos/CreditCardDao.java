package com.revature.scoops.daos;


import com.revature.scoops.models.CreditCard;
import com.revature.scoops.util.ConnectionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class CreditCardDao{


        public CreditCard create(CreditCard newCreditCard) {
            try {
                Session session = ConnectionFactory.getSession();
                Transaction transaction = session.beginTransaction();
                session.save(newCreditCard);
                transaction.commit();
                return newCreditCard;
            } catch (HibernateException | IOException e) {
                e.printStackTrace();
                return null;
            } finally {
                ConnectionFactory.closeSession();
            }
        }


        public List<CreditCard> findAll() {
            try {
                Session session = ConnectionFactory.getSession();
                Transaction transaction = session.beginTransaction();
                List<CreditCard> creditCards = session.createQuery("FROM Credit card").list();
                transaction.commit();
                return creditCards;
            } catch (HibernateException | IOException e) {
                e.printStackTrace();
                return null;
            } finally {
                ConnectionFactory.closeSession();
            }

        }


        public CreditCard findById(String email) {

            try {
                Session session = ConnectionFactory.getSession();
                Transaction transaction = session.beginTransaction();
                CreditCard creditCard = session.get(CreditCard.class, email);
                transaction.commit();
                return creditCard;
            } catch (HibernateException | IOException e) {
                e.printStackTrace();
                return null;
            } finally {
                ConnectionFactory.closeSession();
            }

        }


        public boolean update(CreditCard updatedCreditCard) {
            try {
                Session session = ConnectionFactory.getSession();
                Transaction transaction = session.beginTransaction();
                session.merge(updatedCreditCard);
                transaction.commit();
                return true;
            } catch (HibernateException | IOException e) {
                e.printStackTrace();
                return false;
            } finally {
                ConnectionFactory.closeSession();
            }
        }


        public boolean delete(String email) {
            try {
                Session session = ConnectionFactory.getSession();
                Transaction transaction = session.beginTransaction();
                CreditCard creditCard = session.load(CreditCard.class, email);
                session.remove(creditCard);
                transaction.commit();
                return true;
            } catch (HibernateException | IOException e) {
                e.printStackTrace();
                return false;
            } finally {
                ConnectionFactory.closeSession();
            }
        }

        public CreditCard authenticateCreditCard(String email, String password){

            try {
                Session session = ConnectionFactory.getSession();
                Transaction transaction = session.beginTransaction();
                Query query = session.createQuery("from Credit card where email= :email and password= :password");
                query.setParameter("email", email);
                query.setParameter("password", password);
                CreditCard creditCard = (CreditCard) query.uniqueResult();
                transaction.commit();
                return creditCard;
            } catch (HibernateException | IOException e) {
                e.printStackTrace();
                return null;
            } finally {
                ConnectionFactory.closeSession();
            }

        }
        public boolean checkEmail(String email) {

            try {
                Session session = ConnectionFactory.getSession();
                Transaction transaction = session.beginTransaction();
                Query query = session.createQuery("from Credit Card where email= :email");
                query.setParameter("email", email);
                CreditCard creditCard = (CreditCard) query.uniqueResult();
                transaction.commit();
                if(creditCard == null) return false;
                return true;
            } catch (HibernateException | IOException e) {
                e.printStackTrace();
                return false;
            } finally {
                ConnectionFactory.closeSession();
            }
        }

}
