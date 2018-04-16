package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RunShowAdd extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        //GridView Version
        //Parent root = FXMLLoader.load(getClass().getResource("FXML/GridWindow.fxml"));
        //primaryStage.setScene(new Scene(root, 600, 440));

        //Below line to add Shows to DB
        Parent root = FXMLLoader.load(getClass().getResource("FXML/AddShowWindow.fxml"));
        primaryStage.setScene(new Scene(root, 303, 410));
        primaryStage.setTitle("ShowTracker");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
