package UI.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AlertWindowController extends Controller implements Initializable {
    //States
    final int ERROR_MESSAGE = 0;
    final int INFO_MESSAGE = 1;
    final int GENERAL_MESSAGE = 2;
    //todo: redesign fxml window to allow for images based on the state of the controller.

    //Attributes
    private int state;
    private String title;
    private String message;

    @FXML
    public Label titleLbl;
    public Label messageLbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titleLbl.setText(title);
        messageLbl.setText(message);
    }

    public void initData(int state, String title, String message) {
        this.state = state;
        this.title = title;
        this.message = message;
    }
}
