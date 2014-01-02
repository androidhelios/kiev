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
	
	public static void allignCells(JTable table, int alligment) {
		
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(alligment);
		
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
		}
	}
	
	public static void hideColumns(JTable table, int[] columns) {
		for (int i = 0; i < columns.length; i++) {
			hideColumn(table, i);
		}
	}

}
