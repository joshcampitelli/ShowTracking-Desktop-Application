package UI.Controllers;

import Core.Show;
import Core.User;
import DataStorage.ShowQueries;
import UI.SearchWindowList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import java.sql.SQLException;
import java.util.ArrayList;


public class MainController extends Controller implements Initializable {
    public static final ObservableList names = FXCollections.observableArrayList();
    public ListView<SearchWindowList.HBoxCell> dataList;
    private SearchWindowList searchWindowList = new SearchWindowList();
    private ShowQueries showQueries = new ShowQueries();

    public void getData(ActionEvent event) {
        ArrayList<Show> list = new ArrayList<>();

        try {
            list = showQueries.getAvailableShows();
        } catch (SQLException e) {}

        ObservableList<SearchWindowList.HBoxCell> observableList = searchWindowList.createContent(list);

        dataList.setItems(observableList);
    }

    public void startSearch(ActionEvent event) {
        createStage("SearchWindow", "Show Search", searchWidth, searchHeight);
    }
}
