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
        System.out.println("hello supplierServiceImpl");
        
        Supplier supplier = new Supplier();
        supplier.setSupplierName("ivan1");
        supplier.setSupplierAddress("ivan2");
        supplier.setSupplierBankAccount("ivan3");
        supplier.setSupplierBankName("ivan4");
        supplier.setSupplierRegistryNumber("pile5");        
        
        supplierServiceImpl.saveSupplier(supplier);
        
        supplier.setSupplierRegistryNumber("чадиковски");        
        
        supplierServiceImpl.saveSupplier(supplier);        

        System.out.println("bye " + supplier.getSupplierName());
        
        FrameMain frameMain = (FrameMain)appContext.getBean("frameMain");
        frameMain.initFrame(); 
        
        List<Supplier> suppliersNo = new ArrayList<Supplier>();
        suppliersNo = supplierServiceImpl.findAllSuppliers();
        
        Supplier supplierSecond = suppliersNo.get(0);
        System.out.println("ovovjv:" + supplierSecond.getSupplierBankName());
        
        System.out.println("no " + suppliersNo.size());
        
   	
    }
}
