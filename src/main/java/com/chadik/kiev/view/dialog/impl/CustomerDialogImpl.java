package com.chadik.kiev.view.dialog.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chadik.kiev.model.Customer;
import com.chadik.kiev.service.ICustomerService;
import com.chadik.kiev.view.FrameMain;
import com.chadik.kiev.view.dialog.ICustomerDialog;
import com.chadik.kiev.view.panel.ICustomerPanel;

@Component
public class CustomerDialogImpl implements ICustomerDialog {

	private JDialog dialog;
	private JPanel contentPane;

	private JPanel panelAll;
	private JPanel panelFields;
	private JPanel panelFieldsContent;
	private JPanel panelButtons;
	private JPanel panelButtonsContent;

	private JLabel labelCustomerId;
	private JLabel labelCustomerName;
	private JLabel labelCustomerAddress;
	private JLabel labelCustomerPhoneNumber;
	private JLabel labelCustomerEmail;
	private JLabel labelCustomerAdditionalInfo;

	private JTextField textFieldCustomerId;
	private JTextField textFieldCustomerName;
	private JTextField textFieldCustomerAddress;
	private JTextField textFieldCustomerPhoneNumber;
	private JTextField textFieldCustomerEmail;
	private JTextField textFieldCustomerAdditionalInfo;

	private JButton buttonSave;
	private JButton buttonCancel;

	private Color mandatoryTextFieldColor;

	@Autowired
	private ICustomerService customerServiceImpl;
	@Autowired
	private ICustomerPanel customerPanelImpl;
	@Autowired
	private FrameMain frameMain;
	
	private JFrame frame;

	@Override
	public JDialog initCustomerDialog() {
		dialog = new JDialog(frameMain.getMainFrame(), true);
		dialog.setTitle("Нов Клиент");
		dialog.setResizable(false);
		dialog.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
			}

