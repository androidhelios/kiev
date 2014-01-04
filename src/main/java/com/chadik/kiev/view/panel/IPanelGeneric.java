package com.chadik.kiev.view.panel;

import javax.swing.JPanel;
import javax.swing.JTable;

import com.chadik.kiev.view.table.ITableGeneric;

public interface IPanelGeneric<T> {
	
	public JPanel initPanel();
	
	public JTable createTable();
	
	public void populateTable();
	
	public JPanel createPanelInfoHolderContentInfo();

}
