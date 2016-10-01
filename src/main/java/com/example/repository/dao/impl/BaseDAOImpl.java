package com.example.repository.dao.impl;

import com.example.repository.dao.BaseDAO;

import java.util.List;

/**
 * Created by cavayman on 01.10.2016.
 */
public class BaseDAOImpl<E, N extends Number> implements BaseDAO<E,N> {

    @Override
    public E findById(N id) {
        return null;
    }

    @Override
    public void save(E entity) {

    }

    @Override
    public void remove(E entity) {

    }

    @Override
    public E update(E entity) {
        return null;
    }

    @Override
    public List<E> findAll() {
        return null;
    }
}
