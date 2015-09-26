
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
@Table(name="larimaia.login")
public class Login {
    
    IntegerProperty id = new SimpleIntegerProperty();
    StringProperty username = new SimpleStringProperty();
    StringProperty senha = new SimpleStringProperty();
    
    //Getters e Setters para id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public final Integer getId() {
        return id.get();
    }

    public final void setId(Integer value) {
        id.set(value);
    }

    public IntegerProperty idProperty() {
        return id;
    }
    
    //Getters e Setters para username
    @Column(name = "username")
    public final String getUsername() {
        return username.get();
    }

    public final void setUsername(String value) {
        username.set(value);
    }

    public StringProperty usernameProperty() {
        return username;
    }
    
    //Getters e Setters para senha
    @Column(name = "senha")
    public final String getSenha() {
        return senha.get();
    }

    public final void setSenha(String value) {
        senha.set(value);
    }

    public StringProperty senhaProperty() {
        return senha;
    }
    
}

