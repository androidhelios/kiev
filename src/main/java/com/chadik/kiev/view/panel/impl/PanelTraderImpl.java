package com.chadik.kiev.view.panel.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
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

	private JPanel panelInfoHolderContentInfo;
	
	private JLabel labelTraderId;
	private JLabel labelTraderName;
	private JLabel labelTraderRegistryNumber;
	private JLabel labelTraderBankName;
	private JLabel labelTraderBankAccount;
	private JLabel labelTraderAddress;
	private JLabel labelTraderPhoneNumber;
	private JLabel labelTraderEmail;
	private JLabel labelTraderAdditionalInfo;

	private JTextField textFieldTraderId;
	private JTextField textFieldTraderName;
	private JTextField textFieldTraderRegistryNumber;
	private JTextField textFieldTraderBankName;
	private JTextField textFieldTraderBankAccount;
	private JTextField textFieldTraderAddress;
	private JTextField textFieldTraderPhoneNumber;
	private JTextField textFieldTraderEmail;
	private JTextField textFieldTraderAdditionalInfo;
	
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
		panelInfoHolderContentInfo = new JPanel();
		panelInfoHolderContentInfo.setLayout(null);
		panelInfoHolderContentInfo.setPreferredSize(new Dimension(400, 550));
		
		int spacing = 5;
		int weightLabel = 125;
		int height = 25;
		int weightTextField = 250;
		int xLabel = 10;
		int xTextField = xLabel + weightLabel + spacing;
		int y = 25;

		labelTraderName = new JLabel("Име:");
		labelTraderName.setBounds(xLabel, y, weightLabel, height);

		textFieldTraderName = new JTextField();
		textFieldTraderName.setBounds(xTextField, y, weightTextField, height);

		y = y + height + spacing;

		labelTraderRegistryNumber = new JLabel("Регистерски Број:");
		labelTraderRegistryNumber.setBounds(xLabel, y, weightLabel, height);

		textFieldTraderRegistryNumber = new JTextField();
		textFieldTraderRegistryNumber.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;

		labelTraderBankName = new JLabel("Банка:");
		labelTraderBankName.setBounds(xLabel, y, weightLabel, height);

		textFieldTraderBankName = new JTextField();
		textFieldTraderBankName.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;

		labelTraderBankAccount = new JLabel("Банкарска Сметка:");
		labelTraderBankAccount.setBounds(xLabel, y, weightLabel, height);

		textFieldTraderBankAccount = new JTextField();
		textFieldTraderBankAccount.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;

		labelTraderAddress = new JLabel("Адреса:");
		labelTraderAddress.setBounds(xLabel, y, weightLabel, height);

		textFieldTraderAddress = new JTextField();
		textFieldTraderAddress
				.setBounds(xTextField, y, weightTextField, height);

		y = y + height + spacing;

		labelTraderPhoneNumber = new JLabel("Телефонски Број:");
		labelTraderPhoneNumber.setBounds(xLabel, y, weightLabel, height);

		textFieldTraderPhoneNumber = new JTextField();
		textFieldTraderPhoneNumber.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;

		labelTraderEmail = new JLabel("Email:");
		labelTraderEmail.setBounds(xLabel, y, weightLabel, height);

		textFieldTraderEmail = new JTextField();
		textFieldTraderEmail.setBounds(xTextField, y, weightTextField, height);

		y = y + height + spacing;

		labelTraderAdditionalInfo = new JLabel("Забелешки:");
		labelTraderAdditionalInfo.setBounds(xLabel, y, weightLabel, height);

		textFieldTraderAdditionalInfo = new JTextField();
		textFieldTraderAdditionalInfo.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;

		labelTraderId = new JLabel("ID:");
		labelTraderId.setBounds(xLabel, y, weightLabel, height);

		textFieldTraderId = new JTextField();
		textFieldTraderId.setBounds(xTextField, y, weightTextField, height);

		panelInfoHolderContentInfo.add(labelTraderName);
		panelInfoHolderContentInfo.add(labelTraderRegistryNumber);

		panelInfoHolderContentInfo.add(textFieldTraderName);
		panelInfoHolderContentInfo.add(textFieldTraderRegistryNumber);

		panelInfoHolderContentInfo.add(labelTraderBankName);
		panelInfoHolderContentInfo.add(textFieldTraderBankName);

		panelInfoHolderContentInfo.add(labelTraderBankAccount);
		panelInfoHolderContentInfo.add(textFieldTraderBankAccount);

		panelInfoHolderContentInfo.add(labelTraderAddress);
		panelInfoHolderContentInfo.add(textFieldTraderAddress);

		panelInfoHolderContentInfo.add(labelTraderPhoneNumber);
		panelInfoHolderContentInfo.add(textFieldTraderPhoneNumber);

		panelInfoHolderContentInfo.add(labelTraderEmail);
		panelInfoHolderContentInfo.add(textFieldTraderEmail);

		panelInfoHolderContentInfo.add(labelTraderAdditionalInfo);
		panelInfoHolderContentInfo.add(textFieldTraderAdditionalInfo);
		
		return panelInfoHolderContentInfo;
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

}
