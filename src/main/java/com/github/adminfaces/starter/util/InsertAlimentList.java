/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.adminfaces.starter.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pro.nutrition.repository.util.PostgresWrapper;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author maria
 */
public class InsertAlimentList {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, ParseException {
        List<String> aliments = new ArrayList();
        /* aliments.add("Apple");
        aliments.add("Banana");
        aliments.add("Orange");
        aliments.add("Strawberry");
        aliments.add("Blueberry");
        aliments.add("Grape");
        aliments.add("Watermelon");
        aliments.add("Pineapple");
        aliments.add("Mango");
        aliments.add("Peach");
        aliments.add("Pear");
        aliments.add("Cherry");
        aliments.add("Raspberry");
        aliments.add("Blackberry");
        aliments.add("Lemon");
        aliments.add("Lime");
        aliments.add("Avocado");
        aliments.add("Tomato");
        aliments.add("Cucumber");
        aliments.add("Carrot");
        aliments.add("Potato");
        aliments.add("Onion");
        aliments.add("Garlic");
        aliments.add("Broccoli");
        aliments.add("Cauliflower");
        aliments.add("Spinach");
        aliments.add("Lettuce");
        aliments.add("Kale");
        aliments.add("Celery");
        aliments.add("Eggplant");
        aliments.add("Zucchini");
        aliments.add("Pumpkin");
        aliments.add("Corn");
        aliments.add("Peas");
        aliments.add("Beans");
        aliments.add("Lentils");
        aliments.add("Chickpeas");
        aliments.add("Rice");
        aliments.add("Pasta");
        aliments.add("Bread");
        aliments.add("Oats");
        aliments.add("Quinoa");
        aliments.add("Barley");
        aliments.add("Couscous");
        aliments.add("Almonds");
        aliments.add("Walnuts");
        aliments.add("Cashews");
        aliments.add("Peanuts");
        aliments.add("Avocado");
        aliments.add("Quinoa");
        aliments.add("Kale");
        aliments.add("Acai");
        aliments.add("Dragonfruit");
        aliments.add("Chia");
        aliments.add("Saffron");
        aliments.add("Tofu");
        aliments.add("Mangosteen");
        aliments.add("Seitan");
        aliments.add("Kombucha");
        aliments.add("Spirulina");
        aliments.add("Jicama");
        aliments.add("Guava");
        aliments.add("Kohlrabi");
        aliments.add("Yuzu");
        aliments.add("Elderberry");
        aliments.add("Papaya");
        aliments.add("Lychee");
        aliments.add("Arugula");*/

        String sql
                = "INSERT INTO aliment "
                + "(id, name, calories, fat_total, fat_saturated, protein, sodium, potassium, "
                + "cholesterol, carbohydrates, fiber, sugar) "
                + "VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        String url = "https://api.api-ninjas.com/v1/nutrition?query=";
        int j = 1;
        for (int i = 0; i < aliments.size(); i++) {
            PostgresWrapper pw = new PostgresWrapper();
            pw.openPostgresConnection();
            HttpGet httpget = new HttpGet(url + aliments.get(i));
            httpget.setHeader("X-Api-Key", "QpryDjaLeUc6okBwzdGH8A==kQ4xhPPtLNlLmGT5");
            HttpClient client = HttpClientBuilder.create().build();
            HttpResponse response = client.execute(httpget);
            String result = EntityUtils.toString(response.getEntity());
            System.out.println("#######################\n");
            System.out.println("RESULT " + i + ": " + result);
            if (result == null || result.equals("")) {
                continue;
            }
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(result);

                try ( Connection connection = pw.getConnection()) {
                    for (JsonNode node : rootNode) {
                        try ( PreparedStatement statement = connection.prepareStatement(sql)) {
                            statement.setInt(1, j);
                            statement.setString(2, node.get("name").asText());
                            statement.setDouble(3, node.get("calories").asDouble());
                            statement.setDouble(4, node.get("fat_total_g").asDouble());
                            statement.setDouble(5, node.get("fat_saturated_g").asDouble());
                            statement.setDouble(6, node.get("protein_g").asDouble());
                            statement.setDouble(7, node.get("sodium_mg").asDouble());
                            statement.setDouble(8, node.get("potassium_mg").asDouble());
                            statement.setDouble(9, node.get("cholesterol_mg").asDouble());
                            statement.setDouble(10, node.get("carbohydrates_total_g").asDouble());
                            statement.setDouble(11, node.get("fiber_g").asDouble());
                            statement.setDouble(12, node.get("sugar_g").asDouble());
                            statement.executeUpdate();
                        } finally {
                            pw.closeConnection();
                        }
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            j++;
        }
    }
}
