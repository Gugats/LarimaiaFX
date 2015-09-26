/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.larimaia.business.bo;

import br.com.larimaia.integration.jpa.LoginDAOImpl;
import br.com.larimaia.business.model.Login;

/**
 *
 * @author Gustavo
 */
public class LoginBO extends GenericBO<Login> {

    public LoginBO() {
        dao = new LoginDAOImpl();
    }
    
}
