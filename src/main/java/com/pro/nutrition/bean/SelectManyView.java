/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pro.nutrition.bean;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.omnifaces.cdi.ViewScoped;

/**
 *
 * @author maria
 */
@Named
@ViewScoped
public class SelectManyView implements Serializable {
    
    private List<String> countries;
    private List<String> selectedCountries;
    
    @PostConstruct
    public void init() {
        // Inicializar a lista de países
        countries = new ArrayList<>();
        countries.add("United States");
        countries.add("Canada");
        countries.add("United Kingdom");
        // Adicionar outros países conforme necessário
        
        // Inicializar a lista de países selecionados
        selectedCountries = new ArrayList<>();
    }

    // Getters e setters
    
    public List<String> getCountries() {
        return countries;
    }

    public List<String> getSelectedCountries() {
        return selectedCountries;
    }

    public void setSelectedCountries(List<String> selectedCountries) {
        this.selectedCountries = selectedCountries;
    }
}