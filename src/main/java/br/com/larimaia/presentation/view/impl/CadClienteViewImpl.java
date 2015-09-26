/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.larimaia.presentation.view.impl;

import br.com.larimaia.business.model.Cliente;
import br.com.larimaia.presentation.presenter.CadPedidoPresenter;
import br.com.larimaia.presentation.presenter.CadProdutoPresenter;
import br.com.larimaia.presentation.view.CadClienteView;
import java.util.Collection;
import javafx.scene.layout.VBox;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Gustavo
 */
public class CadClienteViewImpl extends VBox implements CadClienteView {

    List<CadClienteViewListener> listeners = new ArrayList<CadClienteView.CadClienteViewListener>();

    //Elementos do Formulario
    private Text titulo;
    private HBox hNome, hTelefone, hEmail, hBotoes;
    private Label lNome, lTelefone, lEmail;
    private TextField tfNome, tfTelefone, tfEmail;
    private VBox formLayout;
    private Button salvar, cancelar, excluir;
    private Cliente cliente = new Cliente();

    //Elementos da tabela
    private TableView<Cliente> tabela;
    private VBox tabelaLayout;
    private ObservableList<Cliente> listaClientes;

    @Override
    public void addListener(CadClienteViewListener listener) {
        listeners.add(listener);
    }

    public CadClienteViewImpl() {
        this.setSpacing(10);
        formLayout = new VBox();
        formLayout.setSpacing(10);

        titulo = new Text("Cadastro de Clientes");
        titulo.setId("welcome-text");

        lNome = new Label("Nome: ");
        tfNome = new TextField();
        hNome = new HBox();
        hNome.setAlignment(Pos.TOP_CENTER);
        hNome.setSpacing(7);
        hNome.getChildren().addAll(lNome, tfNome);

        lTelefone = new Label("Telefone: ");
        tfTelefone = new TextField();
        hTelefone = new HBox();
        hTelefone.setAlignment(Pos.TOP_CENTER);
        hTelefone.setSpacing(7);
        hTelefone.getChildren().addAll(lTelefone, tfTelefone);

        lEmail = new Label("E-mail: ");
//        Image image = new Image(getClass().getResourceAsStream("/br/com/larimaia/util/mail.png"));
//        lEmail.setGraphic(new ImageView(image));
        tfEmail = new TextField();
        hEmail = new HBox();
        hEmail.setAlignment(Pos.TOP_CENTER);
        hEmail.setSpacing(7);
        hEmail.getChildren().addAll(lEmail, tfEmail);

        hBotoes = new HBox();
        hBotoes.setAlignment(Pos.TOP_CENTER);
        hBotoes.setSpacing(7);
        salvar = new Button("Salvar");
        cancelar = new Button("Cancelar");
        excluir = new Button("Excluir");
        hBotoes.getChildren().addAll(salvar, cancelar, excluir);

        formLayout.getChildren().addAll(titulo, hNome, hTelefone, hEmail, hBotoes);
        formLayout.setAlignment(Pos.TOP_CENTER);

        BooleanBinding bb = new BooleanBinding() {
            {
                super.bind(tfEmail.textProperty(),
                        tfNome.textProperty(),
                        tfTelefone.textProperty());
            }

            @Override
            protected boolean computeValue() {
                return (tfEmail.getText().isEmpty()
                        || tfNome.getText().isEmpty()
                        || tfTelefone.getText().isEmpty());
            }
        };
        salvar.disableProperty().bind(bb);

        tabela = new TableView();
        tabela.setMaxWidth(300);

        TableColumn nome = new TableColumn("Nome");
        nome.setMinWidth(100);
        nome.setCellValueFactory(
                new PropertyValueFactory<Cliente, StringProperty>("nome")
        );

        TableColumn email = new TableColumn("E-mail");
        email.setMinWidth(100);
        email.setCellValueFactory(
                new PropertyValueFactory<Cliente, StringProperty>("email")
        );

        TableColumn telefone = new TableColumn("Telefone");
        telefone.setMinWidth(100);
        telefone.setCellValueFactory(
                new PropertyValueFactory<Cliente, StringProperty>("telefone")
        );
        tabela.getColumns().addAll(nome, telefone, email);

        tabelaLayout = new VBox();
        tabelaLayout.setSpacing(10);
        tabelaLayout.getChildren().add(tabela);
        tabelaLayout.setAlignment(Pos.CENTER);

//        tfNome.textProperty().bind(Bindings.selectString(tabela.getSelectionModel().selectedItemProperty(), "nome"));
//        tfEmail.textProperty().bind(Bindings.selectString(tabela.getSelectionModel().selectedItemProperty(), "email"));
//        tfTelefone.textProperty().bind(Bindings.selectString(tabela.getSelectionModel().selectedItemProperty(), "telefone"));
        tabela.getFocusModel().focusedItemProperty().addListener(new ChangeListener<Cliente>() {
            @Override
            public void changed(ObservableValue<? extends Cliente> observable, Cliente oldValue, Cliente newValue) {
                if (oldValue != null) {
                    tfNome.textProperty().unbindBidirectional(oldValue.nomeProperty());
                    tfTelefone.textProperty().unbindBidirectional(oldValue.telefoneProperty());
                    tfEmail.textProperty().unbindBidirectional(oldValue.emailProperty());
                }

                if (newValue != null) {
                    tfNome.textProperty().bindBidirectional(newValue.nomeProperty());
                    tfTelefone.textProperty().bindBidirectional(newValue.telefoneProperty());
                    tfEmail.textProperty().bindBidirectional(newValue.emailProperty());
                }
            }
        });

        this.getChildren().addAll(formLayout, tabelaLayout);

        excluir.setDisable(true);
        
        tabela.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                cliente = tabela.getSelectionModel().getSelectedItem();
                excluir.setDisable(false);
            }
        });

        salvar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                cliente.setNome(tfNome.getText());
                cliente.setTelefone(tfTelefone.getText());
                cliente.setEmail(tfEmail.getText());

                for (CadClienteViewListener l : listeners) {
                    l.salvar(cliente);
                }
                tabela.getItems().removeAll();
                excluir.setDisable(true);
            }
        });

        cancelar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                tabela.getSelectionModel().select(null);
                tfNome.setText("");
                tfEmail.setText("");
                tfTelefone.setText("");
                cliente = new Cliente();
                excluir.setDisable(true);
            }
        });
        
        excluir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                for (CadClienteViewListener l : listeners) {
                    l.excluir(cliente);
                }

                tabela.getSelectionModel().select(null);
                tfNome.setText("");
                tfEmail.setText("");
                tfTelefone.setText("");
                cliente = new Cliente();
                excluir.setDisable(true);
            }
        });
        
        HBox navegacaoH = new HBox();
        navegacaoH.setSpacing(10);
        
        Button botPro = new Button("Gerenciar Produtos");
        botPro.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                getScene().setRoot((Parent)new CadProdutoPresenter().getView());
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
        
        navegacaoH.getChildren().addAll(botPro, botPed, botLogout);
        navegacaoH.setAlignment(Pos.TOP_CENTER);
        
        this.getChildren().add(navegacaoH);
        
    }

    @Override
    public void sucesso(String mensagem) {
        Notifications.create().title("Sucesso").position(Pos.CENTER).text(mensagem).showInformation();
    }

    @Override
    public void populaListaClientes(Collection clientes) {
        listaClientes = FXCollections.observableArrayList(clientes);
        tabela.setItems(listaClientes);
    }
}
