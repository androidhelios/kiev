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

import com.chadik.kiev.model.Supplier;
import com.chadik.kiev.service.ISupplierService;
import com.chadik.kiev.util.PanelUtil;
import com.chadik.kiev.util.TableUtil;
import com.chadik.kiev.view.dialog.ISupplierDialog;
import com.chadik.kiev.view.panel.ISupplierPanel;

@Component
public class SupplierPanelImpl implements ISupplierPanel {

	private JPanel panelAll;

	private JPanel panelTableHolder;
	private JPanel panelTableHolderContent;
	private JPanel panelTableHolderContentTable;
	private JPanel panelTableHolderContentButtons;

	private JPanel panelInfoHolder;
	private JPanel panelInfoHolderContent;
	private JPanel panelInfoHolderContentInfo;
	private JPanel panelInfoHolderContentButtons;

	private JScrollPane scrollPaneTable;
	private JScrollBar verticalScrollBar;

	private DefaultTableModel defaultTableModel;
	private JTable table;

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

	private JButton buttonNew;
	private JButton buttonEdit;
	private JButton buttonDelete;
	private JButton buttonSave;
	private JButton buttonCancel;

	private Color originalTextFieldColor;
	private Color nonEditableTextFieldColor;

	private List<Supplier> suppliers;

	@Autowired
	private ISupplierService supplierServiceImpl;
	@Autowired
	private ISupplierDialog supplierDialogImpl;

