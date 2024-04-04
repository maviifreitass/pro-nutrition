/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pro.nutrition.repository.entity.db;

import com.pro.nutrition.repository.entity.CustomerData;
import com.pro.nutrition.repository.util.PostgresWrapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author maria
 */
@Named
@ApplicationScoped
public class CustomerDB implements Serializable {

    @Inject
    private PostgresWrapper pw;

    @Inject
    private EntityManager em;

    @Inject
    private UserDB userDB;

    @Inject
    private DietPlanDB dietPlanDB;

    public CustomerDB() {
    }

    public CustomerData findById(Long id) {
        return em.find(CustomerData.class, id);
    }

    public List<CustomerData> findAll() {
        System.out.println("CRITERIA BUILDER ALL");
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<CustomerData> criteriaQuery = criteriaBuilder.createQuery(CustomerData.class);
        Root<CustomerData> root = criteriaQuery.from(CustomerData.class);
        criteriaQuery.select(root);

        return em.createQuery(criteriaQuery).getResultList();
    }

    public void save(CustomerData customer) {
        System.out.println("############## SAVE ###############");
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


