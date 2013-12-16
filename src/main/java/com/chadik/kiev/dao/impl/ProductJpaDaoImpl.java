package com.chadik.kiev.dao.impl;

import com.chadik.kiev.dao.IProductJpaDao;
import com.chadik.kiev.model.Product;
import java.math.BigDecimal;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ivan.chadikovski
 */
@Repository
public class ProductJpaDaoImpl extends GenericJpaDaoImpl<Product, BigDecimal> implements IProductJpaDao {

    @Override
    public Class getClazz() {
        return Product.class;
    }
}