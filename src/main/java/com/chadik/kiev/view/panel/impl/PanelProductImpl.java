package com.chadik.kiev.view.panel.impl;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chadik.kiev.model.Product;
import com.chadik.kiev.service.IProductJpaService;
import com.chadik.kiev.util.TableUtil;
import com.chadik.kiev.view.dialog.IDialogGeneric;
import com.chadik.kiev.view.dialog.IDialogProduct;
import com.chadik.kiev.view.panel.IPanelProduct;

@Component
public class PanelProductImpl extends PanelGenericImpl<Product> implements
		IPanelProduct {

	private JPanel panelInfoHolderContentInfo;
	
	private JLabel labelProductId;
	private JLabel labelProductName;
	private JLabel labelProductMeasurement;
	private JLabel labelProductTax;
	private JLabel labelProductPrice;
	private JLabel labelProductAdditionalInfo;

	private JTextField textFieldProductId;
	private JTextField textFieldProductName;
	private JTextField textFieldProductMeasurement;
	private JTextField textFieldProductTax;
	private JTextField textFieldProductPrice;
	private JTextField textFieldProductAdditionalInfo;

	private DefaultTableModel defaultTableModel;
	private JTable table;
	private List<Product> products;

	@Autowired
	private IProductJpaService productJpaServiceImpl;
	@Autowired
	private IDialogProduct dialogProductImpl;

	@Override
	public Product getTableEntity() {
		return null;
	}

	@Override
	public IDialogGeneric getDialog() {
		return dialogProductImpl;
	}

	@Override
	public JPanel createPanelInfoHolderContentInfo() {
		panelInfoHolderContentInfo = new JPanel();
		panelInfoHolderContentInfo.setLayout(null);
		panelInfoHolderContentInfo.setPreferredSize(new Dimension(400, 550));
		
		int spacing = 5;
		int weightLabel = 125;
		int height = 25;
		int weightTextField = 250;
		int xLabel = 10;
		int xTextField = xLabel + weightLabel + spacing;
		int y = 25;

		labelProductName = new JLabel("Име:");
		labelProductName.setBounds(xLabel, y, weightLabel, height);

		textFieldProductName = new JTextField();
		textFieldProductName.setBounds(xTextField, y, weightTextField, height);

		y = y + height + spacing;

		labelProductMeasurement = new JLabel("Мерна единица:");
		labelProductMeasurement.setBounds(xLabel, y, weightLabel, height);

		textFieldProductMeasurement = new JTextField();
		textFieldProductMeasurement
				.setBounds(xTextField, y, weightTextField, height);

		y = y + height + spacing;

		labelProductTax = new JLabel("Данок:");
		labelProductTax.setBounds(xLabel, y, weightLabel, height);

		textFieldProductTax = new JTextField();
		textFieldProductTax.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;

		labelProductPrice = new JLabel("Цена:");
		labelProductPrice.setBounds(xLabel, y, weightLabel, height);

		textFieldProductPrice = new JTextField();
		textFieldProductPrice.setBounds(xTextField, y, weightTextField, height);

		y = y + height + spacing;

		labelProductAdditionalInfo = new JLabel("Забелешки:");
		labelProductAdditionalInfo.setBounds(xLabel, y, weightLabel, height);

		textFieldProductAdditionalInfo = new JTextField();
		textFieldProductAdditionalInfo.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;

		labelProductId = new JLabel("ID:");
		labelProductId.setBounds(xLabel, y, weightLabel, height);

		textFieldProductId = new JTextField();
		textFieldProductId.setBounds(xTextField, y, weightTextField, height);

		panelInfoHolderContentInfo.add(labelProductName);
		panelInfoHolderContentInfo.add(textFieldProductName);

		panelInfoHolderContentInfo.add(labelProductMeasurement);
		panelInfoHolderContentInfo.add(textFieldProductMeasurement);

		panelInfoHolderContentInfo.add(labelProductTax);
		panelInfoHolderContentInfo.add(textFieldProductTax);

		panelInfoHolderContentInfo.add(labelProductPrice);
		panelInfoHolderContentInfo.add(textFieldProductPrice);

		panelInfoHolderContentInfo.add(labelProductAdditionalInfo);
		panelInfoHolderContentInfo.add(textFieldProductAdditionalInfo);
		
		return panelInfoHolderContentInfo;
	}

	@Override
	public void populateTable() {
		products = productJpaServiceImpl.getAll();

		int i = 0;

		defaultTableModel.setRowCount(0);

		for (Product product : products) {
			defaultTableModel.addRow(new String[] { Integer.toString(++i),
					product.getProductId().toString(),
					product.getProductName(), product.getProductMeasurement(),
					product.getProductTax(), product.getProductPrice(),
					product.getProductAdditionalInfo() });
		}

		if (table.getRowCount() > 0) {
			table.setRowSelectionInterval(table.getRowCount() - 1,
					table.getRowCount() - 1);
		}

	}

	@Override
	public JTable createTable() {
		defaultTableModel = new DefaultTableModel();
		table = new JTable();

		defaultTableModel.setColumnIdentifiers(getTableColumnNames());
		table.setModel(defaultTableModel);
		TableUtil.hideColumns(table, getTableHiddenColumns());
		TableUtil.allignCells(table, SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setMaxWidth(100);

		populateTable();

		return table;
	}

	@Override
	public String[] getTableColumnNames() {
		return new String[] { "Реден Бр.", "Id", "Назив", "Единица Мерка",
				"Данок", "Цена", "Забелешки" };
	}

	@Override
	public int[] getTableHiddenColumns() {
		return new int[] { 1, 4, 5, 6 };
	}

}
