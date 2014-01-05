package com.chadik.kiev.view.panel.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chadik.kiev.model.Trader;
import com.chadik.kiev.service.ITraderService;
import com.chadik.kiev.util.TableUtil;
import com.chadik.kiev.view.dialog.ITraderDialog;
import com.chadik.kiev.view.panel.ITraderPanel;

@Component
public class TraderPanelImpl implements ITraderPanel {

	private JPanel panelAll;

	private JPanel panelTableHolder;
	private JPanel panelTableHolderContent;
	private JPanel panelTableHolderContentTable;
	private JPanel panelTableHolderContentButtons;

	private JPanel panelInfoHolder;
	private JPanel panelInfoHolderContent;
	private JPanel panelInfoHolderContentInfo;
	private JPanel panelInfoHolderContentButtons;

	private JScrollPane scrollPaneTable;

	private DefaultTableModel defaultTableModel;
	private JTable table;

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

	private JButton buttonNew;
	private JButton buttonEdit;
	private JButton buttonDelete;
	private JButton buttonSave;
	private JButton buttonCancel;

	private List<Trader> traders;

	@Autowired
	private ITraderService traderServiceImpl;
	@Autowired
	private ITraderDialog traderDialogImpl;

	@Override
	public JPanel initTraderPanel() {
		panelAll = new JPanel();
		panelAll.setLayout(new BorderLayout());

		panelTableHolder = new JPanel();
		panelTableHolder.setLayout(new BorderLayout());

		panelTableHolderContent = new JPanel();
		panelTableHolderContent.setLayout(new BorderLayout());
		panelTableHolderContent.setPreferredSize(new Dimension(400, 600));

		panelTableHolderContentTable = new JPanel();
		panelTableHolderContentTable.setLayout(new BorderLayout());
		panelTableHolderContentTable.setPreferredSize(new Dimension(400, 550));

		panelTableHolderContentButtons = new JPanel();
		panelTableHolderContentButtons.setLayout(new FlowLayout());
		panelTableHolderContentButtons.setPreferredSize(new Dimension(400, 50));

		panelInfoHolder = new JPanel();
		panelInfoHolder.setLayout(new BorderLayout());

		panelInfoHolderContent = new JPanel();
		panelInfoHolderContent.setLayout(new BorderLayout());
		panelInfoHolderContent.setPreferredSize(new Dimension(400, 600));

		panelInfoHolderContentInfo = new JPanel();
		panelInfoHolderContentInfo.setLayout(null);
		panelInfoHolderContentInfo.setPreferredSize(new Dimension(400, 550));

		panelInfoHolderContentButtons = new JPanel();
		panelInfoHolderContentButtons.setLayout(new FlowLayout());
		panelInfoHolderContentButtons.setPreferredSize(new Dimension(400, 50));

		defaultTableModel = new DefaultTableModel();
		table = new JTable();

		defaultTableModel.setColumnIdentifiers(getTableTraderColumnNames());
		table.setModel(defaultTableModel);
		TableUtil.hideColumns(table, getTableTraderHiddenColumns());
		TableUtil.allignCells(table, SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setMaxWidth(100);

		populateTraderTable();

		scrollPaneTable = new JScrollPane(table);

		buttonNew = new JButton("Креирај");
		buttonNew.setPreferredSize(new Dimension(100, 25));
		buttonNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				traderDialogImpl.initTraderDialog();
			}
		});

		buttonEdit = new JButton("Измени");
		buttonEdit.setPreferredSize(new Dimension(100, 25));

		buttonDelete = new JButton("Избриши");
		buttonDelete.setPreferredSize(new Dimension(100, 25));

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

		buttonSave = new JButton("Зачувај");
		buttonSave.setPreferredSize(new Dimension(100, 25));

		buttonCancel = new JButton("Откажи");
		buttonCancel.setPreferredSize(new Dimension(100, 25));

		panelTableHolderContentTable.add(scrollPaneTable);

		panelTableHolderContentButtons.add(buttonDelete);
		panelTableHolderContentButtons.add(buttonEdit);
		panelTableHolderContentButtons.add(buttonNew);

		panelTableHolderContent.add(panelTableHolderContentTable,
				BorderLayout.CENTER);
		panelTableHolderContent.add(panelTableHolderContentButtons,
				BorderLayout.SOUTH);

		panelTableHolder.add(panelTableHolderContent);

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

		panelInfoHolderContentButtons.add(buttonSave);
		panelInfoHolderContentButtons.add(buttonCancel);

		panelInfoHolderContent.add(panelInfoHolderContentInfo,
				BorderLayout.CENTER);
		panelInfoHolderContent.add(panelInfoHolderContentButtons,
				BorderLayout.SOUTH);

		panelInfoHolder.add(panelInfoHolderContent);

		panelAll.add(panelTableHolder, BorderLayout.WEST);
		panelAll.add(panelInfoHolder, BorderLayout.CENTER);

		return panelAll;
	}

	public void populateTraderTable() {

		traders = traderServiceImpl.findAllTraders();

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

	public int[] getTableTraderHiddenColumns() {
		return new int[] { 1, 3, 4, 5, 6, 8, 9 };
	}

	public String[] getTableTraderColumnNames() {
		return new String[] { "Реден Бр.", "Id", "Назив", "Регистарски Број",
				"Банка", "Банкарска Сметка", "Адреса", "Телефонски број",
				"Email", "Забелешки" };
	}

}
