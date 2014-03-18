package com.chadik.kiev.view.dialog.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import com.chadik.kiev.model.BankInfo;
import com.chadik.kiev.service.IBankInfoService;
import com.chadik.kiev.view.FrameMain;
import com.chadik.kiev.view.dialog.IBankInfoDialog;
import com.chadik.kiev.view.panel.IBankInfoPanel;

@Component
public class BankInfoDialogImpl implements IBankInfoDialog {

	private JDialog dialog;
	private JPanel contentPane;

	private JPanel panelAll;
	private JPanel panelFields;
	private JPanel panelFieldsContent;
	private JPanel panelButtons;
	private JPanel panelButtonsContent;

	private JLabel labelBankInfoId;
	private JLabel labelBankInfoName;
	private JLabel labelBankInfoAccount;
	private JLabel labelBankInfoAddress;
	private JLabel labelBankInfoPhoneNumber;
	private JLabel labelBankInfoEmail;
	private JLabel labelBankInfoAdditionalInfo;

	private JTextField textFieldBankInfoId;
	private JTextField textFieldBankInfoName;
	private JTextField textFieldBankInfoAccount;
	private JTextField textFieldBankInfoAddress;
	private JTextField textFieldBankInfoPhoneNumber;
	private JTextField textFieldBankInfoEmail;
	private JTextField textFieldBankInfoAdditionalInfo;

	private JButton buttonSave;
	private JButton buttonCancel;

	private Color mandatoryTextFieldColor;

	@Autowired
	private IBankInfoService bankInfoServiceImpl;
	@Autowired
	private IBankInfoPanel bankInfoPanelImpl;
	@Autowired
	private FrameMain frameMain;
	
	private JFrame frame;

	@Override
	public JDialog initBankInfoDialog() {
		dialog = new JDialog(frameMain.getMainFrame(), true);
		dialog.setTitle("Новa Сметка");
		dialog.setResizable(false);

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

		labelBankInfoName = new JLabel("Име:");
		labelBankInfoName.setBounds(xLabel, y, weightLabel, height);
		labelBankInfoName.setForeground(mandatoryTextFieldColor);

		textFieldBankInfoName = new JTextField();
		textFieldBankInfoName.setBounds(xTextField, y, weightTextField, height);
		textFieldBankInfoName.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;
		
		labelBankInfoAccount = new JLabel("Сметка:");
		labelBankInfoAccount.setBounds(xLabel, y, weightLabel, height);
		labelBankInfoAccount.setForeground(mandatoryTextFieldColor);

		textFieldBankInfoAccount = new JTextField();
		textFieldBankInfoAccount.setBounds(xTextField, y, weightTextField, height);
		textFieldBankInfoAccount.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelBankInfoAddress = new JLabel("Адреса:");
		labelBankInfoAddress.setBounds(xLabel, y, weightLabel, height);

		textFieldBankInfoAddress = new JTextField();
		textFieldBankInfoAddress.setBounds(xTextField, y, weightTextField,
				height);
		textFieldBankInfoAddress.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelBankInfoPhoneNumber = new JLabel("Телефонски Број:");
		labelBankInfoPhoneNumber.setBounds(xLabel, y, weightLabel, height);

		textFieldBankInfoPhoneNumber = new JTextField();
		textFieldBankInfoPhoneNumber.setBounds(xTextField, y, weightTextField,
				height);
		textFieldBankInfoPhoneNumber.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelBankInfoEmail = new JLabel("Email:");
		labelBankInfoEmail.setBounds(xLabel, y, weightLabel, height);

		textFieldBankInfoEmail = new JTextField();
		textFieldBankInfoEmail
				.setBounds(xTextField, y, weightTextField, height);
		textFieldBankInfoEmail.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelBankInfoAdditionalInfo = new JLabel("Забелешки:");
		labelBankInfoAdditionalInfo.setBounds(xLabel, y, weightLabel, height);

		textFieldBankInfoAdditionalInfo = new JTextField();
		textFieldBankInfoAdditionalInfo.setBounds(xTextField, y,
				weightTextField, height);
		textFieldBankInfoAdditionalInfo.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelBankInfoId = new JLabel("ID:");
		labelBankInfoId.setBounds(xLabel, y, weightLabel, height);

		textFieldBankInfoId = new JTextField();
		textFieldBankInfoId.setBounds(xTextField, y, weightTextField, height);
		textFieldBankInfoId.setMargin(new Insets(2, 2, 2, 2));

		buttonSave = new JButton("Зачувај");
		buttonSave.setPreferredSize(new Dimension(100, 25));
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validateBankInfoFields()) {
					saveBankInfoAndDispose();
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
				bankInfoPanelImpl.setBankInfoTableButtonsEnabled();
			}
		});

		panelFieldsContent.add(labelBankInfoName);
		panelFieldsContent.add(textFieldBankInfoName);
		
		panelFieldsContent.add(labelBankInfoAccount);
		panelFieldsContent.add(textFieldBankInfoAccount);

		panelFieldsContent.add(labelBankInfoAddress);
		panelFieldsContent.add(textFieldBankInfoAddress);

		panelFieldsContent.add(labelBankInfoPhoneNumber);
		panelFieldsContent.add(textFieldBankInfoPhoneNumber);

		panelFieldsContent.add(labelBankInfoEmail);
		panelFieldsContent.add(textFieldBankInfoEmail);

		panelFieldsContent.add(labelBankInfoAdditionalInfo);
		panelFieldsContent.add(textFieldBankInfoAdditionalInfo);

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

	public BankInfo getBankInfoFromBankInfoFields() {
		BankInfo bankInfo = new BankInfo();
		bankInfo.setBankInfoName(textFieldBankInfoName.getText());
		bankInfo.setBankInfoAccount(textFieldBankInfoAccount.getText());
		bankInfo.setBankInfoAddress(textFieldBankInfoAddress.getText());
		bankInfo.setBankInfoPhoneNumber(textFieldBankInfoPhoneNumber.getText());
		bankInfo.setBankInfoEmail(textFieldBankInfoEmail.getText());
		bankInfo.setBankInfoAdditionalInfo(textFieldBankInfoAdditionalInfo
				.getText());

		return bankInfo;
	}

	public boolean validateBankInfoFields() {
		boolean result = true;
		result = result && (!"".equals(textFieldBankInfoName.getText()))
				&& (!"".equals(textFieldBankInfoAccount.getText()));
		return result;
	}

	public void saveBankInfoAndDispose() {
		BankInfo bankInfo = getBankInfoFromBankInfoFields();
		bankInfoServiceImpl.saveBankInfo(bankInfo);
		dialog.dispose();
		bankInfoPanelImpl.populateBankInfoTable();
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
