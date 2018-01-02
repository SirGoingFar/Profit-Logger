/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package profit.logger.stockrecord;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author OLANREWAJU E A
 */
public class Trying extends Application {
    
    Connection newConnection;
    String newName = "Spagg";
    String newSize = "Small size";
    double newPrice = 81.8;
    int newQty = 97;
    String newLastUpdated = "2017-12-01 07:43:45";
    String oldName = "Spaggo";
    String oldSize = "Large size";
    int oldQty = 2;
    String dbName = "biscuit";
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Click");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                try{
                        newConnection = DriverManager.getConnection("jdbc:mysql://LocalHost:3306/profitlogger", "root", "root");
                        System.out.println("Connected!\n");
                }catch(SQLException e){}
                
                Statement statement = null;
                                                   try{
                                                        String query = String.format("UPDATE %s SET name=?, size=?, currentSellingPrice=?, qtyAvailable=?, lastUpdated=? WHERE name=?, size=?, qtyAvailable=?" ,dbName);
//                                                        statement = newConnection.prepareStatement(query);
//                                                               statement.setString(1, newName);
//                                                               statement.setString(2, newSize);
//                                                               statement.setDouble(3, newPrice);
//                                                               statement.setInt(4, newQty);
//                                                               statement.setString(5, newLastUpdated);
//                                                               statement.setString(6, oldName);
//                                                               statement.setString(7, oldSize);
////                                                               statement.setInt(8, oldQty);
                                                        
                                                               System.out.printf("%s\n%s\n%.2f\n%d\n%s\n\n%s\n%s\n%d\n%s", newName, newSize, newPrice, newQty, newLastUpdated, oldName, oldSize, oldQty, dbName);
                                                               statement = newConnection.createStatement();
                                                               statement.executeUpdate(query);
                                                                  System.out.println("\nDone!");
                                                   }catch(SQLException ex){}
                                               
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
        launch(args);
    }
    
}
