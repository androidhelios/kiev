package com.chadik.kiev.dao.impl;

import com.chadik.kiev.dao.IInvoiceJpaDao;
import com.chadik.kiev.model.Invoice;
import java.math.BigDecimal;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ivan.chadikovski
 */
@Repository
public class InvoiceJpaDaoImpl extends GenericJpaDaoImpl<Invoice, BigDecimal> implements IInvoiceJpaDao {

    @Override
    public Class getClazz() {
        return Invoice.class;
    }
}
