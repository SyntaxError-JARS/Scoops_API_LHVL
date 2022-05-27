package com.revature.scoops.daos;

import com.revature.scoops.models.Customer;
import com.revature.scoops.util.ConnectionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class CustomerDao {
    public Customer create(Customer newCustomer) {
        try {
            Session session = ConnectionFactory.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(newCustomer);
            transaction.commit();
            return newCustomer;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionFactory.closeSession();
        }
    }


    public List<Customer> findAll() {
        try {
            Session session = ConnectionFactory.getSession();
            Transaction transaction = session.beginTransaction();
            List<Customer> customers = session.createQuery("FROM Customer").list();
            transaction.commit();
            return customers;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionFactory.closeSession();
        }

    }


    public Customer findById(String email) {

        try {
            Session session = ConnectionFactory.getSession();
            Transaction transaction = session.beginTransaction();
            Customer customer = session.get(Customer.class, email);
            transaction.commit();
            return customer;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionFactory.closeSession();
        }

    }
    public boolean update(Customer updatedCustomer) {
        try {
            Session session = ConnectionFactory.getSession();
            Transaction transaction = session.beginTransaction();
            session.merge(updatedCustomer);
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
            Customer customer = session.load(Customer.class, email);
            session.remove(customer);
            transaction.commit();
            return true;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionFactory.closeSession();
        }
    }

    public Customer authenticateCustomer(String email, String password){

        try {
            Session session = ConnectionFactory.getSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Customer where email= :email and password= :password");
            query.setParameter("email", email);
            query.setParameter("password", password);
            Customer customer = (Customer) query.uniqueResult();
            transaction.commit();
            return customer;
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
            Query query = session.createQuery("from Customer where email= :email");
            query.setParameter("email", email);
            Customer customer = (Customer) query.uniqueResult();
            transaction.commit();
            if(customer == null) return false;
            return true;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionFactory.closeSession();
        }
    }
}

