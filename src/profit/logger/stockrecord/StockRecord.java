package profit.logger.stockrecord;

import java.sql.*;
import java.util.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.*;

public class StockRecord{

    Stage windowPick;
    Stage windowView;
    Stage MainMenu;
    Scene scenePick;
    Scene sceneView;
    VBox content = new VBox(5);
    VBox container = new VBox(20);
    Connection connection, newConnection;
    String stockType, currentUser, dbName;
    ScrollPane list;
    TableView<Stock> table;
    ObservableList<Stock> stock;
    Button deleteButton;
    int category = 0, id, newQtyComputed, currentQtyAvailable; // category: 1 means pieces; 2 means carton
   
    Number currentTypeSelected;
    Map<String, Integer> identifier;
    Stock selectedStockObj;
    
    // Class Constructor
    public StockRecord(){
    
        try{
            connection = DriverManager.getConnection("jdbc:mysql://LocalHost:3306/profitlogger", "root", "root");
            newConnection = DriverManager.getConnection("jdbc:mysql://LocalHost:3306/profitlogger", "root", "root");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void displayPick(String currentUser) {
        
        this.currentUser = currentUser;
        
        //Stock Select Window
        windowPick = new Stage();
        windowPick.setTitle("Select Stock");
        windowPick.setScene(stockPick());
        windowPick.sizeToScene();
        windowPick.resizableProperty().setValue(Boolean.FALSE);
        windowPick.show();
        
        //BACK button navig-to Window
        MainMenu = new Stage();
        MainMenu.setTitle("BisMie Store, Ibadan: Stock and Accounting Record System - Main Menu");
        MainMenu.setMaximized(true);
        
        //Stock Type-Category Window
        windowView = new Stage();
        windowView.setMaximized(true);

    }
    
    private void displayView(String stockType, String dbName){
        windowView.setTitle(stockType + " Stock Record");
        windowView.setScene(stockView(stockType, dbName));
        windowView.show();
    }
  
    private Scene stockPick(){
    
                Text pageDesc = new Text("Select the stock type to check its record"); 
                        pageDesc.setFont(Font.font("ARIAL BLACK", FontWeight.BOLD, 30));
                        pageDesc.setFill(Color.GRAY);

                        HBox description = new HBox();
                             description.setAlignment(Pos.CENTER);
                             description.getChildren().add(pageDesc);

        //menu icons   
                             
        //Button Style       
          String buttonStyle = "-fx-focus-color: transparent;";
                             
                //Biscuit
          
                //carton
                Button biscuitButton = new Button();
                    Image image3 = new Image(getClass().getResourceAsStream("OAU_LOGO.png"));
                    biscuitButton.setStyle(buttonStyle);
                biscuitButton.setGraphic(new ImageView(image3));
                biscuitButton.setOnAction(e -> {
                    stockType = "Biscuit";
                    dbName = "biscuit";
                    category = 2;
                                        
                    displayView(stockType, dbName);
                    windowPick.hide();
                });

                
                
                //Sweet
                
                //pieces
                Button sweetButtonPieces = new Button();
        //          Image image4 = new Image(getClass().getResourceAsStream("EditBase.png"));
                sweetButtonPieces.setGraphic(new ImageView(image3));
                sweetButtonPieces.setStyle(buttonStyle);
                sweetButtonPieces.setOnAction(e -> {

                    stockType = "Sweet - Pieces";
                    dbName = "sweet_pieces";
                    category = 1;
                    
                    displayView(stockType, dbName);
                    windowPick.hide();
                });
                
                //carton
                Button sweetButtonCarton = new Button();
        //          Image image4 = new Image(getClass().getResourceAsStream("EditBase.png"));
                sweetButtonCarton.setGraphic(new ImageView(image3));
                sweetButtonCarton.setStyle(buttonStyle);
                sweetButtonCarton.setOnAction(e -> {

                    stockType = "Sweet - Cartons";
                    dbName = "sweet_cartons";
                    category = 2;
                    
                    displayView(stockType, dbName);
                    windowPick.hide();
                });
                
                
                
                //Provision

                //pieces
                Button provisionButtonPieces = new Button();
        //          Image image5 = new Image(getClass().getResourceAsStream("GenerateTable.png"));
                provisionButtonPieces.setGraphic(new ImageView(image3));
                provisionButtonPieces.setStyle(buttonStyle);
                provisionButtonPieces.setOnAction(e -> {
                    
                    stockType = "Provision - Pieces";
                    dbName = "provision_pieces";
                    category = 1;
                    
                    displayView(stockType, dbName);
                    windowPick.hide();
                });
                
                
                //carton
                Button provisionButtonCarton = new Button();
        //          Image image5 = new Image(getClass().getResourceAsStream("GenerateTable.png"));
                provisionButtonCarton.setGraphic(new ImageView(image3));
                provisionButtonCarton.setStyle(buttonStyle);
                provisionButtonCarton.setOnAction(e -> {
                    
                    stockType = "Provision - Cartons";
                    dbName = "provision_cartons";
                    category = 2;
                    
                    displayView(stockType, dbName);
                    windowPick.hide();
                });

                
                
                //Soap
                
                //pieces
                Button soapButtonPieces = new Button();
        //          Image image6 = new Image(getClass().getResourceAsStream("ViewTable.png"));
                soapButtonPieces.setGraphic(new ImageView(image3));
                soapButtonPieces.setStyle(buttonStyle);
                soapButtonPieces.setOnAction(e -> {

                     stockType = "Soap - Pieces";
                     dbName = "soap_pieces";
                     category = 1;
                     
                     displayView(stockType, dbName);
                     windowPick.hide();
                });
                
                //carton
                Button soapButtonCarton = new Button();
        //          Image image6 = new Image(getClass().getResourceAsStream("ViewTable.png"));
                soapButtonCarton.setGraphic(new ImageView(image3));
                soapButtonCarton.setStyle(buttonStyle);
                soapButtonCarton.setOnAction(e -> {

                     stockType = "Soap - Carton";
                     dbName = "soap_cartons";
                     category = 2;
                     
                     displayView(stockType, dbName);
                     windowPick.hide();
                });
       
       //Icon Merge
                HBox iconHBox = new HBox(30);
                iconHBox.getChildren().addAll(biscuitButton, sweetButtonPieces, sweetButtonCarton, provisionButtonPieces, provisionButtonCarton, soapButtonPieces, soapButtonCarton);
                iconHBox.setAlignment(Pos.CENTER); 
                
                
       //Back button
        Button closeButton = new Button("BACK");
        closeButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 13));
        closeButton.setDefaultButton(true);
            closeButton.setOnAction(ex -> {
            windowPick.hide();
            
//            profit.logger.mainMenu obj = new profit.logger.mainMenu(currentUser);
//                                MainMenu.setScene(obj.display());
//                                MainMenu.show();
        });
           
        HBox closeButtonHolder = new HBox(60);
             closeButtonHolder.getChildren().add(closeButton);
             closeButtonHolder.setAlignment(Pos.CENTER); 
        

        VBox vbox = new VBox(40);
             vbox.getChildren().addAll(pageDesc, iconHBox, closeButtonHolder);
             vbox.setAlignment(Pos.CENTER);
             vbox.setPadding(new Insets(15, 15, 15, 15));
             
        ScrollPane pane = new ScrollPane(vbox);
             pane.setPadding(new Insets(10,10,10,10));
        
        return new Scene(pane, 700, 330);
       
        }
    
