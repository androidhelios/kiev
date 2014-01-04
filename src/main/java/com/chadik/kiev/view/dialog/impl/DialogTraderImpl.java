package com.chadik.kiev.view.dialog.impl;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chadik.kiev.model.Trader;
import com.chadik.kiev.service.IGenericJpaService;
import com.chadik.kiev.service.ITraderJpaService;
import com.chadik.kiev.view.dialog.IDialogGeneric;
import com.chadik.kiev.view.dialog.IDialogTrader;
import com.chadik.kiev.view.panel.IPanelGeneric;
import com.chadik.kiev.view.panel.IPanelTrader;

@Component
public class DialogTraderImpl extends DialogGenericImpl<Trader> implements
		IDialogTrader {
	
	@Autowired
	private ITraderJpaService traderJpaServiceImpl;
	@Autowired
	private IPanelTrader panelTraderImpl;

	private JPanel panelFieldsContent;

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

	@Override
	public String getDilogName() {
		return "Нов Корисник";
	}

	@Override
	public JPanel getPanelFieldsContent() {
		panelFieldsContent = new JPanel();
		panelFieldsContent.setLayout(null);
		panelFieldsContent.setPreferredSize(new Dimension(400, 300));

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

		return panelFieldsContent;
	}

	@Override
	public IGenericJpaService getGenericJpaService() {
		return traderJpaServiceImpl;
	}

	@Override
	public Trader getT() {
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

	@Override
	public IPanelGeneric getPanelGeneric() {
		return panelTraderImpl;
	}

}
