package UI.Controllers;

import Model.Show;
import Queries.UserDataQueries;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ShowController extends Controller implements Initializable{
    //todo: Fix cancel bug where it still changes temporarily (se & ep #)
    @FXML /*Show Info*/
    public Label releaseLbl;
    public Label genreLbl;
    public Label runtimeLbl;
    public Label overviewLbl;
    public ImageView coverImage;
    public AnchorPane anchorPane;
    //public Label seasonsLbl;
    //public Label episodesLbl;
    //public Label ratingLbl;

    @FXML /*Edit User Status*/
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void initData(Show show) {
        this.show = show;
        userDataQueries = new UserDataQueries();
        episode.setText(show.getEpisode() + "");
        season.setText(show.getSeason() + "");

        overviewLbl.setText(show.getOverview());
        genreLbl.setText(show.getGenre());
        runtimeLbl.setText(show.getRuntime() + " Minutes");
        releaseLbl.setText(show.getStartDate());
        coverImage.setImage(new Image(show.getImage(), true));
    }

    /*Edit Functionality*/
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

    public void cancel() {
        returnHomePage();
    }

    public void confirm() {
        try {
            userDataQueries.updateShow(show, getCurrentUser());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        returnHomePage();
    }

    public void returnHomePage() {
        try {
            File file = new File("src/UI/FXML/NewShowsWindow.fxml");
            URL url = file.toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            AnchorPane homeWindow = loader.load();
            anchorPane.getChildren().removeAll();
            anchorPane.getChildren().add(homeWindow);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
