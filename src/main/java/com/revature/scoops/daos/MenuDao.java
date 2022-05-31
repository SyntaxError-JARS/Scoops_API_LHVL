package com.revature.scoops.daos;

import com.revature.scoops.models.Menu;
import com.revature.scoops.models.Menu;
import com.revature.scoops.util.ConnectionFactory;
import com.revature.scoops.util.Crudable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class MenuDao {
    public Menu create(Menu newMenu) {
        try {
            Session session = ConnectionFactory.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(newMenu);
            transaction.commit();
            return newMenu;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionFactory.closeSession();
        }
    }


    public List<Menu> findAll() {
        try {
            Session session = ConnectionFactory.getSession();
            Transaction transaction = session.beginTransaction();
            List<Menu> menus = session.createQuery("FROM Menu").list();
            transaction.commit();
            return menus;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionFactory.closeSession();
        }

    }


    public Menu findById(String email) {

        try {
            Session session = ConnectionFactory.getSession();
            Transaction transaction = session.beginTransaction();
            Menu menu = session.get(Menu.class, email);
            transaction.commit();
            return menu;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionFactory.closeSession();
        }

    }
    public boolean update(Menu updatedMenu) {
        try {
            Session session = ConnectionFactory.getSession();
            Transaction transaction = session.beginTransaction();
            session.merge(updatedMenu);
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
            Menu menu = session.load(Menu.class, email);
            session.remove(menu);
            transaction.commit();
            return true;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionFactory.closeSession();
        }
    }

    public Menu authenticateMenu(String email, String password){

        try {
            Session session = ConnectionFactory.getSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Menu where email= :email and password= :password");
            query.setParameter("email", email);
            query.setParameter("password", password);
            Menu menu = (Menu) query.uniqueResult();
            transaction.commit();
            return menu;
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
            Query query = session.createQuery("from Menu where email= :email");
            query.setParameter("email", email);
            Menu menu = (Menu) query.uniqueResult();
            transaction.commit();
            if(menu == null) return false;
            return true;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionFactory.closeSession();
        }
    }
}