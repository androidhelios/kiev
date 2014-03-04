package com.chadik.kiev.util;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

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
			hideColumn(table, columns[i]);
		}
	}

	public static ArrayList<String> getPresentedTableColumns(JTable table) {
		ArrayList<String> presentedTableColumns = new ArrayList<String>();

		JTableHeader tableHeader = table.getTableHeader();
		TableColumnModel tableColumnModel = tableHeader.getColumnModel();
		for (int i = 0, y = tableColumnModel.getColumnCount(); i < y; i++) {
			TableColumn tableColumn = tableColumnModel.getColumn(i);
			presentedTableColumns.add(tableColumn.getHeaderValue().toString());
		}

		return presentedTableColumns;
	}

}
