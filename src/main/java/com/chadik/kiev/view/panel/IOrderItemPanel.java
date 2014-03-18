package com.chadik.kiev.view.panel;

import java.math.BigDecimal;

import javax.swing.JPanel;
import javax.swing.JTable;

import com.chadik.kiev.model.Invoice;
import com.chadik.kiev.model.OrderItem;

public interface IOrderItemPanel {

	public JPanel initOrderItemPanel();

	public void populateOrderItemTable();
	
	public int getInvoiceId();
	
	public void setInvoiceId(int invoiceId);
	
	public JTable getOrderItemTable();
	
	public void setOrderItemTable(JTable orderItemTable);
	
	public OrderItem getOrderItemFromOrderItemTable(String selectedRowOrderItemId);
	
	public void deleteOrderItem();
	
	public String getSelectedOrderItemTableRow();
	
	public void setSelectedOrderItemTableRow(String selectedCustomerTableRow);

}