package com.chadik.kiev.view.panel.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.springframework.stereotype.Component;

import com.chadik.kiev.view.dialog.IDialogGeneric;
import com.chadik.kiev.view.panel.IPanelGeneric;

@Component
public abstract class PanelGenericImpl<T> implements
		IPanelGeneric<T> {
	
	private JPanel panelAll;
	private JPanel panelTableHolder;
	private JPanel panelInfoHolder;
	
	@Override
	public JPanel initPanel() {

		panelAll = new JPanel();
		panelAll.setLayout(new BorderLayout());

		panelTableHolder = createPanelTableHolder();
		panelInfoHolder = createPanelInfoHolder();		

		panelAll.add(panelTableHolder, BorderLayout.WEST);
		panelAll.add(panelInfoHolder, BorderLayout.CENTER);
		
		return panelAll;

	}
	
	public abstract T getTableEntity();
	
	public abstract IDialogGeneric getDialog();
	
	public abstract JPanel createPanelInfoHolder();
	
	public abstract JPanel createPanelTableHolder();
	

}
