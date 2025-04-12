package com.sasibhumaraju.DAO;

import com.sasibhumaraju.config.DataBaseConfig;
import com.sasibhumaraju.model.Coffee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Queue;

public class CoffeeDAO {

    private final SessionFactory sessionFactory;

    CoffeeDAO( DataBaseConfig dataBaseConfig) {
        this.sessionFactory = dataBaseConfig.getSessionFactory();
    }

    public void saveCoffee(Coffee coffee) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(coffee);
        tx.commit();
        session.close();
    }

    public void updateCoffee(Coffee coffee) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.merge(coffee);
        tx.commit();
        session.close();
    }

    public void deleteCoffee(Coffee coffee) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.remove(coffee);
        tx.commit();
        session.close();
    }

    public Coffee getCoffee(int id) {
        Session session = sessionFactory.openSession();
        Coffee coffee = session.get(Coffee.class, id);
        session.close();
        return coffee;
    }

    public List<Coffee> getAllCoffees() {
        Session session = sessionFactory.openSession();
        Query<Coffee> q = session.createQuery("FROM Coffee", Coffee.class);
        List<Coffee> coffees = q.getResultList();
        session.close();
        return coffees;
    }
}
