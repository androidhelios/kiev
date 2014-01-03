package com.chadik.kiev.view.panel.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JButton;
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
import com.chadik.kiev.view.dialog.IDialogGeneric;
import com.chadik.kiev.view.dialog.IDialogTrader;
import com.chadik.kiev.view.panel.IPanelTrader;
import com.chadik.kiev.view.table.ITableGeneric;
import com.chadik.kiev.view.table.ITableTrader;

@Component
public class PanelTraderImpl extends PanelGenericImpl<Trader> implements
		IPanelTrader {

	private DefaultTableModel defaultTableModel;
	private JTable table;
	private List<Trader> traders;

	@Autowired
	private ITraderJpaService traderJpaServiceImpl;
	@Autowired
	private IDialogTrader dialogTraderImpl;

	@Override
	public Trader getTableEntity() {
		return null;
	}

	@Override
	public IDialogGeneric getDialog() {
		return dialogTraderImpl;
	}

	@Override
	public JPanel createPanelInfoHolderContentInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public JTable createTable() {
		defaultTableModel = new DefaultTableModel();
		table = new JTable();

		defaultTableModel.setColumnIdentifiers(getTableColumnNames());
		table.setModel(defaultTableModel);
		TableUtil.hideColumns(table, getTableHiddenColumns());
		TableUtil.allignCells(table, SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setMaxWidth(100);

		populateTable();

		return table;
	}

	public void populateTable() {

		traders = traderJpaServiceImpl.getAll();

		int i = 0;

		defaultTableModel.setRowCount(0);

		for (Trader trader : traders) {
			defaultTableModel
					.addRow(new String[] { Integer.toString(++i),
							trader.getTraderId().toString(),
							trader.getTraderName(),
							trader.getTraderRegistryNumber(),
							trader.getTraderBankName(),
							trader.getTraderBankAccount(),
							trader.getTraderAddress(),
							trader.getTraderPhoneNumber(),
							trader.getTraderEmail(),
							trader.getTraderAdditionalInfo() });
		}

		if (table.getRowCount() > 0) {
			table.setRowSelectionInterval(table.getRowCount() - 1,
					table.getRowCount() - 1);
		}

	}

	@Override
	public String[] getTableColumnNames() {
		return new String[] { "Реден Бр.", "Id", "Назив", "Регистарски Број",
				"Банка", "Банкарска Сметка", "Адреса", "Телефонски број",
				"Email", "Забелешки" };
	}

	@Override
	public int[] getTableHiddenColumns() {
		return new int[] { 1, 3, 4, 5, 6, 8, 9 };
	}

	@Override
	public void populatePanelInfoHolderContentInfo() {
		// TODO Auto-generated method stub
		
	}

}
