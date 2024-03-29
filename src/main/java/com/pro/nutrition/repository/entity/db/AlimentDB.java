/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pro.nutrition.repository.entity.db;

import com.pro.nutrition.repository.entity.Aliment;
import com.pro.nutrition.repository.util.PostgresWrapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author maria
 */
@Named
@ApplicationScoped
public class AlimentDB {

    @Inject
    private PostgresWrapper pw;

    @Inject
    private EntityManager em;

    public void create(Aliment aliment) {
        pw.openPostgresConnection();
        try ( Connection connection = pw.getConnection()) {
            String sql
                    = "INSERT INTO aliment "
                    + "(name, calories, fat_total, fat_saturated, protein, sodium, potassium, "
                    + "cholesterol, carbohydrates, fiber, sugar) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            try ( PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, aliment.getName());
                statement.setDouble(2, aliment.getCalories());
                statement.setDouble(3, aliment.getFatTotal());
                statement.setDouble(4, aliment.getFatSatured());
                statement.setDouble(5, aliment.getProtein());
                statement.setDouble(6, aliment.getSodium());
                statement.setDouble(7, aliment.getPotassium());
                statement.setDouble(8, aliment.getCholesterol());
                statement.setDouble(9, aliment.getCarbohydrates());
                statement.setDouble(10, aliment.getFiber());
                statement.setDouble(11, aliment.getSugar());
                statement.executeUpdate();
            } finally {
                pw.closeConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getUser() {

    }
}
