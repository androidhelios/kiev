package com.chadik.kiev.view.dialog.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chadik.kiev.model.Customer;
import com.chadik.kiev.model.Invoice;
import com.chadik.kiev.service.ICustomerService;
import com.chadik.kiev.service.IInvoiceService;
import com.chadik.kiev.view.dialog.IInvoiceDialog;
import com.chadik.kiev.view.panel.ICustomerPanel;
import com.chadik.kiev.view.panel.IInvoicePanel;

@Component
public class InvoiceDialogImpl implements IInvoiceDialog {
	
	private JDialog dialog;
	private JPanel contentPane;
	
	private JPanel panelAll;
	private JPanel panelFields;
	private JPanel panelFieldsContent;
	private JPanel panelButtons;
	private JPanel panelButtonsContent;
	
	private JLabel labelInvoiceId;
	private JLabel labelInvoiceSupplierId;
	private JLabel labelInvoiceSupplierName;
	private JLabel labelInvoiceSupplierAddress;
	private JLabel labelInvoiceSupplierPhone;
	private JLabel labelInvoiceSupplierEmail;
	private JLabel labelInvoiceSupplierRegistryNumber;
	private JLabel labelInvoiceSupplierBankName;
	private JLabel labelInvoiceSupplierBankAccount;
	private JLabel labelInvoiceSupplierAdditionalInfo;
	private JLabel labelInvoiceCustomerId;
	private JLabel labelInvoiceCustomerName;
	private JLabel labelInvoiceCustomerAddress;
	private JLabel labelInvoiceCustomerPhoneNumber;
	private JLabel labelInvoiceCustomerEmail;
	private JLabel labelInvoiceCustomerAdditionalInfo;
	private JLabel labelInvoiceNumber;
	private JLabel labelInvoiceSerialNumber;
	private JLabel labelInvoiceDate;
	private JLabel labelInvoiceDeliveryDate;
	private JLabel labelInvoiceDeliveryNumber;
	private JLabel labelInvoiceTotalPrice;
	private JLabel labelInvoiceTotalTax;
	private JLabel labelInvoiceTotalPriceTax;
	private JLabel labelInvoiceAdditionalInfo;

	private JTextField textFieldInvoiceId;
	private JTextField textFieldInvoiceSupplierId;
	private JTextField textFieldInvoiceSupplierName;
	private JTextField textFieldInvoiceSupplierAddress;
	private JTextField textFieldInvoiceSupplierPhone;
	private JTextField textFieldInvoiceSupplierEmail;
	private JTextField textFieldInvoiceSupplierRegistryNumber;
	private JTextField textFieldInvoiceSupplierBankName;
	private JTextField textFieldInvoiceSupplierBankAccount;
	private JTextField textFieldInvoiceSupplierAdditionalInfo;
	private JTextField textFieldInvoiceCustomerId;
	private JTextField textFieldInvoiceCustomerName;
	private JTextField textFieldInvoiceCustomerAddress;
	private JTextField textFieldInvoiceCustomerPhoneNumber;
	private JTextField textFieldInvoiceCustomerEmail;
	private JTextField textFieldInvoiceCustomerAdditionalInfo;
	private JTextField textFieldInvoiceNumber;
	private JTextField textFieldInvoiceSerialNumber;
	private JTextField textFieldInvoiceDate;
	private JTextField textFieldInvoiceDeliveryDate;
	private JTextField textFieldInvoiceDeliveryNumber;
	private JTextField textFieldInvoiceTotalPrice;
	private JTextField textFieldInvoiceTotalTax;
	private JTextField textFieldInvoiceTotalPriceTax;
	private JTextField textFieldInvoiceAdditionalInfo;
	
	private JScrollPane scrollPanePanelFieldsContent;

	private JButton buttonSave;
	private JButton buttonCancel;

	@Autowired
	private IInvoiceService invoiceServiceImpl;
	@Autowired
	private IInvoicePanel invoicePanelImpl;

