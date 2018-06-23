package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RunApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        /*Run on laptop IDE*/
        Parent root = FXMLLoader.load(getClass().getResource("FXML/MainWindow.fxml"));

        /*Run on desktop IDE*/
        //URL url = new File("C:\\Users\\joshc\\Dropbox\\JAVA\\ShowTracking-Desktop-Application\\src\\UI\\FXML\\MainWindow.fxml").toURL();
        //URL url = new File("C:\\Users\\Josh\\Dropbox\\JAVA\\ShowTracking-Desktop-Application\\src\\UI\\FXML\\HomeWindow.fxml").toURL();

        //Parent root = FXMLLoader.load(url);

        Scene scene = new Scene(root, 600, 515);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ShowTracker");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
