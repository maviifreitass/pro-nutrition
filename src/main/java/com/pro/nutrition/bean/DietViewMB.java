package com.pro.nutrition.bean;

import com.pro.nutrition.repository.entity.CustomerData;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

import com.pro.nutrition.repository.entity.dao.DietDataDAO;
import com.pro.nutrition.repository.entity.db.DietPlanDB;
import jakarta.enterprise.context.RequestScoped;
import java.util.ArrayList;

/**
 * Classe que controla o plano de dieta. Esta classe gerencia as operações
 * relacionadas à criação e manipulação do plano de dieta para os clientes.
 */
@Named
@RequestScoped
public class DietViewMB implements Serializable {

    @Inject
    private DietPlanDB dietDB;
    
    private CustomerData customer;
    private List<DietDataDAO> listData = new ArrayList();

    public void getDietByCustomer(Long id) {
        listData = dietDB.findDietByCustomer(id);
    }

}
