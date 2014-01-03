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
    @OneToMany(mappedBy = "customer",
            cascade = CascadeType.ALL)
    @NotNull
    @Valid
    private List<Invoice> invoices = new ArrayList<Invoice>();

    public Customer() {
    }

    /**
     * @return the customerId
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the customerAddress
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     * @param customerAddress the customerAddress to set
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     * @return the customerPhone
     */
    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    /**
     * @param customerPhone the customerPhone to set
     */
    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    /**
     * @return the customerEmail
     */
    public String getCustomerEmail() {
        return customerEmail;
    }

    /**
     * @param customerEmail the customerEmail to set
     */
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    /**
     * @return the customerAdditionalInfo
     */
    public String getCustomerAdditionalInfo() {
        return customerAdditionalInfo;
    }

    /**
     * @param customerAdditionalInfo the customerAdditionalInfo to set
     */
    public void setCustomerAdditionalInfo(String customerAdditionalInfo) {
        this.customerAdditionalInfo = customerAdditionalInfo;
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
