package UI.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AlertWindowController  extends Controller implements Initializable {
    //States
    final int ERROR_MESSAGE = 0;
    final int INFO_MESSAGE = 1;
    final int GENERAL_MESSAGE = 2;

    //Attributes
    private int state;
    private String title;
    private String message;

    @FXML
    public Label titleLbl;
    public Label messageLbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initData(int state, String title, String message) {

    }

    /**
     * Todo: Implement a similar window style to the AWT Java window: constructor accepts
     * todo: a defined int value (ie. 0 for error, 1 for information, 2 for general message)
     */
}
