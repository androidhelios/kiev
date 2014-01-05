package com.chadik.kiev.dao.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.chadik.kiev.dao.IGenericJpaDao;
import com.chadik.kiev.util.HibernateUtil;

@Repository
public abstract class GenericJpaDaoImpl<T, ID extends Serializable> implements
		IGenericJpaDao<T, ID> {

	private Class clazz;

	public Session getSession() {
		return HibernateUtil.getSession();
	}

	@Override
	public void save(T t) {
		Session hibernateSession = getSession();
		hibernateSession.saveOrUpdate(t);
	}

	@Override
	public void merge(T t) {
		Session hibernateSession = getSession();
		hibernateSession.merge(t);
	}

	@Override
	public void delete(T t) {
		Session hibernateSession = getSession();
		hibernateSession.delete(t);
	}

	@Override
	public List<T> findMany(Query query) {
		List<T> t;
		t = (List<T>) query.list();
		return t;
	}

	@Override
	public T findOne(Query query) {
		T t;
		t = (T) query.uniqueResult();
		return t;
	}

	@Override
	public T findByID(BigDecimal id) {
		Session hibernateSession;
		hibernateSession = getSession();
		T t = null;
		t = (T) hibernateSession.get(getClazz(), id);
		return t;
	}

	@Override
	public List findAll() {
		Session hibernateSession = getSession();
		List T = null;
		Query query = hibernateSession.createQuery("from "
				+ getClazz().getName());
		T = query.list();
		return T;
	}

	public abstract Class getClazz();

	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}

}