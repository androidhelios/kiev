package com.chadik.kiev;

import com.chadik.kiev.model.Customer;
import com.chadik.kiev.model.Product;
import com.chadik.kiev.model.Supplier;
import com.chadik.kiev.service.ISupplierService;
import com.chadik.kiev.service.impl.CustomerServiceImpl;
import com.chadik.kiev.service.impl.ProductServiceImpl;
import com.chadik.kiev.service.impl.SupplierServiceImpl;
import com.chadik.kiev.view.FrameMain;
import com.chadik.kiev.view.panel.IPasswordPanel;
import com.chadik.kiev.view.panel.impl.PasswordPanelImpl;


import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {
        
        ApplicationContext appContext = 
    	  new ClassPathXmlApplicationContext("applicationContext.xml");
 
    	SupplierServiceImpl supplierServiceImpl = (SupplierServiceImpl) appContext.getBean("supplierServiceImpl");
    	CustomerServiceImpl customerServiceImpl = (CustomerServiceImpl) appContext.getBean("customerServiceImpl");
    	ProductServiceImpl productServiceImpl = (ProductServiceImpl) appContext.getBean("productServiceImpl");
    	
    	List<Supplier> supplierList = supplierServiceImpl.findAllSuppliers();
    	
    	if (supplierList.size() < 1) {
    		Supplier supplier = createMainSupplier();
    		supplierServiceImpl.saveSupplier(supplier);
    	}
    	
    	if (getAppMode().endsWith("test")) {
    		Customer customer = createCustomer();
    		customerServiceImpl.saveCustomer(customer);
    		
    		Product product = createProduct();
    		productServiceImpl.saveProduct(product);
    		
    	}
        
        FrameMain frameMain = (FrameMain)appContext.getBean("frameMain");
        frameMain.initFrame();
        
    }
    
    public static String getAppMode() {
    	return "test";
    }
    
    public static Supplier createMainSupplier() {
        Supplier supplier = new Supplier();
        supplier.setSupplierName("supplierName");
        supplier.setSupplierAddress("supplierAddress");
        supplier.setSupplierBankAccount("supplierBankAccount");
        supplier.setSupplierBankName("supplierBankName");
        supplier.setSupplierRegistryNumber("supplierRegistryNumber");
        supplier.setSupplierPhoneNumber("supplierPhoneNumber");
        supplier.setSupplierEmail("supplierEmail");
        supplier.setSupplierUserName("supplierUserName");
        supplier.setSupplierPassword("supplierPassword");
        supplier.setSupplierAdditionalInfo("supplierAdditionalInfo");
    	
        return supplier;    	
    }
    
    public static Customer createCustomer() {
    	Customer customer = new Customer();
    	customer.setCustomerName("customerName");
    	customer.setCustomerAddress("customerAddress");
    	customer.setCustomerPhoneNumber("customerPhoneNumber");
    	customer.setCustomerEmail("customerEmail");
    	customer.setCustomerAdditionalInfo("customerAdditionalInfo");
    	
        return customer;    	
    }
    
    public static Product createProduct() {
    	Product product = new Product();
    	product.setProductName("productName");
    	product.setProductMeasurement("0.5l");
    	product.setProductTax("18");
    	product.setProductPrice("20");
    	product.setProductTaxPrice("21");
    	product.setProductAdditionalInfo("productAdditionalInfo");
    	
        return product;    	
    }
}
