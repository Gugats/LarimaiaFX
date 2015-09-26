/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.larimaia.presentation.presenter;

import br.com.larimaia.business.bo.ProdutoBO;
import br.com.larimaia.business.model.Produto;
import br.com.larimaia.presentation.view.CadProdutoView;
import br.com.larimaia.presentation.view.impl.CadProdutoViewImpl;

/**
 *
 * @author Gustavo
 */
public class CadProdutoPresenter implements CadProdutoView.CadProdutoViewListener{
    CadProdutoView view = new CadProdutoViewImpl();
    ProdutoBO bo;
 
    public CadProdutoPresenter(){
        this.view.addListener(this);
        bo = new ProdutoBO();
        
        this.view.populaListaProdutos(bo.listAll());
    }
    public CadProdutoView getView() {
	return view;
    }

    @Override
    public void salvar(Produto p) {
        bo.saveOrUpdate(p);
        this.view.sucesso("Produto salvo com sucesso");
        this.view.populaListaProdutos(bo.listAll());
    }

    @Override
    public void excluir(Produto p) {
        bo.delete(p);
        this.view.sucesso("Produto excluído com sucesso");
        this.view.populaListaProdutos(bo.listAll());
    }
}
