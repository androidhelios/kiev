package com.chadik.kiev.util;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public final class PanelUtil {

	private PanelUtil() {

	}

	public static void switchPanel(JFrame frameHolder, JPanel panelHolder,
			JPanel switchPanel, String panelAlligment) {
		panelHolder.removeAll();
		switchPanel.setVisible(true);
		panelHolder.add(switchPanel, panelAlligment);
		frameHolder.validate();

	}
	
	public static JSeparator createJSeparator() {
		JSeparator separator = new JSeparator(SwingConstants.VERTICAL);  
	    Dimension dimension = separator.getPreferredSize();  
	    dimension.height = 25;  
	    separator.setPreferredSize(dimension);
    	return separator;
	}

}
