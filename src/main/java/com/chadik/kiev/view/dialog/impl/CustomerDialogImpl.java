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

import com.chadik.kiev.model.Customer;
import com.chadik.kiev.service.ICustomerService;
import com.chadik.kiev.view.dialog.ICustomerDialog;
import com.chadik.kiev.view.panel.ICustomerPanel;

@Component
public class CustomerDialogImpl implements ICustomerDialog {

	private JDialog dialog;
	private JPanel contentPane;
	
	private JPanel panelAll;
	private JPanel panelFields;
	private JPanel panelFieldsContent;
	private JPanel panelButtons;
	private JPanel panelButtonsContent;

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

	private JButton buttonSave;
	private JButton buttonCancel;

	@Autowired
	private ICustomerService customerServiceImpl;
	@Autowired
	private ICustomerPanel customerPanelImpl;

	@Override
	public JDialog initCustomerDialog() {
		dialog = new JDialog();
		dialog.setTitle("Нов Клиент");
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

		labelCustomerName = new JLabel("Име:");
		labelCustomerName.setBounds(xLabel, y, weightLabel, height);

		textFieldCustomerName = new JTextField();
		textFieldCustomerName.setBounds(xTextField, y, weightTextField, height);

		y = y + height + spacing;

		labelCustomerAddress = new JLabel("Адреса:");
		labelCustomerAddress.setBounds(xLabel, y, weightLabel, height);

		textFieldCustomerAddress = new JTextField();
		textFieldCustomerAddress.setBounds(xTextField, y, weightTextField,
				height);

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
		textFieldCustomerEmail
				.setBounds(xTextField, y, weightTextField, height);

		y = y + height + spacing;

		labelCustomerAdditionalInfo = new JLabel("Забелешки:");
		labelCustomerAdditionalInfo.setBounds(xLabel, y, weightLabel, height);

		textFieldCustomerAdditionalInfo = new JTextField();
		textFieldCustomerAdditionalInfo.setBounds(xTextField, y,
				weightTextField, height);

		y = y + height + spacing;

		labelCustomerId = new JLabel("ID:");
		labelCustomerId.setBounds(xLabel, y, weightLabel, height);

		textFieldCustomerId = new JTextField();
		textFieldCustomerId.setBounds(xTextField, y, weightTextField, height);

		buttonSave = new JButton("Зачувај");
		buttonSave.setPreferredSize(new Dimension(100, 25));
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveCustomerAndDispose();
			}
		});

		buttonCancel = new JButton("Откажи");
		buttonCancel.setPreferredSize(new Dimension(100, 25));
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

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

	public Customer getCustomerFromFields() {
		Customer customer = new Customer();
		customer.setCustomerName(textFieldCustomerName.getText());
		customer.setCustomerAddress(textFieldCustomerAddress.getText());
		customer.setCustomerPhoneNumber(textFieldCustomerPhoneNumber.getText());
		customer.setCustomerEmail(textFieldCustomerEmail.getText());
		customer.setCustomerAdditionalInfo(textFieldCustomerAdditionalInfo
				.getText());

		return customer;
	}

	public void saveCustomerAndDispose() {
		Customer customer = getCustomerFromFields();
		customerServiceImpl.saveCustomer(customer);
		dialog.dispose();
		customerPanelImpl.populateCustomerTable();
	}

	public void dispose() {
		dialog.dispose();
	}

}
