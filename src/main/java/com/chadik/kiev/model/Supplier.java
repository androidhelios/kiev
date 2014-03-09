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
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Supplier implements Serializable {

	@Id
	@GeneratedValue
	private BigDecimal supplierId;
	@NotNull(message = "Полето име на компанијата не е пополнето")
	private String supplierName;
	@NotNull(message = "Полето регистарски број на компанијата не е пополнето")
	private String supplierRegistryNumber;
	@NotNull(message = "Полето име на банка за компанијата не е пополнето")
	private String supplierBankName;
	@NotNull(message = "Полето банкарска сметка на компанијата не е пополнето")
	private String supplierBankAccount;
	@NotNull(message = "Полето адреса на компанијата не е пополнето")
	private String supplierAddress;
	private String supplierPhoneNumber;
	private String supplierEmail;
	@NotNull(message = "Полето корисничко име не е пополнето")
	private String supplierUserName;
	@NotNull(message = "Полето лозинка не е пополнето")
	private String supplierPassword;
	private String supplierAdditionalInfo;
	@OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@Valid
	private List<Invoice> invoices = new ArrayList<Invoice>();

	public BigDecimal getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(BigDecimal supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierRegistryNumber() {
		return supplierRegistryNumber;
	}

	public void setSupplierRegistryNumber(String supplierRegistryNumber) {
		this.supplierRegistryNumber = supplierRegistryNumber;
	}

	public String getSupplierBankName() {
		return supplierBankName;
	}

	public void setSupplierBankName(String supplierBankName) {
		this.supplierBankName = supplierBankName;
	}

	public String getSupplierBankAccount() {
		return supplierBankAccount;
	}

	public void setSupplierBankAccount(String supplierBankAccount) {
		this.supplierBankAccount = supplierBankAccount;
	}

	public String getSupplierAddress() {
		return supplierAddress;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	public String getSupplierPhoneNumber() {
		return supplierPhoneNumber;
	}

	public void setSupplierPhoneNumber(String supplierPhoneNumber) {
		this.supplierPhoneNumber = supplierPhoneNumber;
	}

	public String getSupplierEmail() {
		return supplierEmail;
	}

	public void setSupplierEmail(String supplierEmail) {
		this.supplierEmail = supplierEmail;
	}

	public String getSupplierUserName() {
		return supplierUserName;
	}

	public void setSupplierUserName(String supplierUserName) {
		this.supplierUserName = supplierUserName;
	}

	public String getSupplierPassword() {
		return supplierPassword;
	}

	public void setSupplierPassword(String supplierPassword) {
		this.supplierPassword = supplierPassword;
	}

	public String getSupplierAdditionalInfo() {
		return supplierAdditionalInfo;
	}

	public void setSupplierAdditionalInfo(String supplierAdditionalInfo) {
		this.supplierAdditionalInfo = supplierAdditionalInfo;
	}

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}
}
