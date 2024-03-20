/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pro.nutrition.repository.entity;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 *
 * @author maria
 */
// @Entity
@Table(name = "meals_plan")
public class MealsPlan implements Serializable {

    @ManyToMany
    @JoinColumn(name = "diet_plan_id")
    private DietPlan dietPlan;

    @ManyToMany
    @JoinColumn(name = "meals_id")
    private Meals meals;

    public DietPlan getDietPlan() {
        return dietPlan;
    }

    public void setDietPlan(DietPlan dietPlan) {
        this.dietPlan = dietPlan;
    }

    public Meals getMeals() {
        return meals;
    }

    public void setMeals(Meals meals) {
        this.meals = meals;
    }

}
