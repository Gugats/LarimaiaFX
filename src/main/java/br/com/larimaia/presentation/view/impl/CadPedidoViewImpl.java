/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.larimaia.presentation.view.impl;

import br.com.larimaia.business.model.Cliente;
import br.com.larimaia.business.model.Endereco;
import br.com.larimaia.business.model.ItemPedido;
import br.com.larimaia.business.model.Pedido;
import br.com.larimaia.business.model.Produto;
import br.com.larimaia.business.model.TipoEvento;
import br.com.larimaia.presentation.presenter.CadClientePresenter;
import br.com.larimaia.presentation.presenter.CadProdutoPresenter;
import br.com.larimaia.presentation.view.CadPedidoView;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.controlsfx.control.Notifications;
/**
 *
 * @author Gustavo
 */
public class CadPedidoViewImpl extends VBox implements CadPedidoView {
    
    List<CadPedidoViewListener> listeners = new ArrayList<CadPedidoViewListener>();

    //Elementos do Formulario
    private Text titulo;
    private Label lCliente, lDataPedido, lOrigemPedido, lCerimonial, lIndicacao, lObs, lDataEvento, lTipoEvento;
    private Label lRua, lNumero, lBairro, lCidade, lCep, lEstado;
    private Label lQtd, lProduto, lValorUnitario, lValorTotal, lvu, lvt;
    private TextField tfOrigemPedido, tfCerimonial, tfIndicacao, tfObs;
    private TextField tfRua, tfNumero, tfBairro, tfCidade, tfCep, tfEstado, tfQtd;
    private HBox formLayout, hTitulo, botoesCarrinho;
    private VBox vCarrinho;
    private Button salvar, cancelar, excluir, adicionar, remover;
    private DatePicker dataPedido, dataEvento;
    private ComboBox<Cliente> clienteCombo;
    private ComboBox<Produto> produtoCombo;
    private ComboBox<TipoEvento> tipoEventoCombo;
    private ObservableList<Cliente> listaClientes;
    private ObservableList<Pedido> listaPedidos;
    private ObservableList<Produto> listaProdutos;
    private ObservableList<TipoEvento> listaTipoEvento;
    private ObservableList<ItemPedido> listaItemPedido = FXCollections.observableArrayList();
    private Set<ItemPedido> itExcluir;
    private GridPane infoLayout, enderecoLayout, itensPedidosLayout;
    private TableView<ItemPedido> tabelaCarrinho;
    private TableView<Pedido> tabelaPedido;
    private Pedido pedido = new Pedido();

