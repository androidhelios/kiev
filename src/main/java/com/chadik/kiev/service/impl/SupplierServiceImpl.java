package com.chadik.kiev.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chadik.kiev.dao.ISupplierDao;
import com.chadik.kiev.model.Supplier;
import com.chadik.kiev.service.ISupplierService;
import com.chadik.kiev.util.HibernateUtil;

@Service
public class SupplierServiceImpl implements ISupplierService {

	@Autowired
	private ISupplierDao supplierDaoImpl;

	@Override
	public void saveSupplier(Supplier supplier) {
		try {
			HibernateUtil.beginTransaction();
			supplierDaoImpl.saveSupplier(supplier);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
			HibernateUtil.rollbackTransaction();
		}
	}

	@Override
	public void deleteSupplier(Supplier supplier) {
		try {
			HibernateUtil.beginTransaction();
			supplierDaoImpl.deleteSupplier(supplier);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
			HibernateUtil.rollbackTransaction();
		}
	}

	@Override
	public List<Supplier> findAllSuppliers() {
		List<Supplier> suppliers = new ArrayList<Supplier>();
		try {
			HibernateUtil.beginTransaction();
			suppliers = supplierDaoImpl.findAllSuppliers();
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
		}
		return suppliers;
	}

	@Override
	public Supplier findSupplierById(BigDecimal id) {
		Supplier supplier = null;
		try {
			HibernateUtil.beginTransaction();
			supplier = (Supplier) supplierDaoImpl.findSupplierById(id);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
		}
		return supplier;
	}

}