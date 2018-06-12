package UI.Controllers;

import DataOperations.DataValidation;
import Queries.AccountQueries;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ResourceBundle;

public class SignUpController extends Controller implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {}
    private AccountQueries accountQueries = new AccountQueries();

    @FXML
    public Button createAccountBtn;
    public TextField usernameField;
    public TextField passwordField;
    public TextField firstNameField;
    public TextField lastNameField;
    public AnchorPane signUpWindow;

    public void createAccount(ActionEvent event) {
        if (!DataValidation.validUser(usernameField.getText()) || !DataValidation.validPass(passwordField.getText())) {
            alertWindow("Create Account", "Failed to Create New Account", "Username or Password entered is incorrect, must both be at least 8 characters.");
            return;
        } else if (usernameExists(usernameField.getText())) {
            alertWindow("Create Account", "Failed to Create New Account", "Username Entered already exists, please try again.");
            return;
        }

        createAccount(usernameField.getText(), passwordField.getText(), firstNameField.getText(), lastNameField.getText());

        try { /*Changes controller improperly*/
            File file = new File("src/UI/FXML/LoginWindow.fxml");
            URL url = file.toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            AnchorPane loginWindow = loader.load();
            signUpWindow.getChildren().removeAll();
            signUpWindow.getChildren().add(loginWindow);
            event.consume();
        } catch (IOException e) {
            e.printStackTrace();
        }
        event.consume();
    }

    public boolean usernameExists(String username) {
        try {
            return accountQueries.usernameExists(username);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void createAccount(String username, String password, String firstName, String lastName) {
        try {
            accountQueries.addAccount(firstName, lastName, username, password);
        } catch (SQLIntegrityConstraintViolationException e) {
            // Duplicate entry
            System.out.println("[Important] Account already exists!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
