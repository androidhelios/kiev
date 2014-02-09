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
public class OrderItem implements Serializable {

	@Id
	@GeneratedValue
	private BigDecimal orderItemId;
	@ManyToOne
	@JoinColumn(name = "product")
	@NotNull
	private Product product;
	@ManyToOne
	@JoinColumn(name = "invoice")
	@NotNull
	private Invoice invoice;
	@NotNull(message = "Полето број на нарачки за продуктот не е пополнето")
	private String orderItemQuantity;
	@NotNull
	private String orderItemQuantityPrice;
	@NotNull
	private String orderItemQuantityPriceWithoutTax;
	@NotNull
	private String orderItemTax;
	@NotNull
	private String orderItemQuantityTax;
	@NotNull
	private String orderItemQuantityTaxPrice;
	private String orderAdditionalInfo;

	public BigDecimal getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(BigDecimal orderItemId) {
		this.orderItemId = orderItemId;
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

	public String getOrderItemQuantity() {
		return orderItemQuantity;
	}

	public void setOrderItemQuantity(String orderItemQuantity) {
		this.orderItemQuantity = orderItemQuantity;
	}

	public String getOrderItemQuantityPrice() {
		return orderItemQuantityPrice;
	}

	public void setOrderItemQuantityPrice(String orderItemQuantityPrice) {
		this.orderItemQuantityPrice = orderItemQuantityPrice;
	}

	public String getOrderItemQuantityPriceWithoutTax() {
		return orderItemQuantityPriceWithoutTax;
	}

	public void setOrderItemQuantityPriceWithoutTax(
			String orderItemQuantityPriceWithoutTax) {
		this.orderItemQuantityPriceWithoutTax = orderItemQuantityPriceWithoutTax;
	}

	public String getOrderItemTax() {
		return orderItemTax;
	}

	public void setOrderItemTax(String orderItemTax) {
		this.orderItemTax = orderItemTax;
	}

	public String getOrderItemQuantityTax() {
		return orderItemQuantityTax;
	}

	public void setOrderItemQuantityTax(String orderItemQuantityTax) {
		this.orderItemQuantityTax = orderItemQuantityTax;
	}

	public String getOrderItemQuantityTaxPrice() {
		return orderItemQuantityTaxPrice;
	}

	public void setOrderItemQuantityTaxPrice(String orderItemQuantityTaxPrice) {
		this.orderItemQuantityTaxPrice = orderItemQuantityTaxPrice;
	}

	public String getOrderAdditionalInfo() {
		return orderAdditionalInfo;
	}

	public void setOrderAdditionalInfo(String orderAdditionalInfo) {
		this.orderAdditionalInfo = orderAdditionalInfo;
	}
	

}
