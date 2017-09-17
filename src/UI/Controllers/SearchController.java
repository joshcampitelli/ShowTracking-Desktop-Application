package UI.Controllers;

import Core.Show;
import DataStorage.ShowQueries;
import UI.SearchWindowList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.ArrayList;

public class SearchController extends Controller implements Initializable {
    public static final ObservableList names = FXCollections.observableArrayList();
    private SearchWindowList searchWindowList = new SearchWindowList();
    private ShowQueries showQueries = new ShowQueries();

    @FXML
    public ListView<SearchWindowList.HBoxCell> showList;
    public Button searchBtn;
    public Button refreshListBtn;
    public TextField searchField;

    public void getData(ActionEvent event) {
        ArrayList<Show> list = new ArrayList<>();

        try {
            list = showQueries.getAvailableShows();
        } catch (SQLException e) {}

        ObservableList<SearchWindowList.HBoxCell> observableList = searchWindowList.createContent(showList, list);

        showList.setItems(observableList);
    }
}