			public void windowClosing(WindowEvent e) {
				customerPanelImpl.setCustomerTableButtonsEnabled();
			}
		});

		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());

		dialog.setContentPane(contentPane);

		panelAll = new JPanel();
		panelAll.setLayout(new BorderLayout());

		panelFields = new JPanel();
		panelFields.setLayout(new BorderLayout());
		panelFields.setBorder(BorderFactory.createCompoundBorder(
				new EmptyBorder(10, 10, 10, 10), new EtchedBorder()));

		panelFieldsContent = new JPanel();
		panelFieldsContent.setLayout(null);
		panelFieldsContent.setBackground(new Color(192, 192, 192));
		panelFieldsContent.setPreferredSize(new Dimension(400, 300));

		panelButtons = new JPanel();
		panelButtons.setLayout(new BorderLayout());

		panelButtonsContent = new JPanel();
		panelButtonsContent.setLayout(new FlowLayout());
		panelButtonsContent.setPreferredSize(new Dimension(400, 50));

		int spacing = 5;
		int weightLabel = 125;
		int height = 25;
		int weightTextField = 250;
		int xLabel = 10;
		int xTextField = xLabel + weightLabel + spacing;
		int y = 25;

		mandatoryTextFieldColor = new Color(204, 0, 0);

		labelCustomerName = new JLabel("Име:");
		labelCustomerName.setBounds(xLabel, y, weightLabel, height);
		labelCustomerName.setForeground(mandatoryTextFieldColor);

		textFieldCustomerName = new JTextField();
		textFieldCustomerName.setBounds(xTextField, y, weightTextField, height);
		textFieldCustomerName.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelCustomerAddress = new JLabel("Адреса:");
		labelCustomerAddress.setBounds(xLabel, y, weightLabel, height);
		labelCustomerAddress.setForeground(mandatoryTextFieldColor);

		textFieldCustomerAddress = new JTextField();
		textFieldCustomerAddress.setBounds(xTextField, y, weightTextField,
				height);
		textFieldCustomerAddress.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelCustomerPhoneNumber = new JLabel("Телефонски Број:");
		labelCustomerPhoneNumber.setBounds(xLabel, y, weightLabel, height);

		textFieldCustomerPhoneNumber = new JTextField();
		textFieldCustomerPhoneNumber.setBounds(xTextField, y, weightTextField,
				height);
		textFieldCustomerPhoneNumber.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelCustomerEmail = new JLabel("Email:");
		labelCustomerEmail.setBounds(xLabel, y, weightLabel, height);

		textFieldCustomerEmail = new JTextField();
		textFieldCustomerEmail
				.setBounds(xTextField, y, weightTextField, height);
		textFieldCustomerEmail.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelCustomerAdditionalInfo = new JLabel("Забелешки:");
		labelCustomerAdditionalInfo.setBounds(xLabel, y, weightLabel, height);

		textFieldCustomerAdditionalInfo = new JTextField();
		textFieldCustomerAdditionalInfo.setBounds(xTextField, y,
				weightTextField, height);
		textFieldCustomerAdditionalInfo.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelCustomerId = new JLabel("ID:");
		labelCustomerId.setBounds(xLabel, y, weightLabel, height);

		textFieldCustomerId = new JTextField();
		textFieldCustomerId.setBounds(xTextField, y, weightTextField, height);
		textFieldCustomerId.setMargin(new Insets(2, 2, 2, 2));

		buttonSave = new JButton("Зачувај");
		buttonSave.setPreferredSize(new Dimension(100, 25));
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validateCustomerFields()) {
					saveCustomerAndDispose();
					JOptionPane.showMessageDialog(frameMain.getMainFrame(), "Клиентот е запишан",
							"Информација", JOptionPane.INFORMATION_MESSAGE);
				} else {
					dialog.setVisible(false);

					Object[] options = { "OK" };
					int input = JOptionPane.showOptionDialog(null,
							"Погрешен внес", "Грешка",
							JOptionPane.ERROR_MESSAGE,
							JOptionPane.ERROR_MESSAGE, null, options,
							options[0]);

					if (input == 0) {
						dialog.setVisible(true);
					}
				}

			}
		});

		buttonCancel = new JButton("Откажи");
		buttonCancel.setPreferredSize(new Dimension(100, 25));
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				customerPanelImpl.setCustomerTableButtonsEnabled();
			}
		});

		panelFieldsContent.add(labelCustomerName);
		panelFieldsContent.add(textFieldCustomerName);

		panelFieldsContent.add(labelCustomerAddress);
		panelFieldsContent.add(textFieldCustomerAddress);

		panelFieldsContent.add(labelCustomerPhoneNumber);
		panelFieldsContent.add(textFieldCustomerPhoneNumber);

		panelFieldsContent.add(labelCustomerEmail);
		panelFieldsContent.add(textFieldCustomerEmail);

		panelFieldsContent.add(labelCustomerAdditionalInfo);
		panelFieldsContent.add(textFieldCustomerAdditionalInfo);

		panelButtonsContent.add(buttonSave);
		panelButtonsContent.add(buttonCancel);

		panelFields.add(panelFieldsContent, BorderLayout.CENTER);

		panelButtons.add(panelButtonsContent, BorderLayout.CENTER);

		panelAll.add(panelButtons, BorderLayout.SOUTH);
		panelAll.add(panelFields, BorderLayout.CENTER);

		contentPane.add(panelAll);

		dialog.pack();
		dialog.setLocationRelativeTo(frameMain.getMainFrame());
		dialog.setVisible(true);

		return dialog;
	}

	public Customer getCustomerFromCustomerFields() {
		Customer customer = new Customer();
		customer.setCustomerName(textFieldCustomerName.getText());
		customer.setCustomerAddress(textFieldCustomerAddress.getText());
		customer.setCustomerPhoneNumber(textFieldCustomerPhoneNumber.getText());
		customer.setCustomerEmail(textFieldCustomerEmail.getText());
		customer.setCustomerAdditionalInfo(textFieldCustomerAdditionalInfo
				.getText());

		return customer;
	}

	public boolean validateCustomerFields() {
		boolean result = true;
		result = result && (!"".equals(textFieldCustomerName.getText()))
				&& (!"".equals(textFieldCustomerAddress.getText()));
		return result;
	}

	public void saveCustomerAndDispose() {
		Customer customer = getCustomerFromCustomerFields();
		customerServiceImpl.saveCustomer(customer);
		dialog.dispose();
		customerPanelImpl.populateCustomerTable();
	}

	public void dispose() {
		dialog.dispose();
	}

	@Override
	public JFrame getFrame() {
		return frame;
	}

	@Override
	public void seFrame(JFrame mainFrame) {
		this.frame = mainFrame;
		
	}

}
