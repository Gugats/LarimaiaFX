
package br.com.larimaia.business.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
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
@Table(name="larimaia.produto")
public class Produto {
    
    IntegerProperty idProduto = new SimpleIntegerProperty();
    StringProperty descricao = new SimpleStringProperty();
    DoubleProperty valor = new SimpleDoubleProperty();
    
    //Getters e Setters para idProduto
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproduto")
    public final Integer getIdProduto(){return idProduto.get();}
    public final void setIdProduto(Integer value){idProduto.set(value);}
    public IntegerProperty idProdutoProperty() {return idProduto;}
    
    //Getters e Setters para descricao
    @Column(name = "descricao")
    public final String getDescricao(){return descricao.get();}
    public final void setDescricao(String value){descricao.set(value);}
    public StringProperty descricaoProperty() {return descricao;}
    
    //Getters e Setters para valor
    @Column(name = "valor")
    public final Double getValor(){return valor.get();}
    public final void setValor(Double value){valor.set(value);}
    public DoubleProperty valorProperty() {return valor;}
    
    @Override
    public String toString() {
        return this.getDescricao();
    }
    
}