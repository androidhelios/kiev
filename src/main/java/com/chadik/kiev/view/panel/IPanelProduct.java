package com.chadik.kiev.view.panel;

import com.chadik.kiev.model.Product;

public interface IPanelProduct extends IPanelGeneric<Product> {
	
	public abstract String[] getTableProductColumnNames();
	
	public int[] getTableProductHiddenColumns();
	
	public void populateTableProduct();

}