	@Override
	public JDialog initInvoiceDialog() {
		dialog = new JDialog();
		dialog.setTitle("Нова Фактура");
		dialog.setResizable(false);

		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());

		dialog.setContentPane(contentPane);

		panelAll = new JPanel();
		panelAll.setLayout(new BorderLayout());
		panelAll.setPreferredSize(new Dimension(450, 350));

		panelFields = new JPanel();
		panelFields.setLayout(new BorderLayout());
		panelFields.setBorder(BorderFactory
				.createCompoundBorder(new EmptyBorder(10, 10, 10, 10),
						new EtchedBorder()));

		panelFieldsContent = new JPanel();
		panelFieldsContent.setLayout(null);
		panelFieldsContent.setBackground(new Color(192, 192, 192));
		panelFieldsContent.setPreferredSize(new Dimension(450, 700));
		
		scrollPanePanelFieldsContent = new JScrollPane(
				panelFieldsContent);
		scrollPanePanelFieldsContent.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		panelButtons = new JPanel();
		panelButtons.setLayout(new BorderLayout());

		panelButtonsContent = new JPanel();
		panelButtonsContent.setLayout(new FlowLayout());
		panelButtonsContent.setPreferredSize(new Dimension(450, 50));
		
		int spacing = 5;
		int weightLabel = 125;
		int height = 25;
		int weightTextField = 250;
		int xLabel = 10;
		int xTextField = xLabel + weightLabel + spacing;
		int y = 25;
		
		labelInvoiceSupplierName = new JLabel("Име на корисник:");
		labelInvoiceSupplierName.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceSupplierName = new JTextField();
		textFieldInvoiceSupplierName.setBounds(xTextField, y, weightTextField, height);

		y = y + height + spacing;
		
		labelInvoiceSupplierAddress = new JLabel("Адреса на корисник:");
		labelInvoiceSupplierAddress.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceSupplierAddress = new JTextField();
		textFieldInvoiceSupplierAddress.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;
		
		labelInvoiceSupplierPhone = new JLabel("Телефон на корисник:");
		labelInvoiceSupplierPhone.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceSupplierPhone = new JTextField();
		textFieldInvoiceSupplierPhone.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;
		
		labelInvoiceSupplierEmail = new JLabel("Email на корисник:");
		labelInvoiceSupplierEmail.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceSupplierEmail = new JTextField();
		textFieldInvoiceSupplierEmail.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;
		
		labelInvoiceSupplierRegistryNumber = new JLabel("Регистарски број:");
		labelInvoiceSupplierRegistryNumber.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceSupplierRegistryNumber = new JTextField();
		textFieldInvoiceSupplierRegistryNumber.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;
		
		labelInvoiceSupplierBankName = new JLabel("Банка:");
		labelInvoiceSupplierBankName.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceSupplierBankName = new JTextField();
		textFieldInvoiceSupplierBankName.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;
		
		labelInvoiceSupplierBankAccount = new JLabel("Банкарска сметка:");
		labelInvoiceSupplierBankAccount.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceSupplierBankAccount = new JTextField();
		textFieldInvoiceSupplierBankAccount.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;
		
		labelInvoiceSupplierAdditionalInfo = new JLabel("Дополнителни информации за корисник:");
		labelInvoiceSupplierAdditionalInfo.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceSupplierAdditionalInfo = new JTextField();
		textFieldInvoiceSupplierAdditionalInfo.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;
		
		labelInvoiceCustomerName = new JLabel("Име на клиент:");
		labelInvoiceCustomerName.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceCustomerName = new JTextField();
		textFieldInvoiceCustomerName.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;
		
		labelInvoiceCustomerAddress = new JLabel("Адреса на клиент:");
		labelInvoiceCustomerAddress.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceCustomerAddress = new JTextField();
		textFieldInvoiceCustomerAddress.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;
		
		labelInvoiceCustomerPhoneNumber = new JLabel("Телефонски број на клиент:");
		labelInvoiceCustomerPhoneNumber.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceCustomerPhoneNumber = new JTextField();
		textFieldInvoiceCustomerPhoneNumber.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;
		
