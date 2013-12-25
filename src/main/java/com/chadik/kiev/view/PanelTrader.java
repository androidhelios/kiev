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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.chadik.kiev.model.Trader;
import com.chadik.kiev.service.IGenericJpaService;
import com.chadik.kiev.service.impl.TraderJpaServiceImpl;
import java.awt.Color;

@Component
public class PanelTrader extends JPanel {
	private IGenericJpaService traderJpaServiceImpl;	
	
	@Autowired
	public PanelTrader(@Qualifier("traderJpaServiceImpl") IGenericJpaService traderJpaServiceImpl) {
		this.traderJpaServiceImpl = traderJpaServiceImpl;
		setLayout(new BorderLayout(0, 0));
		setPreferredSize(new Dimension(800, 600));
		
		JPanel panelTraderTable = new JPanel();
		panelTraderTable.setBackground(Color.YELLOW);
		panelTraderTable.setPreferredSize(new Dimension(400, 600));
		add(panelTraderTable, BorderLayout.WEST);
		panelTraderTable.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTraderTableHolder = new JPanel();
		panelTraderTableHolder.setPreferredSize(new Dimension(400, 550));
		panelTraderTableHolder.setBackground(Color.ORANGE);
		panelTraderTable.add(panelTraderTableHolder, BorderLayout.NORTH);
		
		JPanel panelTraderButtonHolder = new JPanel();
		panelTraderButtonHolder.setPreferredSize(new Dimension(400, 50));
		panelTraderButtonHolder.setBackground(Color.PINK);
		panelTraderTable.add(panelTraderButtonHolder);
		
		JPanel panelTraderInfo = new JPanel();
		panelTraderInfo.setBackground(Color.RED);
		add(panelTraderInfo, BorderLayout.CENTER);
//		initPaneTraider();
	}

//	public void initPaneTraider() {
//
//	}

}
