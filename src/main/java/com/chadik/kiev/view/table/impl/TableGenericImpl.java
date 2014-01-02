package com.chadik.kiev.view.table.impl;

import java.util.List;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.chadik.kiev.util.TableUtil;
import com.chadik.kiev.view.table.ITableGeneric;

public abstract class TableGenericImpl<T> implements ITableGeneric<T> {

	DefaultTableModel defaultTableModel;
	private JTable table;

	public JTable initTable() {
		defaultTableModel = new DefaultTableModel();
		table = new JTable();

		defaultTableModel.setColumnIdentifiers(getColumnsNames());
		table.setModel(defaultTableModel);
		TableUtil.hideColumns(table, getHiddenColumns());
		TableUtil.allignCells(table, SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setMaxWidth(100);

		return table;
	}

	public abstract void populateTable(JTable table,
			DefaultTableModel defaultTableModel, List<T> t);

	public abstract String[] getColumnsNames();

	public abstract int[] getHiddenColumns();
	
}
