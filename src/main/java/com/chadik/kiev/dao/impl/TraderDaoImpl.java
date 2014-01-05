package com.chadik.kiev.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.chadik.kiev.dao.ITraderDao;
import com.chadik.kiev.model.Trader;
import com.chadik.kiev.util.HibernateUtil;

@Repository
public class TraderDaoImpl implements ITraderDao {

	@Override
	public void saveTrader(Trader trader) {
		HibernateUtil.getSession().saveOrUpdate(trader);
	}

	@Override
	public void deleteTrader(Trader trader) {
		HibernateUtil.getSession().delete(trader);
	}

	@Override
	public Trader findTraderById(BigDecimal id) {
		Trader trader = null;
		trader = (Trader) HibernateUtil.getSession().get(Trader.class, id);
		return trader;
	}

	@Override
	public List<Trader> findAllTraders() {
		List<Trader> traders = null;
		Query query = HibernateUtil.getSession().createQuery(
				"from " + Trader.class.getName());
		traders = query.list();
		return traders;
	}

}
