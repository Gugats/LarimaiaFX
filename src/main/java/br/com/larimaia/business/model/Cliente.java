package br.com.larimaia.business.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="larimaia.cliente")
public class Cliente {

    IntegerProperty idCliente;
    StringProperty nome;
    StringProperty telefone;
    StringProperty email;
    
    public Cliente() {
        idCliente = new SimpleIntegerProperty();
        nome = new SimpleStringProperty();
        telefone = new SimpleStringProperty();
        email = new SimpleStringProperty();
    }
    
    //Getters e Setters para idCliente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcliente")
    public final Integer getIdCliente() {
        return idCliente.get();
    }

    public final void setIdCliente(Integer value) {
        idCliente.set(value);
    }

    public IntegerProperty idClienteProperty() {
        return idCliente;
    }

    //Getters e Setters para nome
    @Column(name = "nome")
    public final String getNome() {
        return nome.get();
    }

    public final void setNome(String value) {
        nome.set(value);
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    //Getters e Setters para telefone
    @Column(name = "telefone")
    public final String getTelefone() {
        return telefone.get();
    }

    public final void setTelefone(String value) {
        telefone.set(value);
    }

    public StringProperty telefoneProperty() {
        return telefone;
    }

    //Getters e Setters para email
    @Column(name = "email")
    public final String getEmail() {
        return email.get();
    }

    public final void setEmail(String value) {
        email.set(value);
    }

    public StringProperty emailProperty() {
        return email;
    }

    @Override
    public String toString() {
        return this.getNome();
    }
    
}
