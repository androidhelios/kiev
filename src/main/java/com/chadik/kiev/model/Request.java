package com.chadik.kiev.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Request implements Serializable {

	@Id
	@GeneratedValue
	private BigDecimal orderId;
	@ManyToOne
	@JoinColumn(name = "product")
	@NotNull
	private Product product;
	@ManyToOne
	@JoinColumn(name = "invoice")
	@NotNull
	private Invoice invoice;
	@NotNull(message = "Полето број на нарачки за продуктот не е пополнето")
	private String orderProductQuantity;
	@NotNull
	private String orderProductPrice;
	@NotNull
	private String orderQuantityTax;
	@NotNull
	private String orderQuantityPriceTax;
	private String orderAdditionalInfo;

	public BigDecimal getOrderId() {
		return orderId;
	}

	public void setOrderId(BigDecimal orderId) {
		this.orderId = orderId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public String getOrderProductQuantity() {
		return orderProductQuantity;
	}

	public void setOrderProductQuantity(String orderProductQuantity) {
		this.orderProductQuantity = orderProductQuantity;
	}

	public String getOrderProductPrice() {
		return orderProductPrice;
	}

	public void setOrderProductPrice(String orderProductPrice) {
		this.orderProductPrice = orderProductPrice;
	}

	public String getOrderQuantityTax() {
		return orderQuantityTax;
	}

	public void setOrderQuantityTax(String orderQuantityTax) {
		this.orderQuantityTax = orderQuantityTax;
	}

	public String getOrderQuantityPriceTax() {
		return orderQuantityPriceTax;
	}

	public void setOrderQuantityPriceTax(String orderQuantityPriceTax) {
		this.orderQuantityPriceTax = orderQuantityPriceTax;
	}

	public String getOrderAdditionalInfo() {
		return orderAdditionalInfo;
	}

	public void setOrderAdditionalInfo(String orderAdditionalInfo) {
		this.orderAdditionalInfo = orderAdditionalInfo;
	}
}
