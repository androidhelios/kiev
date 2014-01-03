package com.chadik.kiev.view.table.impl;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chadik.kiev.model.Customer;
import com.chadik.kiev.model.Trader;
import com.chadik.kiev.service.ICustomerJpaService;
import com.chadik.kiev.service.IGenericJpaService;
import com.chadik.kiev.service.ITraderJpaService;
import com.chadik.kiev.view.table.ITableCustomer;
import com.chadik.kiev.view.table.ITableTrader;

@Component
public class TableCustomerImpl extends TableGenericImpl<Customer> implements
		ITableCustomer {

	private List<Customer> customers;

	@Autowired
	private ICustomerJpaService customerJpaServiceImpl;

	@Override
	public void populateTable() {

		customers = customerJpaServiceImpl.getAll();

		int i = 0;

		getDefaultTableModel().setRowCount(0);

		for (Customer customer : customers) {
			getDefaultTableModel().addRow(
					new String[] { 
							Integer.toString(++i),
							customer.getCustomerId().toString(),
							customer.getCustomerName(),
							customer.getCustomerAddress(),
							customer.getCustomerPhone(),
							customer.getCustomerEmail(),
							customer.getCustomerAdditionalInfo()});
		}

		if (getTable().getRowCount() > 0) {
			getTable().setRowSelectionInterval(getTable().getRowCount() - 1,
					getTable().getRowCount() - 1);
		}

	}

	@Override
	public String[] getColumnsNames() {
		return new String[] { "Реден Бр.", "Id", "Назив", "Адреса",
				"Телефонски број", "Email", "Забелешки" };
	}

	@Override
	public int[] getHiddenColumns() {
		return new int[] { 1, 3, 5, 6 };
	}

	@Override
	public IGenericJpaService getGenericJpaService() {
		return customerJpaServiceImpl;
	}

}
