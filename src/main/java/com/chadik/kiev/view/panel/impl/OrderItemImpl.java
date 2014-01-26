package com.chadik.kiev.view.panel.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import org.springframework.stereotype.Component;

import com.chadik.kiev.view.panel.IOrderItemPanel;

@Component
public class OrderItemImpl implements IOrderItemPanel {

	private JPanel panelAll;

	private JPanel panelTableHolder;
	private JPanel panelTableHolderContent;
	private JPanel panelTableHolderContentTable;

	@Override
	public JPanel initOrderItemPanel() {
		panelAll = new JPanel();
		panelAll.setLayout(new BorderLayout());

		panelTableHolder = new JPanel();
		panelTableHolder.setLayout(new BorderLayout());

		panelTableHolderContent = new JPanel();
		panelTableHolderContent.setLayout(new BorderLayout());

		panelTableHolderContentTable = new JPanel();
		panelTableHolderContentTable.setLayout(new BorderLayout());
		panelTableHolderContentTable.setPreferredSize(new Dimension(400, 300));

		panelTableHolderContent.add(panelTableHolderContentTable,
				BorderLayout.CENTER);

		panelTableHolder.add(panelTableHolderContent, BorderLayout.CENTER);

		panelAll.add(panelTableHolder, BorderLayout.CENTER);

		return panelAll;
	}

	@Override
	public void populateOrderItemTable() {
		// TODO Auto-generated method stub

	}

}
