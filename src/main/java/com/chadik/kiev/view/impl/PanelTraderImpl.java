package com.chadik.kiev.view.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.chadik.kiev.dao.IGenericJpaDao;
import com.chadik.kiev.model.Trader;
import com.chadik.kiev.service.IGenericJpaService;
import com.chadik.kiev.service.ITraderJpaService;
import com.chadik.kiev.util.TableUtil;
import com.chadik.kiev.view.IPanelTrader;
import com.chadik.kiev.view.table.ITableGeneric;
import com.chadik.kiev.view.table.ITableTrader;

@Component
public class PanelTraderImpl extends PanelGenericImpl<Trader> implements
		IPanelTrader {

	@Autowired
	private ITraderJpaService traderJpaServiceImpl;
	
	@Autowired
	private ITableTrader tableTraderImpl;

	@Override
	public ITableGeneric getTableGeneric() {
		return tableTraderImpl;
	}

	@Override
	public Trader getTableInfo() {
		return null;
	}


	
}
