
package profit.logger.login;

import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.*;

/**
 *
 * @author OLANREWAJU E A
 */
public class ProfitLogger extends Application {
    
    public static Stage window;
    Stage PasswordReset;
    Stage MainMenu;
    TextField nameField;
    PasswordField passwordField;
       
    
    @Override
    public void start(Stage primaryStage) {
        
        window = primaryStage;
        window.setTitle("Profit Logger - User Login");
        window.resizableProperty().setValue(Boolean.FALSE);
        
        PasswordReset = new Stage();
        PasswordReset.setTitle("Password Setting");
        PasswordReset.resizableProperty().setValue(Boolean.FALSE);
      
        MainMenu = new Stage();
        MainMenu.setTitle("BisMie Store, Ibadan: Stock and Accounting Record System - Main Menu");
        MainMenu.setMaximized(true);
        
        //Picture Tag
        Label picture = new Label ();
        Image image = new Image(getClass().getResourceAsStream("TimerPix.png"));
        picture.setGraphic(new ImageView(image));
        VBox vbox = new VBox();
        vbox.getChildren().add(picture);
        
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(10);
        grid.setHgap(8);
        grid.setAlignment(Pos.CENTER);
        
        //UserName tag
        Text nameLabel = new Text("Username");
            nameLabel.setFont(Font.font("Century Gothic", FontWeight.LIGHT, 13));
        nameLabel.setFill(Color.DARKBLUE);
        GridPane.setConstraints(nameLabel,0,0);
        
        //UserName field
        nameField = new TextField();
        nameField.setPromptText("Your Username");
        GridPane.setConstraints(nameField,1,0);
         
        
        //Password Tag
        Text passwordLabel = new Text ("Password");
            passwordLabel.setFont(Font.font("Century Gothic", FontWeight.LIGHT, 13));
        passwordLabel.setFill(Color.DARKBLUE);           
        GridPane.setConstraints(passwordLabel,0,2);
       
        //Password field
        passwordField = new PasswordField();
        passwordField.setPromptText("Your Password");
        GridPane.setConstraints(passwordField,1,2);
        
        
        //Login button
        Button Login = new Button("LOGIN");
        GridPane.setConstraints(Login,0,5);
        Login.setDefaultButton(true);
        Login.setFont(Font.font("Times New Roman", FontWeight.BOLD, 13));
        Login.setOnAction(e -> {
                    Boolean approved;
                    profit.logger.login.loginValidation object = new profit.logger.login.loginValidation();
                    
                    if((nameField.getText()).isEmpty() || (passwordField.getText()).isEmpty()){
                        profit.logger.utility.currentStatus.display("Login Error", "Field(s) is/are empty");
                    }
                    
                    else{
                        approved = object.validate(nameField.getText(), passwordField.getText());
                        
                            if(approved){
                                profit.logger.utility.currentStatus.display("Login successful", "Welcome...");
                                window.hide();
                                
                                profit.logger.mainMenu obj = new profit.logger.mainMenu(nameField.getText());
                                MainMenu.setScene(obj.display());
                                MainMenu.show();
                                
                                //still clear the password field
                                passwordField.clear();
                            }
                            //add a code to make scene proceed to the MAIN-MENU scene and delete line 92
                            else{
                                passwordField.clear();
                                passwordField.setPromptText("Enter correct Password");
                            }        
                    }
                        
                //use approve value to switch scene [if TRUE, proceed to main menu; if FALSE, pop an alert box]
                
                //house keeping
                object = null;
        });
        
        //Clear button
        Button clear = new Button("CLEAR");
        clear.setFont(Font.font("Times New Roman", FontWeight.BOLD, 13));
        clear.setOnAction(e ->{
             nameField.clear();
             passwordField.clear();
        });
        
        //Password Reset
        Button passwordReset = new Button("PASSWORD RESET");
        passwordReset.setFont(Font.font("Times New Roman", FontWeight.BOLD, 13));
        passwordReset.setOnAction(e ->{
            window.close();
            passwordSetting().show();
        });
        
        //the holder that keeps both CLEAR and PASSWORD RESET buttons on the same line
        HBox clear_resetMerger = new HBox(9);
        clear_resetMerger.getChildren().addAll(clear, passwordReset);
        
        //constraint set for the holder
        GridPane.setConstraints(clear_resetMerger,1,5);
       
        //right grid aligner
        final Text blankSpace = new Text("");
            blankSpace.setFont(Font.font("Agency FB", FontWeight.EXTRA_BOLD, 1));
            
        final Text Title = new Text("   BISMIE STORE, IBADAN");
            Title.setFont(Font.font("Agency FB", FontWeight.EXTRA_BOLD, 35));
            Title.setFill(Color.DARKBLUE);
            Title.setStroke(Color.RED);
            
        grid.getChildren().addAll(nameLabel, nameField, passwordLabel, passwordField, Login, clear_resetMerger);
        
            VBox rightAligner = new VBox(10);
            rightAligner.getChildren().addAll(blankSpace, Title,grid);
        
        
        BorderPane borderpane = new BorderPane();
        borderpane.setLeft(vbox);
        borderpane.setRight(rightAligner);
        
        //      --------------------------------------------------------------------
           String style = String.format("-fx-background: rgb(%d, %d, %d);"+
                    "-fx-background-color: -fx-background;",256,256,256);
//            borderpane.setStyle(style);
//      --------------------------------------------------------------------
        
        
        Scene scene = new Scene(borderpane, 520, 200, Color.BLUE);
        window.setScene(scene);
        window.centerOnScreen();
        window.show();
    }
    
    
    private Stage passwordSetting(){
        
        //Scene Title
        Text title = new Text("Password Reset");
            title.setFont(Font.font("Arial black", FontWeight.BOLD, 20));
            title.setFill(Color.DARKBLUE);
        
        //Username Field
        TextField usernameField = new TextField();
            usernameField.setPromptText("Enter your username");
            usernameField.setMinWidth(10);
            usernameField.setFont(Font.font("Arial", 14));
        
        //Old Password Field
        PasswordField oldPassword = new PasswordField();
            oldPassword.setPromptText("Enter Old Password");
            oldPassword.setMinWidth(10);
        
        //New Password Field
        PasswordField newPassword = new PasswordField();
            newPassword.setPromptText("Enter New Password");
            newPassword.setMinWidth(10);
        
        //Confirm New Password Field
        PasswordField confirmNewPassword = new PasswordField();
            confirmNewPassword.setPromptText("Confirm New Password");
            confirmNewPassword.setMinWidth(10);
        
        //Okay Button
        Button okay = new Button("OK");
        okay.setFont(Font.font("Times New Roman", FontWeight.BOLD, 13));
        okay.setDefaultButton(true);
        okay.setOnAction(e ->{
             
               if((usernameField.getText()).isEmpty() || (oldPassword.getText()).isEmpty() || (newPassword.getText()).isEmpty() || (confirmNewPassword.getText()).isEmpty()){
                   profit.logger.utility.currentStatus.display("Reset Error", "Field(s) is/are empty");
               }
               else{
                   profit.logger.login.loginValidation object = new profit.logger.login.loginValidation();
                   Boolean status = object.passwordResetting(usernameField.getText(), oldPassword.getText(), newPassword.getText(), confirmNewPassword.getText());
                
                    if(status){
                        profit.logger.utility.currentStatus.display("Password Reset", "Password reset successfully!");
                        PasswordReset.close();
                        window.show();
                    }
               }

        });
        
        //Clear Button
        Button clear = new Button("CLEAR");
        clear.setFont(Font.font("Times New Roman", FontWeight.BOLD, 13));
        clear.setOnAction(e ->{
             
               usernameField.clear();
               usernameField.setPromptText("Enter your username");
               
               oldPassword.clear();
               oldPassword.setPromptText("Enter Old Password");
               
               newPassword.clear();
               newPassword.setPromptText("Enter New Password");
               
               confirmNewPassword.clear();
               confirmNewPassword.setPromptText("Confirm New Password");
               
        });
        
        //Cancel Button
        Button cancel = new Button("CLOSE");
        cancel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 13));
        cancel.setOnAction(e ->{
            
               PasswordReset.close();
               window.show();
        });
                //Buttons Merger
                   HBox buttonMerge = new HBox(15);
                    buttonMerge.getChildren().addAll(okay, clear, cancel);
                    buttonMerge.setAlignment(Pos.CENTER);
                    
         final Text blankSpace = new Text("");
            blankSpace.setFont(Font.font("Agency FB", FontWeight.EXTRA_BOLD, 1));
        
        VBox holder = new VBox(20);
            holder.setPadding(new Insets(10,10,10,10));
            holder.getChildren().addAll(title, usernameField, oldPassword, newPassword, confirmNewPassword, blankSpace, buttonMerge);
            holder.setAlignment(Pos.CENTER);
        
             //      --------------------------------------------------------------------
           String style = String.format("-fx-background: rgb(%d, %d, %d);"+
                    "-fx-background-color: -fx-background;",256,256,256);
//            holder.setStyle(style);
//      --------------------------------------------------------------------
        
        Scene passwordReset = new Scene(holder, 300, 290, Color.BLUE);
            passwordReset.setFill(Color.WHITE);
            
        PasswordReset.setScene(passwordReset);
        PasswordReset.centerOnScreen();
        PasswordReset.setOnCloseRequest(e -> window.show());
        
        return PasswordReset;
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
