package com.chadik.kiev.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author ivan.chadikovski
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     *
     * @return
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     *
     * @return
     */
    public static Session beginTransaction() {
        Session hibernateSession = getSession();
        hibernateSession.beginTransaction();
        return hibernateSession;
    }

    /**
     *
     */
    public static void commitTransaction() {
        getSession().getTransaction().commit();
    }

    /**
     *
     */
    public static void rollbackTransaction() {
        getSession().getTransaction().rollback();
    }

    /**
     *
     */
    public static void closeSession() {
        getSession().close();
    }

    /**
     *
     * @return
     */
    public static Session getSession() {
        Session hibernateSession = getSessionFactory().getCurrentSession();
        return hibernateSession;
    }

    /**
     *
     */
    public static void shutdown() {
        getSessionFactory().close();
    }
}
