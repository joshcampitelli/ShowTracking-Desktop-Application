package UI.Controllers;

import Model.User;
import Queries.AccountQueries;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AccountController extends Controller implements Initializable {

    private AccountQueries accountQueries;
    private User user;

    @FXML
    public Label idLbl;
    public Label usernameLbl;
    public TextField firstNameFld;
    public TextField lastNameFld;
    public TextField emailFld;
    public TextField passwordFld;
    public TextField ageFld;
    public Button saveBtn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accountQueries = new AccountQueries();
        this.user = getCurrentUser();
        idLbl.setText(idLbl.getText() + " " + user.getUserID());
        usernameLbl.setText(user.getUsername());
        firstNameFld.setPromptText(user.getFirstName());
        lastNameFld.setPromptText(user.getLastName());
        emailFld.setPromptText(user.getEmail());
        ageFld.setPromptText("" + user.getAge());
    }

    public void updateAccount() {
        try {
            accountQueries.updateAccount(user.getUserID(), firstNameFld.getText(), lastNameFld.getText(), emailFld.getText(), Integer.valueOf(ageFld.getText()));
            user.updateAccount();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeStage(saveBtn);
        createStage("MainWindow", "Show Tracker", mainWidth, mainHeight);
    }

    public void cancel () {
        closeStage(saveBtn);
        createStage("MainWindow", "Show Tracker", mainWidth, mainHeight);
    }
}
