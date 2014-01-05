package com.chadik.kiev.dao;

import java.math.BigDecimal;
import java.util.List;

import com.chadik.kiev.model.Invoice;

public interface IInvoiceDao {

	public void saveInvoice(Invoice invoice);

	public void deleteInvoice(Invoice invoice);

	public List<Invoice> findAllInvoices();

	public Invoice findInvoiceById(BigDecimal id);

}
