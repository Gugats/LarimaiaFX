
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
@Table(name="larimaia.tipoevento")
public class TipoEvento {
    IntegerProperty idTipoEvento;
    StringProperty descricao;

    public TipoEvento() {
        idTipoEvento = new SimpleIntegerProperty();
        descricao = new SimpleStringProperty();
    }
    
    //Getters e Setters para idTipoEvento
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtipoevento")
    public final Integer getIdTipoEvento(){return idTipoEvento.get();}
    public final void setIdTipoEvento(Integer value){idTipoEvento.set(value);}
    public IntegerProperty idTipoEventoProperty() {return idTipoEvento;}

    //Getters e Setters para descricao
    @Column(name = "descricao")
    public final String getDescricao(){return descricao.get();}
    public final void setDescricao(String value){descricao.set(value);}
    public StringProperty descricaoProperty() {return descricao;}
    
    @Override
    public String toString() {
        return this.getDescricao();
    }
    
}