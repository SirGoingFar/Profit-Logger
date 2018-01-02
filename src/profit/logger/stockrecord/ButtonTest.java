/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package profit.logger.stockrecord;

import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author OLANREWAJU E A
 */
public class ButtonTest extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                ButtonType foo = new ButtonType("Foo", ButtonBar.ButtonData.OK_DONE);
                ButtonType bar = new ButtonType("bar", ButtonBar.ButtonData.CANCEL_CLOSE);
                
                Alert alert = new Alert(AlertType.CONFIRMATION, "ABCD", foo, bar);
                alert.setTitle("Title");
                
                Optional<ButtonType> result = alert.showAndWait();
                
                if(result.isPresent() && result.get()==foo){
                    System.out.println("Foo was clicked");
                }else if(result.isPresent() && result.get()==bar){
                    System.out.println("Bar was clicked");
                }
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
