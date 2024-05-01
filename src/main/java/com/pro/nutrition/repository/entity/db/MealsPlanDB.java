/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pro.nutrition.repository.entity.db;

import com.pro.nutrition.repository.entity.MealsPlan;
import com.pro.nutrition.repository.util.PostgresWrapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author maria
 */
@Named
@ApplicationScoped
public class MealsPlanDB implements Serializable {

    public void create(MealsPlan meals) {
        String sql
                = "INSERT INTO meals_plan "
                + "(diet_plan_id, meals_id) "
                + "VALUES (?,?);";
        long dietPlanIdLong = meals.getDietPlan().getId();
        int dietPlanId = (int) dietPlanIdLong;
        long mealsIdLong = meals.getMeals().getId();
        int mealsId = (int) mealsIdLong;
        
        PostgresWrapper pw = new PostgresWrapper();
        pw.openPostgresConnection();
        try {
            try ( Connection connection = pw.getConnection()) {
                try ( PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setInt(1, dietPlanId);
                    statement.setInt(2, mealsId);
                    statement.executeUpdate();
                } finally {
                    pw.closeConnection();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
