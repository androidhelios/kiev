package com.chadik.kiev.dao.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import com.chadik.kiev.dao.ITraderJpaDao;
import com.chadik.kiev.model.Trader;

@Repository
public class TraderJpaDaoImpl extends GenericJpaDaoImpl<Trader, BigDecimal>
		implements ITraderJpaDao {

	@Override
	public Class getClazz() {
		return Trader.class;
	}

}
