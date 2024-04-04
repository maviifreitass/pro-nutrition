/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pro.nutrition.repository.entity;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author maria
 */
@Entity
@Table(name = "aliment")
public class Aliment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "calories")
    private Double calories;

    @Column(name = "fat_total")
    private Double fatTotal;

    @Column(name = "fat_satured")
    private Double fatSatured;

    @Column(name = "protein")
    private Double protein;

    @Column(name = "sodium")
    private Double sodium;

    @Column(name = "potassium")
    private Double potassium;

    @Column(name = "cholesterol")
    private Double cholesterol;

    @Column(name = "carbohydrates")
    private Double carbohydrates;

    @Column(name = "fiber")
    private Double fiber;

    @Column(name = "sugar")
    private Double sugar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public Double getFatTotal() {
        return fatTotal;
    }

    public void setFatTotal(Double fatTotal) {
        this.fatTotal = fatTotal;
    }

    public Double getFatSatured() {
        return fatSatured;
    }

    public void setFatSatured(Double fatSatured) {
        this.fatSatured = fatSatured;
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public Double getSodium() {
        return sodium;
    }

    public void setSodium(Double sodium) {
        this.sodium = sodium;
    }

    public Double getPotassium() {
        return potassium;
    }

    public void setPotassium(Double potassium) {
        this.potassium = potassium;
    }

    public Double getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(Double cholesterol) {
        this.cholesterol = cholesterol;
    }

    public Double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public Double getFiber() {
        return fiber;
    }

    public void setFiber(Double fiber) {
        this.fiber = fiber;
    }

    public Double getSugar() {
        return sugar;
    }

    public void setSugar(Double sugar) {
        this.sugar = sugar;
    }

    @Override
    public String toString() {
        return new com.google.gson.Gson().toJson(this);
    }
    
}
