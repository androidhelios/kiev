package com.chadik.kiev.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.chadik.kiev.dao.ISupplierDao;
import com.chadik.kiev.model.Supplier;
import com.chadik.kiev.util.HibernateUtil;

@Repository
public class SupplierDaoImpl implements ISupplierDao {

	@Override
	public void saveSupplier(Supplier supplier) {
		HibernateUtil.getSession().saveOrUpdate(supplier);
	}

	@Override
	public void deleteSupplier(Supplier supplier) {
		HibernateUtil.getSession().delete(supplier);
	}

	@Override
	public Supplier findSupplierById(BigDecimal id) {
		Supplier supplier = null;
		supplier = (Supplier) HibernateUtil.getSession().get(Supplier.class, id);
		return supplier;
	}

	@Override
	public List<Supplier> findAllSuppliers() {
		List<Supplier> suppliers = null;
		Query query = HibernateUtil.getSession().createQuery(
				"from " + Supplier.class.getName() + " order by supplierId");
		suppliers = query.list();
		return suppliers;
	}

}
