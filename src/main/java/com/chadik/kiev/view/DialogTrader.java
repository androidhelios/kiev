package com.chadik.kiev.view;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.chadik.kiev.model.Trader;
import com.chadik.kiev.service.IGenericJpaService;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Component
public class DialogTrader extends JDialog {
	
	@Autowired
	@Qualifier("traderJpaServiceImpl")
	private IGenericJpaService traderJpaServiceImpl;
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	
	
	public void initDialogTrader() {
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setTitle("Add Trader");
		setContentPane(contentPane);
		
		JLabel labelTraderName = new JLabel("Име:");
		labelTraderName.setBounds(10, 25, 104, 14);
		contentPane.add(labelTraderName);
		
		JLabel labelTraderRegistryNumber = new JLabel("Регистерски Број:");
		labelTraderRegistryNumber.setBounds(10, 50, 104, 14);
		contentPane.add(labelTraderRegistryNumber);
		
		JLabel labelTraderBankName = new JLabel("Банка:");
		labelTraderBankName.setBounds(10, 75, 104, 14);
		contentPane.add(labelTraderBankName);
		
		JLabel labelTraderBankAccount = new JLabel("Банкарска Сметка:");
		labelTraderBankAccount.setBounds(10, 100, 104, 14);
		contentPane.add(labelTraderBankAccount);
		
		JLabel labelTraderAddress = new JLabel("Адреса:");
		labelTraderAddress.setBounds(10, 125, 104, 14);
		contentPane.add(labelTraderAddress);
		
		JLabel labelTraderPhoneNumber = new JLabel("Телефонски Број:");
		labelTraderPhoneNumber.setBounds(10, 150, 104, 14);
		contentPane.add(labelTraderPhoneNumber);
		
		JLabel labelTraderEmail = new JLabel("Email:");
		labelTraderEmail.setBounds(10, 175, 104, 14);
		contentPane.add(labelTraderEmail);
		
		JLabel labelTraderAdditionalInfo = new JLabel("Забелешки:");
		labelTraderAdditionalInfo.setBounds(10, 200, 104, 14);
		contentPane.add(labelTraderAdditionalInfo);
		
		JLabel labelTraderId = new JLabel("ID:");
		labelTraderId.setBounds(10, 225, 104, 14);
		contentPane.add(labelTraderId);
		
		textField = new JTextField();
		textField.setBounds(45, 22, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(45, 69, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(104, 47, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(104, 97, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(56, 123, 86, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(104, 147, 86, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(45, 174, 86, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(74, 197, 86, 20);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setBounds(28, 220, 86, 20);
		contentPane.add(textField_8);
		textField_8.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Trader trader = new Trader();
				trader.setTraderName("pile");
				trader.setTraderRegistryNumber("123");
				trader.setTraderBankName("komercijalna");
				trader.setTraderBankAccount("12344");
				trader.setTraderAddress("nema");
				traderJpaServiceImpl.save(trader);
				dispose();
			}
		});
		btnNewButton.setBounds(71, 250, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(170, 250, 89, 23);
		contentPane.add(btnNewButton_1);
		setPreferredSize(new Dimension(400, 400));
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
}
