package profit.logger;

import javafx.application.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.*;

public class mainMenu extends Application{

    Stage window;
    String currentUser;
    
    @Override
    public void start(Stage primaryStage){
        
   /**     
        //Screen Dimension
          int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
          int screenHeight = (int) Screen.getPrimary().getBounds().getHeight(); 
        
        screenWidth = Screen Resolution Width
        screenHeight = Screen Resolution Height
 */ 
        
        
        window = primaryStage;
        window.setTitle("BisMie Store, Ibadan: Stock and Accounting Record System - Main Menu");
        window.setAlwaysOnTop(true);
        window.setMaximized(true);
        window.setScene(display());
//      window.sizeToScene(); - very useful a brother. Muah!!! It does size the windows according to the size specidfied in the SCENE
        window.show();
    }
    
    public mainMenu(String currentUser){
        this.currentUser = currentUser;
    }
    public Scene display(){
        
        //Header Text
        Text header = new Text("BISMIE STORE, IBADAN");
             header.setFont(Font.font("Georgia", FontWeight.EXTRA_BOLD, 90));
             header.setFill(Color.MIDNIGHTBLUE);
        
        Text desc = new Text("STOCK AND ACCOUNTING RECORD SYSTEM");
             desc.setFont(Font.font("Calibri", FontWeight.EXTRA_BOLD, 40));
             desc.setFill(Color.DARKGOLDENROD);
        
        VBox titleWrapper = new VBox(3);
             titleWrapper.getChildren().addAll(header, desc);
             titleWrapper.setAlignment(Pos.CENTER_LEFT);
            
        //Header Picture
        Image image1 = new Image(getClass().getResourceAsStream("OAU_LOGO.png"));
        Label companyLogo = new Label();
        companyLogo.setGraphic(new ImageView(image1));   
        
        HBox topContainer = new HBox(15);
             topContainer.getChildren().addAll(companyLogo,titleWrapper);
             topContainer.setAlignment(Pos.TOP_CENTER);
        
             
        
             Text greeting = new Text(String.format("Welcome, %s!",currentUser));
                  greeting.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
                  greeting.setFill(Color.RED);
             
             Button logout = new Button("LOGOUT");
                    logout.setOnAction(e -> {
                      currentUser = null;
                      logout(e);
                      
                  });
                  
             //container
             HBox greeting_logoutButtonHolder = new HBox(5);
                  greeting_logoutButtonHolder.getChildren().addAll(greeting,logout);
                  greeting_logoutButtonHolder.setAlignment(Pos.CENTER_RIGHT);
                  

            //page description
            Text pageDesc = new Text("MAIN MENU"); 
                 pageDesc.setFont(Font.font("ARIAL BLACK", FontWeight.BOLD, 30));
                 pageDesc.setFill(Color.GRAY);
                 
                 HBox description = new HBox();
                      description.setAlignment(Pos.CENTER);
                      description.getChildren().add(pageDesc);

            //page filler
            Text filler = new Text(""); 
                 filler.setFont(Font.font("ARIAL BLACK", FontWeight.BOLD, 50));
                 filler.setFill(Color.RED);
                 
                 HBox fill = new HBox();
                      fill.setAlignment(Pos.CENTER);
                      fill.getChildren().add(filler);
                      
                      
        //menu icons   
                      
        //Button Style       
//          String buttonStyle = "-fx-background-color: transparent";
          String buttonStyle = "-fx-focus-color: transparent";
       
       
                //Stock Record
                Button stockRecord = new Button();
                    Image image3 = new Image(getClass().getResourceAsStream("OAU_LOGO.png"));
                stockRecord.setGraphic(new ImageView(image3));
                stockRecord.setStyle(buttonStyle);
                stockRecord.setOnAction(e -> {
                    
                    profit.logger.stockrecord.StockRecord obj = new profit.logger.stockrecord.StockRecord();
                    obj.displayPick(currentUser);
//                    ((Node)e.getSource()).getScene().getWindow().hide();
                    
                    //House-keeping stuff
                    obj = null;
                });

       
                //Sales Record
                Button salesRecord = new Button();
        //          Image image4 = new Image(getClass().getResourceAsStream("EditBase.png"));
                salesRecord.setGraphic(new ImageView(image3));
                salesRecord.setStyle(buttonStyle);
                salesRecord.setOnAction(e -> {

                    //editBase ed = new editBase();
                    //ed.display();
                });

                //Provit Valuer
                Button profitValuer = new Button();
        //          Image image5 = new Image(getClass().getResourceAsStream("GenerateTable.png"));
                profitValuer.setGraphic(new ImageView(image3));
                profitValuer.setStyle(buttonStyle);
                profitValuer.setOnAction(e -> {
                   // genTable gen = new genTable();
                    //gen.display();
                });

                //Expenses Record
                Button expensesRecord = new Button();
        //          Image image6 = new Image(getClass().getResourceAsStream("ViewTable.png"));
                expensesRecord.setGraphic(new ImageView(image3));
                expensesRecord.setStyle(buttonStyle);
                expensesRecord.setOnAction(e -> {

                    //DrawTTUI draw = new DrawTTUI();
                    //draw.display();
                });

                //Expenses Record
                Button signUpAttendant = new Button();
        //          Image image7 = new Image(getClass().getResourceAsStream("ViewTable.png"));
                signUpAttendant.setGraphic(new ImageView(image3));
                signUpAttendant.setStyle(buttonStyle);
                signUpAttendant.setOnAction(e -> {

                    //DrawTTUI draw = new DrawTTUI();
                    //draw.display();
                });
                              

                //Icon Merge
                HBox iconHBox = new HBox(60);
                iconHBox.getChildren().addAll(stockRecord, salesRecord, profitValuer, expensesRecord, signUpAttendant);
                iconHBox.setAlignment(Pos.CENTER);             
                
                //page filler
                Text filler2 = new Text(""); 
                      filler2.setFont(Font.font("ARIAL BLACK", FontWeight.BOLD, 20));
                      filler2.setFill(Color.RED);
                 
                 HBox fill2 = new HBox();
                      fill2.setAlignment(Pos.CENTER);
                      fill2.getChildren().add(filler2);
                      
                //page filler
                Text filler3 = new Text(""); 
                      filler3.setFont(Font.font("ARIAL BLACK", FontWeight.BOLD, 30));
                      filler2.setFill(Color.RED);
                 
                 HBox fill3 = new HBox();
                      fill2.setAlignment(Pos.CENTER);
                      fill2.getChildren().add(filler3);
                      
        VBox vbox = new VBox(20);
            vbox.getChildren().addAll(fill3, topContainer, greeting_logoutButtonHolder, fill, description, fill2, iconHBox);
            vbox.setAlignment(Pos.CENTER);
            
        
        ScrollPane container = new ScrollPane(vbox);
            container.setPadding(new Insets(10,30,10,30));
 
//      --------------------------------------------------------------------
           String style = String.format("-fx-background: rgb(%d, %d, %d);"+
                    "-fx-background-color: -fx-background;",256,256,256);
//            container.setStyle(style);
//      --------------------------------------------------------------------
                       
    
        return new Scene(container,900,600,Color.GRAY);
//        return new Scene(container,900,400,Color.gray(0.85, 0.8));
    }
    
    public void logout(ActionEvent event){
        ((Node)event.getSource()).getScene().getWindow().hide();
        profit.logger.login.ProfitLogger.window.show();
    }
}