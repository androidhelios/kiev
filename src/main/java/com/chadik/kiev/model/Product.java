package com.chadik.kiev.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
public class Product implements Serializable {

	@Id
	@GeneratedValue
	private Integer productId;
	@NotNull(message = "Полето име на продуктот не е пополнето")
	private String productName;
	@NotNull(message = "Полето единица мерка на продуктот не е пополнето")
	private String productMeasurement;
	@NotNull(message = "Полето данок на продуктот не е пополнето")
	private String productTax;
	@NotNull(message = "Полето цена на продуктот не е пополнето")
	private String productPrice;
	private String productAdditionalInfo;
	@OneToMany(mappedBy = "product")
	@Valid
	private List<Request> requests = new ArrayList<Request>(0);

	public Product() {
		
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductMeasurement() {
		return productMeasurement;
	}

	public void setProductMeasurement(String productMeasurement) {
		this.productMeasurement = productMeasurement;
	}

	public String getProductTax() {
		return productTax;
	}

	public void setProductTax(String productTax) {
		this.productTax = productTax;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductAdditionalInfo() {
		return productAdditionalInfo;
	}

	public void setProductAdditionalInfo(String productAdditionalInfo) {
		this.productAdditionalInfo = productAdditionalInfo;
	}

	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}
}
