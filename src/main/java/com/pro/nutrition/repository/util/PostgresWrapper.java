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
 * Esta classe é responsável por fornecer uma conexão com um banco de dados PostgreSQL.
 * Ela utiliza o JDBC para estabelecer a conexão.
 */
@Named
@ApplicationScoped
public class PostgresWrapper {

    protected Connection connection;

    /**
     * Método para abrir uma conexão com o banco de dados PostgreSQL.
     *
     * @return true se a conexão for aberta com sucesso, false caso contrário.
     */
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

    /**
     * Retorna a conexão com o banco de dados PostgreSQL.
     *
     * @return A conexão com o banco de dados.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Método para fechar a conexão com o banco de dados PostgreSQL.
     */
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(PostgresWrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
