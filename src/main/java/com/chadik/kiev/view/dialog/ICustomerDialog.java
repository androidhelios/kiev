package com.chadik.kiev.view.dialog;

import javax.swing.JDialog;
import javax.swing.JFrame;

public interface ICustomerDialog {
	
	public JDialog initCustomerDialog();
	
	public JFrame getFrame();
	
	public void seFrame(JFrame frame);

}
