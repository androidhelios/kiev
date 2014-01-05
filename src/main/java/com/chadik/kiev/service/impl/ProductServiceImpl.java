package com.chadik.kiev.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chadik.kiev.dao.IProductDao;
import com.chadik.kiev.model.Product;
import com.chadik.kiev.service.IProductService;
import com.chadik.kiev.util.HibernateUtil;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductDao productDaoImpl;

	@Override
	public void saveProduct(Product product) {
		try {
			HibernateUtil.beginTransaction();
			productDaoImpl.saveProduct(product);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
			HibernateUtil.rollbackTransaction();
		}
	}

	@Override
	public void deleteProduct(Product product) {
		try {
			HibernateUtil.beginTransaction();
			productDaoImpl.deleteProduct(product);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
			HibernateUtil.rollbackTransaction();
		}
	}

	@Override
	public List<Product> findAllProducts() {
		List<Product> products = new ArrayList<Product>();
		try {
			HibernateUtil.beginTransaction();
			products = productDaoImpl.findAllProducts();
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
		}
		return products;
	}

	@Override
	public Product findProductById(BigDecimal id) {
		Product product = null;
		try {
			HibernateUtil.beginTransaction();
			product = (Product) productDaoImpl.findProductById(id);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
		}
		return product;
	}

}