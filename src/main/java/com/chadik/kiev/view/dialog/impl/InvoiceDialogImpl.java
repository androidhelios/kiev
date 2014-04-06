package com.chadik.kiev.view.dialog.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chadik.kiev.model.BankInfo;
import com.chadik.kiev.model.Customer;
import com.chadik.kiev.model.Invoice;
import com.chadik.kiev.model.OrderItem;
import com.chadik.kiev.model.Supplier;
import com.chadik.kiev.service.IBankInfoService;
import com.chadik.kiev.service.ICustomerService;
import com.chadik.kiev.service.IInvoiceService;
import com.chadik.kiev.service.ISupplierService;
import com.chadik.kiev.view.FrameMain;
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
	private JLabel labelInvoiceSupplierAdditionalInfo;
	private JLabel labelInvoiceCustomerId;
	private JLabel labelInvoiceCustomerName;
	private JLabel labelInvoiceCustomerAddress;
	private JLabel labelInvoiceCustomerPhoneNumber;
	private JLabel labelInvoiceCustomerEmail;
	private JLabel labelInvoiceCustomerAdditionalInfo;
	private JLabel labelInvoiceBankInfoId;
	private JLabel labelInvoiceBankInfoBankName;
	private JLabel labelInvoiceBankInfoBankAccount;
	private JLabel labelInvoiceBankInfoBankAddress;
	private JLabel labelInvoiceBankInfoBankPhoneNumber;
	private JLabel labelInvoiceBankInfoBankEmail;
	private JLabel labelInvoiceBankInfoBankAdditionalInfo;
	private JLabel labelInvoiceNumberBeforeLast;
	private JLabel labelInvoiceNumber;
	private JLabel labelInvoiceSerialNumber;
	private JLabel labelInvoiceDate;
	private JLabel labelInvoiceDeliveryDate;
	private JLabel labelInvoiceDeliveryNumber;
	private JLabel labelInvoiceTotalQuantityPrice;
	private JLabel labelInvoiceTotalQuantityPriceWithoutTax;
	private JLabel labelInvoiceTotalQuantityTax;
	private JLabel labelInvoiceCurrency;
	private JLabel labelInvoicePaymentInfo;
	private JLabel labelInvoiceAdditionalInfo;

	private JTextField textFieldInvoiceId;
	private JTextField textFieldInvoiceSupplierId;
	private JTextField textFieldInvoiceSupplierAddress;
	private JTextField textFieldInvoiceSupplierPhone;
	private JTextField textFieldInvoiceSupplierEmail;
	private JTextField textFieldInvoiceSupplierRegistryNumber;
	private JTextField textFieldInvoiceSupplierAdditionalInfo;
	private JTextField textFieldInvoiceCustomerId;
	private JTextField textFieldInvoiceCustomerAddress;
	private JTextField textFieldInvoiceCustomerPhoneNumber;
	private JTextField textFieldInvoiceCustomerEmail;
	private JTextField textFieldInvoiceCustomerAdditionalInfo;
	private JTextField textFieldInvoiceBankInfoId;
	private JTextField textFieldInvoiceBankInfoBankAccount;
	private JTextField textFieldInvoiceBankInfoBankAddress;
	private JTextField textFieldInvoiceBankInfoBankPhoneNumber;
	private JTextField textFieldInvoiceBankInfoBankEmail;
	private JTextField textFieldInvoiceBankInfoBankAdditionalInfo;
	private JTextField textFieldInvoiceNumberBeforeLast;
	private JTextField textFieldInvoiceNumber;
	private JTextField textFieldInvoiceSerialNumber;
	private JTextField textFieldInvoiceDate;
	private JTextField textFieldInvoiceDeliveryDate;
	private JTextField textFieldInvoiceDeliveryNumber;
	private JTextField textFieldInvoiceCurrency;
	private JTextField textFieldInvoiceTotalQuantityPrice;
	private JTextField textFieldInvoiceTotalQuantityPriceWithoutTax;
	private JTextField textFieldInvoiceTotalQuantityTax;
	private JTextField textFieldInvoiceAdditionalInfo;

	private JComboBox comboBoxInvoiceSupplierName;
	private JComboBox comboBoxInvoiceCustomerName;
	private JComboBox comboBoxInvoicePaymentInfo;
	private JComboBox comboBoxInvoiceBankInfoBankName;

	private JScrollPane scrollPanePanelFieldsContent;

	private JButton buttonSave;
	private JButton buttonCancel;

	private Color nonEditableTextFieldColor;
	private Color mandatoryTextFieldColor;

	private Map<Integer, Integer> mapSuppliers;
	private Map<Integer, Integer> mapCustomers;
	private Map<Integer, Integer> mapBanksInfo;
	private Map<Integer, String> mapPaymentsInfo;

	private List<Invoice> invoices;

	@Autowired
	private IInvoiceService invoiceServiceImpl;
	@Autowired
	private IInvoicePanel invoicePanelImpl;
	@Autowired
	private ISupplierService supplierServiceImpl;
	@Autowired
	private ICustomerService customerServiceImpl;
	@Autowired
	private IBankInfoService bankInfoServiceImpl;
	@Autowired
	private FrameMain frameMain;

	@Override
	public JDialog initInvoiceDialog() {
		dialog = new JDialog(frameMain.getMainFrame(), true);
		dialog.setTitle("Нова Фактура");
		dialog.setResizable(false);
		dialog.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
			}

			public void windowClosing(WindowEvent e) {
				invoicePanelImpl.setInvoiceTableButtonsEnabled();
			}
		});

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
		panelFieldsContent.setPreferredSize(new Dimension(450, 900));

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

		mandatoryTextFieldColor = new Color(204, 0, 0);

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

		labelInvoiceCustomerName = new JLabel("Име на клиент:");
		labelInvoiceCustomerName.setBounds(xLabel, y, weightLabel, height);
		labelInvoiceCustomerName.setForeground(mandatoryTextFieldColor);

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

		labelInvoiceBankInfoBankName = new JLabel("Банка:");
		labelInvoiceBankInfoBankName.setBounds(xLabel, y, weightLabel, height);
		labelInvoiceBankInfoBankName.setForeground(mandatoryTextFieldColor);

		comboBoxInvoiceBankInfoBankName = new JComboBox();
		comboBoxInvoiceBankInfoBankName.setBounds(xTextField, y,
				weightTextField, height);
		populateInvoiceBankInfoComboBox();
		comboBoxInvoiceBankInfoBankName.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox = (JComboBox) e.getSource();
				int selectedComboBoxBankInfoIndex = comboBox.getSelectedIndex();
				if (selectedComboBoxBankInfoIndex > 0) {
					Integer selectedComboBoxBankInfoId = mapBanksInfo
							.get(selectedComboBoxBankInfoIndex);
					int intSelectedComboBoxBankInfoId = selectedComboBoxBankInfoId
							.intValue();
					BankInfo bankInfo = bankInfoServiceImpl
							.findBankInfoById(new BigDecimal(
									intSelectedComboBoxBankInfoId));
					populateInvoiceBankInfoFields(bankInfo);
				}
			}
		});

		y = y + height + spacing;

		labelInvoiceBankInfoBankAccount = new JLabel("Банкарска сметка:");
		labelInvoiceBankInfoBankAccount.setBounds(xLabel, y, weightLabel,
				height);

		textFieldInvoiceBankInfoBankAccount = new JTextField();
		textFieldInvoiceBankInfoBankAccount.setBounds(xTextField, y,
				weightTextField, height);
		textFieldInvoiceBankInfoBankAccount.setEditable(false);
		textFieldInvoiceBankInfoBankAccount
				.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceBankInfoBankAccount.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceBankInfoBankAddress = new JLabel("Адреса на банка:");
		labelInvoiceBankInfoBankAddress.setBounds(xLabel, y, weightLabel,
				height);

		textFieldInvoiceBankInfoBankAddress = new JTextField();
		textFieldInvoiceBankInfoBankAddress.setBounds(xTextField, y,
				weightTextField, height);
		textFieldInvoiceBankInfoBankAddress.setEditable(false);
		textFieldInvoiceBankInfoBankAddress
				.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceBankInfoBankAddress.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceBankInfoBankPhoneNumber = new JLabel("Телефон на банка:");
		labelInvoiceBankInfoBankPhoneNumber.setBounds(xLabel, y, weightLabel,
				height);

		textFieldInvoiceBankInfoBankPhoneNumber = new JTextField();
		textFieldInvoiceBankInfoBankPhoneNumber.setBounds(xTextField, y,
				weightTextField, height);
		textFieldInvoiceBankInfoBankPhoneNumber.setEditable(false);
		textFieldInvoiceBankInfoBankPhoneNumber
				.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceBankInfoBankPhoneNumber
				.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceBankInfoBankEmail = new JLabel("Email на банка:");
		labelInvoiceBankInfoBankEmail.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceBankInfoBankEmail = new JTextField();
		textFieldInvoiceBankInfoBankEmail.setBounds(xTextField, y,
				weightTextField, height);
		textFieldInvoiceBankInfoBankEmail.setEditable(false);
		textFieldInvoiceBankInfoBankEmail
				.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceBankInfoBankEmail.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceBankInfoBankAdditionalInfo = new JLabel("Дополнителни информации за банка:");
		labelInvoiceBankInfoBankAdditionalInfo.setBounds(xLabel, y,
				weightLabel, height);

		textFieldInvoiceBankInfoBankAdditionalInfo = new JTextField();
		textFieldInvoiceBankInfoBankAdditionalInfo.setBounds(xTextField, y,
				weightTextField, height);
		textFieldInvoiceBankInfoBankAdditionalInfo.setEditable(false);
		textFieldInvoiceBankInfoBankAdditionalInfo
				.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceBankInfoBankAdditionalInfo.setMargin(new Insets(2, 2,
				2, 2));

		y = y + height + spacing;

		labelInvoiceNumberBeforeLast = new JLabel("Претходен број на фактура:");
		labelInvoiceNumberBeforeLast.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceNumberBeforeLast = new JTextField(
				getInvoiceNumberBeforeLast());
		textFieldInvoiceNumberBeforeLast.setBounds(xTextField, y,
				weightTextField, height);
		textFieldInvoiceNumberBeforeLast.setEditable(false);
		textFieldInvoiceNumberBeforeLast
				.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceNumberBeforeLast.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceNumber = new JLabel("Број на фактура:");
		labelInvoiceNumber.setBounds(xLabel, y, weightLabel, height);
		labelInvoiceNumber.setForeground(mandatoryTextFieldColor);

		textFieldInvoiceNumber = new JTextField();
		textFieldInvoiceNumber
				.setBounds(xTextField, y, weightTextField, height);
		textFieldInvoiceNumber.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;
		
		labelInvoiceDate = new JLabel("Датум на фактура:");
		labelInvoiceDate.setBounds(xLabel, y, weightLabel, height);
		labelInvoiceDate.setForeground(mandatoryTextFieldColor);

		textFieldInvoiceDate = new JTextField();
		textFieldInvoiceDate.setBounds(xTextField, y, weightTextField, height);
		textFieldInvoiceDate.setMargin(new Insets(2, 2, 2, 2));
		
		y = y + height + spacing;

		labelInvoiceCurrency = new JLabel("Валута:");
		labelInvoiceCurrency.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceCurrency = new JTextField();
		textFieldInvoiceCurrency.setBounds(xTextField, y, weightTextField,
				height);
		textFieldInvoiceCurrency.setMargin(new Insets(2, 2, 2, 2));
		
		y = y + height + spacing;

		labelInvoiceSerialNumber = new JLabel("Сериски број на фактура:");
		labelInvoiceSerialNumber.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceSerialNumber = new JTextField();
		textFieldInvoiceSerialNumber.setBounds(xTextField, y, weightTextField,
				height);
		textFieldInvoiceSerialNumber.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceDeliveryNumber = new JLabel("Испратница бр:");
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
		
		labelInvoicePaymentInfo = new JLabel("Статус на фактура:");
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

		labelInvoiceBankInfoId = new JLabel("Сметка ID:");
		labelInvoiceBankInfoId.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceBankInfoId = new JTextField();
		textFieldInvoiceBankInfoId.setBounds(xTextField, y, weightTextField,
				height);
		textFieldInvoiceBankInfoId.setMargin(new Insets(2, 2, 2, 2));

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
				if (validateInvoiceFields()) {
					saveInvoiceAndDispose();
					JOptionPane.showMessageDialog(frameMain.getMainFrame(), "Фактурата е запишана",
							"Информација", JOptionPane.INFORMATION_MESSAGE);
				} else {
					dialog.setVisible(false);

					Object[] options = { "OK" };
					int input = JOptionPane.showOptionDialog(null,
							"Погрешен внес", "Грешка",
							JOptionPane.ERROR_MESSAGE,
							JOptionPane.ERROR_MESSAGE, null, options,
							options[0]);

					if (input == 0) {
						dialog.setVisible(true);
					}
				}
			}
		});

		buttonCancel = new JButton("Откажи");
		buttonCancel.setPreferredSize(new Dimension(100, 25));
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				invoicePanelImpl.setInvoiceTableButtonsEnabled();
				
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

		panelFieldsContent.add(labelInvoiceBankInfoBankName);
		panelFieldsContent.add(comboBoxInvoiceBankInfoBankName);

		panelFieldsContent.add(labelInvoiceBankInfoBankAccount);
		panelFieldsContent.add(textFieldInvoiceBankInfoBankAccount);

		panelFieldsContent.add(labelInvoiceBankInfoBankAddress);
		panelFieldsContent.add(textFieldInvoiceBankInfoBankAddress);

		panelFieldsContent.add(labelInvoiceBankInfoBankPhoneNumber);
		panelFieldsContent.add(textFieldInvoiceBankInfoBankPhoneNumber);

		panelFieldsContent.add(labelInvoiceBankInfoBankEmail);
		panelFieldsContent.add(textFieldInvoiceBankInfoBankEmail);

		panelFieldsContent.add(labelInvoiceBankInfoBankAdditionalInfo);
		panelFieldsContent.add(textFieldInvoiceBankInfoBankAdditionalInfo);

		panelFieldsContent.add(labelInvoiceBankInfoBankPhoneNumber);
		panelFieldsContent.add(textFieldInvoiceBankInfoBankPhoneNumber);

		panelFieldsContent.add(labelInvoiceNumberBeforeLast);
		panelFieldsContent.add(textFieldInvoiceNumberBeforeLast);

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

		panelFieldsContent.add(labelInvoiceCurrency);
		panelFieldsContent.add(textFieldInvoiceCurrency);

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
		dialog.setLocationRelativeTo(frameMain.getMainFrame());
		dialog.setVisible(true);

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

	public void populateInvoiceBankInfoFields(BankInfo bankInfo) {
		textFieldInvoiceBankInfoId.setText(bankInfo.getBankInfoId().toString());
		textFieldInvoiceBankInfoBankAccount.setText(bankInfo
				.getBankInfoAccount());
		textFieldInvoiceBankInfoBankAddress.setText(bankInfo
				.getBankInfoAddress());
		textFieldInvoiceBankInfoBankPhoneNumber.setText(bankInfo
				.getBankInfoPhoneNumber());
		textFieldInvoiceBankInfoBankEmail.setText(bankInfo.getBankInfoEmail());
		textFieldInvoiceBankInfoBankAdditionalInfo.setText(bankInfo
				.getBankInfoAdditionalInfo());

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

	public void populateInvoiceBankInfoComboBox() {
		mapBanksInfo = new HashMap<Integer, Integer>();
		List<BankInfo> banksInfo = bankInfoServiceImpl.findAllBanksInfo();
		comboBoxInvoiceBankInfoBankName.removeAllItems();
		String firstItem = "- Избери сметка -";
		comboBoxInvoiceBankInfoBankName.addItem(firstItem);
		int i = 1;

		for (BankInfo bankInfo : banksInfo) {
			String bankInfoBankName = bankInfo.getBankInfoName();
			BigDecimal bankInfoId = bankInfo.getBankInfoId();
			Integer integerBankInfoId = bankInfoId.intValue();
			mapBanksInfo.put(i++, integerBankInfoId);
			comboBoxInvoiceBankInfoBankName.addItem(bankInfoBankName);
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
		invoice.setBankInfo(getSelectedComboBoxInvoiceBankInfo());
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
		invoice.setInvoiceCurrency(textFieldInvoiceCurrency.getText());
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

	public BankInfo getSelectedComboBoxInvoiceBankInfo() {
		int selectedComboBoxBankInfoIndex = comboBoxInvoiceBankInfoBankName
				.getSelectedIndex();
		Integer selectedComboBoxBankInfoId = mapBanksInfo
				.get(selectedComboBoxBankInfoIndex);
		int intSelectedComboBoxBankInfoId = selectedComboBoxBankInfoId
				.intValue();
		BankInfo bankInfo = bankInfoServiceImpl
				.findBankInfoById(new BigDecimal(String
						.valueOf(intSelectedComboBoxBankInfoId)));

		return bankInfo;
	}

	public String getSelectedComboBoxInvoicePaymentInfo() {
		String paymentInfo;
		comboBoxInvoicePaymentInfo.setSelectedIndex(0);
		int selectedComboBoxPaymentInfoIndex = comboBoxInvoicePaymentInfo
				.getSelectedIndex();
		paymentInfo = mapPaymentsInfo.get(selectedComboBoxPaymentInfoIndex);
		return paymentInfo;
	}

	public String getInvoiceNumberBeforeLast() {
		String invoceNumberBeforeLast = "";
		invoices = invoiceServiceImpl.findAllInvoices();
		if (invoices.size() > 0) {
			invoceNumberBeforeLast = invoices.get(invoices.size() - 1)
					.getInvoiceNumber();
		}

		return invoceNumberBeforeLast;
	}

	public boolean isValidDate(String dateString) {
		boolean result = true;
		String expectedPattern = "dd.MM.yyyy";
		SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);

		Date date = null;

		try {
			date = formatter.parse(dateString);
		} catch (ParseException e) {
			result = false;
		}

		return result;
	}

	public boolean validateInvoiceFields() {
		boolean result = true;
		result = result
				&& (comboBoxInvoiceCustomerName.getSelectedIndex() > 0)
				&& (comboBoxInvoiceBankInfoBankName.getSelectedIndex() > 0)
				&& (!"".equals(textFieldInvoiceNumber.getText()))
				&& (!"".equals(textFieldInvoiceDate.getText()) && (isValidDate(textFieldInvoiceDate
						.getText())));

		return result;
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
