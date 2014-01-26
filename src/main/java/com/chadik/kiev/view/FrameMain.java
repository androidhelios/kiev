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
import com.chadik.kiev.view.dialog.ISupplierDialog;
import com.chadik.kiev.view.panel.ICustomerPanel;
import com.chadik.kiev.view.panel.IInvoicePanel;
import com.chadik.kiev.view.panel.IProductPanel;
import com.chadik.kiev.view.panel.ISupplierPanel;

@Component
public class FrameMain {
	
	private JFrame mainFrame;

	private JMenuBar menuBar;
	
	private JMenu menuFile;
	
	private JMenuItem menuItemInvoice;
	private JMenuItem menuItemSupplier;
	private JMenuItem menuItemProduct;
	private JMenuItem menuItemCustomer;
	private JMenuItem menuItemExit;
	
	private JPanel contentPane;
	
	@Autowired
	private ISupplierPanel supplierPanelImpl;	
	@Autowired
	private ICustomerPanel customerPanelImpl;	
	@Autowired
	private IProductPanel productPanelImpl;
	@Autowired
	private IInvoicePanel invoicePanelImpl;
	
	public JFrame initFrame() {
		mainFrame = new JFrame();
		mainFrame.setMinimumSize(new Dimension(1000, 600));
		
		menuBar = new JMenuBar();
		
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.setPreferredSize(new Dimension(1000, 600));
		
		mainFrame.setTitle("kiev");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainFrame.setContentPane(contentPane);
		
		mainFrame.setJMenuBar(menuBar);
		
		menuFile = new JMenu("Датотека");
		
		menuBar.add(menuFile);
		
		menuItemInvoice = new JMenuItem("Фактури");
		menuItemInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelUtil.switchPanel(mainFrame, contentPane, invoicePanelImpl.initInvoicePanel(), BorderLayout.CENTER);
				
			}
		});
		
		menuItemSupplier = new JMenuItem("Корисници");
		menuItemSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelUtil.switchPanel(mainFrame, contentPane, supplierPanelImpl.initSupplierPanel(), BorderLayout.CENTER);
				
			}
		});
		
		menuItemProduct = new JMenuItem("Продукти");
		menuItemProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelUtil.switchPanel(mainFrame, contentPane, productPanelImpl.initProductPanel(), BorderLayout.CENTER);
				
			}
		});
		
		menuItemCustomer = new JMenuItem("Клиенти");
		menuItemCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelUtil.switchPanel(mainFrame, contentPane, customerPanelImpl.initCustomerPanel(), BorderLayout.CENTER);
				
			}
		});
		
		menuItemExit = new JMenuItem("Излез");
		menuItemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		menuFile.add(menuItemInvoice);
		menuFile.add(menuItemSupplier);
		menuFile.add(menuItemProduct);
		menuFile.add(menuItemCustomer);
		menuFile.addSeparator();
		menuFile.add(menuItemExit);
		
		supplierPanelImpl.initSupplierPanel();
		customerPanelImpl.initCustomerPanel();
		productPanelImpl.initProductPanel();	
        
		mainFrame.pack();
		mainFrame.setVisible(true);
		mainFrame.setLocationRelativeTo(null);
		
		return mainFrame;
		
	}	

}
