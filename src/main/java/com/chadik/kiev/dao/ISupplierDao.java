package com.chadik.kiev.dao;

import java.math.BigDecimal;
import java.util.List;

import com.chadik.kiev.model.Supplier;

public interface ISupplierDao {

	public void saveSupplier(Supplier supplier);

	public void deleteSupplier(Supplier supplier);

	public List<Supplier> findAllSuppliers();

	public Supplier findSupplierById(BigDecimal id);

}
