package com.sasibhumaraju.DAO;

import com.sasibhumaraju.config.DataBaseConfig;
import com.sasibhumaraju.model.CoffeeOrder;
import com.sasibhumaraju.model.Status;
import com.sasibhumaraju.model.AppUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CoffeeOrderDAO {

    private final SessionFactory sessionFactory;
    private final UserDAO userDAO;

    public CoffeeOrderDAO(DataBaseConfig dataBaseConfig, UserDAO userDAO) {
        this.sessionFactory = dataBaseConfig.getSessionFactory();
        this.userDAO = userDAO;
    }

    public void placeOrder(CoffeeOrder coffeeOrder) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(coffeeOrder);
        tx.commit();
        session.close();
    }

    public CoffeeOrder getOrder(int id) {
        Session session = sessionFactory.openSession();
        CoffeeOrder coffeeOrder = session.get(CoffeeOrder.class, id);
        session.close();
        return coffeeOrder;
    }

    public List<CoffeeOrder> getAllActiveOrdersByEmail(String email ) {
        Session session = sessionFactory.openSession();
        AppUser appUser = userDAO.getUserByEmail(email);
        Query<CoffeeOrder> q = session.createQuery("FROM CoffeeOrder co WHERE co.appUser = :user AND co.status != :status", CoffeeOrder.class);
        q.setParameter("user", appUser);
        q.setParameter("status", Status.PAID);
        List<CoffeeOrder> coffeeOrders = q.getResultList();
//        System.out.println("list is : " + coffeeOrders.size() + " " + appUser);
        session.close();
        return coffeeOrders;
    }

    public List<CoffeeOrder> getAllActiveOrdersInShop() {
        Session session = sessionFactory.openSession();
        Query<CoffeeOrder> q = session.createQuery("FROM CoffeeOrder co WHERE co.status != :status", CoffeeOrder.class);
        q.setParameter("status", Status.PAID);
        List<CoffeeOrder> coffeeOrders = q.getResultList();
        session.close();
        return coffeeOrders;
    }

    public List<CoffeeOrder> getAllOrdersHistoryByEmail(String email ) {
        Session session = sessionFactory.openSession();
        AppUser appUser = userDAO.getUserByEmail(email);
        Query<CoffeeOrder> q = session.createQuery("FROM CoffeeOrder co WHERE co.appUser = :user", CoffeeOrder.class);
        q.setParameter("user", appUser);
        List<CoffeeOrder> coffeeOrders = q.getResultList();
        session.close();
        return coffeeOrders;
    }

    public List<CoffeeOrder> getAllOrdersHistoryInShop() {
        Session session = sessionFactory.openSession();
        Query<CoffeeOrder> q = session.createQuery("FROM CoffeeOrder", CoffeeOrder.class);
        List<CoffeeOrder> coffeeOrders = q.getResultList();
        session.close();
        return coffeeOrders;
    }

    public void updateOrder(CoffeeOrder coffeeOrder) {
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        session.merge(coffeeOrder);
        t.commit();
        session.close();
    }

}
