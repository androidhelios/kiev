package com.chadik.kiev.view.dialog.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chadik.kiev.model.Customer;
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
	private JLabel labelProductTaxShown;
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
	
	private JComboBox comboboxProductTaxShown;

	private JButton buttonSave;
	private JButton buttonCancel;

	private DecimalFormat decimalFormat;
	
	private Map<String, String> mapTaxInfo;

	private Color nonEditableTextFieldColor;
	private Color mandatoryTextFieldColor;
	private Color originalTextFieldColor;

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
		dialog.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
			}

			public void windowClosing(WindowEvent e) {
				productPanelImpl.setProductTableButtonsEnabled();
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
		nonEditableTextFieldColor = new Color(255, 255, 204);

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
		
		labelProductTaxShown = new JLabel("Прикажан данок:");
		labelProductTaxShown.setBounds(xLabel, y, weightLabel, height);
		labelProductTaxShown.setForeground(mandatoryTextFieldColor);

		comboboxProductTaxShown = new JComboBox();
		comboboxProductTaxShown.setBounds(xTextField, y, weightTextField,
				height);
		populateComboboxProductTaxShown();
		comboboxProductTaxShown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox = (JComboBox) e.getSource();
				int selectedComboBoxTaxShownIndex = comboBox.getSelectedIndex();
				if (selectedComboBoxTaxShownIndex > 0) {
					String selectedComboBoxTaxShownTaxValue = mapTaxInfo
							.get(String.valueOf(comboBox.getSelectedItem()));
					populateTextFieldProductTax(selectedComboBoxTaxShownTaxValue);
				}
			}
		});

		y = y + height + spacing;

		labelProductTax = new JLabel("Данок:");
		labelProductTax.setBounds(xLabel, y, weightLabel, height);
//		labelProductTax.setForeground(mandatoryTextFieldColor);

		textFieldProductTax = new JTextField();
		textFieldProductTax.setBounds(xTextField, y, weightTextField, height);
		textFieldProductTax.setEditable(false);
		textFieldProductTax.setBackground(nonEditableTextFieldColor);
//		textFieldProductTax.getDocument().addDocumentListener(
//				new DocumentListener() {
//					@Override
//					public void removeUpdate(DocumentEvent e) {
//						calculateProductPrice(textFieldProductTax.getText(), textFieldProductTaxPrice
//								.getText());
//					}
//
//					@Override
//					public void insertUpdate(DocumentEvent e) {
//						calculateProductPrice(textFieldProductTax.getText(), textFieldProductTaxPrice
//								.getText());
//					}
//
//					@Override
//					public void changedUpdate(DocumentEvent e) {
//						calculateProductPrice(textFieldProductTax.getText(), textFieldProductTaxPrice
//								.getText());
//					}
//				});
		textFieldProductTax.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelProductTaxPrice = new JLabel("Цена со данок:");
		labelProductTaxPrice.setBounds(xLabel, y, weightLabel, height);
		labelProductTaxPrice.setForeground(mandatoryTextFieldColor);

		textFieldProductTaxPrice = new JTextField();
		textFieldProductTaxPrice.setBounds(xTextField, y, weightTextField,
				height);
		textFieldProductTaxPrice.getDocument().addDocumentListener(
				new DocumentListener() {
					@Override
					public void removeUpdate(DocumentEvent e) {
						calculateProductPrice(textFieldProductTax.getText(), textFieldProductTaxPrice
								.getText());
					}

					@Override
					public void insertUpdate(DocumentEvent e) {
						calculateProductPrice(textFieldProductTax.getText(), textFieldProductTaxPrice
								.getText());
					}

					@Override
					public void changedUpdate(DocumentEvent e) {
						calculateProductPrice(textFieldProductTax.getText(), textFieldProductTaxPrice
								.getText());
					}
				});
		textFieldProductTaxPrice.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelProductPrice = new JLabel("Цена без данок:");
		labelProductPrice.setBounds(xLabel, y, weightLabel, height);
		// labelProductPrice.setForeground(mandatoryTextFieldColor);

		textFieldProductPrice = new JTextField();
		textFieldProductPrice.setBounds(xTextField, y, weightTextField, height);
		textFieldProductPrice.setEditable(false);
		textFieldProductPrice.setBackground(nonEditableTextFieldColor);
		textFieldProductPrice.setMargin(new Insets(2, 2, 2, 2));

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
					JOptionPane.showMessageDialog(frameMain.getMainFrame(),
							"Артиклот е запишан", "Информација",
							JOptionPane.INFORMATION_MESSAGE);
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
		
		panelFieldsContent.add(labelProductTaxShown);
		panelFieldsContent.add(comboboxProductTaxShown);

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
	
	public void populateComboboxProductTaxShown() {
		comboboxProductTaxShown.removeAllItems();
		String firstItem = "- Избери данок -";
		comboboxProductTaxShown.addItem(firstItem);
		
		mapTaxInfo = new HashMap<String, String>();
		mapTaxInfo.put("5", "4,762");
		mapTaxInfo.put("18", "15,2542");

		for (Map.Entry<String, String> entry : mapTaxInfo.entrySet()) {
			comboboxProductTaxShown.addItem(entry.getKey());
		}
	}
	
	public void populateTextFieldProductTax(String productTax) {
		textFieldProductTax.setText(productTax);
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

	public void calculateProductPrice(String productTax, String productPrice) {
		decimalFormat = new DecimalFormat("#.##");
		decimalFormat.setRoundingMode(RoundingMode.DOWN);

		if (!"".equals(textFieldProductTax.getText())
				&& isValidDecimal(textFieldProductTax.getText())
				&& !"".equals(productPrice) && isValidDecimal(productPrice)) {
			
			double doubleProductTax = Double.parseDouble(productTax
					.replaceAll(",", "."));
			double doubleProductPrice = Double.parseDouble(productPrice
					.replaceAll(",", "."));

			double percentageResult = (Double) (doubleProductPrice * (doubleProductTax / 100));

			double productPriceResult = doubleProductPrice - percentageResult;

			textFieldProductPrice.setText(decimalFormat
					.format(productPriceResult));
		} else {
			textFieldProductPrice.setText("");
		}

	}

	public boolean validateProductFields() {
		boolean result = true;
		result = result
				&& (!"".equals(textFieldProductName.getText()))
				&& (!"".equals(textFieldProductMeasurement.getText()))
				&& (!"".equals(textFieldProductTax.getText()) && (isInt(textFieldProductTax
						.getText()) || isValidDecimal(textFieldProductTax
						.getText())))
				&& (!"".equals(textFieldProductPrice.getText()) && (isInt(textFieldProductPrice
						.getText()) || isValidDecimal(textFieldProductPrice
						.getText())))
				&& (!"".equals(textFieldProductTaxPrice.getText()) && (isInt(textFieldProductTaxPrice
						.getText()) || isValidDecimal(textFieldProductTaxPrice
						.getText())));
		return result;
	}

	public boolean isValidDecimal(String textFieldValue) {
		boolean result = false;

		final String regularExpression = "\\d+([,]\\d{1,4})?";

		if (textFieldValue.matches(regularExpression)) {
			result = true;
		}

		return result;
	}

	public boolean isInt(String textFieldValue) {
		try {
			Integer.parseInt(textFieldValue);
			return true;
		} catch (Exception e) {
			return false;
		}
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
