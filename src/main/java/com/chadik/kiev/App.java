package com.chadik.kiev;

import com.chadik.kiev.model.Supplier;
import com.chadik.kiev.service.ISupplierService;
import com.chadik.kiev.service.impl.SupplierServiceImpl;
import com.chadik.kiev.view.FrameMain;


import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {
        
        ApplicationContext appContext = 
    	  new ClassPathXmlApplicationContext("applicationContext.xml");
 
    	SupplierServiceImpl supplierServiceImpl = (SupplierServiceImpl) appContext.getBean("supplierServiceImpl");
    	List<Supplier> supplierList = supplierServiceImpl.findAllSuppliers();
    	
    	if (supplierList.size() < 1) {
    		supplierServiceImpl.saveSupplier(createMainSupplier());
    	}
        
        FrameMain frameMain = (FrameMain)appContext.getBean("frameMain");
        frameMain.initFrame();
        
    }
    
    public static Supplier createMainSupplier() {
        Supplier supplier = new Supplier();
        supplier.setSupplierName("supplierName");
        supplier.setSupplierAddress("supplierAddress");
        supplier.setSupplierBankAccount("supplierBankAccount");
        supplier.setSupplierBankName("supplierBankName");
        supplier.setSupplierRegistryNumber("supplierRegistryNumber");
        supplier.setSupplierEmail("supplierEmail");
        supplier.setSupplierPhoneNumber("supplierPhoneNumber");
        supplier.setSupplierAdditionalInfo("supplierAdditionalInfo");
    	
        return supplier;    	
    }
}
