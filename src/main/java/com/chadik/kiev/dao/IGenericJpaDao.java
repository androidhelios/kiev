package com.chadik.kiev.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;

public interface IGenericJpaDao<T, ID extends Serializable> {

	public void save(T t);

	public void merge(T t);

	public void delete(T t);

	public List<T> findMany(Query query);

	public T findOne(Query query);

	public List<T> findAll();

	public T findByID(BigDecimal id);

}