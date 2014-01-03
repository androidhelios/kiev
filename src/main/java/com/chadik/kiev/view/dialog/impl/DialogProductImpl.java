package com.chadik.kiev.view.dialog.impl;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chadik.kiev.model.Customer;
import com.chadik.kiev.model.Product;
import com.chadik.kiev.service.IGenericJpaService;
import com.chadik.kiev.service.IProductJpaService;
import com.chadik.kiev.view.dialog.IDialogProduct;
import com.chadik.kiev.view.panel.IPanelGeneric;
import com.chadik.kiev.view.panel.IPanelProduct;

@Component
public class DialogProductImpl extends DialogGenericImpl<Product> implements
		IDialogProduct {
	
	@Autowired
	private IProductJpaService productJpaServiceImpl;
	@Autowired
	private IPanelProduct panelProductImpl;

	private JPanel panelFieldsContent;

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

	@Override
	public String getDilogName() {
		return "Нов Продукт";
	}

	@Override
	public Dimension getPanelFieldsDimension() {
		return new Dimension(400, 300);
	}

	@Override
	public Dimension getPanelButtonsDimension() {
		return new Dimension(400, 50);
	}

	@Override
	public JPanel getPanelFieldsContent() {
		panelFieldsContent = new JPanel();
		panelFieldsContent.setLayout(null);
		panelFieldsContent.setPreferredSize(getPanelFieldsDimension());

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

		return panelFieldsContent;
	}

	@Override
	public IGenericJpaService getGenericJpaService() {
		return productJpaServiceImpl;
	}

	@Override
	public Product getT() {
		Product product = new Product();
		product.setProductName(textFieldProductName.getText());
		product.setProductMeasurement(textFieldProductMeasurement.getText());
		product.setProductTax(textFieldProductTax.getText());
		product.setProductPrice(textFieldProductPrice.getText());
		product.setProductAdditionalInfo(textFieldProductAdditionalInfo.getText());
		
		return product;
	}

	@Override
	public IPanelGeneric getPanelGeneric() {
		return panelProductImpl;
	}

}
