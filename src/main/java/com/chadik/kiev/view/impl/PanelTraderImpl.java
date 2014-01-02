package com.chadik.kiev.view.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.chadik.kiev.dao.IGenericJpaDao;
import com.chadik.kiev.model.Trader;
import com.chadik.kiev.service.IGenericJpaService;
import com.chadik.kiev.service.ITraderJpaService;
import com.chadik.kiev.util.TableUtil;
import com.chadik.kiev.view.IPanelTrader;

@Component
public class PanelTraderImpl extends PanelGenericImpl<Trader> implements
		IPanelTrader {

	@Autowired
	private ITraderJpaService traderJpaServiceImpl;

	@Override
	public JPanel getPanel() {
		return this;
	}

	@Override
	public void populatePanelInfoHolderContentInfo(
			JPanel panelInfoHolderContentInfo) {

	}

	@Override
	public void populateTable(JTable table,
			DefaultTableModel defaultTableModel, List<Trader> traders) {

		defaultTableModel.setRowCount(0);

		for (Trader trader : traders) {
			defaultTableModel.addRow(new String[] {
					trader.getTraderId().toString(), trader.getTraderName(),
					trader.getTraderBankName(), trader.getTraderBankAccount(),
					trader.getTraderAddress() });
		}

	}

	@Override
	public String[] getColumnNames() {
		return new String[] { "id", "Name", "BankName", "BankAccount",
				"Address" };
	}

	@Override
	public IGenericJpaService getGenericJpaService() {
		return traderJpaServiceImpl;
	}

	@Override
	public Trader getTableInfo() {
		return null;
	}

	@Override
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

}
