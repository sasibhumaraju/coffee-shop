package com.sasibhumaraju.DAO;

import com.sasibhumaraju.config.DataBaseConfig;
import com.sasibhumaraju.model.AppUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAO {

    private final SessionFactory sessionFactory;

    SessionFactory SessionFactory ;

    public UserDAO(DataBaseConfig dataBaseConfig) {
        this.sessionFactory = dataBaseConfig.getSessionFactory();
    }


    public void registerUser(AppUser appUser) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(appUser);
        tx.commit();
        session.close();
    }

    public AppUser getUserById(int id) {
        Session session = sessionFactory.openSession();
        AppUser appUser = session.get(AppUser.class, id);
        session.close();
        return appUser;
    }

    public List<AppUser> getAllUsers() {
        Session session = sessionFactory.openSession();
        Query<AppUser> q = session.createQuery("FROM AppUser", AppUser.class);
        List<AppUser> appUsers = q.getResultList();
        session.close();
        return appUsers;
    }

    public AppUser getUserByEmail(String email) {
        Session session = sessionFactory.openSession();
        Query<AppUser> q = session.createQuery("FROM AppUser WHERE email= :email", AppUser.class);
        q.setParameter("email", email);
        AppUser appUser = q.getSingleResult();
        session.close();
        return appUser;
    }


}
