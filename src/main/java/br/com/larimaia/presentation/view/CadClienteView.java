package br.com.larimaia.presentation.view;

import br.com.larimaia.business.model.Cliente;
import java.util.Collection;

public interface CadClienteView {
    
    interface CadClienteViewListener{
        void salvar(Cliente c);
        void excluir(Cliente c);
    }
    
    public void addListener(CadClienteViewListener listener);
    
    void sucesso(String mensagem);
    
    void populaListaClientes(Collection clientes);
}
