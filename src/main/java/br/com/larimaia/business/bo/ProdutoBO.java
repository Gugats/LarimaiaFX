/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.larimaia.business.bo;

import br.com.larimaia.business.model.Produto;
import br.com.larimaia.integration.jpa.ProdutoDAOImpl;

/**
 *
 * @author Gustavo
 */
public class ProdutoBO extends GenericBO<Produto> {

    public ProdutoBO() {
        dao = new ProdutoDAOImpl();
    }
    
}
