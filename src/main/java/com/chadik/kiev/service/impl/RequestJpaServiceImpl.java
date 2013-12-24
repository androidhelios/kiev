package com.chadik.kiev.service.impl;

import com.chadik.kiev.dao.IGenericJpaDao;
import com.chadik.kiev.dao.impl.RequestJpaDaoImpl;
import com.chadik.kiev.model.Request;
import com.chadik.kiev.service.IRequestJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author ivan.chadikovski
 */
@Service
public class RequestJpaServiceImpl extends GenericJpaServiceImpl<Request> implements IRequestJpaService {

    @Autowired
    @Qualifier("requestJpaDaoImpl")
    private IGenericJpaDao requestJpaDaoImpl;
        
    @Override
    public IGenericJpaDao getGenericDao() {
        return requestJpaDaoImpl;
    }  

}