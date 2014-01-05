package com.chadik.kiev.dao;

import java.math.BigDecimal;
import java.util.List;

import com.chadik.kiev.model.Customer;

public interface ICustomerDao {
	
	public void saveCustomer(Customer customer);

	public void deleteCustomer(Customer customer);

	public List<Customer> findAllCustomers();

	public Customer findCustomerById(BigDecimal id);

}
