package com.chadik.kiev.dao.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import com.chadik.kiev.dao.IInvoiceJpaDao;
import com.chadik.kiev.model.Invoice;

@Repository
public class InvoiceJpaDaoImpl extends GenericJpaDaoImpl<Invoice, BigDecimal>
		implements IInvoiceJpaDao {

	@Override
	public Class getClazz() {
		return Invoice.class;
	}
}
