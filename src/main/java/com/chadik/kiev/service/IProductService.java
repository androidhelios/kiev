package com.chadik.kiev.service;

import java.math.BigDecimal;
import java.util.List;

import com.chadik.kiev.model.Product;

public interface IProductService {

	public void saveProduct(Product product);

	public void deleteProduct(Product product);

	public List<Product> findAllProducts();

	public Product findProductById(BigDecimal id);

}
