package UI;
import Core.ShowTracker;
import Core.Structure;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public Button loginbtn;
    public Button signupbtn;

    @FXML
    public TextField usernameField;
    public TextField passwordField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void startLogin(ActionEvent event) {
        Structure structure = new Structure();
        structure.login(usernameField.getText(), passwordField.getText());
    }

    //Todo: If Signup button pressed create new sigup window, create account, close, re-open login window.
    public void startSignup(ActionEvent event) {
        Structure structure = new Structure();
        System.out.println("Sign Up Clicked");
        //structure.createAccount(usernameField.getText(), passwordField.getText());
    }

    public void createAccount(ActionEvent event) {
        Structure structure = new Structure();
        System.out.println("Create Account Clicked");
    }
}
