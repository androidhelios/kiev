package com.chadik.kiev.service;

import java.math.BigDecimal;
import java.util.List;

import com.chadik.kiev.model.Invoice;

public interface IInvoiceService {

	public void saveInvoice(Invoice invoice);

	public void deleteInvoice(Invoice invoice);

	public List<Invoice> findAllInvoices();

	public Invoice findInvoiceById(BigDecimal id);

}
