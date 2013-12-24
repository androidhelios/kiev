package com.chadik.kiev.service.impl;

import com.chadik.kiev.dao.IGenericJpaDao;
import com.chadik.kiev.dao.impl.ProductJpaDaoImpl;
import com.chadik.kiev.model.Product;
import com.chadik.kiev.service.IProductJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author ivan.chadikovski
 */
@Service
public class ProductJpaServiceImpl extends GenericJpaServiceImpl<Product> implements IProductJpaService {

    @Autowired
    @Qualifier("productJpaDaoImpl")
    private IGenericJpaDao productJpaDaoImpl;
            
    @Override
    public IGenericJpaDao getGenericDao() {
        return productJpaDaoImpl;
    }  


}