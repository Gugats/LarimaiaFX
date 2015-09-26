/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.larimaia.integration.jpa;

import br.com.larimaia.business.model.ItemPedido;
import br.com.larimaia.integration.dao.ItemPedidoDAO;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Gustavo
 */
public class ItemPedidoDAOImpl extends GenericDAOImpl<ItemPedido> implements ItemPedidoDAO{

    @Override
    public List<ItemPedido> buscarPorIdPedido(int id) {
        Query q = em.createQuery("FROM ItemPedido ip WHERE ip.pedido = :id");
        q.setParameter("id" , id);
        return q.getResultList();
    }
    
}
