package com.chadik.kiev.dao.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import com.chadik.kiev.dao.ICustomerJpaDao;
import com.chadik.kiev.model.Customer;

@Repository
public class CustomerJpaDaoImpl extends GenericJpaDaoImpl<Customer, BigDecimal>
		implements ICustomerJpaDao {

	@Override
	public Class getClazz() {
		return Customer.class;
	}
}