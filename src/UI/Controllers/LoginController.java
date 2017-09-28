package UI.Controllers;

import Core.Framework;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController extends Controller implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {}
    private Framework framework = new Framework();

    @FXML
    public TextField usernameField;
    public TextField passwordField;

    @FXML
    public Button loginbtn;
    public Button signupbtn;

    public void startLogin(ActionEvent event) {
        boolean login = framework.login(usernameField.getText(), passwordField.getText());
        if (login) {
            setCurrentUser(usernameField.getText());
            closeStage(loginbtn);
            createStage("MainWindow", "Show Tracking", mainWidth, mainHeight);
        }
    }

    public void startSignup(ActionEvent event) {
        closeStage(signupbtn);
        createStage("SignUpWindow", "SignUp Window", signupWidth, signupHeight);
    }
}
