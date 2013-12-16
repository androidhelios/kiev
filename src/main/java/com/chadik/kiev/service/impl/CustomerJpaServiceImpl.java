package com.chadik.kiev.service.impl;

import com.chadik.kiev.dao.IGenericJpaDao;
import com.chadik.kiev.dao.impl.CustomerJpaDaoImpl;
import com.chadik.kiev.model.Customer;
import com.chadik.kiev.service.ICustomerJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ivan.chadikovski
 */
@Service
public class CustomerJpaServiceImpl extends GenericJpaServiceImpl<Customer> implements ICustomerJpaService {

    @Autowired
    private CustomerJpaDaoImpl customerJpaDaoImpl;
    
    @Override
    public IGenericJpaDao getGenericDao() {
        return getCustomerJpaDaoImpl();
    }  

    /**
     * @return the customerJpaDaoImpl
     */
    public CustomerJpaDaoImpl getCustomerJpaDaoImpl() {
        return customerJpaDaoImpl;
    }

    /**
     * @param customerJpaDaoImpl the customerJpaDaoImpl to set
     */
    public void setCustomerJpaDaoImpl(CustomerJpaDaoImpl customerJpaDaoImpl) {
        this.customerJpaDaoImpl = customerJpaDaoImpl;
    }
}