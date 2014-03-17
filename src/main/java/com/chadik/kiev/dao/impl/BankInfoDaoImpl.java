package com.chadik.kiev.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.chadik.kiev.dao.IBankInfoDao;
import com.chadik.kiev.model.BankInfo;
import com.chadik.kiev.util.HibernateUtil;

@Repository
public class BankInfoDaoImpl implements IBankInfoDao {
	
	@Override
	public void saveBankInfo(BankInfo bankInfo) {
		HibernateUtil.getSession().saveOrUpdate(bankInfo);
	}

	@Override
	public void deleteBankInfo(BankInfo bankInfo) {
		HibernateUtil.getSession().delete(bankInfo);
	}

	@Override
	public BankInfo findBankInfoById(BigDecimal id) {
		BankInfo bankInfo = null;
		bankInfo = (BankInfo) HibernateUtil.getSession()
				.get(BankInfo.class, id);
		return bankInfo;
	}

	@Override
	public List<BankInfo> findAllBanksInfo() {
		List<BankInfo> banksInfo = null;
		Query query = HibernateUtil.getSession().createQuery(
				"from " + BankInfo.class.getName() + " order by bankInfoId");
		banksInfo = query.list();
		return banksInfo;
	}

}
