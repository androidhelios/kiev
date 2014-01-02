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

//@Component
public class PanelTrader extends JPanel {
	
//	@Autowired
//	@Qualifier("traderJpaServiceImpl")
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
	
	@Autowired
	public PanelTrader(@Qualifier("traderJpaServiceImpl") IGenericJpaService traderJpaServiceImpl) {
		this.traderJpaServiceImpl = traderJpaServiceImpl;
		initPanelTrader();
	}
	
	public void initPanelTrader() {
		List<Trader> traders = new ArrayList<Trader>();
		traders = traderJpaServiceImpl.getAll();
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(800, 600));

		JPanel panelTableHolder = new JPanel();
		panelTableHolder.setBorder(new EmptyBorder(10, 10, 10, 10));
//		panelTraderTable.setBackground(Color.YELLOW);
		panelTableHolder.setPreferredSize(new Dimension(400, 600));
		add(panelTableHolder, BorderLayout.WEST);
		panelTableHolder.setLayout(new BorderLayout());

		JPanel panelTableHolderTable = new JPanel();
		panelTableHolderTable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelTableHolderTable.setPreferredSize(new Dimension(400, 540));
//		panelTraderTableHolder.setBackground(Color.ORANGE);
		panelTableHolder.add(panelTableHolderTable, BorderLayout.CENTER);
		panelTableHolderTable.setLayout(new BorderLayout());
		
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
		panelTableHolderTable.add(scrollPaneTable);

		JPanel panelTableHolderButtons = new JPanel();
		panelTableHolderButtons.setPreferredSize(new Dimension(400, 50));
//		panelTraderButtonHolder.setBackground(Color.PINK);
		panelTableHolder.add(panelTableHolderButtons, BorderLayout.SOUTH);

		JButton btnNewTrader = new JButton("Креирај");
		btnNewTrader.setPreferredSize(new Dimension(100,25));
		panelTableHolderButtons.add(btnNewTrader);
		btnNewTrader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				traderDialog.initDialogTrader();				
			}
		});

		JButton btnEditTrader = new JButton("Промени");
		btnEditTrader.setPreferredSize(new Dimension(100,25));
		panelTableHolderButtons.add(btnEditTrader);

		JButton btnDeleteTrader = new JButton("Избриши");
		btnDeleteTrader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				defaultTableModel.setRowCount(0);
				
			}
		});
		btnDeleteTrader.setPreferredSize(new Dimension(100,25));
		panelTableHolderButtons.add(btnDeleteTrader);

		JPanel panelInfoHolder = new JPanel();
		panelInfoHolder.setPreferredSize(new Dimension(400, 600));
//		panelTraderInfo.setBackground(Color.RED);
		add(panelInfoHolder, BorderLayout.CENTER);
		panelInfoHolder.setLayout(new BorderLayout(0, 0));
		
		JPanel panelInfoHolderContent = new JPanel();
		panelInfoHolder.add(panelInfoHolderContent);
		panelInfoHolderContent.setLayout(null);
		
		JLabel labelTraderName = new JLabel("Име:");
		panelInfoHolderContent.add(labelTraderName);
		
		JLabel labelTraderRergistryNumber = new JLabel("Регистарски Број:");
		panelInfoHolderContent.add(labelTraderRergistryNumber);
		
		JLabel labelTraderBankName = new JLabel("Банка:");
		panelInfoHolderContent.add(labelTraderBankName);
		
		JLabel labelTraderBankAccount = new JLabel("Банкарска Сметка:");
		panelInfoHolderContent.add(labelTraderBankAccount);
		
		JLabel labelTraderAddress = new JLabel("Адреса:");
		panelInfoHolderContent.add(labelTraderAddress);
		
		JLabel labelTraderPhoneNumber = new JLabel("Телефонски Број:");
		panelInfoHolderContent.add(labelTraderPhoneNumber);
		
		JLabel labelTraderEmailAddress = new JLabel("Email:");
		panelInfoHolder.add(labelTraderEmailAddress);
		
		JLabel labelTraderAdditionalInfo = new JLabel("Забелешка:");
		panelInfoHolder.add(labelTraderAdditionalInfo);
		
		JLabel labelTraderId = new JLabel("ID:");
		panelInfoHolder.add(labelTraderId);
		
		textFieldTraderName = new JTextField();
		panelInfoHolder.add(textFieldTraderName);
		textFieldTraderName.setColumns(10);
		
		textFieldTraderRegistryNumber = new JTextField();
		panelInfoHolder.add(textFieldTraderRegistryNumber);
		textFieldTraderRegistryNumber.setColumns(10);
		
		textFieldTraderBankName = new JTextField();
		panelInfoHolder.add(textFieldTraderBankName);
		textFieldTraderBankName.setColumns(10);
		
		textFieldTraderBankAccount = new JTextField();
		panelInfoHolder.add(textFieldTraderBankAccount);
		textFieldTraderBankAccount.setColumns(10);
		
		textFieldTraderEmailAddress = new JTextField();
		panelInfoHolder.add(textFieldTraderEmailAddress);
		textFieldTraderEmailAddress.setColumns(10);
		
		textFieldAdditionalInfo = new JTextField();
		panelInfoHolder.add(textFieldAdditionalInfo);
		textFieldAdditionalInfo.setColumns(10);
		
		textFieldTraderId = new JTextField();
		panelInfoHolder.add(textFieldTraderId);
		textFieldTraderId.setColumns(10);
		
		textFieldTraderPhoneNumber = new JTextField();
		panelInfoHolder.add(textFieldTraderPhoneNumber);
		textFieldTraderPhoneNumber.setColumns(10);
		
		textFieldTraderAddress = new JTextField();
		panelInfoHolder.add(textFieldTraderAddress);
		textFieldTraderAddress.setColumns(10);
	}
}
