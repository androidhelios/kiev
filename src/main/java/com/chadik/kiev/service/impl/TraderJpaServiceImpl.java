package com.chadik.kiev.service.impl;

import com.chadik.kiev.dao.IGenericJpaDao;
import com.chadik.kiev.dao.impl.TraderJpaDaoImpl;
import com.chadik.kiev.model.Trader;
import com.chadik.kiev.service.ITraderJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ivan.chadikovski
 */
@Service
public class TraderJpaServiceImpl extends GenericJpaServiceImpl<Trader> implements ITraderJpaService {

    @Autowired
    private TraderJpaDaoImpl traderJpaDaoImpl;
    
    @Override
    public IGenericJpaDao getGenericDao() {
        return getTraderJpaDaoImpl();
    }  

    /**
     * @return the traderJpaDaoImpl
     */
    public TraderJpaDaoImpl getTraderJpaDaoImpl() {
        return traderJpaDaoImpl;
    }

    /**
     * @param traderJpaDaoImpl the traderJpaDaoImpl to set
     */
    public void setTraderJpaDaoImpl(TraderJpaDaoImpl traderJpaDaoImpl) {
        this.traderJpaDaoImpl = traderJpaDaoImpl;
    }
}