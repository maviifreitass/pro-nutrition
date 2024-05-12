/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pro.nutrition.repository.entity.dao;

import com.pro.nutrition.repository.entity.Aliment;
import com.pro.nutrition.repository.entity.CustomerData;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maria
 */
public class DietDataDAO implements Serializable {

    private CustomerData customerData;
    private String dietName;
    private String mealName;
    private String mealDescription;
    private List<Aliment> aliments = new ArrayList();
    private Integer quantity;

    // DietPlanDB findDietByCustomer()
    private Aliment aliment;

    public CustomerData getCustomerData() {
        return customerData;
    }

    public void setCustomerData(CustomerData customerData) {
        this.customerData = customerData;
    }

    public String getDietName() {
        return dietName;
    }

    public void setDietName(String dietName) {
        this.dietName = dietName;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealDescription() {
        return mealDescription;
    }

    public void setMealDescription(String mealDescription) {
        this.mealDescription = mealDescription;
    }

    public List<Aliment> getAliments() {
        return aliments;
    }

    public void setAliments(List<Aliment> aliments) {
        this.aliments = aliments;
    }

    public Aliment getAliment() {
        return aliment;
    }

    public void setAliment(Aliment aliment) {
        this.aliment = aliment;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return new com.google.gson.Gson().toJson(this);
    }

}
