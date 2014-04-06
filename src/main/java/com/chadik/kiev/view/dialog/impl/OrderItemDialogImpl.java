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
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
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

import com.chadik.kiev.model.Invoice;
import com.chadik.kiev.model.OrderItem;
import com.chadik.kiev.model.Product;
import com.chadik.kiev.service.IInvoiceService;
import com.chadik.kiev.service.IOrderItemService;
import com.chadik.kiev.service.IProductService;
import com.chadik.kiev.view.FrameMain;
import com.chadik.kiev.view.dialog.IOrderItemDialog;
import com.chadik.kiev.view.panel.IInvoicePanel;
import com.chadik.kiev.view.panel.IOrderItemPanel;

@Component
public class OrderItemDialogImpl implements IOrderItemDialog {

	private JDialog dialog;
	private JPanel contentPane;

	private JPanel panelAll;
	private JPanel panelFields;
	private JPanel panelFieldsContent;
	private JPanel panelButtons;
	private JPanel panelButtonsContent;

	private JLabel labelOrderItemId;
	private JLabel labelOrderItemProductId;
	private JLabel labelOrderItemInvoiceId;
	private JLabel labelOrderItemProductName;
	private JLabel labelOrderItemProductMeasurement;
	private JLabel labelOrderItemQuantity;
	private JLabel labelOrderItemProductPrice;
	private JLabel labelOrderItemQuantityPriceWithoutTax;
	private JLabel labelOrderItemProductTax;
	private JLabel labelOrderItemQuantityTax;
	private JLabel labelOrderItemQuantityPrice;
	private JLabel labelOrderItemAdditionalInfo;

	private JTextField textFieldOrderItemId;
	private JTextField textFieldOrderItemProductId;
	private JTextField textFieldOrderItemInvoiceId;
	private JTextField textFieldOrderItemProductMeasurement;
	private JTextField textFieldOrderItemQuantity;
	private JTextField textFieldOrderItemProductPrice;
	private JTextField textFieldOrderItemQuantityPriceWithoutTax;
	private JTextField textFieldOrderItemProductTax;
	private JTextField textFieldOrderItemQuantityTax;
	private JTextField textFieldOrderItemQuantityPrice;
	private JTextField textFieldOrderItemAdditionalInfo;

	private JComboBox comboBoxOrderItemProductName;

	private JButton buttonSave;
	private JButton buttonCancel;

	private Color nonEditableTextFieldColor;
	private Color mandatoryTextFieldColor;
	private Color originalTextFieldColor;

	private DecimalFormat decimalFormat;

	private Map<Integer, Integer> mapProducts;

	// private Invoice invoice;
	private int invoiceId;

	@Autowired
	private IOrderItemPanel orderItemPanelImpl;
	@Autowired
	private IInvoicePanel invoicePanelImpl;
	@Autowired
	private IOrderItemService orderItemServiceImpl;
	@Autowired
	private IProductService productServiceImpl;
	@Autowired
	private IInvoiceService invoiceServiceImpl;
	@Autowired
	private FrameMain frameMain;

	@Override
	public JDialog initOrderItemDialog() {
		dialog = new JDialog(frameMain.getMainFrame(), true);
		dialog.setTitle("Нова Фактура");
		dialog.setResizable(false);
		dialog.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				
			}

