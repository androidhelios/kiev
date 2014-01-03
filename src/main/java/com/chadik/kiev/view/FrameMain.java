package com.chadik.kiev.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.chadik.kiev.view.panel.IPanelCustomer;
import com.chadik.kiev.view.panel.IPanelTrader;

@Component
public class FrameMain extends JFrame {

	private JPanel contentPane;
	
	@Autowired
	private IPanelTrader panelTraderImpl;
	
	@Autowired
	private IPanelCustomer panelCustomerImpl;
	
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
		contentPane.add(panelCustomerImpl.initPanel(), BorderLayout.CENTER);		
        
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
		
	}
	

}
