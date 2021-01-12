package ro.mta.selab;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/sample.fxml"));
        primaryStage.setTitle("YAWAP - Yet Another WAP");
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("resources/images/pepe.png")));
        Scene scene = new Scene(root, 513, 392);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    @FXML
    public void init(){

    }

    public static void main(String[] args) {
        launch(args);
    }
}
