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
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chadik.kiev.model.Product;
import com.chadik.kiev.service.IProductService;
import com.chadik.kiev.util.PanelUtil;
import com.chadik.kiev.util.TableUtil;
import com.chadik.kiev.view.FrameMain;
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

	private JButton buttonNew;
	private JButton buttonEdit;
	private JButton buttonDelete;
	private JButton buttonSave;
	private JButton buttonCancel;

	private DecimalFormat decimalFormat;
	
	private Map<String, String> mapTaxInfo;

	private Color originalTextFieldColor;
	private Color nonEditableTextFieldColor;
	private Color mandatoryTextFieldColor;

	private String selectedProductTableRow;

	private boolean editMode;

	private List<Product> products;

	@Autowired
	private IProductService productServiceImpl;
	@Autowired
	private IProductDialog productDialogImpl;
	@Autowired
	private FrameMain frameMain;

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
		panelTableHolderContentTable.setBackground(new Color(224, 224, 224));
		panelTableHolderContentTable.setBorder(new TitledBorder(
				"Листа на артикли"));

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
		defaultTableModel.setColumnIdentifiers(getTableProductColumnNames());

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (isEditMode()) {
					table.setEnabled(false);
				} else {
					int row = table.getSelectedRow();
					String selectedRowProductId = (String) table.getValueAt(
							row, 1);
					Product product = getProductFromProductTable(selectedRowProductId);
					populateProductFields(product);
					setProductInfoButtonsDisabled();
				}
			}
		});

		table.setModel(defaultTableModel);

		TableUtil.hideColumns(table, getTableProductHiddenColumns());

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
				productDialogImpl.initProductDialog();
			}
		});

		buttonEdit = new JButton("Измени");
		buttonEdit.setPreferredSize(new Dimension(100, 25));
		buttonEdit.setEnabled(false);
		buttonEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setProductFieldsEditable();
				setProductInfoButtonsEnabled();
				setProductTableButtonsDisabled();
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
				deleteProduct();
				JOptionPane.showMessageDialog(frameMain.getMainFrame(),
						"Артиклот е избришан", "Информација",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		int spacing = 5;
		int weightLabel = 125;
		int height = 25;
		int weightTextField = 250;
		int xLabel = 10;
		int xTextField = xLabel + weightLabel + spacing;
		int y = 25;

		labelProductName = new JLabel("Име:");
		labelProductName.setBounds(xLabel, y, weightLabel, height);
		labelProductName.setForeground(mandatoryTextFieldColor);

		textFieldProductName = new JTextField();
		textFieldProductName.setBounds(xTextField, y, weightTextField, height);
		textFieldProductName.setMargin(new Insets(2, 2, 2, 2));

		originalTextFieldColor = textFieldProductName.getBackground();
		nonEditableTextFieldColor = new Color(255, 255, 204);

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
		comboboxProductTaxShown.setEnabled(false);
		populateComboboxProductTaxShown();
		comboboxProductTaxShown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox = (JComboBox) e.getSource();
				int selectedComboBoxTaxShownIndex = comboBox.getSelectedIndex();
				if (selectedComboBoxTaxShownIndex > 0) {
					String selectedComboBoxTaxShownTaxValue = mapTaxInfo
							.get(String.valueOf(comboBox.getSelectedItem()));
					textFieldProductTax.setText(selectedComboBoxTaxShownTaxValue);
					if (!"".equals(textFieldProductTaxPrice.getText())) {
						calculateProductPrice(textFieldProductTax.getText(), textFieldProductTaxPrice
								.getText());
					}
				} else {
					textFieldProductTax.setText("");
					textFieldProductPrice.setText("");
					textFieldProductTaxPrice.setText("");
				}
			}
		});

		y = y + height + spacing;

		labelProductTax = new JLabel("Данок:");
		labelProductTax.setBounds(xLabel, y, weightLabel, height);
