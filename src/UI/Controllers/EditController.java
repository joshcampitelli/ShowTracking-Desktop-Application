package UI.Controllers;

import Model.Show;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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

    private Show show;

    public EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            createStage("EditWindow", "Edit Show", 300, 300);
            event.consume();
        }
    };

    public void initData(Show show) {
        this.show = show;
        addListeners();
        episode.setText(show.getEpisode() + "");
        season.setText(show.getSeason() + "");
    }

    private void addListeners() {
        epIncBtn.setOnAction(event -> {
            int value = Integer.valueOf(episode.getText());
            if (value <= 50)
                episode.setText((value+1) + "");
        });

        epDecBtn.setOnAction(event -> {
            int value = Integer.valueOf(episode.getText());
            if (value > 0)
                episode.setText((value-1) + "");
        });

        seIncBtn.setOnAction(event -> {
            int value = Integer.valueOf(season.getText());
            if (value <= 50)
                season.setText((value+1) + "");
        });

        seDecBtn.setOnAction(event -> {
            int value = Integer.valueOf(season.getText());
            if (value > 0)
                season.setText((value-1) + "");
        });
    }
}
