package com.chadik.kiev.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
	@JoinColumn(name = "trader")
	@NotNull
	private Trader trader;
	@NotNull(message = "Полето број на фактурата не е пополнето")
	private String invoiceNumber;
	@NotNull(message = "Полето дата на фактурата не е пополнето")
	private String invoiceDate;
	private String invoiceSerialNumber;
	private String invoiceDeliveryDate;
	private String invoiceDeliveryNumber;
	@NotNull(message = "Полето вкупен износ на продуктите не е пополнето")
	private String invoceTotalPrice;
	@NotNull(message = "Полето вкупен износ на данок на продуктите не е пополнето")
	private String invoiceTotalTax;
	@NotNull(message = "Полето вкупен износ со данок на продуктите не е пополнето")
	private String invoceTotalPriceTax;
	private String invoiceAdditionalInfo;
	@OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
	@NotNull
	@Valid
	private List<Request> requests = new ArrayList<Request>();

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

	public Trader getTrader() {
		return trader;
	}

	public void setTrader(Trader trader) {
		this.trader = trader;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getInvoiceSerialNumber() {
		return invoiceSerialNumber;
	}

	public void setInvoiceSerialNumber(String invoiceSerialNumber) {
		this.invoiceSerialNumber = invoiceSerialNumber;
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

	public String getInvoceTotalPrice() {
		return invoceTotalPrice;
	}

	public void setInvoceTotalPrice(String invoceTotalPrice) {
		this.invoceTotalPrice = invoceTotalPrice;
	}

	public String getInvoiceTotalTax() {
		return invoiceTotalTax;
	}

	public void setInvoiceTotalTax(String invoiceTotalTax) {
		this.invoiceTotalTax = invoiceTotalTax;
	}

	public String getInvoceTotalPriceTax() {
		return invoceTotalPriceTax;
	}

	public void setInvoceTotalPriceTax(String invoceTotalPriceTax) {
		this.invoceTotalPriceTax = invoceTotalPriceTax;
	}

	public String getInvoiceAdditionalInfo() {
		return invoiceAdditionalInfo;
	}

	public void setInvoiceAdditionalInfo(String invoiceAdditionalInfo) {
		this.invoiceAdditionalInfo = invoiceAdditionalInfo;
	}

	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}
}
