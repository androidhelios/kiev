package com.chadik.kiev.view.panel;

import javax.swing.JPanel;

public interface IProductPanel {

	public JPanel initProductPanel();

	public void populateProductTable();
	
	public void setSelectedProductTableRow(String selectedProductTableRow);
	
	public String getSelectedProductTableRow();
	
	public void setProductTableButtonsEnabled();

}