    private Scene stockView(String type, String dataDB){
        
        //Header
        Text description = new Text();
             description.setText(String.format("Available Stock List (%s)", type));
             description.setFont(Font.font("Georgia", FontWeight.EXTRA_BOLD, 30));
             description.setFill(Color.MIDNIGHTBLUE);
             
             HBox header = new HBox();
                  header.getChildren().add(description);
                  header.setAlignment(Pos.TOP_LEFT);
                  header.setPadding(new Insets(10,10,10,10));
                  
                  
        //Control Buttons
             //Partition 1
                Button addStock = new Button("ADD/TOP-UP STOCK");
                       addStock.setFont(Font.font("Times New Roman", FontWeight.BOLD, 13));
                       addStock.setOnAction(e ->{
                           addStock(dbName, category);
                       });
               
               HBox hbox1 = new HBox();
                    hbox1.getChildren().add(addStock);
                    
        //Partition 2
        /**
                Button updateButton = new Button("UPDATE");
                       updateButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 13));
                       updateButton.setOnAction(e ->{
   
                            table.setRowFactory(t -> {

                                TableRow<Stock> row = new TableRow<>();
                                     row.setOnMouseClicked(event -> {
                                         if(event.getClickCount()==2 && !(row.isEmpty()))
                                             System.out.println(table.getSelectionModel().getSelectedIndex()
                                                               +" <-tbl row, idx in items-> "
                                                               +stock.indexOf(table.getSelectionModel().getSelectedItem()));
                                     });
                                return row;
                            });

                       });
                              
                HBox hbox2 = new HBox(15);
                    hbox2.getChildren().addAll(updateButton, deleteButton);            
        */
                    
                       deleteButton = new Button("DELETE");
                       deleteButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 13));
        //               deleteButton.setOnAction(e ->{});
                       
            //Partition 3
        Button refreshButton = new Button("REFRESH LIST");
               refreshButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 13));
               refreshButton.setOnAction(e ->{
                       getTableValue(dbName);
                       table.setItems(stock);
               });
               
               HBox hbox3 = new HBox();
                    hbox3.getChildren().add(refreshButton);
        
        Button backButton = new Button("BACK");
               backButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 13));
               backButton.setOnAction(e ->{
               
                   windowPick.show();
                   windowView.hide();
                   stock.clear(); //empty the Observable List
                   selectedStockObj=null; //clear any selected table object
               });
                    
        //Buttons Container
        HBox baseButtons = new HBox(60);
             baseButtons.getChildren().addAll(hbox1, deleteButton, hbox3, backButton);
             baseButtons.setAlignment(Pos.CENTER);
             baseButtons.setPadding(new Insets(10,10,10,10));
         
             //populate the table with the database value
//             getTabl  eValue(dataDB);    
             
            //populate the scrollpane with the database value
             populateScrollPane(dataDB);  
             
        //List
        list = new ScrollPane(table);
        list.setPadding(new Insets(10,10,10,10));
