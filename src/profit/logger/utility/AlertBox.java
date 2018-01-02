package profit.logger.utility;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class AlertBox{

       public static void display(String title, String message){
       
           Stage window = new Stage();
           window.initModality(Modality.APPLICATION_MODAL);
           window.resizableProperty().setValue(Boolean.FALSE);
           window.setMinWidth(470);
           window.setMaxHeight(400);
           window.setTitle(title);
           
           Label label = new Label(message);
           label.setScaleX(1.5);
           label.setScaleY(1.5);
           Button button = new Button("Close");
           button.setOnAction(e -> window.close());
           
           VBox layout = new VBox(10);
           layout.getChildren().addAll(label, button);
           layout.setAlignment(Pos.CENTER);
           //      --------------------------------------------------------------------
           String style = String.format("-fx-background: rgb(%d, %d, %d);"+
                    "-fx-background-color: -fx-background;",256,256,256);
            layout.setStyle(style);
//      --------------------------------------------------------------------
        
           
           Scene scene = new Scene(layout);
           window.setScene(scene);
           window.setAlwaysOnTop(true);
           window.showAndWait();
       }
}