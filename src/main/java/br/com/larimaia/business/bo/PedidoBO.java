/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.larimaia.business.bo;

import br.com.larimaia.integration.jpa.PedidoDAOImpl;
import br.com.larimaia.business.model.Pedido;

/**
 *
 * @author Gustavo
 */
public class PedidoBO extends GenericBO<Pedido> {

    public PedidoBO() {
        dao = new PedidoDAOImpl();
    }
    
}
