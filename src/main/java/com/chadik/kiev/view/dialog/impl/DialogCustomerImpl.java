package com.chadik.kiev.view.dialog.impl;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chadik.kiev.model.Customer;
import com.chadik.kiev.model.Trader;
import com.chadik.kiev.service.ICustomerJpaService;
import com.chadik.kiev.service.IGenericJpaService;
import com.chadik.kiev.service.ITraderJpaService;
import com.chadik.kiev.view.dialog.IDialogCustomer;
import com.chadik.kiev.view.dialog.IDialogGeneric;
import com.chadik.kiev.view.dialog.IDialogTrader;
import com.chadik.kiev.view.panel.IPanelCustomer;
import com.chadik.kiev.view.panel.IPanelGeneric;
import com.chadik.kiev.view.panel.IPanelTrader;

@Component
public class DialogCustomerImpl extends DialogGenericImpl<Customer> implements
		IDialogCustomer {
	
	@Autowired
	private ICustomerJpaService customerJpaServiceImpl;
	@Autowired
	private IPanelCustomer panelCustomerImpl;

	private JPanel panelFieldsContent;

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

	@Override
	public String getDilogName() {
		return "Нов Клиент";
	}

	@Override
	public Dimension getPanelFieldsDimension() {
		return new Dimension(400, 300);
	}

	@Override
	public Dimension getPanelButtonsDimension() {
		return new Dimension(400, 50);
	}

	@Override
	public JPanel getPanelFieldsContent() {
		panelFieldsContent = new JPanel();
		panelFieldsContent.setLayout(null);
		panelFieldsContent.setPreferredSize(getPanelFieldsDimension());

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

		panelFieldsContent.add(labelCustomerName);
		panelFieldsContent.add(textFieldCustomerName);

		panelFieldsContent.add(labelCustomerAddress);
		panelFieldsContent.add(textFieldCustomerAddress);

		panelFieldsContent.add(labelCustomerPhoneNumber);
		panelFieldsContent.add(textFieldCustomerPhoneNumber);

		panelFieldsContent.add(labelCustomerEmail);
		panelFieldsContent.add(textFieldCustomerEmail);

		panelFieldsContent.add(labelCustomerAdditionalInfo);
		panelFieldsContent.add(textFieldCustomerAdditionalInfo);

		return panelFieldsContent;
	}

	@Override
	public IGenericJpaService getGenericJpaService() {
		return customerJpaServiceImpl;
	}

	@Override
	public Customer getT() {
		Customer customer = new Customer();
		customer.setCustomerName(textFieldCustomerName.getText());
		customer.setCustomerAddress(textFieldCustomerAddress.getText());
		customer.setCustomerPhoneNumber(textFieldCustomerPhoneNumber.getText());
		customer.setCustomerEmail(textFieldCustomerEmail.getText());
		customer.setCustomerAdditionalInfo(textFieldCustomerAdditionalInfo.getText());
		
		return customer;
	}

	@Override
	public IPanelGeneric getPanelGeneric() {
		return panelCustomerImpl;
	}

}
