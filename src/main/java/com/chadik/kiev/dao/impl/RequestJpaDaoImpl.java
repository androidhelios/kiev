package com.chadik.kiev.dao.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import com.chadik.kiev.dao.IRequestJpaDao;
import com.chadik.kiev.model.Request;

@Repository
public class RequestJpaDaoImpl extends GenericJpaDaoImpl<Request, BigDecimal>
		implements IRequestJpaDao {

	@Override
	public Class getClazz() {
		return Request.class;
	}
}