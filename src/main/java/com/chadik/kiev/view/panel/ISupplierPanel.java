package com.chadik.kiev.view.panel;

import javax.swing.JPanel;

public interface ISupplierPanel {

	public JPanel initSupplierPanel();

	public void populateSupplierTable();
	
	public void setSelectedSupplierTableRow(String selectedSupplierTableRow);
	
	public String getSelectedSupplierTableRow();
	
	public void setSupplierTableButtonsEnabled();

}
