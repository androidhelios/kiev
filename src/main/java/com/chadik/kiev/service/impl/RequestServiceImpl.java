package com.chadik.kiev.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chadik.kiev.dao.IRequestDao;
import com.chadik.kiev.model.Request;
import com.chadik.kiev.service.IRequestService;
import com.chadik.kiev.util.HibernateUtil;

@Service
public class RequestServiceImpl implements IRequestService {

	@Autowired
	private IRequestDao requestDaoImpl;

	@Override
	public void saveRequest(Request request) {
		try {
			HibernateUtil.beginTransaction();
			requestDaoImpl.saveRequest(request);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
			HibernateUtil.rollbackTransaction();
		}
	}

	@Override
	public void deleteRequest(Request request) {
		try {
			HibernateUtil.beginTransaction();
			requestDaoImpl.deleteRequest(request);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
			HibernateUtil.rollbackTransaction();
		}
	}

	@Override
	public List<Request> findAllRequests() {
		List<Request> requests = new ArrayList<Request>();
		try {
			HibernateUtil.beginTransaction();
			requests = requestDaoImpl.findAllRequests();
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
		}
		return requests;
	}

	@Override
	public Request findRequestById(BigDecimal id) {
		Request request = null;
		try {
			HibernateUtil.beginTransaction();
			request = (Request) requestDaoImpl.findRequestById(id);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
		}
		return request;
	}

}