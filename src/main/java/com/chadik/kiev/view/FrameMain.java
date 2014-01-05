package com.chadik.kiev.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chadik.kiev.util.PanelUtil;
import com.chadik.kiev.view.dialog.ITraderDialog;
import com.chadik.kiev.view.panel.ICustomerPanel;
import com.chadik.kiev.view.panel.IProductPanel;
import com.chadik.kiev.view.panel.ITraderPanel;

@Component
public class FrameMain {
	
	private JFrame mainFrame;

	private JMenuBar menuBar;
	
	private JMenu menuFile;
	private JMenu menuTrader;
	private JMenu menuProduct;
	private JMenu menuCustomer;
	
	private JMenuItem menuItemExit;
	private JMenuItem menuItemTrader;
	private JMenuItem menuItemTraderNew;
	private JMenuItem menuItemProduct;
	private JMenuItem menuItemProductNew;
	private JMenuItem menuItemCustomer;
	private JMenuItem menuItemCustomerNew;
	
	private JPanel contentPane;
	
	@Autowired
	private ITraderPanel panelTraderImpl;	
	@Autowired
	private ICustomerPanel panelCustomerImpl;	
	@Autowired
	private IProductPanel panelProductImpl;
	
	@Autowired
	private ITraderDialog dialogTraderImpl;
	
	public JFrame initFrame() {
		mainFrame = new JFrame();
		
		menuBar = new JMenuBar();
		
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.setPreferredSize(new Dimension(800, 600));
		
		mainFrame.setTitle("kiev");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainFrame.setContentPane(contentPane);
		
		mainFrame.setJMenuBar(menuBar);
		
		menuFile = new JMenu("Датотека");
		menuTrader = new JMenu("Корисник");
		menuProduct = new JMenu("Продукт");
		menuCustomer = new JMenu("Клиент");
		
		menuBar.add(menuFile);
		menuBar.add(menuTrader);
		menuBar.add(menuProduct);
		menuBar.add(menuCustomer);
		
		menuItemExit = new JMenuItem("Излез");
		menuItemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		menuItemTrader = new JMenuItem("Прегледај корисници");
		menuItemTrader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelUtil.switchPanel(mainFrame, contentPane, panelTraderImpl.initTraderPanel(), BorderLayout.CENTER);
				
			}
		});
		
		menuItemTraderNew = new JMenuItem("Нов корисник");
		menuItemTraderNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialogTraderImpl.initTraderDialog();
			}
		});
		
		menuItemProduct = new JMenuItem("Прегледај продукти");
		menuItemProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelUtil.switchPanel(mainFrame, contentPane, panelProductImpl.initProductPanel(), BorderLayout.CENTER);
				
			}
		});
		
		menuItemProductNew = new JMenuItem("Нов продукт");
		menuItemProductNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialogTraderImpl.initTraderDialog();
			}
		});
		
		menuItemCustomer = new JMenuItem("Прегледај клиенти");
		menuItemCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelUtil.switchPanel(mainFrame, contentPane, panelCustomerImpl.initCustomerPanel(), BorderLayout.CENTER);
				
			}
		});
		
		menuItemCustomerNew = new JMenuItem("Нов клиент");
		
		menuFile.add(menuItemExit);
		
		menuTrader.add(menuItemTrader);
		menuTrader.add(menuItemTraderNew);	
		menuCustomer.add(menuItemCustomer);
		menuCustomer.add(menuItemCustomerNew);
		menuProduct.add(menuItemProduct);
		menuProduct.add(menuItemProductNew);
		
		panelTraderImpl.initTraderPanel();
		panelCustomerImpl.initCustomerPanel();
		panelProductImpl.initProductPanel();	
        
		mainFrame.pack();
		mainFrame.setVisible(true);
		mainFrame.setLocationRelativeTo(null);
		
		return mainFrame;
		
	}
	

}
