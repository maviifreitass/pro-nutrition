/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pro.nutrition.repository.entity.db;

import com.pro.nutrition.repository.entity.User;
import com.pro.nutrition.repository.util.PostgresWrapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

/**
 *
 * @author maria
 */
@Named
@ApplicationScoped
public class UserDB {

    @Inject
    private PostgresWrapper pw;

    @Inject
    private EntityManager em;

    public User findById(Long id) {
        return em.find(User.class, id);
    }

    public List<User> findAll() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);

        return em.createQuery(criteriaQuery).getResultList();
    }

    public void save(User customer) {
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(customer);
            transaction.commit(); 
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback(); 
            }
            e.printStackTrace(); 
        }
    }
}
