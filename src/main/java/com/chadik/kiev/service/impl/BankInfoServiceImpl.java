package com.chadik.kiev.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chadik.kiev.dao.IBankInfoDao;
import com.chadik.kiev.model.BankInfo;
import com.chadik.kiev.service.IBankInfoService;
import com.chadik.kiev.util.HibernateUtil;

@Service
public class BankInfoServiceImpl implements IBankInfoService {
	
	@Autowired
	private IBankInfoDao bankInfoDaoImpl;

	@Override
	public void saveBankInfo(BankInfo bankInfo) {
		try {
			HibernateUtil.beginTransaction();
			bankInfoDaoImpl.saveBankInfo(bankInfo);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
			HibernateUtil.rollbackTransaction();
		}
	}

	@Override
	public void deleteBankInfo(BankInfo bankInfo) {
		try {
			HibernateUtil.beginTransaction();
			bankInfoDaoImpl.deleteBankInfo(bankInfo);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
			HibernateUtil.rollbackTransaction();
		}
	}
	
	@Override
	public List<BankInfo> findAllBanksInfo() {
		List<BankInfo> banksInfo = new ArrayList<BankInfo>();
		try {
			HibernateUtil.beginTransaction();
			banksInfo = bankInfoDaoImpl.findAllBanksInfo();
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
		}
		return banksInfo;
	}
	
	@Override
	public BankInfo findBankInfoById(BigDecimal id) {
		BankInfo bankInfo = null;
		try {
			HibernateUtil.beginTransaction();
			bankInfo = (BankInfo) bankInfoDaoImpl.findBankInfoById(id);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
		}
		return bankInfo;
	}

}