	@Override
	public JPanel initSupplierPanel() {
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
				"Листа на корисници"));

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
		panelInfoHolderContentInfo.setLayout(new GridBagLayout());
		panelInfoHolderContentInfo.setPreferredSize(new Dimension(400, 550));
		panelInfoHolderContentInfo.setBackground(new Color(192, 192, 192));
		panelInfoHolderContentInfo.setBorder(BorderFactory
				.createCompoundBorder(new EmptyBorder(10, 10, 10, 10),
						new EtchedBorder()));

		panelInfoHolderContentButtons = new JPanel();
		panelInfoHolderContentButtons.setLayout(new FlowLayout());
		panelInfoHolderContentButtons.setPreferredSize(new Dimension(400, 50));
		panelInfoHolderContentButtons.setBorder(new EmptyBorder(5, 5, 5, 5));

		defaultTableModel = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		defaultTableModel.setColumnIdentifiers(getTableSupplierColumnNames());

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				String selectedRowSupplierId = (String) table
						.getValueAt(row, 1);
				Supplier supplier = getSupplierFromSupplierTable(selectedRowSupplierId);
				populateSupplierFields(supplier);
				setEditButtonsDisabled();
			}
		});

		table.setModel(defaultTableModel);

		TableUtil.hideColumns(table, getTableSupplierHiddenColumns());

		TableUtil.allignCells(table, SwingConstants.CENTER);

		table.getColumnModel().getColumn(0).setMaxWidth(100);

		scrollPaneTable = new JScrollPane(table);

		verticalScrollBar = scrollPaneTable.getVerticalScrollBar();

		buttonNew = new JButton("Креирај");
		buttonNew.setPreferredSize(new Dimension(100, 25));
		buttonNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				supplierDialogImpl.initSupplierDialog();
				setEditButtonsDisabled();
			}
		});

		buttonEdit = new JButton("Измени");
		buttonEdit.setPreferredSize(new Dimension(100, 25));
		buttonEdit.setEnabled(false);
		buttonEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFieldsEditable();
				setEditButtonsEnabled();
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

		labelSupplierName = new JLabel("Име:");
		labelSupplierName.setBounds(xLabel, y, weightLabel, height);

		textFieldSupplierName = new JTextField();
		textFieldSupplierName.setBounds(xTextField, y, weightTextField, height);
		textFieldSupplierName.setMargin(new Insets(2, 2, 2, 2));

		originalTextFieldColor = textFieldSupplierName.getBackground();
		nonEditableTextFieldColor = new Color(255, 255, 204);

		y = y + height + spacing;

		labelSupplierRegistryNumber = new JLabel("Регистерски Број:");
		labelSupplierRegistryNumber.setBounds(xLabel, y, weightLabel, height);

		textFieldSupplierRegistryNumber = new JTextField();
		textFieldSupplierRegistryNumber.setBounds(xTextField, y,
				weightTextField, height);
		textFieldSupplierRegistryNumber.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelSupplierBankName = new JLabel("Банка:");
		labelSupplierBankName.setBounds(xLabel, y, weightLabel, height);

		textFieldSupplierBankName = new JTextField();
		textFieldSupplierBankName.setBounds(xTextField, y, weightTextField,
				height);
		textFieldSupplierBankName.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelSupplierBankAccount = new JLabel("Банкарска Сметка:");
		labelSupplierBankAccount.setBounds(xLabel, y, weightLabel, height);

		textFieldSupplierBankAccount = new JTextField();
		textFieldSupplierBankAccount.setBounds(xTextField, y, weightTextField,
				height);
		textFieldSupplierBankAccount.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelSupplierAddress = new JLabel("Адреса:");
		labelSupplierAddress.setBounds(xLabel, y, weightLabel, height);

		textFieldSupplierAddress = new JTextField();
		textFieldSupplierAddress.setBounds(xTextField, y, weightTextField,
				height);
		textFieldSupplierAddress.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelSupplierPhoneNumber = new JLabel("Телефонски Број:");
		labelSupplierPhoneNumber.setBounds(xLabel, y, weightLabel, height);

		textFieldSupplierPhoneNumber = new JTextField();
		textFieldSupplierPhoneNumber.setBounds(xTextField, y, weightTextField,
				height);
		textFieldSupplierPhoneNumber.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelSupplierEmail = new JLabel("Email:");
		labelSupplierEmail.setBounds(xLabel, y, weightLabel, height);

		textFieldSupplierEmail = new JTextField();
		textFieldSupplierEmail
				.setBounds(xTextField, y, weightTextField, height);
		textFieldSupplierEmail.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelSupplierAdditionalInfo = new JLabel("Забелешки:");
		labelSupplierAdditionalInfo.setBounds(xLabel, y, weightLabel, height);

		textFieldSupplierAdditionalInfo = new JTextField();
		textFieldSupplierAdditionalInfo.setBounds(xTextField, y,
				weightTextField, height);
		textFieldSupplierAdditionalInfo.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelSupplierId = new JLabel("ID:");
		labelSupplierId.setBounds(xLabel, y, weightLabel, height);

		textFieldSupplierId = new JTextField();
		textFieldSupplierId.setBounds(xTextField, y, weightTextField, height);
		textFieldSupplierId.setMargin(new Insets(2, 2, 2, 2));

		buttonSave = new JButton("Зачувај");
		buttonSave.setPreferredSize(new Dimension(100, 25));
		buttonSave.setEnabled(false);
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveSupplier();
			}
		});

		buttonCancel = new JButton("Откажи");
		buttonCancel.setPreferredSize(new Dimension(100, 25));
		buttonCancel.setEnabled(false);
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String selectedRowSupplierId = (String) table
						.getValueAt(row, 1);
				Supplier supplier = getSupplierFromSupplierTable(selectedRowSupplierId);
				populateSupplierFields(supplier);
				setFieldsNonEditable();
				setEditButtonsDisabled();
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

		panelInfoHolderContentInfo.add(labelSupplierName,
				firstLabelConstrains());
		panelInfoHolderContentInfo.add(textFieldSupplierName,
				textFieldConstraints());

		panelInfoHolderContentInfo.add(labelSupplierRegistryNumber,
				labelConstraints());
		panelInfoHolderContentInfo.add(textFieldSupplierRegistryNumber,
				textFieldConstraints());

		panelInfoHolderContentInfo.add(labelSupplierBankName,
				labelConstraints());
		panelInfoHolderContentInfo.add(textFieldSupplierBankName,
				textFieldConstraints());

		panelInfoHolderContentInfo.add(labelSupplierBankAccount,
				labelConstraints());
		panelInfoHolderContentInfo.add(textFieldSupplierBankAccount,
				textFieldConstraints());

		panelInfoHolderContentInfo
				.add(labelSupplierAddress, labelConstraints());
		panelInfoHolderContentInfo.add(textFieldSupplierAddress,
				textFieldConstraints());

		panelInfoHolderContentInfo.add(labelSupplierPhoneNumber,
				labelConstraints());
		panelInfoHolderContentInfo.add(textFieldSupplierPhoneNumber,
				textFieldConstraints());

		panelInfoHolderContentInfo.add(labelSupplierEmail, labelConstraints());
		panelInfoHolderContentInfo.add(textFieldSupplierEmail,
				textFieldConstraints());

		panelInfoHolderContentInfo.add(labelSupplierAdditionalInfo,
				labelConstraints());
		panelInfoHolderContentInfo.add(textFieldSupplierAdditionalInfo,
				lastComponentConstrains());

		panelInfoHolderContentButtons.add(buttonSave);
		panelInfoHolderContentButtons.add(buttonCancel);

		panelInfoHolderContent.add(panelInfoHolderContentInfo,
				BorderLayout.CENTER);
		panelInfoHolderContent.add(panelInfoHolderContentButtons,
				BorderLayout.SOUTH);

		panelInfoHolder.add(panelInfoHolderContent, BorderLayout.CENTER);

		panelAll.add(panelTableHolder, BorderLayout.WEST);
		panelAll.add(panelInfoHolder, BorderLayout.CENTER);

		populateSupplierTable();

		return panelAll;
	}

	public void populateSupplierTable() {
		String selectedRowSupplierId = "";
		suppliers = supplierServiceImpl.findAllSuppliers();

		int i = 0;

		defaultTableModel.setRowCount(0);

		for (Supplier supplier : suppliers) {
			defaultTableModel.addRow(new String[] { Integer.toString(++i),
					supplier.getSupplierId().toString(),
					supplier.getSupplierName(),
					supplier.getSupplierRegistryNumber(),
					supplier.getSupplierBankName(),
					supplier.getSupplierBankAccount(),
					supplier.getSupplierAddress(),
					supplier.getSupplierPhoneNumber(),
					supplier.getSupplierEmail(),
					supplier.getSupplierAdditionalInfo() });
		}

		if (table.getRowCount() > 0) {
			table.setRowSelectionInterval(table.getRowCount() - 1,
					table.getRowCount() - 1);

			selectedRowSupplierId = (String) table.getValueAt(
					table.getRowCount() - 1, 1);
			Supplier supplier = getSupplierFromSupplierTable(selectedRowSupplierId);
			populateSupplierFields(supplier);

			setTableButtonsEnabled();

		}

		scrollPaneTable.validate();
		verticalScrollBar.setValue(verticalScrollBar.getMaximum());

		setFieldsNonEditable();

	}

	public int[] getTableSupplierHiddenColumns() {
		return new int[] { 1, 3, 4, 5, 6, 8, 9 };
	}

	public String[] getTableSupplierColumnNames() {
		return new String[] { "Реден Бр.", "Id", "Назив", "Регистарски Број",
				"Банка", "Банкарска Сметка", "Адреса", "Телефонски број",
				"Email", "Забелешки" };
	}

	public Supplier getSupplierFromSupplierTable(String selectedRowSupplierId) {
		BigDecimal supplierId = new BigDecimal(selectedRowSupplierId);
		return supplierServiceImpl.findSupplierById(supplierId);
	}

	public Supplier getSupplierFromSupplierFields() {
		int row = table.getSelectedRow();
		String selectedRowSupplierId = (String) table.getValueAt(row, 1);
		Supplier supplier = getSupplierFromSupplierTable(selectedRowSupplierId);
		supplier.setSupplierName(textFieldSupplierName.getText());
		supplier.setSupplierRegistryNumber(textFieldSupplierRegistryNumber
				.getText());
		supplier.setSupplierBankName(textFieldSupplierBankName.getText());
		supplier.setSupplierBankAccount(textFieldSupplierBankAccount.getText());
		supplier.setSupplierAddress(textFieldSupplierAddress.getText());
		supplier.setSupplierPhoneNumber(textFieldSupplierPhoneNumber.getText());
		supplier.setSupplierEmail(textFieldSupplierEmail.getText());
		supplier.setSupplierAdditionalInfo(textFieldSupplierAdditionalInfo
				.getText());

		return supplier;
	}

	public void populateSupplierFields(Supplier supplier) {
		textFieldSupplierId.setText(supplier.getSupplierId().toString());
		textFieldSupplierName.setText(supplier.getSupplierName());
		textFieldSupplierRegistryNumber.setText(supplier
				.getSupplierRegistryNumber());
		textFieldSupplierBankName.setText(supplier.getSupplierBankName());
		textFieldSupplierBankAccount.setText(supplier.getSupplierBankAccount());
		textFieldSupplierAddress.setText(supplier.getSupplierAddress());
		textFieldSupplierPhoneNumber.setText(supplier.getSupplierPhoneNumber());
		textFieldSupplierEmail.setText(supplier.getSupplierEmail());
		textFieldSupplierAdditionalInfo.setText(supplier
				.getSupplierAdditionalInfo());
	}

	public void setFieldsNonEditable() {
		textFieldSupplierName.setEditable(false);
		textFieldSupplierName.setBackground(nonEditableTextFieldColor);
		textFieldSupplierRegistryNumber.setEditable(false);
		textFieldSupplierRegistryNumber
				.setBackground(nonEditableTextFieldColor);
		textFieldSupplierBankName.setEditable(false);
		textFieldSupplierBankName.setBackground(nonEditableTextFieldColor);
		textFieldSupplierBankAccount.setEditable(false);
		textFieldSupplierBankAccount.setBackground(nonEditableTextFieldColor);
		textFieldSupplierAddress.setEditable(false);
		textFieldSupplierAddress.setBackground(nonEditableTextFieldColor);
		textFieldSupplierPhoneNumber.setEditable(false);
		textFieldSupplierPhoneNumber.setBackground(nonEditableTextFieldColor);
		textFieldSupplierEmail.setEditable(false);
		textFieldSupplierEmail.setBackground(nonEditableTextFieldColor);
		textFieldSupplierAdditionalInfo.setEditable(false);
		textFieldSupplierAdditionalInfo
				.setBackground(nonEditableTextFieldColor);
		textFieldSupplierId.setEditable(false);
		textFieldSupplierId.setBackground(nonEditableTextFieldColor);
	}

	public void setFieldsEditable() {
		textFieldSupplierName.setEditable(true);
		textFieldSupplierName.setBackground(originalTextFieldColor);
		textFieldSupplierRegistryNumber.setEditable(true);
		textFieldSupplierRegistryNumber.setBackground(originalTextFieldColor);
		textFieldSupplierBankName.setEditable(true);
		textFieldSupplierBankName.setBackground(originalTextFieldColor);
		textFieldSupplierBankAccount.setEditable(true);
		textFieldSupplierBankAccount.setBackground(originalTextFieldColor);
		textFieldSupplierAddress.setEditable(true);
		textFieldSupplierAddress.setBackground(originalTextFieldColor);
		textFieldSupplierPhoneNumber.setEditable(true);
		textFieldSupplierPhoneNumber.setBackground(originalTextFieldColor);
		textFieldSupplierEmail.setEditable(true);
		textFieldSupplierEmail.setBackground(originalTextFieldColor);
		textFieldSupplierAdditionalInfo.setEditable(true);
		textFieldSupplierAdditionalInfo.setBackground(originalTextFieldColor);
		textFieldSupplierId.setEditable(true);
		textFieldSupplierId.setBackground(originalTextFieldColor);
	}

	public void saveSupplier() {
		Supplier supplier = getSupplierFromSupplierFields();
		supplierServiceImpl.saveSupplier(supplier);
		int row = table.getSelectedRow();
		populateSupplierTable();
		table.setRowSelectionInterval(row, row);
		setFieldsNonEditable();
		setEditButtonsDisabled();
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
