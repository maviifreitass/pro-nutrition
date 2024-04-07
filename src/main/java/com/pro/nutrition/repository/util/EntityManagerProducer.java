/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pro.nutrition.repository.util;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Classe responsável por produzir e fechar instâncias de EntityManager.
 * Esta classe é um bean CDI e é anotada com @ApplicationScoped para garantir
 * que seja criada apenas uma instância por aplicação.
 */
@ApplicationScoped
public class EntityManagerProducer {

    private final EntityManagerFactory factory;

    /**
     * Construtor que inicializa a fábrica de EntityManager, 
     * procurando pelo persistence.xml configurado no META-INF.
     */
    public EntityManagerProducer() {
        factory = Persistence.createEntityManagerFactory("pro-nutritionPU");
    }

    /**
     * Método responsável por criar uma instância de EntityManager.
     * Esta instância é anotada com @RequestScoped para garantir
     * que seja criada uma nova instância a cada requisição.
     *
     * @return Uma instância de EntityManager.
     */
    @Produces
    @RequestScoped
    public EntityManager createEntityManager() {
        return factory.createEntityManager();
    }

    /**
     * Método responsável por fechar uma instância de EntityManager.
     *
     * @param manager A instância de EntityManager a ser fechada.
     */
    public void closeEntityManager(@Disposes EntityManager manager) {
        manager.close();
    }
}
