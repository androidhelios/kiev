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

/**
 *
 * @author ivan.chadikovski
 */
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

    /**
     * @return the productId
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the productMeasurement
     */
    public String getProductMeasurement() {
        return productMeasurement;
    }

    /**
     * @param productMeasurement the productMeasurement to set
     */
    public void setProductMeasurement(String productMeasurement) {
        this.productMeasurement = productMeasurement;
    }

    /**
     * @return the productTax
     */
    public String getProductTax() {
        return productTax;
    }

    /**
     * @param productTax the productTax to set
     */
    public void setProductTax(String productTax) {
        this.productTax = productTax;
    }

    /**
     * @return the productPrice
     */
    public String getProductPrice() {
        return productPrice;
    }

    /**
     * @param productPrice the productPrice to set
     */
    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * @return the productAdditionalInfo
     */
    public String getProductAdditionalInfo() {
        return productAdditionalInfo;
    }

    /**
     * @param productAdditionalInfo the productAdditionalInfo to set
     */
    public void setProductAdditionalInfo(String productAdditionalInfo) {
        this.productAdditionalInfo = productAdditionalInfo;
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
