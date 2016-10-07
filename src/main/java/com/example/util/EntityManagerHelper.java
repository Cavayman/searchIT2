package com.example.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by cavayman on 04.10.2016.
 */
public class EntityManagerHelper {

    private static final EntityManagerFactory entityManager;

    static {
        try {
            entityManager = Persistence.createEntityManagerFactory("CRM");
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManager;
    }

}
