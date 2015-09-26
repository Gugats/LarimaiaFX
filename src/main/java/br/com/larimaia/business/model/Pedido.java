package br.com.larimaia.business.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="larimaia.pedido")
public class Pedido {
    
    IntegerProperty idPedido;
    Cliente cliente;
    Endereco endereco;
    List<ItemPedido> listaItemPedido;
    TipoEvento tipoEvento;
    StringProperty origemPedido, indicacao, cerimonial, observacao;
    Date dataPedido, dataHoraEvento;
    DoubleProperty valor;

    public Pedido() {
        endereco = new Endereco();
        cliente = new Cliente();
        idPedido = new SimpleIntegerProperty();
        origemPedido = new SimpleStringProperty();
        indicacao = new SimpleStringProperty();
        cerimonial = new SimpleStringProperty();
        observacao = new SimpleStringProperty();
        dataPedido = new Date();
        dataHoraEvento = new Date();
        valor = new SimpleDoubleProperty();
    }
    
    //Getters e Setters para idPedido
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpedido")
    public final Integer getIdPedido(){return idPedido.get();}
    public final void setIdPedido(Integer value){idPedido.set(value);}
    public IntegerProperty idPedidoProperty() {return idPedido;}
    
    //Getters e Setters para origemPedido
    @Column(name = "origempedido")
    public final String getOrigemPedido(){return origemPedido.get();}
    public final void setOrigemPedido(String value){origemPedido.set(value);}
    public StringProperty origemPedidoProperty() {return origemPedido;}
    
    //Getters e Setters para indicacao
    @Column(name = "indicacao")
    public final String getIndicacao(){return indicacao.get();}
    public final void setIndicacao(String value){indicacao.set(value);}
    public StringProperty indicacaoProperty() {return indicacao;}
    
    //Getters e Setters para cerimonial
    @Column(name = "cerimonial")
    public final String getCerimonial(){return cerimonial.get();}
    public final void setCerimonial(String value){cerimonial.set(value);}
    public StringProperty cerimonialProperty() {return cerimonial;}
    
    //Getters e Setters para observacao
    @Column(name = "obs")
    public final String getObservacao(){return observacao.get();}
    public final void setObservacao(String value){observacao.set(value);}
    public StringProperty observacaoProperty() {return observacao;}
    
    //Getters e Setters para dataPedido
    @Column(name = "datapedido")
    @Temporal(TemporalType.DATE)
    public Date getDataPedido() {return dataPedido;}
    public void setDataPedido(Date value) {dataPedido=value;}
    
    //Getters e Setters para dataHoraEvento
    @Column(name = "datahoraevento")
    @Temporal(TemporalType.DATE)
    public Date getDataHoraEvento() {return dataHoraEvento;}
    public void setDataHoraEvento(Date value) {dataHoraEvento= value;}
    
    //Getters e Setters para valor
    @Column(name = "valor")
    public double getValor() {return valor.get();}
    public void setValor(double value) {valor.set(value);}
    public DoubleProperty valorProperty() {return valor;}

    //Getters e Setters para cliente
    @ManyToOne
    @JoinColumn(name="idcliente")
    public Cliente getCliente() { return cliente;}
    public void setCliente(Cliente cliente) {this.cliente = cliente;}
    
    //Getters e Setters para tipoEvento
    @ManyToOne
    @JoinColumn(name="idtipoevento")
    public TipoEvento getTipoEvento() { return tipoEvento;}
    public void setTipoEvento(TipoEvento tipoEvento) {this.tipoEvento = tipoEvento;}
    
    //Getters e Setters para endereco
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="idendereco")
    public Endereco getEndereco() { return endereco;}
    public void setEndereco(Endereco endereco) {this.endereco = endereco;}
    
    //Getters e Setters para listaItemPedido
    @OneToMany(mappedBy="pedido", targetEntity = ItemPedido.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<ItemPedido> getListaItemPedido() { return listaItemPedido;}
    public void setListaItemPedido(List<ItemPedido> listaItemPedido) {this.listaItemPedido = listaItemPedido;}
    
    public void addItemPedido(ItemPedido ip){
        
        if(this.listaItemPedido==null){
            this.listaItemPedido = new ArrayList<>();
        }
        this.listaItemPedido.add(ip);
    }
}