package profit.logger.utility;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.util.Duration;

public class currentStatus{

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
//           Button button = new Button("Close");
//           button.setOnAction(e -> window.close());
//           
//           VBox layout = new VBox(10);
//           layout.getChildren().addAll(label, button);
//           layout.setAlignment(Pos.CENTER);
           
           VBox layout = new VBox(10);
           layout.getChildren().add(label);
           layout.setAlignment(Pos.CENTER);
           
           Scene scene = new Scene(layout);
           window.setScene(scene);
           window.setAlwaysOnTop(true);
           window.show();
           
           Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(2) ,new EventHandler<ActionEvent>(){
        
            @Override
            public void handle(ActionEvent event){
            
                window.hide();
            }
        }));
        
        timeline.play();
    }   
           //a statement to make 'window' fade out must be included here 
 }