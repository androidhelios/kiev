package com.chadik.kiev.view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class TraderPanel extends JPanel {

	// The data used as the titles for the table.
	String[] title = { "No.", "Country", "Player", "Position" };

	// The data used in the table, placed as a multi-dimensional array.
	Object[][] playerdata = {
			{ new Integer(4), "United States", "Sterling Davis", "Forward" },
			{ new Integer(6), "Germany", "Moritz Wohlers", "Forward/Centre" },
			{ new Integer(7), "United Kingdom", "Ross Hutton", "Centre" },
			{ new Integer(8), "Belgium", "Hugo Sterk", "Guard" },
			{ new Integer(10), "United Kingdom", "Andy Pearson", "Forward" },
			{ new Integer(11), "United States", "Robert Yanders", "Guard" },
			{ new Integer(12), "United Kingdom", "Graham Hunter", "Guard" },
			{ new Integer(14), "United Kingdom", "Julius Joseph",
					"Guard/Forward" },
			{ new Integer(15), "United Kingdom", "Gareth Murray", "Forward" },
			{ new Integer(21), "United States", "Maurice Hampton", "Guard" } };
	private JTable table;

	/**
	 * Create the panel.
	 */
	public TraderPanel() {
		setLayout(new BorderLayout());
		
		JPanel labelPanel = new JPanel();
		add(labelPanel, BorderLayout.CENTER);
		labelPanel.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(0, 0, 89, 23);
		labelPanel.add(btnNewButton);

		JPanel tablePanel = new JPanel();
		tablePanel.setPreferredSize(new Dimension(100, 100));
		add(tablePanel, BorderLayout.WEST);
		tablePanel.setLayout(new BorderLayout());

		JScrollPane scrollPane = new JScrollPane();
		tablePanel.add(scrollPane, BorderLayout.CENTER);

		table = new JTable(playerdata, title);
		scrollPane.setViewportView(table);

	}
}
