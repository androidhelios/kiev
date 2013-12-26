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

@Component
public class PanelTrader extends JPanel {
	private IGenericJpaService traderJpaServiceImpl;
	private JTable tableTrader;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private DefaultTableModel defaultTableModel;

	@Autowired
	public PanelTrader(
			@Qualifier("traderJpaServiceImpl") IGenericJpaService traderJpaServiceImpl) {
		this.traderJpaServiceImpl = traderJpaServiceImpl;
        System.out.println("hello paneltrader");
        
        initbre();

	}
	
	public void initbre() {
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
		panelTraderInfo.setBorder(new EmptyBorder(10, 1, 10, 10));
		panelTraderInfo.setPreferredSize(new Dimension(400, 600));
//		panelTraderInfo.setBackground(Color.RED);
		add(panelTraderInfo, BorderLayout.CENTER);
		panelTraderInfo.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(10, 11, 94, 14);
		panelTraderInfo.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(10, 37, 94, 14);
		panelTraderInfo.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(10, 62, 94, 14);
		panelTraderInfo.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(10, 87, 94, 14);
		panelTraderInfo.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(10, 112, 94, 14);
		panelTraderInfo.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setBounds(10, 137, 94, 14);
		panelTraderInfo.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setBounds(10, 162, 94, 14);
		panelTraderInfo.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("New label");
		lblNewLabel_7.setBounds(10, 187, 94, 14);
		panelTraderInfo.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("New label");
		lblNewLabel_8.setBounds(10, 221, 94, 14);
		panelTraderInfo.add(lblNewLabel_8);
		
		textField = new JTextField();
		textField.setBounds(114, 11, 160, 20);
		panelTraderInfo.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(114, 37, 160, 20);
		panelTraderInfo.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(114, 62, 160, 20);
		panelTraderInfo.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(114, 87, 160, 20);
		panelTraderInfo.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(114, 112, 160, 20);
		panelTraderInfo.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(114, 137, 160, 20);
		panelTraderInfo.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(114, 162, 160, 20);
		panelTraderInfo.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(114, 190, 160, 20);
		panelTraderInfo.add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setBounds(114, 218, 160, 20);
		panelTraderInfo.add(textField_8);
		textField_8.setColumns(10);
	}
}
