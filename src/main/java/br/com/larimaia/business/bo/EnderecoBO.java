/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.larimaia.business.bo;

import br.com.larimaia.business.model.Endereco;
import br.com.larimaia.integration.jpa.EnderecoDAOImpl;

/**
 *
 * @author Gustavo
 */
public class EnderecoBO extends GenericBO<Endereco> {

    public EnderecoBO() {
        dao = new EnderecoDAOImpl();
    }
    
}
