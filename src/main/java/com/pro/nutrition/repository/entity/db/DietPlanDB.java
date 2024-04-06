/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pro.nutrition.repository.entity.db;

import com.pro.nutrition.repository.entity.CustomerData;
import com.pro.nutrition.repository.entity.DietPlan;
import com.pro.nutrition.repository.entity.Meals;
import com.pro.nutrition.repository.entity.MealsItems;
import com.pro.nutrition.repository.entity.MealsPlan;
import com.pro.nutrition.repository.entity.dao.DietDataDAO;
import com.pro.nutrition.repository.entity.dao.FoodItemDAO;
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

    private DietPlan dietPlan;
    private Meals meals;

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

    public void create(DietDataDAO item) {
        dietPlan.setCreateTime(new Date());
        dietPlan.setUpdateTime(new Date());
        dietPlan.setName(item.getDietName());

        em.persist(dietPlan);

        meals.setCreateTime(new Date());
        meals.setName(item.getMealName());
        meals.setDescription(item.getMealDescription());

        em.persist(meals);

        MealsPlan mealsPlan = new MealsPlan();
        mealsPlan.setDietPlan(dietPlan);
        mealsPlan.setMeals(meals);

        em.persist(mealsPlan);

        for (FoodItemDAO food : item.getAliments()) {
            MealsItems mealItem = new MealsItems(); // Crie um novo objeto mealItem para cada item de alimento
            mealItem.setCreateTime(new Date());
            mealItem.setMeals(meals);
            mealItem.setQuantity(Double.valueOf(food.getQuantity()));
            mealItem.setAliment(food.getAliment());
            em.persist(mealItem); // Persista o novo objeto mealItem
        }

        CustomerData customerData = item.getCustomerData();
        customerData.setDietPlan(dietPlan);
        em.persist(customerData);
    }

}
