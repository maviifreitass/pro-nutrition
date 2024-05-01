/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pro.nutrition.repository.entity.db;

import com.pro.nutrition.repository.entity.CustomerData;
import com.pro.nutrition.repository.entity.CustomerData_;
import com.pro.nutrition.repository.util.PostgresWrapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Classe que fornece métodos para manipulação de dados relacionados aos clientes.
 * Esta classe é responsável por realizar operações de persistência e recuperação de dados relacionados aos clientes.
 */
@Named
@ApplicationScoped
public class CustomerDB implements Serializable {

    @Inject
    private PostgresWrapper pw;

    @Inject
    private EntityManager em;

    /**
     * Construtor padrão da classe CustomerDB.
     */
    public CustomerDB() {
    }

    /**
     * Busca um cliente pelo seu ID.
     * @param id O ID do cliente a ser buscado.
     * @return O cliente encontrado ou null se não encontrado.
     */
    public CustomerData findById(Long id) {
        return em.find(CustomerData.class, id);
    }

    /**
     * Recupera todos os clientes.
     * @return Uma lista de todos os clientes armazenados.
     */
    public List<CustomerData> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CustomerData> criteriaQuery = cb.createQuery(CustomerData.class);
        Root<CustomerData> root = criteriaQuery.from(CustomerData.class);
        criteriaQuery.select(root);

        Predicate p = cb.conjunction();
        p = cb.and(p, cb.isNull(root.get(CustomerData_.deleteTime)));

        criteriaQuery.where(p);
        
        return em.createQuery(criteriaQuery).getResultList();
    }

    /**
     * Salva um cliente no banco de dados.
     * @param customer O cliente a ser salvo.
     */
    public void save(CustomerData customer) {
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
    
    /**
     * Remove um cliente do banco de dados (lógica de exclusão).
     * @param customer O cliente a ser removido.
     */
    public void remove(CustomerData customer) {
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            customer.setDeleteTime(new Date()); 
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


