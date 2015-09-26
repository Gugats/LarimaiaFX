/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.larimaia.presentation.view.impl;

import br.com.larimaia.business.model.Produto;
import br.com.larimaia.presentation.presenter.CadClientePresenter;
import br.com.larimaia.presentation.presenter.CadPedidoPresenter;
import br.com.larimaia.presentation.view.CadProdutoView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.converter.NumberStringConverter;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Gustavo
 */
public class CadProdutoViewImpl extends VBox implements CadProdutoView{

    List<CadProdutoViewListener> listeners = new ArrayList<CadProdutoView.CadProdutoViewListener>();
    
    //Elementos do Formulario
    private Text titulo;
    private HBox hDescricao, hValor, hBotoes;
    private Label lDescricao, lValor;
    private TextField tfDescricao, tfValor;
    private VBox formLayout;
    private Button salvar, cancelar, excluir;
    private Produto produto = new Produto();

    //Elementos da tabela
    private TableView<Produto> tabela;
    private VBox tabelaLayout;
    private ObservableList<Produto> listaProdutos;
    
    @Override
    public void addListener(CadProdutoViewListener listener) {
        listeners.add(listener);
    }
    
    public CadProdutoViewImpl(){
        
        this.setSpacing(10);
        formLayout = new VBox();
        formLayout.setSpacing(10);

        titulo = new Text("Cadastro de Produtos");
        titulo.setId("welcome-text");

        lDescricao = new Label("Descrição: ");
        tfDescricao = new TextField();
        hDescricao = new HBox();
        hDescricao.setAlignment(Pos.TOP_CENTER);
        hDescricao.setSpacing(7);
        hDescricao.getChildren().addAll(lDescricao, tfDescricao);

        lValor = new Label("Valor: ");
        tfValor = new TextField();
        hValor = new HBox();
        hValor.setAlignment(Pos.TOP_CENTER);
        hValor.setSpacing(7);
        hValor.getChildren().addAll(lValor, tfValor);

        hBotoes = new HBox();
        hBotoes.setAlignment(Pos.TOP_CENTER);
        hBotoes.setSpacing(7);
        salvar = new Button("Salvar");
        cancelar = new Button("Cancelar");
        excluir = new Button("Excluir");
        hBotoes.getChildren().addAll(salvar, cancelar, excluir);

        formLayout.getChildren().addAll(titulo, hDescricao, hValor, hBotoes);
        formLayout.setAlignment(Pos.TOP_CENTER);
        
        tabela = new TableView();
        tabela.setMaxWidth(200);

        TableColumn descricao = new TableColumn("Descrição");
        descricao.setMinWidth(100);
        descricao.setCellValueFactory(
                new PropertyValueFactory<Produto, StringProperty>("descricao")
        );

        TableColumn valor = new TableColumn("Produto");
        valor.setMinWidth(100);
        valor.setCellValueFactory(
                new PropertyValueFactory<Produto, StringProperty>("valor")
        );

        tabela.getColumns().addAll(descricao, valor);
        
        BooleanBinding bb = new BooleanBinding() {
            {
                super.bind(tfDescricao.textProperty(),
                        tfValor.textProperty());
            }

            @Override
            protected boolean computeValue() {
                return (tfDescricao.getText().isEmpty()
                        || tfValor.getText().isEmpty());
            }
        };
        salvar.disableProperty().bind(bb);

        tabelaLayout = new VBox();
        tabelaLayout.setSpacing(10);
        tabelaLayout.getChildren().add(tabela);
        tabelaLayout.setAlignment(Pos.CENTER);
        
        this.getChildren().addAll(formLayout, tabelaLayout);
        
        tabela.getFocusModel().focusedItemProperty().addListener(new ChangeListener<Produto>() {
            
            @Override
            public void changed(ObservableValue<? extends Produto> observable, Produto oldValue, Produto newValue) {
                if (oldValue != null) {
                    tfDescricao.textProperty().unbindBidirectional(oldValue.descricaoProperty());
                    tfValor.textProperty().unbindBidirectional(oldValue.valorProperty());
                }

                if (newValue != null) {
                    tfDescricao.textProperty().bindBidirectional(newValue.descricaoProperty());
                    tfValor.textProperty().bindBidirectional(newValue.valorProperty(), new NumberStringConverter());
                }
            }

        });
        
        tabela.setEditable(false);
        
        excluir.setDisable(true);
        
        tabela.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                produto = tabela.getSelectionModel().getSelectedItem();
                excluir.setDisable(false);
            }
        });
        
        salvar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                produto.setDescricao(tfDescricao.getText());
                produto.setValor(Double.parseDouble(tfValor.getText()));

                for (CadProdutoViewListener l : listeners) {
                    l.salvar(produto);
                }
                tabela.getItems().removeAll();
                excluir.setDisable(true);
            }
        });
        
        cancelar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                tabela.getSelectionModel().select(null);
                tfDescricao.setText("");
                tfValor.setText("");
                produto = new Produto();
                excluir.setDisable(true);
            }
        });
        
        excluir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                
                for (CadProdutoViewListener l : listeners) {
                    l.excluir(produto);
                }
                
                tabela.getSelectionModel().select(null);
                tfDescricao.setText("");
                tfValor.setText("");
                produto = new Produto();
                excluir.setDisable(true);
            }
        });
        
        HBox navegacaoH = new HBox();
        navegacaoH.setSpacing(10);
        
        Button botCli = new Button("Gerenciar Clientes");
        botCli.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                getScene().setRoot((Parent)new CadClientePresenter().getView());
            }
        });
        
        Button botPed = new Button("Realizar Pedido");
        botPed.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                getScene().setRoot((Parent)new CadPedidoPresenter().getView());
            }
        });
        
        Button botLogout = new Button("Sair");
        botLogout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                getScene().setRoot((Parent)new HomePageViewImpl());
            }
        });
        
        navegacaoH.getChildren().addAll(botCli, botPed, botLogout);
        navegacaoH.setAlignment(Pos.TOP_CENTER);
        
        this.getChildren().add(navegacaoH);
        
    }

    @Override
    public void sucesso(String mensagem) {
        Notifications.create().title("Sucesso").position(Pos.CENTER).text(mensagem).showInformation();
    }

    @Override
    public void populaListaProdutos(Collection produtos) {
        listaProdutos = FXCollections.observableArrayList(produtos);
        tabela.setItems(listaProdutos);
    }
    
}
