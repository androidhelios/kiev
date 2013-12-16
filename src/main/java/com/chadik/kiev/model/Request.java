package com.chadik.kiev.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ivan.chadikovski
 */
@Entity
public class Request implements Serializable {

    @Id
    @GeneratedValue
    private Integer orderId;
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

    public Request() {
    }

    /**
     * @return the orderId
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * @return the invoice
     */
    public Invoice getInvoice() {
        return invoice;
    }

    /**
     * @param invoice the invoice to set
     */
    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    /**
     * @return the orderProductQuantity
     */
    public String getOrderProductQuantity() {
        return orderProductQuantity;
    }

    /**
     * @param orderProductQuantity the orderProductQuantity to set
     */
    public void setOrderProductQuantity(String orderProductQuantity) {
        this.orderProductQuantity = orderProductQuantity;
    }

    /**
     * @return the orderProductPrice
     */
    public String getOrderProductPrice() {
        return orderProductPrice;
    }

    /**
     * @param orderProductPrice the orderProductPrice to set
     */
    public void setOrderProductPrice(String orderProductPrice) {
        this.orderProductPrice = orderProductPrice;
    }

    /**
     * @return the orderQuantityTax
     */
    public String getOrderQuantityTax() {
        return orderQuantityTax;
    }

    /**
     * @param orderQuantityTax the orderQuantityTax to set
     */
    public void setOrderQuantityTax(String orderQuantityTax) {
        this.orderQuantityTax = orderQuantityTax;
    }

    /**
     * @return the orderQuantityPriceTax
     */
    public String getOrderQuantityPriceTax() {
        return orderQuantityPriceTax;
    }

    /**
     * @param orderQuantityPriceTax the orderQuantityPriceTax to set
     */
    public void setOrderQuantityPriceTax(String orderQuantityPriceTax) {
        this.orderQuantityPriceTax = orderQuantityPriceTax;
    }

    /**
     * @return the orderAdditionalInfo
     */
    public String getOrderAdditionalInfo() {
        return orderAdditionalInfo;
    }

    /**
     * @param orderAdditionalInfo the orderAdditionalInfo to set
     */
    public void setOrderAdditionalInfo(String orderAdditionalInfo) {
        this.orderAdditionalInfo = orderAdditionalInfo;
    }
}
