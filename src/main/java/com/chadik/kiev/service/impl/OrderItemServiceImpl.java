package com.chadik.kiev.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chadik.kiev.dao.IOrderItemDao;
import com.chadik.kiev.model.OrderItem;
import com.chadik.kiev.service.IOrderItemService;
import com.chadik.kiev.util.HibernateUtil;

@Service
public class OrderItemServiceImpl implements IOrderItemService {

	@Autowired
	private IOrderItemDao orderItemDaoImpl;

	@Override
	public void saveOrderItem(OrderItem orderItem) {
		try {
			HibernateUtil.beginTransaction();
			orderItemDaoImpl.saveOrderItem(orderItem);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
			HibernateUtil.rollbackTransaction();
		}
	}

	@Override
	public void deleteOrderItem(OrderItem orderItem) {
		try {
			HibernateUtil.beginTransaction();
			orderItemDaoImpl.deleteOrderItem(orderItem);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
			HibernateUtil.rollbackTransaction();
		}
	}

	@Override
	public List<OrderItem> findAllOrderItems() {
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		try {
			HibernateUtil.beginTransaction();
			orderItems = orderItemDaoImpl.findAllOrderItems();
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
		}
		return orderItems;
	}

	@Override
	public OrderItem findOrderItemById(BigDecimal id) {
		OrderItem orderItem = null;
		try {
			HibernateUtil.beginTransaction();
			orderItem = (OrderItem) orderItemDaoImpl.findOrderItemById(id);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
		}
		return orderItem;
	}

}