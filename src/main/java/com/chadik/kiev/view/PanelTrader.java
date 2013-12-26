package com.chadik.kiev.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.chadik.kiev.model.Trader;
import com.chadik.kiev.service.IGenericJpaService;
import com.chadik.kiev.service.impl.TraderJpaServiceImpl;
import java.awt.Color;

import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.UIManager;

@Component
public class PanelTrader extends JPanel {
	public PanelTrader() {
	}
	
	@Autowired
	@Qualifier("traderJpaServiceImpl")
	private IGenericJpaService traderJpaServiceImpl;	
	@Autowired
	private DialogTrader traderDialog;
	private JTable tableTrader;
	private JTextField textFieldTraderName;
	private JTextField textFieldTraderRegistryNumber;
	private JTextField textFieldTraderBankName;
	private JTextField textFieldTraderBankAccount;
	private JTextField textFieldTraderAddress;
	private JTextField textFieldTraderPhoneNumber;
	private JTextField textFieldTraderEmailAddress;
	private JTextField textFieldAdditionalInfo;
	private JTextField textFieldTraderId;
	private DefaultTableModel defaultTableModel;
	
	public void initPanelTrader() {
		List<Trader> traders = new ArrayList<Trader>();
		traders = traderJpaServiceImpl.getAll();
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(800, 600));

		JPanel panelTraderTable = new JPanel();
		panelTraderTable.setBorder(new EmptyBorder(10, 10, 10, 10));
//		panelTraderTable.setBackground(Color.YELLOW);
		panelTraderTable.setPreferredSize(new Dimension(400, 600));
		add(panelTraderTable, BorderLayout.WEST);
		panelTraderTable.setLayout(new BorderLayout());

		JPanel panelTraderTableHolder = new JPanel();
		panelTraderTableHolder.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelTraderTableHolder.setPreferredSize(new Dimension(400, 540));
//		panelTraderTableHolder.setBackground(Color.ORANGE);
		panelTraderTable.add(panelTraderTableHolder, BorderLayout.CENTER);
		panelTraderTableHolder.setLayout(new BorderLayout());
		
		defaultTableModel = new DefaultTableModel();

		System.out.println("VELICINA: " + traders.size());
		defaultTableModel.setColumnIdentifiers(new String[] { "id",
				"Name", "BankName", "BankAccount",
				"Address" });

		for (Trader trader : traders) {
			defaultTableModel.addRow(new String[] {
					trader.getTraderId().toString(), trader.getTraderName(),
					trader.getTraderBankName(), trader.getTraderBankAccount(),
					trader.getTraderAddress() });
		}
		
		tableTrader = new JTable();
		tableTrader.setModel(defaultTableModel);

		JScrollPane scrollPaneTable = new JScrollPane(tableTrader);
		panelTraderTableHolder.add(scrollPaneTable);

		JPanel panelTraderButtonHolder = new JPanel();
		panelTraderButtonHolder.setPreferredSize(new Dimension(400, 50));
//		panelTraderButtonHolder.setBackground(Color.PINK);
		panelTraderTable.add(panelTraderButtonHolder, BorderLayout.SOUTH);

		JButton btnNewTrader = new JButton("Креирај");
		btnNewTrader.setPreferredSize(new Dimension(100,25));
		panelTraderButtonHolder.add(btnNewTrader);
		btnNewTrader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				traderDialog.initDialogTrader();				
			}
		});

		JButton btnEditTrader = new JButton("Промени");
		btnEditTrader.setPreferredSize(new Dimension(100,25));
		panelTraderButtonHolder.add(btnEditTrader);

		JButton btnDeleteTrader = new JButton("Избриши");
		btnDeleteTrader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				defaultTableModel.setRowCount(0);
				
			}
		});
		btnDeleteTrader.setPreferredSize(new Dimension(100,25));
		panelTraderButtonHolder.add(btnDeleteTrader);

		JPanel panelTraderInfo = new JPanel();
		panelTraderInfo.setPreferredSize(new Dimension(400, 600));
