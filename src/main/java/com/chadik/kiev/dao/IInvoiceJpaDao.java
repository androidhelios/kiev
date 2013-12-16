/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chadik.kiev.dao;

import com.chadik.kiev.model.Invoice;
import java.math.BigDecimal;

/**
 *
 * @author ivan.chadikovski
 */
public interface IInvoiceJpaDao extends IGenericJpaDao<Invoice, BigDecimal> {
    
}
