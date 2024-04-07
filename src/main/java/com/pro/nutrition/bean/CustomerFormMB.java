/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pro.nutrition.bean;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.omnifaces.cdi.ViewScoped;
import jakarta.annotation.ManagedBean;

import java.io.Serializable;

import static com.pro.nutrition.util.Utils.addDetailMessage;
import com.pro.nutrition.repository.entity.CustomerData;
import com.pro.nutrition.repository.entity.db.CustomerDB;

/**
 * @author maria
 */
@Named
@ViewScoped
@ManagedBean
public class CustomerFormMB implements Serializable {

    private Integer id;
    private CustomerData customer;

    @Inject
    CustomerDB customerDB;

    public void init() {
        customer = new CustomerData();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void save() {
        customerDB.save(customer); 
        addDetailMessage("Salvo!");
    }

    public CustomerData getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerData customer) {
        this.customer = customer;
    }

}
