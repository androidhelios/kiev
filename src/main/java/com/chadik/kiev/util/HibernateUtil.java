package com.chadik.kiev.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class HibernateUtil {

	private HibernateUtil() {

	}

	private static final SessionFactory sessionFactory = buildSessionFactory();

	public static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static Session beginTransaction() {
		Session hibernateSession = getSession();
		hibernateSession.beginTransaction();
		return hibernateSession;
	}

	public static void commitTransaction() {
		getSession().getTransaction().commit();
	}

	public static void rollbackTransaction() {
		getSession().getTransaction().rollback();
	}

	public static void closeSession() {
		getSession().close();
	}

	public static Session getSession() {
		Session hibernateSession = getSessionFactory().getCurrentSession();
		return hibernateSession;
	}

	public static void shutdown() {
		getSessionFactory().close();
	}
}
