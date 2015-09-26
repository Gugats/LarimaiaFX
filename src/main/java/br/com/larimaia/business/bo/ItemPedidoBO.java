/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.larimaia.business.bo;

import br.com.larimaia.business.model.ItemPedido;
import br.com.larimaia.integration.dao.ItemPedidoDAO;
import br.com.larimaia.integration.jpa.ItemPedidoDAOImpl;
import java.util.List;

/**
 *
 * @author Gustavo
 */
public class ItemPedidoBO extends GenericBO<ItemPedido>{

    ItemPedidoDAO idao = new ItemPedidoDAOImpl();
    
    public ItemPedidoBO() {
        dao = new ItemPedidoDAOImpl();
    }

    public List<ItemPedido> buscarPorIdPedido(int id) {
        return idao.buscarPorIdPedido(id);
    }
    
}
