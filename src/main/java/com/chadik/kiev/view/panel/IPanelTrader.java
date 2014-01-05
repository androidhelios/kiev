package com.chadik.kiev.view.panel;

import javax.swing.JTable;

import com.chadik.kiev.model.Trader;

public interface IPanelTrader extends IPanelGeneric<Trader> {
	
	public abstract String[] getTableTraderColumnNames();
	
	public int[] getTableTraderHiddenColumns();
	
	public void populateTableTrader();

}
