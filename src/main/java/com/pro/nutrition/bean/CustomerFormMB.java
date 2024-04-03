/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pro.nutrition.bean;

import com.github.adminfaces.starter.model.Car;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;
import jakarta.annotation.ManagedBean;

import java.io.Serializable;

import static com.github.adminfaces.starter.util.Utils.addDetailMessage;
import static com.github.adminfaces.template.util.Assert.has;
import com.pro.nutrition.repository.entity.CustomerData;
import com.pro.nutrition.repository.entity.db.CustomerDB;

/**
 * @author rmpestano
 */
@Named
@ViewScoped
@ManagedBean
public class CustomerFormMB implements Serializable {

    private Integer id;
    private Car car;

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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void remove() {
        if (has(car) && has(car.getId())) {
            addDetailMessage("Car " + car.getModel()
                    + " removed successfully");
            Faces.getFlash().setKeepMessages(true);
            Faces.redirect("customer-list.jsf");
        }
    }

    public void save() {
        String msg = "Salvo!";
        System.out.println(customer);
        addDetailMessage(msg);
    }

    public void clear() {
        car = new Car();
        id = null;
    }

    public boolean isNew() {
        return car == null || car.getId() == null;
    }

    public CustomerData getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerData customer) {
        this.customer = customer;
    }

}
