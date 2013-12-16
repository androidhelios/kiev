package com.chadik.kiev.service;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author ivan.chadikovski
 */
public interface IGenericJpaService<T> {
    
    /**
     *
     * @return
     */
    public List<T> getAll();
    
    /**
     *
     * @param t
     */
    public void save(T t);
    
    /**
     *
     * @param id
     * @return
     */
    public T findById(BigDecimal id);
    
    /**
     *
     * @param t
     */
    public void delete(T t);

}
