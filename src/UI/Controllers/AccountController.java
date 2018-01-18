package UI.Controllers;

import Model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AccountController {

    @FXML
    public Label idLbl;
    public TextField firstNameFld;
    public TextField lastNameFld;
    public TextField usernameFld;
    public TextField emailFld;
    public TextField passwordFld;

    public void initData(User user) {
        idLbl.setText(idLbl.getText() + " " + user.getUserID());
        firstNameFld.setText(user.getFirstName());
        lastNameFld.setText(user.getLastName());
        usernameFld.setText(user.getUsername());
        emailFld.setText(user.getEmail());
    }
}
