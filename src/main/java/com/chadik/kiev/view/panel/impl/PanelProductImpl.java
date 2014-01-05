package com.chadik.kiev.view.panel.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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

	private JPanel panelTableHolder;
	private JPanel panelTableHolderContent;
	private JPanel panelTableHolderContentTable;
	private JPanel panelTableHolderContentButtons;

	private JPanel panelInfoHolder;
	private JPanel panelInfoHolderContent;
	private JPanel panelInfoHolderContentInfo;
	private JPanel panelInfoHolderContentButtons;
	
	private JScrollPane scrollPaneTable;
	
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
	public void populateTableProduct() {
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
	public JPanel createPanelInfoHolder() {
		panelInfoHolder = new JPanel();
		panelInfoHolder.setLayout(new BorderLayout());
		
		panelInfoHolderContent = new JPanel();
		panelInfoHolderContent.setLayout(new BorderLayout());
		panelInfoHolderContent.setPreferredSize(new Dimension(400, 600));
		
		panelInfoHolderContentInfo = new JPanel();
		panelInfoHolderContentInfo.setLayout(null);
		panelInfoHolderContentInfo.setPreferredSize(new Dimension(400, 550));
		
		panelInfoHolderContentButtons = new JPanel();
		panelInfoHolderContentButtons.setLayout(new FlowLayout());
		panelInfoHolderContentButtons.setPreferredSize(new Dimension(400, 50));
		
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
		
		buttonSave = new JButton("Зачувај");
		buttonSave.setPreferredSize(new Dimension(100, 25));

		buttonCancel = new JButton("Откажи");
		buttonCancel.setPreferredSize(new Dimension(100, 25));

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
		
		panelInfoHolder.add(panelInfoHolderContent);
		
		return panelInfoHolder;
	}

	@Override
	public JPanel createPanelTableHolder() {
		panelTableHolder = new JPanel();
		panelTableHolder.setLayout(new BorderLayout());

		panelTableHolderContent = new JPanel();
		panelTableHolderContent.setLayout(new BorderLayout());
		panelTableHolderContent.setPreferredSize(new Dimension(400, 600));

		panelTableHolderContentTable = new JPanel();
		panelTableHolderContentTable.setLayout(new BorderLayout());
		panelTableHolderContentTable.setPreferredSize(new Dimension(400, 550));

		panelTableHolderContentButtons = new JPanel();
		panelTableHolderContentButtons.setLayout(new FlowLayout());
		panelTableHolderContentButtons.setPreferredSize(new Dimension(400, 50));
		
		defaultTableModel = new DefaultTableModel();
		table = new JTable();

		defaultTableModel.setColumnIdentifiers(getTableProductColumnNames());
		table.setModel(defaultTableModel);
		TableUtil.hideColumns(table, getTableProductHiddenColumns());
		TableUtil.allignCells(table, SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setMaxWidth(100);

		populateTableProduct();
		
		scrollPaneTable = new JScrollPane(table);
		
		buttonNew = new JButton("Креирај");
		buttonNew.setPreferredSize(new Dimension(100, 25));
		buttonNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getDialog().initDialog();
			}
		});

		buttonEdit = new JButton("Измени");
		buttonEdit.setPreferredSize(new Dimension(100, 25));

		buttonDelete = new JButton("Избриши");
		buttonDelete.setPreferredSize(new Dimension(100, 25));
		
		panelTableHolderContentTable.add(scrollPaneTable);
		
		panelTableHolderContentButtons.add(buttonDelete);
		panelTableHolderContentButtons.add(buttonEdit);
		panelTableHolderContentButtons.add(buttonNew);

		panelTableHolderContent.add(panelTableHolderContentTable,
				BorderLayout.CENTER);
		panelTableHolderContent.add(panelTableHolderContentButtons,
				BorderLayout.SOUTH);

		panelTableHolder.add(panelTableHolderContent);
		
		return panelTableHolder;
	}

	@Override
	public String[] getTableProductColumnNames() {
		return new String[] { "Реден Бр.", "Id", "Назив", "Единица Мерка",
				"Данок", "Цена", "Забелешки" };
	}

	@Override
	public int[] getTableProductHiddenColumns() {
		return new int[] { 1, 4, 5, 6 };
	}

}
