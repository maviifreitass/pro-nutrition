package com.pro.nutrition.bean;

import static com.github.adminfaces.starter.util.Utils.addDetailMessage;
import org.omnifaces.cdi.ViewScoped;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

import com.pro.nutrition.repository.entity.Aliment;
import com.pro.nutrition.repository.entity.CustomerData;
import com.pro.nutrition.repository.entity.dao.DietDataDAO;
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
    private CustomerDB customerDB;

    @Inject
    private AlimentDB alimentDB;

    private Integer id;
    //   LazyDataModel<CustomerData> cars;
    private List<CustomerData> selectedCars;

    private List<CustomerData> customers = new ArrayList();
    private List<Aliment> aliments = new ArrayList();
    private List<Aliment> selectedAliments;

    private DietDataDAO dietDAO = new DietDataDAO();

    @PostConstruct
    public void initDataModel() {
        System.out.println("[initDataModel] start");
        customers = customerDB.findAll();
        aliments = alimentDB.findAll();
    }

    public void save() {
        System.out.println("oiiiiiiiiiii");
        System.out.println("DIETDATADAO >>>>>"+dietDAO);
        addDetailMessage("Salvo!");
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

    public DietDataDAO getDietDAO() {
        return dietDAO;
    }

    public void setDietDAO(DietDataDAO dietDAO) {
        this.dietDAO = dietDAO;
    }

}
