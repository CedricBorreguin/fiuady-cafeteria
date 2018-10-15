package com.borreguin.cafeteriapointofsale;

import com.borreguin.cafeteriapointofsale.DBHelper.DBConnection;
import com.borreguin.cafeteriapointofsale.models.Product;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;


public class Main extends Application {

    private static ImageView buildImage(String imgPath, boolean rotate) {
        Image i = new Image(imgPath);
        ImageView imageView = new ImageView();
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        if(rotate){
            imageView.setRotate(90);
        }
        imageView.setImage(i);
        return imageView;
    }

    ////////////////////////////////////////////////////
    //////////////      APPLICATION      ///////////////
    ////////////////////////////////////////////////////

    public static void main(String[] args) {
        try {
            DBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        launch(args);
    }

    private VBox root;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        root = new VBox();
        root.getStylesheets().add(getClass().getResource("cafeteria.css").toExternalForm());
        root.minHeight(720);
        root.minWidth(1280);

        Label mainTitle = createMainTitle();
        TabPane mainMenuTp = createMainMenu();

        root.getChildren().addAll(mainTitle, mainMenuTp);

        root.setVgrow(mainMenuTp, Priority.ALWAYS);

        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////

    private Label createMainTitle(){
        Label mainTitle = new Label("La Cafetería - FIUADY");
        mainTitle.prefWidthProperty().bind(root.widthProperty());
        mainTitle.setMinHeight(60);
        mainTitle.setId("main-title");

        return mainTitle;
    }

    private TabPane createMainMenu(){

        TabPane mainMenuTp = new TabPane();
        mainMenuTp.setSide(Side.LEFT);

        //////////

        Tab homeTab = new Tab();
        homeTab.setClosable(false);
        homeTab.setGraphic(buildImage("com/borreguin/cafeteriapointofsale/assets/main/homeTab_ic-01.png", false));
        homeTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if(homeTab.isSelected()){
                    homeTab.setGraphic(buildImage("com/borreguin/cafeteriapointofsale/assets/main/homeTab_ic-02.png", true));
                } else {
                    homeTab.setGraphic(buildImage("com/borreguin/cafeteriapointofsale/assets/main/homeTab_ic-01.png", true));
                }
            }
        });

        Tab mainTab = new Tab();
        mainTab.setClosable(false);
        mainTab.setGraphic(buildImage("com/borreguin/cafeteriapointofsale/assets/main/salesTab_ic-01.png", false));
        mainTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if(mainTab.isSelected()){
                    mainTab.setGraphic(buildImage("com/borreguin/cafeteriapointofsale/assets/main/salesTab_ic-02.png", true));
                } else {
                    mainTab.setGraphic(buildImage("com/borreguin/cafeteriapointofsale/assets/main/salesTab_ic-01.png", true));
                }
            }
        });

        //////////

        HBox mainTabContent = createMainTabContent();
        mainTab.setContent(mainTabContent);
        mainTabContent.prefHeightProperty().bind(mainMenuTp.heightProperty());

        //////////

        mainMenuTp.getTabs().addAll(homeTab, mainTab);
        mainMenuTp.prefWidthProperty().bind(root.widthProperty());

        return mainMenuTp;
    }

    ////////////////////////////////////////////////////
    ///////////////       MainTab       ////////////////
    ////////////////////////////////////////////////////

    private HBox createMainTabContent(){
        HBox salesTabContent = new HBox();
        salesTabContent.setPadding(new Insets(5,5,5,5));
        salesTabContent.setSpacing(5);

        VBox transactionLeftSection = createTransactionLeftSection();
        VBox transactionCenterSection = createTransactionCenterSection();
        VBox transactionRightSection = createTransactionRightSection();

        salesTabContent.widthProperty().addListener((obs, oldVal, newVal)->{

            double productSalesSectionWidth = (Double)newVal*0.4;
            double totalAndSpecialFoodSectionWidth = (Double)newVal*0.3;
            double activeOrdersSectionWidth = (Double)newVal*0.3;

            transactionLeftSection.setPrefWidth(productSalesSectionWidth);

            transactionCenterSection.setPrefWidth(totalAndSpecialFoodSectionWidth);

            transactionRightSection.setPrefWidth(activeOrdersSectionWidth);

        });

        salesTabContent.getChildren().addAll(transactionLeftSection, transactionCenterSection, transactionRightSection);

        return salesTabContent;
    }

    //////////////////////////////////////////////

    VBox receiptTable;

    private VBox createTransactionLeftSection(){

        VBox transactionLeftSection = new VBox();
        transactionLeftSection.setStyle("-fx-background-color: white;");
        transactionLeftSection.setSpacing(5);

        receiptTable = createReceiptTable();
        receiptTable.prefWidthProperty().bind(transactionLeftSection.widthProperty());

        VBox scanedProductInfoContainer = createScanedProductInfoContainer();
        scanedProductInfoContainer.prefWidthProperty().bind(transactionLeftSection.widthProperty());

        HBox receiptBtnsContainer = createReceiptBtnsContainer();
        receiptBtnsContainer.prefWidthProperty().bind(transactionLeftSection.widthProperty());

        transactionLeftSection.getChildren().addAll(receiptTable, scanedProductInfoContainer, receiptBtnsContainer);
        transactionLeftSection.setVgrow(receiptTable, Priority.ALWAYS);

        return transactionLeftSection;
    }

    private VBox productsListContainer;

    private ArrayList<Product> productsList;
    //private ArrayList<ScannedProduct> scannedProductsList;

    private VBox createReceiptTable(){
        VBox productsTableContainer = new VBox();
        productsTableContainer.setSpacing(2);
        productsTableContainer.setStyle("-fx-background-color: white;");

        HBox receiptTableHeader = new HBox();

        Label productNameHeader = new Label("Producto");
        productNameHeader.setId("table-header-1");

        Region region1 = new Region();
        region1.setStyle("-fx-background-color: #400000;");

        Region separator1 = new Region();
        separator1.setStyle("-fx-background-color: white;");
        separator1.setMinWidth(2);
        separator1.setMaxWidth(2);

        Label productQuantityHeader = new Label("Cantidad");
        productQuantityHeader.setId("table-header-1");

        Region separator2 = new Region();
        separator2.setStyle("-fx-background-color: white;");
        separator2.setMinWidth(2);
        separator2.setMaxWidth(2);

        Label productPriceHeader = new Label("Precio");
        productPriceHeader.setId("table-header-1");

        Region region2 = new Region();
        region2.setStyle("-fx-background-color: #400000;");
        region2.setMaxWidth(30);

        receiptTableHeader.getChildren().addAll(productNameHeader, region1, separator1,productQuantityHeader, separator2, productPriceHeader, region2);
        receiptTableHeader.prefWidthProperty().bind(productsTableContainer.widthProperty());
        receiptTableHeader.setHgrow(region1, Priority.ALWAYS);
        receiptTableHeader.setHgrow(region2, Priority.ALWAYS);

        productsListContainer = new VBox();
        productsListContainer.setStyle(
                "-fx-border-color: #400000;"+
                "-fx-border-width: 2px");

        productsList = new ArrayList<>();
        productsList.add(new Product(1, "Hola",34,1));

        //updateProductTableContent();

        productsTableContainer.getChildren().addAll(receiptTableHeader, productsListContainer);

        return productsTableContainer;
    }

    private void updateProductTableContent(){
        productsListContainer.getChildren().clear();

        /*
        for (ScannedProduct sp : scannedProductsList) {
            Product product = new Product(0, "",0,0,0);
            boolean productFinded = false;
            for(Product p: productsList){
                if(sp.getProductId()==p.getProductId()){
                    product=p;
                    productFinded = true;
                    break;
                }
            }
            if(!productFinded){
                return;
            }

            HBox productContainer = new HBox();

            Label productName = new Label(product.getName());
            productName.setId("table-content");
            productName.setPadding(new Insets(2,10,2,10));
            productName.prefWidthProperty().bind(productNameHeader.widthProperty());

            Region region1 = new Region();
            region1.setStyle("-fx-background-color: white;");

            Region separator1 = new Region();
            separator1.setStyle("-fx-background-color: #400000;");
            separator1.setMinWidth(3);
            separator1.setMaxWidth(3);

            Label productQuantity = new Label(Integer.toString(sp.getQuantity()));
            productQuantity.setId("table-content");
            productQuantity.setPadding(new Insets(2,15,2,10));
            productQuantity.prefWidthProperty().bind(productQuantityHeader.widthProperty());

            Region separator2 = new Region();
            separator2.setStyle("-fx-background-color: #400000;");
            separator2.setMinWidth(3);
            separator2.setMaxWidth(3);

            Label productPrice = new Label( "$" + product.getPrice() );
            productPrice.setId("table-content");
            productPrice.setPadding(new Insets(2,15,2,10));
            productPrice.prefWidthProperty().bind(productPriceHeader.widthProperty());

            productContainer.getChildren().addAll(productName, region1, separator1,productQuantity, separator2, productPrice);
            productContainer.prefWidthProperty().bind(productsTableContainer.widthProperty());
            productContainer.setHgrow(region1, Priority.ALWAYS);

            productsListContainer.getChildren().addAll(productContainer);
        }

        */

    }

    private Label scanedProductName;
    private Label scanedProductPrice;
    private TextField scannedProductIdField;

    private VBox createScanedProductInfoContainer(){

        VBox scanedProductInfoContainer = new VBox();
        scanedProductInfoContainer.setStyle("-fx-background-color: white;");
        scanedProductInfoContainer.setMinHeight(100);

        Label scanedProductHeader = new Label("Producto Escaneado:");
        scanedProductHeader.setStyle("-fx-background-color: #CD9D00;" +
                "-fx-text-fill: #800200;" +
                "-fx-font-family: Helvetica;" +
                "-fx-font-weight: bolder;" +
                "-fx-alignment: center;" +
                "-fx-font-size: 14;");

        scanedProductHeader.prefWidthProperty().bind(scanedProductInfoContainer.widthProperty());
        scanedProductHeader.setMinHeight(27);
        scanedProductHeader.setMaxHeight(27);

        Region horizontalDiv1 = new Region();
        horizontalDiv1.setStyle("-fx-background-color: #800200;");
        horizontalDiv1.setMinHeight(2);
        horizontalDiv1.setMaxHeight(2);

        HBox scanedProductInfo = new HBox();
        scanedProductInfo.setStyle("-fx-background-color: white;");

        scanedProductName = new Label("[...]");
        scanedProductName.setId("scanned-product-info");
        scanedProductName.setStyle("-fx-text-alignment: left;");
        scanedProductName.setMinHeight(35);
        scanedProductName.setMaxHeight(35);
        scanedProductName.setPadding(new Insets(5,10,5,10));

        Region region1 = new Region();
        region1.setStyle("-fx-background-color: #CD9D00;");

        Region verticalLine = new Region();
        verticalLine.setStyle("-fx-background-color: #800200;");
        verticalLine.setMinWidth(2);
        verticalLine.setMaxWidth(2);

        scanedProductPrice = new Label("[...]");
        scanedProductPrice.setId("scanned-product-info");
        scanedProductPrice.setStyle("-fx-text-alignment: right;");
        scanedProductPrice.setMinHeight(35);
        scanedProductPrice.setMaxHeight(35);
        scanedProductPrice.setPadding(new Insets(5,10,5,10));

        Region horizontalDiv2 = new Region();
        horizontalDiv2.setStyle("-fx-background-color: #800200;");
        horizontalDiv2.setMinHeight(2);
        horizontalDiv2.setMaxHeight(2);

        StackPane productIdContainer = new StackPane();
        productIdContainer.setStyle("-fx-background-color: #CD9D00;");
        productIdContainer.setPadding(new Insets(5,10,5,10));

        scannedProductIdField = new TextField();
        scannedProductIdField.setStyle("-fx-text-alignment: right;" +
                "-fx-text-fill: black;" +
                "-fx-font-family: Helvetica;" +
                "-fx-font-weight: bolder;" +
                "-fx-background-color: white;");
        scannedProductIdField.setMinHeight(20);
        scannedProductIdField.setMaxHeight(20);

        scannedProductIdField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                scannedProductIdField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        scannedProductIdField.setOnAction(event -> {
            long scannedProductId = 0;
            if(!scannedProductIdField.getText().isEmpty()){
                scannedProductId = Long.parseLong(scannedProductIdField.getText());
            }
            scannedProductIdField.setText("");
            System.out.println(scannedProductId);
        });

        productIdContainer.getChildren().addAll(scannedProductIdField);

        scanedProductInfo.getChildren().addAll(scanedProductName, region1, verticalLine,scanedProductPrice);
        scanedProductInfo.setHgrow(region1, Priority.ALWAYS);

        scanedProductInfo.prefWidthProperty().bind(scanedProductInfoContainer.widthProperty());

        scanedProductInfoContainer.getChildren().addAll(scanedProductHeader, horizontalDiv1, scanedProductInfo, horizontalDiv2, productIdContainer);

        return scanedProductInfoContainer;
    }

    private void scanProduct(){

    }

    private HBox createReceiptBtnsContainer(){
        HBox scanedProductContainer = new HBox();
        scanedProductContainer.setStyle("-fx-background-color: white;");
        scanedProductContainer.setMinHeight(40);
        scanedProductContainer.setSpacing(10);

        Button finishReceiptBtn = new Button("Terminar");
        finishReceiptBtn.setId("positive-btn");
        finishReceiptBtn.prefHeightProperty().bind(scanedProductContainer.heightProperty());

        Button specialItemBtn = new Button("Producto Especial");
        specialItemBtn.setId("positive-btn");
        specialItemBtn.prefHeightProperty().bind(scanedProductContainer.heightProperty());

        Button cancelBtn = new Button("Cancelar");
        cancelBtn.setId("negative-btn");
        cancelBtn.prefHeightProperty().bind(scanedProductContainer.heightProperty());

        scanedProductContainer.getChildren().addAll(finishReceiptBtn, specialItemBtn, cancelBtn);
        scanedProductContainer.setHgrow(finishReceiptBtn, Priority.ALWAYS);
        finishReceiptBtn.setMaxWidth(Double.MAX_VALUE);

        scanedProductContainer.setHgrow(specialItemBtn, Priority.ALWAYS);
        specialItemBtn.setMaxWidth(Double.MAX_VALUE);

        scanedProductContainer.setHgrow(cancelBtn, Priority.ALWAYS);
        cancelBtn.setMaxWidth(Double.MAX_VALUE);

        return scanedProductContainer;
    }

    //////////////////////////////////////////////

    private VBox createTransactionCenterSection(){
        VBox totalAndSpecialFoodSection = new VBox();

        totalAndSpecialFoodSection.setSpacing(5);

        VBox totalContainer = createTotalContainer();
        ScrollPane preparedFoodScrollContainer = createPreparedFoodListScrollContainer();

        //totalAndSpecialFoodSection.setStyle("-fx-background-color: blue;");

        totalAndSpecialFoodSection.getChildren().addAll(totalContainer, preparedFoodScrollContainer);
        totalAndSpecialFoodSection.setVgrow(preparedFoodScrollContainer, Priority.ALWAYS);

        return totalAndSpecialFoodSection;
    }

    private Label total;

    private VBox createTotalContainer(){
        VBox totalContainer = new VBox();
        totalContainer.setSpacing(5);
        totalContainer.setPadding(new Insets(10,0,10,0));
        totalContainer.setStyle("-fx-background-color: #400000;");

        Label totalTitle = new Label("Total:");
        total = new Label("$50.00");

        totalTitle.setStyle("-fx-background-color: #400000;" +
                "-fx-font-family: Helvetica;"+
                "-fx-text-fill: white;"+
                "-fx-alignment: center;"+
                "-fx-font-weight: bold;" +
                "-fx-font-size: 28;");

        total.setStyle("-fx-background-color: #400000;" +
                "-fx-font-family: Helvetica;"+
                "-fx-text-fill: white;"+
                "-fx-alignment: center;"+
                "-fx-font-weight: bold;" +
                "-fx-font-size: 46;");

        totalContainer.getChildren().addAll(totalTitle, total);
        totalTitle.prefWidthProperty().bind(totalContainer.widthProperty());
        total.prefWidthProperty().bind(totalContainer.widthProperty());

        return totalContainer;
    }

    private ArrayList<Product> preparedFoodList;
    private VBox preparedFoodContainer;

    private ScrollPane createPreparedFoodListScrollContainer(){
        ScrollPane preparedFoodScrollContainer = new ScrollPane();

        preparedFoodContainer = new VBox();
        preparedFoodContainer.setSpacing(5);
        preparedFoodContainer.setStyle("-fx-background-color: white");
        preparedFoodContainer.prefWidthProperty().bind(preparedFoodScrollContainer.widthProperty());

        /*

        preparedFoodList = new ArrayList<>();

        preparedFoodList.add(new Product(2,"Torta Alemana",35,10, 2));
        preparedFoodList.add(new Product(3,"Empanizado C.",35,10, 2));
        preparedFoodList.add(new Product(3,"Empanizado 1/2",35,10, 2));
        preparedFoodList.add(new Product(3,"Empanizado 1/2",35,10, 2));
        preparedFoodList.add(new Product(3,"Empanizado 1/2",35,10, 2));
        preparedFoodList.add(new Product(3,"Empanizado 1/2",35,10, 2));
        preparedFoodList.add(new Product(3,"Empanizado 1/2",35,10, 2));
        preparedFoodList.add(new Product(3,"Empanizado 1/2",35,10, 2));
        preparedFoodList.add(new Product(3,"Empanizado 1/2",35,10, 2));
        preparedFoodList.add(new Product(3,"Empanizado 1/2",35,10, 2));
        preparedFoodList.add(new Product(3,"Empanizado 1/2",35,10, 2));
        */

        updatePreparedFoodContent();

        preparedFoodScrollContainer.setContent(preparedFoodContainer);

        return preparedFoodScrollContainer;
    }

    private void updatePreparedFoodContent(){

        /*
        for(Product p: preparedFoodList){
            Button productName = new Button(p.getName());

            productName.prefWidthProperty().bind(preparedFoodContainer.widthProperty());
            productName.setMinHeight(40);
            productName.setStyle("-fx-text-fill: white;" +
                    "-fx-background-color: #008080;" +
                    "-fx-font-family: Helvetica;" +
                    "-fx-font-weight: bold;" +
                    "-fx-alignment: center;" +
                    "-fx-font-size: 20;" +
                    "-fx-padding: 15px;");

            preparedFoodContainer.getChildren().addAll(productName);

        }
        */
    }

    //////////////////////////////////////////////

    private VBox createTransactionRightSection(){
        VBox activeOrdersSection = new VBox();

        activeOrdersSection.setStyle("-fx-background-color: yellow;");

        return activeOrdersSection;
    }


    ////////////////////////////////////////////////////
    ///////////////       Home Tab       ///////////////
    ////////////////////////////////////////////////////

}
