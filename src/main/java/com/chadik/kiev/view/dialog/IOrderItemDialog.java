package com.chadik.kiev.view.dialog;

import javax.swing.JDialog;

import com.chadik.kiev.model.Invoice;

public interface IOrderItemDialog {
	
	public JDialog initOrderItemDialog();
	
	public Invoice getInvoice();
	
	public void setInvoice(Invoice invoice);

}
