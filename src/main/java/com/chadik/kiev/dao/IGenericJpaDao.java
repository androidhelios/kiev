package com.chadik.kiev.dao;

/**
 *
 * @author ivan.chadikovski
 */
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;

public interface IGenericJpaDao<T, ID extends Serializable> {

    /**
     *
     * @param t
     */
    public void save(T t);

    /**
     *
     * @param t
     */
    public void merge(T t);

    /**
     *
     * @param t
     */
    public void delete(T t);

    /**
     *
     * @param query
     * @return
     */
    public List<T> findMany(Query query);

    /**
     *
     * @param query
     * @return
     */
    public T findOne(Query query);

    /**
     *
     * @param clazz
     * @return
     */
    public List findAll();

    /**
     *
     * @param clazz
     * @param id
     * @return
     */
    public T findByID(BigDecimal id);
}