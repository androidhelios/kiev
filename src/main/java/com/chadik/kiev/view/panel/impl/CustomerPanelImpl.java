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

import com.chadik.kiev.model.Customer;
import com.chadik.kiev.service.ICustomerService;
import com.chadik.kiev.util.PanelUtil;
import com.chadik.kiev.util.TableUtil;
import com.chadik.kiev.view.dialog.ICustomerDialog;
import com.chadik.kiev.view.panel.ICustomerPanel;

@Component
public class CustomerPanelImpl implements ICustomerPanel {

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

	private JButton buttonNew;
	private JButton buttonEdit;
	private JButton buttonDelete;
	private JButton buttonSave;
	private JButton buttonCancel;

	private Color originalTextFieldColor;
	private Color nonEditableTextFieldColor;

	private List<Customer> customers;

	@Autowired
	private ICustomerService customerServiceImpl;
	@Autowired
	private ICustomerDialog customerDialogImpl;

	@Override
	public JPanel initCustomerPanel() {
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
				"Листа на продукти"));

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
		defaultTableModel.setColumnIdentifiers(getTableCustomerColumnNames());

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				String selectedRowCustomerId = (String) table
						.getValueAt(row, 1);
				Customer customer = getCustomerFromCustomerTable(selectedRowCustomerId);
				populateCustomerFields(customer);
				setEditButtonsDisabled();
			}
		});

		table.setModel(defaultTableModel);

		TableUtil.hideColumns(table, getTableCustomerHiddenColumns());

		TableUtil.allignCells(table, SwingConstants.CENTER);

		table.getColumnModel().getColumn(0).setMaxWidth(100);

		scrollPaneTable = new JScrollPane(table);

		verticalScrollBar = scrollPaneTable.getVerticalScrollBar();

		buttonNew = new JButton("Креирај");
		buttonNew.setPreferredSize(new Dimension(100, 25));
		buttonNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerDialogImpl.initCustomerDialog();
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

		labelCustomerName = new JLabel("Име:");
		labelCustomerName.setBounds(xLabel, y, weightLabel, height);

		textFieldCustomerName = new JTextField();
		textFieldCustomerName.setBounds(xTextField, y, weightTextField, height);
		textFieldCustomerName.setMargin(new Insets(2, 2, 2, 2));

		originalTextFieldColor = textFieldCustomerName.getBackground();

		y = y + height + spacing;

		labelCustomerAddress = new JLabel("Адреса:");
		labelCustomerAddress.setBounds(xLabel, y, weightLabel, height);

		textFieldCustomerAddress = new JTextField();
		textFieldCustomerAddress.setBounds(xTextField, y, weightTextField,
				height);
		textFieldCustomerAddress.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelCustomerPhoneNumber = new JLabel("Телефонски Број:");
		labelCustomerPhoneNumber.setBounds(xLabel, y, weightLabel, height);

		textFieldCustomerPhoneNumber = new JTextField();
		textFieldCustomerPhoneNumber.setBounds(xTextField, y, weightTextField,
				height);
		textFieldCustomerPhoneNumber.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelCustomerEmail = new JLabel("Email:");
		labelCustomerEmail.setBounds(xLabel, y, weightLabel, height);

		textFieldCustomerEmail = new JTextField();
		textFieldCustomerEmail
				.setBounds(xTextField, y, weightTextField, height);
		textFieldCustomerEmail.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelCustomerAdditionalInfo = new JLabel("Забелешки:");
		labelCustomerAdditionalInfo.setBounds(xLabel, y, weightLabel, height);

		textFieldCustomerAdditionalInfo = new JTextField();
		textFieldCustomerAdditionalInfo.setBounds(xTextField, y,
				weightTextField, height);
		textFieldCustomerAdditionalInfo.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelCustomerId = new JLabel("ID:");
		labelCustomerId.setBounds(xLabel, y, weightLabel, height);

		textFieldCustomerId = new JTextField();
		textFieldCustomerId.setBounds(xTextField, y, weightTextField, height);
		textFieldCustomerId.setMargin(new Insets(2, 2, 2, 2));

		buttonSave = new JButton("Зачувај");
		buttonSave.setPreferredSize(new Dimension(100, 25));
		buttonSave.setEnabled(false);

		buttonCancel = new JButton("Откажи");
		buttonCancel.setPreferredSize(new Dimension(100, 25));
		buttonCancel.setEnabled(false);
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String selectedRowCustomerId = (String) table.getValueAt(row, 1);
				Customer customer = getCustomerFromCustomerTable(selectedRowCustomerId);
				populateCustomerFields(customer);
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

		panelInfoHolderContentInfo.add(labelCustomerName,
				firstLabelConstrains());
		panelInfoHolderContentInfo.add(textFieldCustomerName,
				textFieldConstraints());

		panelInfoHolderContentInfo
				.add(labelCustomerAddress, labelConstraints());
		panelInfoHolderContentInfo.add(textFieldCustomerAddress,
				textFieldConstraints());

		panelInfoHolderContentInfo.add(labelCustomerPhoneNumber,
				labelConstraints());
		panelInfoHolderContentInfo.add(textFieldCustomerPhoneNumber,
				textFieldConstraints());

		panelInfoHolderContentInfo.add(labelCustomerEmail, labelConstraints());
		panelInfoHolderContentInfo.add(textFieldCustomerEmail,
				textFieldConstraints());

		panelInfoHolderContentInfo.add(labelCustomerAdditionalInfo,
				labelConstraints());
		panelInfoHolderContentInfo.add(textFieldCustomerAdditionalInfo,
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

		populateCustomerTable();

		return panelAll;
	}

	@Override
	public void populateCustomerTable() {
		String selectedRowCustomerId = "";
		customers = customerServiceImpl.findAllCustomers();

		int i = 0;

		defaultTableModel.setRowCount(0);

		for (Customer customer : customers) {
			defaultTableModel.addRow(new String[] { Integer.toString(++i),
					customer.getCustomerId().toString(),
					customer.getCustomerName(), customer.getCustomerAddress(),
					customer.getCustomerPhoneNumber(),
					customer.getCustomerEmail(),
					customer.getCustomerAdditionalInfo() });
		}

		if (table.getRowCount() > 0) {
			table.setRowSelectionInterval(table.getRowCount() - 1,
					table.getRowCount() - 1);

			selectedRowCustomerId = (String) table.getValueAt(
					table.getRowCount() - 1, 1);
			Customer customer = getCustomerFromCustomerTable(selectedRowCustomerId);
			populateCustomerFields(customer);

			setTableButtonsEnabled();

		}

		scrollPaneTable.validate();
		verticalScrollBar.setValue(verticalScrollBar.getMaximum());

		setFieldsNonEditable();

	}

	public String[] getTableCustomerColumnNames() {
		return new String[] { "Реден Бр.", "Id", "Назив", "Адреса",
				"Телефонски број", "Email", "Забелешки" };
	}

	public int[] getTableCustomerHiddenColumns() {
		return new int[] { 1, 3, 5, 6 };
	}

	public Customer getCustomerFromCustomerTable(String selectedRowCustomerId) {
		BigDecimal customerId = new BigDecimal(selectedRowCustomerId);
		return customerServiceImpl.findCustomerById(customerId);
	}

	public void populateCustomerFields(Customer customer) {
		textFieldCustomerId.setText(customer.getCustomerId().toString());
		textFieldCustomerName.setText(customer.getCustomerName());
		textFieldCustomerAddress.setText(customer.getCustomerAddress());
		textFieldCustomerPhoneNumber.setText(customer.getCustomerPhoneNumber());
		textFieldCustomerEmail.setText(customer.getCustomerEmail());
		textFieldCustomerAdditionalInfo.setText(customer
				.getCustomerAdditionalInfo());
	}

	public void setFieldsNonEditable() {
		nonEditableTextFieldColor = new Color(255, 255, 204);

		textFieldCustomerName.setEditable(false);
		textFieldCustomerName.setBackground(nonEditableTextFieldColor);
		textFieldCustomerAddress.setEditable(false);
		textFieldCustomerAddress.setBackground(nonEditableTextFieldColor);
		textFieldCustomerPhoneNumber.setEditable(false);
		textFieldCustomerPhoneNumber.setBackground(nonEditableTextFieldColor);
		textFieldCustomerEmail.setEditable(false);
		textFieldCustomerEmail.setBackground(nonEditableTextFieldColor);
		textFieldCustomerAdditionalInfo.setEditable(false);
		textFieldCustomerAdditionalInfo
				.setBackground(nonEditableTextFieldColor);
		textFieldCustomerId.setEditable(false);
		textFieldCustomerId.setBackground(nonEditableTextFieldColor);
	}

	public void setFieldsEditable() {
		textFieldCustomerName.setEditable(true);
		textFieldCustomerName.setBackground(originalTextFieldColor);
		textFieldCustomerAddress.setEditable(true);
		textFieldCustomerAddress.setBackground(originalTextFieldColor);
		textFieldCustomerPhoneNumber.setEditable(true);
		textFieldCustomerPhoneNumber.setBackground(originalTextFieldColor);
		textFieldCustomerEmail.setEditable(true);
		textFieldCustomerEmail.setBackground(originalTextFieldColor);
		textFieldCustomerAdditionalInfo.setEditable(true);
		textFieldCustomerAdditionalInfo.setBackground(originalTextFieldColor);
		textFieldCustomerId.setEditable(true);
		textFieldCustomerId.setBackground(originalTextFieldColor);
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
