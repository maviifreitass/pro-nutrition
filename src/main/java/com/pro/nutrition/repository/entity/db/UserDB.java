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
 * Classe de repositório do banco de dados que irá realizar trazes os dados dos usuários
 * ! Autenticação e autorização serão implementadas em outra classe !
 */
@Named
@ApplicationScoped
public class UserDB {

    @Inject
    private PostgresWrapper pw;

    @Inject
    private EntityManager em;

    /**
     * Busca um usuário pelo ID.
     *
     * @param id O ID do usuário a ser buscado.
     * @return O usuário encontrado, ou null se não encontrado.
     */
    public User findById(Long id) {
        return em.find(User.class, id);
    }

    /**
     * Retorna uma lista de todos os usuários.
     *
     * @return Uma lista contendo todos os usuários.
     */
    public List<User> findAll() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);

        return em.createQuery(criteriaQuery).getResultList();
    }

    /**
     * Salva o usuário.
     *
     * @param user O usuário a ser salvo.
     */
    public void save(User user) {
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
