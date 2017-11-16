package UI.Controllers;

import Model.Show;
import Queries.ShowQueries;
import Queries.UserDataQueries;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
    private UserDataQueries userDataQueries;

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

        cancelBtn.setOnAction(event -> closeStage(cancelBtn));
        confirmBtn.setOnAction(event -> {
            //update user data queries 
        });
    }
}
