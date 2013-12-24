package com.chadik.kiev.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.chadik.kiev.model.Trader;
import com.chadik.kiev.service.IGenericJpaService;

@Component
public class PanelTrader extends JPanel {

	private JTable table;

	private IGenericJpaService traderJpaServiceImpl;	
	
	/**
	 * Create the panel.
	 */
	@Autowired
	public PanelTrader(@Qualifier("traderJpaServiceImpl") IGenericJpaService traderJpaServiceImpl) {
		this.traderJpaServiceImpl = traderJpaServiceImpl;
		initPaneTraider();
	}

	public void initPaneTraider() {
		setLayout(new BorderLayout());

		JPanel labelPanel = new JPanel();
		add(labelPanel, BorderLayout.CENTER);
		labelPanel.setLayout(null);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(0, 0, 89, 23);
		labelPanel.add(btnNewButton);

		JPanel tablePanel = new JPanel();
		tablePanel.setPreferredSize(new Dimension(300, 300));
		add(tablePanel, BorderLayout.WEST);
		tablePanel.setLayout(new BorderLayout());

		JScrollPane scrollPane = new JScrollPane();
		tablePanel.add(scrollPane, BorderLayout.CENTER);

		DefaultTableModel defaultTableModel = new DefaultTableModel();
		table = new JTable();
		table.setModel(defaultTableModel);
		List<Trader> traders = new ArrayList<Trader>();
		traders = traderJpaServiceImpl.getAll();
		defaultTableModel.setColumnIdentifiers(new String[] { "traderId",
				"traderName", "traderBankName", "traderBankAccount",
				"traderAddress" });

		for (Trader trader : traders) {
			defaultTableModel.addRow(new String[] {
					trader.getTraderId().toString(), trader.getTraderName(),
					trader.getTraderBankName(), trader.getTraderBankAccount(),
					trader.getTraderAddress() });
		}

		scrollPane.setViewportView(table);

	}

}
