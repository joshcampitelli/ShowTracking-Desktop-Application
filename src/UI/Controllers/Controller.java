package UI.Controllers;

import Model.Show;
import Model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
/*todo: new design: Controllers will each have a constructor which constructs it's own window.*/
public class Controller {
    //TODO: size variables should be final
    int loginHeight = 275;
    int loginWidth = 300;
    int signUpHeight = loginHeight;
    int signUpWidth = loginWidth;
    int mainHeight = 400;
    int mainWidth = 600;
    int searchHeight = 360;
    int searchWidth = 240;
    int editWidth = 240;
    int editHeight = 195;

    //static so when modified in one subclass, changes for all
    private static User currentUser;

    public void setCurrentUser(String username) {
        currentUser = new User(username);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    //Todo: use event to get handle to the window.
    protected void closeStage(ButtonBase button) {
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
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            primaryStage.setTitle(title);
            primaryStage.setScene(new Scene(root, w, h));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*Could pass arguments to initialize data on the controller*/
    public void openEditWindow(Show show) {
        try {
            File file = new File("src/UI/FXML/EditWindow.fxml");
            URL url = file.toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(root, editWidth, editHeight));
            EditController controller = loader.getController();
            controller.initData(show);
            stage.show();
        } catch (IOException e) {

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