//		labelProductTax.setForeground(mandatoryTextFieldColor);

		textFieldProductTax = new JTextField();
		textFieldProductTax.setBounds(xTextField, y, weightTextField, height);
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

		labelProductPrice = new JLabel("Цена без данок:");
		labelProductPrice.setBounds(xLabel, y, weightLabel, height);
		// labelProductPrice.setForeground(mandatoryTextFieldColor);

		textFieldProductPrice = new JTextField();
		textFieldProductPrice.setBounds(xTextField, y, weightTextField, height);
		textFieldProductPrice.setEditable(false);
		textFieldProductPrice.setBackground(nonEditableTextFieldColor);
		textFieldProductPrice.setMargin(new Insets(2, 2, 2, 2));

		y = y + height + spacing;

		labelProductTaxPrice = new JLabel("Цена со данок:");
		labelProductTaxPrice.setBounds(xLabel, y, weightLabel, height);
		labelProductTaxPrice.setForeground(mandatoryTextFieldColor);

		textFieldProductTaxPrice = new JTextField();
		textFieldProductTaxPrice.setBounds(xTextField, y, weightTextField,
				height);
		textFieldProductTaxPrice.setEditable(false);
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
		buttonSave.setEnabled(false);
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validateProductFields()) {
					saveProduct();
					setEditMode(false);
					table.setEnabled(true);
					JOptionPane.showMessageDialog(frameMain.getMainFrame(),
							"Промената е запишана", "Информација",
							JOptionPane.INFORMATION_MESSAGE);
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
				String selectedRowProductId = (String) table.getValueAt(row, 1);
				Product product = getProductFromProductTable(selectedRowProductId);
				populateProductFields(product);
				setProductFieldsNonEditable();
				setProductInfoButtonsDisabled();
				setProductTableButtonsEnabled();
				setEditMode(false);
				table.setEnabled(true);
				JOptionPane.showMessageDialog(frameMain.getMainFrame(),
						"Промената е откажана", "Информација",
						JOptionPane.INFORMATION_MESSAGE);
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

		panelInfoHolderContentInfo
				.add(labelProductName, firstLabelConstrains());
		panelInfoHolderContentInfo.add(textFieldProductName,
				textFieldConstraints());

		panelInfoHolderContentInfo.add(labelProductMeasurement,
				labelConstraints());
		panelInfoHolderContentInfo.add(textFieldProductMeasurement,
				textFieldConstraints());
		
		panelInfoHolderContentInfo.add(labelProductTaxShown, labelConstraints());
		panelInfoHolderContentInfo.add(comboboxProductTaxShown, textFieldConstraints());

		panelInfoHolderContentInfo.add(labelProductTax, labelConstraints());
		panelInfoHolderContentInfo.add(textFieldProductTax,
				textFieldConstraints());

		panelInfoHolderContentInfo
				.add(labelProductTaxPrice, labelConstraints());
		panelInfoHolderContentInfo.add(textFieldProductTaxPrice,
				textFieldConstraints());

		panelInfoHolderContentInfo.add(labelProductPrice, labelConstraints());
		panelInfoHolderContentInfo.add(textFieldProductPrice,
				textFieldConstraints());

		panelInfoHolderContentInfo.add(labelProductAdditionalInfo,
				labelConstraints());
		panelInfoHolderContentInfo.add(textFieldProductAdditionalInfo,
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

		setSelectedProductTableRow("");
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
					product.getProductTaxPrice(),
					product.getProductAdditionalInfo() });
		}

		if (table.getRowCount() > 0) {
			int selectedRow = table.getRowCount() - 1;

			if (!getSelectedProductTableRow().equals("")) {
				selectedRow = Integer.parseInt(getSelectedProductTableRow());
			}

			table.setRowSelectionInterval(selectedRow, selectedRow);

			selectedRowProductId = (String) table.getValueAt(selectedRow, 1);
			Product product = getProductFromProductTable(selectedRowProductId);
			populateProductFields(product);

			setProductTableButtonsEnabled();

		}

		scrollPaneTable.validate();
		verticalScrollBar.setValue(verticalScrollBar.getMaximum());

		setProductFieldsNonEditable();

		setSelectedProductTableRow("");

	}

	public void populateProductFields(Product product) {
		textFieldProductId.setText(product.getProductId().toString());
		textFieldProductName.setText(product.getProductName());
		textFieldProductMeasurement.setText(product.getProductMeasurement());
		if (product.getProductTax().equals("5")) {
			comboboxProductTaxShown.setSelectedIndex(1);
		} else if (product.getProductTax().equals("18")) {
			comboboxProductTaxShown.setSelectedIndex(2);
		} else {
			comboboxProductTaxShown.setSelectedIndex(0);
		} 
		if (product.getProductTax().equals("5")) {
			textFieldProductTax.setText("4,762");
		} else if (product.getProductTax().equals("18")) {
			textFieldProductTax.setText("15,2542");
		} else {
			textFieldProductTax.setText("");
		}
//		textFieldProductTax.setText(product.getProductTax());
		textFieldProductPrice.setText(product.getProductPrice());
		textFieldProductTaxPrice.setText(product.getProductTaxPrice());
		textFieldProductAdditionalInfo.setText(product
				.getProductAdditionalInfo());
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

	public void clearProductFields() {
		textFieldProductId.setText("");
		textFieldProductName.setText("");
		textFieldProductMeasurement.setText("");
		comboboxProductTaxShown.setSelectedIndex(0);
		textFieldProductTax.setText("");
		textFieldProductPrice.setText("");
		textFieldProductTaxPrice.setText("");
		textFieldProductAdditionalInfo.setText("");
	}

	public void calculateProductPrice(String productTax, String productPrice) {
		decimalFormat = new DecimalFormat("#.##");

		if (!"".equals(textFieldProductTax.getText())
				&& isValidDecimal(textFieldProductTax.getText())
				&& !"".equals(productPrice) && isValidDecimal(productPrice)) {

			double doubleProductTax = Double.parseDouble(productTax);
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

	public String[] getTableProductColumnNames() {
		return new String[] { "Реден Бр.", "Id", "Назив", "Мерна Единица",
				"Данок", "Цена без данок", "Цена со данок", "Забелешки" };
	}

	public int[] getTableProductHiddenColumns() {
		return new int[] { 1, 4, 5, 6, 7 };
	}

	public Product getProductFromProductTable(String selectedRowProductId) {
		BigDecimal productId = new BigDecimal(selectedRowProductId);
		return productServiceImpl.findProductById(productId);
	}

	public Product getProductFromProductFields() {
		int row = table.getSelectedRow();
		String selectedRowProductId = (String) table.getValueAt(row, 1);
		Product product = getProductFromProductTable(selectedRowProductId);
		product.setProductName(textFieldProductName.getText());
		product.setProductMeasurement(textFieldProductMeasurement.getText());
		product.setProductTax(String.valueOf(comboboxProductTaxShown.getSelectedItem()));
		product.setProductPrice(textFieldProductPrice.getText());
		product.setProductTaxPrice(textFieldProductTaxPrice.getText());
		product.setProductAdditionalInfo(textFieldProductAdditionalInfo
				.getText());

		return product;
	}

	@Override
	public String getSelectedProductTableRow() {
		return selectedProductTableRow;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	@Override
	public void setSelectedProductTableRow(String selectedProductTableRow) {
		this.selectedProductTableRow = selectedProductTableRow;
	}

	public void setProductFieldsNonEditable() {
		textFieldProductName.setEditable(false);
		textFieldProductName.setBackground(nonEditableTextFieldColor);
		textFieldProductMeasurement.setEditable(false);
		textFieldProductMeasurement.setBackground(nonEditableTextFieldColor);
		comboboxProductTaxShown.setEnabled(false);
		textFieldProductTax.setEditable(false);
		textFieldProductTax.setBackground(nonEditableTextFieldColor);
		// textFieldProductPrice.setEditable(false);
		// textFieldProductPrice.setBackground(nonEditableTextFieldColor);
		textFieldProductTaxPrice.setEditable(false);
		textFieldProductTaxPrice.setBackground(nonEditableTextFieldColor);
		textFieldProductAdditionalInfo.setEditable(false);
		textFieldProductAdditionalInfo.setBackground(nonEditableTextFieldColor);
		textFieldProductId.setEditable(false);
		textFieldProductId.setBackground(nonEditableTextFieldColor);
	}

	public void setProductFieldsEditable() {
		textFieldProductName.setEditable(true);
		textFieldProductName.setBackground(originalTextFieldColor);
		textFieldProductMeasurement.setEditable(true);
		textFieldProductMeasurement.setBackground(originalTextFieldColor);
		comboboxProductTaxShown.setEnabled(true);
		textFieldProductTax.setEditable(true);
		textFieldProductTax.setBackground(originalTextFieldColor);
		// textFieldProductPrice.setEditable(true);
		// textFieldProductPrice.setBackground(originalTextFieldColor);
//		textFieldProductTaxPrice.setEditable(true);
		textFieldProductTaxPrice.setBackground(originalTextFieldColor);
		textFieldProductAdditionalInfo.setEditable(true);
		textFieldProductAdditionalInfo.setBackground(originalTextFieldColor);
		textFieldProductId.setEditable(true);
		textFieldProductId.setBackground(originalTextFieldColor);
	}

	public void setProductTableButtonsEnabled() {
		buttonNew.setEnabled(true);
		if (table.getRowCount() > 0) {
			buttonEdit.setEnabled(true);
			buttonDelete.setEnabled(true);
		}
	}

	public void setProductTableButtonsDisabled() {
		buttonEdit.setEnabled(false);
		buttonDelete.setEnabled(false);
	}

	public void setProductInfoButtonsEnabled() {
		buttonSave.setEnabled(true);
		buttonCancel.setEnabled(true);
	}

	public void setProductInfoButtonsDisabled() {
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
						.getText())))
				&& (comboboxProductTaxShown.getSelectedIndex() > 0);
		return result;
	}

	public boolean isValidDecimal(String textFieldValue) {
		boolean result = false;

		final String regularExpression = "\\d+([,]\\d{1,2})?";

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

	public void saveProduct() {
		Product product = getProductFromProductFields();
		productServiceImpl.saveProduct(product);
		int row = table.getSelectedRow();
		String selectedRow = Integer.toString(row);
		setSelectedProductTableRow(selectedRow);
		populateProductTable();
		setProductFieldsNonEditable();
		setProductInfoButtonsDisabled();
	}

	public void deleteProduct() {
		int row = table.getSelectedRow();
		String selectedRowProductId = (String) table.getValueAt(row, 1);
		Product product = getProductFromProductTable(selectedRowProductId);
		productServiceImpl.deleteProduct(product);
		String selectedRow = Integer.toString(row);
		int intSelectedRow = Integer.parseInt(selectedRow);

		clearProductFields();

		buttonEdit.setEnabled(false);
		buttonDelete.setEnabled(false);

		if (table.getRowCount() - 1 > intSelectedRow) {
			setSelectedProductTableRow(selectedRow);
		}

		populateProductTable();

		if (table.getRowCount() > 0) {
			buttonEdit.setEnabled(true);
			buttonDelete.setEnabled(true);

		}

		setProductFieldsNonEditable();
		setProductInfoButtonsDisabled();
	}

	public GridBagConstraints productPanelConstraints() {
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
		GridBagConstraints c = productPanelConstraints();
		c.anchor = GridBagConstraints.BASELINE_LEADING;
		c.weightx = 0.0;
		return c;
	}

	public GridBagConstraints textFieldConstraints() {
		GridBagConstraints c = productPanelConstraints();
		c.anchor = GridBagConstraints.BASELINE;
		c.weightx = 1.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = GridBagConstraints.REMAINDER;
		return c;
	}

	public GridBagConstraints lastComponentConstrains() {
		GridBagConstraints c = productPanelConstraints();
		c.anchor = GridBagConstraints.BASELINE;
		c.weightx = 1.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weighty = 1.0;
		return c;
	}

}