		labelInvoiceCustomerEmail = new JLabel("Email на клиент:");
		labelInvoiceCustomerEmail.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceCustomerEmail = new JTextField();
		textFieldInvoiceCustomerEmail.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;
		
		labelInvoiceCustomerAdditionalInfo = new JLabel("Дополнителни информации за клиент:");
		labelInvoiceCustomerAdditionalInfo.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceCustomerAdditionalInfo = new JTextField();
		textFieldInvoiceCustomerAdditionalInfo.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;
		
		labelInvoiceNumber = new JLabel("Дополнителни информации за клиент:");
		labelInvoiceNumber.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceNumber = new JTextField();
		textFieldInvoiceNumber.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;
		
		labelInvoiceSerialNumber = new JLabel("Дополнителни информации за клиент:");
		labelInvoiceSerialNumber.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceSerialNumber = new JTextField();
		textFieldInvoiceSerialNumber.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;
		
		labelInvoiceDate = new JLabel("Дополнителни информации за клиент:");
		labelInvoiceDate.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceDate = new JTextField();
		textFieldInvoiceDate.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;
		
		labelInvoiceDeliveryDate = new JLabel("Дополнителни информации за клиент:");
		labelInvoiceDeliveryDate.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceDeliveryDate = new JTextField();
		textFieldInvoiceDeliveryDate.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;
		
		labelInvoiceDeliveryNumber = new JLabel("Дополнителни информации за клиент:");
		labelInvoiceDeliveryNumber.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceDeliveryNumber = new JTextField();
		textFieldInvoiceDeliveryNumber.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;
		
		labelInvoiceTotalPrice = new JLabel("Дополнителни информации за клиент:");
		labelInvoiceTotalPrice.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceTotalPrice = new JTextField();
		textFieldInvoiceTotalPrice.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;
		
		labelInvoiceTotalTax = new JLabel("Дополнителни информации за клиент:");
		labelInvoiceTotalTax.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceTotalTax = new JTextField();
		textFieldInvoiceTotalTax.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;
		
		labelInvoiceTotalPriceTax = new JLabel("Дополнителни информации за клиент:");
		labelInvoiceTotalPriceTax.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceTotalPriceTax = new JTextField();
		textFieldInvoiceTotalPriceTax.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;
		
		labelInvoiceAdditionalInfo = new JLabel("Дополнителни информации за клиент:");
		labelInvoiceAdditionalInfo.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceAdditionalInfo = new JTextField();
		textFieldInvoiceAdditionalInfo.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;	
		
		labelInvoiceCustomerId = new JLabel("Дополнителни информации за клиент:");
		labelInvoiceCustomerId.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceCustomerId = new JTextField();
		textFieldInvoiceCustomerId.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;
		
		labelInvoiceSupplierId = new JLabel("Дополнителни информации за клиент:");
		labelInvoiceSupplierId.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceSupplierId = new JTextField();
		textFieldInvoiceSupplierId.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;		
		
		labelInvoiceId = new JLabel("Дополнителни информации за клиент:");
		labelInvoiceId.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceId = new JTextField();
		textFieldInvoiceId.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;	
		
