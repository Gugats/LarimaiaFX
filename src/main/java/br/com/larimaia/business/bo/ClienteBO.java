
package br.com.larimaia.business.bo;

import br.com.larimaia.business.model.Cliente;
import br.com.larimaia.integration.jpa.ClienteDAOImpl;

public class ClienteBO extends GenericBO<Cliente> {

    public ClienteBO() {
        dao = new ClienteDAOImpl();
    }
    
}
