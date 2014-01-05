package com.chadik.kiev.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.chadik.kiev.dao.IRequestDao;
import com.chadik.kiev.model.Request;
import com.chadik.kiev.util.HibernateUtil;

@Repository
public class RequestDaoImpl implements IRequestDao {

	@Override
	public void saveRequest(Request request) {
		HibernateUtil.getSession().saveOrUpdate(request);
	}

	@Override
	public void deleteRequest(Request request) {
		HibernateUtil.getSession().delete(request);
	}

	@Override
	public Request findRequestById(BigDecimal id) {
		Request request = null;
		request = (Request) HibernateUtil.getSession().get(Request.class, id);
		return request;
	}

	@Override
	public List<Request> findAllRequests() {
		List<Request> requests = null;
		Query query = HibernateUtil.getSession().createQuery(
				"from " + Request.class.getName());
		requests = query.list();
		return requests;
	}

}