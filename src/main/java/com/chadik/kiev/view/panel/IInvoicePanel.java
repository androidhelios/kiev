package com.chadik.kiev.view.panel;

import javax.swing.JPanel;

public interface IInvoicePanel {

	public JPanel initInvoicePanel();
	
	public void populateInvoiceTable();
	
	public void setProductButtonsEnabled();
	
	public void setSelectedInvoiceTableRow(String selectedInvoiceTableRow);
	
	public String getSelectedInvoiceTableRow();

}