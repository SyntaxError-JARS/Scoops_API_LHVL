package com.revature.scoops.util;


import com.revature.scoops.models.CreditCard;
import com.revature.scoops.models.Customer;
import com.revature.scoops.models.Menu;
import com.revature.scoops.models.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.util.Properties;

public class ConnectionFactory {

    private static SessionFactory sessionFactory;
    private static Session session;

    public static Session getSession() throws IOException {
        if(sessionFactory == null) {
            Configuration configuration = new Configuration();
            Properties props = new Properties();
//            String url = System.getenv("SQLAZURECONNSTR_PokeProjectDB");
//            String username = System.getenv("DBUSER");
//            String password = System.getenv("DBPASS");

/*hibernate.dialect=org.hibernate.dialect.SQLServerDialect
hibernate.connection.driver_class=com.microsoft.sqlserver.jdbc.SQLServerDriver
hibernate.show_sql=true
# leverage create once and update thereafter
hibernate.hbm2ddl.auto=update*/

//            configuration.setProperty("hibernate.connection.url", url);
//            configuration.setProperty("hibernate.connection.username", username);
//            configuration.setProperty("hibernate.connection.password", password);
//            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
//            configuration.setProperty("hibernate.connection.driver_class", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            configuration.setProperty("hibernate.show_sql", "true");
//            configuration.setProperty("hibernate.hbm2ddl.auto", "update");

            // Add properties to our configuration

            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            props.load(loader.getResourceAsStream("hibernate.properties"));
            configuration.setProperties(props);
            // ONE ADDITIONAL STEP I NEED TO INCLUDE
            configuration.addAnnotatedClass(Customer.class);
            configuration.addAnnotatedClass(Menu.class);
            configuration.addAnnotatedClass(Order.class);
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