    public CadPedidoViewImpl() {

        this.setSpacing(10);

        hTitulo = new HBox();
        hTitulo.setAlignment(Pos.TOP_CENTER);
        titulo = new Text("Realizar Pedido");
        titulo.setId("welcome-text");
        hTitulo.getChildren().add(titulo);

        formLayout = new HBox();
        formLayout.setSpacing(10);

        infoLayout = new GridPane();
        infoLayout.setHgap(7);
        infoLayout.setVgap(7);
        infoLayout.setPadding(new Insets(25, 25, 25, 25));

        Text infoTexto = new Text("Informações");
        infoTexto.setId("sub-titulo");
        infoLayout.add(infoTexto, 0, 0, 2, 1);

        lCliente = new Label("Cliente: ");
        clienteCombo = new ComboBox<>();
        clienteCombo.getItems().addAll();
        infoLayout.add(lCliente, 0, 1);
        infoLayout.add(clienteCombo, 1, 1);

        dataPedido = new DatePicker();
//        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        dateFormat.format(null);
//        Date date = new Date();
//        dateFormat.format(date);
        lDataPedido = new Label("Data Pedido: ");
        infoLayout.add(lDataPedido, 0, 2);
        infoLayout.add(dataPedido, 1, 2);

        dataEvento = new DatePicker();
        lDataEvento = new Label("Data Evento: ");
        infoLayout.add(lDataEvento, 0, 3);
        infoLayout.add(dataEvento, 1, 3);

        lOrigemPedido = new Label("Origem do Pedido: ");
        tfOrigemPedido = new TextField();
        infoLayout.add(lOrigemPedido, 0, 4);
        infoLayout.add(tfOrigemPedido, 1, 4);

        lCerimonial = new Label("Cerimonial: ");
        tfCerimonial = new TextField();
        infoLayout.add(lCerimonial, 0, 5);
        infoLayout.add(tfCerimonial, 1, 5);

        lIndicacao = new Label("Indicação: ");
        tfIndicacao = new TextField();
        infoLayout.add(lIndicacao, 0, 6);
        infoLayout.add(tfIndicacao, 1, 6);

        lObs = new Label("Observação: ");
        tfObs = new TextField();
        infoLayout.add(lObs, 0, 7);
        infoLayout.add(tfObs, 1, 7);

        lTipoEvento = new Label("Tipo de Evento: ");
        tipoEventoCombo = new ComboBox<>();
        infoLayout.add(lTipoEvento, 0, 8);
        infoLayout.add(tipoEventoCombo, 1, 8);

        //=================================================================================
        enderecoLayout = new GridPane();
        enderecoLayout.setAlignment(Pos.TOP_CENTER);
        enderecoLayout.setHgap(7);
        enderecoLayout.setVgap(7);
        enderecoLayout.setPadding(new Insets(25, 25, 25, 25));

        Text enderecoTexto = new Text("Endereço de Entrega");
        enderecoTexto.setId("sub-titulo");
        enderecoLayout.add(enderecoTexto, 0, 0, 2, 1);

        lEstado = new Label("Estado: ");
        tfEstado = new TextField();
        enderecoLayout.add(lEstado, 0, 1);
        enderecoLayout.add(tfEstado, 1, 1);

        lCidade = new Label("Cidade");
        tfCidade = new TextField();
        enderecoLayout.add(lCidade, 0, 2);
        enderecoLayout.add(tfCidade, 1, 2);

        lCep = new Label("CEP: ");
        tfCep = new TextField();
        enderecoLayout.add(lCep, 0, 3);
        enderecoLayout.add(tfCep, 1, 3);

        lBairro = new Label("Bairro: ");
        tfBairro = new TextField();
        enderecoLayout.add(lBairro, 0, 4);
        enderecoLayout.add(tfBairro, 1, 4);

        lRua = new Label("Rua: ");
        tfRua = new TextField();
        enderecoLayout.add(lRua, 0, 5);
        enderecoLayout.add(tfRua, 1, 5);

        lNumero = new Label("Número: ");
        tfNumero = new TextField();
        enderecoLayout.add(lNumero, 0, 6);
        enderecoLayout.add(tfNumero, 1, 6);

        //==========================================================================
        itensPedidosLayout = new GridPane();
        itensPedidosLayout.setAlignment(Pos.TOP_RIGHT);
        itensPedidosLayout.setHgap(7);
        itensPedidosLayout.setVgap(7);
        itensPedidosLayout.setPadding(new Insets(25, 25, 25, 25));

        Text itensPedidosTexto = new Text("Carrinho");
        itensPedidosTexto.setId("sub-titulo");
        itensPedidosLayout.add(itensPedidosTexto, 0, 0, 2, 1);

        lProduto = new Label("Produtos Disponiveis: ");
        produtoCombo = new ComboBox<>();
        itensPedidosLayout.add(lProduto, 0, 1);
        itensPedidosLayout.add(produtoCombo, 1, 1);

        lValorUnitario = new Label("Valor Unitário: ");
        lvu = new Label("");
        itensPedidosLayout.add(lValorUnitario, 0, 2);
        itensPedidosLayout.add(lvu, 1, 2);

        lQtd = new Label("Quantidade: ");
        tfQtd = new TextField();
        itensPedidosLayout.add(lQtd, 0, 3);
        itensPedidosLayout.add(tfQtd, 1, 3);

        lValorTotal = new Label("Valor Total: ");
        lvt = new Label("");
        itensPedidosLayout.add(lValorTotal, 0, 6);
        itensPedidosLayout.add(lvt, 1, 6);

        lvu.textProperty().bind(Bindings.selectString(produtoCombo.getSelectionModel().selectedItemProperty(), "valor"));

        tfQtd.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                DoubleProperty x = new SimpleDoubleProperty(Double.parseDouble(lvu.getText()));
                DoubleProperty y = new SimpleDoubleProperty();

                if (tfQtd.getText().isEmpty()) {
                    lvt.setText("0.00");
                } else {
                    y.bind(x.multiply(Double.parseDouble(tfQtd.getText())));
                    lvt.setText(y.getValue().toString());
                }

            }
        });

        produtoCombo.valueProperty().addListener(new ChangeListener<Produto>() {
            @Override
            public void changed(ObservableValue<? extends Produto> ov, Produto t, Produto t1) {
                tfQtd.setText("0");
            }
        });

        //============================================================================
        tabelaCarrinho = new TableView<>();
        tabelaCarrinho.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tabelaCarrinho.setMaxHeight(220);

        TableColumn descricao = new TableColumn("Descrição");
        descricao.setMinWidth(100);
        descricao.setCellValueFactory(
                new PropertyValueFactory<ItemPedido, StringProperty>("produto")
        );

        TableColumn qtd = new TableColumn("Quantidade");
        qtd.setMinWidth(50);
        qtd.setCellValueFactory(
                new PropertyValueFactory<ItemPedido, IntegerProperty>("quantidade")
        );

        TableColumn valorTotal = new TableColumn("Valor Total");
        valorTotal.setMinWidth(50);
        valorTotal.setCellValueFactory(
                new PropertyValueFactory<ItemPedido, IntegerProperty>("valor")
        );

        tabelaCarrinho.getColumns().addAll(descricao, qtd, valorTotal);

        //=============================================================================
        vCarrinho = new VBox();
        vCarrinho.setSpacing(7);

        botoesCarrinho = new HBox();
        botoesCarrinho.setSpacing(7);

        adicionar = new Button("Adicionar");
        remover = new Button("Remover");

        adicionar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {

                ItemPedido ip = new ItemPedido();
                ip.setQuantidade(Integer.parseInt(tfQtd.getText()));
                ip.setValor(Double.parseDouble(lvt.getText()));
                ip.setProduto(produtoCombo.getValue());

                listaItemPedido.add(ip);
                tabelaCarrinho.getItems().clear();
                populaTabela(listaItemPedido);
            }
        });

        remover.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                itExcluir = new HashSet(tabelaCarrinho.getSelectionModel().getSelectedItems());
                listaItemPedido.removeAll(itExcluir);
                tabelaCarrinho.getItems().clear();
                populaTabela(listaItemPedido);
            }
        });

        botoesCarrinho.getChildren().addAll(adicionar, remover);

        vCarrinho.getChildren().addAll(tabelaCarrinho, botoesCarrinho);

        formLayout.getChildren().addAll(infoLayout, enderecoLayout, itensPedidosLayout, vCarrinho);

        //===============================================================================
        HBox hBotoes = new HBox();
        hBotoes.setSpacing(10);
        hBotoes.setAlignment(Pos.TOP_CENTER);

        tabelaPedido = new TableView();

        tabelaPedido.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                listaItemPedido.clear();
                
                pedido = tabelaPedido.getSelectionModel().getSelectedItem();
                tfCerimonial.setText(pedido.getCerimonial());
                tfOrigemPedido.setText(pedido.getOrigemPedido());
                clienteCombo.setValue(pedido.getCliente());
                
                Date data = pedido.getDataHoraEvento();
                Instant instant = Instant.ofEpochMilli(data.getTime());
                LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
                dataEvento.setValue(localDate);
                
                Date data2 = pedido.getDataPedido();
                Instant instant2 = Instant.ofEpochMilli(data2.getTime());
                LocalDate localDate2 = LocalDateTime.ofInstant(instant2, ZoneId.systemDefault()).toLocalDate();
                dataPedido.setValue(localDate2);
                
                
                
                tfIndicacao.setText(pedido.getIndicacao());
                tfObs.setText(pedido.getObservacao());
                tipoEventoCombo.setValue(pedido.getTipoEvento());
                tabelaCarrinho.getItems().clear();
