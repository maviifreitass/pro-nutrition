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
 * Classe que fornece métodos para manipulação de dados relacionados aos alimentos.
 * Esta classe é responsável por realizar operações de persistência e recuperação de dados relacionados aos alimentos.
 */
@Named
@ApplicationScoped
public class AlimentDB {

    @Inject
    private PostgresWrapper pw;

    @Inject
    private EntityManager em;

    /**
     * Busca um alimento pelo seu ID.
     * @param id O ID do alimento a ser buscado.
     * @return O alimento encontrado ou null se não encontrado.
     */
    public Aliment findById(Long id) {
        return em.find(Aliment.class, id);
    }

    /**
     * Recupera todos os alimentos.
     * @return Uma lista de todos os alimentos armazenados.
     */
    public List<Aliment> findAll() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Aliment> criteriaQuery = criteriaBuilder.createQuery(Aliment.class);
        Root<Aliment> root = criteriaQuery.from(Aliment.class);
        criteriaQuery.select(root);

        return em.createQuery(criteriaQuery).getResultList();
    }

    /**
     * Salva um alimento no banco de dados.
     * @param customer O alimento a ser salvo.
     */
    public void save(Aliment customer) {
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
