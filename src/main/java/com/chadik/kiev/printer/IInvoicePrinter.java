package com.chadik.kiev.printer;

import com.chadik.kiev.model.Invoice;

public interface IInvoicePrinter {

	public void initInvoicePrinter();

	public Invoice getInvoice();

	public void setInvoice(Invoice invoice);

}
