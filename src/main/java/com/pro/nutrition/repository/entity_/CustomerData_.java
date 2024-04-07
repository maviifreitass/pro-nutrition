package com.pro.nutrition.repository.entity;

import com.pro.nutrition.repository.entity.DietPlan;
import com.pro.nutrition.repository.entity.User;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@Generated(value="EclipseLink-3.0.0.v20201208-r3986bdbeae8e0e04e5be4a7076f2bda2ee1a09a5", date="2024-04-06T00:54:52")
@StaticMetamodel(CustomerData.class)
public class CustomerData_ { 

    public static volatile SingularAttribute<CustomerData, String> goal;
    public static volatile SingularAttribute<CustomerData, String> gender;
    public static volatile SingularAttribute<CustomerData, Date> createTime;
    public static volatile SingularAttribute<CustomerData, Date> deleteTime;
    public static volatile SingularAttribute<CustomerData, DietPlan> dietPlan;
    public static volatile SingularAttribute<CustomerData, String> name;
    public static volatile SingularAttribute<CustomerData, Double> weight;
    public static volatile SingularAttribute<CustomerData, Date> updateTime;
    public static volatile SingularAttribute<CustomerData, Long> id;
    public static volatile SingularAttribute<CustomerData, User> user;
    public static volatile SingularAttribute<CustomerData, Integer> age;
    public static volatile SingularAttribute<CustomerData, Double> height;

}