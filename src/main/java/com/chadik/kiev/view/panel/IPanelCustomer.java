package com.chadik.kiev.view.panel;

import com.chadik.kiev.model.Customer;

public interface IPanelCustomer extends IPanelGeneric<Customer> {
	
	public abstract String[] getTableCustomerColumnNames();
	
	public int[] getTableCustomerHiddenColumns();
	
	public void populateTableCustomer();

}