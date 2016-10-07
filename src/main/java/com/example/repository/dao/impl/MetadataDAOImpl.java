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
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT m FROM Metadata m WHERE m.fileName LIKE :name");
            query.setParameter("name", "%" + name + "%");
            Metadata metadata=(Metadata) query.getSingleResult();
            System.out.println("FROM DAO"+name);

            System.out.println("FROM DAO"+metadata);
            em.getTransaction().commit();

            return metadata;
        } catch (NoResultException e) {
          if(em.getTransaction()!=null)
              em.getTransaction().rollback();
            return null;
        }
    }
}
