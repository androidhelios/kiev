package com.chadik.kiev.service.impl;

import com.chadik.kiev.dao.IGenericJpaDao;
import com.chadik.kiev.service.IGenericJpaService;
import com.chadik.kiev.util.HibernateUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

/**
 *
 * @author ivan.chadikovski
 */
@Service
public abstract class GenericJpaServiceImpl<T> implements IGenericJpaService<T> {

    private IGenericJpaDao genericDao;

    @Override
    public List<T> getAll() {
        List<T> t = new ArrayList<T>();
        try {
            HibernateUtil.beginTransaction();
            t = getGenericDao().findAll();
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Handle your error here");
        }
        return t;
    }

    @Override
    public void save(T t) {
        try {
            HibernateUtil.beginTransaction();
            getGenericDao().save(t);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Handle your error here");
            HibernateUtil.rollbackTransaction();
        }
    }

    @Override
    public T findById(BigDecimal id) {
        T t = null;
        try {
            HibernateUtil.beginTransaction();
            t = (T) getGenericDao().findByID(id);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Handle your error here");
        }
        return t;
    }

    @Override
    public void delete(T t) {
        try {
            HibernateUtil.beginTransaction();
            getGenericDao().delete(t);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Handle your error here");
            HibernateUtil.rollbackTransaction();
        }
    }

    /**
     * @return the genericDao
     */
    public abstract IGenericJpaDao getGenericDao();

    /**
     * @param genericDao the genericDao to set
     */
    public void setGenericDao(IGenericJpaDao genericDao) {
        this.genericDao = genericDao;
    }
}
