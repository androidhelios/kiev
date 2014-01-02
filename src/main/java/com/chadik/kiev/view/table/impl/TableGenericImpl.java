package com.chadik.kiev.view.table.impl;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.chadik.kiev.util.TableUtil;
import com.chadik.kiev.view.table.ITableGeneric;

public abstract class TableGenericImpl<T> extends JTable implements ITableGeneric<T> {
	
	public void createTable(JTable table, DefaultTableModel defaultTableModel,
			String[] columnNames) {
		
		defaultTableModel.setColumnIdentifiers(columnNames);

		table.setModel(defaultTableModel);

		TableUtil.hideColumn(table, 2);
		TableUtil.hideColumn(table, 3);
		TableUtil.hideColumn(table, 4);

		TableUtil.centerCells(table);

		table.getColumnModel().getColumn(0).setMaxWidth(100);
	}

	public abstract void populateTable(JTable table, DefaultTableModel defaultTableModel,
			List<T> t);
}
