/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.larimaia.presentation.view.impl;

import br.com.larimaia.business.bo.LoginBO;
import br.com.larimaia.business.model.Login;
import br.com.larimaia.presentation.presenter.CadClientePresenter;
import br.com.larimaia.presentation.presenter.CadPedidoPresenter;
import br.com.larimaia.presentation.presenter.CadProdutoPresenter;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import org.controlsfx.control.Notifications;

public class HomePageViewImpl extends BorderPane {
    
    CadClientePresenter cadClientePresenter;
    CadProdutoPresenter cadProdutoPresenter;
    CadPedidoPresenter cadPedidoPresenter;

    private Button botCli, botPro, botPed;
    private HBox layout;
    private Label lUser, lPwd;
    private GridPane gridCenter;
    private TextField user;
    private PasswordField pwd;
    private Button botaoLogin, botaoLogout;
    private LoginBO bo = new LoginBO();
    private MenuBar barraMenu;
    private List<Login> lista;
    
    public HomePageViewImpl(){
        
        lista = new ArrayList<>(bo.listAll());
        
        layout = new HBox();
        layout.setPadding(new Insets(15, 12, 15, 12));
        layout.setStyle("-fx-background-color: #336699;");
        layout.setSpacing(10); 
        
        this.setTop(layout);
        this.isResizable();
        
        gridCenter = getLoginLayout();
        
        this.setCenter(gridCenter);

        cadClientePresenter = new CadClientePresenter();
        cadProdutoPresenter = new CadProdutoPresenter();
        cadPedidoPresenter = new CadPedidoPresenter();
        
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setDisable(true);
        
        botCli = new Button("Gerenciar Clientes");
        botCli.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                getScene().setRoot((Parent)new CadClientePresenter().getView());
            }
        });

        botPro = new Button("Gerenciar Produtos");
        botPro.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                getScene().setRoot((Parent)new CadProdutoPresenter().getView());
            }
        });

        botPed = new Button("Realizar Pedido");
        botPed.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                getScene().setRoot((Parent)new CadPedidoPresenter().getView());
            }
        });

        layout.getChildren().addAll(botCli, botPed, botPro);
        
        botaoLogout = new Button("Logout");
        botaoLogout.setUnderline(true);
        botaoLogout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                layout.setDisable(true);
                gridCenter.getChildren().clear();
                gridCenter.getChildren().add(getLoginLayout());
            }
        });
    }
    
    public GridPane getLoginLayout(){
        GridPane gridLogin = new GridPane();
        gridLogin.setHgap(7);
        gridLogin.setVgap(7);
        gridLogin.setPadding(new Insets(25, 25, 25, 25));
        
        gridLogin.setAlignment(Pos.CENTER);
        
        Text infoTexto = new Text("Login");
        infoTexto.setId("welcome-text");
        gridLogin.add(infoTexto, 0, 0, 2, 1);
        
        lUser = new Label("Usuário: ");
        lPwd = new Label("Senha: ");
        user = new TextField();
        pwd = new PasswordField();
        botaoLogin = new Button("OK");
        Button cadastre = new Button("Cadastre-se Aqui!");
        gridLogin.add(lUser, 0, 1);
        gridLogin.add(user, 1, 1);
        gridLogin.add(lPwd, 0, 2);
        gridLogin.add(pwd, 1, 2);
        gridLogin.add(botaoLogin, 0, 3);
        gridLogin.add(cadastre, 1, 3);
        
        cadastre.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                gridCenter.getChildren().clear();
                gridCenter.getChildren().add(getCadastroLayout());
            }
        });
        
        botaoLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                for(Login l : lista){
                    if(l.getUsername().equals(user.getText())){
                        if(l.getSenha().equals(pwd.getText())){
                            Notifications.create().title("Sucesso").position(Pos.CENTER).text("Usuário autenticado com sucesso!").showInformation();
                            layout.setDisable(false);
                            gridCenter.getChildren().clear();
                            gridCenter.getChildren().add(getBoasVindasLayout(l.getUsername()));
                            return;
                        }
                    }
                }
                Notifications.create().title("Falha").position(Pos.CENTER).text("Não foi possível logar! Verifique Login e Senha!").showError();
            }
        });
        
        return gridLogin;
    }

    public GridPane getBoasVindasLayout(String user){
        GridPane gridBoasVindas = new GridPane();
        gridBoasVindas.setHgap(7);
        gridBoasVindas.setVgap(7);
        gridBoasVindas.setPadding(new Insets(25, 25, 25, 25));
        
        gridBoasVindas.setAlignment(Pos.CENTER);
        
        Text infoTexto = new Text("Seja bem vindo, "+user);
        infoTexto.setId("welcome-text");
        gridBoasVindas.add(infoTexto, 0, 0, 2, 1);
        
        gridBoasVindas.add(botaoLogout, 0, 1);
        
        return gridBoasVindas;
    }
    
    public GridPane getCadastroLayout(){
        GridPane grid = new GridPane();
        grid.setHgap(7);
        grid.setVgap(7);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        grid.setAlignment(Pos.CENTER);
        
        Text infoTexto = new Text("Cadastro!");
        infoTexto.setId("welcome-text");
        grid.add(infoTexto, 0, 0, 2, 1);
        
        Label lUser = new Label("Escolha seu nome de Usuário: ");
        Label lPwd = new Label("Escolha sua Senha: ");
        final TextField userName = new TextField();
        final PasswordField pwd = new PasswordField();
        Button salvar = new Button("Cadastrar");
        Button voltar = new Button("Voltar");
        
        grid.add(lUser, 0, 1);
        grid.add(userName, 1, 1);
        grid.add(lPwd, 0, 2);
        grid.add(pwd, 1, 2);
        grid.add(salvar, 0, 3);
        grid.add(voltar, 1, 3);
        
        salvar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                Login login = new Login();
                login.setUsername(userName.getText());
                login.setSenha(pwd.getText());
                
                if(login.getUsername().isEmpty() || login.getSenha().isEmpty()){
                    Notifications.create().title("Falha").position(Pos.CENTER).text("Campos usuário e senha obrigatórios!").showWarning();
                }else{
                    try{
                    bo.saveOrUpdate(login);
                    }catch(RuntimeException e){
                        Notifications.create().title("Falha").position(Pos.CENTER).text("Não foi possível realizar o cadastro").showError();
                    }

                    Notifications.create().title("Sucesso").position(Pos.CENTER).text("Usuário cadastrado com sucesso!").showInformation();
                    lista.clear();
                    lista.addAll(bo.listAll());
                    gridCenter.getChildren().clear();
                    gridCenter.getChildren().add(getLoginLayout());
                }
            }
        });
        
        voltar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                gridCenter.getChildren().clear();
                gridCenter.getChildren().add(getLoginLayout());
            }
        });
        
        return grid;
    }
    
}