			public void windowClosing(WindowEvent e) {

			}
		});

		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());

		dialog.setContentPane(contentPane);

		panelAll = new JPanel();
		panelAll.setLayout(new BorderLayout());
		panelAll.setPreferredSize(new Dimension(450, 350));

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
		panelButtonsContent.setPreferredSize(new Dimension(450, 50));

		int spacing = 5;
		int weightLabel = 125;
		int height = 25;
		int weightTextField = 250;
		int xLabel = 10;
		int xTextField = xLabel + weightLabel + spacing;
		int y = 25;

		mandatoryTextFieldColor = new Color(204, 0, 0);
		nonEditableTextFieldColor = new Color(255, 255, 204);

		labelOrderItemProductName = new JLabel("Продукт:");
		labelOrderItemProductName.setBounds(xLabel, y, weightLabel, height);
		labelOrderItemProductName.setForeground(mandatoryTextFieldColor);

		comboBoxOrderItemProductName = new JComboBox();
		comboBoxOrderItemProductName.setBounds(xTextField, y, weightTextField,
				height);
		populateOrderItemProductComboBox();
		comboBoxOrderItemProductName.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox = (JComboBox) e.getSource();
				int selectedComboBoxProductIndex = comboBox.getSelectedIndex();
				textFieldOrderItemQuantity.setEditable(false);	
				textFieldOrderItemQuantity.setBackground(new Color(255, 255, 204));
				textFieldOrderItemQuantity.setText("");
				if (selectedComboBoxProductIndex > 0) {
					textFieldOrderItemQuantity.setEditable(true);
					textFieldOrderItemQuantity.setBackground(new Color(255, 255, 255));
					Integer selectedComboBoxProductId = mapProducts
							.get(selectedComboBoxProductIndex);
					int intSelectedComboBoxProductId = selectedComboBoxProductId
							.intValue();
					Product product = productServiceImpl
							.findProductById(new BigDecimal(
									intSelectedComboBoxProductId));
					populateOrderItemProductFields(product);
				}
			}
		});

		y = y + height + spacing;

		labelOrderItemQuantity = new JLabel("Количина:");
		labelOrderItemQuantity.setBounds(xLabel, y, weightLabel, height);
		labelOrderItemQuantity.setForeground(mandatoryTextFieldColor);

		

		textFieldOrderItemQuantity = new JTextField();
		textFieldOrderItemQuantity.setBounds(xTextField, y, weightTextField,
				height);
		textFieldOrderItemQuantity.getDocument().addDocumentListener(
				new DocumentListener() {
					@Override
					public void removeUpdate(DocumentEvent e) {
						populateCalculatedOrderItemFields(textFieldOrderItemQuantity
								.getText());
					}

					@Override
					public void insertUpdate(DocumentEvent e) {
						populateCalculatedOrderItemFields(textFieldOrderItemQuantity
								.getText());
					}

					@Override
					public void changedUpdate(DocumentEvent e) {
						populateCalculatedOrderItemFields(textFieldOrderItemQuantity
								.getText());
					}
				});
		textFieldOrderItemQuantity.setEditable(false);
		textFieldOrderItemQuantity.setBackground(new Color(255, 255, 204));
		textFieldOrderItemQuantity.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelOrderItemProductPrice = new JLabel("Цена без данок:");
		labelOrderItemProductPrice.setBounds(xLabel, y, weightLabel, height);

		textFieldOrderItemProductPrice = new JTextField();
		textFieldOrderItemProductPrice.setBounds(xTextField, y,
				weightTextField, height);
		textFieldOrderItemProductPrice.setEditable(false);
		textFieldOrderItemProductPrice.setBackground(nonEditableTextFieldColor);
		textFieldOrderItemProductPrice.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelOrderItemQuantityPriceWithoutTax = new JLabel("Износ без данок:");
		labelOrderItemQuantityPriceWithoutTax.setBounds(xLabel, y, weightLabel,
				height);

		textFieldOrderItemQuantityPriceWithoutTax = new JTextField();
		textFieldOrderItemQuantityPriceWithoutTax.setBounds(xTextField, y,
				weightTextField, height);
		textFieldOrderItemQuantityPriceWithoutTax.setEditable(false);
		textFieldOrderItemQuantityPriceWithoutTax
				.setBackground(nonEditableTextFieldColor);
		textFieldOrderItemQuantityPriceWithoutTax.setMargin(new Insets(2, 2, 2,
				2));

		y = y + height + spacing;

		labelOrderItemProductTax = new JLabel("Данок:");
		labelOrderItemProductTax.setBounds(xLabel, y, weightLabel, height);

		textFieldOrderItemProductTax = new JTextField();
		textFieldOrderItemProductTax.setBounds(xTextField, y, weightTextField,
				height);
		textFieldOrderItemProductTax.setEditable(false);
		textFieldOrderItemProductTax.setBackground(nonEditableTextFieldColor);
		textFieldOrderItemProductTax.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelOrderItemQuantityTax = new JLabel("Износ на ДДВ:");
		labelOrderItemQuantityTax.setBounds(xLabel, y, weightLabel, height);

		textFieldOrderItemQuantityTax = new JTextField();
		textFieldOrderItemQuantityTax.setBounds(xTextField, y, weightTextField,
				height);
		textFieldOrderItemQuantityTax.setEditable(false);
		textFieldOrderItemQuantityTax.setBackground(nonEditableTextFieldColor);
		textFieldOrderItemQuantityTax.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelOrderItemQuantityPrice = new JLabel("Вкупен износ со ДДВ:");
		labelOrderItemQuantityPrice.setBounds(xLabel, y, weightLabel, height);

		textFieldOrderItemQuantityPrice = new JTextField();
		textFieldOrderItemQuantityPrice.setBounds(xTextField, y,
				weightTextField, height);
		textFieldOrderItemQuantityPrice.setEditable(false);
		textFieldOrderItemQuantityPrice
				.setBackground(nonEditableTextFieldColor);
		textFieldOrderItemQuantityPrice.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelOrderItemAdditionalInfo = new JLabel("Забелешки:");
		labelOrderItemAdditionalInfo.setBounds(xLabel, y, weightLabel, height);

		textFieldOrderItemAdditionalInfo = new JTextField();
		textFieldOrderItemAdditionalInfo.setBounds(xTextField, y,
				weightTextField, height);
		textFieldOrderItemAdditionalInfo.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelOrderItemProductMeasurement = new JLabel("Мерна единица:");
		labelOrderItemProductMeasurement.setBounds(xLabel, y, weightLabel,
				height);

		nonEditableTextFieldColor = new Color(255, 255, 204);

		textFieldOrderItemProductMeasurement = new JTextField();
		textFieldOrderItemProductMeasurement.setBounds(xTextField, y,
				weightTextField, height);
		textFieldOrderItemProductMeasurement.setEditable(false);
		textFieldOrderItemProductMeasurement
				.setBackground(nonEditableTextFieldColor);
		textFieldOrderItemProductMeasurement.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelOrderItemInvoiceId = new JLabel("Фактура ID:");
		labelOrderItemInvoiceId.setBounds(xLabel, y, weightLabel, height);

		textFieldOrderItemInvoiceId = new JTextField();
		textFieldOrderItemInvoiceId.setBounds(xTextField, y, weightTextField,
				height);
		textFieldOrderItemInvoiceId.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelOrderItemProductId = new JLabel("Продукт ID:");
		labelOrderItemProductId.setBounds(xLabel, y, weightLabel, height);

		textFieldOrderItemProductId = new JTextField();
		textFieldOrderItemProductId.setBounds(xTextField, y, weightTextField,
				height);
		textFieldOrderItemProductId.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelOrderItemId = new JLabel("Порачка ID:");
		labelOrderItemId.setBounds(xLabel, y, weightLabel, height);

		textFieldOrderItemId = new JTextField();
		textFieldOrderItemId.setBounds(xTextField, y, weightTextField, height);
		textFieldOrderItemId.setMargin(new Insets(2, 2, 2, 2));

		buttonSave = new JButton("Зачувај");
		buttonSave.setPreferredSize(new Dimension(100, 25));
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validateOrderItemFields()) {
					saveOrderItemAndDispose();
					JOptionPane.showMessageDialog(frameMain.getMainFrame(), "Артиклот е додаден",
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
			}
		});

		panelFieldsContent.add(labelOrderItemProductName);
		panelFieldsContent.add(comboBoxOrderItemProductName);

		panelFieldsContent.add(labelOrderItemQuantity);
		panelFieldsContent.add(textFieldOrderItemQuantity);

		panelFieldsContent.add(labelOrderItemProductPrice);
		panelFieldsContent.add(textFieldOrderItemProductPrice);

		panelFieldsContent.add(labelOrderItemQuantityPriceWithoutTax);
		panelFieldsContent.add(textFieldOrderItemQuantityPriceWithoutTax);

		panelFieldsContent.add(labelOrderItemProductTax);
		panelFieldsContent.add(textFieldOrderItemProductTax);

		panelFieldsContent.add(labelOrderItemQuantityTax);
		panelFieldsContent.add(textFieldOrderItemQuantityTax);

		panelFieldsContent.add(labelOrderItemQuantityPrice);
		panelFieldsContent.add(textFieldOrderItemQuantityPrice);

		panelFieldsContent.add(labelOrderItemAdditionalInfo);
		panelFieldsContent.add(textFieldOrderItemAdditionalInfo);

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

	public void populateOrderItemProductFields(Product product) {
		textFieldOrderItemProductId.setText(product.getProductId().toString());
		textFieldOrderItemProductMeasurement.setText(product
				.getProductMeasurement());
		textFieldOrderItemProductPrice.setText(product.getProductPrice());
		textFieldOrderItemProductTax.setText(product.getProductTax());
	}

	public void populateOrderItemProductComboBox() {
		mapProducts = new HashMap<Integer, Integer>();
		List<Product> products = productServiceImpl.findAllProducts();
		String firstItem = "- Избери продукт -";
		comboBoxOrderItemProductName.addItem(firstItem);
		int i = 1;

		for (Product product : products) {
			String productName = product.getProductName();
			String productMeasurement = product.getProductMeasurement();
			String productComboBoxStringItem = productName + " - "
					+ productMeasurement;
			BigDecimal productId = product.getProductId();
			Integer integerProductId = productId.intValue();
			mapProducts.put(i++, integerProductId);
			comboBoxOrderItemProductName.addItem(productComboBoxStringItem);
		}
	}

	public void populateCalculatedOrderItemFields(String quantity) {
		decimalFormat = new DecimalFormat("#.##");
		String emptyString = "";

		if (isInt(quantity)) {
			double quantityTextFieldValue = Double.parseDouble(quantity);
			String doubleOrderItemProductPriceDotSeparator = textFieldOrderItemProductPrice
					.getText().replace(",", ".");
			double doubleOrderItemProductPrice = Double
					.parseDouble(doubleOrderItemProductPriceDotSeparator);
			double doubleOrderItemProductTax = Double
					.parseDouble(textFieldOrderItemProductTax.getText());

			double doubleOrderItemQuantityPriceWithoutTax = quantityTextFieldValue
					* doubleOrderItemProductPrice;
			double doubleOrderItemQuantityTax = doubleOrderItemQuantityPriceWithoutTax
					* doubleOrderItemProductTax / 100;
			double doubleOrderItemQuantityPrice = doubleOrderItemQuantityPriceWithoutTax
					+ doubleOrderItemQuantityTax;

			textFieldOrderItemQuantityPriceWithoutTax.setText(decimalFormat
					.format(doubleOrderItemQuantityPriceWithoutTax));
			textFieldOrderItemQuantityTax.setText(decimalFormat
					.format(doubleOrderItemQuantityTax));
			textFieldOrderItemQuantityPrice.setText(decimalFormat
					.format(doubleOrderItemQuantityPrice));
		} else {
			textFieldOrderItemQuantityPriceWithoutTax.setText(emptyString);
			textFieldOrderItemQuantityTax.setText(emptyString);
			textFieldOrderItemQuantityPrice.setText(emptyString);
		}
	}

	public OrderItem getOrderItemFromOrderItemFields() {
		OrderItem orderItem = new OrderItem();
		BigDecimal bigDecimalId = new BigDecimal(String.valueOf(getInvoiceId()));

		Invoice invoice = invoiceServiceImpl.findInvoiceById(bigDecimalId);

		orderItem.setInvoice(invoice);
		orderItem.setProduct(getSelectedComboBoxOrderItemProduct());
		orderItem.setOrderItemQuantity(textFieldOrderItemQuantity.getText());
		orderItem.setOrderItemQuantityPrice(textFieldOrderItemQuantityPrice
				.getText());
		orderItem
				.setOrderItemQuantityPriceWithoutTax(textFieldOrderItemQuantityPriceWithoutTax
						.getText());
		orderItem.setOrderItemQuantityTax(textFieldOrderItemQuantityTax
				.getText());
		orderItem.setOrderAdditionalInfo(textFieldOrderItemAdditionalInfo
				.getText());

		invoice.getOrderItems().add(orderItem);

		return orderItem;
	}

	public Product getSelectedComboBoxOrderItemProduct() {
		int selectedComboBoxProductIndex = comboBoxOrderItemProductName
				.getSelectedIndex();
		Integer selectedComboBoxProductId = mapProducts
				.get(selectedComboBoxProductIndex);
		int intSelectedComboBoxProductId = selectedComboBoxProductId.intValue();
		Product product = productServiceImpl.findProductById(new BigDecimal(
				String.valueOf(intSelectedComboBoxProductId)));

		return product;
	}

	public boolean validateOrderItemFields() {
		boolean result = true;
		result = result
				&& (comboBoxOrderItemProductName.getSelectedIndex() > 0)
				&& (!"".equals(textFieldOrderItemQuantity.getText()) && isInt(textFieldOrderItemQuantity
						.getText()));
		return result;
	}

	public void saveOrderItemAndDispose() {
		OrderItem orderItem = getOrderItemFromOrderItemFields();
		orderItemServiceImpl.saveOrderItem(orderItem);
		dialog.dispose();
		orderItemPanelImpl.setInvoiceId(getInvoiceId());
		orderItemPanelImpl.populateOrderItemTable();
		invoicePanelImpl.setProductButtonsEnabled();
	}

	public boolean isInt(String textFieldValue) {
		try {
			Integer.parseInt(textFieldValue);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void dispose() {
		dialog.dispose();
	}

	@Override
	public int getInvoiceId() {
		return invoiceId;
	}

	@Override
	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;

	}

}
