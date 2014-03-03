package com.chadik.kiev.view.dialog.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import com.chadik.kiev.model.Supplier;
import com.chadik.kiev.service.ICustomerService;
import com.chadik.kiev.service.IInvoiceService;
import com.chadik.kiev.service.ISupplierService;
import com.chadik.kiev.view.dialog.IInvoiceDialog;
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
	private JLabel labelInvoiceTotalQuantityPrice;
	private JLabel labelInvoiceTotalQuantityPriceWithoutTax;
	private JLabel labelInvoiceTotalQuantityTax;
	private JLabel labelInvoicePaymentInfo;
	private JLabel labelInvoiceAdditionalInfo;

	private JTextField textFieldInvoiceId;
	private JTextField textFieldInvoiceSupplierId;
	private JTextField textFieldInvoiceSupplierAddress;
	private JTextField textFieldInvoiceSupplierPhone;
	private JTextField textFieldInvoiceSupplierEmail;
	private JTextField textFieldInvoiceSupplierRegistryNumber;
	private JTextField textFieldInvoiceSupplierBankName;
	private JTextField textFieldInvoiceSupplierBankAccount;
	private JTextField textFieldInvoiceSupplierAdditionalInfo;
	private JTextField textFieldInvoiceCustomerId;
	private JTextField textFieldInvoiceCustomerAddress;
	private JTextField textFieldInvoiceCustomerPhoneNumber;
	private JTextField textFieldInvoiceCustomerEmail;
	private JTextField textFieldInvoiceCustomerAdditionalInfo;
	private JTextField textFieldInvoiceNumber;
	private JTextField textFieldInvoiceSerialNumber;
	private JTextField textFieldInvoiceDate;
	private JTextField textFieldInvoiceDeliveryDate;
	private JTextField textFieldInvoiceDeliveryNumber;
	private JTextField textFieldInvoiceTotalQuantityPrice;
	private JTextField textFieldInvoiceTotalQuantityPriceWithoutTax;
	private JTextField textFieldInvoiceTotalQuantityTax;
	private JTextField textFieldInvoiceAdditionalInfo;

	private JComboBox comboBoxInvoiceSupplierName;
	private JComboBox comboBoxInvoiceCustomerName;
	private JComboBox comboBoxInvoicePaymentInfo;

	private JScrollPane scrollPanePanelFieldsContent;

	private JButton buttonSave;
	private JButton buttonCancel;

	private Color nonEditableTextFieldColor;

	private Map<Integer, Integer> mapSuppliers;
	private Map<Integer, Integer> mapCustomers;
	private Map<Integer, String> mapPaymentsInfo;

	@Autowired
	private IInvoiceService invoiceServiceImpl;
	@Autowired
	private IInvoicePanel invoicePanelImpl;
	@Autowired
	private ISupplierService supplierServiceImpl;
	@Autowired
	private ICustomerService customerServiceImpl;

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
		panelFields.setBorder(BorderFactory.createCompoundBorder(
				new EmptyBorder(10, 10, 10, 10), new EtchedBorder()));

		panelFieldsContent = new JPanel();
		panelFieldsContent.setLayout(null);
		panelFieldsContent.setBackground(new Color(192, 192, 192));
		panelFieldsContent.setPreferredSize(new Dimension(450, 750));

		scrollPanePanelFieldsContent = new JScrollPane(panelFieldsContent);
		scrollPanePanelFieldsContent
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

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

		comboBoxInvoiceSupplierName = new JComboBox();
		comboBoxInvoiceSupplierName.setBounds(xTextField, y, weightTextField,
				height);
		comboBoxInvoiceSupplierName.setEnabled(false);
		populateInvoiceSupplierComboBox();
		comboBoxInvoiceSupplierName.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox = (JComboBox) e.getSource();
				int selectedComboBoxSuplierIndex = comboBox.getSelectedIndex();
				Integer selectedComboBoxSupplierId = mapSuppliers
						.get(selectedComboBoxSuplierIndex);
				int intSelectedComboBoxSupplerId = selectedComboBoxSupplierId
						.intValue();
				Supplier supplier = supplierServiceImpl
						.findSupplierById(new BigDecimal(
								intSelectedComboBoxSupplerId));
				populateInvoiceSupplierFields(supplier);
			}
		});

		y = y + height + spacing;

		labelInvoiceCustomerName = new JLabel("Име на клиент:");
		labelInvoiceCustomerName.setBounds(xLabel, y, weightLabel, height);

		comboBoxInvoiceCustomerName = new JComboBox();
		comboBoxInvoiceCustomerName.setBounds(xTextField, y, weightTextField,
				height);
		populateInvoiceCustomerComboBox();
		comboBoxInvoiceCustomerName.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox = (JComboBox) e.getSource();
				int selectedComboBoxCustomerIndex = comboBox.getSelectedIndex();
				if (selectedComboBoxCustomerIndex > 0) {
					Integer selectedComboBoxCustomerId = mapCustomers
							.get(selectedComboBoxCustomerIndex);
					int intSelectedComboBoxCustomerId = selectedComboBoxCustomerId
							.intValue();
					Customer customer = customerServiceImpl
							.findCustomerById(new BigDecimal(
									intSelectedComboBoxCustomerId));
					populateInvoiceCustomerFields(customer);
				}
			}
		});

		y = y + height + spacing;

		labelInvoiceSupplierAddress = new JLabel("Адреса на корисник:");
		labelInvoiceSupplierAddress.setBounds(xLabel, y, weightLabel, height);

		nonEditableTextFieldColor = new Color(255, 255, 204);

		textFieldInvoiceSupplierAddress = new JTextField();
		textFieldInvoiceSupplierAddress.setBounds(xTextField, y,
				weightTextField, height);
		textFieldInvoiceSupplierAddress.setEditable(false);
		textFieldInvoiceSupplierAddress
				.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceSupplierAddress.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceSupplierPhone = new JLabel("Телефон на корисник:");
		labelInvoiceSupplierPhone.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceSupplierPhone = new JTextField();
		textFieldInvoiceSupplierPhone.setBounds(xTextField, y, weightTextField,
				height);
		textFieldInvoiceSupplierPhone.setEditable(false);
		textFieldInvoiceSupplierPhone.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceSupplierPhone.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceSupplierEmail = new JLabel("Email на корисник:");
		labelInvoiceSupplierEmail.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceSupplierEmail = new JTextField();
		textFieldInvoiceSupplierEmail.setBounds(xTextField, y, weightTextField,
				height);
		textFieldInvoiceSupplierEmail.setEditable(false);
		textFieldInvoiceSupplierEmail.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceSupplierEmail.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceSupplierRegistryNumber = new JLabel("Регистарски број:");
		labelInvoiceSupplierRegistryNumber.setBounds(xLabel, y, weightLabel,
				height);

		textFieldInvoiceSupplierRegistryNumber = new JTextField();
		textFieldInvoiceSupplierRegistryNumber.setBounds(xTextField, y,
				weightTextField, height);
		textFieldInvoiceSupplierRegistryNumber.setEditable(false);
		textFieldInvoiceSupplierRegistryNumber
				.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceSupplierRegistryNumber
				.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceSupplierBankName = new JLabel("Банка:");
		labelInvoiceSupplierBankName.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceSupplierBankName = new JTextField();
		textFieldInvoiceSupplierBankName.setBounds(xTextField, y,
				weightTextField, height);
		textFieldInvoiceSupplierBankName.setEditable(false);
		textFieldInvoiceSupplierBankName
				.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceSupplierBankName.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceSupplierBankAccount = new JLabel("Банкарска сметка:");
		labelInvoiceSupplierBankAccount.setBounds(xLabel, y, weightLabel,
				height);

		textFieldInvoiceSupplierBankAccount = new JTextField();
		textFieldInvoiceSupplierBankAccount.setBounds(xTextField, y,
				weightTextField, height);
		textFieldInvoiceSupplierBankAccount.setEditable(false);
		textFieldInvoiceSupplierBankAccount
				.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceSupplierBankAccount.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceSupplierAdditionalInfo = new JLabel(
				"Дополнителни информации за корисник:");
		labelInvoiceSupplierAdditionalInfo.setBounds(xLabel, y, weightLabel,
				height);

		textFieldInvoiceSupplierAdditionalInfo = new JTextField();
		textFieldInvoiceSupplierAdditionalInfo.setBounds(xTextField, y,
				weightTextField, height);
		textFieldInvoiceSupplierAdditionalInfo.setEditable(false);
		textFieldInvoiceSupplierAdditionalInfo
				.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceSupplierAdditionalInfo
				.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceCustomerAddress = new JLabel("Адреса на клиент:");
		labelInvoiceCustomerAddress.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceCustomerAddress = new JTextField();
		textFieldInvoiceCustomerAddress.setBounds(xTextField, y,
				weightTextField, height);
		textFieldInvoiceCustomerAddress.setEditable(false);
		textFieldInvoiceCustomerAddress
				.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceCustomerAddress.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceCustomerPhoneNumber = new JLabel(
				"Телефонски број на клиент:");
		labelInvoiceCustomerPhoneNumber.setBounds(xLabel, y, weightLabel,
				height);

		textFieldInvoiceCustomerPhoneNumber = new JTextField();
		textFieldInvoiceCustomerPhoneNumber.setBounds(xTextField, y,
				weightTextField, height);
		textFieldInvoiceCustomerPhoneNumber.setEditable(false);
		textFieldInvoiceCustomerPhoneNumber
				.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceCustomerPhoneNumber.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceCustomerEmail = new JLabel("Email на клиент:");
		labelInvoiceCustomerEmail.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceCustomerEmail = new JTextField();
		textFieldInvoiceCustomerEmail.setBounds(xTextField, y, weightTextField,
				height);
		textFieldInvoiceCustomerEmail.setEditable(false);
		textFieldInvoiceCustomerEmail.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceCustomerEmail.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceCustomerAdditionalInfo = new JLabel(
				"Дополнителни информации за клиент:");
		labelInvoiceCustomerAdditionalInfo.setBounds(xLabel, y, weightLabel,
				height);

		textFieldInvoiceCustomerAdditionalInfo = new JTextField();
		textFieldInvoiceCustomerAdditionalInfo.setBounds(xTextField, y,
				weightTextField, height);
		textFieldInvoiceCustomerAdditionalInfo.setEditable(false);
		textFieldInvoiceCustomerAdditionalInfo
				.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceCustomerAdditionalInfo
				.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceNumber = new JLabel("Број на фактура:");
		labelInvoiceNumber.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceNumber = new JTextField();
		textFieldInvoiceNumber
				.setBounds(xTextField, y, weightTextField, height);
		textFieldInvoiceNumber.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceSerialNumber = new JLabel("Сериски број на фактура:");
		labelInvoiceSerialNumber.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceSerialNumber = new JTextField();
		textFieldInvoiceSerialNumber.setBounds(xTextField, y, weightTextField,
				height);
		textFieldInvoiceSerialNumber.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceDate = new JLabel("Датум на фактура:");
		labelInvoiceDate.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceDate = new JTextField();
		textFieldInvoiceDate.setBounds(xTextField, y, weightTextField, height);
		textFieldInvoiceDate.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceDeliveryNumber = new JLabel("Испратница бр.:");
		labelInvoiceDeliveryNumber.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceDeliveryNumber = new JTextField();
		textFieldInvoiceDeliveryNumber.setBounds(xTextField, y,
				weightTextField, height);
		textFieldInvoiceDeliveryNumber.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceDeliveryDate = new JLabel("Дата на испратница:");
		labelInvoiceDeliveryDate.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceDeliveryDate = new JTextField();
		textFieldInvoiceDeliveryDate.setBounds(xTextField, y, weightTextField,
				height);
		textFieldInvoiceDeliveryDate.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceTotalQuantityPriceWithoutTax = new JLabel(
				"Збир износ без данок:");
		labelInvoiceTotalQuantityPriceWithoutTax.setBounds(xLabel, y,
				weightLabel, height);

		textFieldInvoiceTotalQuantityPriceWithoutTax = new JTextField();
		textFieldInvoiceTotalQuantityPriceWithoutTax.setBounds(xTextField, y,
				weightTextField, height);
		textFieldInvoiceTotalQuantityPriceWithoutTax.setText("0,00");
		textFieldInvoiceTotalQuantityPriceWithoutTax
				.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceTotalQuantityPriceWithoutTax.setMargin(new Insets(2, 2,
				2, 2));

		y = y + height + spacing;

		labelInvoiceTotalQuantityTax = new JLabel("Збир износ на ДДВ:");
		labelInvoiceTotalQuantityTax.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceTotalQuantityTax = new JTextField();
		textFieldInvoiceTotalQuantityTax.setBounds(xTextField, y,
				weightTextField, height);
		textFieldInvoiceTotalQuantityTax.setText("0,00");
		textFieldInvoiceTotalQuantityTax
				.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceTotalQuantityTax.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceTotalQuantityPrice = new JLabel("Вкупен износ со ДДВ:");
		labelInvoiceTotalQuantityPrice
				.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceTotalQuantityPrice = new JTextField();
		textFieldInvoiceTotalQuantityPrice.setBounds(xTextField, y,
				weightTextField, height);
		textFieldInvoiceTotalQuantityPrice.setText("0,00");
		textFieldInvoiceTotalQuantityPrice
				.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceTotalQuantityPrice.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoicePaymentInfo = new JLabel("Исплата на фактура:");
		labelInvoicePaymentInfo.setBounds(xLabel, y, weightLabel, height);

		comboBoxInvoicePaymentInfo = new JComboBox();
		comboBoxInvoicePaymentInfo.setBounds(xTextField, y, weightTextField,
				height);
		comboBoxInvoicePaymentInfo.setEnabled(false);
		populateInvoicePaymentInfoComboBox();
		comboBoxInvoicePaymentInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		y = y + height + spacing;

		labelInvoiceAdditionalInfo = new JLabel(
				"Дополнителни информации фактура:");
		labelInvoiceAdditionalInfo.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceAdditionalInfo = new JTextField();
		textFieldInvoiceAdditionalInfo.setBounds(xTextField, y,
				weightTextField, height);
		textFieldInvoiceAdditionalInfo.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceCustomerId = new JLabel("Клиент ID:");
		labelInvoiceCustomerId.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceCustomerId = new JTextField();
		textFieldInvoiceCustomerId.setBounds(xTextField, y, weightTextField,
				height);
		textFieldInvoiceCustomerId.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceSupplierId = new JLabel("Корисник ID:");
		labelInvoiceSupplierId.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceSupplierId = new JTextField();
		textFieldInvoiceSupplierId.setBounds(xTextField, y, weightTextField,
				height);
		textFieldInvoiceSupplierId.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceId = new JLabel("Фактура ID:");
		labelInvoiceId.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceId = new JTextField();
		textFieldInvoiceId.setBounds(xTextField, y, weightTextField, height);
		textFieldInvoiceId.setMargin(new Insets(2, 2, 2, 2));

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
		panelFieldsContent.add(comboBoxInvoiceSupplierName);

		panelFieldsContent.add(labelInvoiceCustomerName);
		panelFieldsContent.add(comboBoxInvoiceCustomerName);

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

		panelFieldsContent.add(labelInvoiceDeliveryNumber);
		panelFieldsContent.add(textFieldInvoiceDeliveryNumber);

		panelFieldsContent.add(labelInvoiceDeliveryDate);
		panelFieldsContent.add(textFieldInvoiceDeliveryDate);

		panelFieldsContent.add(labelInvoiceTotalQuantityPriceWithoutTax);
		panelFieldsContent.add(textFieldInvoiceTotalQuantityPriceWithoutTax);

		panelFieldsContent.add(labelInvoiceTotalQuantityTax);
		panelFieldsContent.add(textFieldInvoiceTotalQuantityTax);

		panelFieldsContent.add(labelInvoiceTotalQuantityPrice);
		panelFieldsContent.add(textFieldInvoiceTotalQuantityPrice);

		panelFieldsContent.add(labelInvoicePaymentInfo);
		panelFieldsContent.add(comboBoxInvoicePaymentInfo);

		panelFieldsContent.add(labelInvoiceAdditionalInfo);
		panelFieldsContent.add(textFieldInvoiceAdditionalInfo);

		panelButtonsContent.add(buttonSave);
		panelButtonsContent.add(buttonCancel);

		panelFields.add(scrollPanePanelFieldsContent, BorderLayout.CENTER);

		panelButtons.add(panelButtonsContent, BorderLayout.CENTER);

		panelAll.add(panelButtons, BorderLayout.SOUTH);
		panelAll.add(panelFields, BorderLayout.CENTER);

		contentPane.add(panelAll);

		Supplier supplier = getSelectedComboBoxInvoiceSupplier();
		populateInvoiceSupplierFields(supplier);

		dialog.pack();
		dialog.setVisible(true);
		dialog.setLocationRelativeTo(null);

		return dialog;
	}

	public void populateInvoiceSupplierFields(Supplier supplier) {
		textFieldInvoiceSupplierId.setText(supplier.getSupplierId().toString());
		textFieldInvoiceSupplierAddress.setText(supplier.getSupplierAddress());
		textFieldInvoiceSupplierPhone
				.setText(supplier.getSupplierPhoneNumber());
		textFieldInvoiceSupplierEmail.setText(supplier.getSupplierEmail());
		textFieldInvoiceSupplierRegistryNumber.setText(supplier
				.getSupplierRegistryNumber());
		textFieldInvoiceSupplierBankName
				.setText(supplier.getSupplierBankName());
		textFieldInvoiceSupplierBankAccount.setText(supplier
				.getSupplierBankAccount());
		textFieldInvoiceSupplierAdditionalInfo.setText(supplier
				.getSupplierAdditionalInfo());
	}

	public void populateInvoiceCustomerFields(Customer customer) {
		textFieldInvoiceCustomerId.setText(customer.getCustomerId().toString());
		textFieldInvoiceCustomerAddress.setText(customer.getCustomerAddress());
		textFieldInvoiceCustomerPhoneNumber.setText(customer
				.getCustomerPhoneNumber());
		textFieldInvoiceCustomerEmail.setText(customer.getCustomerEmail());
		textFieldInvoiceCustomerAdditionalInfo.setText(customer
				.getCustomerAdditionalInfo());
	}

	public void populateInvoiceSupplierComboBox() {
		mapSuppliers = new HashMap<Integer, Integer>();
		List<Supplier> suppliers = supplierServiceImpl.findAllSuppliers();
		comboBoxInvoiceSupplierName.removeAllItems();
		int i = 0;

		for (Supplier supplier : suppliers) {
			String supplierName = supplier.getSupplierName();
			BigDecimal supplierId = supplier.getSupplierId();
			Integer integerSupplierId = supplierId.intValue();
			mapSuppliers.put(i++, integerSupplierId);
			comboBoxInvoiceSupplierName.addItem(supplierName);
		}
	}

	public void populateInvoiceCustomerComboBox() {
		mapCustomers = new HashMap<Integer, Integer>();
		List<Customer> customers = customerServiceImpl.findAllCustomers();
		comboBoxInvoiceCustomerName.removeAllItems();
		String firstItem = "- Избери клеинт -";
		comboBoxInvoiceCustomerName.addItem(firstItem);
		int i = 1;

		for (Customer customer : customers) {
			String customerName = customer.getCustomerName();
			BigDecimal customerId = customer.getCustomerId();
			Integer integerCustomerId = customerId.intValue();
			mapCustomers.put(i++, integerCustomerId);
			comboBoxInvoiceCustomerName.addItem(customerName);
		}
	}

	public void populateInvoicePaymentInfoComboBox() {
		comboBoxInvoicePaymentInfo.removeAllItems();
		mapPaymentsInfo = new HashMap<Integer, String>();
		mapPaymentsInfo.put(0, "Неисплатена");
		mapPaymentsInfo.put(1, "Делумно исплатена");
		mapPaymentsInfo.put(2, "Исплатена");

		for (Map.Entry<Integer, String> entry : mapPaymentsInfo.entrySet()) {
			comboBoxInvoicePaymentInfo.addItem(entry.getValue());
		}
	}

	public Invoice getInvoiceFromInvoiceFields() {
		Invoice invoice = new Invoice();
		invoice.setCustomer(getSelectedComboBoxInvoiceCustomer());
		invoice.setSupplier(getSelectedComboBoxInvoiceSupplier());
		invoice.setInvoiceNumber(textFieldInvoiceNumber.getText());
		invoice.setInvoiceSerialNumber(textFieldInvoiceSerialNumber.getText());
		invoice.setInvoiceDate(textFieldInvoiceDate.getText());
		invoice.setInvoiceDeliveryDate(textFieldInvoiceDeliveryDate.getText());
		invoice.setInvoiceDeliveryNumber(textFieldInvoiceDeliveryNumber
				.getText());
		invoice.setInvoiceTotalPrice(textFieldInvoiceTotalQuantityPrice
				.getText());
		invoice.setInvoiceTotalTax(textFieldInvoiceTotalQuantityPriceWithoutTax
				.getText());
		invoice.setInvoiceTotalPriceTax(textFieldInvoiceTotalQuantityTax
				.getText());
		invoice.setInvoicePaymentInfo(getSelectedComboBoxInvoicePaymentInfo());
		invoice.setInvoiceAdditionalInfo(textFieldInvoiceAdditionalInfo
				.getText());

		return invoice;
	}

	public Supplier getSelectedComboBoxInvoiceSupplier() {
		int selectedComboBoxSupplierIndex = comboBoxInvoiceSupplierName
				.getSelectedIndex();
		Integer selectedComboBoxSupplierId = mapSuppliers
				.get(selectedComboBoxSupplierIndex);
		int intSelectedComboBoxSupplierId = selectedComboBoxSupplierId
				.intValue();
		Supplier supplier = supplierServiceImpl
				.findSupplierById(new BigDecimal(String
						.valueOf(intSelectedComboBoxSupplierId)));

		return supplier;
	}

	public Customer getSelectedComboBoxInvoiceCustomer() {
		int selectedComboBoxCustomerIndex = comboBoxInvoiceCustomerName
				.getSelectedIndex();
		Integer selectedComboBoxCustomerId = mapCustomers
				.get(selectedComboBoxCustomerIndex);
		int intSelectedComboBoxCustomerId = selectedComboBoxCustomerId
				.intValue();
		Customer customer = customerServiceImpl
				.findCustomerById(new BigDecimal(String
						.valueOf(intSelectedComboBoxCustomerId)));

		return customer;
	}

	public String getSelectedComboBoxInvoicePaymentInfo() {
		String paymentInfo;
		comboBoxInvoicePaymentInfo.setSelectedIndex(0);
		int selectedComboBoxPaymentInfoIndex = comboBoxInvoicePaymentInfo
				.getSelectedIndex();
		paymentInfo = mapPaymentsInfo.get(selectedComboBoxPaymentInfoIndex);
		return paymentInfo;
	}

	public void saveInvoiceAndDispose() {
		Invoice invoice = getInvoiceFromInvoiceFields();

		invoiceServiceImpl.saveInvoice(invoice);
		dialog.dispose();
		invoicePanelImpl.populateInvoiceTable();
	}

	public void dispose() {
		dialog.dispose();
	}

}
