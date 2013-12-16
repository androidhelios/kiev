package com.chadik.kiev.dao.impl;

import com.chadik.kiev.dao.ITraderJpaDao;
import com.chadik.kiev.model.Trader;
import java.math.BigDecimal;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ivan.chadikovski
 */
@Repository
public class TraderJpaDaoImpl extends GenericJpaDaoImpl<Trader, BigDecimal> implements ITraderJpaDao {

    @Override
    public Class getClazz() {
        return Trader.class;
    }
    
}