		buttonSave = new JButton("Зачувај");
		buttonSave.setPreferredSize(new Dimension(100, 25));
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveInvoiceAndDispose();
			}
		});

		buttonCancel = new JButton("Откажи");
		buttonCancel.setPreferredSize(new Dimension(100, 25));
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		panelFieldsContent.add(labelInvoiceSupplierName);
		panelFieldsContent.add(textFieldInvoiceSupplierName);
		
		panelFieldsContent.add(labelInvoiceSupplierAddress);
		panelFieldsContent.add(textFieldInvoiceSupplierAddress);
		
		panelFieldsContent.add(labelInvoiceSupplierPhone);
		panelFieldsContent.add(textFieldInvoiceSupplierPhone);
		
		panelFieldsContent.add(labelInvoiceSupplierEmail);
		panelFieldsContent.add(textFieldInvoiceSupplierEmail);
		
		panelFieldsContent.add(labelInvoiceSupplierRegistryNumber);
		panelFieldsContent.add(textFieldInvoiceSupplierRegistryNumber);
		
		panelFieldsContent.add(labelInvoiceSupplierBankName);
		panelFieldsContent.add(textFieldInvoiceSupplierBankName);
		
		panelFieldsContent.add(labelInvoiceSupplierBankAccount);
		panelFieldsContent.add(textFieldInvoiceSupplierBankAccount);
		
		panelFieldsContent.add(labelInvoiceSupplierAdditionalInfo);
		panelFieldsContent.add(textFieldInvoiceSupplierAdditionalInfo);
		
		panelFieldsContent.add(labelInvoiceCustomerName);
		panelFieldsContent.add(textFieldInvoiceCustomerName);
		
		panelFieldsContent.add(labelInvoiceCustomerAddress);
		panelFieldsContent.add(textFieldInvoiceCustomerAddress);
		
		panelFieldsContent.add(labelInvoiceCustomerPhoneNumber);
		panelFieldsContent.add(textFieldInvoiceCustomerPhoneNumber);
		
		panelFieldsContent.add(labelInvoiceCustomerEmail);
		panelFieldsContent.add(textFieldInvoiceCustomerEmail);
		
		panelFieldsContent.add(labelInvoiceCustomerAdditionalInfo);
		panelFieldsContent.add(textFieldInvoiceCustomerAdditionalInfo);
		
		panelFieldsContent.add(labelInvoiceNumber);
		panelFieldsContent.add(textFieldInvoiceNumber);
		
		panelFieldsContent.add(labelInvoiceSerialNumber);
		panelFieldsContent.add(textFieldInvoiceSerialNumber);
		
		panelFieldsContent.add(labelInvoiceDate);
		panelFieldsContent.add(textFieldInvoiceDate);
		
		panelFieldsContent.add(labelInvoiceDeliveryDate);
		panelFieldsContent.add(textFieldInvoiceDeliveryDate);
		
		panelFieldsContent.add(labelInvoiceDeliveryNumber);
		panelFieldsContent.add(textFieldInvoiceDeliveryNumber);
		
		panelFieldsContent.add(labelInvoiceTotalPrice);
		panelFieldsContent.add(textFieldInvoiceTotalPrice);
		
		panelFieldsContent.add(labelInvoiceTotalTax);
		panelFieldsContent.add(textFieldInvoiceTotalTax);
		
		panelFieldsContent.add(labelInvoiceTotalPriceTax);
		panelFieldsContent.add(textFieldInvoiceTotalPriceTax);
		
		panelFieldsContent.add(labelInvoiceAdditionalInfo);
		panelFieldsContent.add(textFieldInvoiceAdditionalInfo);
		
		panelButtonsContent.add(buttonSave);
		panelButtonsContent.add(buttonCancel);
		
		panelFields.add(scrollPanePanelFieldsContent, BorderLayout.CENTER);

		panelButtons.add(panelButtonsContent, BorderLayout.CENTER);

		panelAll.add(panelButtons, BorderLayout.SOUTH);
		panelAll.add(panelFields, BorderLayout.CENTER);

		contentPane.add(panelAll);

		dialog.pack();
		dialog.setVisible(true);
		dialog.setLocationRelativeTo(null);

		return dialog;
	}
	
	public Invoice getInvoiceFromFields() {
		Invoice invoice = new Invoice();
		//TODO
		return invoice;
	}
	
	public void saveInvoiceAndDispose() {
		Invoice invoice = getInvoiceFromFields();
		invoiceServiceImpl.saveInvoice(invoice);
		dialog.dispose();
		invoicePanelImpl.populateInvoiceTable();
	}
	
	public void dispose() {
		dialog.dispose();
	}

}
