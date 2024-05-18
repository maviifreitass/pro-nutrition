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
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.export.Exporter;

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
    private Exporter<DataTable> textExporter;

    public void getDietByCustomer(Long id) {
        listData = dietDB.findDietByCustomer(id);
    }

    public void setDietDB(DietPlanDB dietDB) {
        this.dietDB = dietDB;
    }

    public void setCustomer(CustomerData customer) {
        this.customer = customer;
    }

    public void setListData(List<DietDataDAO> listData) {
        this.listData = listData;
    }

    public DietPlanDB getDietDB() {
        return dietDB;
    }

    public CustomerData getCustomer() {
        return customer;
    }

    public List<DietDataDAO> getListData() {
        return listData;
    }

    public Exporter<DataTable> getTextExporter() {
        return textExporter;
    }

    public void setTextExporter(Exporter<DataTable> textExporter) {
        this.textExporter = textExporter;
    }

}
