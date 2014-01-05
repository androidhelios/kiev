package com.chadik.kiev.service;

import java.math.BigDecimal;
import java.util.List;

import com.chadik.kiev.model.Trader;

public interface ITraderService {

	public void saveTrader(Trader trader);

	public void deleteTrader(Trader trader);

	public List<Trader> findAllTraders();

	public Trader findTraderById(BigDecimal id);

}
