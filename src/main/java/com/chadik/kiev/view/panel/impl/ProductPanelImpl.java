package com.chadik.kiev.view.panel.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chadik.kiev.model.Product;
import com.chadik.kiev.service.IProductService;
import com.chadik.kiev.util.TableUtil;
import com.chadik.kiev.view.dialog.IProductDialog;
import com.chadik.kiev.view.panel.IProductPanel;

@Component
public class ProductPanelImpl implements IProductPanel {

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

	private JButton buttonNew;
	private JButton buttonEdit;
	private JButton buttonDelete;
	private JButton buttonSave;
	private JButton buttonCancel;

	private List<Product> products;

	@Autowired
	private IProductService productServiceImpl;
	@Autowired
	private IProductDialog productDialogImpl;

	@Override
	public JPanel initProductPanel() {
		panelAll = new JPanel();
		panelAll.setLayout(new BorderLayout());

		panelTableHolder = new JPanel();
		panelTableHolder.setLayout(new BorderLayout());

		panelTableHolderContent = new JPanel();
		panelTableHolderContent.setLayout(new BorderLayout());

		panelTableHolderContentTable = new JPanel();
		panelTableHolderContentTable.setLayout(new BorderLayout());
		panelTableHolderContentTable.setPreferredSize(new Dimension(400, 550));

		panelTableHolderContentButtons = new JPanel();
		panelTableHolderContentButtons.setLayout(new FlowLayout());
		panelTableHolderContentButtons.setPreferredSize(new Dimension(400, 50));

		panelInfoHolder = new JPanel();
		panelInfoHolder.setLayout(new BorderLayout());

		panelInfoHolderContent = new JPanel();
		panelInfoHolderContent.setLayout(new BorderLayout());

		panelInfoHolderContentInfo = new JPanel();
		panelInfoHolderContentInfo.setLayout(null);
		panelInfoHolderContentInfo.setPreferredSize(new Dimension(400, 550));

		panelInfoHolderContentButtons = new JPanel();
		panelInfoHolderContentButtons.setLayout(new FlowLayout());
		panelInfoHolderContentButtons.setPreferredSize(new Dimension(400, 50));

		defaultTableModel = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		defaultTableModel.setColumnIdentifiers(getTableProductColumnNames());

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				String selectedRowProductId = (String) table.getValueAt(row, 1);
				Product product = getProductFromProductTable(selectedRowProductId);
				populateProductFields(product);
			}
		});

		table.setModel(defaultTableModel);

		TableUtil.hideColumns(table, getTableProductHiddenColumns());

		TableUtil.allignCells(table, SwingConstants.CENTER);

		table.getColumnModel().getColumn(0).setMaxWidth(100);

		scrollPaneTable = new JScrollPane(table);

		verticalScrollBar = scrollPaneTable.getVerticalScrollBar();

		buttonNew = new JButton("Креирај");
		buttonNew.setPreferredSize(new Dimension(100, 25));
		buttonNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				productDialogImpl.initProductDialog();
			}
		});

		buttonEdit = new JButton("Измени");
		buttonEdit.setPreferredSize(new Dimension(100, 25));

		buttonDelete = new JButton("Избриши");
		buttonDelete.setPreferredSize(new Dimension(100, 25));

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
		textFieldProductMeasurement.setBounds(xTextField, y, weightTextField,
				height);

		y = y + height + spacing;

		labelProductTax = new JLabel("Данок:");
		labelProductTax.setBounds(xLabel, y, weightLabel, height);

		textFieldProductTax = new JTextField();
		textFieldProductTax.setBounds(xTextField, y, weightTextField, height);

		y = y + height + spacing;

		labelProductPrice = new JLabel("Цена:");
		labelProductPrice.setBounds(xLabel, y, weightLabel, height);

		textFieldProductPrice = new JTextField();
		textFieldProductPrice.setBounds(xTextField, y, weightTextField, height);

		y = y + height + spacing;

		labelProductAdditionalInfo = new JLabel("Забелешки:");
		labelProductAdditionalInfo.setBounds(xLabel, y, weightLabel, height);

		textFieldProductAdditionalInfo = new JTextField();
		textFieldProductAdditionalInfo.setBounds(xTextField, y,
				weightTextField, height);

		y = y + height + spacing;

		labelProductId = new JLabel("ID:");
		labelProductId.setBounds(xLabel, y, weightLabel, height);

		textFieldProductId = new JTextField();
		textFieldProductId.setBounds(xTextField, y, weightTextField, height);

		buttonSave = new JButton("Зачувај");
		buttonSave.setPreferredSize(new Dimension(100, 25));

		buttonCancel = new JButton("Откажи");
		buttonCancel.setPreferredSize(new Dimension(100, 25));

		panelTableHolderContentTable.add(scrollPaneTable);

		panelTableHolderContentButtons.add(buttonDelete);
		panelTableHolderContentButtons.add(buttonEdit);
		panelTableHolderContentButtons.add(buttonNew);

		panelTableHolderContent.add(panelTableHolderContentTable,
				BorderLayout.CENTER);
		panelTableHolderContent.add(panelTableHolderContentButtons,
				BorderLayout.SOUTH);

		panelTableHolder.add(panelTableHolderContent);

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

		panelInfoHolderContentButtons.add(buttonSave);
		panelInfoHolderContentButtons.add(buttonCancel);

		panelInfoHolderContent.add(panelInfoHolderContentInfo,
				BorderLayout.CENTER);
		panelInfoHolderContent.add(panelInfoHolderContentButtons,
				BorderLayout.SOUTH);

		panelInfoHolder.add(panelInfoHolderContent, BorderLayout.CENTER);

		panelAll.add(panelTableHolder, BorderLayout.WEST);
		panelAll.add(panelInfoHolder, BorderLayout.CENTER);

		populateProductTable();

		return panelAll;
	}

	@Override
	public void populateProductTable() {
		String selectedRowProductId = "";
		products = productServiceImpl.findAllProducts();

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

			selectedRowProductId = (String) table.getValueAt(
					table.getRowCount() - 1, 1);
			Product product = getProductFromProductTable(selectedRowProductId);
			populateProductFields(product);
		}

		scrollPaneTable.validate();
		verticalScrollBar.setValue(verticalScrollBar.getMaximum());

	}

	public String[] getTableProductColumnNames() {
		return new String[] { "Реден Бр.", "Id", "Назив", "Единица Мерка",
				"Данок", "Цена", "Забелешки" };
	}

	public int[] getTableProductHiddenColumns() {
		return new int[] { 1, 4, 5, 6 };
	}

	public Product getProductFromProductTable(String selectedRowProductId) {
		BigDecimal productId = new BigDecimal(selectedRowProductId);
		return productServiceImpl.findProductById(productId);
	}

	public void populateProductFields(Product product) {
		textFieldProductId.setText(product.getProductId().toString());
		textFieldProductName.setText(product.getProductName());
		textFieldProductMeasurement.setText(product.getProductMeasurement());
		textFieldProductTax.setText(product.getProductTax());
		textFieldProductPrice.setText(product.getProductPrice());
		textFieldProductAdditionalInfo.setText(product
				.getProductAdditionalInfo());
	}

}
