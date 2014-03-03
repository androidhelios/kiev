package com.chadik.kiev.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Invoice implements Serializable {

	@Id
	@GeneratedValue
	private BigDecimal invoiceId;
	@ManyToOne
	@JoinColumn(name = "customer")
	@NotNull
	private Customer customer;
	@ManyToOne
	@JoinColumn(name = "supplier")
	@NotNull
	private Supplier supplier;
	@NotNull(message = "Полето број на фактурата не е пополнето")
	private String invoiceNumber;
	private String invoiceSerialNumber;
	@NotNull(message = "Полето дата на фактурата не е пополнето")
	private String invoiceDate;
	private String invoiceDeliveryDate;
	private String invoiceDeliveryNumber;
	@NotNull(message = "Полето вкупен износ на продуктите не е пополнето")
	private String invoiceTotalPrice;
	@NotNull(message = "Полето вкупен износ на данок на продуктите не е пополнето")
	private String invoiceTotalTax;
	@NotNull(message = "Полето вкупен износ со данок на продуктите не е пополнето")
	private String invoiceTotalPriceTax;
	@NotNull(message = "Полето податок за исплата на фактурата не е пополнето")
	private String invoicePaymentInfo;
	private String invoiceAdditionalInfo;
	@OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@Valid
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();

	public BigDecimal getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(BigDecimal invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getInvoiceSerialNumber() {
		return invoiceSerialNumber;
	}

	public void setInvoiceSerialNumber(String invoiceSerialNumber) {
		this.invoiceSerialNumber = invoiceSerialNumber;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getInvoiceDeliveryDate() {
		return invoiceDeliveryDate;
	}

	public void setInvoiceDeliveryDate(String invoiceDeliveryDate) {
		this.invoiceDeliveryDate = invoiceDeliveryDate;
	}

	public String getInvoiceDeliveryNumber() {
		return invoiceDeliveryNumber;
	}

	public void setInvoiceDeliveryNumber(String invoiceDeliveryNumber) {
		this.invoiceDeliveryNumber = invoiceDeliveryNumber;
	}

	public String getInvoiceTotalPrice() {
		return invoiceTotalPrice;
	}

	public void setInvoiceTotalPrice(String invoiceTotalPrice) {
		this.invoiceTotalPrice = invoiceTotalPrice;
	}

	public String getInvoiceTotalTax() {
		return invoiceTotalTax;
	}

	public void setInvoiceTotalTax(String invoiceTotalTax) {
		this.invoiceTotalTax = invoiceTotalTax;
	}

	public String getInvoiceTotalPriceTax() {
		return invoiceTotalPriceTax;
	}

	public void setInvoiceTotalPriceTax(String invoiceTotalPriceTax) {
		this.invoiceTotalPriceTax = invoiceTotalPriceTax;
	}	
	
	public String getInvoicePaymentInfo() {
		return invoicePaymentInfo;
	}

	public void setInvoicePaymentInfo(String invoicePaymentInfo) {
		this.invoicePaymentInfo = invoicePaymentInfo;
	}

	public String getInvoiceAdditionalInfo() {
		return invoiceAdditionalInfo;
	}

	public void setInvoiceAdditionalInfo(String invoiceAdditionalInfo) {
		this.invoiceAdditionalInfo = invoiceAdditionalInfo;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

}
