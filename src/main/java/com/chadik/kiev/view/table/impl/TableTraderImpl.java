package com.chadik.kiev.view.table.impl;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.chadik.kiev.model.Trader;
import com.chadik.kiev.view.table.ITableTrader;

public class TableTraderImpl extends TableGenericImpl<Trader> implements
		ITableTrader {

	@Override
	public void populateTable(JTable table,
			DefaultTableModel defaultTableModel, List<Trader> traders) {

		int i = 0;

		defaultTableModel.setRowCount(0);

		for (Trader trader : traders) {
			defaultTableModel.addRow(new String[] { Integer.toString(++i),
					trader.getTraderId().toString(), trader.getTraderName(),
					trader.getTraderBankName(), trader.getTraderBankAccount(),
					trader.getTraderAddress() });
		}

		if (table.getRowCount() > 0) {
			table.setRowSelectionInterval(table.getRowCount() - 1,
					table.getRowCount() - 1);
		}

	}

	@Override
	public String[] getColumnsNames() {
		return new String[] { "No", "id", "Name", "BankName", "BankAccount",
		"Address" };
	}

	@Override
	public int[] getHiddenColumns() {
		return new int[] {2, 3, 4};
	}

}
