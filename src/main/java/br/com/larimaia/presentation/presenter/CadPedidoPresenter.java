/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.larimaia.presentation.presenter;

import br.com.larimaia.business.bo.ClienteBO;
import br.com.larimaia.business.bo.EnderecoBO;
import br.com.larimaia.business.bo.ItemPedidoBO;
import br.com.larimaia.business.bo.PedidoBO;
import br.com.larimaia.business.bo.ProdutoBO;
import br.com.larimaia.business.bo.TipoEventoBO;
import br.com.larimaia.business.model.Cliente;
import br.com.larimaia.business.model.Endereco;
import br.com.larimaia.business.model.ItemPedido;
import br.com.larimaia.business.model.Pedido;
import br.com.larimaia.business.model.Produto;
import br.com.larimaia.business.model.TipoEvento;
import br.com.larimaia.presentation.view.CadPedidoView;
import br.com.larimaia.presentation.view.impl.CadPedidoViewImpl;
import br.com.larimaia.presentation.view.CadPedidoView.CadPedidoViewListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gustavo
 */
public class CadPedidoPresenter implements CadPedidoViewListener{
    CadPedidoView view = new CadPedidoViewImpl();
    PedidoBO pedidoBO;
    TipoEventoBO tipoEventoBO;
    ClienteBO clienteBO;
    ProdutoBO produtoBO;
    EnderecoBO enderecoBO;
    ItemPedidoBO itemPedidoBO;

    public CadPedidoPresenter() {
        
        this.view.addListener(this);
        
        pedidoBO = new PedidoBO();
        tipoEventoBO = new TipoEventoBO();
        clienteBO = new ClienteBO();
        produtoBO = new ProdutoBO();
        enderecoBO = new EnderecoBO();
        itemPedidoBO = new ItemPedidoBO();
        
        this.view.populaListaPedidos(new ArrayList<Pedido>(pedidoBO.listAll()));
        this.view.populaComboCliente(new ArrayList<Cliente>(clienteBO.listAll()));
        this.view.populaComboProduto(new ArrayList<Produto>(produtoBO.listAll()));
        this.view.populaComboTipoEvento(new ArrayList<TipoEvento>(tipoEventoBO.listAll()));
        
    }
    
    public CadPedidoView getView() {
	return view;
    }

    @Override
    public void salvarPedido(Pedido p) {
       
        pedidoBO.saveOrUpdate(p);
        this.view.sucesso("Pedido realizado com sucesso");
        
        this.view.populaListaPedidos(new ArrayList<Pedido>(pedidoBO.listAll()));
    }

    @Override
    public List<ItemPedido> listaItemPedido(int id) {
        //return itemPedidoBO.buscarPorIdPedido(id);
        return itemPedidoBO.buscarPorIdPedido(id);
    }

    @Override
    public void excluir(Pedido p) {
        
        
        
        pedidoBO.delete(p);
        this.view.sucesso("Pedido excluido com sucesso");
        this.view.populaListaPedidos(new ArrayList<Pedido>(pedidoBO.listAll()));
    }
}
