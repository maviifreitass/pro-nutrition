/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pro.nutrition.repository.util;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLRecoverableException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maria
 */
@Named
@ApplicationScoped
public class PostgresWrapper {

    protected Connection connection;

    public Boolean openPostgresConnection() {
        try {
            String url = "jdbc:postgresql://localhost:5432/pro-nutrition";
            connection = DriverManager.getConnection(url, "admin-nutrition", "admin");
        } catch (SQLRecoverableException ex) {
            Logger.getLogger(PostgresWrapper.class.getName()).log(Level.SEVERE, "Network connection error!", ex);
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(PostgresWrapper.class.getName()).log(Level.SEVERE, "Database access error!", ex);
            return false;
        }
        return true;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(PostgresWrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
