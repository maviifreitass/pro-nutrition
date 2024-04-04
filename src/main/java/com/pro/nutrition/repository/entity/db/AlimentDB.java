/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pro.nutrition.repository.entity.db;

import com.pro.nutrition.repository.entity.Aliment;
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
public class AlimentDB {

    @Inject
    private PostgresWrapper pw;

    @Inject
    private EntityManager em;

    public Aliment findById(Long id) {
        return em.find(Aliment.class, id);
    }

    public List<Aliment> findAll() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Aliment> criteriaQuery = criteriaBuilder.createQuery(Aliment.class);
        Root<Aliment> root = criteriaQuery.from(Aliment.class);
        criteriaQuery.select(root);

        return em.createQuery(criteriaQuery).getResultList();
    }

    public void save(Aliment customer) {
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
