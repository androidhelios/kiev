package com.chadik.kiev.service;

import java.math.BigDecimal;
import java.util.List;
import com.chadik.kiev.model.BankInfo;

public interface IBankInfoService {
	
	public void saveBankInfo(BankInfo bankInfo);

	public void deleteBankInfo(BankInfo bankInfo);

	public List<BankInfo> findAllBanksInfo();

	public BankInfo findBankInfoById(BigDecimal id);

}
