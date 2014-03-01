package com.chadik.kiev.view.panel.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chadik.kiev.model.OrderItem;
import com.chadik.kiev.model.Supplier;
import com.chadik.kiev.service.IInvoiceService;
import com.chadik.kiev.service.IOrderItemService;
import com.chadik.kiev.service.ISupplierService;
import com.chadik.kiev.view.panel.IOrderItemPanel;

@Component
public class OrderItemPanelImpl implements IOrderItemPanel {

	private JPanel panelAll;

	private JPanel panelTableHolder;
	private JPanel panelTableHolderContent;
	private JPanel panelTableHolderContentTable;
	
	private DefaultTableModel defaultTableModel;
	private JTable table;
	
	private List<OrderItem> orderItems;
	
	@Autowired
	private IOrderItemService orderItemServiceImpl;
	@Autowired
	private IInvoiceService invoiceServiceImpl;

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
		defaultTableModel.setColumnIdentifiers(getTableCustomerColumnNames());

		panelTableHolderContent.add(panelTableHolderContentTable,
				BorderLayout.CENTER);

		panelTableHolder.add(panelTableHolderContent, BorderLayout.CENTER);

		panelAll.add(panelTableHolder, BorderLayout.CENTER);

		return panelAll;
	}
	
	public String[] getTableCustomerColumnNames() {
		return new String[] { "Реден Бр.", "Id", "Назив", "Мерна единица",
				"Телефонски број", "Email", "Забелешки" };
	}

	@Override
	public void populateOrderItemTable() {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void populateOrderItemTable() {
//		String selectedRowInvoiceId = "";
//		orderItems = orderItemServiceImpl.findAllOrderItems();
//
//		int i = 0;
//
//		defaultTableModel.setRowCount(0);
//
//		for (OrderItem orderItem : orderItems) {
//			if (orderItem.getInvoice().getInvoiceId())
//			defaultTableModel
//					.addRow(new String[] { Integer.toString(++i),
//							orderItem.getProduct().getProductName(),
//							orderItem.getProduct().getProductMeasurement(),
//							orderItem.getOrderItemQuantity(),
//							orderItem.getOrderItemQuantityPrice(),
//							orderItem.getOrderItemQuantityPriceWithoutTax(),
//							orderItem.getOrderItemTax(),
//							orderItem.getOrderItemQuantityTax(),
//							orderItem.getOrderItemQuantityTaxPrice(),
//							orderItem.getOrderAdditionalInfo() });
//		}
//
//		if (table.getRowCount() > 0) {
//			table.setRowSelectionInterval(table.getRowCount() - 1,
//					table.getRowCount() - 1);
//
//			selectedRowInvoiceId = (String) table.getValueAt(
//					table.getRowCount() - 1, 1);
//			OrderItem orderItem = getSupplierFromSupplierTable(selectedRowInvoiceId);
//			populateSupplierFields(supplier);
//		}
//
//		scrollPaneTable.validate();
//		verticalScrollBar.setValue(verticalScrollBar.getMaximum());
//		
//		setFieldsNonEditable();
//	}
	
//	public OrderItem getOrderItemFromInvoiceTable(String selectedRowInvoiceId) {
//		BigDecimal invoiceId = new BigDecimal(selectedRowInvoiceId);
//		return invoiceItemServiceImpl.findInvoiceById(invoiceId);
//	}

}
