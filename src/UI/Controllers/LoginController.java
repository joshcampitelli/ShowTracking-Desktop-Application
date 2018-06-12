package UI.Controllers;

import DataOperations.DataValidation;
import Queries.AccountQueries;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController extends Controller implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    @FXML
    public TextField usernameField;
    public TextField passwordField;
    public Button loginBtn;
    public Button signUpBtn;
    public AnchorPane loginWindow;
    public AnchorPane anchorPane;

    public void startLogin(ActionEvent event) {
        boolean login = login(usernameField.getText(), passwordField.getText());
        if (login) {
            setCurrentUser(usernameField.getText());
        }
        event.consume();
        loginWindow.setVisible(false);
        anchorPane.setVisible(false);
    }

    public void startSignUp(ActionEvent event) {
        openPane(anchorPane, "SignUpWindow");
        event.consume();
    }

    public boolean login(String username, String password) {
        if (!DataValidation.validUser(username) || !DataValidation.validPass(password)) {
            return false;
        } else if (!AccountQueries.login(username, password)) {
            return false;
        } else {
            return true;
        }
    }
}
