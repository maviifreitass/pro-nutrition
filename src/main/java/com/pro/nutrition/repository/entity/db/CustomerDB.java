/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pro.nutrition.repository.entity.db;

import com.pro.nutrition.repository.entity.CustomerData;
import com.pro.nutrition.repository.util.PostgresWrapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maria
 */
@Named
@ApplicationScoped
public class CustomerDB implements Serializable {

    @Inject
    private PostgresWrapper pw;

    @Inject
    private EntityManager em;
    
    @Inject
    private UserDB userDB;
    
    @Inject
    private DietPlanDB dietPlanDB;

    public CustomerDB() {
    }

    /* @Override
    protected EntityManager getEntityManager() {
        return em;
    }*/
    /**
     *
     * @return
     */
    /*  @Override
    public List<CustomerData> findAll() {
        return super.findAll();
    }*/
 /*    @Override
    public CustomerData find(Long id) {
        return super.find(id);
    }*/
    public List<CustomerData> findAll() {
        pw.openPostgresConnection();
        List<CustomerData> customers = new ArrayList();
        try ( Connection connection = pw.getConnection()) {
            String sql
                    = "SELECT C.HEIGHT, C.WEIGHT, C.AGE, C.GENDER, C.GOAL, "
                    + "C.USER_ID, C.DIET_PLAN_ID "
                    + "FROM CUSTOMER_DATA C ";

            try (final Statement pwStatement = connection.createStatement()) {
                ResultSet result = pwStatement.executeQuery(sql);
                while (result.next()) {
                    CustomerData c = new CustomerData();
                    c.setHeight(result.getDouble("height"));
                    c.setWeight(result.getDouble("weight"));
                    c.setAge(result.getInt("age"));
                    c.setGender(result.getString("gender"));
                    c.setGoal(result.getString("goal"));
                    c.setUser(userDB.find(result.getLong("user_id"))); 
                    c.setDietPlan(dietPlanDB.find(result.getLong("diet_plan_id"))); 
                    customers.add(c);
                }
            } finally {
                pw.closeConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(customers);
        return customers;
    }

    public CustomerData find(Long id) {
        pw.openPostgresConnection();
        CustomerData c = new CustomerData();
        try ( Connection connection = pw.getConnection()) {
            String sql
                    = "SELECT * FROM CUSTOMER_dATA where id = " + id;

            try (final Statement pwStatement = connection.createStatement()) {
                ResultSet result = pwStatement.executeQuery(sql);
                while (result.next()) {
                    c.setHeight(result.getDouble("height"));
                    c.setWeight(result.getDouble("weight"));
                }
            } finally {
                pw.closeConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

}
