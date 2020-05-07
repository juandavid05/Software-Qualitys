/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zafirodesktop.controller;
import com.itextpdf.text.DocumentException;
import com.zafirodesktop.entity.Category;
import com.zafirodesktop.entity.Discount;
import com.zafirodesktop.entity.Inventory;
import com.zafirodesktop.entity.ItemCategory;
import com.zafirodesktop.entity.MinimumStock;
import com.zafirodesktop.entity.Person;
import com.zafirodesktop.entity.Product;
import com.zafirodesktop.entity.ProductDiscount;
import com.zafirodesktop.entity.ProductTaxes;
import com.zafirodesktop.entity.Quotation;
import com.zafirodesktop.entity.Remission;
import com.zafirodesktop.entity.Stock;
import com.zafirodesktop.entity.Tax;
import com.zafirodesktop.entity.Tranzaction;
import com.zafirodesktop.entity.Turn;
import com.zafirodesktop.entity.Users;
import com.zafirodesktop.model.AbstractFacade;
import com.zafirodesktop.model.CategoryModel;
import com.zafirodesktop.model.ProductModel;
import com.zafirodesktop.model.RemissionModel;
import com.zafirodesktop.model.ReportsModel;
import com.zafirodesktop.model.SessionBD;
import com.zafirodesktop.model.SettingsModel;
import com.zafirodesktop.util.ComboBoxChoices;
import com.zafirodesktop.util.EditingCell;
import com.zafirodesktop.util.EditingCellFloat;
import com.zafirodesktop.util.LogActions;
import com.zafirodesktop.util.PrintPDF;
import com.zafirodesktop.util.ProductConverter;
import java.awt.HeadlessException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
/**
 * FXML Controller class
 *
 * @author Digitall
 */
