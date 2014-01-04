package com.chadik.kiev.view.panel;

import javax.swing.JPanel;
import javax.swing.JTable;

public interface IPanelGeneric<T> {
	
	public JPanel initPanel();
	
	public JTable createTable();
	
	public void populateTable();
	
	public JPanel createPanelInfoHolderContentInfo();

}
