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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import javax.swing.table.TableCellRenderer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chadik.kiev.model.BankInfo;
import com.chadik.kiev.model.Customer;
import com.chadik.kiev.model.Invoice;
import com.chadik.kiev.model.Supplier;
import com.chadik.kiev.printer.IInvoicePrinter;
import com.chadik.kiev.service.IBankInfoService;
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
	private JLabel labelTotalQuantityPriceWithoutTax;
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
	private Color mandatoryTextFieldColor;

	private String selectedInvoiceTableRow;

	private boolean editMode;

	private Map<Integer, Integer> mapSuppliers;
	private Map<Integer, Integer> mapCustomers;
	private Map<Integer, Integer> mapBanksInfo;
	private Map<Integer, String> mapPaymentsInfo;

	private List<Invoice> invoices;

	@Autowired
	private IInvoiceService invoiceServiceImpl;
	@Autowired
	private ISupplierService supplierServiceImpl;
	@Autowired
	private ICustomerService customerServiceImpl;
	@Autowired
	private IBankInfoService bankInfoServiceImpl;
	@Autowired
	private IOrderItemPanel orderItemPanelImpl;
	@Autowired
	private IInvoiceDialog invoiceDialogImpl;
	@Autowired
	private IOrderItemDialog orderItemDialogImpl;
	@Autowired
	private IInvoicePrinter documentPrinterImpl;

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
				1000));
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

		table = new JTable() {
			public java.awt.Component prepareRenderer(
					TableCellRenderer renderer, int rowIndex, int colIndex) {

				java.awt.Component c = super.prepareRenderer(renderer,
						rowIndex, colIndex);

				Color halfPayedInvoiceColor = new Color(255, 255, 153);
				Color fullPayedInvoiceColor = new Color(153, 255, 153);
				Color defaultColor = javax.swing.UIManager
						.getColor("Table.dropCellForeground");

				String invoicePaymentInfo = (String) table.getValueAt(rowIndex,
						12);

				if ("Неисплатена".equals(invoicePaymentInfo)
						&& !isCellSelected(rowIndex, colIndex)) {
					c.setBackground(defaultColor);
				}

				if ("Делумно исплатена".equals(invoicePaymentInfo)
						&& !isCellSelected(rowIndex, colIndex)) {
					c.setBackground(halfPayedInvoiceColor);
				}

				if ("Исплатена".equals(invoicePaymentInfo)
						&& !isCellSelected(rowIndex, colIndex)) {
					c.setBackground(fullPayedInvoiceColor);
				}

				return c;
			}
		};

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (isEditMode()) {
					table.setEnabled(false);
				} else {
					table.setEnabled(true);
					int row = table.getSelectedRow();
					String selectedRowInvoiceId = (String) table.getValueAt(
							row, 1);
					BigDecimal bigDecimalSelectedRowInvoiceId = new BigDecimal(
							selectedRowInvoiceId);
					int intSelectedRowInvoiceId = Integer
							.valueOf(bigDecimalSelectedRowInvoiceId.intValue());
					Invoice invoice = getInvoiceFromInvoiceTable(selectedRowInvoiceId);
					populateInvoiceFields(invoice);
					buttonAddProduct.setEnabled(false);
					buttonDeleteProduct.setEnabled(false);
					buttonPrint.setEnabled(true);
					orderItemPanelImpl.setInvoiceId(intSelectedRowInvoiceId);
					orderItemPanelImpl.populateOrderItemTable();
				}
			}
		});

		table.setModel(defaultTableModel);

		TableUtil.hideColumns(table, getTableInvoiceHiddenColumns());

		TableUtil.allignCells(table, SwingConstants.CENTER);

		table.getColumnModel().getColumn(0).setMaxWidth(100);

		scrollPaneTable = new JScrollPane(table);

		verticalScrollBar = scrollPaneTable.getVerticalScrollBar();
		
		mandatoryTextFieldColor = new Color(204, 0, 0);

		buttonNew = new JButton("Креирај");
		buttonNew.setPreferredSize(new Dimension(100, 25));
		buttonNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAllButtonsDisabled();
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
				setInvoiceInfoPrintButtonDisabled();
				setInvoiceTableButtonsDisabled();
				buttonNew.setEnabled(false);
				setEditMode(true);
				table.setEnabled(false);
			}
		});

		buttonDelete = new JButton("Избриши");
		buttonDelete.setForeground(mandatoryTextFieldColor);
		buttonDelete.setPreferredSize(new Dimension(100, 25));
		buttonDelete.setEnabled(false);
		buttonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteInvoice();
			}
		});

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
		labelInvoiceCustomerName.setForeground(mandatoryTextFieldColor);

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
		
		labelInvoiceBankInfoBankName = new JLabel("Банка:");
		labelInvoiceBankInfoBankName.setBounds(xLabel, y, weightLabel, height);
		labelInvoiceBankInfoBankName.setForeground(mandatoryTextFieldColor);

		comboBoxInvoiceBankInfoBankName = new JComboBox();
		comboBoxInvoiceBankInfoBankName.setBounds(xTextField, y,
				weightTextField, height);
		comboBoxInvoiceBankInfoBankName.setEnabled(false);
		comboBoxInvoiceBankInfoBankName.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox = (JComboBox) e.getSource();
				int selectedComboBoxBankInfoIndex = comboBox.getSelectedIndex();
				if (selectedComboBoxBankInfoIndex != -1) {
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

		labelInvoiceNumberBeforeLast = new JLabel("Број на претходна фактура:");
		labelInvoiceNumberBeforeLast.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceNumberBeforeLast = new JTextField();
		textFieldInvoiceNumberBeforeLast.setBounds(xTextField, y,
				weightTextField, height);
		textFieldInvoiceNumberBeforeLast.setMargin(new Insets(2, 2, 2, 2));
		textFieldInvoiceNumberBeforeLast.setEditable(false);
		textFieldInvoiceNumberBeforeLast
				.setBackground(nonEditableTextFieldColor);

		y = y + height + spacing;

		labelInvoiceNumber = new JLabel("Број на фактура:");
		labelInvoiceNumber.setBounds(xLabel, y, weightLabel, height);
		labelInvoiceNumber.setForeground(mandatoryTextFieldColor);

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
		labelInvoiceDate.setForeground(mandatoryTextFieldColor);

		textFieldInvoiceDate = new JTextField();
		textFieldInvoiceDate.setBounds(xTextField, y, weightTextField, height);
		textFieldInvoiceDate.setMargin(new Insets(2, 2, 2, 2));

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

		labelTotalQuantityPriceWithoutTax = new JLabel("Збир износ без данок:");
		labelTotalQuantityPriceWithoutTax.setBounds(xLabel, y, weightLabel,
				height);

		textFieldInvoiceTotalQuantityPriceWithoutTax = new JTextField();
		textFieldInvoiceTotalQuantityPriceWithoutTax.setBounds(xTextField, y,
				weightTextField, height);
		textFieldInvoiceTotalQuantityPriceWithoutTax.setMargin(new Insets(2, 2,
				2, 2));

		y = y + height + spacing;

		labelInvoiceTotalQuantityTax = new JLabel("Збир износ на ДДВ:");
		labelInvoiceTotalQuantityTax.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceTotalQuantityTax = new JTextField();
		textFieldInvoiceTotalQuantityTax.setBounds(xTextField, y,
				weightTextField, height);
		textFieldInvoiceTotalQuantityTax.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceTotalQuantityPrice = new JLabel("Вкупен износ со ДДВ:");
		labelInvoiceTotalQuantityPrice
				.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceTotalQuantityPrice = new JTextField();
		textFieldInvoiceTotalQuantityPrice.setBounds(xTextField, y,
				weightTextField, height);
		textFieldInvoiceTotalQuantityPrice.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelInvoiceCurrency = new JLabel("Валута:");
		labelInvoiceCurrency.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceCurrency = new JTextField();
		textFieldInvoiceCurrency.setBounds(xTextField, y,
				weightTextField, height);
		textFieldInvoiceCurrency.setMargin(new Insets(2, 2, 2, 2));

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

		labelInvoiceAdditionalInfo = new JLabel("Забелешки за фактура:");
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
		buttonSave.setEnabled(false);
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validateInvoiceFields()) {
					saveInvoice();
					setInvoiceInfoPrintButtonEnabled();
					setEditMode(false);
					table.setEnabled(true);
				} else {
					Object[] options = { "OK" };
					int input = JOptionPane.showOptionDialog(null,
							"Погрешен внес", "Грешка",
							JOptionPane.ERROR_MESSAGE,
							JOptionPane.ERROR_MESSAGE, null, options,
							options[0]);
				}
			}
		});

		buttonCancel = new JButton("Откажи");
		buttonCancel.setPreferredSize(new Dimension(100, 25));
		buttonCancel.setEnabled(false);
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String selectedRowInvoiceId = (String) table.getValueAt(row, 1);
				Invoice invoice = getInvoiceFromInvoiceTable(selectedRowInvoiceId);

				populateInvoiceFields(invoice);
				setInvoiceFieldsNonEditable();
				setInvoiceInfoButtonsDisabled();
				setInvoiceTableButtonsEnabled();
				setEditMode(false);
				table.setEnabled(true);
			}
		});

		ImageIcon iconButtonAddProduct = new ImageIcon("img/plus.png");

		buttonAddProduct = new JButton(iconButtonAddProduct);
		// buttonAddProduct.setBackground(new Color(224, 224, 224));
		buttonAddProduct.setPreferredSize(new Dimension(25, 25));
		buttonAddProduct.setEnabled(false);
		buttonAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String selectedRowInvoiceId = (String) table.getValueAt(row, 1);
				BigDecimal bigDecimalSelectedRowInvoiceId = new BigDecimal(
						selectedRowInvoiceId);
				int intSelectedRowInvoiceId = Integer
						.valueOf(bigDecimalSelectedRowInvoiceId.intValue());
				orderItemDialogImpl.setInvoiceId(intSelectedRowInvoiceId);
				orderItemDialogImpl.initOrderItemDialog();
			}
		});

		ImageIcon iconButtonDeleteProduct = new ImageIcon(
				"img/minus.png");

		buttonDeleteProduct = new JButton(iconButtonDeleteProduct);
		// buttonDeleteProduct.setBackground(new Color(224, 224, 224));
		buttonDeleteProduct.setPreferredSize(new Dimension(25, 25));
		buttonDeleteProduct.setEnabled(false);
		buttonDeleteProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String selectedRowInvoiceId = (String) table.getValueAt(row, 1);
				BigDecimal bigDecimalSelectedRowInvoiceId = new BigDecimal(
						selectedRowInvoiceId);
				int intSelectedRowInvoiceId = Integer
						.valueOf(bigDecimalSelectedRowInvoiceId.intValue());
				orderItemPanelImpl.setInvoiceId(intSelectedRowInvoiceId);
				orderItemPanelImpl.deleteOrderItem();
			}
		});

		buttonPrint = new JButton("Испечати");
		buttonPrint.setPreferredSize(new Dimension(100, 25));
		buttonPrint.setEnabled(false);
		buttonPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String selectedRowInvoiceId = (String) table.getValueAt(row, 1);
				Invoice invoice = getInvoiceFromInvoiceTable(selectedRowInvoiceId);
				documentPrinterImpl.setInvoice(invoice);
				documentPrinterImpl.initInvoicePrinter();
				JOptionPane.showMessageDialog(null, "Фактурата е испечатена",
						"Информација", JOptionPane.INFORMATION_MESSAGE);
			}
		});

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
		
		panelInfoHolderContentInfoFields.add(
				labelInvoiceSupplierAdditionalInfo, labelConstraints());
		panelInfoHolderContentInfoFields.add(
				textFieldInvoiceSupplierAdditionalInfo, textFieldConstraints());

		panelInfoHolderContentInfoFields.add(labelInvoiceCustomerName,
				labelConstraints());
		panelInfoHolderContentInfoFields.add(comboBoxInvoiceCustomerName,
				textFieldConstraints());

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
		
		panelInfoHolderContentInfoFields.add(labelInvoiceBankInfoBankName,
				labelConstraints());
		panelInfoHolderContentInfoFields.add(
				comboBoxInvoiceBankInfoBankName, textFieldConstraints());
		
		panelInfoHolderContentInfoFields.add(labelInvoiceBankInfoBankAccount,
				labelConstraints());
		panelInfoHolderContentInfoFields.add(
				textFieldInvoiceBankInfoBankAccount, textFieldConstraints());
		
		panelInfoHolderContentInfoFields.add(
				labelInvoiceBankInfoBankAddress, labelConstraints());
		panelInfoHolderContentInfoFields.add(
				textFieldInvoiceBankInfoBankAddress, textFieldConstraints());
		
		panelInfoHolderContentInfoFields.add(
				labelInvoiceBankInfoBankPhoneNumber, labelConstraints());
		panelInfoHolderContentInfoFields.add(
				textFieldInvoiceBankInfoBankPhoneNumber, textFieldConstraints());
		
		panelInfoHolderContentInfoFields.add(
				labelInvoiceBankInfoBankEmail, labelConstraints());
		panelInfoHolderContentInfoFields.add(
				textFieldInvoiceBankInfoBankEmail, textFieldConstraints());
		
		panelInfoHolderContentInfoFields.add(
				labelInvoiceBankInfoBankAdditionalInfo, labelConstraints());
		panelInfoHolderContentInfoFields.add(
				textFieldInvoiceBankInfoBankAdditionalInfo, textFieldConstraints());

		panelInfoHolderContentInfoFields.add(labelInvoiceNumberBeforeLast,
				labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceNumberBeforeLast,
				textFieldConstraints());

		panelInfoHolderContentInfoFields.add(labelInvoiceNumber,
				labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceNumber,
				textFieldConstraints());
		
		panelInfoHolderContentInfoFields.add(labelInvoiceDate,
				labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceDate,
				textFieldConstraints());		

		panelInfoHolderContentInfoFields.add(labelInvoiceCurrency,
				labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceCurrency,
				textFieldConstraints());

		panelInfoHolderContentInfoFields.add(labelInvoiceSerialNumber,
				labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceSerialNumber,
				textFieldConstraints());

		panelInfoHolderContentInfoFields.add(labelInvoiceDeliveryNumber,
				labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceDeliveryNumber,
				textFieldConstraints());
		
		panelInfoHolderContentInfoFields.add(labelInvoiceDeliveryDate,
				labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceDeliveryDate,
				textFieldConstraints());

		panelInfoHolderContentInfoFields.add(labelTotalQuantityPriceWithoutTax,
				labelConstraints());
		panelInfoHolderContentInfoFields.add(
				textFieldInvoiceTotalQuantityPriceWithoutTax,
				textFieldConstraints());

		panelInfoHolderContentInfoFields.add(labelInvoiceTotalQuantityTax,
				labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceTotalQuantityTax,
				textFieldConstraints());

		panelInfoHolderContentInfoFields.add(labelInvoiceTotalQuantityPrice,
				labelConstraints());
		panelInfoHolderContentInfoFields.add(
				textFieldInvoiceTotalQuantityPrice, textFieldConstraints());

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
		panelInfoHolderContentButtons.add(new JLabel(" Артикл: "));
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
					invoice.getBankInfo().getBankInfoName(),
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

			table.setRowSelectionInterval(selectedRow, selectedRow);

			selectedRowInvoiceId = (String) table.getValueAt(selectedRow, 1);
			BigDecimal bigDecimalSelectedRowInvoiceId = new BigDecimal(
					selectedRowInvoiceId);
			int intSelectedRowInvoiceId = Integer
					.valueOf(bigDecimalSelectedRowInvoiceId.intValue());
			Invoice invoice = getInvoiceFromInvoiceTable(selectedRowInvoiceId);
			populateInvoiceFields(invoice);

			orderItemPanelImpl.setInvoiceId(intSelectedRowInvoiceId);
			orderItemPanelImpl.populateOrderItemTable();

			setInvoiceTableButtonsEnabled();
			setInvoicePrintEnabled();

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
		int i = 0;

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
		int i = 0;

		for (BankInfo bankInfo : banksInfo) {
			String bankInfoName = bankInfo.getBankInfoName();
			BigDecimal bankInfoId = bankInfo.getBankInfoId();
			Integer integerBankInfoId = bankInfoId.intValue();
			mapBanksInfo.put(i++, integerBankInfoId);
			comboBoxInvoiceBankInfoBankName.addItem(bankInfoName);
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
		comboBoxInvoiceBankInfoBankName
				.setSelectedIndex(getSelectedInvoiceBankInfoComboBoxIndex(invoice));
		textFieldInvoiceSupplierAddress.setText(invoice.getSupplier()
				.getSupplierAddress());
		textFieldInvoiceSupplierPhone.setText(invoice.getSupplier()
				.getSupplierPhoneNumber());
		textFieldInvoiceSupplierEmail.setText(invoice.getSupplier()
				.getSupplierEmail());
		textFieldInvoiceSupplierRegistryNumber.setText(invoice.getSupplier()
				.getSupplierRegistryNumber());
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
		textFieldInvoiceBankInfoBankAccount.setText(invoice.getBankInfo()
				.getBankInfoAccount());
		textFieldInvoiceBankInfoBankAddress.setText(invoice.getBankInfo()
				.getBankInfoAddress());
		textFieldInvoiceBankInfoBankPhoneNumber.setText(invoice.getBankInfo()
				.getBankInfoPhoneNumber());
		textFieldInvoiceBankInfoBankEmail.setText(invoice.getBankInfo().getBankInfoEmail());
		textFieldInvoiceBankInfoBankAdditionalInfo.setText(invoice.getBankInfo()
				.getBankInfoAdditionalInfo());
		textFieldInvoiceNumberBeforeLast
				.setText(getInvoiceNumberBeforeLast(invoice.getInvoiceNumber()));
		textFieldInvoiceNumber.setText(invoice.getInvoiceNumber());
		textFieldInvoiceSerialNumber.setText(invoice.getInvoiceSerialNumber());
		textFieldInvoiceDate.setText(invoice.getInvoiceDate());
		textFieldInvoiceDeliveryDate.setText(invoice.getInvoiceDeliveryDate());
		textFieldInvoiceDeliveryNumber.setText(invoice
				.getInvoiceDeliveryNumber());
		textFieldInvoiceTotalQuantityPrice.setText(invoice
				.getInvoiceTotalPrice());
		textFieldInvoiceTotalQuantityPriceWithoutTax.setText(invoice
				.getInvoiceTotalTax());
		textFieldInvoiceTotalQuantityTax.setText(invoice
				.getInvoiceTotalPriceTax());
		textFieldInvoiceCurrency.setText(invoice.getInvoiceCurrency());
		comboBoxInvoicePaymentInfo
				.setSelectedIndex(getSelectedInvoicePaymentInfoComboBoxIndex(invoice));
		textFieldInvoiceAdditionalInfo.setText(invoice
				.getInvoiceAdditionalInfo());
		textFieldInvoiceId.setText(invoice.getInvoiceId().toString());
	}

	public void populateInvoiceComboBoxes() {
		populateInvoiceSupplierComboBox();
		populateInvoiceCustomerComboBox();
		populateInvoiceBankInfoComboBox();
		populateInvoicePaymentInfoComboBox();
	}

	public void clearInvoiceFields() {
		comboBoxInvoiceSupplierName.removeAllItems();
		comboBoxInvoiceCustomerName.removeAllItems();
		comboBoxInvoiceBankInfoBankName.removeAllItems();
		textFieldInvoiceSupplierAddress.setText("");
		textFieldInvoiceSupplierPhone.setText("");
		textFieldInvoiceSupplierEmail.setText("");
		textFieldInvoiceSupplierRegistryNumber.setText("");
		textFieldInvoiceSupplierAdditionalInfo.setText("");
		textFieldInvoiceCustomerAddress.setText("");
		textFieldInvoiceCustomerPhoneNumber.setText("");
		textFieldInvoiceCustomerEmail.setText("");
		textFieldInvoiceCustomerAdditionalInfo.setText("");
		textFieldInvoiceBankInfoBankAccount.setText("");
		textFieldInvoiceBankInfoBankAddress.setText("");
		textFieldInvoiceBankInfoBankPhoneNumber.setText("");
		textFieldInvoiceBankInfoBankEmail.setText("");
		textFieldInvoiceBankInfoBankAdditionalInfo.setText("");
		textFieldInvoiceNumberBeforeLast.setText("");
		textFieldInvoiceNumber.setText("");
		textFieldInvoiceSerialNumber.setText("");
		textFieldInvoiceDate.setText("");
		textFieldInvoiceDeliveryDate.setText("");
		textFieldInvoiceDeliveryNumber.setText("");
		textFieldInvoiceTotalQuantityPrice.setText("");
		textFieldInvoiceTotalQuantityPriceWithoutTax.setText("");
		textFieldInvoiceTotalQuantityTax.setText("");
		textFieldInvoiceCurrency.setText("");
		comboBoxInvoicePaymentInfo.removeAllItems();
		textFieldInvoiceAdditionalInfo.setText("");
		textFieldInvoiceId.setText("");
	}

	public void clearInvoiceComboBoxes() {
		comboBoxInvoiceSupplierName.removeAllItems();
		comboBoxInvoiceCustomerName.removeAllItems();
		comboBoxInvoiceBankInfoBankName.removeAllItems();
		comboBoxInvoicePaymentInfo.removeAllItems();
	}

	public int[] getTableInvoiceHiddenColumns() {
		return new int[] { 1, 2, 6, 8, 9, 10, 11, 12, 13, 14 };
	}

	public String[] getTableInvoiceColumnNames() {
		return new String[] { "Реден Бр.", "Id", "Корисник", "Клиент", "Банка",
				"Број на фактура", "Сериски број на фактура", "Дата",
				"Датум на достава", "Број на достава", "Износ без данок",
				"Износ на ДДВ", "Вкупен износ со ДДВ", "Исплата на фактура",
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
	
	public int getSelectedInvoiceBankInfoComboBoxIndex(Invoice invoice) {
		int selectedIndex;
		Integer key = null;
		BankInfo bankInfo = invoice.getBankInfo();
		BigDecimal bankInfoId = bankInfo.getBankInfoId();
		Integer integerBankInfoId = bankInfoId.intValue();
		for (Map.Entry<Integer, Integer> entry : mapBanksInfo.entrySet()) {
			if (integerBankInfoId != null
					&& integerBankInfoId.equals(entry.getValue())) {
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

	public String getInvoiceNumberBeforeLast(String currentInvoiceNumber) {
		String invoceNumberBeforeLast = "";
		invoices = invoiceServiceImpl.findAllInvoices();

		if (invoices.size() >= 1) {
			invoceNumberBeforeLast = invoices.get(invoices.size() - 1)
					.getInvoiceNumber();
		}

		if (currentInvoiceNumber.equals(invoceNumberBeforeLast)) {
			invoceNumberBeforeLast = "(" + currentInvoiceNumber
					+ ") - Оваа фактура е последно внесена фактура";
		}

		return invoceNumberBeforeLast;
	}

	@Override
	public String[] getInvoiceOrderItemTotalValues() {
		String[] values = new String[3];
		values[0] = textFieldInvoiceTotalQuantityPriceWithoutTax.getText();
		values[1] = textFieldInvoiceTotalQuantityTax.getText();
		values[2] = textFieldInvoiceTotalQuantityPrice.getText();

		return values;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	@Override
	public void setSelectedInvoiceTableRow(String selectedInvoiceTableRow) {
		this.selectedInvoiceTableRow = selectedInvoiceTableRow;
	}

	@Override
	public void setInvoiceTableButtonsEnabled() {
		buttonNew.setEnabled(true);
		if (table.getRowCount() > 0) {
			buttonEdit.setEnabled(true);
			buttonDelete.setEnabled(true);
		}
	}

	public void setInvoiceTableButtonsDisabled() {
		buttonEdit.setEnabled(false);
		buttonDelete.setEnabled(false);
	}

	public void setInvoiceFieldsNonEditable() {
		comboBoxInvoiceSupplierName.setEnabled(false);
		comboBoxInvoiceCustomerName.setEnabled(false);
		comboBoxInvoiceBankInfoBankName.setEnabled(false);
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
		textFieldInvoiceBankInfoBankAccount.setEditable(false);
		textFieldInvoiceBankInfoBankAccount.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceBankInfoBankAddress.setEditable(false);
		textFieldInvoiceBankInfoBankAddress.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceBankInfoBankEmail.setEditable(false);
		textFieldInvoiceBankInfoBankEmail.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceBankInfoBankPhoneNumber.setEditable(false);
		textFieldInvoiceBankInfoBankPhoneNumber.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceBankInfoBankAdditionalInfo.setEditable(false);
		textFieldInvoiceBankInfoBankAdditionalInfo.setBackground(nonEditableTextFieldColor);		
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
		textFieldInvoiceTotalQuantityPrice.setEditable(false);
		textFieldInvoiceTotalQuantityPrice
				.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceTotalQuantityPriceWithoutTax.setEditable(false);
		textFieldInvoiceTotalQuantityPriceWithoutTax
				.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceTotalQuantityTax.setEditable(false);
		textFieldInvoiceTotalQuantityTax
				.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceCurrency.setEditable(false);
		textFieldInvoiceCurrency.setBackground(nonEditableTextFieldColor);
		comboBoxInvoicePaymentInfo.setEnabled(false);
		textFieldInvoiceAdditionalInfo.setEditable(false);
		textFieldInvoiceAdditionalInfo.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceId.setEditable(false);
		textFieldInvoiceId.setBackground(nonEditableTextFieldColor);
	}

	public void setInvoiceFieldsEditable() {
		comboBoxInvoiceCustomerName.setEnabled(true);
		comboBoxInvoiceBankInfoBankName.setEnabled(true);
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
		textFieldInvoiceCurrency.setEditable(true);
		textFieldInvoiceCurrency.setBackground(originalTextFieldColor);
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
	}

	public void setInvoiceInfoButtonsDisabled() {
		buttonSave.setEnabled(false);
		buttonCancel.setEnabled(false);
		buttonAddProduct.setEnabled(false);
		buttonDeleteProduct.setEnabled(false);
		if (table.getRowCount() > 0) {
			buttonPrint.setEnabled(true);
		}
	}

	public void setInvoiceInfoPrintButtonDisabled() {
		buttonPrint.setEnabled(false);
	}

	public void setInvoiceInfoPrintButtonEnabled() {
		buttonPrint.setEnabled(true);
	}

	@Override
	public void setProductButtonsEnabled() {
		buttonAddProduct.setEnabled(true);
		buttonDeleteProduct.setEnabled(true);
	}

	public void setInvoicePrintEnabled() {
		buttonPrint.setEnabled(true);
	}

	@Override
	public void setInvoiceOrderItemTotalValues(
			String invoiceTotalQuantityPriceWithoutTax,
			String invoiceTotalQuantityTax, String invoiceTotalQuantityPrice) {
		textFieldInvoiceTotalQuantityPriceWithoutTax
				.setText(invoiceTotalQuantityPriceWithoutTax);
		textFieldInvoiceTotalQuantityTax.setText(invoiceTotalQuantityTax);
		textFieldInvoiceTotalQuantityPrice.setText(invoiceTotalQuantityPrice);
	}

	@Override
	public void setOrderItemRemoveButtonEnabled() {
		buttonDeleteProduct.setEnabled(true);
	}

	@Override
	public void setOrderItemRemoveButtonDisabled() {
		buttonDeleteProduct.setEnabled(false);
	}

	public void setAllButtonsDisabled() {
		buttonNew.setEnabled(false);
		buttonEdit.setEnabled(false);
		buttonDelete.setEnabled(false);
		buttonAddProduct.setEnabled(false);
		buttonDeleteProduct.setEnabled(false);
		buttonSave.setEnabled(false);
		buttonCancel.setEnabled(false);
		buttonPrint.setEnabled(false);
	}

	public boolean validateInvoiceFields() {
		boolean result = true;
		result = result && (!"".equals(textFieldInvoiceNumber.getText()))
				&& (!"".equals(textFieldInvoiceDate.getText()));
		return result;
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

	public void deleteInvoice() {
		int row = table.getSelectedRow();
		String selectedRowInvoiceId = (String) table.getValueAt(row, 1);
		Invoice invoice = getInvoiceFromInvoiceTable(selectedRowInvoiceId);
		invoiceServiceImpl.deleteInvoice(invoice);
		String selectedRow = Integer.toString(row);
		int intSelectedRow = Integer.parseInt(selectedRow);

		clearInvoiceFields();

		buttonEdit.setEnabled(false);
		buttonDelete.setEnabled(false);

		if (table.getRowCount() - 1 > intSelectedRow) {
			setSelectedInvoiceTableRow(selectedRow);
		}

		populateInvoiceTable();

		if (table.getRowCount() > 0) {
			buttonEdit.setEnabled(true);
			buttonDelete.setEnabled(true);

		}

		setInvoiceFieldsNonEditable();
		setInvoiceInfoButtonsDisabled();
	}

	public void deleteOrderItem() {

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
