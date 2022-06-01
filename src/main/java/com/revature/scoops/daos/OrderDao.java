package com.revature.scoops.daos;

import com.revature.scoops.models.Orders;
import com.revature.scoops.util.ConnectionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.*;
import java.util.List;
public class OrderDao {

    public Orders create(Orders newOrder) {
        try {
            Session session = ConnectionFactory.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(newOrder);
            transaction.commit();
            return newOrder;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionFactory.closeSession();
        }
    }


    public List<Orders> findAll() {
        try {
            Session session = ConnectionFactory.getSession();
            Transaction transaction = session.beginTransaction();
            List<Orders> orders = session.createQuery("FROM Orders").list();
            transaction.commit();
            return orders;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionFactory.closeSession();
        }

    }


    public Orders findById(String id) {

        try {
            Session session = ConnectionFactory.getSession();
            Transaction transaction = session.beginTransaction();
            Orders order = session.get(Orders.class, Integer.valueOf(id));
            transaction.commit();
            return order;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionFactory.closeSession();
        }

    }
    public boolean update(Orders updatedOrder) {
        try {
            Session session = ConnectionFactory.getSession();
            Transaction transaction = session.beginTransaction();
            session.merge(updatedOrder);
            transaction.commit();
            return true;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionFactory.closeSession();
        }
    }


    public boolean delete(String id) {
        try {
            Session session = ConnectionFactory.getSession();
            Transaction transaction = session.beginTransaction();
            Orders order = session.load(Orders.class, Integer.valueOf(id));
            session.remove(order);
            transaction.commit();
            return true;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionFactory.closeSession();
        }
    }

    public Orders authenticateOrder(String id, String password){

        try {
            Session session = ConnectionFactory.getSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Orders where id= :id and password= :password");
            query.setParameter("id", id);
            query.setParameter("password", password);
            Orders order = (Orders) query.uniqueResult();
            transaction.commit();
            return order;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionFactory.closeSession();
        }

    }
    public boolean checkId(String id) {

        try {
            Session session = ConnectionFactory.getSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Orders where id= :id");
            query.setParameter("id", id);
            Orders order = (Orders) query.uniqueResult();
            transaction.commit();
            if(order == null) return false;
            return true;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionFactory.closeSession();
        }
    }
}