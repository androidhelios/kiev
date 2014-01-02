package com.chadik.kiev.view.dialog.impl;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import org.springframework.stereotype.Component;

import com.chadik.kiev.model.Trader;
import com.chadik.kiev.view.dialog.IDialogGeneric;
import com.chadik.kiev.view.dialog.IDialogTrader;

@Component
public class DialogTraderImpl extends GenericDialogImpl<Trader> implements IDialogTrader {

	private JPanel panelFieldsContent;
	
	@Override
	public String getDilogName() {
		return "Нов Корисник";
	}

	@Override
	public Dimension getPanelFieldsDimension() {
		return new Dimension(400, 250);
	}

	@Override
	public Dimension getPanelButtonsDimension() {
		return new Dimension(400, 50);
	}

	@Override
	public JPanel getPanelFieldsContent() {
		panelFieldsContent = new JPanel();
		panelFieldsContent.setLayout(null);
		panelFieldsContent.setPreferredSize(getPanelFieldsDimension());
		panelFieldsContent.setBackground(Color.RED);
		
		return panelFieldsContent;
	}

}