//		panelTraderInfo.setBackground(Color.RED);
		add(panelTraderInfo, BorderLayout.CENTER);
		panelTraderInfo.setLayout(null);
		
		JLabel labelTraderName = new JLabel("Име:");
		labelTraderName.setBounds(10, 25, 116, 14);
		panelTraderInfo.add(labelTraderName);
		
		JLabel labelTraderRergistryNumber = new JLabel("Регистарски Број:");
		labelTraderRergistryNumber.setBounds(10, 50, 116, 14);
		panelTraderInfo.add(labelTraderRergistryNumber);
		
		JLabel labelTraderBankName = new JLabel("Банка:");
		labelTraderBankName.setBounds(10, 75, 116, 14);
		panelTraderInfo.add(labelTraderBankName);
		
		JLabel labelTraderBankAccount = new JLabel("Банкарска Сметка:");
		labelTraderBankAccount.setBounds(10, 100, 116, 14);
		panelTraderInfo.add(labelTraderBankAccount);
		
		JLabel labelTraderAddress = new JLabel("Адреса:");
		labelTraderAddress.setBounds(10, 125, 116, 14);
		panelTraderInfo.add(labelTraderAddress);
		
		JLabel labelTraderPhoneNumber = new JLabel("Телефонски Број:");
		labelTraderPhoneNumber.setBounds(10, 150, 116, 14);
		panelTraderInfo.add(labelTraderPhoneNumber);
		
		JLabel labelTraderEmailAddress = new JLabel("Email:");
		labelTraderEmailAddress.setBounds(10, 175, 116, 14);
		panelTraderInfo.add(labelTraderEmailAddress);
		
		JLabel labelTraderAdditionalInfo = new JLabel("Забелешка:");
		labelTraderAdditionalInfo.setBounds(10, 200, 116, 14);
		panelTraderInfo.add(labelTraderAdditionalInfo);
		
		JLabel labelTraderId = new JLabel("ID:");
		labelTraderId.setBounds(10, 225, 116, 14);
		panelTraderInfo.add(labelTraderId);
		
		textFieldTraderName = new JTextField();
		textFieldTraderName.setBounds(130, 22, 230, 20);
		panelTraderInfo.add(textFieldTraderName);
		textFieldTraderName.setColumns(10);
		
		textFieldTraderRegistryNumber = new JTextField();
		textFieldTraderRegistryNumber.setBounds(130, 47, 230, 20);
		panelTraderInfo.add(textFieldTraderRegistryNumber);
		textFieldTraderRegistryNumber.setColumns(10);
		
		textFieldTraderBankName = new JTextField();
		textFieldTraderBankName.setBounds(130, 72, 230, 20);
		panelTraderInfo.add(textFieldTraderBankName);
		textFieldTraderBankName.setColumns(10);
		
		textFieldTraderBankAccount = new JTextField();
		textFieldTraderBankAccount.setBounds(130, 97, 230, 20);
		panelTraderInfo.add(textFieldTraderBankAccount);
		textFieldTraderBankAccount.setColumns(10);
		
		textFieldTraderAddress = new JTextField();
		textFieldTraderAddress.setBounds(130, 122, 230, 20);
		panelTraderInfo.add(textFieldTraderAddress);
		textFieldTraderAddress.setColumns(10);
		
		textFieldTraderPhoneNumber = new JTextField();
		textFieldTraderPhoneNumber.setBounds(130, 147, 230, 20);
		panelTraderInfo.add(textFieldTraderPhoneNumber);
		textFieldTraderPhoneNumber.setColumns(10);
		
		textFieldTraderEmailAddress = new JTextField();
		textFieldTraderEmailAddress.setBounds(130, 172, 230, 20);
		panelTraderInfo.add(textFieldTraderEmailAddress);
		textFieldTraderEmailAddress.setColumns(10);
		
		textFieldAdditionalInfo = new JTextField();
		textFieldAdditionalInfo.setBounds(130, 197, 230, 20);
		panelTraderInfo.add(textFieldAdditionalInfo);
		textFieldAdditionalInfo.setColumns(10);
		
		textFieldTraderId = new JTextField();
		textFieldTraderId.setBounds(130, 222, 230, 20);
		panelTraderInfo.add(textFieldTraderId);
		textFieldTraderId.setColumns(10);
	}
}
