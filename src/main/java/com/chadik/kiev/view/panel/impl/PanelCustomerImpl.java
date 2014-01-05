package com.chadik.kiev.view.panel.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chadik.kiev.model.Customer;
import com.chadik.kiev.service.ICustomerJpaService;
import com.chadik.kiev.util.TableUtil;
import com.chadik.kiev.view.dialog.IDialogCustomer;
import com.chadik.kiev.view.dialog.IDialogGeneric;
import com.chadik.kiev.view.panel.IPanelCustomer;

@Component
public class PanelCustomerImpl extends PanelGenericImpl<Customer> implements
		IPanelCustomer {
	
	private JPanel panelTableHolder;
	private JPanel panelTableHolderContent;
	private JPanel panelTableHolderContentTable;
	private JPanel panelTableHolderContentButtons;

	private JPanel panelInfoHolder;
	private JPanel panelInfoHolderContent;
	private JPanel panelInfoHolderContentInfo;
	private JPanel panelInfoHolderContentButtons;
	
	private JScrollPane scrollPaneTable;
	
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
	
	private List<Customer> customers;

	@Autowired
	private ICustomerJpaService customerJpaServiceImpl;
	@Autowired
	private IDialogCustomer dialogCustomerImpl;

	@Override
	public Customer getTableEntity() {
		return null;
	}

	@Override
	public IDialogGeneric getDialog() {
		return dialogCustomerImpl;
	}

	@Override
	public void populateTableCustomer() {
		customers = customerJpaServiceImpl.getAll();

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
		}

	}

	@Override
	public JPanel createPanelInfoHolder() {
		panelInfoHolder = new JPanel();
		panelInfoHolder.setLayout(new BorderLayout());
		
		panelInfoHolderContent = new JPanel();
		panelInfoHolderContent.setLayout(new BorderLayout());
		panelInfoHolderContent.setPreferredSize(new Dimension(400, 600));
		
		panelInfoHolderContentInfo = new JPanel();
		panelInfoHolderContentInfo.setLayout(null);
		panelInfoHolderContentInfo.setPreferredSize(new Dimension(400, 550));
		
		panelInfoHolderContentButtons = new JPanel();
		panelInfoHolderContentButtons.setLayout(new FlowLayout());
		panelInfoHolderContentButtons.setPreferredSize(new Dimension(400, 50));
		
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

		y = y + height + spacing;

		labelCustomerAddress = new JLabel("Адреса:");
		labelCustomerAddress.setBounds(xLabel, y, weightLabel, height);

		textFieldCustomerAddress = new JTextField();
		textFieldCustomerAddress
				.setBounds(xTextField, y, weightTextField, height);

		y = y + height + spacing;

		labelCustomerPhoneNumber = new JLabel("Телефонски Број:");
		labelCustomerPhoneNumber.setBounds(xLabel, y, weightLabel, height);

		textFieldCustomerPhoneNumber = new JTextField();
		textFieldCustomerPhoneNumber.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;

		labelCustomerEmail = new JLabel("Email:");
		labelCustomerEmail.setBounds(xLabel, y, weightLabel, height);

		textFieldCustomerEmail = new JTextField();
		textFieldCustomerEmail.setBounds(xTextField, y, weightTextField, height);

		y = y + height + spacing;

		labelCustomerAdditionalInfo = new JLabel("Забелешки:");
		labelCustomerAdditionalInfo.setBounds(xLabel, y, weightLabel, height);

		textFieldCustomerAdditionalInfo = new JTextField();
		textFieldCustomerAdditionalInfo.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;

		labelCustomerId = new JLabel("ID:");
		labelCustomerId.setBounds(xLabel, y, weightLabel, height);

		textFieldCustomerId = new JTextField();
		textFieldCustomerId.setBounds(xTextField, y, weightTextField, height);
		
		buttonSave = new JButton("Зачувај");
		buttonSave.setPreferredSize(new Dimension(100, 25));

		buttonCancel = new JButton("Откажи");
		buttonCancel.setPreferredSize(new Dimension(100, 25));

		panelInfoHolderContentInfo.add(labelCustomerName);
		panelInfoHolderContentInfo.add(textFieldCustomerName);

		panelInfoHolderContentInfo.add(labelCustomerAddress);
		panelInfoHolderContentInfo.add(textFieldCustomerAddress);

		panelInfoHolderContentInfo.add(labelCustomerPhoneNumber);
		panelInfoHolderContentInfo.add(textFieldCustomerPhoneNumber);

		panelInfoHolderContentInfo.add(labelCustomerEmail);
		panelInfoHolderContentInfo.add(textFieldCustomerEmail);

		panelInfoHolderContentInfo.add(labelCustomerAdditionalInfo);
		panelInfoHolderContentInfo.add(textFieldCustomerAdditionalInfo);
		
		panelInfoHolderContentButtons.add(buttonSave);
		panelInfoHolderContentButtons.add(buttonCancel);
		
		panelInfoHolderContent.add(panelInfoHolderContentInfo,
				BorderLayout.CENTER);
		panelInfoHolderContent.add(panelInfoHolderContentButtons,
				BorderLayout.SOUTH);
		
		panelInfoHolder.add(panelInfoHolderContent);
		
		return panelInfoHolder;
	}

	@Override
	public JPanel createPanelTableHolder() {
		panelTableHolder = new JPanel();
		panelTableHolder.setLayout(new BorderLayout());

		panelTableHolderContent = new JPanel();
		panelTableHolderContent.setLayout(new BorderLayout());
		panelTableHolderContent.setPreferredSize(new Dimension(400, 600));

		panelTableHolderContentTable = new JPanel();
		panelTableHolderContentTable.setLayout(new BorderLayout());
		panelTableHolderContentTable.setPreferredSize(new Dimension(400, 550));

		panelTableHolderContentButtons = new JPanel();
		panelTableHolderContentButtons.setLayout(new FlowLayout());
		panelTableHolderContentButtons.setPreferredSize(new Dimension(400, 50));
		
		defaultTableModel = new DefaultTableModel();
		table = new JTable();

		defaultTableModel.setColumnIdentifiers(getTableCustomerColumnNames());
		table.setModel(defaultTableModel);
		TableUtil.hideColumns(table, getTableCustomerHiddenColumns());
		TableUtil.allignCells(table, SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setMaxWidth(100);

		populateTableCustomer();
		
		scrollPaneTable = new JScrollPane(table);
		
		buttonNew = new JButton("Креирај");
		buttonNew.setPreferredSize(new Dimension(100, 25));
		buttonNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getDialog().initDialog();
			}
		});

		buttonEdit = new JButton("Измени");
		buttonEdit.setPreferredSize(new Dimension(100, 25));

		buttonDelete = new JButton("Избриши");
		buttonDelete.setPreferredSize(new Dimension(100, 25));
		
		panelTableHolderContentTable.add(scrollPaneTable);
		
		panelTableHolderContentButtons.add(buttonDelete);
		panelTableHolderContentButtons.add(buttonEdit);
		panelTableHolderContentButtons.add(buttonNew);

		panelTableHolderContent.add(panelTableHolderContentTable,
				BorderLayout.CENTER);
		panelTableHolderContent.add(panelTableHolderContentButtons,
				BorderLayout.SOUTH);

		panelTableHolder.add(panelTableHolderContent);
		
		return panelTableHolder;
	}

	@Override
	public String[] getTableCustomerColumnNames() {
		return new String[] { "Реден Бр.", "Id", "Назив", "Адреса",
				"Телефонски број", "Email", "Забелешки" };
	}

	@Override
	public int[] getTableCustomerHiddenColumns() {
		return new int[] { 1, 3, 5, 6 };
	}

}
