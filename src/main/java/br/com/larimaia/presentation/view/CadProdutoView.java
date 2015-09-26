/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.larimaia.presentation.view;

import br.com.larimaia.business.model.Produto;
import java.util.Collection;

/**
 *
 * @author Gustavo
 */
public interface CadProdutoView {
    interface CadProdutoViewListener{
        void salvar(Produto p);
        void excluir(Produto p);
    }
    
    public void addListener(CadProdutoViewListener listener);
    
    void sucesso(String mensagem);
    
    void populaListaProdutos(Collection produtos);
}
