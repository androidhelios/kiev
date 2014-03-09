package com.chadik.kiev.view.panel;

import javax.swing.JPanel;

public interface IPasswordPanel {
	
	public JPanel initPasswordPanel();
	
	public String getSupplierUserName();
	
	public String getSupplierPassword();
	
	public void setSupplierUserName(String supplierUserName);
	
	public void setSupplierPassword(String supplierPassword);

}
