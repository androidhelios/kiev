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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chadik.kiev.model.Product;
import com.chadik.kiev.service.IProductService;
import com.chadik.kiev.view.FrameMain;
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
	private JLabel labelProductTaxPrice;
	private JLabel labelProductAdditionalInfo;

	private JTextField textFieldProductId;
	private JTextField textFieldProductName;
	private JTextField textFieldProductMeasurement;
	private JTextField textFieldProductTax;
	private JTextField textFieldProductPrice;
	private JTextField textFieldProductTaxPrice;
	private JTextField textFieldProductAdditionalInfo;

	private JButton buttonSave;
	private JButton buttonCancel;

	private Color mandatoryTextFieldColor;

	@Autowired
	private IProductService productServiceImpl;
	@Autowired
	private IProductPanel productPanelImpl;
	@Autowired
	private FrameMain frameMain;

	@Override
	public JDialog initProductDialog() {
		dialog = new JDialog(frameMain.getMainFrame(), true);
		dialog.setTitle("Нов Артикл");
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

		labelProductName = new JLabel("Име:");
		labelProductName.setBounds(xLabel, y, weightLabel, height);
		labelProductName.setForeground(mandatoryTextFieldColor);

		textFieldProductName = new JTextField();
		textFieldProductName.setBounds(xTextField, y, weightTextField, height);
		textFieldProductName.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelProductMeasurement = new JLabel("Мерна единица:");
		labelProductMeasurement.setBounds(xLabel, y, weightLabel, height);
		labelProductMeasurement.setForeground(mandatoryTextFieldColor);

		textFieldProductMeasurement = new JTextField();
		textFieldProductMeasurement.setBounds(xTextField, y, weightTextField,
				height);
		textFieldProductMeasurement.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelProductTax = new JLabel("Данок:");
		labelProductTax.setBounds(xLabel, y, weightLabel, height);
		labelProductTax.setForeground(mandatoryTextFieldColor);

		textFieldProductTax = new JTextField();
		textFieldProductTax.setBounds(xTextField, y, weightTextField, height);
		textFieldProductTax.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelProductPrice = new JLabel("Цена без данок:");
		labelProductPrice.setBounds(xLabel, y, weightLabel, height);
		labelProductPrice.setForeground(mandatoryTextFieldColor);

		textFieldProductPrice = new JTextField();
		textFieldProductPrice.setBounds(xTextField, y, weightTextField, height);
		textFieldProductPrice.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelProductTaxPrice = new JLabel("Цена со данок:");
		labelProductTaxPrice.setBounds(xLabel, y, weightLabel, height);

		textFieldProductTaxPrice = new JTextField();
		textFieldProductTaxPrice.setBounds(xTextField, y, weightTextField,
				height);
		textFieldProductTaxPrice.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelProductAdditionalInfo = new JLabel("Забелешки:");
		labelProductAdditionalInfo.setBounds(xLabel, y, weightLabel, height);

		textFieldProductAdditionalInfo = new JTextField();
		textFieldProductAdditionalInfo.setBounds(xTextField, y,
				weightTextField, height);
		textFieldProductAdditionalInfo.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelProductId = new JLabel("ID:");
		labelProductId.setBounds(xLabel, y, weightLabel, height);

		textFieldProductId = new JTextField();
		textFieldProductId.setBounds(xTextField, y, weightTextField, height);
		textFieldProductId.setMargin(new Insets(2, 2, 2, 2));

		buttonSave = new JButton("Зачувај");
		buttonSave.setPreferredSize(new Dimension(100, 25));
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validateProductFields()) {
					saveProductAndDispose();
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
				productPanelImpl.setProductTableButtonsEnabled();
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

		panelFieldsContent.add(labelProductTaxPrice);
		panelFieldsContent.add(textFieldProductTaxPrice);

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
		dialog.setLocationRelativeTo(frameMain.getMainFrame());
		dialog.setVisible(true);

		return dialog;

	}

	public Product getProductFromProductFields() {
		Product product = new Product();
		product.setProductName(textFieldProductName.getText());
		product.setProductMeasurement(textFieldProductMeasurement.getText());
		product.setProductTax(textFieldProductTax.getText());
		product.setProductPrice(textFieldProductPrice.getText());
		product.setProductTaxPrice(textFieldProductTaxPrice.getText());
		product.setProductAdditionalInfo(textFieldProductAdditionalInfo
				.getText());

		return product;
	}

	public boolean validateProductFields() {
		boolean result = true;
		result = result && (!"".equals(textFieldProductName.getText()))
				&& (!"".equals(textFieldProductMeasurement.getText()))
				&& (!"".equals(textFieldProductTax.getText()))
				&& (!"".equals(textFieldProductPrice.getText()));
		return result;
	}

	public void saveProductAndDispose() {
		Product product = getProductFromProductFields();
		productServiceImpl.saveProduct(product);
		dialog.dispose();
		productPanelImpl.populateProductTable();
	}

	public void dispose() {
		dialog.dispose();
	}

}
