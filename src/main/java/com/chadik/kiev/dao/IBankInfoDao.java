package com.chadik.kiev.dao;

import java.math.BigDecimal;
import java.util.List;

import com.chadik.kiev.model.BankInfo;

public interface IBankInfoDao {
	
	public void saveBankInfo(BankInfo bankInfo);

	public void deleteBankInfo(BankInfo bankInfo);

	public List<BankInfo> findAllBanksInfo();

	public BankInfo findBankInfoById(BigDecimal id);

}
