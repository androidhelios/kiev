package com.chadik.kiev.view.panel.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
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
	private JLabel labelInvoiceSupplierName;
	private JLabel labelInvoiceCustomerName;
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
	private JTextField textFieldInvoiceSupplierName;
	private JTextField textFieldInvoiceCustomerName;
	private JTextField textFieldInvoiceNumber;
	private JTextField textFieldInvoiceSerialNumber;
	private JTextField textFieldInvoiceDate;
	private JTextField textFieldInvoiceDeliveryDate;
	private JTextField textFieldInvoiceDeliveryNumber;
	private JTextField textFieldInvoiceTotalPrice;
	private JTextField textFieldInvoiceTotalTax;
	private JTextField textFieldInvoiceTotalPriceTax;
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

		panelTableHolderContentButtons = new JPanel();
		panelTableHolderContentButtons.setLayout(new FlowLayout());
		panelTableHolderContentButtons.setPreferredSize(new Dimension(400, 50));

		panelInfoHolder = new JPanel();
		panelInfoHolder.setLayout(new BorderLayout());

		panelInfoHolderContent = new JPanel();
		panelInfoHolderContent.setLayout(new BorderLayout());

		panelInfoHolderContentInfo = new JPanel();
		panelInfoHolderContentInfo.setLayout(new BorderLayout());

		panelInfoHolderContentInfoFields = new JPanel();
		panelInfoHolderContentInfoFields.setLayout(null);
		panelInfoHolderContentInfoFields.setPreferredSize(new Dimension(400,
				400));

		scrollPanePanelInfoHolderContentInfo = new JScrollPane(
				panelInfoHolderContentInfoFields);

		panelInfoHolderContentInfoTable = orderItemPanelImpl
				.initOrderItemPanel();

		panelInfoHolderContentButtons = new JPanel();
		panelInfoHolderContentButtons.setLayout(new FlowLayout());
		panelInfoHolderContentButtons.setPreferredSize(new Dimension(400, 50));

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

		labelInvoiceSupplierName = new JLabel("Име:");
		labelInvoiceSupplierName.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceSupplierName = new JTextField();
		textFieldInvoiceSupplierName.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;

		labelInvoiceCustomerName = new JLabel("Име:");
		labelInvoiceCustomerName.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceCustomerName = new JTextField();
		textFieldInvoiceCustomerName.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;

		labelInvoiceNumber = new JLabel("Име:");
		labelInvoiceNumber.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceNumber = new JTextField();
		textFieldInvoiceNumber
				.setBounds(xTextField, y, weightTextField, height);

		y = y + height + spacing;

		labelInvoiceSerialNumber = new JLabel("Име:");
		labelInvoiceSerialNumber.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceSerialNumber = new JTextField();
		textFieldInvoiceSerialNumber.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;

		labelInvoiceDate = new JLabel("Име:");
		labelInvoiceDate.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceDate = new JTextField();
		textFieldInvoiceDate.setBounds(xTextField, y, weightTextField, height);

		y = y + height + spacing;

		labelInvoiceDeliveryDate = new JLabel("Име:");
		labelInvoiceDeliveryDate.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceDeliveryDate = new JTextField();
		textFieldInvoiceDeliveryDate.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;

		labelInvoiceDeliveryNumber = new JLabel("Име:");
		labelInvoiceDeliveryNumber.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceDeliveryNumber = new JTextField();
		textFieldInvoiceDeliveryNumber.setBounds(xTextField, y,
				weightTextField, height);

		y = y + height + spacing;

		labelInvoiceTotalPrice = new JLabel("Име:");
		labelInvoiceTotalPrice.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceTotalPrice = new JTextField();
		textFieldInvoiceTotalPrice.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;

		labelInvoiceTotalTax = new JLabel("Име:");
		labelInvoiceTotalTax.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceTotalTax = new JTextField();
		textFieldInvoiceTotalTax.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;

		labelInvoiceTotalPriceTax = new JLabel("Име:");
		labelInvoiceTotalPriceTax.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceTotalPriceTax = new JTextField();
		textFieldInvoiceTotalPriceTax.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;

		labelInvoiceAdditionalInfo = new JLabel("Име:");
		labelInvoiceAdditionalInfo.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceAdditionalInfo = new JTextField();
		textFieldInvoiceAdditionalInfo.setBounds(xTextField, y,
				weightTextField, height);

		y = y + height + spacing;

		labelInvoiceId = new JLabel("Име:");
		labelInvoiceId.setBounds(xLabel, y, weightLabel, height);

		textFieldInvoiceId = new JTextField();
		textFieldInvoiceId.setBounds(xTextField, y, weightTextField, height);

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

		panelInfoHolderContentInfoFields.add(labelInvoiceSupplierName);
		panelInfoHolderContentInfoFields.add(textFieldInvoiceSupplierName);

		panelInfoHolderContentInfoFields.add(labelInvoiceCustomerName);
		panelInfoHolderContentInfoFields.add(textFieldInvoiceCustomerName);

		panelInfoHolderContentInfoFields.add(labelInvoiceNumber);
		panelInfoHolderContentInfoFields.add(textFieldInvoiceNumber);

		panelInfoHolderContentInfoFields.add(labelInvoiceSerialNumber);
		panelInfoHolderContentInfoFields.add(textFieldInvoiceSerialNumber);

		panelInfoHolderContentInfoFields.add(labelInvoiceDate);
		panelInfoHolderContentInfoFields.add(textFieldInvoiceDate);

		panelInfoHolderContentInfoFields.add(labelInvoiceDeliveryDate);
		panelInfoHolderContentInfoFields.add(textFieldInvoiceDeliveryDate);

		panelInfoHolderContentInfoFields.add(labelInvoiceDeliveryNumber);
		panelInfoHolderContentInfoFields.add(textFieldInvoiceDeliveryNumber);

		panelInfoHolderContentInfoFields.add(labelInvoiceTotalPrice);
		panelInfoHolderContentInfoFields.add(textFieldInvoiceTotalPrice);

		panelInfoHolderContentInfoFields.add(labelInvoiceTotalTax);
		panelInfoHolderContentInfoFields.add(textFieldInvoiceTotalTax);

		panelInfoHolderContentInfoFields.add(labelInvoiceTotalPriceTax);
		panelInfoHolderContentInfoFields.add(textFieldInvoiceTotalPriceTax);

		panelInfoHolderContentInfoFields.add(labelInvoiceAdditionalInfo);
		panelInfoHolderContentInfoFields.add(textFieldInvoiceAdditionalInfo);

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
		textFieldInvoiceSupplierName.setText(invoice.getSupplier()
				.getSupplierName());
		textFieldInvoiceCustomerName.setText(invoice.getCustomer()
				.getCustomerName());
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
		textFieldInvoiceAdditionalInfo.setText(invoice
				.getInvoiceAdditionalInfo());
	}

	public void setFieldsNonEditable() {
		nonEditableTextFieldColor = new Color(255, 255, 204);

		textFieldInvoiceId.setEditable(false);
		textFieldInvoiceId.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceSupplierName.setEditable(false);
		textFieldInvoiceSupplierName.setBackground(nonEditableTextFieldColor);
		textFieldInvoiceCustomerName.setEditable(false);
		textFieldInvoiceCustomerName.setBackground(nonEditableTextFieldColor);
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
		textFieldInvoiceAdditionalInfo.setEditable(false);
		textFieldInvoiceAdditionalInfo.setBackground(nonEditableTextFieldColor);
	}

	public void setFieldsEditable() {
		textFieldInvoiceId.setEditable(true);
		textFieldInvoiceId.setBackground(originalTextFieldColor);
		textFieldInvoiceSupplierName.setEditable(true);
		textFieldInvoiceSupplierName.setBackground(originalTextFieldColor);
		textFieldInvoiceCustomerName.setEditable(true);
		textFieldInvoiceCustomerName.setBackground(originalTextFieldColor);
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
		textFieldInvoiceTotalTax.setEditable(false);
		textFieldInvoiceTotalTax.setBackground(originalTextFieldColor);
		textFieldInvoiceTotalPriceTax.setEditable(false);
		textFieldInvoiceTotalPriceTax.setBackground(originalTextFieldColor);
		textFieldInvoiceAdditionalInfo.setEditable(false);
		textFieldInvoiceAdditionalInfo.setBackground(originalTextFieldColor);
	}

}