//        list.autosize();
        list.setFitToHeight(true);
        list.setFitToWidth(true);
      
        
        return new Scene(new BorderPane(list, header, null, baseButtons, null), 900, 600);
    }

    private void populateScrollPane(String source) {

        //create the Table Columns
            
            //id column
            TableColumn<Stock, Integer> idColumn = new TableColumn("Id");
                idColumn.setMaxWidth(100);
                idColumn.setResizable(false);
                idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
                //Align the text at the centre
                
            //name column
            TableColumn<Stock, String> nameColumn = new TableColumn("Stock Name");
                nameColumn.setMinWidth(400);
                nameColumn.setResizable(true);
                nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                //Align the text at the centre
                
             //size column
            TableColumn<Stock, String> sizeColumn = new TableColumn("Stock Size");
                sizeColumn.setMinWidth(150);
                sizeColumn.setResizable(false);
                sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
                //Align the text at the centre
                
            //Current Selling Price column
            TableColumn<Stock, Double> currentSellingPriceColumn = new TableColumn("Current Selling Price");
                currentSellingPriceColumn.setMinWidth(200);
                currentSellingPriceColumn.setResizable(false);
                currentSellingPriceColumn.setCellValueFactory(new PropertyValueFactory<>("currentSellingPrice"));
                //Align the text at the centre
                
            //Quantity Available column
            TableColumn<Stock, Integer> qtyAvailableColumn = new TableColumn("Quantity Available");
                qtyAvailableColumn.setMinWidth(200);
                qtyAvailableColumn.setResizable(false);
                qtyAvailableColumn.setCellValueFactory(new PropertyValueFactory<>("qtyAvailable"));
                //Align the text at the centre
                
             //Last Updated column
            TableColumn<Stock, String> lastUpdatedColumn = new TableColumn("Last Updated");
                lastUpdatedColumn.setMinWidth(250);
                lastUpdatedColumn.setResizable(false);
                lastUpdatedColumn.setCellValueFactory(new PropertyValueFactory<>("lastUpdated"));
            
                       
                //create the table
                table = new TableView<>(); 
                table.setItems(getTableValue(source));
                table.getColumns().addAll(idColumn, nameColumn, sizeColumn, currentSellingPriceColumn, qtyAvailableColumn, lastUpdatedColumn);
                table.setStyle("-fx-font-size:14");
                table.editableProperty();
                table.setTableMenuButtonVisible(true);
                
                table.setRowFactory(t -> {
                       
                           TableRow<Stock> row = new TableRow<>();

                                row.setOnMouseClicked(event -> {
                                    
                                    if(event.getClickCount()==2 && !(row.isEmpty())){
                                        int stockListID = stock.indexOf(table.getSelectionModel().getSelectedItem());
                                        Stock selectedStockObj = stock.get(stockListID);
//                                        System.out.printf("%d  %s  %s  %.2f  %d  %s\n",selectedStockObj.getID(),selectedStockObj.getName(),selectedStockObj.getSize(),selectedStockObj.getCurrentSellingPrice(),selectedStockObj.getQtyAvailable(), selectedStockObj.getLastUpdated());
                                            String oldName = selectedStockObj.getName();
                                            String oldSize = selectedStockObj.getSize();
                                            int oldQty = selectedStockObj.getQtyAvailable();
                                            double oldPrice = selectedStockObj.getCurrentSellingPrice();
                                           
                                        updateStockRecord(oldName, oldSize, oldQty, oldPrice);
                                    }
                                    
                                    else if(event.getClickCount()==1 && !(row.isEmpty())){
                                        int stockListID = stock.indexOf(table.getSelectionModel().getSelectedItem());
                                        selectedStockObj = stock.get(stockListID);
                                        
                                        System.out.println(selectedStockObj.getName());
                                                
                                        deleteButton.setOnAction(e -> {
                                            
                                            if(selectedStockObj!=null){
                                                ButtonType Yes = new ButtonType("YES", ButtonBar.ButtonData.OK_DONE);
                                                ButtonType No = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

                                                Alert alert = new Alert(AlertType.CONFIRMATION, "Do you want to delete this stock detail?", Yes, No);
                                                      alert.setTitle("Delete Confirmation");
                                                      alert.setHeaderText(null);

                                               Optional<ButtonType> result = alert.showAndWait();

                                                    if(result.isPresent() && result.get()==Yes){
                                                        System.out.println(selectedStockObj.getName() + " - Button clicked");
                                                        deleteStockRecord(selectedStockObj);
                                                        table.getItems().remove(selectedStockObj);
                                                        selectedStockObj=null;
                                                    }
                                            }
                                            
                                        });
                                        
                                    }
                                });
                           return row;
                       });
                
    }

    private ObservableList<Stock> getTableValue(String source) {
        
        stock = FXCollections.observableArrayList();
        
        id = 0;
        String name;
        String size;
//        String sizeFinal;
//        int sizeInitial;
        double currentSellingPrice;
        int qtyAvailable;
        String lastUpdated;
   
        try{
            Statement st = connection.createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM profitlogger." + source);            

            while(result.next()){
            
                //DB values
                id = id + 1;
                
                name = result.getString("name");
                
                size = result.getString("size");
                
                    currentSellingPrice = result.getDouble("currentSellingPrice");
                    String currentSellingPriceConverted = String.format("%.2f", currentSellingPrice);
                
                qtyAvailable = result.getInt("qtyAvailable");
                
                lastUpdated = result.getString("lastUpdated").substring(0, 19);
                
                //Add class Stock instance to the Observable List
//                stock.add(new Stock(id, name, sizeFinal, currentSellingPrice, qtyAvailable, lastUpdated));
                stock.add(new Stock(id, name, size, currentSellingPriceConverted, qtyAvailable, lastUpdated));
            }
            
        }catch(SQLException e){}
        
        
        return stock;
    }
    
    private void addStock(String dbName, int category){
        
//        Create window
             Stage windowAddStock = new Stage();
                   windowAddStock.setTitle("Stock Addition");
             
        //Scene Title
                Text title = new Text("Stock Addition");
                    title.setFont(Font.font("Georgia", FontWeight.BOLD, 30));
                    title.setFill(Color.DARKBLUE);
                    
                    //Layout
                    HBox titleBox = new HBox();
                         titleBox.getChildren().add(title);
                         titleBox.setAlignment(Pos.CENTER);
                 
        //Type Field
        Button addStock = new Button("ADD STOCK");
        
        //Final container
        VBox layoutAddition = new VBox(20);
        
        
        //All DB Stock
        ChoiceBox<String> stockSelect = new ChoiceBox<>(); 
            identifier = new HashMap<>(); // this hashMap maps stockName to its dbID
            try{
                Statement st = connection.createStatement();
                ResultSet result = st.executeQuery("SELECT * FROM profitlogger."+dbName);
                 
                          stockSelect.getItems().add("Select an existing stock");
                        
                //iterate through the Stock Name column only
                while(result.next()){ 
                    String currentStock = String.format("%s (%s)",result.getString("name"), result.getString("size"));
                    stockSelect.getItems().add(currentStock);
                    
                        //add data to the hashmap
                        identifier.put(currentStock, new Integer(result.getInt("id")));
                }
                    stockSelect.setValue("Select an existing stock");
            }catch(SQLException e){
            }
                    
                    
       //Stock Name Field
        TextField stockName = new TextField();
            stockName.setPromptText("Enter stock name");
//            stockName.setMaxWidth(100);          
        
        //Variations Field
        ChoiceBox<String> variation = new ChoiceBox<>(); 
            variation.getItems().addAll("No variation", "Small size", "Medium size", "Big size", "Extra Big size");
            variation.setValue("No variation");
//            selectCategory.setMinWidth(200);
                       
                    
        //Type Field
        ChoiceBox<String> typeSelect = new ChoiceBox<>(); 
            typeSelect.getItems().addAll("New addition", "Already added");
            typeSelect.setValue("New addition");
//            typeSelect.setMaxWidth(200);
            currentTypeSelected = typeSelect.getSelectionModel().getSelectedIndex();
            typeSelect.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> value, Number initialIndex, Number finalIndex) -> {
          
                    if(((Integer)finalIndex)==0){
                        title.setText("Stock Addition");
                        windowAddStock.setTitle("Stock Addition");
                        addStock.setText("ADD STOCK");
                        
                        //new-name-input OR old-name-select
                        layoutAddition.getChildren().remove(2);
                        layoutAddition.getChildren().add(2,stockName);
                        
                       //Enable Variation option
                         variation.setDisable(false);
                    }
                    
                    else if(((Integer)finalIndex)==1){
                        title.setText("Stock Top-up");
                        windowAddStock.setTitle("Stock Top-up");
                        addStock.setText("TOP-UP STOCK");
                        
                        //new-name-input OR old-name-select
                        layoutAddition.getChildren().remove(2);
                        layoutAddition.getChildren().add(2,stockSelect);
                        
                        //Disable Variation option
                         variation.setDisable(true);
                        
                    }
                    
                    //current selected option index of the typeSelect
                    currentTypeSelected = finalIndex;
            });
        
        
        //Quantity Field
        TextField qtyCarton = new TextField();
            qtyCarton.setPromptText("No of cartons to add");
