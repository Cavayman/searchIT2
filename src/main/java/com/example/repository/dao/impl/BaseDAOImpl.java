package com.example.repository.dao.impl;

import com.example.repository.dao.BaseDAO;
import com.example.util.EntityManagerHelper;

import javax.persistence.*;
import java.util.List;

/**
 * Created by cavayman on 01.10.2016.
 */

public class BaseDAOImpl<E, N extends Number> implements BaseDAO<E, N> {

    private Class<E> entityClass;

    protected EntityManager em = EntityManagerHelper.getEntityManagerFactory().createEntityManager();

    public BaseDAOImpl(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public E findById(N id) {
        try {
            Query query = em.createQuery("from " + entityClass.getSimpleName() + " e where e.id = :id");
            query.setParameter("id", id);
            return (E) query.getSingleResult();
        } catch (NoResultException e) {
            if(em.getTransaction()!=null)
                em.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public void save(E entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            if(em.getTransaction()!=null)
                em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
       }
    }

    @Override
    public void remove(E entity) {
        try {
            em.getTransaction().begin();
            em.remove(em.merge(entity));
        } catch (Exception e) {
            if(em.getTransaction()!=null)
                em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.getTransaction().commit();
        }
    }

    @Override
    public E update(E entity) {
        try {
            em.getTransaction().begin();
         return   em.merge(entity);
        } catch (NoResultException e) {
            if(em.getTransaction()!=null)
                em.getTransaction().rollback();
            return null;
        } finally {
            em.getTransaction().commit();
        }
    }

    @Override
    public List<E> findAll() {
        try {
            em.getTransaction().begin();
            return em.createQuery("from " + entityClass.getSimpleName()).getResultList();
        } catch (NoResultException e) {
            if(em.getTransaction()!=null)
                em.getTransaction().rollback();
            return null;
        } finally {
            em.getTransaction().commit();
        }
    }
}
