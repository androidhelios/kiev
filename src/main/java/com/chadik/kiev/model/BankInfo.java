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
public class BankInfo implements Serializable {

	@Id
	@GeneratedValue
	private BigDecimal bankInfoId;
	@NotNull(message = "Името на банката не е пополнето")
	private String bankInfoName;
	@NotNull(message = "Полето сметка во банката не е пополнето")
	private String bankInfoAccount;
	private String bankInfoAddress;
	private String bankInfoPhoneNumber;
	private String bankInfoEmail;
	private String bankInfoAdditionalInfo;
	@OneToMany(mappedBy = "bankInfo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@Valid
	private List<Invoice> invoices = new ArrayList<Invoice>();

	public BigDecimal getBankInfoId() {
		return bankInfoId;
	}

	public void setBankInfoId(BigDecimal bankInfoId) {
		this.bankInfoId = bankInfoId;
	}

	public String getBankInfoName() {
		return bankInfoName;
	}

	public void setBankInfoName(String bankInfoName) {
		this.bankInfoName = bankInfoName;
	}

	public String getBankInfoAccount() {
		return bankInfoAccount;
	}

	public void setBankInfoAccount(String bankInfoAccount) {
		this.bankInfoAccount = bankInfoAccount;
	}

	public String getBankInfoAddress() {
		return bankInfoAddress;
	}

	public void setBankInfoAddress(String bankInfoAddress) {
		this.bankInfoAddress = bankInfoAddress;
	}

	public String getBankInfoPhoneNumber() {
		return bankInfoPhoneNumber;
	}

	public void setBankInfoPhoneNumber(String bankInfoPhoneNumber) {
		this.bankInfoPhoneNumber = bankInfoPhoneNumber;
	}

	public String getBankInfoEmail() {
		return bankInfoEmail;
	}

	public void setBankInfoEmail(String bankInfoEmail) {
		this.bankInfoEmail = bankInfoEmail;
	}

	public String getBankInfoAdditionalInfo() {
		return bankInfoAdditionalInfo;
	}

	public void setBankInfoAdditionalInfo(String bankInfoAdditionalInfo) {
		this.bankInfoAdditionalInfo = bankInfoAdditionalInfo;
	}

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

}
