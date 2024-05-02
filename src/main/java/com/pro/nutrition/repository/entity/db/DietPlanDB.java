/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pro.nutrition.repository.entity.db;

import com.pro.nutrition.repository.entity.Aliment;
import com.pro.nutrition.repository.entity.CustomerData;
import com.pro.nutrition.repository.entity.DietPlan;
import com.pro.nutrition.repository.entity.Meals;
import com.pro.nutrition.repository.entity.MealsItems;
import com.pro.nutrition.repository.entity.MealsPlan;
import com.pro.nutrition.repository.entity.dao.DietDataDAO;
import com.pro.nutrition.repository.util.PostgresWrapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

/**
 * Classe de repositório do banco de dados que irá estabelecer o cadastro de
 * dietas
 */
@Named
@ApplicationScoped
public class DietPlanDB {

    @Inject
    private PostgresWrapper pw;

    @Inject
    private EntityManager em;
    
    @Inject
    private MealsPlanDB mealsPlanDB;

    private DietPlan dietPlan = new DietPlan();
    private Meals meals = new Meals();

    /**
     * Busca um plano de dieta pelo ID.
     *
     * @param id O ID do plano de dieta a ser buscado.
     * @return O plano de dieta encontrado, ou null se não encontrado.
     */
    public DietPlan findById(Long id) {
        return em.find(DietPlan.class, id);
    }

    /**
     * Retorna uma lista de todos os planos de dieta.
     *
     * @return Uma lista contendo todos os planos de dieta.
     */
    public List<DietPlan> findAll() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<DietPlan> criteriaQuery = criteriaBuilder.createQuery(DietPlan.class);
        Root<DietPlan> root = criteriaQuery.from(DietPlan.class);
        criteriaQuery.select(root);

        return em.createQuery(criteriaQuery).getResultList();
    }

    /**
     * Salva ou atualiza um plano de dieta.
     *
     * @param dietPlan O plano de dieta a ser salvo.
     */
    public void save(DietPlan dietPlan) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(dietPlan);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Cria um novo plano de dieta junto com as informações associadas.
     *
     * @param item Os dados necessários para criar o plano de dieta.
     */
    public void create(DietDataDAO item) {
        EntityTransaction transaction = em.getTransaction();

        transaction.begin(); 

        dietPlan.setCreateTime(new Date());
        dietPlan.setName(item.getDietName());

        em.persist(dietPlan);

        meals.setCreateTime(new Date());
        meals.setName(item.getMealName());
        meals.setDescription(item.getMealDescription());

        em.persist(meals);

//        transaction.commit();
        
        MealsPlan mealsPlan = new MealsPlan();
        mealsPlan.setDietPlan(dietPlan);
        mealsPlan.setMeals(meals);
        
        mealsPlanDB.create(mealsPlan);

//        transaction.begin(); 
        for (Aliment food : item.getAliments()) {
            MealsItems mealItem = new MealsItems();
            mealItem.setCreateTime(new Date());
            mealItem.setMeals(meals);
            mealItem.setAliment(food);
            em.persist(mealItem);
        }

        CustomerData customerData = item.getCustomerData();
        customerData.setDietPlan(dietPlan);
        customerData = em.merge(customerData);
        em.persist(customerData);
        transaction.commit();
    }

}
