package com.chadik.kiev.view.panel.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chadik.kiev.model.Invoice;
import com.chadik.kiev.model.OrderItem;
import com.chadik.kiev.service.IInvoiceService;
import com.chadik.kiev.service.IOrderItemService;
import com.chadik.kiev.util.TableUtil;
import com.chadik.kiev.view.panel.IInvoicePanel;
import com.chadik.kiev.view.panel.IOrderItemPanel;

@Component
public class OrderItemPanelImpl implements IOrderItemPanel {

	private JPanel panelAll;

	private JPanel panelTableHolder;
	private JPanel panelTableHolderContent;
	private JPanel panelTableHolderContentTable;

	private DefaultTableModel defaultTableModel;
	private JTable table;

	private JScrollPane scrollPaneTable;
	private JScrollBar verticalScrollBar;

	private Invoice invoice;
	private List<OrderItem> orderItems;

	private DecimalFormat decimalFormat;

	@Autowired
	private IOrderItemService orderItemServiceImpl;
	@Autowired
	private IInvoiceService invoiceServiceImpl;
	@Autowired
	private IInvoicePanel invoicePanelImpl;

	@Override
	public JPanel initOrderItemPanel() {
		panelAll = new JPanel();
		panelAll.setLayout(new BorderLayout());

		panelTableHolder = new JPanel();
		panelTableHolder.setLayout(new BorderLayout());

		panelTableHolderContent = new JPanel();
		panelTableHolderContent.setLayout(new BorderLayout());

		panelTableHolderContentTable = new JPanel();
		panelTableHolderContentTable.setLayout(new BorderLayout());
		panelTableHolderContentTable.setPreferredSize(new Dimension(400, 300));

		defaultTableModel = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		defaultTableModel.setColumnIdentifiers(getTableOrderItemColumnNames());

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(defaultTableModel);

		TableUtil.hideColumns(table, getTableOrderItemHiddenColumns());

		TableUtil.allignCells(table, SwingConstants.CENTER);

		table.getColumnModel().getColumn(0).setMaxWidth(100);

		scrollPaneTable = new JScrollPane(table);

		verticalScrollBar = scrollPaneTable.getVerticalScrollBar();

		panelTableHolderContentTable.add(scrollPaneTable);

		panelTableHolderContent.add(panelTableHolderContentTable,
				BorderLayout.CENTER);

		panelTableHolder.add(panelTableHolderContent, BorderLayout.CENTER);

		panelAll.add(panelTableHolder, BorderLayout.CENTER);
		
		setOrderItemTable(table);

		if (invoice != null) {
			populateOrderItemTable();
		}

		return panelAll;
	}

	@Override
	public void populateOrderItemTable() {
		decimalFormat = new DecimalFormat("#.##");

		double invoiceTotalQuantityPriceWithoutTax = 0;
		double invoiceTotalQuantityTax = 0;
		double invoiceTotalQuantityPrice = 0;

		Invoice invoice = getInvoice();
		orderItems = invoice.getOrderItems();

		int i = 0;

		defaultTableModel.setRowCount(0);

		for (OrderItem orderItem : orderItems) {
			defaultTableModel.addRow(new String[] { Integer.toString(++i),
					orderItem.getOrderItemId().toString(),
					orderItem.getProduct().getProductName(),
					orderItem.getProduct().getProductMeasurement(),
					orderItem.getOrderItemQuantity(),
					orderItem.getProduct().getProductPrice(),
					orderItem.getOrderItemQuantityPriceWithoutTax(),
					orderItem.getProduct().getProductTax(),
					orderItem.getOrderItemQuantityTax(),
					orderItem.getOrderItemQuantityPrice(),
					orderItem.getOrderAdditionalInfo() });

			String orderItemTotalQuantityPriceWithoutTax = orderItem
					.getOrderItemQuantityPriceWithoutTax();
			String orderItemTotalQuantityTax = orderItem
					.getOrderItemQuantityTax();
			String orderItemTotalQuantityPrice = orderItem
					.getOrderItemQuantityPrice();

			invoiceTotalQuantityPriceWithoutTax = invoiceTotalQuantityPriceWithoutTax
					+ Double.parseDouble(orderItemTotalQuantityPriceWithoutTax.replace(",", "."));
			invoiceTotalQuantityTax = invoiceTotalQuantityTax
					+ Double.parseDouble(orderItemTotalQuantityTax.replace(",", "."));
			invoiceTotalQuantityPrice = invoiceTotalQuantityPrice
					+ Double.parseDouble(orderItemTotalQuantityPrice.replace(",", "."));
		}

		if (table.getRowCount() > 0) {
			invoicePanelImpl.setProductButtonsEnabled();
			invoicePanelImpl.setInvoiceOrderItemTotalValues(
					decimalFormat.format(invoiceTotalQuantityPriceWithoutTax),
					decimalFormat.format(invoiceTotalQuantityTax),
					decimalFormat.format(invoiceTotalQuantityPrice));
		}

		scrollPaneTable.validate();
		verticalScrollBar.setValue(verticalScrollBar.getMaximum());

	}

	public String[] getTableOrderItemColumnNames() {
		return new String[] { "Реден Бр.", "Id", "Назив", "Мерна единица",
				"Количина", "Цена без данок", "Износ без данок", "ДДВ",
				"Износ на ДДВ", "Вкупен износ со ДДВ",
				"Дополнителни информации" };
	}

	public int[] getTableOrderItemHiddenColumns() {
		return new int[] { 1, 10 };
	}

	@Override
	public Invoice getInvoice() {
		return invoice;
	}

	@Override
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	@Override
	public JTable getOrderItemTable() {
		return table;
	}

	@Override
	public void setOrderItemTable(JTable orderItemTable) {
		this.table = orderItemTable;
	}

}
