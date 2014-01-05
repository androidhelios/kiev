package com.chadik.kiev.dao;

import java.math.BigDecimal;
import java.util.List;

import com.chadik.kiev.model.Trader;

public interface ITraderDao {

	public void saveTrader(Trader trader);

	public void deleteTrader(Trader trader);

	public List<Trader> findAllTraders();

	public Trader findTraderById(BigDecimal id);

}
