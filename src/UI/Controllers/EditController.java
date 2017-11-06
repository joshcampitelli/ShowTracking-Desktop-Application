package UI.Controllers;

import Core.Show;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditController extends Controller {
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

    public EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            createStage("EditWindow", "Edit Show", searchWidth, searchHeight);
            event.consume();
        }
    };

    //todo: Known Issue.
    public EditController(Show show) {/*
        if (show != null) {
            System.out.println(show.getName());
            se = show.getSeason() + "";
            ep = show.getEpisode() + "";
        }
        try {
            Parent root = FXMLLoader.load(getClass().getResource("src/UI/FXML/EditWindow.fxml"));

            Stage primaryStage = new Stage();
            primaryStage.setTitle("ShowTracker");
            primaryStage.setScene(new Scene(root, 275, 300));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
    }
}
