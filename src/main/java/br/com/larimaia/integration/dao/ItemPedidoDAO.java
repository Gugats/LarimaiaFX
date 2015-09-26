/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.larimaia.integration.dao;

import br.com.larimaia.business.model.ItemPedido;
import java.util.List;

/**
 *
 * @author Gustavo
 */
public interface ItemPedidoDAO extends GenericDAO<ItemPedido> {
    
    List<ItemPedido> buscarPorIdPedido(int id);
    
}
