package UI.Controllers;

import Model.Show;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class NewShowController extends Controller implements Initializable {
    //Todo: Add total episodes & seasons
    @FXML
    public Label titleLbl;
    public Label overviewLbl;
    public Label genreLbl;
    public Label runtimeLbl;
    public Label releaseLbl;
    public ImageView coverImage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void initData(Show show) {
        titleLbl.setText(show.getName());
        overviewLbl.setText(show.getOverview());
        genreLbl.setText(show.getGenre());
        runtimeLbl.setText(show.getRuntime() + " Minutes");
        releaseLbl.setText(show.getStartDate());
        coverImage.setImage(new Image(show.getImage(), true));
    }
}
