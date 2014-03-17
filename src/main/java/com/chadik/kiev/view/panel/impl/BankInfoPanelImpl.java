package com.chadik.kiev.view.panel.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chadik.kiev.model.BankInfo;
import com.chadik.kiev.service.IBankInfoService;
import com.chadik.kiev.util.PanelUtil;
import com.chadik.kiev.util.TableUtil;
import com.chadik.kiev.view.dialog.IBankInfoDialog;
import com.chadik.kiev.view.panel.IBankInfoPanel;

@Component
public class BankInfoPanelImpl implements IBankInfoPanel {
	
	private JPanel panelAll;

	private JPanel panelTableHolder;
	private JPanel panelTableHolderContent;
	private JPanel panelTableHolderContentTable;
	private JPanel panelTableHolderContentButtons;

	private JPanel panelInfoHolder;
	private JPanel panelInfoHolderContent;
	private JPanel panelInfoHolderContentInfo;
	private JPanel panelInfoHolderContentButtons;

	private JScrollPane scrollPaneTable;
	private JScrollBar verticalScrollBar;

	private DefaultTableModel defaultTableModel;
	private JTable table;

	private JLabel labelBankInfoId;
	private JLabel labelBankInfoName;
	private JLabel labelBankInfoAddress;
	private JLabel labelBankInfoPhoneNumber;
	private JLabel labelBankInfoEmail;
	private JLabel labelBankInfoAdditionalInfo;

	private JTextField textFieldBankInfoId;
	private JTextField textFieldBankInfoName;
	private JTextField textFieldBankInfoAddress;
	private JTextField textFieldBankInfoPhoneNumber;
	private JTextField textFieldBankInfoEmail;
	private JTextField textFieldBankInfoAdditionalInfo;

	private JButton buttonNew;
	private JButton buttonEdit;
	private JButton buttonDelete;
	private JButton buttonSave;
	private JButton buttonCancel;

	private Color originalTextFieldColor;
	private Color nonEditableTextFieldColor;
	private Color mandatoryTextFieldColor;

	private String selectedBankInfoTableRow;
	
	private boolean editMode;

	private List<BankInfo> banksInfo;

	@Autowired
	private IBankInfoService bankInfoServiceImpl;
	@Autowired
	private IBankInfoDialog bankInfoDialogImpl;

