package com.borreguin.cafeteriapointofsale;

import com.borreguin.cafeteriapointofsale.models.Product;
import javafx.application.Application;
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


        Tab salesTab = new Tab();
        salesTab.setClosable(false);
        salesTab.setGraphic(buildImage("com/borreguin/cafeteriapointofsale/assets/main/salesTab_ic-01.png", false));
        salesTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if(salesTab.isSelected()){
                    salesTab.setGraphic(buildImage("com/borreguin/cafeteriapointofsale/assets/main/salesTab_ic-02.png", true));
                } else {
                    salesTab.setGraphic(buildImage("com/borreguin/cafeteriapointofsale/assets/main/salesTab_ic-01.png", true));
                }
            }
        });

        HBox salesTabContent = createMainTabContent();
        salesTab.setContent(salesTabContent);
        salesTabContent.prefHeightProperty().bind(mainMenuTp.heightProperty());

        mainMenuTp.getTabs().addAll(homeTab, salesTab);
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

        VBox productSalesSection = transactionLeftSection();
        VBox totalAndSpecialFoodSection = transactionCenterSection();
        VBox activeOrdersSection = transactionRightSection();

        salesTabContent.widthProperty().addListener((obs, oldVal, newVal)->{

            double productSalesSectionWidth = (Double)newVal*0.4;
            double totalAndSpecialFoodSectionWidth = (Double)newVal*0.3;
            double activeOrdersSectionWidth = (Double)newVal*0.3;

            productSalesSection.setPrefWidth(productSalesSectionWidth);

            totalAndSpecialFoodSection.setPrefWidth(totalAndSpecialFoodSectionWidth);

            activeOrdersSection.setPrefWidth(totalAndSpecialFoodSectionWidth);

        });

        salesTabContent.getChildren().addAll(productSalesSection, totalAndSpecialFoodSection, activeOrdersSection);

        return salesTabContent;
    }

    //////////////////////////////////////////////

    private VBox transactionLeftSection(){
        VBox productSalesSection = new VBox();
        productSalesSection.setStyle("-fx-background-color: white;");
        productSalesSection.setSpacing(5);

        VBox productList = createProductsTableContainer();
        productList.prefWidthProperty().bind(productSalesSection.widthProperty());

        VBox scanedProductInfoContainer = createScanedProductInfoContainer();
        scanedProductInfoContainer.prefWidthProperty().bind(productSalesSection.widthProperty());

        HBox receiptControlsContainer = createReceiptControlsContainer();
        receiptControlsContainer.prefWidthProperty().bind(productSalesSection.widthProperty());

        productSalesSection.getChildren().addAll(productList, scanedProductInfoContainer, receiptControlsContainer);
        productSalesSection.setVgrow(productList, Priority.ALWAYS);

        return productSalesSection;
    }

    private VBox productsTableContainer;

    private Label productNameHeader;
    private Label productQuantityHeader;
    private Label productPriceHeader;
    private VBox productsListContent;

    private ArrayList<Product> productsList;
    //private ArrayList<ScannedProduct> scannedProductsList;

    private VBox createProductsTableContainer(){
        productsTableContainer = new VBox();
        productsTableContainer.setSpacing(2);
        productsTableContainer.setStyle("-fx-background-color: white;");

        HBox productTableHeader = new HBox();

        productNameHeader = new Label("Producto");
        productNameHeader.setId("table-header-1");

        Region region1 = new Region();
        region1.setStyle("-fx-background-color: #400000;");

        Region separator1 = new Region();
        separator1.setStyle("-fx-background-color: white;");
        separator1.setMinWidth(2);
        separator1.setMaxWidth(2);

        productQuantityHeader = new Label("Cantidad");
        productQuantityHeader.setId("table-header-1");

        Region separator2 = new Region();
        separator2.setStyle("-fx-background-color: white;");
        separator2.setMinWidth(2);
        separator2.setMaxWidth(2);

        productPriceHeader = new Label("Precio");
        productPriceHeader.setId("table-header-1");

        productTableHeader.getChildren().addAll(productNameHeader, region1, separator1,productQuantityHeader, separator2, productPriceHeader);
        productTableHeader.prefWidthProperty().bind(productsTableContainer.widthProperty());
        productTableHeader.setHgrow(region1, Priority.ALWAYS);

        productsListContent = new VBox();
        productsListContent.setStyle("-fx-border-color: #400000;"+
                "-fx-border-width: 2px");


        productsList = new ArrayList<>();
        productsList.add(new Product(1, "Hola",34,45,0));
        /*
        scannedProductsList = new ArrayList<>();
        scannedProductsList.add(new ScannedProduct(1,2));
        */

        updateProductTableContent();

        productsTableContainer.getChildren().addAll(productTableHeader, productsListContent);

        return productsTableContainer;
    }

    private Label scanedProductName;
    private Label scanedProductPrice;
    private TextField scannedProductId;

    private VBox createScanedProductInfoContainer(){
        VBox scanedProductInfoContainer = new VBox();
        scanedProductInfoContainer.setStyle("-fx-background-color: white;");
        scanedProductInfoContainer.setMinHeight(100);

        Label scanedProductTitle = new Label("Producto Escaneado:");
        scanedProductTitle.setStyle("-fx-background-color: #CD9D00;" +
                "-fx-text-fill: #800200;" +
                "-fx-font-family: Helvetica;" +
                "-fx-font-weight: bolder;" +
                "-fx-alignment: center;" +
                "-fx-font-size: 16;");

        scanedProductTitle.prefWidthProperty().bind(scanedProductInfoContainer.widthProperty());
        scanedProductTitle.setMinHeight(27);
        scanedProductTitle.setMaxHeight(27);

        Region horizontalDiv1 = new Region();
        horizontalDiv1.setStyle("-fx-background-color: #800200;");
        horizontalDiv1.setMinHeight(2);
        horizontalDiv1.setMaxHeight(2);

        HBox scanedProductInfo = new HBox();
        scanedProductInfo.setStyle("-fx-background-color: white;");

        scanedProductName = new Label("Gansito");
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

        scanedProductPrice = new Label("$15.00");
        scanedProductPrice.setId("scanned-product-info");
        scanedProductPrice.setStyle("-fx-text-alignment: right;");
        scanedProductPrice.setMinHeight(35);
        scanedProductPrice.setMaxHeight(35);
        scanedProductPrice.setPadding(new Insets(5,10,5,10));

        Region horizontalDiv2 = new Region();
        horizontalDiv2.setStyle("-fx-background-color: #800200;");
        horizontalDiv2.setMinHeight(2);
        horizontalDiv2.setMaxHeight(2);

        StackPane productIdTfContainer = new StackPane();
        productIdTfContainer.setStyle("-fx-background-color: #CD9D00;");
        productIdTfContainer.setPadding(new Insets(5,10,5,10));

        scannedProductId = new TextField();
        scannedProductId.setStyle("-fx-text-alignment: right;" +
                "-fx-text-fill: black;" +
                "-fx-font-family: Helvetica;" +
                "-fx-font-weight: bolder;" +
                "-fx-background-color: white;");
        scannedProductId.setMinHeight(20);
        scannedProductId.setMaxHeight(20);

        productIdTfContainer.getChildren().addAll(scannedProductId);

        scanedProductInfo.getChildren().addAll(scanedProductName, region1, verticalLine,scanedProductPrice);
        scanedProductInfo.setHgrow(region1, Priority.ALWAYS);

        scanedProductInfo.prefWidthProperty().bind(scanedProductInfoContainer.widthProperty());

        scanedProductInfoContainer.getChildren().addAll(scanedProductTitle, horizontalDiv1, scanedProductInfo, horizontalDiv2, productIdTfContainer);


        return scanedProductInfoContainer;
    }

    private HBox createReceiptControlsContainer(){
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

    private void updateProductTableContent(){
        productsListContent.getChildren().clear();

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

            productsListContent.getChildren().addAll(productContainer);
        }

        */

    }

    //////////////////////////////////////////////

    private VBox transactionCenterSection(){
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

        updatePreparedFoodContent();

        preparedFoodScrollContainer.setContent(preparedFoodContainer);

        return preparedFoodScrollContainer;
    }

    private void updatePreparedFoodContent(){

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
    }

    //////////////////////////////////////////////

    private VBox transactionRightSection(){
        VBox activeOrdersSection = new VBox();

        activeOrdersSection.setStyle("-fx-background-color: yellow;");

        return activeOrdersSection;
    }


    ////////////////////////////////////////////////////
    ///////////////       Home Tab       ///////////////
    ////////////////////////////////////////////////////

}
