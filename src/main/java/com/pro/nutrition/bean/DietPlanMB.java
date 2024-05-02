package com.pro.nutrition.bean;

import static com.pro.nutrition.util.Utils.addDetailMessage;

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
import com.pro.nutrition.repository.entity.db.DietPlanDB;
import jakarta.faces.view.ViewScoped;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Classe que controla o plano de dieta.
 * Esta classe gerencia as operações relacionadas à criação e manipulação do plano de dieta para os clientes.
 */
@Named
@ViewScoped
public class DietPlanMB implements Serializable {
    
    private static final Logger logger = Logger.getLogger(DietPlanMB.class.getName());

    @Inject
    private CustomerDB customerDB;

    @Inject
    private AlimentDB alimentDB;

    @Inject
    private DietPlanDB dietDB;

    private Integer id;
    private List<CustomerData> selectedCars;
    private CustomerData selectedCustomerData;
    private List<CustomerData> customers = new ArrayList();
    private List<Aliment> aliments;
    private List<Aliment> selectedAliments;
    private Set<Aliment> selectedAlimentsDB = new HashSet<>();

    private DietDataDAO dietDAO = new DietDataDAO();

    /**
     * Inicializa o modelo de dados do plano de dieta.
     * Este método é chamado após a construção da instância da classe.
     */
    @PostConstruct
    public void initDataModel() {
        logger.log(Level.INFO, "[initDataModel] Start");
        aliments = new ArrayList();
        customers = customerDB.findAll();
        aliments = alimentDB.findAll();
    }
    
    public void onAlimentSelection() {
        selectedAlimentsDB.addAll(selectedAliments);
        selectedAlimentsDB.retainAll(selectedAliments);
    }

    /**
     * Salva o plano de dieta.
     * Este método salva os alimentos selecionados, o nome da dieta e a descrição da refeição, além de adicionar uma mensagem de sucesso.
     */
    public void save() {
        DietDataDAO item = new DietDataDAO();
        
        List<Aliment> alimentsDAO = new ArrayList();
        alimentsDAO.addAll(selectedAlimentsDB);
        item.setCustomerData(selectedCustomerData);
        item.setAliments(alimentsDAO);
        item.setDietName(dietDAO.getDietName());
        item.setMealDescription(dietDAO.getMealDescription());
        item.setMealName(dietDAO.getMealName());
        dietDB.create(item); 
        addDetailMessage("Salvo!");
    }
    
    /**
     * Seleciona um cliente para o plano de dieta.
     * @param car O cliente selecionado.
     */
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

    public CustomerData getSelectedCustomerData() {
        return selectedCustomerData;
    }

    public void setSelectedCustomerData(CustomerData selectedCustomerData) {
        this.selectedCustomerData = selectedCustomerData;
    }

}
