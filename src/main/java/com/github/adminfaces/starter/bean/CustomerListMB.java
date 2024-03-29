package com.github.adminfaces.starter.bean;

import com.github.adminfaces.template.exception.BusinessException;
import org.omnifaces.cdi.ViewScoped;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

import com.github.adminfaces.starter.infra.model.Filter;
import com.github.adminfaces.starter.model.Car;
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
    CarService carService;

    @Inject
    CustomerDB customerDB;

    Integer id;

//    LazyDataModel<Car> cars;
    Filter<CustomerData> filter = new Filter<>(new CustomerData());

    List<CustomerData> selectedCars; //cars selected in checkbox column

    List<CustomerData> cars = new ArrayList();

    List<Car> filteredValue;// datatable filteredValue attribute (column filters)

    @PostConstruct
    public void initDataModel() {

        System.out.println("[initDataModel] start");

        cars = customerDB.findAll();
        /*{

            @Override
            public int count(Map<String, FilterMeta> map) {
                return (int) carService.count(filter);
            }

            @Override
            public List<Car> load(int first, int pageSize, Map<String, SortMeta> sortMap, Map<String, FilterMeta> filterMap) {
                if (has(sortMap)) {
                    sortMap.entrySet().stream()
                            .findAny()
                            .ifPresent(sortField -> {
                                        filter.setSortField(sortField.getKey());
                                    }
                            );
                }
                filter.setFirst(first).setPageSize(pageSize);
                if (has(filterMap)) {
                    filter.setParams(filterMap.entrySet().stream()
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
                    );
                }
                return carService.paginate(filter);
            }


            @Override
            public Car getRowData(String key) {
                return carService.findById(Integer.valueOf(key));
            }

            @Override
            public String getRowKey(Car car) {
                return car.getId().toString();
            }
        };*/
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

    public List<Car> getFilteredValue() {
        return filteredValue;
    }

    public void setFilteredValue(List<Car> filteredValue) {
        this.filteredValue = filteredValue;
    }

    public void setSelectedCars(List<CustomerData> selectedCars) {
        this.selectedCars = selectedCars;
    }

    public List<CustomerData> getCars() {
        return cars;
    }

    public void setCars(List<CustomerData> cars) {
        this.cars = cars;
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
}
