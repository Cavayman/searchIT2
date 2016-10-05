package com.example.repository.dao.impl;

import com.example.repository.dao.MetadataDAO;
import com.example.repository.model.Metadata;

import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Created by cavayman on 01.10.2016.
 */
public class MetadataDAOImpl extends BaseDAOImpl<Metadata,Integer> implements MetadataDAO {
    public MetadataDAOImpl(Class<Metadata> entityClass) {
        super(entityClass);
    }

    public Metadata findByName(String name) {
        try {
            Query query = em.createQuery("SELECT m FROM Metadata m WHERE m.fileName LIKE :name");
            query.setParameter("name", "%" + name + "%");
            return (Metadata) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
