package com.chadik.kiev.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.chadik.kiev.dao.IProductDao;
import com.chadik.kiev.model.Product;
import com.chadik.kiev.util.HibernateUtil;

@Repository
public class ProductDaoImpl implements IProductDao {

	@Override
	public void saveProduct(Product product) {
		HibernateUtil.getSession().saveOrUpdate(product);
	}

	@Override
	public void deleteProduct(Product product) {
		HibernateUtil.getSession().delete(product);
	}

	@Override
	public Product findProductById(BigDecimal id) {
		Product product = null;
		product = (Product) HibernateUtil.getSession().get(Product.class, id);
		return product;
	}

	@Override
	public List<Product> findAllProducts() {
		List<Product> products = null;
		Query query = HibernateUtil.getSession().createQuery(
				"from " + Product.class.getName());
		products = query.list();
		return products;
	}

}