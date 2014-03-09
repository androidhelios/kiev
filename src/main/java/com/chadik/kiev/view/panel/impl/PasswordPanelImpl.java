package com.chadik.kiev.view.panel.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import org.apache.log4j.chainsaw.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chadik.kiev.model.Customer;
import com.chadik.kiev.model.Supplier;
import com.chadik.kiev.service.ICustomerService;
import com.chadik.kiev.service.ISupplierService;
import com.chadik.kiev.view.FrameMain;
import com.chadik.kiev.view.panel.IPasswordPanel;

@Component
public class PasswordPanelImpl implements IPasswordPanel {
	
	private JPanel panelAll;
	private JPanel panelLoginHolder;
	private JPanel panelLoginHolderContent;
	
	private JLabel labelUserName;
	private JLabel labelPassword;
	
	private JTextField textFieldUserName;
	private JPasswordField passwordFieldPassword;
	
	private JButton buttonLogin;
	private JButton buttonCancel;
	
	private String supplierUserName;
	private String supplierPassword;
	
	@Autowired
	private FrameMain frameMain;
	@Autowired
	private ISupplierService supplierServiceImpl;

	@Override
	public JPanel initPasswordPanel() {
		panelAll = new JPanel();
		panelAll.setLayout(new BorderLayout());
		
		panelLoginHolder = new JPanel();
		panelLoginHolder.setLayout(new GridBagLayout());
//		panelLoginHolder.setPreferredSize(new Dimension(1000, 600));
		panelLoginHolder.setBackground(new Color(224, 224, 224));
		
		panelLoginHolderContent = new JPanel();
		panelLoginHolderContent.setLayout(null);
		panelLoginHolderContent.setPreferredSize(new Dimension(400, 150));
		panelLoginHolderContent.setBorder(new BevelBorder(BevelBorder.LOWERED));
		
		int spacing = 5;
		int weightLabel = 125;
		int height = 25;
		int weightTextField = 250;
		int xLabel = 10;
		int xTextField = xLabel + weightLabel + spacing;
		int y = 25;
		
		labelUserName = new JLabel("Име:");
		labelUserName.setBounds(xLabel, y, weightLabel, height);

		textFieldUserName = new JTextField();
		textFieldUserName.setBounds(xTextField, y, weightTextField, height);
		textFieldUserName.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelPassword = new JLabel("Лозинка:");
		labelPassword.setBounds(xLabel, y, weightLabel, height);

		passwordFieldPassword = new JPasswordField();
		passwordFieldPassword.setBounds(xTextField, y, weightTextField,
				height);
		passwordFieldPassword.setMargin(new Insets(2, 2, 2, 2));
		
		y = y + height + spacing;
		
		buttonLogin = new JButton("Влез");
		buttonLogin.setBounds(xTextField, y, weightTextField - 127,
				height);
		buttonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validatePassword()) {
					frameMain.clearContentPane();
				} else {
					textFieldUserName.setText("");
					passwordFieldPassword.setText("");
				}
			}
		});
		
		buttonCancel = new JButton("Откажи");
		buttonCancel.setBounds(xTextField + 125, y, weightTextField - 127,
				height);
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		panelLoginHolderContent.add(labelUserName);
		panelLoginHolderContent.add(textFieldUserName);
		
		panelLoginHolderContent.add(labelPassword);
		panelLoginHolderContent.add(passwordFieldPassword);
		
		panelLoginHolderContent.add(buttonLogin);
		panelLoginHolderContent.add(buttonCancel);
		
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		
		panelLoginHolder.add(panelLoginHolderContent, gridBagConstraints);
		
		panelAll.add(panelLoginHolder);
		
		return panelAll;
	}
	
	public boolean validatePassword() {
		boolean result = false;
		Supplier supplier = supplierServiceImpl.findSupplierById(new BigDecimal(1));
		
		if (textFieldUserName.getText().equals(supplier.getSupplierUserName()) && (passwordFieldPassword.getText().equals(supplier.getSupplierPassword()))) {
			result = true;
		}
		
		return result;
	}
	
	@Override
	public String getSupplierUserName() {
		return supplierUserName;
	}
	
	@Override
	public String getSupplierPassword() {
		return supplierPassword;
	}
	
	@Override
	public void setSupplierUserName(String supplierUserName) {
		this.supplierUserName = supplierUserName;
	}
	
	@Override
	public void setSupplierPassword(String supplierPassword) {
		this.supplierPassword = supplierPassword;
	}

}