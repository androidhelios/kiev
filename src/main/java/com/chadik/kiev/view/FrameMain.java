package com.chadik.kiev.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JSeparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.chadik.kiev.model.Trader;
import com.chadik.kiev.service.IGenericJpaService;
import com.chadik.kiev.service.ITraderJpaService;

@Component
public class FrameMain extends JFrame {

	private JPanel contentPane;
	
	@Autowired
	@Qualifier("panelTraderImpl")
	private IPanelTrader panelTraderImpl;
	
//	@Autowired
//	private ITraderJpaService traderJpaServiceImpl;
	
	private PanelTrader panelTrader;
	
	public void initFrameMain() {
		setTitle("kiev");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuFile = new JMenu("File");
		menuBar.add(menuFile);
		
		JMenuItem menuItemExit = new JMenuItem("Exit");
		menuItemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JMenuItem menuItemTrader = new JMenuItem("Trader");
		menuFile.add(menuItemTrader);
		
		JSeparator menuItemSeparator = new JSeparator();
		menuFile.add(menuItemSeparator);
		
		menuFile.add(menuItemExit);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
//		panelTraderImpl.initPanel();
//		panelTrader = new PanelTrader(traderJpaServiceImpl);
		contentPane.add(panelTraderImpl.initPanel(), BorderLayout.CENTER);		
        
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
		
	}
	

}
