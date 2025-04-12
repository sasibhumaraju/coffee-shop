package com.sasibhumaraju.config;

import com.sasibhumaraju.model.Coffee;
import com.sasibhumaraju.model.CoffeeOrder;
import com.sasibhumaraju.model.AppUser;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DataBaseConfig {

    private SessionFactory sessionFactory;

    public DataBaseConfig() {
        try {
            this.sessionFactory = new Configuration()
                    .addAnnotatedClass(AppUser.class)
                    .addAnnotatedClass(Coffee.class)
                    .addAnnotatedClass(CoffeeOrder.class)
                    .configure()
                    .buildSessionFactory();
        } catch (HibernateException e) {
            System.out.println(e);
            System.out.println("------------------- Failed to connect to DB -----");
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
