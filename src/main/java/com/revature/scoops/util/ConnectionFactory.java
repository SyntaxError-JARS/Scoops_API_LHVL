package com.revature.scoops.util;

import com.revature.scoops.models.CreditCard;
import com.revature.scoops.models.Customer;
import com.revature.scoops.models.Menu;
import com.revature.scoops.models.Order;


import java.io.IOException;
import java.util.Properties;

public class ConnectionFactory {
    private static SessionFactory sessionFactory;
    private static Session session;

    public static Session getSession() throws IOException {
        if(sessionFactory == null) {
            Configuration configuration = new Configuration();
            Properties props = new Properties();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            props.load(loader.getResourceAsStream("hibernate.properties"));

            // Add properties to our configuration
            configuration.setProperties(props);
            // ONE ADDITIONAL STEP I NEED TO INCLUDE
            configuration.addAnnotatedClass(Order.class);
            configuration.addAnnotatedClass(Menu.class);
            configuration.addAnnotatedClass(Customer.class);
            configuration.addAnnotatedClass(CreditCard.class);

            // ServiceRegistry
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }

        if(session == null) {
            session = sessionFactory.openSession();
        }

        return session;
    }

    public static void closeSession() {
        session.close();
        session = null;

    }
}

