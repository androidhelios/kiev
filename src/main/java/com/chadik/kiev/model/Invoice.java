package com.chadik.kiev.model;

import java.io.Serializable;
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

/**
 *
 * @author ivan.chadikovski
 */
@Entity
public class Invoice implements Serializable {

    @Id
    @GeneratedValue
    private Integer invoiceId;
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
    @OneToMany(mappedBy = "invoice",
            cascade = CascadeType.ALL)
    @NotNull
    @Valid
    private List<Request> requests = new ArrayList<Request>();

    public Invoice() {
    }

    /**
     * @return the invoiceId
     */
    public Integer getInvoiceId() {
        return invoiceId;
    }

    /**
     * @param invoiceId the invoiceId to set
     */
    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    /**
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * @return the trader
     */
    public Trader getTrader() {
        return trader;
    }

    /**
     * @param trader the trader to set
     */
    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    /**
     * @return the invoiceNumber
     */
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    /**
     * @param invoiceNumber the invoiceNumber to set
     */
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    /**
     * @return the invoiceDate
     */
    public String getInvoiceDate() {
        return invoiceDate;
    }

    /**
     * @param invoiceDate the invoiceDate to set
     */
    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    /**
     * @return the invoiceSerialNumber
     */
    public String getInvoiceSerialNumber() {
        return invoiceSerialNumber;
    }

    /**
     * @param invoiceSerialNumber the invoiceSerialNumber to set
     */
    public void setInvoiceSerialNumber(String invoiceSerialNumber) {
        this.invoiceSerialNumber = invoiceSerialNumber;
    }

    /**
     * @return the invoiceDeliveryDate
     */
    public String getInvoiceDeliveryDate() {
        return invoiceDeliveryDate;
    }

    /**
     * @param invoiceDeliveryDate the invoiceDeliveryDate to set
     */
    public void setInvoiceDeliveryDate(String invoiceDeliveryDate) {
        this.invoiceDeliveryDate = invoiceDeliveryDate;
    }

    /**
     * @return the invoiceDeliveryNumber
     */
    public String getInvoiceDeliveryNumber() {
        return invoiceDeliveryNumber;
    }

    /**
     * @param invoiceDeliveryNumber the invoiceDeliveryNumber to set
     */
    public void setInvoiceDeliveryNumber(String invoiceDeliveryNumber) {
        this.invoiceDeliveryNumber = invoiceDeliveryNumber;
    }

    /**
     * @return the invoceTotalPrice
     */
    public String getInvoceTotalPrice() {
        return invoceTotalPrice;
    }

    /**
     * @param invoceTotalPrice the invoceTotalPrice to set
     */
    public void setInvoceTotalPrice(String invoceTotalPrice) {
        this.invoceTotalPrice = invoceTotalPrice;
    }

    /**
     * @return the invoiceTotalTax
     */
    public String getInvoiceTotalTax() {
        return invoiceTotalTax;
    }

    /**
     * @param invoiceTotalTax the invoiceTotalTax to set
     */
    public void setInvoiceTotalTax(String invoiceTotalTax) {
        this.invoiceTotalTax = invoiceTotalTax;
    }

    /**
     * @return the invoceTotalPriceTax
     */
    public String getInvoceTotalPriceTax() {
        return invoceTotalPriceTax;
    }

    /**
     * @param invoceTotalPriceTax the invoceTotalPriceTax to set
     */
    public void setInvoceTotalPriceTax(String invoceTotalPriceTax) {
        this.invoceTotalPriceTax = invoceTotalPriceTax;
    }

    /**
     * @return the invoiceAdditionalInfo
     */
    public String getInvoiceAdditionalInfo() {
        return invoiceAdditionalInfo;
    }

    /**
     * @param invoiceAdditionalInfo the invoiceAdditionalInfo to set
     */
    public void setInvoiceAdditionalInfo(String invoiceAdditionalInfo) {
        this.invoiceAdditionalInfo = invoiceAdditionalInfo;
    }

    /**
     * @return the requests
     */
    public List<Request> getRequests() {
        return requests;
    }

    /**
     * @param requests the requests to set
     */
    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }
}
