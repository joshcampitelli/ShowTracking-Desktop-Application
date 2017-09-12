package UI;
import Authentication.DataValidation;
import Core.Framework;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public Button loginbtn;
    public Button signupbtn;
    public Button createAccountbtn;

    @FXML
    public TextField usernameField;
    public TextField passwordField;
    public TextField usernameFieldSU;
    public TextField passwordFieldSU;
    public TextField firstnameFieldSU;
    public TextField lastnameFieldSU;

    @FXML
    public ListView dataList;

    private Framework framework = new Framework();


    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void startLogin(ActionEvent event) {
        framework.login(usernameField.getText(), passwordField.getText());
        closeStage(loginbtn);
        createStage("MainWindow", "Show Tracking", 600, 400);
    }

    public void getData(ActionEvent event) {
        //dataList.getItems().add(new CustomCell());
        dataList.getItems().add("SHOW NAME");
    }

    public void startSignup(ActionEvent event) {
        closeStage(signupbtn);
        createStage("SignUpWindow", "SignUp Window", 300, 275);
    }

    public void createAccount(ActionEvent event) {
        if (!DataValidation.validUser(usernameFieldSU.getText()) || !DataValidation.validPass(passwordFieldSU.getText())) {
            alertWindow("Create Account", "Failed to Create New Account", "Username or Password entered is incorrect, must both be at least 8 characters.");
            return;
        } else if (framework.usernameExists(usernameFieldSU.getText())) {
            alertWindow("Create Account", "Failed to Create New Account", "Username Entered already exists, please try again.");
           return;
        }

        framework.createAccount(usernameFieldSU.getText(), passwordFieldSU.getText(), firstnameFieldSU.getText(), lastnameFieldSU.getText());
        closeStage(createAccountbtn);
        createStage("LoginWindow", "Login Window", 300, 275);
    }

    private void closeStage(Button button) {
        // get a handle to the stage
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    private void createStage(String fxmlClass, String title, int w, int h) {
        try {
            //Create new stage for the new window
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource(fxmlClass + ".fxml"));
            primaryStage.setTitle(title);
            primaryStage.setScene(new Scene(root, w, h));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void alertWindow(String title, String mainText, String subText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(mainText);
        alert.setContentText(subText);
        alert.showAndWait();
    }
}
