package com.chadik.kiev.view.panel;

import javax.swing.JPanel;

public interface IInvoicePanel {

	public JPanel initInvoicePanel();
	
	public void populateInvoiceTable();
	
	public int getSelectedComboBoxCustomer();
	
	public void setSelectedComboBoxCustomer(int selectedComboBoxCustomerIndex);
	
	public int getSelectedComboBoxPaymentInfo();
	
	public void setSelectedComboBoxPaymentInfo(int selectedComboBoxPaymentInfoIndex);

}