package com.chadik.kiev.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.chadik.kiev.dao.IInvoiceDao;
import com.chadik.kiev.model.Invoice;
import com.chadik.kiev.util.HibernateUtil;

@Repository
public class InvoiceDaoImpl implements IInvoiceDao {

	@Override
	public void saveInvoice(Invoice invoice) {
		HibernateUtil.getSession().saveOrUpdate(invoice);
	}

	@Override
	public void deleteInvoice(Invoice invoice) {
		HibernateUtil.getSession().delete(invoice);
	}

	@Override
	public Invoice findInvoiceById(BigDecimal id) {
		Invoice invoice = null;
		invoice = (Invoice) HibernateUtil.getSession().get(Invoice.class, id);
		return invoice;
	}

	@Override
	public List<Invoice> findAllInvoices() {
		List<Invoice> invoices = null;
		Query query = HibernateUtil.getSession().createQuery(
				"from " + Invoice.class.getName() + " order by invoiceId");
		invoices = query.list();
		return invoices;
	}
}
