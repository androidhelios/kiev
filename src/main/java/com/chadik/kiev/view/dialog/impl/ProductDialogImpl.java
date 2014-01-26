package com.chadik.kiev.view.dialog.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chadik.kiev.model.Product;
import com.chadik.kiev.service.IProductService;
import com.chadik.kiev.view.dialog.IProductDialog;
import com.chadik.kiev.view.panel.IProductPanel;

@Component
public class ProductDialogImpl implements IProductDialog {

	private JDialog dialog;	
	private JPanel contentPane;
	
	private JPanel panelAll;
	private JPanel panelFields;
	private JPanel panelFieldsContent;
	private JPanel panelButtons;
	private JPanel panelButtonsContent;

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

	private JButton buttonSave;
	private JButton buttonCancel;

	@Autowired
	private IProductService productServiceImpl;
	@Autowired
	private IProductPanel productPanelImpl;

	@Override
	public JDialog initProductDialog() {
		dialog = new JDialog();
		dialog.setTitle("Нов Продукт");
		dialog.setResizable(false);

		contentPane = new JPanel();

		dialog.setContentPane(contentPane);

		panelAll = new JPanel();
		panelAll.setLayout(new BorderLayout());

		panelFields = new JPanel();
		panelFields.setLayout(new BorderLayout());

		panelFieldsContent = new JPanel();
		panelFieldsContent.setLayout(null);
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
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveProductAndDispose();
			}
		});

		buttonCancel = new JButton("Откажи");
		buttonCancel.setPreferredSize(new Dimension(100, 25));
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		panelFieldsContent.add(labelProductName);
		panelFieldsContent.add(textFieldProductName);

		panelFieldsContent.add(labelProductMeasurement);
		panelFieldsContent.add(textFieldProductMeasurement);

		panelFieldsContent.add(labelProductTax);
		panelFieldsContent.add(textFieldProductTax);

		panelFieldsContent.add(labelProductPrice);
		panelFieldsContent.add(textFieldProductPrice);

		panelFieldsContent.add(labelProductAdditionalInfo);
		panelFieldsContent.add(textFieldProductAdditionalInfo);

		panelButtonsContent.add(buttonSave);
		panelButtonsContent.add(buttonCancel);

		panelFields.add(panelFieldsContent, BorderLayout.CENTER);

		panelButtons.add(panelButtonsContent, BorderLayout.CENTER);

		panelAll.add(panelButtons, BorderLayout.SOUTH);
		panelAll.add(panelFields, BorderLayout.CENTER);

		contentPane.add(panelAll);

		dialog.pack();
		dialog.setVisible(true);
		dialog.setLocationRelativeTo(null);

		return dialog;

	}

	public Product getProductFromFields() {
		Product product = new Product();
		product.setProductName(textFieldProductName.getText());
		product.setProductMeasurement(textFieldProductMeasurement.getText());
		product.setProductTax(textFieldProductTax.getText());
		product.setProductPrice(textFieldProductPrice.getText());
		product.setProductAdditionalInfo(textFieldProductAdditionalInfo
				.getText());

		return product;
	}

	public void saveProductAndDispose() {
		Product product = getProductFromFields();
		productServiceImpl.saveProduct(product);
		dialog.dispose();
		productPanelImpl.populateProductTable();
	}

	public void dispose() {
		dialog.dispose();
	}

}
