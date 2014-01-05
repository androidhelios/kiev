package com.chadik.kiev.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chadik.kiev.dao.ITraderDao;
import com.chadik.kiev.model.Trader;
import com.chadik.kiev.service.ITraderService;
import com.chadik.kiev.util.HibernateUtil;

@Service
public class TraderServiceImpl implements ITraderService {

	@Autowired
	private ITraderDao traderDaoImpl;

	@Override
	public void saveTrader(Trader trader) {
		try {
			HibernateUtil.beginTransaction();
			traderDaoImpl.saveTrader(trader);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
			HibernateUtil.rollbackTransaction();
		}
	}

	@Override
	public void deleteTrader(Trader trader) {
		try {
			HibernateUtil.beginTransaction();
			traderDaoImpl.deleteTrader(trader);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
			HibernateUtil.rollbackTransaction();
		}
	}

	@Override
	public List<Trader> findAllTraders() {
		List<Trader> traders = new ArrayList<Trader>();
		try {
			HibernateUtil.beginTransaction();
			traders = traderDaoImpl.findAllTraders();
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
		}
		return traders;
	}

	@Override
	public Trader findTraderById(BigDecimal id) {
		Trader trader = null;
		try {
			HibernateUtil.beginTransaction();
			trader = (Trader) traderDaoImpl.findTraderById(id);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
		}
		return trader;
	}

}