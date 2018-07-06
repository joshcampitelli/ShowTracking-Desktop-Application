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

    private static User currentUser;
    private ImageView arr1, arr2, arr3, arr4;

    public void setCurrentUser(String username) {
        currentUser = new User(username);
    }

    //Get handle to arrows for access from other controllers
    public void setArrows(ImageView arr1, ImageView arr2, ImageView arr3, ImageView arr4) {
        this.arr1 = arr1;
        this.arr2 = arr2;
        this.arr3 = arr3;
        this.arr4 = arr4;
    }

    //SetVisible to selected arrow, 0 to set all invisible
    public void toggleArrows(int arrNum) {
        if (arrNum == 2)
            arr2.setVisible(true);
        switch (arrNum) {
            case 1: {arr1.setVisible(true);}
            case 2: {arr2.setVisible(true);}
            case 3: {arr3.setVisible(true);}
            case 4: {arr4.setVisible(true);}
            case 0: {arr1.setVisible(true);
                    arr2.setVisible(false);
                    arr3.setVisible(false);
                    arr4.setVisible(false);}
        }
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
