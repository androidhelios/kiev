package com.chadik.kiev.view.panel;

import javax.swing.JPanel;

import com.chadik.kiev.view.table.ITableGeneric;

public interface IPanelGeneric<T> {
	
	public JPanel initPanel();
	
	public ITableGeneric getPanelTable();

}
