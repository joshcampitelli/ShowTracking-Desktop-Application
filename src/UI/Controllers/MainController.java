package UI.Controllers;

import Core.Show;
import DataStorage.ShowQueries;
import UI.SearchWindowList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class MainController extends Controller implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {}
    public ListView<SearchWindowList.HBoxCell> dataList;
    private SearchWindowList searchWindowList = new SearchWindowList();
    private ShowQueries showQueries = new ShowQueries();

    public void getData(ActionEvent event) {
        ArrayList<Show> list = getCurrentUser().getAllShows();
        ObservableList<SearchWindowList.HBoxCell> observableList = searchWindowList.createContent(list);

        dataList.setItems(observableList);
        event.consume();
    }

    public void startSearch(ActionEvent event) {
        createStage("SearchWindow", "Show Search", searchWidth, searchHeight);
    }
}
