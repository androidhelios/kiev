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
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {

		try {

			ServerSocket socket = new ServerSocket(9999, 10,
					InetAddress.getLocalHost());

			ApplicationContext appContext = new ClassPathXmlApplicationContext(
					"applicationContext.xml");

			SupplierServiceImpl supplierServiceImpl = (SupplierServiceImpl) appContext
					.getBean("supplierServiceImpl");
			CustomerServiceImpl customerServiceImpl = (CustomerServiceImpl) appContext
					.getBean("customerServiceImpl");
			ProductServiceImpl productServiceImpl = (ProductServiceImpl) appContext
					.getBean("productServiceImpl");

			List<Supplier> supplierList = supplierServiceImpl
					.findAllSuppliers();

			if (supplierList.size() < 1) {
				Supplier supplier = createMainSupplier();
				supplierServiceImpl.saveSupplier(supplier);
			}

			if (getAppMode().equals("test")) {
				Customer customer = createCustomer();
				customerServiceImpl.saveCustomer(customer);

				Product product = createProduct();
				productServiceImpl.saveProduct(product);

			}

			FrameMain frameMain = (FrameMain) appContext.getBean("frameMain");
			frameMain.initFrame();
		} catch (java.net.BindException b) {
			Object[] options = { "OK" };
			int input = JOptionPane.showOptionDialog(null,
					"ПСФ - в(0.0.1) е веќе стартувана", "Грешка",
					JOptionPane.ERROR_MESSAGE,
					JOptionPane.ERROR_MESSAGE, null, options,
					options[0]);
			
			if (input == 0) {
				System.exit(0);
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	public static String getAppMode() {
		return "live";
	}

	public static Supplier createMainSupplier() {
		Supplier supplier = new Supplier();
		supplier.setSupplierName("Име");
		supplier.setSupplierAddress("Адреса");
		supplier.setSupplierRegistryNumber("0123456789");
		supplier.setSupplierPhoneNumber("070123456");
		supplier.setSupplierEmail("Email");
		supplier.setSupplierUserName("korisnik");
		supplier.setSupplierPassword("korisnik1");
		supplier.setSupplierAdditionalInfo("Дополнителни информации");

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
