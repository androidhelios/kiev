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
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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

import com.chadik.kiev.model.Invoice;
import com.chadik.kiev.service.IInvoiceService;
import com.chadik.kiev.util.PanelUtil;
import com.chadik.kiev.util.TableUtil;
import com.chadik.kiev.view.dialog.IInvoiceDialog;
import com.chadik.kiev.view.dialog.ISupplierDialog;
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
	private JTextField textFieldInvoicePaymentInfo;
	private JTextField textFieldInvoiceAdditionalInfo;

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

	private List<Invoice> invoices;

	@Autowired
	private IInvoiceService invoiceServiceImpl;
	@Autowired
	private IInvoiceDialog invoiceDialogImpl;
	@Autowired
	private IOrderItemPanel orderItemPanelImpl;

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
		panelInfoHolderContentInfoFields.setBackground(new Color(192, 192, 192));
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
		buttonEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFieldsEditable();
			}
		});

		buttonDelete = new JButton("Избриши");
		buttonDelete.setPreferredSize(new Dimension(100, 25));

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
		textFieldInvoiceSupplierName.setMargin(new Insets(2, 2, 2, 2));
		
		originalTextFieldColor = textFieldInvoiceSupplierName.getBackground();
		nonEditableTextFieldColor = new Color(255, 255, 204);

		y = y + height + spacing;
		
		labelInvoiceSupplierAddress = new JLabel("Адреса на корисник:");
		labelInvoiceSupplierAddress.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceSupplierAddress = new JTextField();
		textFieldInvoiceSupplierAddress.setBounds(xTextField, y, weightTextField,
				height);
		textFieldInvoiceSupplierAddress.setMargin(new Insets(2, 2, 2, 2));

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
		labelInvoiceSupplierRegistryNumber.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceSupplierRegistryNumber = new JTextField();
		textFieldInvoiceSupplierRegistryNumber.setBounds(xTextField, y, weightTextField,
				height);
		textFieldInvoiceSupplierRegistryNumber.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;
		
		labelInvoiceSupplierBankName = new JLabel("Банка:");
		labelInvoiceSupplierBankName.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceSupplierBankName = new JTextField();
		textFieldInvoiceSupplierBankName.setBounds(xTextField, y, weightTextField,
				height);
		textFieldInvoiceSupplierBankName.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;
		
		labelInvoiceSupplierBankAccount = new JLabel("Банкарска сметка:");
		labelInvoiceSupplierBankAccount.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceSupplierBankAccount = new JTextField();
		textFieldInvoiceSupplierBankAccount.setBounds(xTextField, y, weightTextField,
				height);
		textFieldInvoiceSupplierBankAccount.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;
		
		labelInvoiceSupplierAdditionalInfo = new JLabel("Забелешки за корисник:");
		labelInvoiceSupplierAdditionalInfo.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceSupplierAdditionalInfo = new JTextField();
		textFieldInvoiceSupplierAdditionalInfo.setBounds(xTextField, y, weightTextField,
				height);
		textFieldInvoiceSupplierAdditionalInfo.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;
		
		labelInvoiceCustomerName = new JLabel("Име на клиент:");
		labelInvoiceCustomerName.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceCustomerName = new JTextField();
		textFieldInvoiceCustomerName.setBounds(xTextField, y, weightTextField,
				height);
		textFieldInvoiceCustomerName.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;
		
		labelInvoiceCustomerAddress = new JLabel("Адреса на клиент:");
		labelInvoiceCustomerAddress.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceCustomerAddress = new JTextField();
		textFieldInvoiceCustomerAddress.setBounds(xTextField, y, weightTextField,
				height);
		textFieldInvoiceCustomerAddress.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;
		
		labelInvoiceCustomerPhoneNumber = new JLabel("Телефонски број на клиент:");
		labelInvoiceCustomerPhoneNumber.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceCustomerPhoneNumber = new JTextField();
		textFieldInvoiceCustomerPhoneNumber.setBounds(xTextField, y, weightTextField,
				height);
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
		labelInvoiceCustomerAdditionalInfo.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceCustomerAdditionalInfo = new JTextField();
		textFieldInvoiceCustomerAdditionalInfo.setBounds(xTextField, y, weightTextField,
				height);
		textFieldInvoiceCustomerAdditionalInfo.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;
		
		labelInvoiceNumber = new JLabel("Број на фактура:");
		labelInvoiceNumber.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceNumber = new JTextField();
		textFieldInvoiceNumber.setBounds(xTextField, y, weightTextField,
				height);
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
		textFieldInvoiceDate.setBounds(xTextField, y, weightTextField,
				height);
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
		textFieldInvoiceDeliveryNumber.setBounds(xTextField, y, weightTextField,
				height);
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
		
		labelInvoicePaymentInfo = new JLabel("Состојба на фактура:");
		labelInvoicePaymentInfo.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoicePaymentInfo = new JTextField();
		textFieldInvoicePaymentInfo.setBounds(xTextField, y, weightTextField,
				height);
		textFieldInvoicePaymentInfo.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;
		
		labelInvoiceAdditionalInfo = new JLabel("Забелешки:");
		labelInvoiceAdditionalInfo.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceAdditionalInfo = new JTextField();
		textFieldInvoiceAdditionalInfo.setBounds(xTextField, y, weightTextField,
				height);
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
		textFieldInvoiceId.setBounds(xTextField, y, weightTextField,
				height);
		textFieldInvoiceId.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;	

		buttonSave = new JButton("Зачувај");
		buttonSave.setPreferredSize(new Dimension(100, 25));

		buttonCancel = new JButton("Откажи");
		buttonCancel.setPreferredSize(new Dimension(100, 25));

		buttonAddProduct = new JButton("Додади");
		buttonAddProduct.setBackground(new Color(224, 224, 224));
		buttonAddProduct.setPreferredSize(new Dimension(100, 25));
		buttonAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				invoiceDialogImpl.initInvoiceDialog();
			}
		});

		buttonDeleteProduct = new JButton("Одземи");
		buttonDeleteProduct.setBackground(new Color(224, 224, 224));
		buttonDeleteProduct.setPreferredSize(new Dimension(100, 25));

		buttonPrint = new JButton("Испечати");
		buttonPrint.setPreferredSize(new Dimension(100, 25));

		panelTableHolderContentTable.add(scrollPaneTable);

		panelTableHolderContentButtons.add(buttonDelete);
		panelTableHolderContentButtons.add(buttonEdit);
		panelTableHolderContentButtons.add(buttonNew);

		panelTableHolderContent.add(panelTableHolderContentTable,
				BorderLayout.CENTER);
		panelTableHolderContent.add(panelTableHolderContentButtons,
				BorderLayout.SOUTH);

		panelTableHolder.add(panelTableHolderContent);

		panelInfoHolderContentInfoFields.add(labelInvoiceSupplierName, firstLabelConstrains());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceSupplierName, textFieldConstraints());
		
		panelInfoHolderContentInfoFields.add(labelInvoiceSupplierAddress, labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceSupplierAddress, textFieldConstraints());
		
		panelInfoHolderContentInfoFields.add(labelInvoiceSupplierPhone, labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceSupplierPhone, textFieldConstraints());
		
		panelInfoHolderContentInfoFields.add(labelInvoiceSupplierEmail, labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceSupplierEmail, textFieldConstraints());
		
		panelInfoHolderContentInfoFields.add(labelInvoiceSupplierRegistryNumber, labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceSupplierRegistryNumber, textFieldConstraints());
		
		panelInfoHolderContentInfoFields.add(labelInvoiceSupplierBankName, labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceSupplierBankName, textFieldConstraints());
		
		panelInfoHolderContentInfoFields.add(labelInvoiceSupplierBankAccount, labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceSupplierBankAccount, textFieldConstraints());
		
		panelInfoHolderContentInfoFields.add(labelInvoiceSupplierAdditionalInfo, labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceSupplierAdditionalInfo, textFieldConstraints());
		
		panelInfoHolderContentInfoFields.add(labelInvoiceCustomerName, labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceCustomerName, textFieldConstraints());
		
		panelInfoHolderContentInfoFields.add(labelInvoiceCustomerAddress, labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceCustomerAddress, textFieldConstraints());
		
		panelInfoHolderContentInfoFields.add(labelInvoiceCustomerPhoneNumber, labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceCustomerPhoneNumber, textFieldConstraints());
		
		panelInfoHolderContentInfoFields.add(labelInvoiceCustomerEmail, labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceCustomerEmail, textFieldConstraints());
		
		panelInfoHolderContentInfoFields.add(labelInvoiceCustomerAdditionalInfo, labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceCustomerAdditionalInfo, textFieldConstraints());
		
		panelInfoHolderContentInfoFields.add(labelInvoiceNumber, labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceNumber, textFieldConstraints());
		
		panelInfoHolderContentInfoFields.add(labelInvoiceSerialNumber, labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceSerialNumber, textFieldConstraints());
		
		panelInfoHolderContentInfoFields.add(labelInvoiceDate, labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceDate, textFieldConstraints());
		
		panelInfoHolderContentInfoFields.add(labelInvoiceDeliveryDate, labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceDeliveryDate, textFieldConstraints());
		
		panelInfoHolderContentInfoFields.add(labelInvoiceDeliveryNumber, labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceDeliveryNumber, textFieldConstraints());
		
		panelInfoHolderContentInfoFields.add(labelInvoiceTotalPrice, labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceTotalPrice, textFieldConstraints());
		
		panelInfoHolderContentInfoFields.add(labelInvoiceTotalTax, labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceTotalTax, textFieldConstraints());
		
		panelInfoHolderContentInfoFields.add(labelInvoiceTotalPriceTax, labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceTotalPriceTax, textFieldConstraints());
		
		panelInfoHolderContentInfoFields.add(labelInvoicePaymentInfo, labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoicePaymentInfo, textFieldConstraints());

		panelInfoHolderContentInfoFields.add(labelInvoiceAdditionalInfo, labelConstraints());
		panelInfoHolderContentInfoFields.add(textFieldInvoiceAdditionalInfo, lastComponentConstrains());

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
					Integer.toString(invoice.getInvoicePaymentInfo()),
					invoice.getInvoiceAdditionalInfo() });
		}

		if (table.getRowCount() > 0) {
			table.setRowSelectionInterval(table.getRowCount() - 1,
					table.getRowCount() - 1);

			selectedRowInvoiceId = (String) table.getValueAt(
					table.getRowCount() - 1, 1);
			Invoice invoice = getInvoiceFromInvoiceTable(selectedRowInvoiceId);
			populateInvoiceFields(invoice);
		}

		scrollPaneTable.validate();
		verticalScrollBar.setValue(verticalScrollBar.getMaximum());

		setFieldsNonEditable();

	}

	public int[] getTableInvoiceHiddenColumns() {
		return new int[] { 1, 3, 4, 5, 6, 8, 9 };
	}

	public String[] getTableInvoiceColumnNames() {
		return new String[] { "Реден Бр.", "Id", "Назив", "Регистарски Број",
				"Банка", "Банкарска Сметка", "Адреса", "Телефонски број",
				"Email", "Забелешки" };
	}

	public Invoice getInvoiceFromInvoiceTable(String selectedRowInvoiceId) {
		BigDecimal invoiceId = new BigDecimal(selectedRowInvoiceId);
		return invoiceServiceImpl.findInvoiceById(invoiceId);
	}

	public void populateInvoiceFields(Invoice invoice) {
		textFieldInvoiceId.setText(invoice.getInvoiceId().toString());
		textFieldInvoiceSupplierName.setText(invoice.getSupplier().getSupplierName());
		textFieldInvoiceSupplierAddress.setText(invoice.getSupplier().getSupplierAddress());
		textFieldInvoiceSupplierPhone.setText(invoice.getSupplier().getSupplierPhoneNumber());
		textFieldInvoiceSupplierEmail.setText(invoice.getSupplier().getSupplierEmail());
		textFieldInvoiceSupplierRegistryNumber.setText(invoice.getSupplier().getSupplierRegistryNumber());
		textFieldInvoiceSupplierBankName.setText(invoice.getSupplier().getSupplierBankName());
		textFieldInvoiceSupplierBankAccount.setText(invoice.getSupplier().getSupplierBankAccount());
		textFieldInvoiceSupplierAdditionalInfo.setText(invoice.getSupplier().getSupplierAdditionalInfo());
		textFieldInvoiceCustomerName.setText(invoice.getCustomer().getCustomerName());
		textFieldInvoiceCustomerAddress.setText(invoice.getCustomer().getCustomerAddress());
		textFieldInvoiceCustomerPhoneNumber.setText(invoice.getCustomer().getCustomerPhoneNumber());
		textFieldInvoiceCustomerEmail.setText(invoice.getCustomer().getCustomerEmail());
		textFieldInvoiceCustomerAdditionalInfo.setText(invoice.getCustomer().getCustomerAdditionalInfo());
		textFieldInvoiceNumber.setText(invoice.getInvoiceNumber());
		textFieldInvoiceSerialNumber.setText(invoice.getInvoiceSerialNumber());
		textFieldInvoiceDate.setText(invoice.getInvoiceDate());
		textFieldInvoiceDeliveryDate.setText(invoice.getInvoiceDeliveryDate());
		textFieldInvoiceDeliveryNumber.setText(invoice.getInvoiceDeliveryNumber());
		textFieldInvoiceTotalPrice.setText(invoice.getInvoiceTotalPrice());
		textFieldInvoiceTotalTax.setText(invoice.getInvoiceTotalTax());
		textFieldInvoiceTotalPriceTax.setText(invoice.getInvoiceTotalPriceTax());
		textFieldInvoicePaymentInfo.setText(Integer.toString(invoice.getInvoicePaymentInfo()));
		textFieldInvoiceAdditionalInfo.setText(invoice.getInvoiceAdditionalInfo());
	}

	public void setFieldsNonEditable() {
		textFieldInvoiceId.setEditable(false);
		textFieldInvoiceId.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceSupplierName.setEditable(false);
		textFieldInvoiceSupplierName.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceSupplierAddress.setEditable(false);
		textFieldInvoiceSupplierAddress.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceSupplierPhone.setEditable(false);
		textFieldInvoiceSupplierPhone.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceSupplierEmail.setEditable(false);
		textFieldInvoiceSupplierEmail.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceSupplierRegistryNumber.setEditable(false);
		textFieldInvoiceSupplierRegistryNumber.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceSupplierBankName.setEditable(false);
		textFieldInvoiceSupplierBankName.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceSupplierBankAccount.setEditable(false);
		textFieldInvoiceSupplierBankAccount.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceSupplierAdditionalInfo.setEditable(false);
		textFieldInvoiceSupplierAdditionalInfo.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceCustomerName.setEditable(false);
		textFieldInvoiceCustomerName.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceCustomerAddress.setEditable(false);
		textFieldInvoiceCustomerAddress.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceCustomerPhoneNumber.setEditable(false);
		textFieldInvoiceCustomerPhoneNumber.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceCustomerEmail.setEditable(false);
		textFieldInvoiceCustomerEmail.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceCustomerAdditionalInfo.setEditable(false);
		textFieldInvoiceCustomerAdditionalInfo.setBackground(nonEditableTextFieldColor);
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
		textFieldInvoicePaymentInfo.setEditable(false);
		textFieldInvoicePaymentInfo.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceAdditionalInfo.setEditable(false);
		textFieldInvoiceAdditionalInfo.setBackground(nonEditableTextFieldColor);
	}

	public void setFieldsEditable() {
		textFieldInvoiceId.setEditable(true);
		textFieldInvoiceId.setBackground(originalTextFieldColor);
		textFieldInvoiceSupplierName.setEditable(true);
		textFieldInvoiceSupplierName.setBackground(originalTextFieldColor);
		textFieldInvoiceSupplierAddress.setEditable(true);
		textFieldInvoiceSupplierAddress.setBackground(originalTextFieldColor);
		textFieldInvoiceSupplierPhone.setEditable(true);
		textFieldInvoiceSupplierPhone.setBackground(originalTextFieldColor);
		textFieldInvoiceSupplierEmail.setEditable(true);
		textFieldInvoiceSupplierEmail.setBackground(originalTextFieldColor);
		textFieldInvoiceSupplierRegistryNumber.setEditable(true);
		textFieldInvoiceSupplierRegistryNumber.setBackground(originalTextFieldColor);
		textFieldInvoiceSupplierBankName.setEditable(true);
		textFieldInvoiceSupplierBankName.setBackground(originalTextFieldColor);
		textFieldInvoiceSupplierBankAccount.setEditable(true);
		textFieldInvoiceSupplierBankAccount.setBackground(originalTextFieldColor);
		textFieldInvoiceSupplierAdditionalInfo.setEditable(true);
		textFieldInvoiceSupplierAdditionalInfo.setBackground(originalTextFieldColor);
		textFieldInvoiceCustomerName.setEditable(true);
		textFieldInvoiceCustomerName.setBackground(originalTextFieldColor);
		textFieldInvoiceCustomerAddress.setEditable(true);
		textFieldInvoiceCustomerAddress.setBackground(originalTextFieldColor);
		textFieldInvoiceCustomerPhoneNumber.setEditable(true);
		textFieldInvoiceCustomerPhoneNumber.setBackground(originalTextFieldColor);
		textFieldInvoiceCustomerEmail.setEditable(true);
		textFieldInvoiceCustomerEmail.setBackground(originalTextFieldColor);
		textFieldInvoiceCustomerAdditionalInfo.setEditable(true);
		textFieldInvoiceCustomerAdditionalInfo.setBackground(originalTextFieldColor);
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
		textFieldInvoiceTotalPrice.setEditable(true);
		textFieldInvoiceTotalPrice.setBackground(originalTextFieldColor);
		textFieldInvoiceTotalTax.setEditable(true);
		textFieldInvoiceTotalTax.setBackground(originalTextFieldColor);
		textFieldInvoiceTotalPriceTax.setEditable(true);
		textFieldInvoiceTotalPriceTax.setBackground(originalTextFieldColor);
		textFieldInvoicePaymentInfo.setEditable(true);
		textFieldInvoicePaymentInfo.setBackground(originalTextFieldColor);
		textFieldInvoiceAdditionalInfo.setEditable(true);
		textFieldInvoiceAdditionalInfo.setBackground(originalTextFieldColor);
	}
	
	public GridBagConstraints newConstraints() {
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(4, 10, 4, 10);
		return c;
	}

	public GridBagConstraints labelConstraints() {
		GridBagConstraints c = newConstraints();
		c.anchor = GridBagConstraints.BASELINE_LEADING;
		c.weightx = 0.0;
		return c;
	}

	public GridBagConstraints textFieldConstraints() {
		GridBagConstraints c = newConstraints();
		c.anchor = GridBagConstraints.BASELINE;
		c.weightx = 1.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = GridBagConstraints.REMAINDER;
		return c;
	}

	public GridBagConstraints lastComponentConstrains() {
		GridBagConstraints c = newConstraints();
		c.anchor = GridBagConstraints.BASELINE;
		c.weightx = 1.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weighty = 1.0;
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

	public void setTableButtonsEnabled() {
		buttonEdit.setEnabled(true);
		buttonDelete.setEnabled(true);
	}

	public void setTableButtonsDisabled() {
		buttonEdit.setEnabled(false);
		buttonDelete.setEnabled(false);
	}

	public void setEditButtonsEnabled() {
		buttonSave.setEnabled(true);
		buttonCancel.setEnabled(true);
	}

	public void setEditButtonsDisabled() {
		buttonSave.setEnabled(false);
		buttonCancel.setEnabled(false);
	}

}
