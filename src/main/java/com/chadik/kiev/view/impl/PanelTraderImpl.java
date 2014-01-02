package com.chadik.kiev.view.impl;

import javax.swing.JPanel;

import org.springframework.stereotype.Component;

import com.chadik.kiev.view.IPanelTrader;

@Component
public class PanelTraderImpl extends PanelGenericImpl implements IPanelTrader {

	@Override
	public JPanel getPanel() {
		return this;
	}

}
