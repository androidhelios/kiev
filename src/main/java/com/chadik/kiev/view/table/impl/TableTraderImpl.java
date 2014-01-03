package com.chadik.kiev.view.table.impl;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chadik.kiev.model.Trader;
import com.chadik.kiev.service.IGenericJpaService;
import com.chadik.kiev.service.ITraderJpaService;
import com.chadik.kiev.view.table.ITableTrader;

@Component
public class TableTraderImpl extends TableGenericImpl<Trader> implements
		ITableTrader {

	private List<Trader> traders;

	@Autowired
	private ITraderJpaService traderJpaServiceImpl;

	@Override
	public void populateTable() {

		traders = traderJpaServiceImpl.getAll();

		int i = 0;

		getDefaultTableModel().setRowCount(0);

		for (Trader trader : traders) {
			getDefaultTableModel().addRow(
					new String[] { 
							Integer.toString(++i),
							trader.getTraderId().toString(),
							trader.getTraderName(),
							trader.getTraderRegistryNumber(),
							trader.getTraderBankName(),
							trader.getTraderBankAccount(),
							trader.getTraderAddress(),
							trader.getTraderPhoneNumber(),
							trader.getTraderEmail(),
							trader.getTraderAdditionalInfo() });
		}

		if (getTable().getRowCount() > 0) {
			getTable().setRowSelectionInterval(getTable().getRowCount() - 1,
					getTable().getRowCount() - 1);
		}

	}

	@Override
	public String[] getColumnsNames() {
		return new String[] { "Реден Бр.", "Id", "Назив", "Регистарски Број", "Банка",
				"Банкарска Сметка", "Адреса", "Телефонски број", "Email", "Забелешки" };
	}

	@Override
	public int[] getHiddenColumns() {
		return new int[] { 1, 3, 4, 5, 6, 8, 9 };
	}

	@Override
	public IGenericJpaService getGenericJpaService() {
		return traderJpaServiceImpl;
	}

}
