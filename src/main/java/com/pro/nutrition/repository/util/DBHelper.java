/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pro.nutrition.repository.util;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author maria
 * @param <T>
 */
public abstract class DBHelper<T> {

    @Inject
    private EntityManagerProducer entity;

    private final Class<T> entityClass;

    public DBHelper(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public T find(Long id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        jakarta.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }
}
