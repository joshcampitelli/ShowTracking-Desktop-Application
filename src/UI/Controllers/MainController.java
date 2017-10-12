package UI.Controllers;

import Core.Show;
import UI.MainLayout;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController extends Controller implements Initializable {
    @FXML
    public Button searchBtn;
    public ListView<MainLayout.HBoxCell> dataList;
    private MainLayout searchLayout = new MainLayout();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Show> list = getCurrentUser().getAllShows();
        ObservableList<MainLayout.HBoxCell> observableList = searchLayout.createContent(list);
        dataList.setItems(observableList);
    }

    public void startSearch(ActionEvent event) {
        createStage("SearchWindow", "Show Search", searchWidth, searchHeight);
        this.closeStage(searchBtn);
        event.consume();
    }
}
