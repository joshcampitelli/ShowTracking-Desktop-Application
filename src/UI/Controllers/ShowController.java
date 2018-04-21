package UI.Controllers;

import Model.Show;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowController extends Controller implements Initializable{

    @FXML
    public Label showIDLbl;
    public Label nameLbl;
    public Label dateLbl;
    public Label genreLbl;
    public Label runtimeLbl;
    public Label seasonsLbl;
    public Label episodesLbl;
    public Label ratingLbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void initData(Show show) {
        showIDLbl.setText(showIDLbl.getText() + " " + show.getID());
        nameLbl.setText(nameLbl.getText() + " " + show.getName());
        dateLbl.setText(dateLbl.getText() + " " + show.getStartDate());
        genreLbl.setText(genreLbl.getText() + " " + show.getGenre());
        runtimeLbl.setText(runtimeLbl.getText() + " " + show.getRuntime() + " minutes");
        seasonsLbl.setText(seasonsLbl.getText() + " " + show.getSeasons());
        episodesLbl.setText(episodesLbl.getText() + " " + show.getEpisodes());
        ratingLbl.setText(ratingLbl.getText() + " " + show.getRating() + "% (IMDb)");
    }
}
