package com.chadik.kiev.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chadik.kiev.util.PanelUtil;
import com.chadik.kiev.view.panel.IBankInfoPanel;
import com.chadik.kiev.view.panel.ICustomerPanel;
import com.chadik.kiev.view.panel.IInvoicePanel;
import com.chadik.kiev.view.panel.IPasswordPanel;
import com.chadik.kiev.view.panel.IProductPanel;
import com.chadik.kiev.view.panel.ISupplierPanel;

@Component
public class FrameMain {

	private JFrame mainFrame;

	private JMenuBar menuBar;

	private JMenu menuFile;
	private JMenu menuInvoice;

	private JMenuItem menuItemInvoice;
	private JMenuItem menuItemSupplier;
	private JMenuItem menuItemProduct;
	private JMenuItem menuItemCustomer;
	private JMenuItem menuItemBankInfo;
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
	@Autowired
	private IBankInfoPanel bankInfoPanelImpl;
	@Autowired
	private IPasswordPanel passwordPanelImpl;

	public JFrame initFrame() {
		mainFrame = new JFrame();
		mainFrame.setMinimumSize(new Dimension(1000, 600));

		ImageIcon imageIcon = new ImageIcon("img/invoice.png");

		mainFrame.setIconImage(imageIcon.getImage());

		menuBar = new JMenuBar();

		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.setPreferredSize(new Dimension(1000, 600));

		mainFrame.setTitle("ПСФ");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainFrame.setContentPane(contentPane);

		mainFrame.setJMenuBar(menuBar);

		menuFile = new JMenu("Датотека");
		menuFile.setEnabled(false);
		
		menuInvoice = new JMenu("ПСФ");
		menuInvoice.setEnabled(false);

		menuBar.add(menuFile);
		menuBar.add(menuInvoice);
		
		menuItemInvoice = new JMenuItem("     Фактури");
		menuItemInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelUtil.switchPanel(mainFrame, contentPane,
						invoicePanelImpl.initInvoicePanel(),
						BorderLayout.CENTER);

			}
		});

		menuItemSupplier = new JMenuItem("     Корисник");
		menuItemSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelUtil.switchPanel(mainFrame, contentPane,
						supplierPanelImpl.initSupplierPanel(),
						BorderLayout.CENTER);

			}
		});

		menuItemProduct = new JMenuItem("     Артикли");
		menuItemProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelUtil.switchPanel(mainFrame, contentPane,
						productPanelImpl.initProductPanel(),
						BorderLayout.CENTER);

			}
		});

		menuItemCustomer = new JMenuItem("     Клиенти");
		menuItemCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelUtil.switchPanel(mainFrame, contentPane,
						customerPanelImpl.initCustomerPanel(),
						BorderLayout.CENTER);

			}
		});

		menuItemBankInfo = new JMenuItem("     Сметки");
		menuItemBankInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelUtil.switchPanel(mainFrame, contentPane,
						bankInfoPanelImpl.initBankInfoPanel(),
						BorderLayout.CENTER);

			}
		});

		menuItemExit = new JMenuItem("     Излез");
		menuItemExit.setForeground(new Color(204, 0, 0));
		menuItemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		menuInvoice.add(menuItemInvoice);
		menuInvoice.add(menuItemCustomer);
		menuInvoice.add(menuItemProduct);
		menuInvoice.add(menuItemBankInfo);
		menuInvoice.addSeparator();
		menuInvoice.add(menuItemSupplier);
		
//		menuFile.addSeparator();
		menuFile.add(menuItemExit);

		supplierPanelImpl.initSupplierPanel();
		customerPanelImpl.initCustomerPanel();
		productPanelImpl.initProductPanel();
		bankInfoPanelImpl.initBankInfoPanel();
		invoicePanelImpl.initInvoicePanel();

		mainFrame.add(passwordPanelImpl.initPasswordPanel());

		mainFrame.pack();
		mainFrame.setVisible(true);
		mainFrame.setLocationRelativeTo(null);

		return mainFrame;

	}

	public void clearContentPane() {
		menuFile.setEnabled(true);
		menuInvoice.setEnabled(true);
		contentPane.removeAll();
		contentPane.validate();
		contentPane.repaint();
	}

	public JFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

}
