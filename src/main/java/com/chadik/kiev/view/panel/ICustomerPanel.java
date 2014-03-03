package com.chadik.kiev.view.panel;

import javax.swing.JPanel;

public interface ICustomerPanel {

	public JPanel initCustomerPanel();

	public void populateCustomerTable();
	
	public void setSelectedCustomerTableRow(String selectedCustomerTableRow);
	
	public String getSelectedCustomerTableRow();

}