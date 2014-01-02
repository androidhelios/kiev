package com.chadik.kiev.util;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public final class TableUtil {
	
	private TableUtil() {
		
	}	
	
	public static void hideColumn(JTable table, int columnIndex) {
		table.getColumnModel().getColumn(columnIndex).setMaxWidth(0);
		table.getColumnModel().getColumn(columnIndex).setMinWidth(0);
		table.getColumnModel().getColumn(columnIndex).setPreferredWidth(0);
	}
	
	public static void centerCells(JTable table) {
		
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
		}
	}

}
