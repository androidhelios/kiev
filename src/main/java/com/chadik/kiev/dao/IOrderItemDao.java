package com.chadik.kiev.dao;

import java.math.BigDecimal;
import java.util.List;

import com.chadik.kiev.model.OrderItem;

public interface IOrderItemDao {

	public void saveOrderItem(OrderItem orderItem);

	public void deleteOrderItem(OrderItem orderItem);

	public List<OrderItem> findAllOrderItems();

	public OrderItem findOrderItemById(BigDecimal id);

}
