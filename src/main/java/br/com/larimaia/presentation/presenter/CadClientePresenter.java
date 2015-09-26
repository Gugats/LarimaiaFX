/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.larimaia.presentation.presenter;

import br.com.larimaia.business.bo.ClienteBO;
import br.com.larimaia.business.model.Cliente;
import br.com.larimaia.presentation.view.CadClienteView;
import br.com.larimaia.presentation.view.CadClienteView.CadClienteViewListener;
import br.com.larimaia.presentation.view.impl.CadClienteViewImpl;

/**
 *
 * @author Gustavo
 */
public class CadClientePresenter implements CadClienteViewListener {
    CadClienteView view = new CadClienteViewImpl();
    ClienteBO bo;
    
    public CadClientePresenter(){
        this.view.addListener(this);
        bo = new ClienteBO();
        
        this.view.populaListaClientes(bo.listAll());
    }
    public CadClienteView getView() {
	return view;
    }

    @Override
    public void salvar(Cliente c) {
        bo.saveOrUpdate(c);
        this.view.sucesso("Cliente salvo com sucesso!");
        this.view.populaListaClientes(bo.listAll());
    }

    @Override
    public void excluir(Cliente c) {
        bo.delete(c);
        this.view.sucesso("Cliente excluído com sucesso!");
        this.view.populaListaClientes(bo.listAll());
    }
}
