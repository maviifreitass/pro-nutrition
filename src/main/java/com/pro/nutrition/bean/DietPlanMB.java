package com.pro.nutrition.bean;

import com.github.adminfaces.template.exception.BusinessException;
import org.omnifaces.cdi.ViewScoped;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

import com.github.adminfaces.starter.service.CarService;
import static com.github.adminfaces.starter.util.Utils.addDetailMessage;
import com.pro.nutrition.repository.entity.Aliment;
import com.pro.nutrition.repository.entity.CustomerData;
import com.pro.nutrition.repository.entity.db.AlimentDB;
import com.pro.nutrition.repository.entity.db.CustomerDB;
import java.util.ArrayList;

/**
 * Created by rmpestano on 12/02/17.
 */
@Named
@ViewScoped
public class DietPlanMB implements Serializable {

    @Inject
    private CarService carService;

    @Inject
    private CustomerDB customerDB;

    @Inject
    private AlimentDB alimentDB;

    private Integer id;
    //   LazyDataModel<CustomerData> cars;
    private List<CustomerData> selectedCars;

    private List<CustomerData> customers = new ArrayList();
    private CustomerData selectedCustomerData;
    private List<Aliment> aliments = new ArrayList();
    private List<Aliment> selectedAliments;

    @PostConstruct
    public void initDataModel() {
        System.out.println("[initDataModel] start");
        customers = customerDB.findAll();
        aliments = alimentDB.findAll();
    }

    public List<String> completeModel(String query) {
        return carService.getModels(query);
    }

    public void findCarById(Integer id) {
        if (id == null) {
            throw new BusinessException("Provide Car ID to load");
        }
    }

    public void delete() {
        int numCars = 0;
        for (CustomerData selectedCar : selectedCars) {
            numCars++;
        }
        selectedCars.clear();
        addDetailMessage(numCars + " cars deleted successfully!");
    }

    public void selectCar(CustomerData car) {
        this.selectedCars.add(car);
    }

    public List<CustomerData> getSelectedCars() {
        return selectedCars;
    }

    public void setSelectedCars(List<CustomerData> selectedCars) {
        this.selectedCars = selectedCars;
    }

    public CustomerData getSelectedCustomerData() {
        return selectedCustomerData;
    }

    public void setSelectedCustomerData(CustomerData selectedCustomerData) {
        this.selectedCustomerData = selectedCustomerData;
    }

    public List<CustomerData> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerData> cars) {
        this.customers = cars;
    }

    public List<Aliment> getAliments() {
        return aliments;
    }

    public void setAliments(List<Aliment> aliments) {
        this.aliments = aliments;
    }

    public List<Aliment> getSelectedAliments() {
        return selectedAliments;
    }

    public void setSelectedAliments(List<Aliment> selectedAliments) {
        this.selectedAliments = selectedAliments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
