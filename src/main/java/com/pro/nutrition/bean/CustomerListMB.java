package com.pro.nutrition.bean;

import com.github.adminfaces.template.exception.BusinessException;
import org.omnifaces.cdi.ViewScoped;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

import com.github.adminfaces.starter.infra.model.Filter;
import com.github.adminfaces.starter.service.CarService;
import static com.github.adminfaces.starter.util.Utils.addDetailMessage;
import com.pro.nutrition.repository.entity.CustomerData;
import com.pro.nutrition.repository.entity.db.CustomerDB;
import java.util.ArrayList;

/**
 * Created by rmpestano on 12/02/17.
 */
@Named
@ViewScoped
public class CustomerListMB implements Serializable {
    @Inject
    private CarService carService;

    @Inject
    private CustomerDB customerDB;
    
    private Integer id;
    //   LazyDataModel<CustomerData> cars;
    private Filter<CustomerData> filter = new Filter<>(new CustomerData());
    private List<CustomerData> selectedCars;
    private CustomerData selectedCustomerData;
    private List<CustomerData> cars = new ArrayList();
    private List<CustomerData> filteredValue;

    @PostConstruct
    public void initDataModel() {
        System.out.println("[initDataModel] start");
        cars = customerDB.findAll();
    }

    public void clear() {
        filter = new Filter<>(new CustomerData());
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
        customerDB.remove(selectedCustomerData); 
        cars.remove(selectedCustomerData);
        addDetailMessage("Paciente removido");
    }

    public void selectCar(CustomerData car) {
        this.selectedCars.add(car);
    }

    public List<CustomerData> getSelectedCars() {
        return selectedCars;
    }

    public List<CustomerData> getFilteredValue() {
        return filteredValue;
    }

    public void setFilteredValue(List<CustomerData> filteredValue) {
        this.filteredValue = filteredValue;
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

    public List<CustomerData> getCars() {
        return cars;
    }

    public void setCars(List<CustomerData> cars) {
        this.cars = cars;
    }

    /*  public LazyDataModel<CustomerData> getCars() {
        return cars; 
    }

    public void setCars(LazyDataModel<CustomerData> cars) {
        this.cars = cars; 
    }*/

    public Filter<CustomerData> getFilter() {
        return filter;
    }

    public void setFilter(Filter<CustomerData> filter) {
        this.filter = filter;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    
}
