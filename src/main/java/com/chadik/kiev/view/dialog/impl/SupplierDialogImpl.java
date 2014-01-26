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

import com.chadik.kiev.model.Supplier;
import com.chadik.kiev.service.ISupplierService;
import com.chadik.kiev.view.dialog.ISupplierDialog;
import com.chadik.kiev.view.panel.ISupplierPanel;

@Component
public class SupplierDialogImpl implements ISupplierDialog {

	private JDialog dialog;
	private JPanel contentPane;

	private JPanel panelAll;
	private JPanel panelFields;
	private JPanel panelFieldsContent;
	private JPanel panelButtons;
	private JPanel panelButtonsContent;

	private JLabel labelSupplierId;
	private JLabel labelSupplierName;
	private JLabel labelSupplierRegistryNumber;
	private JLabel labelSupplierBankName;
	private JLabel labelSupplierBankAccount;
	private JLabel labelSupplierAddress;
	private JLabel labelSupplierPhoneNumber;
	private JLabel labelSupplierEmail;
	private JLabel labelSupplierAdditionalInfo;

	private JTextField textFieldSupplierId;
	private JTextField textFieldSupplierName;
	private JTextField textFieldSupplierRegistryNumber;
	private JTextField textFieldSupplierBankName;
	private JTextField textFieldSupplierBankAccount;
	private JTextField textFieldSupplierAddress;
	private JTextField textFieldSupplierPhoneNumber;
	private JTextField textFieldSupplierEmail;
	private JTextField textFieldSupplierAdditionalInfo;

	private JButton buttonSave;
	private JButton buttonCancel;

	@Autowired
	private ISupplierService supplierServiceImpl;
	@Autowired
	private ISupplierPanel supplierPanelImpl;

	@Override
	public JDialog initSupplierDialog() {
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

		panelButtonsContent = new JPanel();
		panelButtonsContent.setLayout(new FlowLayout());
		panelButtonsContent.setPreferredSize(new Dimension(400, 50));

		int spacing = 5;
		int weightLabel = 125;
		int height = 25;
		int weightTextField = 250;
		int xLabel = 10;
		int xTextField = xLabel + weightLabel + spacing;
		int y = 25;

		labelSupplierName = new JLabel("Име:");
		labelSupplierName.setBounds(xLabel, y, weightLabel, height);

		textFieldSupplierName = new JTextField();
		textFieldSupplierName.setBounds(xTextField, y, weightTextField, height);

		y = y + height + spacing;

		labelSupplierRegistryNumber = new JLabel("Регистерски Број:");
		labelSupplierRegistryNumber.setBounds(xLabel, y, weightLabel, height);

		textFieldSupplierRegistryNumber = new JTextField();
		textFieldSupplierRegistryNumber.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;

		labelSupplierBankName = new JLabel("Банка:");
		labelSupplierBankName.setBounds(xLabel, y, weightLabel, height);

		textFieldSupplierBankName = new JTextField();
		textFieldSupplierBankName.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;

		labelSupplierBankAccount = new JLabel("Банкарска Сметка:");
		labelSupplierBankAccount.setBounds(xLabel, y, weightLabel, height);

		textFieldSupplierBankAccount = new JTextField();
		textFieldSupplierBankAccount.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;

		labelSupplierAddress = new JLabel("Адреса:");
		labelSupplierAddress.setBounds(xLabel, y, weightLabel, height);

		textFieldSupplierAddress = new JTextField();
		textFieldSupplierAddress
				.setBounds(xTextField, y, weightTextField, height);

		y = y + height + spacing;

		labelSupplierPhoneNumber = new JLabel("Телефонски Број:");
		labelSupplierPhoneNumber.setBounds(xLabel, y, weightLabel, height);

		textFieldSupplierPhoneNumber = new JTextField();
		textFieldSupplierPhoneNumber.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;

		labelSupplierEmail = new JLabel("Email:");
		labelSupplierEmail.setBounds(xLabel, y, weightLabel, height);

		textFieldSupplierEmail = new JTextField();
		textFieldSupplierEmail.setBounds(xTextField, y, weightTextField, height);

		y = y + height + spacing;

		labelSupplierAdditionalInfo = new JLabel("Забелешки:");
		labelSupplierAdditionalInfo.setBounds(xLabel, y, weightLabel, height);

		textFieldSupplierAdditionalInfo = new JTextField();
		textFieldSupplierAdditionalInfo.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;

		labelSupplierId = new JLabel("ID:");
		labelSupplierId.setBounds(xLabel, y, weightLabel, height);

		textFieldSupplierId = new JTextField();
		textFieldSupplierId.setBounds(xTextField, y, weightTextField, height);

		buttonSave = new JButton("Зачувај");
		buttonSave.setPreferredSize(new Dimension(100, 25));
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveSupplierAndDispose();
			}
		});

		buttonCancel = new JButton("Откажи");
		buttonCancel.setPreferredSize(new Dimension(100, 25));
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		panelFieldsContent.add(labelSupplierName);
		panelFieldsContent.add(labelSupplierRegistryNumber);

		panelFieldsContent.add(textFieldSupplierName);
		panelFieldsContent.add(textFieldSupplierRegistryNumber);

		panelFieldsContent.add(labelSupplierBankName);
		panelFieldsContent.add(textFieldSupplierBankName);

		panelFieldsContent.add(labelSupplierBankAccount);
		panelFieldsContent.add(textFieldSupplierBankAccount);

		panelFieldsContent.add(labelSupplierAddress);
		panelFieldsContent.add(textFieldSupplierAddress);

		panelFieldsContent.add(labelSupplierPhoneNumber);
		panelFieldsContent.add(textFieldSupplierPhoneNumber);

		panelFieldsContent.add(labelSupplierEmail);
		panelFieldsContent.add(textFieldSupplierEmail);

		panelFieldsContent.add(labelSupplierAdditionalInfo);
		panelFieldsContent.add(textFieldSupplierAdditionalInfo);

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

	public Supplier getSupplierFromFields() {
		Supplier supplier = new Supplier();
		supplier.setSupplierName(textFieldSupplierName.getText());
		supplier.setSupplierRegistryNumber(textFieldSupplierRegistryNumber.getText());
		supplier.setSupplierBankName(textFieldSupplierBankName.getText());
		supplier.setSupplierBankAccount(textFieldSupplierBankAccount.getText());
		supplier.setSupplierAddress(textFieldSupplierAddress.getText());
		supplier.setSupplierPhoneNumber(textFieldSupplierPhoneNumber.getText());
		supplier.setSupplierEmail(textFieldSupplierEmail.getText());
		supplier.setSupplierAdditionalInfo(textFieldSupplierAdditionalInfo.getText());

		return supplier;
	}

	public void saveSupplierAndDispose() {
		Supplier supplier = getSupplierFromFields();
		supplierServiceImpl.saveSupplier(supplier);
		dialog.dispose();
		supplierPanelImpl.populateSupplierTable();
	}

	public void dispose() {
		dialog.dispose();
	}

}
