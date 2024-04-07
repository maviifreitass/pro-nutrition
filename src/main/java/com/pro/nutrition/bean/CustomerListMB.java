package com.pro.nutrition.bean;

import com.github.adminfaces.template.exception.BusinessException;
import org.omnifaces.cdi.ViewScoped;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

import com.github.adminfaces.starter.infra.model.Filter;
import static com.pro.nutrition.util.Utils.addDetailMessage;
import com.pro.nutrition.repository.entity.CustomerData;
import com.pro.nutrition.repository.entity.db.CustomerDB;
import java.util.ArrayList;

/**
 * Created by maria.
 */
@Named
@ViewScoped
public class CustomerListMB implements Serializable {

    @Inject
    private CustomerDB customerDB;

    private Integer id;
    private Filter<CustomerData> filter = new Filter<>(new CustomerData());
    private CustomerData selectedCustomerData;
    private List<CustomerData> customers = new ArrayList();
    private List<CustomerData> filteredValue;

    @PostConstruct
    public void initDataModel() {
        System.out.println("[initDataModel] start");
        customers = customerDB.findAll();
    }

    public void clear() {
        filter = new Filter<>(new CustomerData());
    }

    public void delete() {
        customerDB.remove(selectedCustomerData);
        customers.remove(selectedCustomerData);
        addDetailMessage("Paciente removido");
    }

    public List<CustomerData> getFilteredValue() {
        return filteredValue;
    }

    public void setFilteredValue(List<CustomerData> filteredValue) {
        this.filteredValue = filteredValue;
    }

    public CustomerData getSelectedCustomerData() {
        return selectedCustomerData;
    }

    public void setSelectedCustomerData(CustomerData selectedCustomerData) {
        this.selectedCustomerData = selectedCustomerData;
    }

    public List<CustomerData> getCars() {
        return customers;
    }

    public void setCars(List<CustomerData> customers) {
        this.customers = customers;
    }

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

    public List<CustomerData> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerData> customers) {
        this.customers = customers;
    }

}
