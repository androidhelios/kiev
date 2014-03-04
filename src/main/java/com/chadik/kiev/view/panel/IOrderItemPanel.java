package com.chadik.kiev.view.panel;

import javax.swing.JPanel;
import javax.swing.JTable;

import com.chadik.kiev.model.Invoice;

public interface IOrderItemPanel {

	public JPanel initOrderItemPanel();

	public void populateOrderItemTable();
	
	public Invoice getInvoice();
	
	public void setInvoice(Invoice invoice);
	
	public JTable getOrderItemTable();
	
	public void setOrderItemTable(JTable orderItemTable);

}