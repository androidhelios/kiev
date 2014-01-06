package com.chadik.kiev.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.chadik.kiev.dao.IOrderItemDao;
import com.chadik.kiev.model.OrderItem;
import com.chadik.kiev.util.HibernateUtil;

@Repository
public class OrderItemDaoImpl implements IOrderItemDao {

	@Override
	public void saveOrderItem(OrderItem orderItem) {
		HibernateUtil.getSession().saveOrUpdate(orderItem);
	}

	@Override
	public void deleteOrderItem(OrderItem orderItem) {
		HibernateUtil.getSession().delete(orderItem);
	}

	@Override
	public OrderItem findOrderItemById(BigDecimal id) {
		OrderItem orderItem = null;
		orderItem = (OrderItem) HibernateUtil.getSession().get(OrderItem.class, id);
		return orderItem;
	}

	@Override
	public List<OrderItem> findAllOrderItems() {
		List<OrderItem> orderItems = null;
		Query query = HibernateUtil.getSession().createQuery(
				"from " + OrderItem.class.getName());
		orderItems = query.list();
		return orderItems;
	}

}