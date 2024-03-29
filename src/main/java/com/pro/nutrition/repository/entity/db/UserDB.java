/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pro.nutrition.repository.entity.db;

import com.pro.nutrition.repository.entity.User;
import com.pro.nutrition.repository.util.PostgresWrapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author maria
 */
@Named
@ApplicationScoped
public class UserDB {

    @Inject
    private PostgresWrapper pw;
    
    @Inject
    private EntityManager em;

    public void create(User user) {
        pw.openPostgresConnection();
        try ( Connection connection = pw.getConnection()) {
            LocalDateTime currentDateTime = LocalDateTime.now();

            // Convertendo para Timestamp
            Timestamp currentTimestamp = Timestamp.valueOf(currentDateTime);
            
            String sql =
            "INSERT INTO users (role_id, username, email, password, create_time) "
            + "VALUES (?, ?, ?, ?, ?)";
            try ( PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, 1);
                statement.setString(2, user.getUsername());
                statement.setString(3, user.getEmail());
                statement.setString(4, user.getPassword());
                statement.setTimestamp(5, currentTimestamp);
                statement.executeUpdate();
            } finally {
                pw.closeConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
      public User find(Long id) {
        pw.openPostgresConnection();
        User u = new User();
        try ( Connection connection = pw.getConnection()) {
            String sql
                    = "SELECT username FROM users where id = " + id;

            try (final Statement pwStatement = connection.createStatement()) {
                ResultSet result = pwStatement.executeQuery(sql);
                while (result.next()) {
                    u.setUsername(result.getString("username"));
                }
            } finally {
                pw.closeConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }
    
    public void getUser(){
     
    }
}
