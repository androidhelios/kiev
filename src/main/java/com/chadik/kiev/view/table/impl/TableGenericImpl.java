package com.chadik.kiev.view.table.impl;

import java.util.List;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chadik.kiev.service.IGenericJpaService;
import com.chadik.kiev.service.ITraderJpaService;
import com.chadik.kiev.util.TableUtil;
import com.chadik.kiev.view.table.ITableGeneric;

@Component
public abstract class TableGenericImpl<T> implements ITableGeneric<T> {

	private DefaultTableModel defaultTableModel;
	private JTable table;
	
	private IGenericJpaService genericJpaService;

	public JTable initTable() {
		defaultTableModel = new DefaultTableModel();
		table = new JTable();

		defaultTableModel.setColumnIdentifiers(getColumnsNames());
		table.setModel(defaultTableModel);
		TableUtil.hideColumns(table, getHiddenColumns());
		TableUtil.allignCells(table, SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setMaxWidth(100);
		
		populateTable();

		return table;
	}
	
	public DefaultTableModel getDefaultTableModel() {
		return defaultTableModel;
	}

	public void setDefaultTableModel(DefaultTableModel defaultTableModel) {
		this.defaultTableModel = defaultTableModel;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public abstract void populateTable();

	public abstract String[] getColumnsNames();

	public abstract int[] getHiddenColumns();
	
	public abstract IGenericJpaService getGenericJpaService();
	
}
