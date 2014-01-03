package com.chadik.kiev.view.panel.impl;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chadik.kiev.model.Customer;
import com.chadik.kiev.service.ICustomerJpaService;
import com.chadik.kiev.util.TableUtil;
import com.chadik.kiev.view.dialog.IDialogCustomer;
import com.chadik.kiev.view.dialog.IDialogGeneric;
import com.chadik.kiev.view.dialog.IDialogTrader;
import com.chadik.kiev.view.panel.IPanelCustomer;
import com.chadik.kiev.view.table.ITableCustomer;
import com.chadik.kiev.view.table.ITableGeneric;

@Component
public class PanelCustomerImpl extends PanelGenericImpl<Customer> implements
		IPanelCustomer {

	private DefaultTableModel defaultTableModel;
	private JTable table;
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
	public JPanel createPanelInfoHolderContentInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void populateTable() {
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
	public JTable createTable() {
		defaultTableModel = new DefaultTableModel();
		table = new JTable();

		defaultTableModel.setColumnIdentifiers(getTableColumnNames());
		table.setModel(defaultTableModel);
		TableUtil.hideColumns(table, getTableHiddenColumns());
		TableUtil.allignCells(table, SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setMaxWidth(100);

		populateTable();

		return table;
	}

	@Override
	public String[] getTableColumnNames() {
		return new String[] { "Реден Бр.", "Id", "Назив", "Адреса",
				"Телефонски број", "Email", "Забелешки" };
	}

	@Override
	public int[] getTableHiddenColumns() {
		return new int[] { 1, 3, 5, 6 };
	}

	@Override
	public void populatePanelInfoHolderContentInfo() {
		// TODO Auto-generated method stub
		
	}

}
