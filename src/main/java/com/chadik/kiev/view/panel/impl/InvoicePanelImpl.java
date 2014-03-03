package com.chadik.kiev.view.panel.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chadik.kiev.model.Customer;
import com.chadik.kiev.model.Invoice;
import com.chadik.kiev.model.Supplier;
import com.chadik.kiev.service.ICustomerService;
import com.chadik.kiev.service.IInvoiceService;
import com.chadik.kiev.service.ISupplierService;
import com.chadik.kiev.util.PanelUtil;
import com.chadik.kiev.util.TableUtil;
import com.chadik.kiev.view.dialog.IInvoiceDialog;
import com.chadik.kiev.view.dialog.IOrderItemDialog;
import com.chadik.kiev.view.panel.IInvoicePanel;
import com.chadik.kiev.view.panel.IOrderItemPanel;

@Component
public class InvoicePanelImpl implements IInvoicePanel {

	private JPanel panelAll;

	private JPanel panelTableHolder;
	private JPanel panelTableHolderContent;
	private JPanel panelTableHolderContentTable;
	private JPanel panelTableHolderContentButtons;

	private JPanel panelInfoHolder;
	private JPanel panelInfoHolderContent;
	private JPanel panelInfoHolderContentInfo;
	private JPanel panelInfoHolderContentInfoFields;
	private JPanel panelInfoHolderContentInfoTable;
	private JPanel panelInfoHolderContentButtons;

	private JScrollPane scrollPaneTable;
	private JScrollPane scrollPanePanelInfoHolderContentInfo;
	private JScrollBar verticalScrollBar;

	private DefaultTableModel defaultTableModel;
	private JTable table;

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
	private JTextField textFieldInvoiceTotalPrice;
	private JTextField textFieldInvoiceTotalTax;
	private JTextField textFieldInvoiceTotalPriceTax;
	private JTextField textFieldInvoiceAdditionalInfo;

	private JComboBox comboBoxInvoiceSupplierName;
	private JComboBox comboBoxInvoiceCustomerName;
	private JComboBox comboBoxInvoicePaymentInfo;

	private JButton buttonNew;
	private JButton buttonEdit;
	private JButton buttonDelete;
	private JButton buttonSave;
	private JButton buttonCancel;
	private JButton buttonAddProduct;
	private JButton buttonDeleteProduct;
	private JButton buttonPrint;

	private Color originalTextFieldColor;
	private Color nonEditableTextFieldColor;
	
	private String selectedInvoiceTableRow;

	private Map<Integer, Integer> mapSuppliers;
	private Map<Integer, Integer> mapCustomers;
	private Map<Integer, String> mapPaymentsInfo;

	private List<Invoice> invoices;

	@Autowired
	private IInvoiceService invoiceServiceImpl;
	@Autowired
	private ISupplierService supplierServiceImpl;
	@Autowired
	private ICustomerService customerServiceImpl;
	@Autowired
	private IOrderItemPanel orderItemPanelImpl;
	@Autowired
	private IInvoiceDialog invoiceDialogImpl;
	@Autowired
	private IOrderItemDialog orderItemDialogImpl;