//            qtyCarton.setMaxWidth(45);
            
        TextField qtyPerCarton = new TextField();
            qtyPerCarton.setPromptText("No of units per cartons");
//            qtyPerCarton.setMaxWidth(45);
            
            HBox qtyContainer = new HBox(10);
                 qtyContainer.getChildren().addAll(qtyCarton, qtyPerCarton);
       
        //Cost Price Field
        TextField costPrice = new TextField();
            costPrice.setPromptText("Stock Cost Price");
//            costPrice.setMaxWidth(100);

        
        //Add Stock Button
               addStock.setFont(Font.font("Times New Roman", FontWeight.BOLD, 13));
               addStock.setDefaultButton(true);
               addStock.setOnAction(e -> {
                   
                   Stock stockObj = new Stock();
                   profit.logger.utility.Date_TimeBuild dt = new profit.logger.utility.Date_TimeBuild();
                   
                   //check if all the common fields supplied inputs (cartonQty and costPrice) are valid
                   if(stockObj.setQtyAvailable(qtyCarton.getText()) && stockObj.setqtyAddCartons(qtyCarton.getText()) && stockObj.setCurrentSellingPrice(costPrice.getText())){

                       //CASE: Carton & New Addition
                       if(category==2 && currentTypeSelected.equals(0)){
                           
                            Boolean nameResult = stockObj.setName(stockName.getText());
                            Boolean sizeResult = stockObj.setSize(variation.getSelectionModel().selectedItemProperty().getValue());
                            
                           //check if the stock already exist
                           try{
                                String query = String.format("SELECT * FROM profitlogger.%s WHERE name='"+ stockObj.getName() +"' AND size='"+ variation.getSelectionModel().selectedItemProperty().getValue() +"'", dbName);
//                                String query = String.format("SELECT * FROM profitlogger.%s WHERE name='"+ stockObj.getName() +"'", dbName);
                                Statement st = connection.createStatement();
                                ResultSet result = st.executeQuery(query);
                                   
                                    if(result.next()==false){ //if the resultset has any data set
                                       
                                        if(stockObj.setLastUpdated(dt.toString()) && nameResult && sizeResult){
                                           //set new ID
                                            stockObj.setID(++id);
                                            
                                            query = String.format("INSERT INTO profitlogger.%s(name, size, currentSellingPrice, qtyAvailable, lastUpdated) VALUES ('"+stockObj.getName()+"','"+stockObj.getSize()+"','"+stockObj.getCurrentSellingPrice()+"','"+stockObj.getCalcQtyAddCartons()+"','"+stockObj.getLastUpdated()+"')",dbName);
                                            st.executeUpdate(query);
                                            
                                            //append the new stock detail column to the table
                                            table.getItems().add(stockObj);
                                            
                                            //notify the user of the addition success
                                            Alert alert = new Alert(AlertType.INFORMATION);
                                              alert.setTitle("Success");
                                              alert.setHeaderText(null);
                                              alert.setContentText("Stock added successfully");
                                              alert.showAndWait();
                                              
                                              //clear the field(s) with invalid values
                                              stockName.clear();
                                              qtyCarton.clear();
                                              costPrice.clear();
                                              typeSelect.getSelectionModel().clearAndSelect(0);
                                              variation.getSelectionModel().clearAndSelect(0);
                                        }else{
                                            //notify the user of the addition failure
                                            Alert alert = new Alert(AlertType.WARNING);
                                                  alert.setTitle("Error");
                                                  alert.setHeaderText(null);
                                                  alert.setContentText("Enter valid value(s) in the blank field(s)");
                                                  alert.showAndWait();
                                                  
                                                  //clear the field(s) with invalid values
                                                  if(nameResult==false)
                                                      stockName.clear();
                                                  typeSelect.getSelectionModel().clearAndSelect(0);
                                                  variation.getSelectionModel().clearAndSelect(0);
                                                  
                                        }
                                    }
                                    else{
                                        Alert alert = new Alert(AlertType.ERROR);
                                              alert.setTitle("Input error");
                                              alert.setHeaderText(null);
                                              alert.setContentText("Stock name with size already exist, update its value only");
                                              alert.showAndWait();
                                              
                                              //clear the field(s) with invalid values
                                              stockName.clear();
                                              qtyCarton.clear();
                                              costPrice.clear();
                                              typeSelect.getSelectionModel().clearAndSelect(0);
                                              variation.getSelectionModel().clearAndSelect(0);
                                    }
                           }catch(SQLException ex){}
                       }
                       
                        //CASE: Carton & Already added
                       else if(category==2 && currentTypeSelected.equals(1) && stockSelect.getSelectionModel().getSelectedIndex()!=0){
                           
                           String currentStockName = stockSelect.getSelectionModel().selectedItemProperty().getValue();
                           int currentStockID = identifier.get(currentStockName); // using HashMap
                           
                           try{
                               
                               String query = String.format("SELECT * FROM profitlogger.%s WHERE id=%d", dbName,currentStockID);
                               Statement st = connection.createStatement();
                               ResultSet result = st.executeQuery(query);
                               
                                    while(result.next()){
                                        currentQtyAvailable = result.getInt("qtyAvailable");
                                        newQtyComputed = (currentQtyAvailable + stockObj.getCalcQtyAddCartons());
                                    }
                            }catch(SQLException ex){}
                                                             
                                            if(stockObj.setLastUpdated(dt.toString()) && stockSelect.getSelectionModel().getSelectedIndex()!=0){
                                                
                                                PreparedStatement statement = null;
                                                double newPrice = stockObj.getCurrentSellingPrice();
                                                String newUpdate = stockObj.getLastUpdated();
                                                
                                                try{
                                                       String query = String.format("UPDATE %s SET currentSellingPrice=?, qtyAvailable=?, lastUpdated=? WHERE id=?",dbName);
                                                       statement = newConnection.prepareStatement(query);
                                                       statement.setDouble(1, newPrice);
                                                       statement.setInt(2, newQtyComputed);
                                                       statement.setString(3, newUpdate);
                                                       statement.setInt(4, currentStockID);
                                                       statement.executeUpdate();
                                                        
                                                }catch(SQLException E){}
                                                            
                                                            //update list
                                                            getTableValue(dbName);
                                                            table.setItems(stock);
                                                            
                                                            //notify the user of the addition success
                                                            Alert alert = new Alert(AlertType.INFORMATION);
                                                                  alert.setTitle("Success");
                                                                  alert.setHeaderText(null);
                                                                  alert.setContentText("Stock updated successfully");
                                                                  alert.showAndWait();
                                                            
                                                            //clear the field(s) with invalid values
                                                                  qtyCarton.clear();
                                                                  costPrice.clear();
                                                                  stockSelect.getSelectionModel().clearAndSelect(0); 
                                                                                    
                                            }else{
                                                //notify the user of the addition failure
                                                Alert alert = new Alert(AlertType.ERROR);
                                                      alert.setTitle("Failure");
                                                      alert.setHeaderText(null);
                                                      alert.setContentText("Unable to update Stock record");
                                                      alert.showAndWait();

                                                //clear the field(s) with invalid values
                                                      qtyCarton.clear();
                                                      costPrice.clear();
                                            }
                               
                       }
                       
                       //CASE: Piece & New Addition
                       if(category==1 && currentTypeSelected.equals(0)){
                           
                            Boolean nameResult = stockObj.setName(stockName.getText());
                            Boolean sizeResult = stockObj.setSize(variation.getSelectionModel().selectedItemProperty().getValue());
                            Boolean piecePerCartonResult = stockObj.setqtyAddPieces(qtyPerCarton.getText());
                            
                            if(piecePerCartonResult){
                                
                                int newQtyAvailable = (stockObj.getCalcQtyAddCartons() * stockObj.getCalcQtyAddPieces());
                                Boolean validQtyAvailable = stockObj.setQtyAvailable(String.format("%d",newQtyAvailable)); //reset qtyAvailable because of the table update
                            
                           
                                //check if the stock already exist
                                try{
                                     String query = String.format("SELECT * FROM profitlogger.%s WHERE name='"+ stockObj.getName() +"' AND size='"+ variation.getSelectionModel().selectedItemProperty().getValue() +"'", dbName);
     //                                String query = String.format("SELECT * FROM profitlogger.%s WHERE name='"+ stockObj.getName() +"'", dbName);
                                     Statement st = connection.createStatement();
                                     ResultSet result = st.executeQuery(query);

                                         if(result.next()==false){ //if the resultset has any data set

                                             if(stockObj.setLastUpdated(dt.toString()) && nameResult && sizeResult && piecePerCartonResult && validQtyAvailable){
                                                //set new ID
                                                 stockObj.setID(++id);

                                                 query = String.format("INSERT INTO profitlogger.%s(name, size, currentSellingPrice, qtyAvailable, lastUpdated) VALUES ('"+stockObj.getName()+"','"+stockObj.getSize()+"','"+stockObj.getCurrentSellingPrice()+"','"+newQtyAvailable+"','"+stockObj.getLastUpdated()+"')",dbName);
                                                 st.executeUpdate(query);

                                                 //append the new stock detail column to the table
                                                 table.getItems().add(stockObj);

                                                 //notify the user of the addition success
                                                 Alert alert = new Alert(AlertType.INFORMATION);
                                                   alert.setTitle("Success");
                                                   alert.setHeaderText(null);
                                                   alert.setContentText("Stock added successfully");
                                                   alert.showAndWait();

                                                   //clear the field(s) with invalid values
                                                   stockName.clear();
                                                   qtyCarton.clear();
                                                   costPrice.clear();
                                                   qtyPerCarton.clear();
                                                   typeSelect.getSelectionModel().clearAndSelect(0);
                                                   variation.getSelectionModel().clearAndSelect(0);
                                             }else{
                                                 //notify the user of the addition failure
                                                 Alert alert = new Alert(AlertType.WARNING);
                                                       alert.setTitle("Error");
                                                       alert.setHeaderText(null);
                                                       alert.setContentText("Enter valid value(s) in the blank field(s)");
                                                       alert.showAndWait();

                                                       //clear the field(s) with invalid values
                                                       if(nameResult==false)
                                                           stockName.clear();
                                                       typeSelect.getSelectionModel().clearAndSelect(0);
                                                       variation.getSelectionModel().clearAndSelect(0);

                                             }
                                         }
                                         else{
                                             Alert alert = new Alert(AlertType.ERROR);
                                                   alert.setTitle("Input error");
                                                   alert.setHeaderText(null);
                                                   alert.setContentText("Stock name with size already exist, update its value only");
                                                   alert.showAndWait();

                                                   //clear the field(s) with invalid values
                                                   stockName.clear();
                                                   qtyCarton.clear();
                                                   costPrice.clear();
                                                   qtyPerCarton.clear();
                                                   typeSelect.getSelectionModel().clearAndSelect(0);
                                                   variation.getSelectionModel().clearAndSelect(0);
                                         }
                                }catch(SQLException ex){}

                           }else{
                                Alert alert = new Alert(AlertType.WARNING);
                                      alert.setTitle("Error");
                                      alert.setHeaderText(null);
                                      alert.setContentText("Enter valid value(s) in the blank field(s)");
                                      alert.showAndWait();
                                      
                                      //clear the field(s) with invalid values
                                      qtyCarton.clear();
                                      costPrice.clear();
                                      qtyPerCarton.clear();
                           }
                       }
                       
                        //CASE: Pieces & Already added
                       else if(category==1 && currentTypeSelected.equals(1) && stockSelect.getSelectionModel().getSelectedIndex()!=0){
                           
                           String currentStockName = stockSelect.getSelectionModel().selectedItemProperty().getValue();
                           int currentStockID = identifier.get(currentStockName); // using HashMap
                           
                           Boolean pieceResult = stockObj.setqtyAddPieces(qtyPerCarton.getText());
                           
                           if(pieceResult){
                           try{
                               
                               String query = String.format("SELECT * FROM profitlogger.%s WHERE id=%d", dbName,currentStockID);
                               Statement st = connection.createStatement();
                               ResultSet result = st.executeQuery(query);
                               
                                    while(result.next()){
                                        currentQtyAvailable = result.getInt("qtyAvailable");
                                        newQtyComputed = (currentQtyAvailable + (stockObj.getCalcQtyAddPieces() * stockObj.getCalcQtyAddCartons()));
                                        stockObj.setQtyAvailable(newQtyComputed);
                                    }
                            }catch(SQLException ex){}
                                                             
                                            if(stockObj.setLastUpdated(dt.toString()) && pieceResult && stockSelect.getSelectionModel().getSelectedIndex()!=0){
                                                
                                                PreparedStatement statement;
                                                double newPrice = stockObj.getCurrentSellingPrice();
                                                String newUpdate = stockObj.getLastUpdated();
                                                
                                                try{
                                                       String query = String.format("UPDATE %s SET currentSellingPrice=?, qtyAvailable=?, lastUpdated=? WHERE id=?",dbName);
                                                       statement = newConnection.prepareStatement(query);
                                                       statement.setDouble(1, newPrice);
                                                       statement.setInt(2, newQtyComputed);
                                                       statement.setString(3, newUpdate);
                                                       statement.setInt(4, currentStockID);
                                                       statement.executeUpdate();
                                                        
                                                }catch(SQLException E){}
                                                            //update list
                                                            getTableValue(dbName);
                                                            table.setItems(stock);
                                                            
                                                            //notify the user of the addition success
                                                            Alert alert = new Alert(AlertType.INFORMATION);
                                                                  alert.setTitle("Success");
                                                                  alert.setHeaderText(null);
                                                                  alert.setContentText("Stock updated successfully");
                                                                  alert.showAndWait();
                                                                                                                                                                         
                                                            //clear the field(s) with invalid values
                                                                  qtyCarton.clear();
                                                                  costPrice.clear();
                                                                  qtyPerCarton.clear();
                                                                  stockSelect.getSelectionModel().clearAndSelect(0); 
                                                                                    
                                            }else{
                                                //notify the user of the addition failure
                                                Alert alert = new Alert(AlertType.ERROR);
                                                      alert.setTitle("Failure");
                                                      alert.setHeaderText(null);
                                                      alert.setContentText("Unable to update Stock record");
                                                      alert.showAndWait();

                                                //clear the field(s) with invalid values
                                                      qtyCarton.clear();
                                                      costPrice.clear();
                                            }
                           }else{
                               Alert alert = new Alert(AlertType.WARNING);
                                      alert.setTitle("Error");
                                      alert.setHeaderText(null);
                                      alert.setContentText("Enter valid value(s) in the blank field(s)");
                                      alert.showAndWait();
                                      
                                      //clear the field(s) with invalid values
                                      qtyCarton.clear();
                                      costPrice.clear();
                                      qtyPerCarton.clear();
                           }  
                       }
                       
                       else{
                           //Send an error message; showing that the user should fill appropriately the empty fields (Qty Carton & Selling Price fields) 
                            Alert alert = new Alert(AlertType.WARNING);
                                              alert.setTitle("Input Error");
                                              alert.setHeaderText(null);
                                              alert.setContentText("No stock selected!");
                                              alert.showAndWait();
                                              
                                              //clear the field(s) with invalid values
                                              qtyCarton.clear();
                                              qtyPerCarton.clear();
                                              costPrice.clear();
                       }
                                              
                       
                       
                   } else{
                       //Send an error message; showing that the user should fill appropriately the empty fields (Qty Carton & Selling Price fields) 
                       Alert alert = new Alert(AlertType.WARNING);
                                              alert.setTitle("Input Error");
                                              alert.setHeaderText(null);
                                              alert.setContentText("Enter valid value(s) in the blank field(s)");
                                              alert.showAndWait();
                                              
                                              //clear the field(s) with invalid values
                                              qtyCarton.clear();
                                              qtyPerCarton.clear();
                                              costPrice.clear();
                   }

               });
        
        Button closeButton = new Button("CLOSE");
               closeButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 13));
               closeButton.setOnAction(e ->{
                   windowAddStock.close();
               });
        
        HBox buttonContainer = new HBox(10);
             buttonContainer.getChildren().addAll(addStock,closeButton);
             buttonContainer.setAlignment(Pos.CENTER);
            
             //Pieces addition     
             layoutAddition.setAlignment(Pos.CENTER_LEFT);
             layoutAddition.getChildren().addAll(titleBox, typeSelect, stockName, variation, qtyContainer, costPrice, buttonContainer);
             layoutAddition.setPadding(new Insets(10,10,10,10));
             
             //types of scene
             Scene Addition = new Scene(layoutAddition, 300, 320);             
             
             //set-up window
