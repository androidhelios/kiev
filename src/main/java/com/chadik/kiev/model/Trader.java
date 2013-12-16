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

/**
 *
 * @author ivan.chadikovski
 */
@Entity
public class Trader implements Serializable {

    @Id
    @GeneratedValue
    private Integer traderId;
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
    @OneToMany(mappedBy = "trader",
            cascade = CascadeType.ALL)
    @Valid
    private List<Invoice> invoices = new ArrayList<Invoice>();

    public Trader() {
    }

    /**
     * @return the traderId
     */
    public Integer getTraderId() {
        return traderId;
    }

    /**
     * @param traderId the traderId to set
     */
    public void setTraderId(Integer traderId) {
        this.traderId = traderId;
    }

    /**
     * @return the traderName
     */
    public String getTraderName() {
        return traderName;
    }

    /**
     * @param traderName the traderName to set
     */
    public void setTraderName(String traderName) {
        this.traderName = traderName;
    }

    /**
     * @return the traderRegistryNumber
     */
    public String getTraderRegistryNumber() {
        return traderRegistryNumber;
    }

    /**
     * @param traderRegistryNumber the traderRegistryNumber to set
     */
    public void setTraderRegistryNumber(String traderRegistryNumber) {
        this.traderRegistryNumber = traderRegistryNumber;
    }

    /**
     * @return the traderBankName
     */
    public String getTraderBankName() {
        return traderBankName;
    }

    /**
     * @param traderBankName the traderBankName to set
     */
    public void setTraderBankName(String traderBankName) {
        this.traderBankName = traderBankName;
    }

    /**
     * @return the traderBankAccount
     */
    public String getTraderBankAccount() {
        return traderBankAccount;
    }

    /**
     * @param traderBankAccount the traderBankAccount to set
     */
    public void setTraderBankAccount(String traderBankAccount) {
        this.traderBankAccount = traderBankAccount;
    }

    /**
     * @return the traderAddress
     */
    public String getTraderAddress() {
        return traderAddress;
    }

    /**
     * @param traderAddress the traderAddress to set
     */
    public void setTraderAddress(String traderAddress) {
        this.traderAddress = traderAddress;
    }

    /**
     * @return the traderPhoneNumber
     */
    public String getTraderPhoneNumber() {
        return traderPhoneNumber;
    }

    /**
     * @param traderPhoneNumber the traderPhoneNumber to set
     */
    public void setTraderPhoneNumber(String traderPhoneNumber) {
        this.traderPhoneNumber = traderPhoneNumber;
    }

    /**
     * @return the traderEmail
     */
    public String getTraderEmail() {
        return traderEmail;
    }

    /**
     * @param traderEmail the traderEmail to set
     */
    public void setTraderEmail(String traderEmail) {
        this.traderEmail = traderEmail;
    }

    /**
     * @return the traderAdditionalInfo
     */
    public String getTraderAdditionalInfo() {
        return traderAdditionalInfo;
    }

    /**
     * @param traderAdditionalInfo the traderAdditionalInfo to set
     */
    public void setTraderAdditionalInfo(String traderAdditionalInfo) {
        this.traderAdditionalInfo = traderAdditionalInfo;
    }

    /**
     * @return the invoices
     */
    public List<Invoice> getInvoices() {
        return invoices;
    }

    /**
     * @param invoices the invoices to set
     */
    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
}
