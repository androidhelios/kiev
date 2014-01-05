package com.chadik.kiev.service;

import java.math.BigDecimal;
import java.util.List;

import com.chadik.kiev.model.Request;

public interface IRequestService {

	public void saveRequest(Request request);

	public void deleteRequest(Request request);

	public List<Request> findAllRequests();

	public Request findRequestById(BigDecimal id);

}
