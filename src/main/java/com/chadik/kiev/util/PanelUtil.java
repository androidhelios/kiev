package com.chadik.kiev.util;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public final class PanelUtil {
	
	private PanelUtil() {
		
	}
	
	public static void switchPanel(JFrame frameHolder, JPanel panelHolder, JPanel switchPanel, String panelAlligment) {
		panelHolder.removeAll();
		switchPanel.setVisible(true);
		panelHolder.add(switchPanel, panelAlligment);
		frameHolder.validate();

	}

}
