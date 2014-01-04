package com.chadik.kiev.view.panel.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chadik.kiev.model.Customer;
import com.chadik.kiev.service.ICustomerJpaService;
import com.chadik.kiev.util.TableUtil;
import com.chadik.kiev.view.dialog.IDialogCustomer;
import com.chadik.kiev.view.dialog.IDialogGeneric;
import com.chadik.kiev.view.dialog.IDialogTrader;
import com.chadik.kiev.view.panel.IPanelCustomer;
import com.chadik.kiev.view.table.ITableCustomer;
import com.chadik.kiev.view.table.ITableGeneric;

@Component
public class PanelCustomerImpl extends PanelGenericImpl<Customer> implements
		IPanelCustomer {
	
	private JPanel panelInfoHolderContentInfo;
	
	private JLabel labelCustomerId;
	private JLabel labelCustomerName;
	private JLabel labelCustomerAddress;
	private JLabel labelCustomerPhoneNumber;
	private JLabel labelCustomerEmail;
	private JLabel labelCustomerAdditionalInfo;

	private JTextField textFieldCustomerId;
	private JTextField textFieldCustomerName;
	private JTextField textFieldCustomerAddress;
	private JTextField textFieldCustomerPhoneNumber;
	private JTextField textFieldCustomerEmail;
	private JTextField textFieldCustomerAdditionalInfo;

	private DefaultTableModel defaultTableModel;
	private JTable table;
	private List<Customer> customers;

	@Autowired
	private ICustomerJpaService customerJpaServiceImpl;
	@Autowired
	private IDialogCustomer dialogCustomerImpl;

	@Override
	public Customer getTableEntity() {
		return null;
	}

	@Override
	public IDialogGeneric getDialog() {
		return dialogCustomerImpl;
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

		labelCustomerName = new JLabel("Име:");
		labelCustomerName.setBounds(xLabel, y, weightLabel, height);

		textFieldCustomerName = new JTextField();
		textFieldCustomerName.setBounds(xTextField, y, weightTextField, height);

		y = y + height + spacing;

		labelCustomerAddress = new JLabel("Адреса:");
		labelCustomerAddress.setBounds(xLabel, y, weightLabel, height);

		textFieldCustomerAddress = new JTextField();
		textFieldCustomerAddress
				.setBounds(xTextField, y, weightTextField, height);

		y = y + height + spacing;

		labelCustomerPhoneNumber = new JLabel("Телефонски Број:");
		labelCustomerPhoneNumber.setBounds(xLabel, y, weightLabel, height);

		textFieldCustomerPhoneNumber = new JTextField();
		textFieldCustomerPhoneNumber.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;

		labelCustomerEmail = new JLabel("Email:");
		labelCustomerEmail.setBounds(xLabel, y, weightLabel, height);

		textFieldCustomerEmail = new JTextField();
		textFieldCustomerEmail.setBounds(xTextField, y, weightTextField, height);

		y = y + height + spacing;

		labelCustomerAdditionalInfo = new JLabel("Забелешки:");
		labelCustomerAdditionalInfo.setBounds(xLabel, y, weightLabel, height);

		textFieldCustomerAdditionalInfo = new JTextField();
		textFieldCustomerAdditionalInfo.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;

		labelCustomerId = new JLabel("ID:");
		labelCustomerId.setBounds(xLabel, y, weightLabel, height);

		textFieldCustomerId = new JTextField();
		textFieldCustomerId.setBounds(xTextField, y, weightTextField, height);

		panelInfoHolderContentInfo.add(labelCustomerName);
		panelInfoHolderContentInfo.add(textFieldCustomerName);

		panelInfoHolderContentInfo.add(labelCustomerAddress);
		panelInfoHolderContentInfo.add(textFieldCustomerAddress);

		panelInfoHolderContentInfo.add(labelCustomerPhoneNumber);
		panelInfoHolderContentInfo.add(textFieldCustomerPhoneNumber);

		panelInfoHolderContentInfo.add(labelCustomerEmail);
		panelInfoHolderContentInfo.add(textFieldCustomerEmail);

		panelInfoHolderContentInfo.add(labelCustomerAdditionalInfo);
		panelInfoHolderContentInfo.add(textFieldCustomerAdditionalInfo);
		
		return panelInfoHolderContentInfo;
	}

	@Override
	public void populateTable() {
		customers = customerJpaServiceImpl.getAll();

		int i = 0;

		defaultTableModel.setRowCount(0);

		for (Customer customer : customers) {
			defaultTableModel.addRow(new String[] { Integer.toString(++i),
					customer.getCustomerId().toString(),
					customer.getCustomerName(), customer.getCustomerAddress(),
					customer.getCustomerPhoneNumber(),
					customer.getCustomerEmail(),
					customer.getCustomerAdditionalInfo() });
		}

		if (table.getRowCount() > 0) {
			table.setRowSelectionInterval(table.getRowCount() - 1,
					table.getRowCount() - 1);
		}

	}

	@Override
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

	@Override
	public String[] getTableColumnNames() {
		return new String[] { "Реден Бр.", "Id", "Назив", "Адреса",
				"Телефонски број", "Email", "Забелешки" };
	}

	@Override
	public int[] getTableHiddenColumns() {
		return new int[] { 1, 3, 5, 6 };
	}

}
