package com.chadik.kiev.view.panel.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chadik.kiev.model.Invoice;
import com.chadik.kiev.model.OrderItem;
import com.chadik.kiev.model.Supplier;
import com.chadik.kiev.service.IInvoiceService;
import com.chadik.kiev.service.IOrderItemService;
import com.chadik.kiev.service.ISupplierService;
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

		if (invoice != null) {
			populateOrderItemTable();
		}

		return panelAll;
	}

	@Override
	public void populateOrderItemTable() {
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
		}

		if (table.getRowCount() > 0) {
			invoicePanelImpl.setProductButtonsEnabled();
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

}