	@Override
	public JPanel initInvoicePanel() {
		panelAll = new JPanel();
		panelAll.setLayout(new BorderLayout());

		panelTableHolder = new JPanel();
		panelTableHolder.setLayout(new BorderLayout());

		panelTableHolderContent = new JPanel();
		panelTableHolderContent.setLayout(new BorderLayout());

		panelTableHolderContentTable = new JPanel();
		panelTableHolderContentTable.setLayout(new BorderLayout());
		panelTableHolderContentTable.setPreferredSize(new Dimension(400, 550));
		panelTableHolderContentTable.setBackground(new Color(224, 224, 224));
		panelTableHolderContentTable.setBorder(new TitledBorder(
				"Листа на фактури"));

		panelTableHolderContentButtons = new JPanel();
		panelTableHolderContentButtons.setLayout(new FlowLayout());
		panelTableHolderContentButtons.setPreferredSize(new Dimension(400, 50));
		panelTableHolderContentButtons.setBorder(new EmptyBorder(5, 5, 5, 5));

		panelInfoHolder = new JPanel();
		panelInfoHolder.setLayout(new BorderLayout());
		panelInfoHolder.setBackground(new Color(224, 224, 224));

		panelInfoHolderContent = new JPanel();
		panelInfoHolderContent.setLayout(new BorderLayout());

		panelInfoHolderContentInfo = new JPanel();
		panelInfoHolderContentInfo.setLayout(new BorderLayout());

		panelInfoHolderContentInfoFields = new JPanel();
		panelInfoHolderContentInfoFields.setLayout(new GridBagLayout());
		panelInfoHolderContentInfoFields.setPreferredSize(new Dimension(400,
				800));
		panelInfoHolderContentInfoFields
				.setBackground(new Color(192, 192, 192));
		panelInfoHolderContentInfoFields.setBorder(BorderFactory
				.createCompoundBorder(new EmptyBorder(10, 10, 10, 10),
						new EtchedBorder()));

		scrollPanePanelInfoHolderContentInfo = new JScrollPane(
				panelInfoHolderContentInfoFields);

		panelInfoHolderContentInfoTable = orderItemPanelImpl
				.initOrderItemPanel();

		panelInfoHolderContentButtons = new JPanel();
		panelInfoHolderContentButtons.setLayout(new FlowLayout());
		panelInfoHolderContentButtons.setPreferredSize(new Dimension(400, 50));
		panelInfoHolderContentButtons.setBorder(new EmptyBorder(5, 5, 5, 5));

		defaultTableModel = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		defaultTableModel.setColumnIdentifiers(getTableInvoiceColumnNames());

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				String selectedRowInvoiceId = (String) table.getValueAt(row, 1);
				Invoice invoice = getInvoiceFromInvoiceTable(selectedRowInvoiceId);
				populateInvoiceFields(invoice);
				orderItemPanelImpl.setInvoice(invoice);
				orderItemPanelImpl.populateOrderItemTable();				
			}
		});

		table.setModel(defaultTableModel);

		TableUtil.hideColumns(table, getTableInvoiceHiddenColumns());

		TableUtil.allignCells(table, SwingConstants.CENTER);

		table.getColumnModel().getColumn(0).setMaxWidth(100);

		scrollPaneTable = new JScrollPane(table);

		verticalScrollBar = scrollPaneTable.getVerticalScrollBar();

		buttonNew = new JButton("Креирај");
		buttonNew.setPreferredSize(new Dimension(100, 25));
		buttonNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				invoiceDialogImpl.initInvoiceDialog();
			}
		});

		buttonEdit = new JButton("Измени");
		buttonEdit.setPreferredSize(new Dimension(100, 25));
		buttonEdit.setEnabled(false);
		buttonEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setInvoiceFieldsEditable();
				setInvoiceInfoButtonsEnabled();
			}
		});

		buttonDelete = new JButton("Избриши");
		buttonDelete.setPreferredSize(new Dimension(100, 25));
		buttonDelete.setEnabled(false);

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
		comboBoxInvoiceSupplierName.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox = (JComboBox) e.getSource();
				int selectedComboBoxSuplierIndex = comboBox.getSelectedIndex();
				if (selectedComboBoxSuplierIndex != -1) {
					Integer selectedComboBoxSupplierId = mapSuppliers
							.get(selectedComboBoxSuplierIndex);
					int intSelectedComboBoxSupplerId = selectedComboBoxSupplierId
							.intValue();
					Supplier supplier = supplierServiceImpl
							.findSupplierById(new BigDecimal(
									intSelectedComboBoxSupplerId));
					populateInvoiceSupplierFields(supplier);
				}
			}
		});

		y = y + height + spacing;

		labelInvoiceCustomerName = new JLabel("Име на клиент:");
		labelInvoiceCustomerName.setBounds(xLabel, y, weightLabel, height);

		comboBoxInvoiceCustomerName = new JComboBox();
		comboBoxInvoiceCustomerName.setBounds(xTextField, y, weightTextField,
				height);
		comboBoxInvoiceCustomerName.setEnabled(false);
		comboBoxInvoiceCustomerName.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox = (JComboBox) e.getSource();
				int selectedComboBoxCustomerIndex = comboBox.getSelectedIndex();
				if (selectedComboBoxCustomerIndex != -1) {
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

		textFieldInvoiceSupplierAddress = new JTextField();
		textFieldInvoiceSupplierAddress.setBounds(xTextField, y,
				weightTextField, height);
		textFieldInvoiceSupplierAddress.setMargin(new Insets(2, 2, 2, 2));

		originalTextFieldColor = textFieldInvoiceSupplierAddress
				.getBackground();
		nonEditableTextFieldColor = new Color(255, 255, 204);

		y = y + height + spacing;

		labelInvoiceSupplierPhone = new JLabel("Телефон на корисник:");
		labelInvoiceSupplierPhone.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceSupplierPhone = new JTextField();
		textFieldInvoiceSupplierPhone.setBounds(xTextField, y, weightTextField,
				height);
		textFieldInvoiceSupplierPhone.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceSupplierEmail = new JLabel("Email на корисник:");
		labelInvoiceSupplierEmail.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceSupplierEmail = new JTextField();
		textFieldInvoiceSupplierEmail.setBounds(xTextField, y, weightTextField,
				height);
		textFieldInvoiceSupplierEmail.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceSupplierRegistryNumber = new JLabel("Регистарски број:");
		labelInvoiceSupplierRegistryNumber.setBounds(xLabel, y, weightLabel,
				height);

		textFieldInvoiceSupplierRegistryNumber = new JTextField();
		textFieldInvoiceSupplierRegistryNumber.setBounds(xTextField, y,
				weightTextField, height);
		textFieldInvoiceSupplierRegistryNumber
				.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceSupplierBankName = new JLabel("Банка:");
		labelInvoiceSupplierBankName.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceSupplierBankName = new JTextField();
		textFieldInvoiceSupplierBankName.setBounds(xTextField, y,
				weightTextField, height);
		textFieldInvoiceSupplierBankName.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceSupplierBankAccount = new JLabel("Банкарска сметка:");
		labelInvoiceSupplierBankAccount.setBounds(xLabel, y, weightLabel,
				height);

		textFieldInvoiceSupplierBankAccount = new JTextField();
		textFieldInvoiceSupplierBankAccount.setBounds(xTextField, y,
				weightTextField, height);
		textFieldInvoiceSupplierBankAccount.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceSupplierAdditionalInfo = new JLabel(
				"Забелешки за корисник:");
		labelInvoiceSupplierAdditionalInfo.setBounds(xLabel, y, weightLabel,
				height);

		textFieldInvoiceSupplierAdditionalInfo = new JTextField();
		textFieldInvoiceSupplierAdditionalInfo.setBounds(xTextField, y,
				weightTextField, height);
		textFieldInvoiceSupplierAdditionalInfo
				.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceCustomerAddress = new JLabel("Адреса на клиент:");
		labelInvoiceCustomerAddress.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceCustomerAddress = new JTextField();
		textFieldInvoiceCustomerAddress.setBounds(xTextField, y,
				weightTextField, height);
		textFieldInvoiceCustomerAddress.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceCustomerPhoneNumber = new JLabel(
				"Телефонски број на клиент:");
		labelInvoiceCustomerPhoneNumber.setBounds(xLabel, y, weightLabel,
				height);

		textFieldInvoiceCustomerPhoneNumber = new JTextField();
		textFieldInvoiceCustomerPhoneNumber.setBounds(xTextField, y,
				weightTextField, height);
		textFieldInvoiceCustomerPhoneNumber.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceCustomerEmail = new JLabel("Email на клиент:");
		labelInvoiceCustomerEmail.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceCustomerEmail = new JTextField();
		textFieldInvoiceCustomerEmail.setBounds(xTextField, y, weightTextField,
				height);
		textFieldInvoiceCustomerEmail.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceCustomerAdditionalInfo = new JLabel("Забелешки за клиент:");
		labelInvoiceCustomerAdditionalInfo.setBounds(xLabel, y, weightLabel,
				height);

		textFieldInvoiceCustomerAdditionalInfo = new JTextField();
		textFieldInvoiceCustomerAdditionalInfo.setBounds(xTextField, y,
				weightTextField, height);
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

		labelInvoiceDeliveryDate = new JLabel("Delivery date:");
		labelInvoiceDeliveryDate.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceDeliveryDate = new JTextField();
		textFieldInvoiceDeliveryDate.setBounds(xTextField, y, weightTextField,
				height);
		textFieldInvoiceDeliveryDate.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceDeliveryNumber = new JLabel("Delivery number:");
		labelInvoiceDeliveryNumber.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceDeliveryNumber = new JTextField();
		textFieldInvoiceDeliveryNumber.setBounds(xTextField, y,
				weightTextField, height);
		textFieldInvoiceDeliveryNumber.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceTotalPrice = new JLabel("Нејасно 3:");
		labelInvoiceTotalPrice.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceTotalPrice = new JTextField();
		textFieldInvoiceTotalPrice.setBounds(xTextField, y, weightTextField,
				height);
		textFieldInvoiceTotalPrice.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceTotalTax = new JLabel("Нејасно 2:");
		labelInvoiceTotalTax.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceTotalTax = new JTextField();
		textFieldInvoiceTotalTax.setBounds(xTextField, y, weightTextField,
				height);
		textFieldInvoiceTotalTax.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceTotalPriceTax = new JLabel("Нејасно 1:");
		labelInvoiceTotalPriceTax.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceTotalPriceTax = new JTextField();
		textFieldInvoiceTotalPriceTax.setBounds(xTextField, y, weightTextField,
				height);
		textFieldInvoiceTotalPriceTax.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoicePaymentInfo = new JLabel("Исплата на фактура:");
		labelInvoicePaymentInfo.setBounds(xLabel, y, weightLabel, height);

		comboBoxInvoicePaymentInfo = new JComboBox();
		comboBoxInvoicePaymentInfo.setBounds(xTextField, y, weightTextField,
				height);
		comboBoxInvoicePaymentInfo.setEnabled(false);
		comboBoxInvoicePaymentInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		y = y + height + spacing;

		labelInvoiceAdditionalInfo = new JLabel("Забелешки:");
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
		buttonSave.setEnabled(false);
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveInvoice();
			}
		});

		buttonCancel = new JButton("Откажи");
		buttonCancel.setPreferredSize(new Dimension(100, 25));
		buttonCancel.setEnabled(false);
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String selectedRowInvoiceId = (String) table
						.getValueAt(row, 1);
				Invoice invoice = getInvoiceFromInvoiceTable(selectedRowInvoiceId);
				orderItemPanelImpl.setInvoice(invoice);
				orderItemDialogImpl.setInvoice(invoice);
				
				populateInvoiceFields(invoice);
				setInvoiceFieldsNonEditable();
				setInvoiceInfoButtonsDisabled();
			}
		});

		buttonAddProduct = new JButton("Додади");
		buttonAddProduct.setBackground(new Color(224, 224, 224));
		buttonAddProduct.setPreferredSize(new Dimension(100, 25));
		buttonAddProduct.setEnabled(false);
		buttonAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String selectedRowInvoiceId = (String) table
						.getValueAt(row, 1);
				Invoice invoice = getInvoiceFromInvoiceTable(selectedRowInvoiceId);
				orderItemDialogImpl.setInvoice(invoice);
				orderItemDialogImpl.initOrderItemDialog();
			}
		});

		buttonDeleteProduct = new JButton("Одземи");
		buttonDeleteProduct.setBackground(new Color(224, 224, 224));
		buttonDeleteProduct.setPreferredSize(new Dimension(100, 25));
		buttonDeleteProduct.setEnabled(false);

		buttonPrint = new JButton("Испечати");
		buttonPrint.setPreferredSize(new Dimension(100, 25));
		buttonPrint.setEnabled(false);

		panelTableHolderContentTable.add(scrollPaneTable);

		panelTableHolderContentButtons.add(buttonNew);
		panelTableHolderContentButtons.add(buttonEdit);
		panelTableHolderContentButtons.add(PanelUtil.createJSeparator());
		panelTableHolderContentButtons.add(buttonDelete);

		panelTableHolderContent.add(panelTableHolderContentTable,
				BorderLayout.CENTER);
		panelTableHolderContent.add(panelTableHolderContentButtons,
				BorderLayout.SOUTH);

		panelTableHolder.add(panelTableHolderContent);

		panelInfoHolderContentInfoFields.add(labelInvoiceSupplierName,
				firstLabelConstrains());
		panelInfoHolderContentInfoFields.add(comboBoxInvoiceSupplierName,
				textFieldConstraints());

		panelInfoHolderContentInfoFields.add(labelInvoiceCustomerName,
				labelConstraints());
		panelInfoHolderContentInfoFields.add(comboBoxInvoiceCustomerName,
				textFieldConstraints());

		panelInfoHolderContentInfoFields.add(labelInvoiceSupplierAddress,
				labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceSupplierAddress,
				textFieldConstraints());

		panelInfoHolderContentInfoFields.add(labelInvoiceSupplierPhone,
				labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceSupplierPhone,
				textFieldConstraints());

		panelInfoHolderContentInfoFields.add(labelInvoiceSupplierEmail,
				labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceSupplierEmail,
				textFieldConstraints());

		panelInfoHolderContentInfoFields.add(
				labelInvoiceSupplierRegistryNumber, labelConstraints());
		panelInfoHolderContentInfoFields.add(
				textFieldInvoiceSupplierRegistryNumber, textFieldConstraints());

		panelInfoHolderContentInfoFields.add(labelInvoiceSupplierBankName,
				labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceSupplierBankName,
				textFieldConstraints());

		panelInfoHolderContentInfoFields.add(labelInvoiceSupplierBankAccount,
				labelConstraints());
		panelInfoHolderContentInfoFields.add(
				textFieldInvoiceSupplierBankAccount, textFieldConstraints());

		panelInfoHolderContentInfoFields.add(
				labelInvoiceSupplierAdditionalInfo, labelConstraints());
		panelInfoHolderContentInfoFields.add(
				textFieldInvoiceSupplierAdditionalInfo, textFieldConstraints());

		panelInfoHolderContentInfoFields.add(labelInvoiceCustomerAddress,
				labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceCustomerAddress,
				textFieldConstraints());

		panelInfoHolderContentInfoFields.add(labelInvoiceCustomerPhoneNumber,
				labelConstraints());
		panelInfoHolderContentInfoFields.add(
				textFieldInvoiceCustomerPhoneNumber, textFieldConstraints());

		panelInfoHolderContentInfoFields.add(labelInvoiceCustomerEmail,
				labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceCustomerEmail,
				textFieldConstraints());

		panelInfoHolderContentInfoFields.add(
				labelInvoiceCustomerAdditionalInfo, labelConstraints());
		panelInfoHolderContentInfoFields.add(
				textFieldInvoiceCustomerAdditionalInfo, textFieldConstraints());

		panelInfoHolderContentInfoFields.add(labelInvoiceNumber,
				labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceNumber,
				textFieldConstraints());

		panelInfoHolderContentInfoFields.add(labelInvoiceSerialNumber,
				labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceSerialNumber,
				textFieldConstraints());

		panelInfoHolderContentInfoFields.add(labelInvoiceDate,
				labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceDate,
				textFieldConstraints());

		panelInfoHolderContentInfoFields.add(labelInvoiceDeliveryDate,
				labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceDeliveryDate,
				textFieldConstraints());

		panelInfoHolderContentInfoFields.add(labelInvoiceDeliveryNumber,
				labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceDeliveryNumber,
				textFieldConstraints());

		panelInfoHolderContentInfoFields.add(labelInvoiceTotalPrice,
				labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceTotalPrice,
				textFieldConstraints());

		panelInfoHolderContentInfoFields.add(labelInvoiceTotalTax,
				labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceTotalTax,
				textFieldConstraints());

		panelInfoHolderContentInfoFields.add(labelInvoiceTotalPriceTax,
				labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceTotalPriceTax,
				textFieldConstraints());

		panelInfoHolderContentInfoFields.add(labelInvoicePaymentInfo,
				labelConstraints());
		panelInfoHolderContentInfoFields.add(comboBoxInvoicePaymentInfo,
				textFieldConstraints());

		panelInfoHolderContentInfoFields.add(labelInvoiceAdditionalInfo,
				labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceAdditionalInfo,
				lastComponentConstrains());

		panelInfoHolderContentButtons.add(buttonSave);
		panelInfoHolderContentButtons.add(buttonCancel);
		panelInfoHolderContentButtons.add(PanelUtil.createJSeparator());
		panelInfoHolderContentButtons.add(buttonAddProduct);
		panelInfoHolderContentButtons.add(buttonDeleteProduct);
		panelInfoHolderContentButtons.add(PanelUtil.createJSeparator());
		panelInfoHolderContentButtons.add(buttonPrint);

		panelInfoHolderContentInfo.add(scrollPanePanelInfoHolderContentInfo,
				BorderLayout.CENTER);
		panelInfoHolderContentInfo.add(panelInfoHolderContentInfoTable,
				BorderLayout.SOUTH);

		panelInfoHolderContent.add(panelInfoHolderContentInfo,
				BorderLayout.CENTER);
		panelInfoHolderContent.add(panelInfoHolderContentButtons,
				BorderLayout.SOUTH);

		panelInfoHolder.add(panelInfoHolderContent, BorderLayout.CENTER);

		panelAll.add(panelTableHolder, BorderLayout.WEST);
		panelAll.add(panelInfoHolder, BorderLayout.CENTER);

		setSelectedInvoiceTableRow("");
		populateInvoiceTable();

		return panelAll;
	}

	@Override
	public void populateInvoiceTable() {
		String selectedRowInvoiceId = "";
		invoices = invoiceServiceImpl.findAllInvoices();

		int i = 0;

		defaultTableModel.setRowCount(0);

		for (Invoice invoice : invoices) {
			defaultTableModel.addRow(new String[] { Integer.toString(++i),
					invoice.getInvoiceId().toString(),
					invoice.getSupplier().getSupplierName(),
					invoice.getCustomer().getCustomerName(),
					invoice.getInvoiceNumber(),
					invoice.getInvoiceSerialNumber(), invoice.getInvoiceDate(),
					invoice.getInvoiceDeliveryDate(),
					invoice.getInvoiceDeliveryNumber(),
					invoice.getInvoiceTotalPrice(),
					invoice.getInvoiceTotalTax(),
					invoice.getInvoiceTotalPriceTax(),
					invoice.getInvoicePaymentInfo(),
					invoice.getInvoiceAdditionalInfo() });
		}

		if (table.getRowCount() > 0) {
			
			populateInvoiceComboBoxes();
			
			int selectedRow = table.getRowCount() - 1;
			
			if (!getSelectedInvoiceTableRow().equals("")) {
				selectedRow = Integer.parseInt(getSelectedInvoiceTableRow());
			}
			
			table.setRowSelectionInterval(selectedRow,
					selectedRow);

			selectedRowInvoiceId = (String) table.getValueAt(
					selectedRow, 1);
			Invoice invoice = getInvoiceFromInvoiceTable(selectedRowInvoiceId);
			populateInvoiceFields(invoice);
			
			setInvoiceTableButtonsEnabled();
			
			setSelectedInvoiceTableRow("");
			
		}

		scrollPaneTable.validate();
		verticalScrollBar.setValue(verticalScrollBar.getMaximum());

		setInvoiceFieldsNonEditable();

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
		int i = 0;

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
	
	public void populateInvoiceFields(Invoice invoice) {
		comboBoxInvoiceSupplierName.setSelectedIndex(0);
		comboBoxInvoiceCustomerName
				.setSelectedIndex(getSelectedInvoiceCustomerComboBoxIndex(invoice));
		textFieldInvoiceSupplierAddress.setText(invoice.getSupplier()
				.getSupplierAddress());
		textFieldInvoiceSupplierPhone.setText(invoice.getSupplier()
				.getSupplierPhoneNumber());
		textFieldInvoiceSupplierEmail.setText(invoice.getSupplier()
				.getSupplierEmail());
		textFieldInvoiceSupplierRegistryNumber.setText(invoice.getSupplier()
				.getSupplierRegistryNumber());
		textFieldInvoiceSupplierBankName.setText(invoice.getSupplier()
				.getSupplierBankName());
		textFieldInvoiceSupplierBankAccount.setText(invoice.getSupplier()
				.getSupplierBankAccount());
		textFieldInvoiceSupplierAdditionalInfo.setText(invoice.getSupplier()
				.getSupplierAdditionalInfo());
		textFieldInvoiceCustomerAddress.setText(invoice.getCustomer()
				.getCustomerAddress());
		textFieldInvoiceCustomerPhoneNumber.setText(invoice.getCustomer()
				.getCustomerPhoneNumber());
		textFieldInvoiceCustomerEmail.setText(invoice.getCustomer()
				.getCustomerEmail());
		textFieldInvoiceCustomerAdditionalInfo.setText(invoice.getCustomer()
				.getCustomerAdditionalInfo());
		textFieldInvoiceNumber.setText(invoice.getInvoiceNumber());
		textFieldInvoiceSerialNumber.setText(invoice.getInvoiceSerialNumber());
		textFieldInvoiceDate.setText(invoice.getInvoiceDate());
		textFieldInvoiceDeliveryDate.setText(invoice.getInvoiceDeliveryDate());
		textFieldInvoiceDeliveryNumber.setText(invoice
				.getInvoiceDeliveryNumber());
		textFieldInvoiceTotalPrice.setText(invoice.getInvoiceTotalPrice());
		textFieldInvoiceTotalTax.setText(invoice.getInvoiceTotalTax());
		textFieldInvoiceTotalPriceTax
				.setText(invoice.getInvoiceTotalPriceTax());
		comboBoxInvoicePaymentInfo
				.setSelectedIndex(getSelectedInvoicePaymentInfoComboBoxIndex(invoice));
		textFieldInvoiceAdditionalInfo.setText(invoice
				.getInvoiceAdditionalInfo());
		textFieldInvoiceId.setText(invoice.getInvoiceId().toString());
	}
	
	public void populateInvoiceComboBoxes() {
		populateInvoiceSupplierComboBox();
		populateInvoiceCustomerComboBox();
		populateInvoicePaymentInfoComboBox();
	}
	
	public void clearInvoiceComboBoxes() {
		comboBoxInvoiceSupplierName.removeAllItems();
		comboBoxInvoiceCustomerName.removeAllItems();
		comboBoxInvoicePaymentInfo.removeAllItems();
	}

	public int[] getTableInvoiceHiddenColumns() {
		return new int[] { 1, 5, 7, 8, 9, 10, 11, 12, 13 };
	}

	public String[] getTableInvoiceColumnNames() {
		return new String[] { "Реден Бр.", "Id", "Корисник", "Клиент",
				"Број на фактура", "Сериски број на фактура", "Дата",
				"Датум на достава", "Број на достава", "Непознато 1",
				"Непознато 2", "Непознато 3", "Исплата на фактура",
				"Дополнителни информации" };
	}

	public Invoice getInvoiceFromInvoiceTable(String selectedRowInvoiceId) {
		BigDecimal invoiceId = new BigDecimal(selectedRowInvoiceId);
		return invoiceServiceImpl.findInvoiceById(invoiceId);
	}

	public Invoice getInvoiceFromInvoiceFields() {
		int row = table.getSelectedRow();
		String selectedRowInvoiceId = (String) table.getValueAt(row, 1);
		Invoice invoice = getInvoiceFromInvoiceTable(selectedRowInvoiceId);
		invoice.setSupplier(getSelectedComboBoxInvoiceSupplier());
		invoice.setCustomer(getSelectedComboBoxInvoiceCustomer());
		invoice.setInvoiceNumber(textFieldInvoiceNumber.getText());
		invoice.setInvoiceSerialNumber(textFieldInvoiceSerialNumber.getText());
		invoice.setInvoiceDate(textFieldInvoiceDate.getText());
		invoice.setInvoiceDeliveryDate(textFieldInvoiceDeliveryDate.getText());
		invoice.setInvoiceDeliveryNumber(textFieldInvoiceDeliveryNumber
				.getText());
		invoice.setInvoiceTotalPrice(textFieldInvoiceTotalPrice.getText());
		invoice.setInvoiceTotalTax(textFieldInvoiceTotalTax.getText());
		invoice.setInvoiceTotalPriceTax(textFieldInvoiceTotalPriceTax.getText());
		invoice.setInvoicePaymentInfo(getSelectedComboBoxInvoicePaymentInfo());
		invoice.setInvoiceAdditionalInfo(textFieldInvoiceAdditionalInfo
				.getText());

		return invoice;
	}

	public Supplier getSelectedComboBoxInvoiceSupplier() {
		int selectedComboBoxSupplierIndex = comboBoxInvoiceSupplierName
				.getSelectedIndex();
		Integer selectedComboBoxSupplierId = mapCustomers
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
		int selectedComboBoxPaymentInfoIndex = comboBoxInvoicePaymentInfo
				.getSelectedIndex();
		paymentInfo = mapPaymentsInfo.get(selectedComboBoxPaymentInfoIndex);
		return paymentInfo;
	}
	
	public int getSelectedInvoiceSupplierComboBoxIndex(Invoice invoice) {
		int selectedIndex;
		Integer key = null;
		Supplier supplier = invoice.getSupplier();
		BigDecimal supplierId = supplier.getSupplierId();
		Integer integerSupplierId = supplierId.intValue();
		for (Map.Entry<Integer, Integer> entry : mapSuppliers.entrySet()) {
			if (integerSupplierId != null
					&& integerSupplierId.equals(entry.getValue())) {
				key = entry.getKey();
				break;
			}
		}
		selectedIndex = key.intValue();
		return selectedIndex;
	}

	public int getSelectedInvoiceCustomerComboBoxIndex(Invoice invoice) {
		int selectedIndex;
		Integer key = null;
		Customer customer = invoice.getCustomer();
		BigDecimal customerId = customer.getCustomerId();
		Integer integerCustomerId = customerId.intValue();
		for (Map.Entry<Integer, Integer> entry : mapCustomers.entrySet()) {
			if (integerCustomerId != null
					&& integerCustomerId.equals(entry.getValue())) {
				key = entry.getKey();
				break;
			}
		}
		selectedIndex = key.intValue();
		return selectedIndex;
	}

	public int getSelectedInvoicePaymentInfoComboBoxIndex(Invoice invoice) {
		int selectedIndex;
		Integer key = null;
		String paymentInfo = invoice.getInvoicePaymentInfo();
		for (Map.Entry<Integer, String> entry : mapPaymentsInfo.entrySet()) {
			if (paymentInfo != null && paymentInfo.equals(entry.getValue())) {
				key = entry.getKey();
				break;
			}
		}
		selectedIndex = key.intValue();
		return selectedIndex;
	}
	

	@Override
	public String getSelectedInvoiceTableRow() {
		return selectedInvoiceTableRow;
	}
	
	@Override
	public void setSelectedInvoiceTableRow(String selectedInvoiceTableRow) {
		this.selectedInvoiceTableRow = selectedInvoiceTableRow;		
	}

	public void setInvoiceTableButtonsEnabled() {
		buttonEdit.setEnabled(true);
		buttonDelete.setEnabled(true);
	}

	public void setInvoiceTableButtonsDisabled() {
		buttonEdit.setEnabled(false);
		buttonDelete.setEnabled(false);
	}

	public void setInvoiceFieldsNonEditable() {
		textFieldInvoiceId.setBackground(nonEditableTextFieldColor);
		comboBoxInvoiceSupplierName.setEnabled(false);
		comboBoxInvoiceCustomerName.setEnabled(false);
		textFieldInvoiceSupplierAddress.setEditable(false);
		textFieldInvoiceSupplierAddress
				.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceSupplierPhone.setEditable(false);
		textFieldInvoiceSupplierPhone.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceSupplierEmail.setEditable(false);
		textFieldInvoiceSupplierEmail.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceSupplierRegistryNumber.setEditable(false);
		textFieldInvoiceSupplierRegistryNumber
				.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceSupplierBankName.setEditable(false);
		textFieldInvoiceSupplierBankName
				.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceSupplierBankAccount.setEditable(false);
		textFieldInvoiceSupplierBankAccount
				.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceSupplierAdditionalInfo.setEditable(false);
		textFieldInvoiceSupplierAdditionalInfo
				.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceCustomerAddress.setEditable(false);
		textFieldInvoiceCustomerAddress
				.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceCustomerPhoneNumber.setEditable(false);
		textFieldInvoiceCustomerPhoneNumber
				.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceCustomerEmail.setEditable(false);
		textFieldInvoiceCustomerEmail.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceCustomerAdditionalInfo.setEditable(false);
		textFieldInvoiceCustomerAdditionalInfo
				.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceNumber.setEditable(false);
		textFieldInvoiceNumber.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceSerialNumber.setEditable(false);
		textFieldInvoiceSerialNumber.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceDate.setEditable(false);
		textFieldInvoiceDate.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceDeliveryDate.setEditable(false);
		textFieldInvoiceDeliveryDate.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceDeliveryNumber.setEditable(false);
		textFieldInvoiceDeliveryNumber.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceTotalPrice.setEditable(false);
		textFieldInvoiceTotalPrice.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceTotalTax.setEditable(false);
		textFieldInvoiceTotalTax.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceTotalPriceTax.setEditable(false);
		textFieldInvoiceTotalPriceTax.setBackground(nonEditableTextFieldColor);
		comboBoxInvoicePaymentInfo.setEnabled(false);
		textFieldInvoiceAdditionalInfo.setEditable(false);
		textFieldInvoiceAdditionalInfo.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceId.setEditable(false);
	}

	public void setInvoiceFieldsEditable() {
		comboBoxInvoiceCustomerName.setEnabled(true);
		textFieldInvoiceNumber.setEditable(true);
		textFieldInvoiceNumber.setBackground(originalTextFieldColor);
		textFieldInvoiceSerialNumber.setEditable(true);
		textFieldInvoiceSerialNumber.setBackground(originalTextFieldColor);
		textFieldInvoiceDate.setEditable(true);
		textFieldInvoiceDate.setBackground(originalTextFieldColor);
		textFieldInvoiceDeliveryDate.setEditable(true);
		textFieldInvoiceDeliveryDate.setBackground(originalTextFieldColor);
		textFieldInvoiceDeliveryNumber.setEditable(true);
		textFieldInvoiceDeliveryNumber.setBackground(originalTextFieldColor);
		comboBoxInvoicePaymentInfo.setEnabled(true);
		textFieldInvoiceAdditionalInfo.setEditable(true);
		textFieldInvoiceAdditionalInfo.setBackground(originalTextFieldColor);
		textFieldInvoiceId.setEditable(true);
		textFieldInvoiceId.setBackground(originalTextFieldColor);
	}
	
	public void setInvoiceInfoButtonsEnabled() {
		buttonSave.setEnabled(true);
		buttonCancel.setEnabled(true);
		buttonAddProduct.setEnabled(true);
		buttonDeleteProduct.setEnabled(true);
		buttonPrint.setEnabled(true);
	}

	public void setInvoiceInfoButtonsDisabled() {
		buttonSave.setEnabled(false);
		buttonCancel.setEnabled(false);
		buttonAddProduct.setEnabled(false);
		buttonDeleteProduct.setEnabled(false);
		buttonPrint.setEnabled(false);
	}	
	
	@Override
	public void setProductButtonsEnabled() {
		buttonAddProduct.setEnabled(true);
		buttonDeleteProduct.setEnabled(true);
	}
	
	public void saveInvoice() {
		Invoice invoice = getInvoiceFromInvoiceFields();
		invoiceServiceImpl.saveInvoice(invoice);
		int row = table.getSelectedRow();
		String selectedRow = Integer.toString(row);
		setSelectedInvoiceTableRow(selectedRow);
		populateInvoiceTable();
		table.setRowSelectionInterval(row, row);
		setInvoiceFieldsNonEditable();
		setInvoiceInfoButtonsDisabled();
	}
	
	public GridBagConstraints invoicePanelConstraints() {
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(4, 10, 4, 10);
		return c;
	}
	
	public GridBagConstraints firstLabelConstrains() {
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(15, 10, 0, 0);
		c.anchor = GridBagConstraints.BASELINE_LEADING;
		c.weightx = 0.0;
		return c;
	}

	public GridBagConstraints firstTextFieldConstrains() {
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 4, 4, 4);
		c.anchor = GridBagConstraints.BASELINE;
		c.weightx = 1.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = GridBagConstraints.REMAINDER;
		return c;
	}


	public GridBagConstraints labelConstraints() {
		GridBagConstraints c = invoicePanelConstraints();
		c.anchor = GridBagConstraints.BASELINE_LEADING;
		c.weightx = 0.0;
		return c;
	}

	public GridBagConstraints textFieldConstraints() {
		GridBagConstraints c = invoicePanelConstraints();
		c.anchor = GridBagConstraints.BASELINE;
		c.weightx = 1.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = GridBagConstraints.REMAINDER;
		return c;
	}

	public GridBagConstraints lastComponentConstrains() {
		GridBagConstraints c = invoicePanelConstraints();
		c.anchor = GridBagConstraints.BASELINE;
		c.weightx = 1.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weighty = 1.0;
		return c;
	}

}
