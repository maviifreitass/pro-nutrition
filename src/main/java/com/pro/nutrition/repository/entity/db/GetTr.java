/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pro.nutrition.repository.entity.db;

import com.pro.nutrition.repository.util.PostgresWrapper;
import jakarta.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author maria
 */
/*
Classe utilizada para teste com o banco de Dados.
*/
public class GetTr {
    
    @Inject
    PostgresWrapper pw;

    public void get() {
        PostgresWrapper pw = new PostgresWrapper();
        pw.openPostgresConnection();
        
        try (Connection connection = pw.getConnection()) {
            String sql = "INSERT INTO aliment (id, name) VALUES (?, ?)";
            try ( PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, 1);
                statement.setString(2, "ALIMENTO TESTE");
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void create() {
        pw.openPostgresConnection();
        
        try (Connection connection = pw.getConnection()) {
            String sql = "INSERT INTO aliment (id, name) VALUES (?, ?)";
            try ( PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, 1);
                statement.setString(2, "ALIMENTO TESTE");
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
