/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.larimaia.presentation.view;

import br.com.larimaia.business.model.Cliente;
import br.com.larimaia.business.model.Endereco;
import br.com.larimaia.business.model.ItemPedido;
import br.com.larimaia.business.model.Pedido;
import br.com.larimaia.business.model.Produto;
import br.com.larimaia.business.model.TipoEvento;
import java.util.List;

/**
 *
 * @author Gustavo
 */
public interface CadPedidoView {
    interface CadPedidoViewListener{
        void salvarPedido(Pedido p);
//        void salvarEndereco(Endereco e, Pedido p);
//        void salvarItemPedido(ItemPedido ip);
        List<ItemPedido> listaItemPedido(int id);
        void excluir(Pedido p);
    }
    
    public void addListener(CadPedidoViewListener listener);
    
    void populaComboCliente(List<Cliente> lista);
    
    void populaComboProduto(List<Produto> lista);
    
    void populaComboTipoEvento(List<TipoEvento> lista);
    
    void sucesso(String msg);
    
    void populaTabela(List<ItemPedido> lista);
    
    void populaListaPedidos(List<Pedido> lista);
    
    void resetForm();
}
