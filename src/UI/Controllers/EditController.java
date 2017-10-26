package UI.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class EditController extends Controller implements Initializable {
    @FXML
    public Label episode;
    public Label season;
    public Button epIncBtn;     //'+' button for episode
    public Button epDecBtn;     //'-' button for episode
    public Button seIncBtn;     //'+' button for season
    public Button seDecBtn;     //'-' button for season
    public Button cancelBtn;
    public Button confirmBtn;

    private String se = "";
    private String ep = "";

    public void initialize(URL location, ResourceBundle resources) {
        episode.setText(ep);
        season.setText(se);
    }

    public EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            createStage("EditWindow", "Edit Show", searchWidth, searchHeight);
            event.consume();
        }
    };
}
