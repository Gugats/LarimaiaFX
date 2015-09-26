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
@Table(name="larimaia.enderecoentrega")
public class Endereco {
    
    StringProperty bairro, cidade, cep, estado, rua;
    IntegerProperty idEndereco, numero;

    public Endereco() {
        bairro = new SimpleStringProperty();
        cidade = new SimpleStringProperty();
        cep = new SimpleStringProperty();
        estado = new SimpleStringProperty();
        rua = new SimpleStringProperty();
        idEndereco = new SimpleIntegerProperty();
        numero = new SimpleIntegerProperty();
    }
 
    //Getters e Setters para idEndereco
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idendereco")
    public final Integer getIdEndereco(){return idEndereco.get();}
    public final void setIdEndereco(Integer value){idEndereco.set(value);}
    public IntegerProperty idEnderecoProperty() {return idEndereco;}
    
    //Getters e Setters para numero
    @Column(name = "numero")
    public final Integer getNumero(){return numero.get();}
    public final void setNumero(Integer value){numero.set(value);}
    public IntegerProperty numeroProperty() {return numero;}
    
    //Getters e Setters para bairro
    @Column(name = "bairro")
    public final String getBairro(){return bairro.get();}
    public final void setBairro(String value){bairro.set(value);}
    public StringProperty bairroProperty() {return bairro;}
 
    //Getters e Setters para cidade
    @Column(name = "cidade")
    public final String getCidade(){return cidade.get();}
    public final void setCidade(String value){cidade.set(value);}
    public StringProperty cidadeProperty() {return cidade;}
    
    //Getters e Setters para CEP
    @Column(name = "cep")
    public final String getCep(){return cep.get();}
    public final void setCep(String value){cep.set(value);}
    public StringProperty cepProperty() {return cep;}
    
     //Getters e Setters para estado
    @Column(name = "estado")
    public final String getEstado(){return estado.get();}
    public final void setEstado(String value){estado.set(value);}
    public StringProperty estadoProperty() {return estado;}
    
     //Getters e Setters para rua
    @Column(name = "rua")
    public final String getRua(){return rua.get();}
    public final void setRua(String value){rua.set(value);}
    public StringProperty ruaProperty() {return rua;}
    
}
