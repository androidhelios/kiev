package com.chadik.kiev.view.table;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public interface ITableGeneric<T> {
	
	public JTable initTable();

	public void populateTable(JTable table, DefaultTableModel defaultTableModel,
			List<T> t);

}
