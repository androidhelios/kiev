package com.chadik.kiev.view.dialog.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import org.springframework.stereotype.Component;

import com.chadik.kiev.service.IGenericJpaService;
import com.chadik.kiev.view.dialog.IDialogGeneric;
import com.chadik.kiev.view.panel.IPanelGeneric;

@Component
public abstract class DialogGenericImpl<T> implements IDialogGeneric<T> {
	
	private JDialog dialog;
	private JPanel contentPane;
	private JPanel panelAll;
	private JPanel panelFields;
	private JPanel panelFieldsContent;
	private JPanel panelButtons;
	private JPanel panelButtonsContent;
	private JButton buttonSave;
	private JButton buttonCancel;
	
	private IGenericJpaService genericJpaService;
	private IPanelGeneric panelGeneric;

	@Override
	public JDialog initDialog() {
		
		dialog = new JDialog();	
		dialog.setTitle(getDilogName());
		dialog.setResizable(false);
		
		contentPane = new JPanel();
		
		dialog.setContentPane(contentPane);		

		panelAll = new JPanel();
		panelAll.setLayout(new BorderLayout());
		
		panelFields = new JPanel();
		panelFields.setLayout(new BorderLayout());
		
		panelFieldsContent = getPanelFieldsContent();
		
		panelButtons = new JPanel();
		panelButtons.setLayout(new BorderLayout());
		panelButtons.setPreferredSize(new Dimension(400, 50));
		
		panelButtonsContent = new JPanel();
		panelButtonsContent.setLayout(new FlowLayout());
		
		buttonSave = new JButton("Зачувај");
		buttonSave.setPreferredSize(new Dimension(100, 25));
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getGenericJpaService().save(getT());
				dialog.dispose();
//				getPanelGeneric().populateTable();
			}
		});

		buttonCancel = new JButton("Откажи");
		buttonCancel.setPreferredSize(new Dimension(100, 25));
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
		
		panelButtonsContent.add(buttonSave);
		panelButtonsContent.add(buttonCancel);
		
		panelButtons.add(panelButtonsContent, BorderLayout.CENTER);
		
		panelFields.add(panelFieldsContent, BorderLayout.CENTER);
		
		panelAll.add(panelButtons, BorderLayout.SOUTH);
		panelAll.add(panelFields, BorderLayout.CENTER);
		
		contentPane.add(panelAll);
		
		dialog.pack();
		dialog.setVisible(true);
		dialog.setLocationRelativeTo(null);		
		
		return dialog;
	}
	
	public abstract String getDilogName();
	
	public abstract JPanel getPanelFieldsContent();
	
	public abstract IGenericJpaService getGenericJpaService();
	
	public abstract IPanelGeneric getPanelGeneric();
	
	public abstract T getT();
	

}
