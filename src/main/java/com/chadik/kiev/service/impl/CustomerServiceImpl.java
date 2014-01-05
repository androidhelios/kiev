package com.chadik.kiev.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chadik.kiev.dao.ICustomerDao;
import com.chadik.kiev.model.Customer;
import com.chadik.kiev.service.ICustomerService;
import com.chadik.kiev.util.HibernateUtil;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private ICustomerDao customerDaoImpl;

	@Override
	public void saveCustomer(Customer customer) {
		try {
			HibernateUtil.beginTransaction();
			customerDaoImpl.saveCustomer(customer);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
			HibernateUtil.rollbackTransaction();
		}
	}

	@Override
	public void deleteCustomer(Customer customer) {
		try {
			HibernateUtil.beginTransaction();
			customerDaoImpl.deleteCustomer(customer);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
			HibernateUtil.rollbackTransaction();
		}
	}
	
	@Override
	public List<Customer> findAllCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		try {
			HibernateUtil.beginTransaction();
			customers = customerDaoImpl.findAllCustomers();
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
		}
		return customers;
	}
	
	@Override
	public Customer findCustomerById(BigDecimal id) {
		Customer customer = null;
		try {
			HibernateUtil.beginTransaction();
			customer = (Customer) customerDaoImpl.findCustomerById(id);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
		}
		return customer;
	}

}