package com.chadik.kiev.view.dialog;

import javax.swing.JDialog;

import com.chadik.kiev.model.Invoice;

public interface IOrderItemDialog {
	
	public JDialog initOrderItemDialog();
	
	public int getInvoiceId();
	
	public void setInvoiceId(int invoiceId);

}
