package com.chadik.kiev.view.panel;

import javax.swing.JPanel;

public interface IBankInfoPanel {
	
	public JPanel initBankInfoPanel();

	public void populateBankInfoTable();
	
	public void setSelectedBankInfoTableRow(String selectedBankInfoTableTableRow);
	
	public String getSelectedBankInfoTableRow();
	
	public void setBankInfoTableButtonsEnabled();

}
