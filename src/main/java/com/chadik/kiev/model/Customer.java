package com.chadik.kiev.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
public class Customer implements Serializable {

	@Id
	@GeneratedValue
	private Integer customerId;
	@NotNull(message = "Името на клиентот не е пополнето")
	private String customerName;
	@NotNull(message = "Адресата на клиентот не е пополнета")
	private String customerAddress;
	private String customerPhoneNumber;
	private String customerEmail;
	private String customerAdditionalInfo;
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	@NotNull
	@Valid
	private List<Invoice> invoices = new ArrayList<Invoice>();

	public Customer() {
		
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerAdditionalInfo() {
		return customerAdditionalInfo;
	}

	public void setCustomerAdditionalInfo(String customerAdditionalInfo) {
		this.customerAdditionalInfo = customerAdditionalInfo;
	}

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}
}
