package com.chadik.kiev.view.table.impl;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chadik.kiev.model.Customer;
import com.chadik.kiev.model.Product;
import com.chadik.kiev.model.Trader;
import com.chadik.kiev.service.ICustomerJpaService;
import com.chadik.kiev.service.IGenericJpaService;
import com.chadik.kiev.service.IProductJpaService;
import com.chadik.kiev.service.ITraderJpaService;
import com.chadik.kiev.view.table.ITableCustomer;
import com.chadik.kiev.view.table.ITableProduct;
import com.chadik.kiev.view.table.ITableTrader;

@Component
public class TableProductImpl extends TableGenericImpl<Product> implements
		ITableProduct {

	private List<Product> products;

	@Autowired
	private IProductJpaService productJpaServiceImpl;

	@Override
	public void populateTable() {

		products = productJpaServiceImpl.getAll();

		int i = 0;

		getDefaultTableModel().setRowCount(0);

		for (Product product : products) {
			getDefaultTableModel().addRow(
					new String[] { 
							Integer.toString(++i),
							product.getProductId().toString(),
							product.getProductName(),
							product.getProductMeasurement(),
							product.getProductTax(),
							product.getProductPrice(),
							product.getProductAdditionalInfo()});
		}

		if (getTable().getRowCount() > 0) {
			getTable().setRowSelectionInterval(getTable().getRowCount() - 1,
					getTable().getRowCount() - 1);
		}

	}

	@Override
	public String[] getColumnsNames() {
		return new String[] { "Реден Бр.", "Id", "Назив", "Единица Мерка",
				"Данок", "Цена", "Забелешки" };
	}

	@Override
	public int[] getHiddenColumns() {
		return new int[] { 1, 4, 5, 6 };
	}

	@Override
	public IGenericJpaService getGenericJpaService() {
		return productJpaServiceImpl;
	}

}
