package com.chadik.kiev.service.impl;

import com.chadik.kiev.dao.IGenericJpaDao;
import com.chadik.kiev.dao.impl.TraderJpaDaoImpl;
import com.chadik.kiev.model.Trader;
import com.chadik.kiev.service.ITraderJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author ivan.chadikovski
 */
@Service
public class TraderJpaServiceImpl extends GenericJpaServiceImpl<Trader> implements ITraderJpaService {

    @Autowired
    @Qualifier("traderJpaDaoImpl")
    private IGenericJpaDao traderJpaDaoImpl;
    
    @Override
    public IGenericJpaDao getGenericDao() {
        return traderJpaDaoImpl;
    }  

}