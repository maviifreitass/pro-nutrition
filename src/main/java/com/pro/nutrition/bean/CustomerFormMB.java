package com.pro.nutrition.bean;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.omnifaces.cdi.ViewScoped;
import jakarta.annotation.ManagedBean;

import java.io.Serializable;

import static com.pro.nutrition.util.Utils.addDetailMessage;
import com.pro.nutrition.repository.entity.CustomerData;
import com.pro.nutrition.repository.entity.db.CustomerDB;

/**
 * Classe para controlar o formulário de clientes.
 * Esta classe gerencia as operações relacionadas à entrada de dados do cliente.
 */
@Named
@ViewScoped
@ManagedBean
public class CustomerFormMB implements Serializable {

    private Integer id;
    private CustomerData customer;

    @Inject
    CustomerDB customerDB;

    /**
     * Inicializa o formulário do cliente criando uma nova instância de CustomerData.
     */
    public void init() {
        customer = new CustomerData();
    }
    /**
     * Obtém o ID do cliente.
     * @return O ID do cliente.
     */
    public Integer getId() {
        return id;
    }
    /**
     * Define o ID do cliente.
     * @param id O ID do cliente a ser definido.
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * Salva os dados do cliente no banco de dados.
     * Após a operação de salvar, uma mensagem de sucesso é adicionada.
     */
    public void save() {
        customerDB.save(customer); 
        addDetailMessage("Salvo!");
    }
    /**
     * Obtém os dados do cliente.
     * @return Os dados do cliente.
     */
    public CustomerData getCustomer() {
        return customer;
    }
    /**
     * Define os dados do cliente.
     * @param customer Os dados do cliente a serem definidos.
     */
    public void setCustomer(CustomerData customer) {
        this.customer = customer;
    }

}
