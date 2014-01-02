package com.chadik.kiev.view.table;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public interface ITableGeneric<T> {
	
	public void createTable(JTable table, DefaultTableModel defaultTableModel,
			String[] columnNames);

	public void populateTable(JTable table, DefaultTableModel defaultTableModel,
			List<T> t);

}
