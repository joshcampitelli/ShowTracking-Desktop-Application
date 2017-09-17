package UI.Controllers;

import Core.Framework;
import Core.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController extends Controller implements Initializable {
    private Framework framework = new Framework();
    private User user;

    @FXML
    public TextField usernameField;
    public TextField passwordField;


    @FXML
    public Button loginbtn;
    public Button signupbtn;

    public void startLogin(ActionEvent event) {
        boolean login = framework.login(usernameField.getText(), passwordField.getText());
        if (login) {
            user = new User(usernameField.getText());
            closeStage(loginbtn);
            createStage("MainWindow", "Show Tracking", mainWidth, mainHeight);
        }
    }

    public void startSignup(ActionEvent event) {
        closeStage(signupbtn);
        createStage("SignUpWindow", "SignUp Window", signupWidth, signupHeight);
    }
}
