package com.example.repository.dao;

import java.util.List;

/**
 * Created by cavayman on 01.10.2016.
 */
public interface BaseDAO<E, N extends Number> {

        E findById(N id);

        void save(E entity);

        void remove(E entity);

        E update(E entity);

        List<E> findAll();
}
