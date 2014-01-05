package com.chadik.kiev.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.chadik.kiev.dao.ICustomerDao;
import com.chadik.kiev.model.Customer;
import com.chadik.kiev.util.HibernateUtil;

@Repository
public class CustomerDaoImpl implements ICustomerDao {

	@Override
	public void saveCustomer(Customer customer) {
		HibernateUtil.getSession().saveOrUpdate(customer);
	}

	@Override
	public void deleteCustomer(Customer customer) {
		HibernateUtil.getSession().delete(customer);
	}

	@Override
	public Customer findCustomerById(BigDecimal id) {
		Customer customer = null;
		customer = (Customer) HibernateUtil.getSession()
				.get(Customer.class, id);
		return customer;
	}

	@Override
	public List<Customer> findAllCustomers() {
		List<Customer> customers = null;
		Query query = HibernateUtil.getSession().createQuery(
				"from " + Customer.class.getName());
		customers = query.list();
		return customers;
	}

}