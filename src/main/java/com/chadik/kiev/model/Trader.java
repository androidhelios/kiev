package com.chadik.kiev.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class Trader implements Serializable {

	@Id
	@GeneratedValue
	private BigDecimal traderId;
	@NotNull(message = "Полето име на компанијата не е пополнето")
	private String traderName;
	@NotNull(message = "Полето регистарски број на компанијата не е пополнето")
	private String traderRegistryNumber;
	@NotNull(message = "Полето име на банка за компанијата не е пополнето")
	private String traderBankName;
	@NotNull(message = "Полето банкарска сметка на компанијата не е пополнето")
	private String traderBankAccount;
	@NotNull(message = "Полето адреса на компанијата не е пополнето")
	private String traderAddress;
	private String traderPhoneNumber;
	private String traderEmail;
	private String traderAdditionalInfo;
	@OneToMany(mappedBy = "trader", cascade = CascadeType.ALL)
	@Valid
	private List<Invoice> invoices = new ArrayList<Invoice>();

	public BigDecimal getTraderId() {
		return traderId;
	}

	public void setTraderId(BigDecimal traderId) {
		this.traderId = traderId;
	}

	public String getTraderName() {
		return traderName;
	}

	public void setTraderName(String traderName) {
		this.traderName = traderName;
	}

	public String getTraderRegistryNumber() {
		return traderRegistryNumber;
	}

	public void setTraderRegistryNumber(String traderRegistryNumber) {
		this.traderRegistryNumber = traderRegistryNumber;
	}

	public String getTraderBankName() {
		return traderBankName;
	}

	public void setTraderBankName(String traderBankName) {
		this.traderBankName = traderBankName;
	}

	public String getTraderBankAccount() {
		return traderBankAccount;
	}

	public void setTraderBankAccount(String traderBankAccount) {
		this.traderBankAccount = traderBankAccount;
	}

	public String getTraderAddress() {
		return traderAddress;
	}

	public void setTraderAddress(String traderAddress) {
		this.traderAddress = traderAddress;
	}

	public String getTraderPhoneNumber() {
		return traderPhoneNumber;
	}

	public void setTraderPhoneNumber(String traderPhoneNumber) {
		this.traderPhoneNumber = traderPhoneNumber;
	}

	public String getTraderEmail() {
		return traderEmail;
	}

	public void setTraderEmail(String traderEmail) {
		this.traderEmail = traderEmail;
	}

	public String getTraderAdditionalInfo() {
		return traderAdditionalInfo;
	}

	public void setTraderAdditionalInfo(String traderAdditionalInfo) {
		this.traderAdditionalInfo = traderAdditionalInfo;
	}

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}
}
