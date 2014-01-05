package com.chadik.kiev.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chadik.kiev.dao.IInvoiceDao;
import com.chadik.kiev.model.Invoice;
import com.chadik.kiev.service.IInvoiceService;
import com.chadik.kiev.util.HibernateUtil;

@Service
public class InvoiceServiceImpl implements IInvoiceService {

	@Autowired
	private IInvoiceDao invoiceDaoImpl;

	@Override
	public void saveInvoice(Invoice invoice) {
		try {
			HibernateUtil.beginTransaction();
			invoiceDaoImpl.saveInvoice(invoice);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
			HibernateUtil.rollbackTransaction();
		}
	}

	@Override
	public void deleteInvoice(Invoice invoice) {
		try {
			HibernateUtil.beginTransaction();
			invoiceDaoImpl.deleteInvoice(invoice);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
			HibernateUtil.rollbackTransaction();
		}
	}

	@Override
	public List<Invoice> findAllInvoices() {
		List<Invoice> invoices = new ArrayList<Invoice>();
		try {
			HibernateUtil.beginTransaction();
			invoices = invoiceDaoImpl.findAllInvoices();
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
		}
		return invoices;
	}

	@Override
	public Invoice findInvoiceById(BigDecimal id) {
		Invoice invoice = null;
		try {
			HibernateUtil.beginTransaction();
			invoice = (Invoice) invoiceDaoImpl.findInvoiceById(id);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
		}
		return invoice;
	}

}