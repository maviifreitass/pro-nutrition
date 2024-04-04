/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pro.nutrition.repository.entity.db;

import com.pro.nutrition.repository.entity.DietPlan;
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
public class DietPlanDB {

    @Inject
    private PostgresWrapper pw;

    @Inject
    private EntityManager em;
    
    public DietPlan findById(Long id) {
        return em.find(DietPlan.class, id);
    }

    public List<DietPlan> findAll() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<DietPlan> criteriaQuery = criteriaBuilder.createQuery(DietPlan.class);
        Root<DietPlan> root = criteriaQuery.from(DietPlan.class);
        criteriaQuery.select(root);

        return em.createQuery(criteriaQuery).getResultList();
    }

    public void save(DietPlan customer) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(customer); // Salva ou atualiza a entidade
            transaction.commit(); // Confirma a transação
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback(); // Rollback se ocorrer um erro
            }
            e.printStackTrace(); // Trate o erro de alguma forma adequada
        }
    }
    
}
