package com.chadik.kiev.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class FrameMain extends JFrame {

	private JPanel contentPane;
	
//	@Autowired
//	@Qualifier("panelTrader")
//	private PanelTrader panelTrader;

	public void initFrameMain() {
		setTitle("kiev");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 500);
		
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
		setContentPane(contentPane);
//		traderPanel = new PanelTrader();
//		contentPane.add(panelTrader);
	}

}