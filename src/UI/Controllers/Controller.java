package UI.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    int loginHeight = 275;
    int loginWidth = 300;
    int signupHeight = loginHeight;
    int signupWidth = loginWidth;
    int mainHeight = 400;
    int mainWidth = 600;
    int searchHeight = 430;
    int searchWidth = 240;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    protected void closeStage(Button button) {
        // get a handle to the stage
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    protected void createStage(String fxmlClass, String title, int w, int h) {
        try {
            //Create new stage for the new window
            Stage primaryStage = new Stage();

            File file = new File("src/UI/FXML/" + fxmlClass + ".fxml");
            URL url = file.toURI().toURL();
            Parent root = FXMLLoader.load(url);

            primaryStage.setTitle(title);
            primaryStage.setScene(new Scene(root, w, h));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void alertWindow(String title, String mainText, String subText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(mainText);
        alert.setContentText(subText);
        alert.showAndWait();
    }
}
