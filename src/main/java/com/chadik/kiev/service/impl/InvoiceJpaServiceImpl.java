/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chadik.kiev.service.impl;

import com.chadik.kiev.dao.IGenericJpaDao;
import com.chadik.kiev.dao.impl.InvoiceJpaDaoImpl;
import com.chadik.kiev.model.Invoice;
import com.chadik.kiev.service.IInvoiceJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ivan.chadikovski
 */
@Service
public class InvoiceJpaServiceImpl extends GenericJpaServiceImpl<Invoice> implements IInvoiceJpaService {

    @Autowired
    private InvoiceJpaDaoImpl invoiceJpaDaoImpl;
    
    @Override
    public IGenericJpaDao getGenericDao() {
        return getInvoiceJpaDaoImpl();
    }  

    /**
     * @return the invoiceJpaDaoImpl
     */
    public InvoiceJpaDaoImpl getInvoiceJpaDaoImpl() {
        return invoiceJpaDaoImpl;
    }

    /**
     * @param invoiceJpaDaoImpl the invoiceJpaDaoImpl to set
     */
    public void setInvoiceJpaDaoImpl(InvoiceJpaDaoImpl invoiceJpaDaoImpl) {
        this.invoiceJpaDaoImpl = invoiceJpaDaoImpl;
    }
}