	@Override
	public JPanel initBankInfoPanel() {
		panelAll = new JPanel();
		panelAll.setLayout(new BorderLayout());

		panelTableHolder = new JPanel();
		panelTableHolder.setLayout(new BorderLayout());

		panelTableHolderContent = new JPanel();
		panelTableHolderContent.setLayout(new BorderLayout());

		panelTableHolderContentTable = new JPanel();
		panelTableHolderContentTable.setLayout(new BorderLayout());
		panelTableHolderContentTable.setPreferredSize(new Dimension(400, 550));
		panelTableHolderContentTable.setBackground(new Color(224, 224, 224));
		panelTableHolderContentTable.setBorder(new TitledBorder(
				"Листа на сметки"));

		panelTableHolderContentButtons = new JPanel();
		panelTableHolderContentButtons.setLayout(new FlowLayout());
		panelTableHolderContentButtons.setPreferredSize(new Dimension(400, 50));
		panelTableHolderContentButtons.setBorder(new EmptyBorder(5, 5, 5, 5));

		panelInfoHolder = new JPanel();
		panelInfoHolder.setLayout(new BorderLayout());
		panelInfoHolder.setBackground(new Color(224, 224, 224));

		panelInfoHolderContent = new JPanel();
		panelInfoHolderContent.setLayout(new BorderLayout());

		panelInfoHolderContentInfo = new JPanel();
		panelInfoHolderContentInfo.setLayout(new GridBagLayout());
		panelInfoHolderContentInfo.setPreferredSize(new Dimension(400, 550));
		panelInfoHolderContentInfo.setBackground(new Color(192, 192, 192));
		panelInfoHolderContentInfo.setBorder(BorderFactory
				.createCompoundBorder(new EmptyBorder(10, 10, 10, 10),
						new EtchedBorder()));

		panelInfoHolderContentButtons = new JPanel();
		panelInfoHolderContentButtons.setLayout(new FlowLayout());
		panelInfoHolderContentButtons.setPreferredSize(new Dimension(400, 50));
		panelInfoHolderContentButtons.setBorder(new EmptyBorder(5, 5, 5, 5));

		defaultTableModel = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		defaultTableModel.setColumnIdentifiers(getTableBankInfoColumnNames());

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (isEditMode()) {
					table.setEnabled(false);
				} else {
				int row = table.getSelectedRow();
				String selectedRowBankInfoId = (String) table
						.getValueAt(row, 1);
				BankInfo bankInfo = getBankInfoFromBankInfoTable(selectedRowBankInfoId);
				populateBankInfoFields(bankInfo);
				setBankInfoInfoButtonsDisabled();
				}
			}
		});

		table.setModel(defaultTableModel);

		TableUtil.hideColumns(table, getTableBankInfoHiddenColumns());

		TableUtil.allignCells(table, SwingConstants.CENTER);

		table.getColumnModel().getColumn(0).setMaxWidth(100);

		scrollPaneTable = new JScrollPane(table);

		verticalScrollBar = scrollPaneTable.getVerticalScrollBar();
		
		mandatoryTextFieldColor = new Color(204, 0, 0);

		buttonNew = new JButton("Креирај");
		buttonNew.setPreferredSize(new Dimension(100, 25));
		buttonNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAllButtonsDisabled();
				bankInfoDialogImpl.initBankInfoDialog();
			}
		});

		buttonEdit = new JButton("Измени");
		buttonEdit.setPreferredSize(new Dimension(100, 25));
		buttonEdit.setEnabled(false);
		buttonEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setBankInfoFieldsEditable();
				setBankInfoInfoButtonsEnabled();
				buttonNew.setEnabled(false);
				setEditMode(true);
				table.setEnabled(false);
			}
		});

		buttonDelete = new JButton("Избриши");
		buttonDelete.setForeground(mandatoryTextFieldColor);
		buttonDelete.setPreferredSize(new Dimension(100, 25));
		buttonDelete.setEnabled(false);
		buttonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteBankInfo();
			}
		});

		int spacing = 5;
		int weightLabel = 125;
		int height = 25;
		int weightTextField = 250;
		int xLabel = 10;
		int xTextField = xLabel + weightLabel + spacing;
		int y = 25;

		labelBankInfoName = new JLabel("Име:");
		labelBankInfoName.setBounds(xLabel, y, weightLabel, height);
		labelBankInfoName.setForeground(mandatoryTextFieldColor);

		textFieldBankInfoName = new JTextField();
		textFieldBankInfoName.setBounds(xTextField, y, weightTextField, height);
		textFieldBankInfoName.setMargin(new Insets(2, 2, 2, 2));

		originalTextFieldColor = textFieldBankInfoName.getBackground();
		nonEditableTextFieldColor = new Color(255, 255, 204);

		y = y + height + spacing;

		labelBankInfoAddress = new JLabel("Адреса:");
		labelBankInfoAddress.setBounds(xLabel, y, weightLabel, height);
		labelBankInfoAddress.setForeground(mandatoryTextFieldColor);

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
		buttonSave.setEnabled(false);
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validateBankInfoFields()) {
					saveBankInfo();
					setEditMode(false);
					table.setEnabled(true);
				} else {
					Object[] options = { "OK" };
					int input = JOptionPane.showOptionDialog(null,
							"Погрешен внес", "Грешка",
							JOptionPane.ERROR_MESSAGE,
							JOptionPane.ERROR_MESSAGE, null, options,
							options[0]);
				}
			}
		});

		buttonCancel = new JButton("Откажи");
		buttonCancel.setPreferredSize(new Dimension(100, 25));
		buttonCancel.setEnabled(false);
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String selectedRowBankInfoId = (String) table
						.getValueAt(row, 1);
				BankInfo bankInfo = getBankInfoFromBankInfoTable(selectedRowBankInfoId);
				populateBankInfoFields(bankInfo);
				setBankInfoFieldsNonEditable();
				setBankInfoInfoButtonsDisabled();
				setEditMode(false);
				table.setEnabled(true);
			}
		});

		panelTableHolderContentTable.add(scrollPaneTable);

		panelTableHolderContentButtons.add(buttonNew);
		panelTableHolderContentButtons.add(buttonEdit);
		panelTableHolderContentButtons.add(PanelUtil.createJSeparator());
		panelTableHolderContentButtons.add(buttonDelete);

		panelTableHolderContent.add(panelTableHolderContentTable,
				BorderLayout.CENTER);
		panelTableHolderContent.add(panelTableHolderContentButtons,
				BorderLayout.SOUTH);

		panelTableHolder.add(panelTableHolderContent);

		panelInfoHolderContentInfo.add(labelBankInfoName,
				firstLabelConstrains());
		panelInfoHolderContentInfo.add(textFieldBankInfoName,
				textFieldConstraints());

		panelInfoHolderContentInfo
				.add(labelBankInfoAddress, labelConstraints());
		panelInfoHolderContentInfo.add(textFieldBankInfoAddress,
				textFieldConstraints());

		panelInfoHolderContentInfo.add(labelBankInfoPhoneNumber,
				labelConstraints());
		panelInfoHolderContentInfo.add(textFieldBankInfoPhoneNumber,
				textFieldConstraints());

		panelInfoHolderContentInfo.add(labelBankInfoEmail, labelConstraints());
		panelInfoHolderContentInfo.add(textFieldBankInfoEmail,
				textFieldConstraints());

		panelInfoHolderContentInfo.add(labelBankInfoAdditionalInfo,
				labelConstraints());
		panelInfoHolderContentInfo.add(textFieldBankInfoAdditionalInfo,
				lastComponentConstrains());

		panelInfoHolderContentButtons.add(buttonSave);
		panelInfoHolderContentButtons.add(buttonCancel);

		panelInfoHolderContent.add(panelInfoHolderContentInfo,
				BorderLayout.CENTER);
		panelInfoHolderContent.add(panelInfoHolderContentButtons,
				BorderLayout.SOUTH);

		panelInfoHolder.add(panelInfoHolderContent, BorderLayout.CENTER);

		panelAll.add(panelTableHolder, BorderLayout.WEST);
		panelAll.add(panelInfoHolder, BorderLayout.CENTER);

		setSelectedBankInfoTableRow("");
		populateBankInfoTable();

		return panelAll;
	}

	@Override
	public void populateBankInfoTable() {
		String selectedRowBankInfoId = "";
		banksInfo = bankInfoServiceImpl.findAllBanksInfo();

		int i = 0;

		defaultTableModel.setRowCount(0);

		for (BankInfo bankInfo : banksInfo) {
			defaultTableModel.addRow(new String[] { Integer.toString(++i),
					bankInfo.getBankInfoId().toString(),
					bankInfo.getBankInfoName(), bankInfo.getBankInfoAddress(),
					bankInfo.getBankInfoPhoneNumber(),
					bankInfo.getBankInfoEmail(),
					bankInfo.getBankInfoAdditionalInfo() });
		}

		if (table.getRowCount() > 0) {
			int selectedRow = table.getRowCount() - 1;

			if (!getSelectedBankInfoTableRow().equals("")) {
				selectedRow = Integer.parseInt(getSelectedBankInfoTableRow());
			}

			table.setRowSelectionInterval(selectedRow, selectedRow);

			selectedRowBankInfoId = (String) table.getValueAt(selectedRow, 1);
			BankInfo bankInfo = getBankInfoFromBankInfoTable(selectedRowBankInfoId);
			populateBankInfoFields(bankInfo);

			setBankInfoTableButtonsEnabled();

		}

		scrollPaneTable.validate();
		verticalScrollBar.setValue(verticalScrollBar.getMaximum());

		setBankInfoFieldsNonEditable();

		setSelectedBankInfoTableRow("");

	}

	public void populateBankInfoFields(BankInfo bankInfo) {
		textFieldBankInfoId.setText(bankInfo.getBankInfoId().toString());
		textFieldBankInfoName.setText(bankInfo.getBankInfoName());
		textFieldBankInfoAddress.setText(bankInfo.getBankInfoAddress());
		textFieldBankInfoPhoneNumber.setText(bankInfo.getBankInfoPhoneNumber());
		textFieldBankInfoEmail.setText(bankInfo.getBankInfoEmail());
		textFieldBankInfoAdditionalInfo.setText(bankInfo
				.getBankInfoAdditionalInfo());
	}

	public void clearBankInfoFields() {
		textFieldBankInfoId.setText("");
		textFieldBankInfoName.setText("");
		textFieldBankInfoAddress.setText("");
		textFieldBankInfoPhoneNumber.setText("");
		textFieldBankInfoEmail.setText("");
		textFieldBankInfoAdditionalInfo.setText("");
	}

	public String[] getTableBankInfoColumnNames() {
		return new String[] { "Реден Бр.", "Id", "Назив", "Адреса",
				"Телефонски број", "Email", "Забелешки" };
	}

	public int[] getTableBankInfoHiddenColumns() {
		return new int[] { 1, 3, 5, 6 };
	}

	public BankInfo getBankInfoFromBankInfoTable(String selectedRowBankInfoId) {
		BigDecimal bankInfoId = new BigDecimal(selectedRowBankInfoId);
		return bankInfoServiceImpl.findBankInfoById(bankInfoId);
	}

	public BankInfo getBankInfoFromBankInfoFields() {
		int row = table.getSelectedRow();
		String selectedRowBankInfoId = (String) table.getValueAt(row, 1);
		BankInfo bankInfo = getBankInfoFromBankInfoTable(selectedRowBankInfoId);
		bankInfo.setBankInfoName(textFieldBankInfoName.getText());
		bankInfo.setBankInfoAddress(textFieldBankInfoAddress.getText());
		bankInfo.setBankInfoPhoneNumber(textFieldBankInfoPhoneNumber.getText());
		bankInfo.setBankInfoEmail(textFieldBankInfoEmail.getText());
		bankInfo.setBankInfoAdditionalInfo(textFieldBankInfoAdditionalInfo
				.getText());

		return bankInfo;
	}

	@Override
	public String getSelectedBankInfoTableRow() {
		return selectedBankInfoTableRow;
	}
	
	public boolean isEditMode() {
		return editMode;
	}
	
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	@Override
	public void setSelectedBankInfoTableRow(String selectedBankInfoTableRow) {
		this.selectedBankInfoTableRow = selectedBankInfoTableRow;
	}

	public void setBankInfoFieldsNonEditable() {
		textFieldBankInfoName.setEditable(false);
		textFieldBankInfoName.setBackground(nonEditableTextFieldColor);
		textFieldBankInfoAddress.setEditable(false);
		textFieldBankInfoAddress.setBackground(nonEditableTextFieldColor);
		textFieldBankInfoPhoneNumber.setEditable(false);
		textFieldBankInfoPhoneNumber.setBackground(nonEditableTextFieldColor);
		textFieldBankInfoEmail.setEditable(false);
		textFieldBankInfoEmail.setBackground(nonEditableTextFieldColor);
		textFieldBankInfoAdditionalInfo.setEditable(false);
		textFieldBankInfoAdditionalInfo
				.setBackground(nonEditableTextFieldColor);
		textFieldBankInfoId.setEditable(false);
		textFieldBankInfoId.setBackground(nonEditableTextFieldColor);
	}

	public void setBankInfoFieldsEditable() {
		textFieldBankInfoName.setEditable(true);
		textFieldBankInfoName.setBackground(originalTextFieldColor);
		textFieldBankInfoAddress.setEditable(true);
		textFieldBankInfoAddress.setBackground(originalTextFieldColor);
		textFieldBankInfoPhoneNumber.setEditable(true);
		textFieldBankInfoPhoneNumber.setBackground(originalTextFieldColor);
		textFieldBankInfoEmail.setEditable(true);
		textFieldBankInfoEmail.setBackground(originalTextFieldColor);
		textFieldBankInfoAdditionalInfo.setEditable(true);
		textFieldBankInfoAdditionalInfo.setBackground(originalTextFieldColor);
		textFieldBankInfoId.setEditable(true);
		textFieldBankInfoId.setBackground(originalTextFieldColor);
	}

	public void setBankInfoTableButtonsEnabled() {
		buttonNew.setEnabled(true); 
		if (table.getRowCount() > 0) {
			buttonEdit.setEnabled(true);
			buttonDelete.setEnabled(true);
		}
	}

	public void setBankInfoTableButtonsDisabled() {
		buttonEdit.setEnabled(false);
		buttonDelete.setEnabled(false);
	}

	public void setBankInfoInfoButtonsEnabled() {
		buttonSave.setEnabled(true);
		buttonCancel.setEnabled(true);
	}

	public void setBankInfoInfoButtonsDisabled() {
		buttonSave.setEnabled(false);
		buttonCancel.setEnabled(false);
	}
	
	public void setAllButtonsDisabled() {
		buttonNew.setEnabled(false);
		buttonEdit.setEnabled(false);
		buttonDelete.setEnabled(false);
		buttonSave.setEnabled(false);
		buttonCancel.setEnabled(false);
	}

	public boolean validateBankInfoFields() {
		boolean result = true;
		result = result && (!"".equals(textFieldBankInfoName.getText()))
				&& (!"".equals(textFieldBankInfoAddress.getText()));
		return result;
	}

	public void saveBankInfo() {
		BankInfo bankInfo = getBankInfoFromBankInfoFields();
		bankInfoServiceImpl.saveBankInfo(bankInfo);
		int row = table.getSelectedRow();
		String selectedRow = Integer.toString(row);
		setSelectedBankInfoTableRow(selectedRow);
		populateBankInfoTable();
		setBankInfoFieldsNonEditable();
		setBankInfoInfoButtonsDisabled();
	}

	public void deleteBankInfo() {
		int row = table.getSelectedRow();
		String selectedRowBankInfoId = (String) table.getValueAt(row, 1);
		BankInfo bankInfo = getBankInfoFromBankInfoTable(selectedRowBankInfoId);
		bankInfoServiceImpl.deleteBankInfo(bankInfo);
		String selectedRow = Integer.toString(row);
		int intSelectedRow = Integer.parseInt(selectedRow);
		
		clearBankInfoFields();

		buttonEdit.setEnabled(false);
		buttonDelete.setEnabled(false);
		
		if (table.getRowCount() - 1 > intSelectedRow) {
			setSelectedBankInfoTableRow(selectedRow);
		}
		
		populateBankInfoTable();
		
		if (table.getRowCount() > 0) {			
			buttonEdit.setEnabled(true);
			buttonDelete.setEnabled(true);

		}

		setBankInfoFieldsNonEditable();
		setBankInfoInfoButtonsDisabled();
	}

	public GridBagConstraints bankInfoPanelConstraints() {
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(4, 10, 4, 10);
		return c;
	}

	public GridBagConstraints firstLabelConstrains() {
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(15, 10, 0, 0);
		c.anchor = GridBagConstraints.BASELINE_LEADING;
		c.weightx = 0.0;
		return c;
	}

	public GridBagConstraints firstTextFieldConstrains() {
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 4, 4, 4);
		c.anchor = GridBagConstraints.BASELINE;
		c.weightx = 1.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = GridBagConstraints.REMAINDER;
		return c;
	}

	public GridBagConstraints labelConstraints() {
		GridBagConstraints c = bankInfoPanelConstraints();
		c.anchor = GridBagConstraints.BASELINE_LEADING;
		c.weightx = 0.0;
		return c;
	}

	public GridBagConstraints textFieldConstraints() {
		GridBagConstraints c = bankInfoPanelConstraints();
		c.anchor = GridBagConstraints.BASELINE;
		c.weightx = 1.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = GridBagConstraints.REMAINDER;
		return c;
	}

	public GridBagConstraints lastComponentConstrains() {
		GridBagConstraints c = bankInfoPanelConstraints();
		c.anchor = GridBagConstraints.BASELINE;
		c.weightx = 1.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weighty = 1.0;
		return c;
	}

}
