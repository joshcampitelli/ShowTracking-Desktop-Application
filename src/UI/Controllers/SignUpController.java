package UI.Controllers;

import Core.Framework;
import DataOperations.DataValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController extends Controller implements Initializable {
    private Framework framework = new Framework();

    @FXML
    public Button createAccountbtn;
    public TextField usernameField;
    public TextField passwordField;
    public TextField firstnameField;
    public TextField lastnameField;

    public void createAccount(ActionEvent event) {
        if (!DataValidation.validUser(usernameField.getText()) || !DataValidation.validPass(passwordField.getText())) {
            alertWindow("Create Account", "Failed to Create New Account", "Username or Password entered is incorrect, must both be at least 8 characters.");
            return;
        } else if (framework.usernameExists(usernameField.getText())) {
            alertWindow("Create Account", "Failed to Create New Account", "Username Entered already exists, please try again.");
            return;
        }

        framework.createAccount(usernameField.getText(), passwordField.getText(), firstnameField.getText(), lastnameField.getText());
        closeStage(createAccountbtn);
        createStage("LoginWindow", "Login Window", loginWidth, loginHeight);
    }
}