//                   windowAddStock.setScene((category == 2 ? cartonAddition : piecesAddition));
             if(category==2)
                 qtyContainer.getChildren().remove(qtyPerCarton);
             
             
                   windowAddStock.setScene(Addition);
                   windowAddStock.sizeToScene();
                   windowAddStock.show();
                   
                   
    }
    
    
    private void updateStockRecord(String oldName, String oldSize, int oldQty, double oldPrice){
        
//        Create window
             Stage windowUpdateStock = new Stage();
                   windowUpdateStock.setTitle("Stock Update");
             
        //Scene Title
                Text title = new Text("Stock Detail Update");
                    title.setFont(Font.font("Georgia", FontWeight.BOLD, 25));
                    title.setFill(Color.DARKBLUE);
                    
                    //Layout
                    HBox titleBox = new HBox();
                         titleBox.getChildren().add(title);
                         titleBox.setAlignment(Pos.CENTER);
                 
        //Type Field
        Button updateStock = new Button("UPDATE STOCK");
        
        //Final container
        VBox layoutUpdate = new VBox(20);
        
       //Stock Name Field
        TextField stockName = new TextField();
            stockName.setPromptText("Enter stock name");
            stockName.setText(oldName);
//            stockName.setMaxWidth(100);          
        
        //Variations Field
        ChoiceBox<String> variation = new ChoiceBox<>(); 
            variation.getItems().addAll("No variation", "Small size", "Medium size", "Big size", "Extra Big size");
                int selectedStockSizeOptionIndex=0;
                
                switch(oldSize){
                    case "No variation": {
                        selectedStockSizeOptionIndex=0;
                        break;
                    }
                    case "Small size": {
                        selectedStockSizeOptionIndex=1;
                        break;
                    }
                    case "Medium size": {
                        selectedStockSizeOptionIndex=2;
                        break;
                    }
                    case "Big size": {
                        selectedStockSizeOptionIndex=3;
                        break;
                    }
                    case "Extra Big size": {
                        selectedStockSizeOptionIndex=4;
                        break;
                    }
                }            
            
            variation.getSelectionModel().clearAndSelect(selectedStockSizeOptionIndex);
//            selectCategory.setMinWidth(200);
                              
        
        //Quantity Field
        TextField qtyAvailable = new TextField();
            qtyAvailable.setPromptText("No of cartons to add");
            qtyAvailable.setText(String.format("%d", oldQty));
//            qtyCarton.setMaxWidth(45);
            
       
        //Cost Price Field
        TextField costPrice = new TextField();
            costPrice.setPromptText("Stock Cost Price");
            costPrice.setText(String.format("%.2f", oldPrice));
//            costPrice.setMaxWidth(100);

        
        //Update Stock Button
               updateStock.setFont(Font.font("Times New Roman", FontWeight.BOLD, 13));
               updateStock.setDefaultButton(true);
               updateStock.setOnAction(e -> {
                   
                   Stock stockObj = new Stock();
                   profit.logger.utility.Date_TimeBuild dt = new profit.logger.utility.Date_TimeBuild();
                   
                   //check if all the common fields supplied inputs (cartonQty and costPrice) are valid
                   if(stockObj.setQtyAvailable(qtyAvailable.getText()) && stockObj.setCurrentSellingPrice(costPrice.getText())){

                            Boolean nameResult = stockObj.setName(stockName.getText());
                            Boolean sizeResult = stockObj.setSize(variation.getSelectionModel().selectedItemProperty().getValue());
                            
                                    String newName = stockObj.getName();
                                    String newSize = stockObj.getSize();
                            try{
                                     String query3 = String.format("SELECT * FROM profitlogger.%s WHERE name='"+ newName +"' AND size='"+ newSize +"'", dbName);
     //                                String query = String.format("SELECT * FROM profitlogger.%s WHERE name='"+ stockObj.getName() +"'", dbName);
                                     Statement st3 = connection.createStatement();
                                     ResultSet result3 = st3.executeQuery(query3);
        
                                    if(result3.next()==false){ //if the resultset has any data set

                                                    if(stockObj.setLastUpdated(dt.toString()) && nameResult && sizeResult){

                                                        ButtonType Yes = new ButtonType("YES", ButtonBar.ButtonData.OK_DONE);
                                                        ButtonType No = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

                                                        Alert alert = new Alert(AlertType.CONFIRMATION, "Do you want to update this stock detail?", Yes, No);
                                                            alert.setTitle("Update Confirmation");
                                                            alert.setHeaderText(null);

                                                        Optional<ButtonType> result = alert.showAndWait();

                                                            if(result.isPresent() && result.get()==Yes){

                                                                double newPrice = stockObj.getCurrentSellingPrice();
                                                                int newQty = stockObj.getQtyAvailable();
                                                                String newLastUpdated = stockObj.getLastUpdated();

                                                                   Statement st;
                                                                   PreparedStatement statement;

                                                                   int selectedID = 0;


                                                                    //get DB ID of the selected row
                                                                     try{
                 //                                                           String IDQuery = String.format("SELECT * FROM profitlogger.%s WHERE name='"+oldName+"', size='"+oldSize+"' AND qtyAvailable='"+oldQty+"'", dbName);
                                                                            String IDQuery = String.format("SELECT * FROM profitlogger.%s WHERE name='"+oldName+"'", dbName);
                                                                            st = newConnection.createStatement();
                                                                            ResultSet result1 = st.executeQuery(IDQuery);

                                                                             while(result1.next()){
                                                                                 if(result1.getString("size").equals(oldSize) && result1.getInt("qtyAvailable")==oldQty && result1.getDouble("currentSellingPrice")==oldPrice)
                                                                                      selectedID = result1.getInt("id");
                                                                             }
                                                                     }catch(SQLException d){}

                                                                    //update
                                                                    try{
                                                                         String query = String.format("UPDATE %s SET name=?, size=?, currentSellingPrice=?, qtyAvailable=?, lastUpdated=? WHERE id=?", dbName);
                                                                                statement = newConnection.prepareStatement(query);
                                                                                statement.setString(1, newName);
                                                                                statement.setString(2, newSize);
                                                                                statement.setDouble(3, newPrice);
                                                                                statement.setInt(4, newQty);
                                                                                statement.setString(5, newLastUpdated);
                                                                                statement.setInt(6, selectedID);
                                                                                statement.executeUpdate();

                                                                    }catch(SQLException ex){}

                                                                            //refresh table
                                                                              getTableValue(dbName);
                                                                              table.setItems(stock);

                                                                            //close pop up pane
                                                                            windowUpdateStock.close();

                                                                            //notify the user of the addition success
                                                                            Alert confirmAlert = new Alert(AlertType.INFORMATION);
                                                                              confirmAlert.setTitle("Success");
                                                                              confirmAlert.setHeaderText(null);
                                                                              confirmAlert.setContentText("Stock updated successfully");
                                                                              confirmAlert.showAndWait();
                                                            }

                                                }else{
                                                    //notify the user of the addition failure
                                                    Alert alert = new Alert(AlertType.WARNING);
                                                          alert.setTitle("Error");
                                                          alert.setHeaderText(null);
                                                          alert.setContentText("Enter valid value(s) in the blank field(s)");
                                                          alert.showAndWait();

                                                          //clear the field(s) with invalid values
                                                          if(nameResult==false)
                                                              stockName.clear();

                                                    variation.getSelectionModel().clearAndSelect(0);

                                                }
                            }else{
                                    //clear stock name
                                        stockName.clear();
                                        
                                    //notification of error
                                    Alert alert = new Alert(AlertType.ERROR);
                                          alert.setTitle("Error");
                                          alert.setHeaderText(null);
                                          alert.setContentText("Stock name already exit! Enter valid name in the blank stock name field");
                                          alert.showAndWait();
                                
                            }
                            
                        }catch(SQLException q){}
                   } else{
                       //Send an error message; showing that the user should fill appropriately the empty fields (Qty Carton & Selling Price fields) 
                       Alert alert = new Alert(AlertType.WARNING);
                                              alert.setTitle("Input Error");
                                              alert.setHeaderText(null);
                                              alert.setContentText("Enter valid value(s) in the blank field(s)");
                                              alert.showAndWait();
                                              
                                              //clear the field(s) with invalid values
                                              qtyAvailable.clear();
                                              costPrice.clear();
                   }

               });
        
        Button closeButton = new Button("CLOSE");
               closeButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 13));
               closeButton.setOnAction(e ->{
                   windowUpdateStock.close();
               });
        
        HBox buttonContainer = new HBox(10);
             buttonContainer.getChildren().addAll(updateStock,closeButton);
             buttonContainer.setAlignment(Pos.CENTER);
             
            
            //Pieces addition     
             layoutUpdate.setAlignment(Pos.CENTER_LEFT);
             layoutUpdate.getChildren().addAll(titleBox, stockName, variation, qtyAvailable, costPrice, buttonContainer);
             layoutUpdate.setPadding(new Insets(10,10,10,10));
             
             //types of scene
             Scene Update = new Scene(layoutUpdate, 275, 275);             
             
             //set-up window
                   windowUpdateStock.setScene(Update);
                   windowUpdateStock.sizeToScene();
                   windowUpdateStock.show();
                   
                   
    }

    private void deleteStockRecord(Stock selectedStockObj) {
        String oldName = selectedStockObj.getName();
        String oldSize = selectedStockObj.getSize();
        int oldQty = selectedStockObj.getQtyAvailable();
        double oldPrice = selectedStockObj.getCurrentSellingPrice();
        
        int selectedID = 0;

            //get DB ID of the selected row
                try{
//                  String IDQuery = String.format("SELECT * FROM profitlogger.%s WHERE name='"+oldName+"', size='"+oldSize+"' AND qtyAvailable='"+oldQty+"'", dbName);
                    String IDQuery = String.format("SELECT * FROM profitlogger.%s WHERE name='"+oldName+"'", dbName);
                    Statement st = newConnection.createStatement();
                    ResultSet result1 = st.executeQuery(IDQuery);
                    
                        while(result1.next()){
                               if(result1.getString("size").equals(oldSize) && result1.getInt("qtyAvailable")==oldQty && result1.getDouble("currentSellingPrice")==oldPrice)
                                    selectedID = result1.getInt("id");
                                }
                }catch(SQLException d){}

            //update
                try{
                    String query = String.format("DELETE FROM profitlogger.%s WHERE id='"+ selectedID +"'", dbName);
                    Statement statement = newConnection.createStatement();
                    statement.executeUpdate(query);

                }catch(SQLException ex){}
 
            //refresh table
                getTableValue(dbName);
                table.setItems(stock);

            //notify the user of the addition success
                Alert confirmAlert = new Alert(AlertType.INFORMATION);
                      confirmAlert.setTitle("Success");
                      confirmAlert.setHeaderText(null);
                      confirmAlert.setContentText("Stock deleted successfully");
                      confirmAlert.showAndWait();
    }
    
}