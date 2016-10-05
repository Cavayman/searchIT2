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

    protected EntityManager em = EntityManagerHelper.getEntityManager();

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
            return null;
        }
    }

    @Override
    public void save(E entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.getTransaction().commit();
        }
    }

    @Override
    public void remove(E entity) {
        try {
            em.getTransaction().begin();
            em.remove(em.merge(entity));
        } catch (Exception e) {
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
            return null;
        } finally {
            em.getTransaction().commit();
        }
    }
}
