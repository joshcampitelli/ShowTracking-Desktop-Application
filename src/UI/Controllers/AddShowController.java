package UI.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import DataStorage.ShowQueries;

public class AddShowController extends Controller implements Initializable {
    @FXML
    public TextField nameFld;
    public TextField yearFld;
    public TextField genreFld;
    public TextField runtimeFld;
    public TextField seasonsFld;
    public TextField episodesFld;
    public TextField ratingFld;
    public Button addBtn;
    public Button cancelBtn;

    ShowQueries showQueries;

    public void initialize(URL location, ResourceBundle resources) {
        showQueries = new ShowQueries();
    }

    private void addShow() {
        try {
            showQueries.addNewShow(nameFld.getText(), Integer.valueOf(yearFld.getText()), genreFld.getText(), Integer.valueOf(runtimeFld.getText()), Integer.valueOf(seasonsFld.getText()), Integer.valueOf(episodesFld.getText()), Integer.valueOf(ratingFld.getText()));
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }
}