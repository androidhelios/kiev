package com.chadik.kiev.view.dialog.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chadik.kiev.model.Trader;
import com.chadik.kiev.service.ITraderService;
import com.chadik.kiev.view.dialog.ITraderDialog;
import com.chadik.kiev.view.panel.ITraderPanel;

@Component
public class TraderDialogImpl implements ITraderDialog {

	private JDialog dialog;
	private JPanel contentPane;

	private JPanel panelAll;
	private JPanel panelFields;
	private JPanel panelFieldsContent;
	private JPanel panelButtons;
	private JPanel panelButtonsContent;

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

	private JButton buttonSave;
	private JButton buttonCancel;

	@Autowired
	private ITraderService traderServiceImpl;
	@Autowired
	private ITraderPanel traderPanelImpl;

	@Override
	public JDialog initTraderDialog() {
		dialog = new JDialog();
		dialog.setTitle("Нов Корисник");
		dialog.setResizable(false);

		contentPane = new JPanel();

		dialog.setContentPane(contentPane);

		panelAll = new JPanel();
		panelAll.setLayout(new BorderLayout());

		panelFields = new JPanel();
		panelFields.setLayout(new BorderLayout());

		panelFieldsContent = new JPanel();
		panelFieldsContent.setLayout(null);
		panelFieldsContent.setPreferredSize(new Dimension(400, 300));

		panelButtons = new JPanel();
		panelButtons.setLayout(new BorderLayout());
		panelButtons.setPreferredSize(new Dimension(400, 50));

		panelButtonsContent = new JPanel();
		panelButtonsContent.setLayout(new FlowLayout());

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
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveTraderAndDispose();
			}
		});

		buttonCancel = new JButton("Откажи");
		buttonCancel.setPreferredSize(new Dimension(100, 25));
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		panelFieldsContent.add(labelTraderName);
		panelFieldsContent.add(labelTraderRegistryNumber);

		panelFieldsContent.add(textFieldTraderName);
		panelFieldsContent.add(textFieldTraderRegistryNumber);

		panelFieldsContent.add(labelTraderBankName);
		panelFieldsContent.add(textFieldTraderBankName);

		panelFieldsContent.add(labelTraderBankAccount);
		panelFieldsContent.add(textFieldTraderBankAccount);

		panelFieldsContent.add(labelTraderAddress);
		panelFieldsContent.add(textFieldTraderAddress);

		panelFieldsContent.add(labelTraderPhoneNumber);
		panelFieldsContent.add(textFieldTraderPhoneNumber);

		panelFieldsContent.add(labelTraderEmail);
		panelFieldsContent.add(textFieldTraderEmail);

		panelFieldsContent.add(labelTraderAdditionalInfo);
		panelFieldsContent.add(textFieldTraderAdditionalInfo);

		panelButtonsContent.add(buttonSave);
		panelButtonsContent.add(buttonCancel);

		panelFields.add(panelFieldsContent, BorderLayout.CENTER);

		panelButtons.add(panelButtonsContent, BorderLayout.CENTER);

		panelAll.add(panelButtons, BorderLayout.SOUTH);
		panelAll.add(panelFields, BorderLayout.CENTER);

		contentPane.add(panelAll);

		dialog.pack();
		dialog.setVisible(true);
		dialog.setLocationRelativeTo(null);

		return dialog;
	}

	public Trader getTraderFromFields() {
		Trader trader = new Trader();
		trader.setTraderName(textFieldTraderName.getText());
		trader.setTraderRegistryNumber(textFieldTraderRegistryNumber.getText());
		trader.setTraderBankName(textFieldTraderBankName.getText());
		trader.setTraderBankAccount(textFieldTraderBankAccount.getText());
		trader.setTraderAddress(textFieldTraderAddress.getText());
		trader.setTraderPhoneNumber(textFieldTraderPhoneNumber.getText());
		trader.setTraderEmail(textFieldTraderEmail.getText());
		trader.setTraderAdditionalInfo(textFieldTraderAdditionalInfo.getText());

		return trader;
	}

	public void saveTraderAndDispose() {
		Trader trader = getTraderFromFields();
		traderServiceImpl.saveTrader(trader);
		dialog.dispose();
		traderPanelImpl.populateTraderTable();
	}

	public void dispose() {
		dialog.dispose();
	}

}