public class MainController implements Initializable {
    /*
     Especificación de los componentes asociados en el FXML
     */
    //Plantilla principal
    @FXML
    private AnchorPane mainPane;
    @FXML
    private AnchorPane contentPane;
    @FXML
    private AnchorPane welcomePane;
    @FXML
    private Pane maskPane;
    @FXML
    private GridPane chartsPane;
    @FXML
    public HBox messagesPanel;
    @FXML
    public HBox aboutButtonsPanel;
    @FXML
    public HBox leftChartReport;
    @FXML
    public HBox rigthChartReport;
    @FXML
    public VBox viewTitlePane;
    @FXML
    private Tab searchInvoiceTab;
    @FXML
    private Label user;
    @FXML
    private Label leftChart;
    @FXML
    private Label leftChart2;
    @FXML
    private Label centerChart;
    @FXML
    private Label centerChart2;
    @FXML
    private Label rigthChart;
    @FXML
    private Label rigthChart2;
    @FXML
    private Label contentTitle;
    @FXML
    private Label resultsDesc;
    @FXML
    private Label message;
    @FXML
    private ImageView imageContent;
    @FXML
    private Button logOutBT;
    @FXML
    private Button changeViewButton;
    @FXML
    private Button closeBoxBT;
    @FXML
    private Button newItemBT;
    @FXML
    private Button helpButton;
    @FXML
    private ComboBox<ComboBoxChoices> discountOpt;
    @FXML
    private Tooltip tlpHelp;
    @FXML
    private TextField searchTF;
    @FXML
    private TableView dataTable;
    @FXML
    private TableView invoiceDataTable;
    @FXML
    public Stage modal;
    @FXML
    private TableColumn fdtbFirstColumn;
    @FXML
    private TableColumn fdtbSecondColumn;
    @FXML
    private TableColumn fdtbThirdColumn;
    @FXML
    private TableColumn fdtbFourthColumn;
    @FXML
    private TableColumn dtbFirstColumn;
    @FXML
    private TableColumn dtbSecondColumn;
    @FXML
    private TableColumn dtbThirdColumn;
    @FXML
    private TableColumn dtbFourthColumn;
    @FXML
    private TableColumn dtbFifthColumn;
    //Inicio
    @FXML
    private Button home;
    //Configuración
    @FXML
    private TitledPane settingsPane;
    @FXML
    private Button settings;
    @FXML
    private Button backup;
    @FXML
    private Button users;
    @FXML
    private Button impor;
    @FXML
    private Button discount;
    //Inventario
    @FXML
    private TitledPane inventoryPane;
    @FXML
    private Button product;
    @FXML
    private Button productsBarcode;
    @FXML
    private Button category;
    @FXML
    private Button addInventory;
    @FXML
    private Button movement;
    @FXML
    private ComboBox<ComboBoxChoices> productCategories;
    @FXML
    private ComboBox<ComboBoxChoices> categories;
    //Serivicios
    @FXML
    private TitledPane servicesPane;
    @FXML
    private Button service;
    @FXML
    private Button serviceOrder;
    //Personas
    @FXML
    private TitledPane personsPane;
    @FXML
    private Button person;
    @FXML
    private Button credit;
    @FXML
    private Button creditOrder;
    //Compras
    @FXML
    private TitledPane buyPane;
    @FXML
    private Button order;
    @FXML
    private Button output;
    //Ventas
    @FXML
    private TitledPane invoicesPane;
    @FXML
    private Button invoice;
    @FXML
    private Button quotation;
    @FXML
    private Button tax;
    //Reportes
    @FXML
    private TitledPane reportsPane;
    @FXML
    private Button earningsReport;
    @FXML
    private Button invoicesReport;
    @FXML
    private Button ordersReport;
    @FXML
    private Button entriesReport;
    @FXML
    private Button expensesReport;
    @FXML
    private Button orderCreditsReport;
    @FXML
    private Button invalidatedInvoices;
    @FXML
    private Button closesBoxButton;
    //Vista de cajero
    @FXML
    public HBox listsViewPanel;
    @FXML
    public TabPane checkerTabs;
    @FXML
    private TextField invoiceSearchTF;
    @FXML
    private TextField quotationSearchTF;
    @FXML
    public TableView productDataTable;
    @FXML
    public TableView quotationDataTable;
    @FXML
    private TableColumn pdtbFirstColumn;
    @FXML
    private TableColumn pdtbSecondColumn;
    @FXML
    private TableColumn pdtbThirdColumn;
    @FXML
    private TableColumn pdtbFourthColumn;
    @FXML
    private TableColumn qdtbFirstColumn;
    @FXML
    private TableColumn qdtbSecondColumn;
    @FXML
    private TableColumn qdtbThirdColumn;
    @FXML
    private TableColumn tcProductTotalPrice;
    @FXML
    private TableColumn qdtbFourthColumn;
    @FXML
    private TextField getProduct;
    @FXML
    private TextField getPerson;
    @FXML
    private Label resultsDesc2;
    @FXML
    private Label resultsQuotation;
    @FXML
    private TextArea obs;
    @FXML
    private Button rmvProductButton;
    @FXML
    private Button paytype;
    @FXML
    private GridPane lblTotalsGrid;
    @FXML
    private ListView personList;
    @FXML
    private ListView productList;
    @FXML
    private Label lblTotal;
    @FXML
    private Label lblTotalTitle;
    @FXML
    private Label lblSubTotal;
    @FXML
    private Label lblSubTotalTitle;
    /*
     *Especificación variables del controlador
     */
    public String moduleID, moduleTitle;
    private AbstractFacade abs;
    private ResourceBundle bundle;
    private Turn sessionUser;
    private Person actualClient;
    private Discount invoiceDiscount;
    private Collection<Product> productsList;
    private double totalInvoice;
    ObservableList<ProductConverter> selectedProducts;
    private List<ProductConverter> productValuesBfTaxes;
    Collection<Tax> taxes;
    private Collection<Discount> discounts;
    BarChart<String, Number> bc;
    LineChart<String, Number> lineChart;
    private boolean useCashBox;
    public boolean isAdminView;
    private boolean isBDRestore;
    private DecimalFormat format;
    public Turn getSessionUser() {
        return sessionUser;
    }
    public void setActualClient(Person actualClient) {
        this.actualClient = actualClient;
        getPerson.setText(actualClient.toString());
    }
    public void setModuleID(String moduleID) {
        this.moduleID = moduleID;
    }
    public void setUseCashBox(boolean useCashBox) {
        this.useCashBox = useCashBox;
    }
    public boolean isUseCashBox() {
        return useCashBox;
    }
    public void setIsAdminView(boolean isAdminView) {
        this.isAdminView = isAdminView;
    }
    public void setIsBDRestore(boolean isBDRestore) {
        this.isBDRestore = isBDRestore;
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bundle = rb;
        contentTitle.setText(bundle.getString("contentTitleText"));
        Image img = new Image("/com/zafirodesktop/ui/img/ico/pointer_black.png");
        ImageView imgvw = new ImageView(img);
        imgvw.setFitHeight(15);
        imgvw.setFitWidth(15);
        tlpHelp.setGraphic(imgvw);
        searchTF.setPromptText(bundle.getString("searchTextfieldPlaceHolder"));
    }
    /*
     Método para inicializar los valores de acuerdo al tipo de usuario
     @param: Usuario que inica la sesión 
     */
    public void initializeValuesUser(Turn turn) {
        sessionUser = turn;
        int userType = sessionUser.getIdUserFk().getCreatedOn();
        setStringUsuario();
    }
    /*
     De acuerdo a la entrada, se muestran o no las opciones de caja
     */
    public void hideRemoveCloseBox() {
        if (!useCashBox || sessionUser.getIdUserFk().getCreatedOn() == 0) {
            aboutButtonsPanel.getChildren().remove(closeBoxBT);
            if (closesBoxButton != null) {
                closesBoxButton.setDisable(true);
            }
        }
    }
    /*
     De acuerdo al tipo de usuario se muestra o oculta la opción de cambio de vista
     */
    public void hideRemoveChangeView() {
        if (sessionUser.getIdUserFk().getCreatedOn() == 0) {
            viewTitlePane.setVisible(false);
        }
    }
    /*
     Método para inicializar los valores de acuerdo al tipo de usuario 
     */
    public void initializeValuesChecker() {
        //Se carga el cliente por defecto
        abs = new AbstractFacade();
        actualClient = (Person)abs.findByRealId("Person", 16000);
        selectedProducts = FXCollections.observableArrayList();
        productValuesBfTaxes = new ArrayList<>();
        taxes = new ArrayList();
        discounts = new ArrayList();
        getProduct.setPromptText(bundle.getString("lblInvoiceGetProduct"));
        getPerson.setPromptText(bundle.getString("lblInvoiceIdPerson"));
        getPerson.setText(actualClient.toString());
        obs.setPromptText(bundle.getString("lblInvoiceObs"));
        invoiceSearchTF.setPromptText(bundle.getString("searchTextfieldPlaceHolder"));
        quotationSearchTF.setPromptText(bundle.getString("searchTextfieldPlaceHolder"));
        format = new DecimalFormat("###,###.00");
        // Inicio parte Tabla de productos
        productDataTable.setPlaceholder(new Label(bundle.getString("lblInvoiceTableNoItems")));
        productDataTable.setEditable(
                true);
        Callback<TableColumn, TableCell> cellFactory
                = new Callback<TableColumn, TableCell>() {
                    @Override
                    public TableCell call(TableColumn p) {
                        return new EditingCell();
                    }
                };
        Callback<TableColumn, TableCell> cellFactoryFloat
                = new Callback<TableColumn, TableCell>() {
                    @Override
                    public TableCell call(TableColumn p) {
                        return new EditingCellFloat();
                    }
                };
        pdtbFirstColumn = new TableColumn(bundle.getString("productIdInvoiceDatatable"));
        pdtbFirstColumn.prefWidthProperty()
                .bind(productDataTable.widthProperty().divide(8));
        pdtbFirstColumn.setCellValueFactory(
                new PropertyValueFactory<ProductConverter, String>("skuProduct"));
        /*pdtbSecondColumn = new TableColumn(bundle.getString("productDescriptionInvoiceDatatable"));
         pdtbSecondColumn.prefWidthProperty()
         .bind(productDataTable.widthProperty().divide(2));
         pdtbSecondColumn.setCellValueFactory(
         new PropertyValueFactory<Product, String>("productDescription"));*/
        pdtbSecondColumn = new TableColumn(bundle.getString("productDescriptionInvoiceDatatable"));
        pdtbSecondColumn.prefWidthProperty()
                .bind(productDataTable.widthProperty().divide(2));
        pdtbSecondColumn.setCellValueFactory(
                new PropertyValueFactory<ProductConverter, String>("productDescription"));
        pdtbSecondColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        pdtbSecondColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<ProductConverter, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<ProductConverter, String> t) {
                        ((ProductConverter) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setProductDescription(t.getNewValue());
                    }
                }
        );
        pdtbThirdColumn = new TableColumn(bundle.getString("productPriceInvoiceDatatable"));
        pdtbThirdColumn.prefWidthProperty()
                .bind(productDataTable.widthProperty().divide(8));
        pdtbThirdColumn.setCellValueFactory(
                new PropertyValueFactory<ProductConverter, Integer>("actualPrice"));
        pdtbThirdColumn.setCellFactory(cellFactoryFloat);
        pdtbThirdColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<ProductConverter, Double>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<ProductConverter, Double> t
                    ) {
                        ProductConverter temp = (ProductConverter) t.getTableView().getItems().get(
                                t.getTablePosition().getRow());
                        double newValue = t.getNewValue();
                        //Se trae el producto con los valores reales
                        ProductConverter toUpdate = new ProductConverter();
                        for(ProductConverter cnvrt:productValuesBfTaxes){
                            if(temp.getIdProductPk().equals(cnvrt.getIdProductPk()))
                                toUpdate = cnvrt;
                        }
                        //Si tiene descuentos
                        if(!temp.getDiscountCollection().isEmpty()){
                            int index = productValuesBfTaxes.indexOf(toUpdate);
                            productValuesBfTaxes.get(index).setActualPrice(newValue);
                        }
                        //Si tiene impuestos
                        Collection<Tax> pTaxes = temp.getTaxesCollection();
                        double pcts = 0;
                        if(!pTaxes.isEmpty()){
                        for (Tax productTaxes : pTaxes) {
                            ///Se actualiza el valor antes de impuestos del producto
                            productValuesBfTaxes.remove(toUpdate);
                            ProductConverter p = new ProductConverter(temp.getIdProductPk(), newValue, temp.getProductDescription());
                            productValuesBfTaxes.add(p);
                            //
                            pcts = pcts + productTaxes.getTaxPct();
                            if (!taxes.contains(productTaxes)) {
                                taxes.add(productTaxes);
                            }
                        }
                        newValue = newValue / ((pcts / 100) + 1);
                        //Se redondea a 2 cifras el valor escrito
                        newValue = (double)Math.round(newValue*100)/100;
                        }
                        //
                        temp.setActualPrice(newValue);
                        temp.setTotalPrice(format.format(newValue*temp.getAmount()));
                        updatePriceValues();
                        refreshProductDataTable();
                        validForm();
                    }
                }
        );
        tcProductTotalPrice = new TableColumn(bundle.getString("productTotalPriceInvoiceDatatable"));
        tcProductTotalPrice.prefWidthProperty()
                .bind(productDataTable.widthProperty().divide(8));
        tcProductTotalPrice.setCellValueFactory(
                new PropertyValueFactory<ProductConverter, String>("totalPrice"));
        pdtbFourthColumn = new TableColumn(bundle.getString("productAmountInvoiceDatatable"));
        pdtbFourthColumn.prefWidthProperty()
                .bind(productDataTable.widthProperty().divide(8));
        pdtbFourthColumn.setCellValueFactory(
                new PropertyValueFactory<ProductConverter, Integer>("amount"));
        pdtbFourthColumn.setCellFactory(cellFactory);
        pdtbFourthColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<ProductConverter, Integer>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<ProductConverter, Integer> t
                    ) {
                        ProductConverter temp = (ProductConverter) t.getTableView().getItems().get(
                                t.getTablePosition().getRow());
                        int newValue = t.getNewValue();
                        temp.setAmount(newValue);
                        temp.setTotalPrice(format.format(newValue*temp.getActualPrice()));
                        updatePriceValues();
                        refreshProductDataTable();
                        validForm();
                    }
                }
        );
        productDataTable.getColumns()
                .addAll(pdtbFirstColumn, pdtbSecondColumn, pdtbThirdColumn, pdtbFourthColumn, tcProductTotalPrice);
        //Fin parte tabla de productos
        mainPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.F10) {
                    try {
                        loadPayTypeModal();
                    } catch (IOException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (event.getCode() == KeyCode.ESCAPE) {
                    try {
                        reinitializeFormValues();
                    } catch (IOException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        //Listener Tabs
        checkerTabs.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Tab>(){
                    @Override
                    public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
                        switch (t1.getId()) {
                            case "quotation":
                                moduleID = "quotation";
                                setInvoiceDataTableView();
                                break;
                            case "credit":
                                moduleID = "credit";
                                setDataTableView();
                                break; 
                            case "invoice":
                                moduleID = "invoice";
                                setInvoiceDataTableView();
                                break;
                        }
                    }  
                });
        //Carga de descuentos disponibles
            Collection<Discount> discountList = abs.findAll("Discount");
            ObservableList<ComboBoxChoices> comboDiscList = FXCollections.observableArrayList();
            comboDiscList.add(new ComboBoxChoices("-1", bundle.getString("lblInvoiceNoDiscount")));
            for (Discount dis : discountList) {
                comboDiscList.add(new ComboBoxChoices(dis.getIdDiscountPk().toString(), dis.toString()));
            }
            discountOpt.getItems().addAll(comboDiscList);
            discountOpt.getSelectionModel().selectFirst();
        //Para ponerle foco al buscador de productos
            getProduct.setText("");
    }
    /*
     Método para esconder la lista de selección cuando se esta trabajando en otro campo
     */
    public void updatePriceValues() {
        //Se traen los productos en la tabla
        ObservableList<ProductConverter> tempList = productDataTable.getItems();
        format = new DecimalFormat("###,###.00");
        double subTotalInvoice = 0;
        double totalProducts = 0;
        lblTotalsGrid.getChildren().clear();
        for (ProductConverter product1 : tempList) {
            subTotalInvoice += product1.getActualPrice() * product1.getAmount();
            //Se cargan los valores reales del producto actual para los descuentos de factura
            for(ProductConverter convrt:productValuesBfTaxes){
                if(product1.getIdProductPk().equals(convrt.getIdProductPk())){
                    totalProducts += convrt.getActualPrice() * product1.getAmount();
                }
            }
        }
        //Se verifica que el producto tenga asociados impuestos o descuentos
            if (!discounts.isEmpty() || !taxes.isEmpty()) {
                double tempSubtotal = 0, tempDescs = 0;
                double tempTax;
                double tempTaxValue;
                int i = 1;
                if (!discounts.isEmpty()) {
                    lblSubTotalTitle.setText(bundle.getString("lblInvoiceSubTotal"));
                    lblSubTotal.setText(bundle.getString("moneyNotation") + format.format(subTotalInvoice));
                    lblTotalsGrid.add(lblSubTotalTitle, 0, 0);
                    lblTotalsGrid.add(lblSubTotal, 1, 0);
                    for (Discount disc : discounts) {
                        tempTax = disc.getDiscountPct() / 100;
                        //Se compara con el descuento de la factura, si lo tiene
                        if (invoiceDiscount != null && invoiceDiscount.equals(disc)) {
                            tempTaxValue = totalProducts * tempTax;
                        } else {
                            tempTaxValue = 0;
                        }
                        for (ProductConverter prod : tempList) {
                            for (Discount prodDisc : prod.getDiscountCollection()) {
                                if (prodDisc.equals(disc)) {
                                    //A fin de que el descuento lo haga sobre el valor sin impuestos
                                    for(ProductConverter convrt:productValuesBfTaxes){
                                        if(prod.getIdProductPk().equals(convrt.getIdProductPk())){
                                            tempTaxValue += (convrt.getActualPrice() * prod.getAmount()) * tempTax;
                                        }
                                    }
                                    /*int index = productValuesBfTaxes.indexOf(prod);
                                    ProductConverter tempProduct = productValuesBfTaxes.get(index);
                                    tempTaxValue += (tempProduct.getActualPrice() * prod.getAmount()) * tempTax;*/
                                }
                            }
                        }
                        tempSubtotal += tempTaxValue;
                        Label tempTaxTitle = new Label(bundle.getString("lblInvoiceDiscount") + "(" + disc.getPercentaje() + ")");
                        tempTaxTitle.getStyleClass().add("totalValueslabel");
                        tempTaxTitle.setAlignment(Pos.CENTER_RIGHT);
                        tempTaxTitle.setPrefWidth(164);
                        Label tempTaxLabel = new Label(bundle.getString("moneyNotation") + format.format(tempTaxValue));
                        tempTaxLabel.getStyleClass().add("totalValueslabel");
                        tempTaxLabel.setAlignment(Pos.CENTER_LEFT);
                        tempTaxLabel.setPrefWidth(136);
                        lblTotalsGrid.add(tempTaxTitle, 0, i);
                        lblTotalsGrid.add(tempTaxLabel, 1, i);
                        i++;
                    }
                    lblSubTotal.setText(bundle.getString("moneyNotation") + format.format(subTotalInvoice));
                    totalInvoice = Math.round(subTotalInvoice - tempSubtotal);
                    tempDescs = tempSubtotal;
                    lblTotalTitle.setText(bundle.getString("lblInvoiceTotal"));
                    lblTotalTitle.getStyleClass().add("totalLabel");
                    lblTotal.setText(bundle.getString("moneyNotation") + format.format(totalInvoice));
                    lblTotal.getStyleClass().add("totalLabel");
                    lblTotalsGrid.add(lblTotalTitle, 0, i);
                    lblTotalsGrid.add(lblTotal, 1, i);
                }
                if (!taxes.isEmpty()) {
                    //Se comprueba que no hayan descuentos en los totales
                    boolean hasDiscounts = false;
                    if (i > 1) {
                        hasDiscounts = true;
                        tempSubtotal = 0;
                        subTotalInvoice -= tempDescs;
                        lblSubTotalTitle.setText(bundle.getString("lblInvoiceTotalValue"));
                        lblTotalTitle.setText(bundle.getString("lblInvoiceSubTotal"));
                        lblTotalTitle.getStyleClass().remove("totalLabel");
                        lblTotalTitle.getStyleClass().add("totalValueslabel");
                        lblTotal.getStyleClass().remove("totalLabel");
                        lblTotal.getStyleClass().add("totalValueslabel");
                        lblTotal.setText(bundle.getString("moneyNotation") + format.format(subTotalInvoice));
                        i++;
                    } else {
                        lblSubTotalTitle.setText(bundle.getString("lblInvoiceSubTotal"));
                        lblSubTotal.setText(bundle.getString("moneyNotation") + format.format(subTotalInvoice));
                        lblTotalsGrid.add(lblSubTotalTitle, 0, 0);
                        lblTotalsGrid.add(lblSubTotal, 1, 0);
                    }
                    for (Tax tx : taxes) {
                        //
                        tempTaxValue = 0;
                        tempTax = tx.getTaxPct() / 100;
                        for (ProductConverter prod : tempList) {
                            for (Tax pordTax : prod.getTaxesCollection()) {
                                if (pordTax.equals(tx)) {
                                    tempTaxValue = tempTaxValue + (prod.getActualPrice() * prod.getAmount()) * tempTax;
                                }
                            }
                        }
                        tempSubtotal += tempTaxValue;
                        Label tempTaxTitle = new Label(tx.toString());
                        tempTaxTitle.getStyleClass().add("totalValueslabel");
                        tempTaxTitle.setAlignment(Pos.CENTER_RIGHT);
                        tempTaxTitle.setPrefWidth(164);
                        Label tempTaxLabel = new Label(bundle.getString("moneyNotation") + format.format(tempTaxValue));
                        tempTaxLabel.getStyleClass().add("totalValueslabel");
                        tempTaxLabel.setAlignment(Pos.CENTER_LEFT);
                        tempTaxLabel.setPrefWidth(136);
                        lblTotalsGrid.add(tempTaxTitle, 0, i);
                        lblTotalsGrid.add(tempTaxLabel, 1, i);
                        i++;
                    }
                    totalInvoice = Math.round(tempSubtotal + subTotalInvoice);
                    if (hasDiscounts) {
                        Label tempTotalsTitle = new Label(bundle.getString("lblInvoiceTotal"));
                        Label tempTotalsValue = new Label(bundle.getString("moneyNotation") + format.format(totalInvoice));
                        tempTotalsTitle.getStyleClass().add("totalLabel");
                        tempTotalsValue.getStyleClass().add("totalLabel");
                        tempTotalsTitle.setAlignment(Pos.CENTER_RIGHT);
                        tempTotalsValue.setAlignment(Pos.CENTER_LEFT);
                        tempTotalsTitle.setPrefWidth(164);
                        tempTotalsValue.setPrefWidth(136);
                        lblTotalsGrid.add(tempTotalsTitle, 0, i);
                        lblTotalsGrid.add(tempTotalsValue, 1, i);
                    } else {
                        lblTotalTitle.setText(bundle.getString("lblInvoiceTotal"));
                        lblTotalTitle.getStyleClass().add("totalLabel");
                        lblTotal.setText(bundle.getString("moneyNotation") + format.format(totalInvoice));
                        lblTotal.getStyleClass().add("totalLabel");
                        lblTotalsGrid.add(lblTotalTitle, 0, i);
                        lblTotalsGrid.add(lblTotal, 1, i);
                    }
                }
            }else{
                //Se comprueba si existe algún descuento de factura
                int i=0;
                totalInvoice = subTotalInvoice;
                if(invoiceDiscount!=null){
                    double desc = subTotalInvoice * (invoiceDiscount.getDiscountPct()/100);
                    totalInvoice = subTotalInvoice - desc;
                    //Se adicionan los label del subtotal
                    Label tempSubtotalTitle = new Label(bundle.getString("lblInvoiceSubTotal"));
                    tempSubtotalTitle.getStyleClass().add("totalValueslabel");
                    tempSubtotalTitle.setAlignment(Pos.CENTER_RIGHT);
                    tempSubtotalTitle.setPrefWidth(164);
                    Label tempValueLabel = new Label(bundle.getString("moneyNotation") + format.format(subTotalInvoice));
                    tempValueLabel.getStyleClass().add("totalValueslabel");
                    tempValueLabel.setAlignment(Pos.CENTER_LEFT);
                    tempValueLabel.setPrefWidth(136);
                    lblTotalsGrid.add(tempSubtotalTitle, 0, i);
                    lblTotalsGrid.add(tempValueLabel, 1, i);
                    i++;
                    //Se adicionan los label del descuento
                    Label tempTaxTitle = new Label(bundle.getString("lblInvoiceDiscount") + "(" + invoiceDiscount.getPercentaje() + ")");
                    tempTaxTitle.getStyleClass().add("totalValueslabel");
                    tempTaxTitle.setAlignment(Pos.CENTER_RIGHT);
                    tempTaxTitle.setPrefWidth(164);
                    Label tempTaxLabel = new Label(bundle.getString("moneyNotation") + format.format(desc));
                    tempTaxLabel.getStyleClass().add("totalValueslabel");
                    tempTaxLabel.setAlignment(Pos.CENTER_LEFT);
                    tempTaxLabel.setPrefWidth(136);
                    lblTotalsGrid.add(tempTaxTitle, 0, i);
                    lblTotalsGrid.add(tempTaxLabel, 1, i);
                    i++;
                }
                totalInvoice = Math.round(totalInvoice);
            lblTotalTitle.setText(bundle.getString("lblInvoiceTotal"));
            lblTotalTitle.getStyleClass().add("totalLabel");
            lblTotal.setText(bundle.getString("moneyNotation") + format.format(totalInvoice));
            lblTotal.getStyleClass().add("totalLabel");
            lblTotalsGrid.add(lblTotalTitle, 0, i);
            lblTotalsGrid.add(lblTotal, 1, i);
        }
    }
    /*
     Método para cambiar los valores de acuerdo al descuento seleccionado
     @param: evento que ejecute la acción (onAction)
     */
    @FXML
    private void updateDiscountOpt(ActionEvent event) {
        ComboBoxChoices selectedOpt = discountOpt.getSelectionModel().getSelectedItem();
        //Se recorre la lista de productos asociados para definir si tienen el descuento a remover
        boolean disscount = false;
        Collection<ProductConverter> tempList = productDataTable.getItems();
        for (ProductConverter tempProduct : tempList) {
            for (Discount prodDisc : tempProduct.getDiscountCollection()) {
                if (prodDisc.equals(invoiceDiscount)) {
                    disscount = true;
                }
            }
        }
        if (!disscount && discounts.contains(invoiceDiscount)) {
            discounts.remove(invoiceDiscount);
        }
        switch (selectedOpt.getItemValue()) {
            case "-1":
                invoiceDiscount = null;
                break;
            default:
                invoiceDiscount = (Discount) abs.findByIdInt("Discount", Integer.parseInt(selectedOpt.getItemValue()));
                if (!discounts.contains(invoiceDiscount)) {
                    discounts.add(invoiceDiscount);
                }
        }
        updatePriceValues();
    }
    /*
     Método para cargar el modal de registro de nuevo abono
     */
    public void refreshProductDataTable() {
        productDataTable.getColumns().clear();
        productDataTable.getColumns()
                .addAll(pdtbFirstColumn, pdtbSecondColumn, pdtbThirdColumn, pdtbFourthColumn, tcProductTotalPrice);
    }
    /*
     Método para validar que el formulario ya esta listo para ser enviado
     */
    void validForm() {
        if (!messagesPanel.isVisible() && !productDataTable.getItems().isEmpty() && actualClient != null) {
            paytype.setDisable(false);
        } else {
            paytype.setDisable(true);
        }
    }
    /*
     Método que carga en una cadena el nombre del usuario con sesión actual
     */
    public void setStringUsuario() {
        this.user.setText(sessionUser.getIdUserFk().getTotalName());
    }
    /*
     Método para cerrar la sesión a través de un botón
     @param: evento que ejecute la acción (onAction)
     */
    @FXML
    public void logOutAction(ActionEvent event) {
        try {
            String modalName = "dialog";
            String modalTitle = "dialogTitle";
            if (useCashBox) {
                modalName = "boxFinalCash";
                modalTitle = "closeSessionTitle";
            }
            Stage window = new Stage(StageStyle.DECORATED);
            Locale es = new Locale("es", "ES");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/zafirodesktop/ui/" + modalName + ".fxml"));
            fxmlLoader.setResources(ResourceBundle.getBundle("com.zafirodesktop.util.Bundle", es));
            Parent rootModal = (Parent) fxmlLoader.load();
            window.setScene(new Scene(rootModal));
            window.setTitle(bundle.getString(modalTitle));
            window.initModality(Modality.APPLICATION_MODAL);
            if (event != null) {
                window.initOwner(
                        ((Node) event.getSource()).getScene().getWindow());
            }
            window.show();
            DialogController dialogController = fxmlLoader.<DialogController>getController();
            dialogController.setMainController(this);
            dialogController.setIsBDRestore(isBDRestore);
            if (useCashBox) {
                Button bt = (Button) event.getSource();
                String optionID = null;
                if (bt.getId().equals("closeBoxBT")) {
                    optionID = bt.getId();
                }
                dialogController.initializeValuesCloseSession(null, optionID);
            } else {
                dialogController.initializeValues("closeSession", DialogController.LOG_OUT);
            }
        } catch (Exception e) {
            setMessage(bundle.getString("logOutError"), true);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            LogActions log = new LogActions();
            log.createEntry(String.valueOf(e.hashCode()), "Cerrar sesión", e.getMessage(), sw.toString(), sessionUser.toString());
            //e.printStackTrace();
        }
    }
    /*
     Método con las acciones que se realizan al cerrar sesión
     @param: Evento que ejecuta la acción (OnAction event) valor real en caja (Float realAmount),
     si se trata o no de un cierre de Turno (boolean isCloseTurn), si se trata de un cierre de caja (boolean isCloseBox),
     si se trata de una restauración de BD (boolean isRestore)
     */
    public void logOut(ActionEvent event, Double realAmount, boolean closeTurn, boolean isCloseBox, boolean isRestore) throws IOException, Exception {
        finalizeTurn(realAmount, closeTurn, isRestore);
        Locale es = new Locale("es", "ES");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/zafirodesktop/ui/login.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("com.zafirodesktop.util.Bundle", es));
        Parent rootModal = (Parent) fxmlLoader.load();
        LoginController loginController = fxmlLoader.<LoginController>getController();
        if (event != null) {
            loginController.setMessage("logOutSuccess");
        } else {
            loginController.setMessage("importSucces");
        }
        Stage window = new Stage(StageStyle.UNDECORATED);
        window.getIcons().add(new Image("/com/zafirodesktop/ui/img/ico/scirebox.bmp"));
        window.setScene(new Scene(rootModal));
        window.setTitle("Login");
        window.initModality(Modality.NONE);
        if (event != null) {
            window.initOwner(
                    ((Node) event.getSource()).getScene().getWindow());
        }
        closeModal();
        window.show();
        if (isCloseBox) {
            closeBox();
        }
        if(!isRestore){
            backUpDatabase();
        }
    }
    /*
     Método para iniciar el módulo de ayuda
     @param: evento que ejecute la acción (onAction)
     */
    @FXML
    private void helpAction(ActionEvent event) throws IOException {
        try {
            showHideMask(true);
            Locale es = new Locale("es", "ES");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/zafirodesktop/ui/help.fxml"));
            fxmlLoader.setResources(ResourceBundle.getBundle("com.zafirodesktop.util.Bundle", es));
            Parent rootModal = (Parent) fxmlLoader.load();
            HelpController helpController = fxmlLoader.<HelpController>getController();
            helpController.setMainController(this);
            Stage window = new Stage();
            window.initStyle(StageStyle.UNDECORATED);
            window.setScene(new Scene(rootModal));
            window.initModality(Modality.APPLICATION_MODAL);
            window.initOwner(
                    ((Node) event.getSource()).getScene().getWindow());
            window.show();
        } catch (Exception e) {
            setMessage(bundle.getString("helpError"), true);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            LogActions log = new LogActions();
            log.createEntry(String.valueOf(e.hashCode()), "Cargar modal de ayuda", e.getMessage(), sw.toString(), sessionUser.toString());
            showHideMask(false);
            //e.printStackTrace();
        }
    }
    /*
     Método para cambiar la vista Administrador/cajero
     @param: evento que ejecute la acción (onAction)
     */
    @FXML
    private void changeView(ActionEvent event) throws Exception{
        //Se redirecciona a la nueva vista dependiendo de la actual
        if (isAdminView) {
            String viewType = "mainChecker";
            loadView(viewType);
        } else {
            if(productDataTable.getItems().isEmpty()){
                String viewType = "main";
                loadView(viewType);
            }else{
                Stage modall = new Stage(StageStyle.DECORATED);
                Locale es = new Locale("es", "ES");
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/zafirodesktop/ui/dialog.fxml"));
                fxmlLoader.setResources(ResourceBundle.getBundle("com.zafirodesktop.util.Bundle", es));
                Parent rootModal = (Parent) fxmlLoader.load();
                modall.setScene(new Scene(rootModal));
                modall.setTitle(bundle.getString("dialogTitle"));
                modall.initModality(Modality.APPLICATION_MODAL);
                modall.initOwner(
                    ((Node) event.getSource()).getScene().getWindow());
                modall.show();
                DialogController dialogController = fxmlLoader.<DialogController>getController();
                dialogController.setMainController(this);
                dialogController.initializeValues("txtChangeView", 13);
            }
        }
    }
    /*
        Método para cargar a la vista especificada
        @param: tipo de vista a cargar (String viewType)
    */
    public void loadView(String viewType) throws IOException, Exception {
        Locale es = new Locale("es", "ES");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/zafirodesktop/ui/" + viewType + ".fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("com.zafirodesktop.util.Bundle", es));
        Parent rootModal = (Parent) fxmlLoader.load();
        MainController mainController = fxmlLoader.<MainController>getController();
        mainController.initializeValuesUser(sessionUser);
        //Se especifica la vista a mostrar
        if (viewType.equals("mainChecker")) {
            mainController.initializeValuesChecker();
            mainController.setIsAdminView(false);
        } else {
            mainController.loadCharts();
            mainController.setIsAdminView(true);
        }
        //Se inicializa la variable que indica si se esta utilizando caja
        mainController.setUseCashBox(useCashBox);
        mainController.hideRemoveCloseBox();
        Stage window = new Stage();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        window.getIcons().add(new Image("/com/zafirodesktop/ui/img/ico/scirebox.bmp"));
        window.setX(bounds.getMinX());
        window.setY(bounds.getMinY());
        window.setWidth(bounds.getWidth());
        window.setHeight(bounds.getHeight());
        window.setScene(new Scene(rootModal));
        window.setTitle("Scirebox");
        window.initStyle(StageStyle.UNDECORATED);
        window.initModality(Modality.NONE);
        window.show();
        closeModal();
    }
    /*
     Método para mostrar el contenido de inicio
     */
    private void setWelcomeContent() {
        loadCharts();
        messagesPanel.setVisible(false);
        contentPane.setVisible(false);
        welcomePane.setVisible(true);
        contentTitle.setText(bundle.getString("contentTitleText"));
        Image img = new Image("/com/zafirodesktop/ui/img/dash.png");
        imageContent.setImage(img);
    }
    /*
     Método para la acción del botón home
     @param: evento que ejecute la acción (onAction)
     */
    @FXML
    private void displayHome(ActionEvent event) {
        setWelcomeContent();
    }
    /*
     Método para cargar el módulo respecto a la opción seleccionada en el menú
     @param: evento que ejecute la acción (onAction)
     */
    @FXML
    private void displaySelectedMenu(ActionEvent event) {
        messagesPanel.setVisible(false);
        welcomePane.setVisible(false);
        contentPane.setVisible(true);
        Button button = (Button) event.getSource();
        moduleID = button.getId();
        moduleTitle = button.getText();
        contentTitle.setText(moduleTitle);
        Image img = new Image("/com/zafirodesktop/ui/img/" + moduleID + ".png");
        imageContent.setImage(img);
        setDataTableView();
        switch (moduleID) {
            case "product":
            case "service":
                categories.setVisible(true);
                categories.setDisable(false);
                productCategories.setVisible(true);
                productCategories.setDisable(true);
                categories.getItems().clear();
                abs = new AbstractFacade();
                categories.getItems().add(new ComboBoxChoices("-1", bundle.getString("homeComboBoxOption")));
                Collection<Category> categoriesList = abs.findAll("Category");
                ObservableList<ComboBoxChoices> comboList = FXCollections.observableArrayList();
                for (Category cat : categoriesList) {
                    comboList.add(new ComboBoxChoices(cat.getIdCategoryPk().toString(), cat.getCategoryName()));
                }
                categories.getItems().addAll(comboList);
                categories.getSelectionModel().selectFirst();
                //productCategories.getItems().clear();
                if (moduleID.equals("product")) {
                    productsBarcode.setVisible(true);
                } else {
                    productsBarcode.setVisible(false);
                }
                break;
            case "movement":
            case "serviceOrder":
            case "quotation":
                categories.getItems().clear();
                categories.getItems().add(new ComboBoxChoices("-1", bundle.getString("homeComboBoxOption")));
                categories.getSelectionModel().selectFirst();
                categories.setVisible(false);
                categories.setDisable(true);
                productsBarcode.setVisible(false);
                productCategories.getItems().clear();
                productCategories.getItems().add(new ComboBoxChoices("-1", bundle.getString("homeComboBoxOption")));
                ObservableList<ComboBoxChoices> optionsComboList;
                switch (moduleID) {
                    case "quotation":
                        optionsComboList = FXCollections.observableArrayList(new ComboBoxChoices("0", "Pendiente"),
                                new ComboBoxChoices("1", "Facturada"));
                        break;
                    case "movement":
                        optionsComboList = FXCollections.observableArrayList(new ComboBoxChoices("1", "Pendiente"),
                                new ComboBoxChoices("2", "Finalizado"));
                        break;
                    default:
                        optionsComboList = FXCollections.observableArrayList(new ComboBoxChoices("0", "Anulado"),
                                new ComboBoxChoices("1", "Pendiente"),
                                new ComboBoxChoices("2", "Finalizado"),
                                new ComboBoxChoices("3", "Facturado"));
                        break;
                }
                productCategories.getItems().addAll(optionsComboList);
                productCategories.getSelectionModel().selectFirst();
                productCategories.setVisible(true);
                productCategories.setDisable(false);
                break;
            default:
                categories.setVisible(false);
                categories.setDisable(true);
                productCategories.setVisible(false);
                productCategories.setDisable(true);
                productsBarcode.setVisible(false);
                break;
        }
        switch (moduleID) {
            case "credit":
                moduleID = "initializeCredit";
                newItemBT.setText(bundle.getString("initializeCreditButton"));
                break;
            case "creditOrder":
                moduleID = "initializeCreditOrder";
                newItemBT.setText(bundle.getString("initializeCreditButton"));
                break;
            default:
                newItemBT.setText(bundle.getString("insertNewItemButton"));
                break;
        }
    }
    /*
     Método para cargar el modal de registro de nuevo item, especifico para Settings
     @param: evento que ejecute la acción (onAction)
     */
    public void loadSettingsModal(ActionEvent event) throws IOException {
        messagesPanel.setVisible(false);
        showHideMask(true);
        if(isAdminView){
            setWelcomeContent();
        }
        modal = new Stage(StageStyle.UNDECORATED);
        Button button = (Button) event.getSource();
        moduleID = button.getId();
        moduleTitle = button.getText();
        Locale es = new Locale("es", "ES");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/zafirodesktop/ui/" + moduleID + ".fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("com.zafirodesktop.util.Bundle", es));
        Parent rootModal = (Parent) fxmlLoader.load();
        switch (moduleID) {
            case "settings":
                SettingsController settingsController = fxmlLoader.<SettingsController>getController();
                settingsController.setMainController(this);
                break;
            case "about":
                WelcomeController aboutController = fxmlLoader.<WelcomeController>getController();
                aboutController.setMainController(this);
                aboutController.initializeAboutValues();
                break;
            case "impor":
                BackupController backController = fxmlLoader.<BackupController>getController();
                backController.setMainController(this);
                backController.initializeValues(sessionUser);
                break;
            case "stock":
                ReportsController reportsController = fxmlLoader.<ReportsController>getController();
                reportsController.setMainController(this);
                reportsController.initializeHelp();
                break;
            case "invalidatedInvoices":
            case "entriesReport":
            case "invoicesReport":
            case "earningsReport":
            case "ordersReport":
            case "closeesBoxReport":
            case "inventoryReport":
            case "minimumStock":
            case "topSelled":
            case "expensesReport":
                int type;
                switch (moduleID) {
                    case "invalidatedInvoices":
                        type = 3;
                        break;
                    case "expensesReport":
                        type = ReportsController.EXPENSES_REPORT;
                        break;
                    case "inventoryReport":
                        type = 4;
                        break;
                    case "topSelled":
                        type = 5;
                        break;
                    case "invoicesReport":
                        type = ReportsController.INVOICES_REPORT;
                        break;
                    case "closeesBoxReport":
                        type = ReportsController.CLOSE_BOX_REPORT;
                        break;
                    case "ordersReport":
                        type = ReportsController.ORDERS_REPORT;
                        break;
                    case "earningsReport":
                        type = ReportsController.EARNINGS_REPORT;
                        break;
                    default:
                        type = ReportsController.ENTRIES_REPORT;
                        break;
                }
                ReportsController reportController = fxmlLoader.<ReportsController>getController();
                reportController.setMainController(this);
                reportController.initializeValues(type);
                break;
            case "backup":
                BackupController backupController = fxmlLoader.<BackupController>getController();
                backupController.setMainController(this);
        }
        modal.setScene(new Scene(rootModal));
        modal.setTitle(moduleTitle);
        modal.initModality(Modality.APPLICATION_MODAL);
        modal.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
        modal.show();
    }
    /*
     Método para cargar el modal de registro de nuevo item, respecto al módulo actual
     @param: evento que ejecute la acción (onAction)
     */
    public void insertNewItemModal(ActionEvent event) throws IOException {
        showHideMask(true);
        messagesPanel.setVisible(false);
        if (productDataTable != null && event != null) {
            Button button = (Button) event.getSource();
            moduleID = button.getId();
            selectedProducts = productDataTable.getItems();
        }
        switch (moduleID) {
            case "credit":
                moduleID = "initializeCredit";
                break; 
            case "creditOrder":
                moduleID = "initializeCreditOrder";
                break;
        }
        modal = new Stage(StageStyle.UNDECORATED);
        Locale es = new Locale("es", "ES");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/zafirodesktop/ui/" + moduleID + ".fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("com.zafirodesktop.util.Bundle", es));
        Parent rootModal = (Parent) fxmlLoader.load();
        switch (moduleID) {
            case "users":
                UsersController userController = fxmlLoader.<UsersController>getController();
                userController.setMainController(this);
                break;
            case "category":
                CategoryController categoryController = fxmlLoader.<CategoryController>getController();
                categoryController.setMainController(this);
                break;
            case "person":
                PersonController personController = fxmlLoader.<PersonController>getController();
                personController.setMainController(this);
                if (productDataTable != null) {
                    personController.initializeValuesChecker();
                }
                break;
            case "product":
                ProductController productController = fxmlLoader.<ProductController>getController();
                productController.setMainController(this);
                break;
            case "service":
                ProductController serviceController = fxmlLoader.<ProductController>getController();
                serviceController.setMainController(this);
                break;
            case "productDepartment":
                ProductController departmentController = fxmlLoader.<ProductController>getController();
                departmentController.setMainController(this);
                break;
            case "tax":
                TaxController taxController = fxmlLoader.<TaxController>getController();
                taxController.setMainController(this);
                taxController.initializeTax();
                break;
            case "discount":
                DiscountsController discountController = fxmlLoader.<DiscountsController>getController();
                discountController.setMainController(this);
                discountController.initializeValues();
                break;
            case "paytype":
                PaytypeController paytypeController = fxmlLoader.<PaytypeController>getController();
                paytypeController.setMainController(this);
                paytypeController.initializeValues(isUseCashBox(), totalInvoice, selectedProducts, actualClient, sessionUser, obs.getText(), productValuesBfTaxes, invoiceDiscount);
                break;
            case "output":
                DepositController depositController = fxmlLoader.<DepositController>getController();
                depositController.setMainController(this);
                depositController.initializeValuesOutput(sessionUser);
                break;
            /*case "restaurantTable":
                RestaurantTableController mesaController = fxmlLoader.<RestaurantTableController>getController();
                mesaController.setMainController(this);
                break;*/
            default:
                InvoiceController invoiceController = fxmlLoader.<InvoiceController>getController();
                invoiceController.setMainController(this);
                switch (moduleID) {
                    case "invoice":
                        invoiceController.initializeValues(InvoiceController.INVOICE, sessionUser);
                        break;
                    case "order":
                        invoiceController.initializeValues(InvoiceController.ORDER, sessionUser);
                        break;
                    case "addInventory":
                        invoiceController.initializeValues(InvoiceController.ADD_INVENTORY, sessionUser);
                        break;
                    case "initializeCredit":
                        invoiceController.initializeCredit(InvoiceController.INITIALIZE_CREDIT, sessionUser);
                        break;
                    case "initializeCreditOrder":
                        invoiceController.initializeCredit(InvoiceController.INITIALIZE_CREDIT_ORDER, sessionUser);
                        break;
                    case "serviceOrder":
                        invoiceController.initializeValues(InvoiceController.SERVICE_ORDER, sessionUser);
                        break;
                    case "quotation":
                        invoiceController.initializeValues(InvoiceController.QUOTATION, sessionUser);
                        break;
                    default:
                        invoiceController.initializeValues(4, sessionUser);
                        break;
                }
        }
        modal.setScene(new Scene(rootModal));
        modal.setTitle(moduleTitle);
        modal.initModality(Modality.APPLICATION_MODAL);
        if (event != null) {
            modal.initOwner(
                    ((Node) event.getSource()).getScene().getWindow());
        }
        modal.show();
    }
    /*
     Método para cargar los datos del TableView de acuerdo al módulo actual
     */
    public void setDataTableView() {
        ObservableList<String> init = FXCollections.observableArrayList();
        dataTable.setPlaceholder(new Label(bundle.getString("lblDataTableNoItems")));
        dataTable.setItems(init);
        dataTable.getColumns().removeAll(dtbFirstColumn, dtbSecondColumn, dtbThirdColumn, dtbFourthColumn, dtbFifthColumn);
        final ObservableList<Object> data = FXCollections.observableArrayList();
        SessionBD.persistenceCreate();
        abs = new AbstractFacade();
        switch (moduleID) {
            case "users": {
                String[] columnNames = {bundle.getString("idUserDatatable"), bundle.getString("nameUserDatatable"), bundle.getString("lastnameUserDatatable"), bundle.getString("typeUserDatatable")};
                String[] rowValues = {"username", "firstName", "lastName", "type"};
                Collection<Users> usersList = abs.findAll("Users");
                for (Users usr : usersList) {
                    data.add(usr);
                }
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
            case "category": {
                String[] columnNames = {bundle.getString("idCategoryDatatable"), bundle.getString("nameCategoryDatatable"), bundle.getString("asociatedItemsCategoryDatatable"), ""};
                String[] rowValues = {"id", "categoryName", "asociatedItems", ""};
                Collection<Category> categoryList = abs.findAll("Category");
                for (Category usr : categoryList) {
                    data.add(usr);
                }
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
            case "person": {
                String[] columnNames = {bundle.getString("idPersonDatatable"), bundle.getString("namePesronDatatable"), bundle.getString("phonePersonDatatable"), bundle.getString("typePersonDatatable")};
                String[] rowValues = {"nit", "totalName", "personPhoneNo", "type"};
                Collection<Person> persons = abs.findAll("Person");
                for (Person per : persons) {
                    data.add(per);
                }
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
            case "product": {
                String[] columnNames = {bundle.getString("productIdDatatable"), bundle.getString("productDescriptionDatatable"), bundle.getString("productPriceDatatable"), bundle.getString("productAmountDatatable")};
                String[] rowValues = {"skuProduct", "productDescription", "price", "cantidadDisponible"};
                Collection<Product> products = abs.findAll("Product");
                for (Product pdt : products) {
                    data.add(pdt);
                }
                productsList = products;
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
            case "service": {
                String[] columnNames = {bundle.getString("productIdDatatable"), bundle.getString("productReferenceDatatable"), bundle.getString("productDescriptionDatatable"), bundle.getString("productPriceDatatable")};
                String[] rowValues = {"skuProduct", "productReference", "productDescription", "price"};
                Collection<Product> products = abs.findAll("Service");
                for (Product pdt : products) {
                    data.add(pdt);
                }
                productsList = products;
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
            case "productDepartment": {
                String[] columnNames = {bundle.getString("productIdDatatable"), bundle.getString("productReferenceDatatable"), bundle.getString("productDescriptionDatatable"), bundle.getString("asociatedProductsDatatable")};
                String[] rowValues = {"skuProduct", "productReference", "productDescription", "associatedProducts"};
                Collection<Product> products = abs.findAll("Department");
                for (Product pdt : products) {
                    data.add(pdt);
                }
                productsList = products;
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
            case "invoice": {
                String[] columnNames = {bundle.getString("IdInvoiceDatatable"), bundle.getString("dateInvoiceDatatable"), bundle.getString("clientInvoiceDatatable"), bundle.getString("valueInvoiceDatatable")};
                String[] rowValues = {"invoiceId", "invoiceDate", "totalName", "tranzactionPrice"};
                Collection<Remission> remissionList = abs.findAll("Invoice");
                for (Remission remission : remissionList) {
                    data.add(remission);
                }
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
            case "quotation": {
                String[] columnNames = {bundle.getString("IdInvoiceDatatable"), bundle.getString("dateInvoiceDatatable"), bundle.getString("clientInvoiceDatatable"), bundle.getString("movementStatusInvoiceDatatable")};
                String[] rowValues = {"idQuotationPk", "date", "totalName", "quotationStatus"};
                Collection<Quotation> quotationList = abs.findAll("Quotation");
                for (Quotation quot : quotationList) {
                    data.add(quot);
                }
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
            case "order": {
                String[] columnNames = {bundle.getString("IdInvoiceDatatable"), bundle.getString("dateInvoiceDatatable"), bundle.getString("supplierInvoiceDatatable"), bundle.getString("valueInvoiceDatatable")};
                String[] rowValues = {"idRemissionPk", "date", "supplierName", "tranzactionPrice"};
                Collection<Remission> remissionList = abs.findAll("Order");
                for (Remission remission : remissionList) {
                    data.add(remission);
                }
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
            case "addInventory": {
                String[] columnNames = {bundle.getString("IdInvoiceDatatable"), bundle.getString("dateInvoiceDatatable"), bundle.getString("userInvoiceDatatable"), bundle.getString("lblInvoiceObs")};
                String[] rowValues = {"idRemissionPk", "date", "userName", "obs"};
                Collection<Remission> remissionList = abs.findAll("AddInventory");
                for (Remission remission : remissionList) {
                    data.add(remission);
                }
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
            case "output": {
                String[] columnNames = {bundle.getString("IdInvoiceDatatable"), bundle.getString("dateInvoiceDatatable"), bundle.getString("conceptInvoiceDatatable"), bundle.getString("valueInvoiceDatatable")};
                String[] rowValues = {"idRemissionPk", "date", "movementType", "tranzactionPrice"};
                Collection<Remission> remissionList = abs.findAll("Output");
                for (Remission remission : remissionList) {
                    data.add(remission);
                }
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
            case "serviceOrder": {
                String[] columnNames = {bundle.getString("IdInvoiceDatatable"), bundle.getString("dateInvoiceDatatable"), bundle.getString("clientInvoiceDatatable"), bundle.getString("movementStatusInvoiceDatatable")};
                String[] rowValues = {"idRemissionPk", "date", "totalName", "movementStatus"};
                Collection<Remission> remissionList = abs.findAll("ServiceOrder");
                for (Remission remission : remissionList) {
                    data.add(remission);
                }
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
            case "tax": {
                String[] columnNames = {bundle.getString("idTaxDatatable"), bundle.getString("taxNameTaxDatatable"), bundle.getString("taxPercentTaxDatatable"), ""};
                String[] rowValues = {"idTaxPk", "taxName", "percentage", ""};
                Collection<Tax> taxList = abs.findAll("Tax");
                for (Tax usr : taxList) {
                    data.add(usr);
                }
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
            case "discount": {
                String[] columnNames = {bundle.getString("idDiscountDatatable"), bundle.getString("lblDiscountObs"), bundle.getString("discountPercentDatatable"), ""};
                String[] rowValues = {"idDiscountPk", "discountDescrption", "percentaje", ""};
                Collection<Discount> taxList = abs.findAll("Discount");
                for (Discount usr : taxList) {
                    data.add(usr);
                }
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
            case "credit": {
                String[] columnNames = {bundle.getString("IdInvoiceDatatable"), bundle.getString("dateInvoiceDatatable"), bundle.getString("clientInvoiceDatatable"), bundle.getString("leftAmountInvoiceDatatable")};
                String[] rowValues = {"idRemissionPk", "date", "totalName", "tranzactionLeftAmount"};
                Collection<Remission> remissionList = abs.findAll("Credit");
                for (Remission rem : remissionList) {
                    data.add(rem);
                }
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
            case "creditOrder": {
                String[] columnNames = {bundle.getString("IdInvoiceDatatable"), bundle.getString("dateInvoiceDatatable"), bundle.getString("supplierInvoiceDatatable"), bundle.getString("leftAmountInvoiceDatatable")};
                String[] rowValues = {"idRemissionPk", "date", "supplierName", "tranzactionLeftAmount"};
                Collection<Remission> remissionList = abs.findAll("CreditOrder");
                for (Remission rem : remissionList) {
                    data.add(rem);
                }
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
            default: {
                String[] columnNames = {bundle.getString("IdInvoiceDatatable"), bundle.getString("dateInvoiceDatatable"), bundle.getString("personInvoiceDatatable"), bundle.getString("movementStatusInvoiceDatatable")};
                String[] rowValues = {"idRemissionPk", "date", "movementType", "movementStatus"};
                Collection<Remission> remissionList = abs.findAll("Movement");
                for (Remission remission : remissionList) {
                    data.add(remission);
                }
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
        }
        resultsDesc.setText(dataTable.getItems().size() + " " + bundle.getString("resultsFind"));
    }
    /*
     Método para cargar los datos del TableView de facturas y cotizaciones para la vista de cajero
     */
    public void setInvoiceDataTableView() {
        ObservableList<String> init = FXCollections.observableArrayList();
        final ObservableList<Object> data = FXCollections.observableArrayList();
        SessionBD.persistenceCreate();
        abs = new AbstractFacade();
        //Se comprueba si esta accediendo a facturas o cotizaciones
        if(moduleID.equals("invoice")){
            invoiceDataTable.setItems(init);
            invoiceDataTable.getColumns().removeAll(fdtbFirstColumn, fdtbSecondColumn, fdtbThirdColumn, fdtbFourthColumn);
            String[] columnNames = {bundle.getString("IdInvoiceDatatable"), bundle.getString("dateInvoiceDatatable"), bundle.getString("clientInvoiceDatatable"), bundle.getString("valueInvoiceDatatable")};
            String[] rowValues = {"invoiceId", "invoiceDate", "totalName", "tranzactionPrice"};
            Collection<Remission> remissionList = abs.findAll("Invoice");
            for (Remission remission : remissionList) {
                data.add(remission);
            }
            loadInvoiceDataTableView(columnNames, rowValues, data);
            resultsDesc2.setText(invoiceDataTable.getItems().size() + " " + bundle.getString("resultsFind"));
        }else{
            quotationDataTable.setItems(init);
            quotationDataTable.getColumns().removeAll(qdtbFirstColumn, qdtbSecondColumn, qdtbThirdColumn, qdtbFourthColumn);
            String[] columnNames = {bundle.getString("IdInvoiceDatatable"), bundle.getString("dateInvoiceDatatable"), bundle.getString("clientInvoiceDatatable"), bundle.getString("movementStatusInvoiceDatatable")};
            String[] rowValues = {"idQuotationPk", "date", "totalName", "quotationStatus"};
            Collection<Quotation> quotationList = abs.findAll("Quotation");
            for (Quotation quot : quotationList) {
                data.add(quot);
            }
            loadInvoiceDataTableView(columnNames, rowValues, data);
            resultsQuotation.setText(quotationDataTable.getItems().size() + " " + bundle.getString("resultsFind"));
        }
    }
    /*
     Método para cargar los valores del table view de acuerdo al módulo seleccionado
     @param: nombres de las columnas(String[]), valores de las filas (String[]), lista de Objetos
     */
    public void loadDataTableView(String[] columnNames, String[] rowValues, ObservableList<Object> data) {
        dtbFirstColumn = new TableColumn(columnNames[0]);
        dtbFirstColumn.prefWidthProperty().bind(dataTable.widthProperty().divide(4));
        dtbSecondColumn = new TableColumn(columnNames[1]);
        dtbSecondColumn.prefWidthProperty().bind(dataTable.widthProperty().divide(4));
        dtbThirdColumn = new TableColumn(columnNames[2]);
        dtbThirdColumn.prefWidthProperty().bind(dataTable.widthProperty().divide(4));
        dtbFourthColumn = new TableColumn(columnNames[3]);
        dtbFourthColumn.prefWidthProperty().bind(dataTable.widthProperty().divide(4));
        dtbFirstColumn.setCellValueFactory(new PropertyValueFactory<Users, String>(rowValues[0]));
        dtbSecondColumn.setCellValueFactory(new PropertyValueFactory<Users, String>(rowValues[1]));
        dtbThirdColumn.setCellValueFactory(new PropertyValueFactory<Users, String>(rowValues[2]));
        dtbFourthColumn.setCellValueFactory(new PropertyValueFactory<Users, String>(rowValues[3]));
        dataTable.setItems(data);
        dataTable.getColumns().addAll(dtbFirstColumn, dtbSecondColumn, dtbThirdColumn, dtbFourthColumn);
        /*Pagination pagination = new Pagination(data.size() / 15, 0);
         pagination.setPageFactory(new Callback<Integer, Node>() {
         @Override
         public Node call(Integer pageIndex) {
         return createPage(pageIndex);
         }
         });
         paginationVB.getChildren().add(pagination);*/
    }
    /*
     Método para cargar los valores del table view de busqueda de facturas y cotizaciones en la vista cajero
     @param: nombres de las columnas(String[]), valores de las filas (String[]), lista de Objetos
     */
    public void loadInvoiceDataTableView(String[] columnNames, String[] rowValues, ObservableList<Object> data) {
        //Se comprueba si está accediendo a facturas o cotizaciones
        if(moduleID.equals("invoice")){
            fdtbFirstColumn = new TableColumn(columnNames[0]);
            fdtbFirstColumn.prefWidthProperty().bind(invoiceDataTable.widthProperty().divide(4));
            fdtbSecondColumn = new TableColumn(columnNames[1]);
            fdtbSecondColumn.prefWidthProperty().bind(invoiceDataTable.widthProperty().divide(4));
            fdtbThirdColumn = new TableColumn(columnNames[2]);
            fdtbThirdColumn.prefWidthProperty().bind(invoiceDataTable.widthProperty().divide(4));
            fdtbFourthColumn = new TableColumn(columnNames[3]);
            fdtbFourthColumn.prefWidthProperty().bind(invoiceDataTable.widthProperty().divide(4));
            fdtbFirstColumn.setCellValueFactory(new PropertyValueFactory<Users, String>(rowValues[0]));
            fdtbSecondColumn.setCellValueFactory(new PropertyValueFactory<Users, String>(rowValues[1]));
            fdtbThirdColumn.setCellValueFactory(new PropertyValueFactory<Users, String>(rowValues[2]));
            fdtbFourthColumn.setCellValueFactory(new PropertyValueFactory<Users, String>(rowValues[3]));
            invoiceDataTable.setItems(data);
            invoiceDataTable.getColumns().addAll(fdtbFirstColumn, fdtbSecondColumn, fdtbThirdColumn, fdtbFourthColumn);
        }else{
            qdtbFirstColumn = new TableColumn(columnNames[0]);
            qdtbFirstColumn.prefWidthProperty().bind(quotationDataTable.widthProperty().divide(4));
            qdtbSecondColumn = new TableColumn(columnNames[1]);
            qdtbSecondColumn.prefWidthProperty().bind(quotationDataTable.widthProperty().divide(4));
            qdtbThirdColumn = new TableColumn(columnNames[2]);
            qdtbThirdColumn.prefWidthProperty().bind(quotationDataTable.widthProperty().divide(4));
            qdtbFourthColumn = new TableColumn(columnNames[3]);
            qdtbFourthColumn.prefWidthProperty().bind(quotationDataTable.widthProperty().divide(4));
            qdtbFirstColumn.setCellValueFactory(new PropertyValueFactory<Users, String>(rowValues[0]));
            qdtbSecondColumn.setCellValueFactory(new PropertyValueFactory<Users, String>(rowValues[1]));
            qdtbThirdColumn.setCellValueFactory(new PropertyValueFactory<Users, String>(rowValues[2]));
            qdtbFourthColumn.setCellValueFactory(new PropertyValueFactory<Users, String>(rowValues[3]));
            quotationDataTable.setItems(data);
            quotationDataTable.getColumns().addAll(qdtbFirstColumn, qdtbSecondColumn, qdtbThirdColumn, qdtbFourthColumn);
        }
        /*Pagination pagination = new Pagination(data.size() / 15, 0);
         pagination.setPageFactory(new Callback<Integer, Node>() {
         @Override
         public Node call(Integer pageIndex) {
         return createPage(pageIndex);
         }
         });
         paginationVB.getChildren().add(pagination);*/
    }
    /*
     Método para cargar el fxml del update del item seleccionado
     @param: evento que ejecute la acción (onMouseClicked)
     */
    public void loadSelectedTableViewItem(MouseEvent event) throws IOException {
        messagesPanel.setVisible(false);
        if (dataTable.getSelectionModel().getSelectedItem() != null) {
            if (moduleID.equals("initializeCredit") || productDataTable != null) {
                moduleID = "credit";
            }else if(moduleID.equals("initializeCreditOrder")){
                moduleID = "creditOrder";
            }
            showHideMask(true);
            modal = new Stage(StageStyle.UNDECORATED);
            Locale es = new Locale("es", "ES");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/zafirodesktop/ui/" + moduleID + ".fxml"));
            fxmlLoader.setResources(ResourceBundle.getBundle("com.zafirodesktop.util.Bundle", es));
            Parent rootModal = (Parent) fxmlLoader.load();
            modal.setScene(new Scene(rootModal));
            modal.setTitle(moduleTitle);
            modal.initModality(Modality.APPLICATION_MODAL);
            modal.initOwner(
                    ((Node) event.getSource()).getScene().getWindow());
            modal.show();
            switch (moduleID) {
                case "users":
                    Users actualUsers = (Users) dataTable.getSelectionModel().getSelectedItem();
                    UsersController userController = fxmlLoader.<UsersController>getController();
                    userController.setMainController(this);
                    userController.initializeValuesExists(actualUsers);
                    break;
                case "category":
                    Category actualCategory = (Category) dataTable.getSelectionModel().getSelectedItem();
                    CategoryController categoryController = fxmlLoader.<CategoryController>getController();
                    categoryController.setMainController(this);
                    categoryController.initializeValuesExists(actualCategory);
                    break;
                case "person":
                    Person actualPerson = (Person) dataTable.getSelectionModel().getSelectedItem();
                    PersonController personController = fxmlLoader.<PersonController>getController();
                    personController.setMainController(this);
                    personController.initializeValuesExists(actualPerson);
                    break;
                case "product":
                    Product actualProduct = (Product) dataTable.getSelectionModel().getSelectedItem();
                    ProductController productController = fxmlLoader.<ProductController>getController();
                    productController.setMainController(this);
                    productController.initializeValuesExists(actualProduct, sessionUser);
                    break;
                case "service":
                    Product actualService = (Product) dataTable.getSelectionModel().getSelectedItem();
                    ProductController serviceController = fxmlLoader.<ProductController>getController();
                    serviceController.setMainController(this);
                    serviceController.initializeValuesExists(actualService, sessionUser);
                    break;
                case "productDepartment":
                    Product actualDepartment = (Product) dataTable.getSelectionModel().getSelectedItem();
                    ProductController departmentController = fxmlLoader.<ProductController>getController();
                    departmentController.setMainController(this);
                    departmentController.initializeValuesExists(actualDepartment, sessionUser);
                    break;
                case "tax":
                    Tax actualTax = (Tax) dataTable.getSelectionModel().getSelectedItem();
                    TaxController taxController = fxmlLoader.<TaxController>getController();
                    taxController.setMainController(this);
                    taxController.initializeTax();
                    taxController.initializeExists(actualTax);
                    break;
                case "discount":
                    Discount actuDiscount = (Discount) dataTable.getSelectionModel().getSelectedItem();
                    DiscountsController discountController = fxmlLoader.<DiscountsController>getController();
                    discountController.setMainController(this);
                    discountController.initializeValues();
                    discountController.initializeValuesExists(actuDiscount);
                    break;
                case "credit":
                    Remission actualRemission = (Remission) dataTable.getSelectionModel().getSelectedItem();
                    DepositController depositController = fxmlLoader.<DepositController>getController();
                    depositController.setMainController(this);
                    depositController.initializeValues(actualRemission, sessionUser, DepositController.DEPOSIT);
                    break;
                case "creditOrder":
                    Remission actualCredit = (Remission) dataTable.getSelectionModel().getSelectedItem();
                    DepositController creditController = fxmlLoader.<DepositController>getController();
                    creditController.setMainController(this);
                    creditController.initializeValues(actualCredit, sessionUser, DepositController.DEPOSIT_ORDER);
                    break;
                case "quotation":
                    Quotation actualQuotation = (Quotation) dataTable.getSelectionModel().getSelectedItem();
                    InvoiceController quotationController = fxmlLoader.<InvoiceController>getController();
                    quotationController.setMainController(this);
                    quotationController.initializeValues(20, sessionUser);
                    quotationController.initializeValuesQuotation(actualQuotation);
                    break;
                case "output":
                    Remission actualOutput = (Remission) dataTable.getSelectionModel().getSelectedItem();
                    DepositController outputController = fxmlLoader.<DepositController>getController();
                    outputController.setMainController(this);
                    outputController.initializeValuesOutput(sessionUser);
                    outputController.initializeValuesOutputExits(actualOutput);
                    break;
                default:
                    int type;
                    Remission remi = (Remission) dataTable.getSelectionModel().getSelectedItem();
                    InvoiceController invoiceController = fxmlLoader.<InvoiceController>getController();
                    invoiceController.setMainController(this);
                    switch (moduleID) {
                        case "order":
                            type = InvoiceController.ORDER;
                            break;
                        case "invoice":
                            type = InvoiceController.INVOICE;
                            break;
                        case "serviceOrder":
                            type = InvoiceController.SERVICE_ORDER;
                            break;
                        case "addInventory":
                            type = InvoiceController.ADD_INVENTORY;
                            break;
                        default:
                            type = InvoiceController.MOVEMENT;
                            break;
                    }
                    invoiceController.initializeValues(type, sessionUser);
                    invoiceController.initializeValuesExists(remi, null, null);
                    break;
            }
        }
    }
    /*
     Método para cargar el fxml del update del item seleccionado
     @param: evento que ejecute la acción (onMouseClicked)
     */
    public void loadSelectedInvoiceTableViewItem(MouseEvent event) throws IOException {
        messagesPanel.setVisible(false);
        //Se comprueba si es factura o cotización
        if(moduleID.equals("invoice")){
            if (invoiceDataTable.getSelectionModel().getSelectedItem() != null) {
                showHideMask(true);
                modal = new Stage(StageStyle.UNDECORATED);
                Locale es = new Locale("es", "ES");
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/zafirodesktop/ui/invoice.fxml"));
                fxmlLoader.setResources(ResourceBundle.getBundle("com.zafirodesktop.util.Bundle", es));
                Parent rootModal = (Parent) fxmlLoader.load();
                modal.setScene(new Scene(rootModal));
                modal.setTitle(moduleTitle);
                modal.initModality(Modality.APPLICATION_MODAL);
                modal.initOwner(
                    ((Node) event.getSource()).getScene().getWindow());
                modal.show();
                Remission remi = (Remission) invoiceDataTable.getSelectionModel().getSelectedItem();
                InvoiceController invoiceController = fxmlLoader.<InvoiceController>getController();
                invoiceController.setMainController(this);
                invoiceController.initializeValues(2, sessionUser);
                invoiceController.initializeValuesExists(remi, null, null);
            }
        }else{
            if (quotationDataTable.getSelectionModel().getSelectedItem() != null) {
                showHideMask(true);
                modal = new Stage(StageStyle.UNDECORATED);
                Locale es = new Locale("es", "ES");
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/zafirodesktop/ui/quotation.fxml"));
                fxmlLoader.setResources(ResourceBundle.getBundle("com.zafirodesktop.util.Bundle", es));
                Parent rootModal = (Parent) fxmlLoader.load();
                modal.setScene(new Scene(rootModal));
                modal.setTitle(moduleTitle);
                modal.initModality(Modality.APPLICATION_MODAL);
                modal.initOwner(
                    ((Node) event.getSource()).getScene().getWindow());
                modal.show();
                Quotation quota = (Quotation) quotationDataTable.getSelectionModel().getSelectedItem();
                InvoiceController invoiceController = fxmlLoader.<InvoiceController>getController();
                invoiceController.setMainController(this);
                invoiceController.initializeValues(20, sessionUser);
                invoiceController.initializeValuesQuotation(quota);
            }
        }    
    }
    /*
     Método para buscar un dato en el datatable de acuerdo al menú actual
     @param: evento que ejecute la acción (onAction)
     */
    @FXML
    public void searchTableView(KeyEvent event) throws ParseException {
        messagesPanel.setVisible(false);
        ObservableList<String> init = FXCollections.observableArrayList();
        dataTable.setItems(init);
        dataTable.getColumns().removeAll(dtbFirstColumn, dtbSecondColumn, dtbThirdColumn, dtbFourthColumn, dtbFifthColumn);
        final ObservableList<Object> data = FXCollections.observableArrayList();
        //Se adapta para la búsqueda en la versión cajero
        if (moduleID.equals("credit")) {
            moduleID = "initializeCredit";
        }
        SessionBD.persistenceCreate();
        abs = new AbstractFacade();
        switch (moduleID) {
            case "users": {
                String[] columnNames = {"ID Usuario", "Nombre", "Apellidos", "Tipo"};
                String[] rowValues = {"username", "firstName", "lastName", "type"};
                Collection<Users> usersList = abs.findByLike("Users", searchTF.getText(), searchTF.getText().toLowerCase(), searchTF.getText().toLowerCase());
                for (Users usr : usersList) {
                    data.add(usr);
                }
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
            case "category": {
                int id = 0;
                if (Pattern.matches("\\d+", searchTF.getText())) {
                    id = Integer.valueOf(searchTF.getText());
                }
                String[] columnNames = {bundle.getString("idCategoryDatatable"), bundle.getString("nameCategoryDatatable"), bundle.getString("asociatedItemsCategoryDatatable"), ""};
                String[] rowValues = {"id", "categoryName", "asociatedItems", ""};
                Collection<Category> categoryList = abs.findByLikeInt("Category", id, searchTF.getText().toLowerCase(), searchTF.getText().toLowerCase());
                for (Category usr : categoryList) {
                    data.add(usr);
                }
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
            case "person": {
                String[] columnNames = {bundle.getString("idPersonDatatable"), bundle.getString("namePesronDatatable"), bundle.getString("phonePersonDatatable"), bundle.getString("typePersonDatatable")};
                String[] rowValues = {"nit", "totalName", "personPhoneNo", "type"};
                Collection<Person> persons = abs.findByLike("Person", searchTF.getText().toLowerCase(), searchTF.getText().toLowerCase(), searchTF.getText().toLowerCase());
                for (Person per : persons) {
                    data.add(per);
                }
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
            case "product": {
                String[] columnNames = {bundle.getString("productIdDatatable"), bundle.getString("productDescriptionDatatable"), bundle.getString("productPriceDatatable"), bundle.getString("productAmountDatatable")};
                String[] rowValues = {"skuProduct", "productDescription", "price", "cantidadDisponible"};
                Collection<Product> producs = abs.findByLike("Product", searchTF.getText().toLowerCase(), searchTF.getText().toLowerCase(), searchTF.getText().toLowerCase());
                for (Product pdt : producs) {
                    data.add(pdt);
                }
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
            case "service": {
                String[] columnNames = {bundle.getString("productIdDatatable"), bundle.getString("productReferenceDatatable"), bundle.getString("productDescriptionDatatable"), bundle.getString("productPriceDatatable")};
                String[] rowValues = {"skuProduct", "productReference", "productDescription", "price"};
                Collection<Product> products = abs.findByLike("Service", searchTF.getText().toLowerCase(), searchTF.getText().toLowerCase(), searchTF.getText().toLowerCase());
                for (Product pdt : products) {
                    data.add(pdt);
                }
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
            case "productDepartment": {
                String[] columnNames = {bundle.getString("productIdDatatable"), bundle.getString("productReferenceDatatable"), bundle.getString("productDescriptionDatatable"), bundle.getString("asociatedProductsDatatable")};
                String[] rowValues = {"skuProduct", "productReference", "productDescription", "associatedProducts"};
                Collection<Product> products = abs.findByLike("Department", searchTF.getText().toLowerCase(), searchTF.getText().toLowerCase(), searchTF.getText().toLowerCase());
                for (Product pdt : products) {
                    data.add(pdt);
                }
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
            case "invoice": {
                String[] columnNames = {bundle.getString("IdInvoiceDatatable"), bundle.getString("dateInvoiceDatatable"), bundle.getString("clientInvoiceDatatable"), bundle.getString("valueInvoiceDatatable")};
                String[] rowValues = {"invoiceId", "invoiceDate", "totalName", "tranzactionPrice"};
                Collection<Remission> remissionList = abs.findByLike("Invoice", searchTF.getText(), searchTF.getText().toLowerCase(), searchTF.getText());
                for (Remission remission : remissionList) {
                    data.add(remission);
                }
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
            case "order": {
                int id = 0;
                if (Pattern.matches("\\d+", searchTF.getText())) {
                    id = Integer.valueOf(searchTF.getText());
                }
                String[] columnNames = {bundle.getString("IdInvoiceDatatable"), bundle.getString("dateInvoiceDatatable"), bundle.getString("supplierInvoiceDatatable"), bundle.getString("valueInvoiceDatatable")};
                String[] rowValues = {"idRemissionPk", "date", "supplierName", "tranzactionPrice"};
                Collection<Remission> remissionList = abs.findByLikeInt("Order", id, searchTF.getText().toLowerCase(), searchTF.getText());
                for (Remission remission : remissionList) {
                    data.add(remission);
                }
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
            case "addInventory": {
                int id = 0;
                if (Pattern.matches("\\d+", searchTF.getText())) {
                    id = Integer.valueOf(searchTF.getText());
                }
                String[] columnNames = {bundle.getString("IdInvoiceDatatable"), bundle.getString("dateInvoiceDatatable"), bundle.getString("userInvoiceDatatable"), bundle.getString("lblInvoiceObs")};
                String[] rowValues = {"idRemissionPk", "date", "userName", "obs"};
                Collection<Remission> remissionList = abs.findByLikeInt("AddInventory", id, searchTF.getText().toLowerCase(), searchTF.getText().toLowerCase());
                for (Remission remission : remissionList) {
                    data.add(remission);
                }
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
            case "quotation": {
                int id = 0;
                if (Pattern.matches("\\d+", searchTF.getText())) {
                    id = Integer.valueOf(searchTF.getText());
                }
                String[] columnNames = {bundle.getString("IdInvoiceDatatable"), bundle.getString("dateInvoiceDatatable"), bundle.getString("clientInvoiceDatatable"), bundle.getString("movementStatusInvoiceDatatable")};
                String[] rowValues = {"idQuotationPk", "date", "totalName", "quotationStatus"};
                Collection<Quotation> quotationList = abs.findByLikeInt("Quotation", id, searchTF.getText().toLowerCase(), searchTF.getText().toLowerCase());
                for (Quotation quot : quotationList) {
                    data.add(quot);
                }
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
            case "serviceOrder": {
                int id = 0;
                if (Pattern.matches("\\d+", searchTF.getText())) {
                    id = Integer.valueOf(searchTF.getText());
                }
                String[] columnNames = {bundle.getString("IdInvoiceDatatable"), bundle.getString("dateInvoiceDatatable"), bundle.getString("clientInvoiceDatatable"), bundle.getString("movementStatusInvoiceDatatable")};
                String[] rowValues = {"idRemissionPk", "date", "totalName", "movementStatus"};
                Collection<Remission> remissionList = abs.findByLikeInt("ServiceOrder", id, searchTF.getText().toLowerCase(), searchTF.getText());
                for (Remission remission : remissionList) {
                    data.add(remission);
                }
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
            case "tax": {
                String[] columnNames = {bundle.getString("idTaxDatatable"), bundle.getString("taxNameTaxDatatable"), bundle.getString("taxPercentTaxDatatable"), ""};
                String[] rowValues = {"idTaxPk", "taxName", "percentage", ""};
                Collection<Tax> taxList = abs.findByLike("Tax", searchTF.getText(), searchTF.getText().toLowerCase(), searchTF.getText().toLowerCase());
                for (Tax usr : taxList) {
                    data.add(usr);
                }
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
            case "discount": {
                String[] columnNames = {bundle.getString("idDiscountDatatable"), bundle.getString("lblDiscountObs"), bundle.getString("discountPercentDatatable"), ""};
                String[] rowValues = {"idDiscountPk", "discountDescrption", "percentaje", ""};
                Collection<Discount> taxList = abs.findByLike("Discount", searchTF.getText(), searchTF.getText().toLowerCase(), searchTF.getText().toLowerCase());
                for (Discount usr : taxList) {
                    data.add(usr);
                }
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
            case "initializeCredit": {
                int id = 0;
                if (Pattern.matches("\\d+", searchTF.getText())) {
                    id = Integer.valueOf(searchTF.getText());
                }
                String[] columnNames = {bundle.getString("IdInvoiceDatatable"), bundle.getString("dateInvoiceDatatable"), bundle.getString("clientInvoiceDatatable"), bundle.getString("leftAmountInvoiceDatatable")};
                String[] rowValues = {"idRemissionPk", "date", "totalName", "tranzactionLeftAmount"};
                Collection<Remission> remissionList = abs.findByLikeInt("Credit", id, searchTF.getText().toLowerCase(), searchTF.getText());
                for (Remission remission : remissionList) {
                    data.add(remission);
                }
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
            case "creditOrder": {
                int id = 0;
                if (Pattern.matches("\\d+", searchTF.getText())) {
                    id = Integer.valueOf(searchTF.getText());
                }
                String[] columnNames = {bundle.getString("IdInvoiceDatatable"), bundle.getString("dateInvoiceDatatable"), bundle.getString("supplierInvoiceDatatable"), bundle.getString("leftAmountInvoiceDatatable")};
                String[] rowValues = {"idRemissionPk", "date", "supplierName", "tranzactionLeftAmount"};
                Collection<Remission> remissionList = abs.findByLikeInt("CreditOrder", id, searchTF.getText().toLowerCase(), searchTF.getText());
                for (Remission remission : remissionList) {
                    data.add(remission);
                }
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
            case "output": {
                int id = 0;
                if (Pattern.matches("\\d+", searchTF.getText())) {
                    id = Integer.valueOf(searchTF.getText());
                }
                String[] columnNames = {bundle.getString("IdInvoiceDatatable"), bundle.getString("dateInvoiceDatatable"), bundle.getString("conceptInvoiceDatatable"), bundle.getString("valueInvoiceDatatable")};
                String[] rowValues = {"idRemissionPk", "date", "movementType", "tranzactionPrice"};
                Collection<Remission> remissionList = abs.findByLikeInt("Output", id, searchTF.getText().toLowerCase(), searchTF.getText());
                for (Remission remission : remissionList) {
                    data.add(remission);
                }
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
            default: {
                int id = 0;
                if (Pattern.matches("\\d+", searchTF.getText())) {
                    id = Integer.valueOf(searchTF.getText());
                }
                String[] columnNames = {bundle.getString("IdInvoiceDatatable"), bundle.getString("dateInvoiceDatatable"), bundle.getString("personInvoiceDatatable"), bundle.getString("movementStatusInvoiceDatatable")};
                String[] rowValues = {"idRemissionPk", "date", "movementType", "movementStatus"};
                Collection<Remission> remissionList = abs.findByLikeInt("Movement", id, searchTF.getText().toLowerCase(), searchTF.getText().toLowerCase());
                for (Remission remission : remissionList) {
                    data.add(remission);
                }
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
        }
        resultsDesc.setText(dataTable.getItems().size() + " " + bundle.getString("resultsFind"));
    }
    /*
     Método para buscar un dato en el datatable de búsqueda de Facturas o cotizaciones en la vista cajero
     @param: evento que ejecute la acción (onAction)
     }*/
    @FXML
    public void searchInvoiceTableView(KeyEvent event) throws ParseException {
        messagesPanel.setVisible(false);
        ObservableList<String> init = FXCollections.observableArrayList();
        invoiceDataTable.setItems(init);
        invoiceDataTable.getColumns().removeAll(fdtbFirstColumn, fdtbSecondColumn, fdtbThirdColumn, fdtbFourthColumn);
        final ObservableList<Object> data = FXCollections.observableArrayList();
        SessionBD.persistenceCreate();
        abs = new AbstractFacade();
        //Se comprueba si se esta buscando facturas o cotizaciones
        if(moduleID.equals("invoice")){
            String[] columnNames = {bundle.getString("IdInvoiceDatatable"), bundle.getString("dateInvoiceDatatable"), bundle.getString("clientInvoiceDatatable"), bundle.getString("valueInvoiceDatatable")};
            String[] rowValues = {"invoiceId", "invoiceDate", "totalName", "tranzactionPrice"};
            Collection<Remission> remissionList = abs.findByLike("Invoice", invoiceSearchTF.getText(), invoiceSearchTF.getText().toLowerCase(), invoiceSearchTF.getText());
            for (Remission remission : remissionList) {
                data.add(remission);
            }
            loadInvoiceDataTableView(columnNames, rowValues, data);
            resultsDesc2.setText(invoiceDataTable.getItems().size() + " " + bundle.getString("resultsFind"));
        }else{
            int id = 0;
            if (Pattern.matches("\\d+", quotationSearchTF.getText())) {
                id = Integer.valueOf(quotationSearchTF.getText());
            }
            String[] columnNames = {bundle.getString("IdInvoiceDatatable"), bundle.getString("dateInvoiceDatatable"), bundle.getString("clientInvoiceDatatable"), bundle.getString("movementStatusInvoiceDatatable")};
            String[] rowValues = {"idQuotationPk", "date", "totalName", "quotationStatus"};
            Collection<Quotation> quotationList = abs.findByLikeInt("Quotation", id, quotationSearchTF.getText().toLowerCase(), quotationSearchTF.getText().toLowerCase());
            for (Quotation quot : quotationList) {
                data.add(quot);
            }
            loadInvoiceDataTableView(columnNames, rowValues, data);
            resultsQuotation.setText(quotationDataTable.getItems().size() + " " + bundle.getString("resultsFind"));
        }
    }
    /*
     Método para cargar el fxml de la factura
     @param: Remisión a facturar (Remission remission), Tipo de datos a cargar (int type)
     */
    public void loadInvoiceView(Remission remi, int type, Collection<ProductConverter> list, Quotation quot) throws IOException {
        messagesPanel.setVisible(false);
        showHideMask(true);
        modal = new Stage(StageStyle.UNDECORATED);
        Locale es = new Locale("es", "ES");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/zafirodesktop/ui/invoice.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("com.zafirodesktop.util.Bundle", es));
        Parent rootModal = (Parent) fxmlLoader.load();
        modal.setScene(new Scene(rootModal));
        modal.setTitle(moduleTitle);
        modal.initModality(Modality.APPLICATION_MODAL);
        modal.initOwner(
                (mainPane.getScene().getWindow()));
        modal.show();
        InvoiceController invoiceController = fxmlLoader.<InvoiceController>getController();
        invoiceController.setMainController(this);
        invoiceController.initializeValues(type, sessionUser);
        if (quot == null) {
            invoiceController.initializeValuesExists(remi, null, null);
        } else {
            invoiceController.initializeValuesExists(remi, list, quot);
        }
    }
    /*
     Método para cargar el reporte de inventario
     */
    public void inventoryReport() throws DocumentException, IOException {
        try {
            PrintPDF pdf = new PrintPDF();
            String type = "inventory";
            String reportTilte = "Inventario";
            String[] columnNames = {"Código", "Referencia", "Descripción", "Valor Unitario $", "Cantidad Mínima", "Cantidad Disponible", "Valor Total $"};
            abs = new AbstractFacade();
            Collection<Inventory> inventory = abs.findAll("Inventory");
            int[] columnWidths = new int[]{10, 10, 40, 10, 10, 10, 10};
            setMessage(bundle.getString("generateReportSucces"), false);
            pdf.printReport(type, reportTilte, columnNames, columnWidths, inventory, null, null, bundle);
        } catch (IOException | HeadlessException e) {
            setMessage(bundle.getString("pdfError"), true);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            LogActions log = new LogActions();
            log.createEntry(String.valueOf(e.hashCode()), "Imprimir reporte de inventario", e.getMessage(), sw.toString(), sessionUser.toString());
            //e.printStackTrace();
        } catch (Exception e) {
            setMessage(bundle.getString("generateReportError"), true);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            LogActions log = new LogActions();
            log.createEntry(String.valueOf(e.hashCode()), "Generar reporte de inventario", e.getMessage(), sw.toString(), sessionUser.toString());
            //e.printStackTrace();
        }
    }
    /*
     Método para generar el reporte de cantidades Mínimas
     */
    public void minimunStockReport() throws DocumentException, IOException {
        try {
            PrintPDF pdf = new PrintPDF();
            String type = "minimumStock";
            String reportTilte = "Stock sobre cantidad mínima";
            String[] columnNames = {"Código", "Referencia", "Cantidad Mínima", "Cantidad Disponible", "Diferencia"};
            abs = new AbstractFacade();
            Collection<Inventory> inventory = abs.findAll("MinimumStock");
            int[] columnWidths = new int[]{25, 45, 10, 10, 10};
            setMessage(bundle.getString("generateReportSucces"), false);
            pdf.printReport(type, reportTilte, columnNames, columnWidths, inventory, null, null, bundle);
        } catch (IOException | HeadlessException e) {
            setMessage(bundle.getString("pdfError"), true);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            LogActions log = new LogActions();
            log.createEntry(String.valueOf(e.hashCode()), "Imprimir reporte de minimo stock", e.getMessage(), sw.toString(), sessionUser.toString());
            //e.printStackTrace();
        } catch (Exception e) {
            setMessage(bundle.getString("generateReportError"), true);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            LogActions log = new LogActions();
            log.createEntry(String.valueOf(e.hashCode()), "Generar reporte de minimo stock", e.getMessage(), sw.toString(), sessionUser.toString());
            //e.printStackTrace();
        }
    }
    /*
     Método para generar el reporte de créditos activos mas antiguos
     */
    @FXML
    public void activeCreditReport(ActionEvent event) throws DocumentException, IOException {
        try {
            PrintPDF pdf = new PrintPDF();
            String type = "activeCredit";
            String reportTilte = bundle.getString("activeCreditsReportTitle");
            String personType = "Cliente";
            RemissionModel rm = new RemissionModel();
            Collection<Remission> remissions; 
            //Se comprueba el tipo de reporte a mostrar
            Button bSelected = (Button)event.getSource();
            if(bSelected.getId()!=null && bSelected.getId().equals("orderCreditsReport")){
                personType = "Proveedor";
                reportTilte = bundle.getString("activeCreditsOrderReportTitle");
                remissions = rm.findAllCreditsOrderActive();
            }else{
                remissions = rm.findAllCreditsActive();
            }
            String[] columnNames = {bundle.getString("lblReportMovementId"), bundle.getString("lblReportLastCredit"), personType, bundle.getString("lblReportLeftAmount")};
            int[] columnWidths = new int[]{15, 15, 55, 15};
            setMessage(bundle.getString("generateReportSucces"), false);
            pdf.printReport(type, reportTilte, columnNames, columnWidths, remissions, null, null, bundle);
        } catch (IOException | HeadlessException e) {
            setMessage(bundle.getString("pdfError"), true);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            LogActions log = new LogActions();
            log.createEntry(String.valueOf(e.hashCode()), "Imprimir reporte de créditos activos", e.getMessage(), sw.toString(), sessionUser.toString());
            e.printStackTrace();
        } catch (Exception e) {
            setMessage(bundle.getString("generateReportError"), true);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            LogActions log = new LogActions();
            log.createEntry(String.valueOf(e.hashCode()), "Generar reporte de créditos activos", e.getMessage(), sw.toString(), sessionUser.toString());
            e.printStackTrace();
        }
    }
    /*
     Método para recargar los valores del combobox primario
     @param: evento que ejecute la acción (onAction)
     */
    @FXML
    private void selectedComboBoxUpdateListMain(ActionEvent event) {
        if (moduleID.equals("product") || moduleID.equals("service")) {
            productCategories.getItems().clear();
        }
        if (categories.getSelectionModel().getSelectedItem() != null) {
            ComboBoxChoices selectedCategory = categories.getSelectionModel().getSelectedItem();
            if (selectedCategory.getItemValue().equals("-1")) {
                setDataTableView();
                productCategories.setDisable(true);
            } else {
                CategoryModel cm = new CategoryModel();
                Category categori = (Category) cm.findByIdInt("Category", Integer.parseInt(selectedCategory.getItemValue()));
                Collection<ItemCategory> itemsList = cm.findByIdCategory(categori);
                ObservableList<ComboBoxChoices> comboList = FXCollections.observableArrayList();
                for (ItemCategory itm : itemsList) {
                    comboList.add(new ComboBoxChoices(itm.getIdItemCategoryPk().toString(), itm.getItemCategoryName()));
                }
                productCategories.setDisable(false);
                productCategories.getItems().addAll(comboList);
                productCategories.getSelectionModel().selectFirst();
                productCategories.setDisable(false);
            }
        }
    }
    /*
     Método para recargar los valores del combobox secundario
     @param: evento que ejecute la acción (onAction)
     */
    @FXML
    private void selectedComboBoxUpdateListSecondary(ActionEvent event) throws ParseException {
        if (productCategories.getSelectionModel().getSelectedItem() != null) {
            if (moduleID.equals("product") || moduleID.equals("service")) {
                if (productCategories.getItems() != null) {
                    ComboBoxChoices optionSelected = (ComboBoxChoices) productCategories.getSelectionModel().getSelectedItem();
                    updateSelectedComboBoxOption(optionSelected);
                }
            } else {
                if (productCategories.getSelectionModel().getSelectedItem() != null) {
                    ComboBoxChoices optionSelected = (ComboBoxChoices) productCategories.getSelectionModel().getSelectedItem();
                    if (optionSelected.getItemValue().equals("-1")) {
                        setDataTableView();
                    } else {
                        updateSelectedComboBoxOption(optionSelected);
                    }
                }
            }
        }
    }
    /*
     Método para cargar la información de acuerdo a la categoría seleccionada
     */
    @FXML
    public void updateSelectedComboBoxOption(ComboBoxChoices optionSelected) throws ParseException {
        messagesPanel.setVisible(false);
        ObservableList<String> init = FXCollections.observableArrayList();
        dataTable.setItems(init);
        dataTable.getColumns().removeAll(dtbFirstColumn, dtbSecondColumn, dtbThirdColumn, dtbFourthColumn, dtbFifthColumn);
        final ObservableList<Object> data = FXCollections.observableArrayList();
        ProductModel pdm = new ProductModel();
        switch (moduleID) {
            case "product": {
                String[] columnNames = {bundle.getString("productIdDatatable"), bundle.getString("productDescriptionDatatable"), bundle.getString("productPriceDatatable"), bundle.getString("productAmountDatatable")};
                String[] rowValues = {"skuProduct", "productDescription", "price", "cantidadDisponible"};
                Collection<Product> productLista = pdm.findByCategory(Integer.valueOf(optionSelected.getItemValue()));
                for (Product pdt : productLista) {
                    data.add(pdt);
                }
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
            case "service": {
                String[] columnNames = {bundle.getString("productIdDatatable"), bundle.getString("productIdDatatable"), bundle.getString("productDescriptionDatatable"), bundle.getString("productPriceDatatable")};
                String[] rowValues = {"skuProduct", "productReference", "productDescription", "price"};
                Collection<Product> serviceList = pdm.findServiceByCategory(Integer.valueOf(optionSelected.getItemValue()));
                for (Product pdt : serviceList) {
                    data.add(pdt);
                }
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
            case "movement": {
                String[] columnNames = {bundle.getString("IdInvoiceDatatable"), bundle.getString("dateInvoiceDatatable"), bundle.getString("personInvoiceDatatable"), bundle.getString("movementStatusInvoiceDatatable")};
                String[] rowValues = {"idRemissionPk", "date", "movementType", "movementStatus"};
                Collection< Remission> remissionList = abs.findByStatus("Movement", Short.valueOf(optionSelected.getItemValue()));
                for (Remission remission : remissionList) {
                    data.add(remission);
                }
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
            case "quotation": {
                String[] columnNames = {bundle.getString("IdInvoiceDatatable"), bundle.getString("dateInvoiceDatatable"), bundle.getString("clientInvoiceDatatable"), bundle.getString("movementStatusInvoiceDatatable")};
                String[] rowValues = {"idQuotationPk", "date", "totalName", "quotationStatus"};
                Collection<Quotation> quotationList = abs.findByStatus("Quotation", Short.valueOf(optionSelected.getItemValue()));
                for (Quotation quot : quotationList) {
                    data.add(quot);
                }
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
            default: {
                String[] columnNames = {bundle.getString("IdInvoiceDatatable"), bundle.getString("dateInvoiceDatatable"), bundle.getString("clientInvoiceDatatable"), bundle.getString("movementStatusInvoiceDatatable")};
                String[] rowValues = {"idRemissionPk", "date", "totalName", "movementStatus"};
                Collection<Remission> remissionList = abs.findByStatus("ServiceOrder", Short.valueOf(optionSelected.getItemValue()));
                for (Remission remission : remissionList) {
                    data.add(remission);
                }
                loadDataTableView(columnNames, rowValues, data);
                break;
            }
        }
    }
    /*
     Método para crear un backup online de la BD
     @param: Conexión a la bd (Connection conexion)
     */
    public static void backUpDatabase() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
        String backupdirectory = "c:/backups/scireboxBK" + sdf.format(new Date());
        SessionBD.persistenceCreate();
        SettingsModel sm = new SettingsModel();
        sm.backupBD(backupdirectory);
    }
    /*
     Método para cerrar el turno y guardar los datos correspondientes
     @param: valor esperado en caja Float, valor real en caja Float
     */
    public void finalizeTurn(Double realAmount, boolean closeTurn, boolean isRestore) throws Exception {
        SessionBD.persistenceCreate();
        abs = new AbstractFacade();
        sessionUser.setRealAmount(realAmount);
        if (!isRestore) {
            if (closeTurn) {
                sessionUser.setEndDate(Calendar.getInstance().getTime());
            }
            abs.updateFinal(sessionUser);
        }
    }
    /*
     Método para cerrar la caja, en caso de que hayan turnos sin finalizar, se finalizan
     */
    public void closeBox() throws Exception {
        abs = new AbstractFacade();
        PrintPDF pdf = new PrintPDF();
        Collection<Turn> ct = abs.findByNonDate("Turn");
        for (Turn turn : ct) {
            turn.setEndDate(Calendar.getInstance().getTime());
            turn.setRealAmount(turn.getExpectedAmount());
            abs.updateFinal(turn);
        }
        pdf.printCloseBoxReport(null, bundle);
    }
    /*
     Método para cargar los mensajes a mostrar desde el Bundle del idioma
     @param: Mensaje a mostrar, tipo de mensaje
     */
    public void setMessage(String message, boolean errorMessage) {
        messagesPanel.setVisible(true);
        this.message.setText(message);
        if (!errorMessage) {
            this.message.getStyleClass().remove("errorMessage");
            this.message.getStyleClass().remove("succesMessage");
            this.message.getStyleClass().add("succesMessage");
        } else {
            this.message.getStyleClass().remove("succesMessage");
            this.message.getStyleClass().remove("errorMessage");
            this.message.getStyleClass().add("errorMessage");
        }
    }
    /*
     Método para la impresión de todos los códigos de barras de los productos
     @param: Evento que ejecuta la acción (ActionEvent onAction)
     @return: void
     */
    public void printAllProducts(ActionEvent onAction) throws DocumentException, IOException {
        PrintPDF print = new PrintPDF();
        print.printAllProductsBarcode(productsList);
    }
    /*
     Método para ocultar o mostrar la máscara de diálogo
     @param: La bandera que indica si debe ser mostrada o ocultada (boolean toShow) 
     */
    public void showHideMask(boolean toShow) {
        maskPane.setVisible(toShow);
    }
    /*
     Método buscar una persona a través de un ListView
     @param: evento que ejecute la acción (onKeyPress)
     */
    @FXML
    private void searchAction(KeyEvent event) throws IOException {
        messagesPanel.setVisible(false);
        listsViewPanel.setVisible(true);
        TextField tfActual = (TextField) event.getSource();
        moduleID = tfActual.getId();
        ObservableList data = FXCollections.observableArrayList();
        abs = new AbstractFacade();
        switch (moduleID) {
            case "getPerson": {
                productList.setVisible(false);
                Collection<Person> list = abs.findByLike("Client", getPerson.getText(), getPerson.getText(), getPerson.getText());
                for (Person person1 : list) {
                    data.add(person1);
                }
                personList.setVisible(true);
                personList.setItems(data);
                personList.getSelectionModel().selectFirst();
                break;
            }
            default: {
                personList.setVisible(false);
                Collection<Product> list;
                list = abs.findByLike("ServiceProduct", getProduct.getText(), getProduct.getText(), getProduct.getText());
                for (Product product1 : list) {
                    data.add(product1);
                }
                productList.setVisible(true);
                productList.setItems(data);
                productList.getSelectionModel().selectFirst();
                break;
            }
        }
    }
    /*
     Método para cargar los datos del formulario de acuerdo al dato seleccionado 
     */
    private void loadSelectedData(String moduleID) {
        if (moduleID.equals("getPerson") || moduleID.equals("personList")) {
            if (personList.getSelectionModel().getSelectedItem() != null) {
                Person persona = (Person) personList.getSelectionModel().getSelectedItem();
                actualClient = persona;
                getPerson.setText(persona.toString());
            } else {
                getPerson.setText("");
            }
            personList.setVisible(false);
        } else {
            if (productList.getSelectionModel().getSelectedItem() != null) {
                Product slPrd = (Product) productList.getSelectionModel().getSelectedItem();
                //Se crea un nuevo producto para asociarlo a la factura
                ProductConverter tempProduct = new ProductConverter(slPrd.getIdProductPk(), slPrd.getSkuProduct(), slPrd.getActualPrice(), slPrd.getProductDescription());
                tempProduct.setTaxesCollection(new ArrayList<Tax>());
                tempProduct.setDiscountCollection(new ArrayList<Discount>());
                //Se asocian los impuestos y descuentos, en caso de que los tenga
                if(!slPrd.getProductTaxesCollection().isEmpty()){
                    for(ProductTaxes pTx:slPrd.getProductTaxesCollection()){
                        tempProduct.getTaxesCollection().add(pTx.getTax());
                    }
                }
                if(!slPrd.getProductDiscountCollection().isEmpty()){
                    for(ProductDiscount pDc:slPrd.getProductDiscountCollection()){
                        tempProduct.getDiscountCollection().add(pDc.getDiscount());
                    }
                }
                ///Agregar producto a la lista de valores actuales y a la lista de valores antes de impuestos, en caso de que no esté agregado ya
                int i=0;
                ProductConverter p = new ProductConverter(tempProduct.getIdProductPk(), tempProduct.getActualPrice(), tempProduct.getProductDescription());
                for(ProductConverter cnvrtr:productValuesBfTaxes){
                    if(cnvrtr.getIdProductPk().equals(tempProduct.getIdProductPk())){
                        i++;
                    }
                }
                if(i==0)
                    productValuesBfTaxes.add(p);
                //
                ///Se comprueba que la tabla no tenga asociado ya el producto
                ObservableList<ProductConverter> currentItems = productDataTable.getItems();
                for(ProductConverter pcnvrtr:currentItems){
                    if(pcnvrtr.getIdProductPk().equals(tempProduct.getIdProductPk())){
                        i=-1;
                        p = pcnvrtr;
                    }
                }
                //
                if (i>=0) {
                    /// Agregar Descuentos en caso de que los tenga a la lista
                        float dcts = 0, pcts = 0;
                        if (!tempProduct.getDiscountCollection().isEmpty()) {
                            Collection<Discount> pDiscount = tempProduct.getDiscountCollection();
                            for (Discount productDiscount : pDiscount) {
                                dcts += productDiscount.getDiscountPct();
                                if (!discounts.contains(productDiscount)) {
                                    discounts.add(productDiscount);
                                }
                            }
                            //Se agrega el descuento a la descripción del producto
                            tempProduct.setProductDescription(tempProduct.getProductDescription() +" ("+ dcts + "% " + bundle.getString("lblDiscountInfo")
                                    + ")");
                        }//
                        /// Agregar Impuestos en caso de que los tenga a la lista
                    if (!tempProduct.getTaxesCollection().isEmpty()) {
                        Collection<Tax> pTaxes = tempProduct.getTaxesCollection();
                    for (Tax productTaxes : pTaxes) {
                        pcts = pcts + productTaxes.getTaxPct();
                        if (!taxes.contains(productTaxes)) {
                            taxes.add(productTaxes);
                        }
                    }
                    double newValue = tempProduct.getActualPrice() / ((pcts / 100) + 1);
                    //Se redondea a 2 cifras el valor escrito
                    newValue = (double)Math.round(newValue*100)/100;
                    tempProduct.setActualPrice(newValue);
                    tempProduct.setTotalPrice(format.format(newValue*tempProduct.getAmount()));
                    }//
                    productDataTable.getItems().add(0, tempProduct);
                    updatePriceValues();
                    refreshProductDataTable();
                    getProduct.setText("");
                    rmvProductButton.setDisable(false);
                } else {
                    productDataTable.getSelectionModel().select(p);
                    ProductConverter tempProduct2 = (ProductConverter) productDataTable.getSelectionModel().getSelectedItem();
                    productDataTable.getItems().remove(tempProduct2);
                    int newAmount = tempProduct2.getAmount() + 1;
                    tempProduct2.setAmount(newAmount);
                    tempProduct2.setTotalPrice(format.format(tempProduct2.getActualPrice()*newAmount));
                    productDataTable.getItems().add(0,tempProduct2);
                    updatePriceValues();
                    refreshProductDataTable();
                    getProduct.setText("");
                }
            } else {
                setMessage(bundle.getString("notMatches"), true);
            }
            productList.setVisible(false);
        }
        listsViewPanel.setVisible(false);
        validForm();
    }
    /*
     Método quitar un producto de los agregados en la lista
     @param: evento que ejecute la acción (onAction)
     */
    @FXML
    private void removeProductTableView(ActionEvent event) {
        messagesPanel.setVisible(false);
        ObservableList<ProductConverter> tempList = productDataTable.getItems();
        Collection<Tax> removedTaxes = new ArrayList<>();
        ProductConverter tempProduct;
        if (productDataTable.getSelectionModel().getSelectedItem() != null) {
            tempProduct = (ProductConverter) productDataTable.getSelectionModel().getSelectedItem();
        } else {
            productDataTable.getSelectionModel().selectLast();
            tempProduct = (ProductConverter) productDataTable.getSelectionModel().getSelectedItem();
        }
        productDataTable.getItems().remove(tempProduct);
        productValuesBfTaxes.remove(tempProduct);
        //Se saca el producto de las listas de valores especificados
        Collection<Discount> removedDiscounts = new ArrayList<>();
        //Quita los descuentos asociados al producto en caso de que no estén en mas productos
            for (Discount disc : discounts) {
                int i = 0;
                for (ProductConverter prod : tempList) {
                    for (Discount prodDiscount : prod.getDiscountCollection()) {
                        if (prodDiscount.equals(disc)) {
                            i++;
                        }
                    }
                }
                if (i == 0) {
                    removedDiscounts.add(disc);
                }
            }
            discounts.removeAll(removedDiscounts);
            //
            //Quita los impuestos asociados al producto en caso de que no estén en mas productos
        for (Tax taxe : taxes) {
            int i = 0;
            for (ProductConverter prod : tempList) {
                for (Tax pordTax : prod.getTaxesCollection()) {
                    if (pordTax.equals(taxe)) {
                        i++;
                    }
                }
            }
            if (i == 0) {
                removedTaxes.add(taxe);
            }
        }
        taxes.removeAll(removedTaxes);
        //
        updatePriceValues();
        if (!productDataTable.getItems().isEmpty()) {
            rmvProductButton.setDisable(false);
        } else {
            rmvProductButton.setDisable(true);
        }
        validForm();
        getProduct.setText("");
    }
    /*
     Método para verificar la cantidad mínima del o los productos asociados
     a una venta o movimiento
     @param Colección de productos asociados a la venta (Colecction<Product> productos)
     */
    public void checkMinimumStock(Collection<ProductConverter> products) throws IOException {
        abs = new AbstractFacade();
        Stock stock;
        Product product1;
        for (ProductConverter prodConv : products) {
            product1 = (Product) abs.findByIdInt("Product", prodConv.getIdProductPk());
            if (product1.getType() == 1) {
                stock = (Stock) abs.findByIdInt("Stock", product1.getIdProductPk());
                if (product1.getMinimunStock() >= stock.getAmount()) {
                    String msg = bundle.getString("minimumStock") + " " + product1.getSkuProduct() + " - " + product1.getProductReference()
                            + "\n" + bundle.getString("productMinmumAmountDatatable") + ": " + product1.getMinimunStock() + " - "
                            + bundle.getString("productAmountDatatable") + ": " + stock.getAmount();
                    loadAlertModal(msg);
                }
            }
        }
    }
    /*
     Método para cargar el modal de Alerta para cantidad mìnima de producto
     @param: mensaje a Mostrar (String msg)
     */
    private void loadAlertModal(String msg) throws IOException {
        modal = new Stage(StageStyle.DECORATED);
        Locale es = new Locale("es", "ES");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/zafirodesktop/ui/dialog.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("com.zafirodesktop.util.Bundle", es));
        Parent rootModal = (Parent) fxmlLoader.load();
        DialogController dialogController = fxmlLoader.<DialogController>getController();
        dialogController.setMainController(this);
        dialogController.initializeAlert(DialogController.ALERT, msg);
        modal.setScene(new Scene(rootModal));
        modal.setTitle(bundle.getString("alertTitle"));
        modal.initModality(Modality.APPLICATION_MODAL);
        /*modal.initOwner(
         ((Node) event.getSource()).getScene().getWindow());*/
        modal.show();
    }
    /*
     Método para mostrar las opciones del listado de acuerdo al texto ingresado
     @param: evento que ejecute la acción (onAction)
     */
    @FXML
    private void selectedAction(ActionEvent event) {
        TextField tfActual = (TextField) event.getSource();
        moduleID = tfActual.getId();
        loadSelectedData(moduleID);
    }
    /*
     Método para cargar los datos del formulario de acuerdo al formulario seleccionado
     @param: evento que ejecute la acción (onMouseClicked)
     */
    @FXML
    private void selectedActionList(MouseEvent event) {
        ListView lvActual = (ListView) event.getSource();
        moduleID = lvActual.getId();
        loadSelectedData(moduleID);
    }
    /*
     Método para abrir las opciones de pago con una tecla(F10) en la vista cajero
     */
    private void loadPayTypeModal() throws IOException {
        if (!paytype.isDisable()) {
            hideListsView();
            moduleID = "paytype";
            selectedProducts = productDataTable.getItems();
            insertNewItemModal(null);
        }
    }
    /*
     Método para reinicializar los campos de la factura con una tecla(F9)
     */
    public void reinitializeFormValues() throws IOException, Exception {
        selectedProducts = productDataTable.getItems();
        productDataTable.getItems().clear();
        productValuesBfTaxes.clear();
        discountOpt.getSelectionModel().selectFirst();
        taxes.clear();
        discounts.clear();
        updatePriceValues();
        actualClient = (Person)abs.findByRealId("Person", 16000);
        getPerson.setText(actualClient.toString());
        obs.setText("");
        getProduct.setText("");
        //getProduct.setFocusTraversable(true);
        paytype.setDisable(true);
        rmvProductButton.setDisable(true);
        getProduct.requestFocus();
    }
    /*
     Método para cargar los chart de la pantalla de inicio
     */
    public void loadCharts() {
        chartsPane.getChildren().removeAll(bc, lineChart);
        int maxElements = 5;
        int count = 0;
        //Creación del chart de cantidades de productos
        abs = new AbstractFacade();
        final CategoryAxis barxAxis = new CategoryAxis();
        final NumberAxis baryAxis = new NumberAxis();
        bc = new BarChart<String, Number>(barxAxis, baryAxis);
        bc.setTitle(bundle.getString("leftChartTitle"));
        barxAxis.setLabel(bundle.getString("leftChartXLabel"));
        baryAxis.setLabel(bundle.getString("leftChartYLabel"));
        XYChart.Series cantidadMinima = new XYChart.Series();
        cantidadMinima.setName(bundle.getString("leftChartFirstLabel"));
        XYChart.Series cantidadDisponible = new XYChart.Series();
        cantidadDisponible.setName(bundle.getString("leftChartSecondLabel"));
        XYChart.Series cantidadDiferencia = new XYChart.Series();
        cantidadDiferencia.setName(bundle.getString("leftChartThirdLabel"));
        Collection inventoryList = abs.findAll("MinimumStock");
        Iterator productIt = inventoryList.iterator();
        while (productIt.hasNext() && count < maxElements) {
            MinimumStock ms = (MinimumStock) productIt.next();
            cantidadMinima.getData().add(new XYChart.Data(ms.getProductReference(), ms.getCantidadMinima()));
            cantidadDisponible.getData().add(new XYChart.Data(ms.getProductReference(), ms.getCantidadDisponible()));
            cantidadDiferencia.getData().add(new XYChart.Data(ms.getProductReference(), ms.getDiferencia()));
            productIt.remove();
            count += 1;
        }
        bc.getData().addAll(cantidadMinima, cantidadDisponible, cantidadDiferencia);
        try {
            //Creación del chart de créditos activos
            count = 0;
            ArrayList<Remission> sortedList = new ArrayList<>();
            final NumberAxis linexAxis = new NumberAxis();
            final CategoryAxis lineyAxis = new CategoryAxis();
            lineChart = new LineChart<String, Number>(lineyAxis, linexAxis);
            lineChart.setTitle(bundle.getString("rigthChartTitle"));
            linexAxis.setLabel(bundle.getString("rigthChartXLabel"));
            lineyAxis.setLabel(bundle.getString("rigthChartYLabel"));
            XYChart.Series series;
            RemissionModel rm = new RemissionModel();
            Tranzaction tr = new Tranzaction();
            Collection<Remission> remissions = rm.findAllCreditsActive();
            Iterator remiIt = remissions.iterator();
            while (remiIt.hasNext() && count < maxElements) {
                Remission remission = (Remission) remiIt.next();
                Remission lastCredit = new Remission();
                Collection<Remission> credits = rm.findAllCreditsByClient(remission.getIdClientFk().getIdPersonPk());
                for (Remission remission1 : credits) {
                    lastCredit = remission1;
                }
                for (Tranzaction tran : lastCredit.getTranzactionCollection()) {
                    tr = tran;
                }
                sortedList.add(new Remission(lastCredit.getRemissionDate(), remission.getLeftAmount(), remission.getIdClientFk()));
                remiIt.remove();
                count += 1;
            }
            //Ordenar la lista de acuerdo a las fechas
            Collections.sort(sortedList, new Comparator<Remission>() {
                @Override
                public int compare(Remission o1, Remission o2) {
                    return o1.getRemissionDate().compareTo(o2.getRemissionDate());
                }
            });
            for (Remission remission : sortedList) {
                series = new XYChart.Series();
                series.setName(remission.getIdClientFk().getTotalName());
                series.getData().add(new XYChart.Data(remission.getDate(), remission.getLeftAmount()));
                lineChart.getData().add(series);
            }
            chartsPane.add(bc, 0, 1);
            chartsPane.add(lineChart, 1, 1);
            //Parte de recarga de estadísitcas
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("MMM, yyyy");
            SimpleDateFormat compareDF = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
            SimpleDateFormat toCompareDF = new SimpleDateFormat("M-yyyy");
            //Se especifican las fechas inicial y final del mes actual
            String date1 = "01-" + toCompareDF.format(cal.getTime()) + " 00:00:00";
            String date2 = "31-" + toCompareDF.format(cal.getTime()) + " 23:59:00";
            Date dateInit = compareDF.parse(date1);
            Date dateFinish = compareDF.parse(date2);
            ReportsModel rpm = new ReportsModel();
            //Se recibe y se muestra el valor correspondiente a la sumatoria de ingresos
            float totalSelles = rpm.monthSellesTotal(dateInit, dateFinish);
            DecimalFormat format = new DecimalFormat("###,###.##");
            leftChart.setText(bundle.getString("moneyNotation") + format.format(totalSelles));
            leftChart2.setText(bundle.getString("loadTotal")+" "+ sdf.format(cal.getTime()));
            //Se recibe y se muestra el valor correspondiente al producto más vendido 
            Object[] obj = rpm.topSelledProduct(dateInit, dateFinish);
            centerChart.setText(obj[3].toString());
            centerChart2.setText((String) obj[1] +" "+bundle.getString("topSelled")+" "+ sdf.format(cal.getTime()));
            //Se recibe y se muestra el valor correspondiente a la sumatoria de egresos
            float totalExpenses = rpm.monthTotalExpenses(dateInit, dateFinish);
            rigthChart.setText(bundle.getString("moneyNotation") + format.format(totalExpenses));
            rigthChart2.setText(bundle.getString("uploadTotal")+" "+ sdf.format(cal.getTime()));
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            LogActions log = new LogActions();
            log.createEntry(String.valueOf(e.hashCode()), "Cargar dashboard", e.getMessage(), sw.toString(), sessionUser.toString());
            //e.printStackTrace();
        }
    }
    /*
        Método para actualizar los valores originales de los productos asociados
    public void updateProductOriginalVales() throws Exception {
        for (Product prod : actualProductValues) {
            for (Product pro : selectedProducts) {
                if (prod.getIdProductPk().compareTo(pro.getIdProductPk()) == 0) {
                    pro.setActualPrice(prod.getActualPrice());
                    pro.setCantidadSeleccionada(1);
                    pro.setProductDescription(prod.getProductDescription());
                    abs.updateFinal(pro);
                }
            }
        }
    }*/
    /*
     Método para ocultar las vistas de buscar personas, municipios y productos
     cuadno se presiona una tecla sobre
     @param: Evento que ejecuta la acción "onPress" (KeyEvent arg)
     */
    public void hideListsViewOnClick(MouseEvent arg) {
        hideListsView();
    }
    /*
     Método para ocultar las vistas de buscar personas, municipios y productos
     cuadno se presiona una tecla sobre
     @param: Evento que ejecuta la acción "onPress" (KeyEvent arg)
     */
    public void hideListsViewOnKey(KeyEvent arg) {
        hideListsView();
    }
    /*
     Método para ocultar las vistas de buscar personas, municipios y productos
     */
    public void hideListsView() {
        if (personList.isVisible()) {
            personList.setVisible(false);
            listsViewPanel.setVisible(false);
        }
        if (productList.isVisible()) {
            productList.setVisible(false);
            listsViewPanel.setVisible(false);
        }
    }
    /*
     Método para tomar el mes y año actual
     */
    public String getActualMonthYear() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        return sdf.format(cal.getTime());
    }
    /*
     Método para ocultar o mostrar los tooltip de ayuda
     */
    public void showHideTooltip() {
        if (!tlpHelp.isShowing()) {
            if (!helpButton.getStyleClass().contains("helpPressed")) {
                helpButton.getStyleClass().add("helpPressed");
            }
            tlpHelp.show(helpButton,
                    helpButton.getScene().getWindow().getX() + helpButton.getLayoutX() + helpButton.getWidth() - 10,
                    helpButton.getScene().getWindow().getY() + helpButton.getLayoutY() + helpButton.getHeight());
        } else {
            if (helpButton.getStyleClass().contains("helpPressed")) {
                helpButton.getStyleClass().remove("helpPressed");
            }
            tlpHelp.hide();
        }
    }
    /*
     Método para minimizar el modal iniciado sin guardar ninguna cambio, desde el boto cancelar
     @param: evento que ejecute la acción (onAction)
     */
    @FXML
    public void minimizeButtonAction(ActionEvent event) {
        Scene scene = mainPane.getScene();
        Stage stage = (Stage) scene.getWindow();
        stage.setIconified(true);
    }
    public void closeModal() {
        Scene scene = mainPane.getScene();
        Stage stage = (Stage) scene.getWindow();
        stage.close();
    }
}
