package com.chadik.kiev.service.impl;

import com.chadik.kiev.dao.IGenericJpaDao;
import com.chadik.kiev.dao.impl.CustomerJpaDaoImpl;
import com.chadik.kiev.model.Customer;
import com.chadik.kiev.service.ICustomerJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author ivan.chadikovski
 */
@Service
public class CustomerJpaServiceImpl extends GenericJpaServiceImpl<Customer> implements ICustomerJpaService {

    @Autowired
    @Qualifier("customerJpaDaoImpl")
    private IGenericJpaDao customerJpaDaoImpl;
    
    @Override
    public IGenericJpaDao getGenericDao() {
        return customerJpaDaoImpl;
    }  

}