//                List<ItemPedido> lista = new ArrayList<>();
//                for (CadPedidoViewListener l : listeners) {
//                    lista = l.listaItemPedido(pedido.getIdPedido());
//                }
                
                populaTabela(pedido.getListaItemPedido());
                tfEstado.setText(pedido.getEndereco().getEstado());
                tfCidade.setText(pedido.getEndereco().getCidade());
                tfCep.setText(pedido.getEndereco().getCep());
                tfBairro.setText(pedido.getEndereco().getBairro());
                tfRua.setText(pedido.getEndereco().getRua());
                tfNumero.setText(pedido.getEndereco().getNumero().toString());
                excluir.setDisable(false);
            }
        });

        salvar = new Button("Salvar Pedido"); 
        salvar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {

                pedido.setCerimonial(tfCerimonial.getText());
                pedido.setOrigemPedido(tfOrigemPedido.getText());
                pedido.setCliente(clienteCombo.getValue());
                LocalDate ld1 = dataEvento.getValue();
                Date date1 = Date.from(ld1.atStartOfDay(ZoneId.systemDefault()).toInstant());
                pedido.setDataHoraEvento(date1);
                LocalDate ld2 = dataPedido.getValue();
                Date date2 = Date.from(ld2.atStartOfDay(ZoneId.systemDefault()).toInstant());
                pedido.setDataPedido(date2);
                pedido.setIndicacao(tfIndicacao.getText());
                
                for(ItemPedido ip : listaItemPedido){
                    ip.setPedido(pedido);
                    pedido.addItemPedido(ip);
                }
                
                pedido.setObservacao(tfObs.getText());
                pedido.setTipoEvento(tipoEventoCombo.getValue());

                double valor = 0;
                for (ItemPedido ip : listaItemPedido) {
                    valor += ip.getValor();
                }
                pedido.setValor(valor);

                Endereco endereco = new Endereco();
                endereco.setBairro(tfBairro.getText());
                endereco.setCep(tfCep.getText());
                endereco.setCidade(tfCidade.getText());
                endereco.setEstado(tfEstado.getText());
                endereco.setNumero(Integer.parseInt(tfNumero.getText()));
                endereco.setRua(tfRua.getText());

                pedido.setEndereco(endereco);

                for(CadPedidoViewListener l : listeners){
                    l.salvarPedido(pedido);
                }
                
                resetForm();

            }
        });

        excluir = new Button("Excluir Pedido");
        excluir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                for (CadPedidoViewListener l : listeners) {
                    l.excluir(pedido);
                }
                resetForm();
            }
        });

        cancelar = new Button("Cancelar");
        cancelar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                resetForm();
            }
        });
        hBotoes.getChildren().addAll(salvar, excluir, cancelar);

        //=======================================================================
        HBox bottomLayout = new HBox();
        bottomLayout.setSpacing(7);
        bottomLayout.setAlignment(Pos.CENTER);

        VBox layoutT = new VBox();
        layoutT.setSpacing(7);
        layoutT.setAlignment(Pos.TOP_CENTER);

        TableColumn id = new TableColumn("ID");
        id.setMinWidth(50);
        id.setCellValueFactory(
                new PropertyValueFactory<Pedido, IntegerProperty>("idPedido")
        );

        TableColumn cliente = new TableColumn("Cliente");
        cliente.setMinWidth(100);
        cliente.setCellValueFactory(
                new PropertyValueFactory<Pedido, StringProperty>("cliente")
        );

        TableColumn valor = new TableColumn("Valor Pedido");
        valor.setMinWidth(50);
        valor.setCellValueFactory(
                new PropertyValueFactory<Pedido, DoubleProperty>("valor")
        );

        tabelaPedido.setMaxWidth(200);
        tabelaPedido.getColumns().addAll(id, cliente, valor);

        VBox proLayout = new VBox();
        proLayout.setSpacing(7);
        Button botPro = new Button("Gerenciar Produtos");
        botPro.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                getScene().setRoot((Parent) new CadProdutoPresenter().getView());
            }
        });
        proLayout.getChildren().add(botPro);
        proLayout.setAlignment(Pos.CENTER);

        VBox cliLayout = new VBox();
        cliLayout.setSpacing(7);
        Button botCli = new Button("Gerenciar Clientes");
        botCli.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                getScene().setRoot((Parent) new CadClientePresenter().getView());
            }
        });
        cliLayout.getChildren().add(botCli);
        cliLayout.setAlignment(Pos.CENTER);

        layoutT.getChildren().addAll(tabelaPedido);

        bottomLayout.getChildren().addAll(proLayout, layoutT, cliLayout);

        this.getChildren().addAll(hTitulo, formLayout, hBotoes, bottomLayout);

    }

    @Override
    public void addListener(CadPedidoViewListener listener) {
        listeners.add(listener);
    }

    @Override
    public void populaComboCliente(List<Cliente> lista) {
        listaClientes = FXCollections.observableArrayList(lista);
        clienteCombo.setItems(listaClientes);
    }

    @Override
    public void sucesso(String msg) {
        Notifications.create().title("Sucesso").position(Pos.CENTER).text(msg).showInformation();
    }

    @Override
    public void populaComboProduto(List<Produto> lista) {
        listaProdutos = FXCollections.observableArrayList(lista);
        produtoCombo.setItems(listaProdutos);
    }

    @Override
    public void populaTabela(List<ItemPedido> lista) {
        listaItemPedido = FXCollections.observableArrayList(lista);
        tabelaCarrinho.getItems().addAll(listaItemPedido);
        //tabelaCarrinho.setItems(listaItemPedido);
    }

    @Override
    public void populaComboTipoEvento(List<TipoEvento> lista) {
        listaTipoEvento = FXCollections.observableArrayList(lista);
        tipoEventoCombo.setItems(listaTipoEvento);
    }

    @Override
    public void populaListaPedidos(List<Pedido> lista) {
        listaPedidos = FXCollections.observableArrayList(lista);
        tabelaPedido.setItems(listaPedidos);
    }

    @Override
    public void resetForm() {
        tfCerimonial.setText("");
        tfOrigemPedido.setText("");
        clienteCombo.setValue(null);
        dataEvento.setValue(null);
        dataPedido.setValue(null);
        tfIndicacao.setText("");
        tfObs.setText("");
        tipoEventoCombo.setValue(null);
        tfEstado.setText("");
        tfCidade.setText("");
        tfCep.setText("");
        tfBairro.setText("");
        tfRua.setText("");
        tfNumero.setText("");
        tabelaCarrinho.getItems().clear();
        listaItemPedido.clear();
        populaTabela(listaItemPedido);
        tabelaPedido.getItems().removeAll();
        pedido = new Pedido();
        produtoCombo.setValue(null);
        tfQtd.setText(null);
//        lvt.setText("");
//        lvu.setText("");

        tabelaPedido.getSelectionModel().select(null);
    }
    
}
