package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXML/LoginWindow.fxml"));
        //Below line to add Shows to DB
        //Parent root = FXMLLoader.load(getClass().getResource("FXML/AddShowWindow.fxml"));

        primaryStage.setTitle("ShowTracker");
        primaryStage.setScene(new Scene(root, 303, 410));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
