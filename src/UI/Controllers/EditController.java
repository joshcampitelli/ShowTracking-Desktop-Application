package UI.Controllers;

import Model.Show;
import Queries.UserDataQueries;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.sql.SQLException;

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

    public void incrementSe() {
        int value = Integer.valueOf(season.getText());
        if (value <= 50) {
            season.setText((value + 1) + "");
            show.setSeason(value + 1);
        }
    }

    public void decrementSe() {
        int value = Integer.valueOf(season.getText());
        if (value > 0) {
            season.setText((value - 1) + "");
            show.setSeason(value - 1);
        }
    }

    public void incrementEp() {
        int value = Integer.valueOf(episode.getText());
        if (value <= 50) {
            episode.setText((value + 1) + "");
            show.setEpisode(value + 1);
        }
    }

    public void decrementEp() {
        int value = Integer.valueOf(episode.getText());
        if (value > 0) {
            episode.setText((value - 1) + "");
            show.setEpisode(value - 1);
        }
    }

    public void initData(Show show) {
        this.show = show;
        userDataQueries = new UserDataQueries();
        episode.setText(show.getEpisode() + "");
        season.setText(show.getSeason() + "");
    }

    public void cancel() {
        closeStage(cancelBtn);
        createStage("MainWindow", "Show Tracker", mainWidth, mainHeight);
    }

    public void confirm() {
        try {
            userDataQueries.updateShow(show, getCurrentUser());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeStage(epDecBtn);
        createStage("MainWindow", "Show Tracker", mainWidth, mainHeight);
    }
}
