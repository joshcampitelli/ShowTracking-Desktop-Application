package UI.Controllers;

import Model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Controller {
    int mainHeight = 515;
    int mainWidth = 600;

    //static so when modified in one subclass, changes for all
    private static User currentUser;

    public void setCurrentUser(String username) {
        currentUser = new User(username);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    protected void closeStage(ButtonBase button) {
        // get a handle to the stage
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    protected  void closeImageView(ImageView imageView) {
        Stage stage = (Stage) (imageView.getScene().getWindow());
        stage.close();
    }

    protected void createStage(String fxmlClass, String title, int w, int h) {
        try {
            //Create new stage for the new window
            Stage primaryStage = new Stage();

            File file = new File("src/UI/FXML/" + fxmlClass + ".fxml");
            URL url = file.toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            primaryStage.setTitle(title);
            Scene scene = new Scene(root, w, h);
            primaryStage.setScene(scene);

            scene.setFill(Color.TRANSPARENT);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void openPane(AnchorPane anchorPane, String fxml) {
        try { /*Changes controller improperly*/
            File file = new File("src/UI/FXML/" + fxml + ".fxml");
            URL url = file.toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            AnchorPane newPane = loader.load();
            anchorPane.getChildren().removeAll();
            anchorPane.getChildren().add(newPane);
        } catch (IOException e) {
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
