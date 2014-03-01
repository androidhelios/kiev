package com.chadik.kiev.view.panel;

import javax.swing.JPanel;

import com.chadik.kiev.model.Invoice;

public interface IOrderItemPanel {

	public JPanel initOrderItemPanel();

	public void populateOrderItemTable();
	
	public Invoice getInvoice();
	
	public void setInvoice(Invoice invoice);

}