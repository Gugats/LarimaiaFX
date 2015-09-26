/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.larimaia.business.bo;

import br.com.larimaia.business.model.TipoEvento;
import br.com.larimaia.integration.jpa.TipoEventoDAOImpl;

/**
 *
 * @author Gustavo
 */
public class TipoEventoBO extends GenericBO<TipoEvento> {

    public TipoEventoBO() {
        dao = new TipoEventoDAOImpl();
    }
    
}
