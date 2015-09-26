package br.com.larimaia.business.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="larimaia.itempedido")
public class ItemPedido {
    
    IntegerProperty idItemPedido, quantidade;
    Produto produto;
    Pedido pedido;
    DoubleProperty valor;

    public ItemPedido() {
        idItemPedido = new SimpleIntegerProperty();
        quantidade = new SimpleIntegerProperty();
        produto = new Produto();
        pedido = new Pedido();
        valor = new SimpleDoubleProperty();
    }
    
    //Getters e Setters para produto
    @OneToOne
    @JoinColumn(name="idproduto")
    public Produto getProduto() { return produto;}
    public void setProduto(Produto produto) {this.produto = produto;}
    
    //Getters e Setters para pedido
    @ManyToOne
    @JoinColumn(name = "idpedido")
    public Pedido getPedido() { return pedido;}
    public void setPedido(Pedido pedido) {this.pedido = pedido;}
    
    //Getters e Setters para valor
    @Column(name = "valor")
    public final Double getValor(){return valor.get();}
    public final void setValor(Double value){valor.set(value);}
    public DoubleProperty valorProperty() {return valor;}
    
    //Getters e Setters para idTipoEvento
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iditempedido")
    public final Integer getIdItemPedido(){return idItemPedido.get();}
    public final void setIdItemPedido(Integer value){idItemPedido.set(value);}
    public IntegerProperty idItemPedidoProperty() {return idItemPedido;}
    
    //Getters e Setters para quantidade
    @Column(name = "quantidade")
    public final Integer getQuantidade(){return quantidade.get();}
    public final void setQuantidade(Integer value){quantidade.set(value);}
    public IntegerProperty quantidadeProperty() {return quantidade;}
    
}