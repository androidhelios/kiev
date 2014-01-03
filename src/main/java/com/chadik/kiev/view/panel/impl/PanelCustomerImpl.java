package com.chadik.kiev.view.panel.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chadik.kiev.model.Customer;
import com.chadik.kiev.service.ICustomerJpaService;
import com.chadik.kiev.view.dialog.IDialogCustomer;
import com.chadik.kiev.view.dialog.IDialogGeneric;
import com.chadik.kiev.view.dialog.IDialogTrader;
import com.chadik.kiev.view.panel.IPanelCustomer;
import com.chadik.kiev.view.table.ITableCustomer;
import com.chadik.kiev.view.table.ITableGeneric;

@Component
public class PanelCustomerImpl extends PanelGenericImpl<Customer> implements
		IPanelCustomer {

	@Autowired
	private ICustomerJpaService customerJpaServiceImpl;
	
	@Autowired
	private IDialogCustomer dialogCustomerImpl;
	
	@Autowired
	private ITableCustomer tableCustomerImpl;

	@Override
	public ITableGeneric getPanelTable() {
		return tableCustomerImpl;
	}

	@Override
	public Customer getTableInfo() {
		return null;
	}

	@Override
	public IDialogGeneric getDialogGeneric() {
		return dialogCustomerImpl;
	}
	
}
