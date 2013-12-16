package com.chadik.kiev.dao.impl;

import com.chadik.kiev.dao.ICustomerJpaDao;
import com.chadik.kiev.model.Customer;
import java.math.BigDecimal;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ivan.chadikovski
 */
@Repository
public class CustomerJpaDaoImpl extends GenericJpaDaoImpl<Customer, BigDecimal> implements ICustomerJpaDao {

    @Override
    public Class getClazz() {
        return Customer.class;
    }
}