package com.chadik.kiev.view.dialog.impl;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.chadik.kiev.view.dialog.IOrderItemDialog;

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
	
	
	@Override
	public JDialog initOrderItemDialog() {
		// TODO Auto-generated method stub
		return null;
	}

}
