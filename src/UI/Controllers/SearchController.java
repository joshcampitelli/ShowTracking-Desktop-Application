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

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchController extends Controller implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {}
    public static final ObservableList names = FXCollections.observableArrayList();
    private SearchWindowList searchWindowList = new SearchWindowList();
    private ShowQueries showQueries = new ShowQueries();
    private ArrayList<Show> showsToAdd = new ArrayList<>();

    @FXML
    public ListView<SearchWindowList.HBoxCell> showList;
    public Button searchBtn;
    public Button refreshListBtn;
    public TextField searchField;

    private ArrayList<Show> list;

    public void getData(ActionEvent event) {
        list = new ArrayList<>();

        try {
            list = showQueries.getAvailableShows();
        } catch (SQLException e) {}
        for (Show show : list) {
            if (getCurrentUser().getAllShows().contains(show)) {
                list.remove(show);
            }
        }

        ObservableList<SearchWindowList.HBoxCell> observableList = searchWindowList.createContent(list);

        showList.setItems(observableList);
        event.consume();
    }

    public void printSelectedShows(ActionEvent event) {
        if (list != null) {
            for (Show show : list) {
                if (show.getCheckBox().isSelected()) {
                    getCurrentUser().addShow(show);
                }
            }
        }
    }

    public void addShowToList(Show show) {
        showsToAdd.add(show);
    }

    public ArrayList<Show> getShowsToAdd() {
        return showsToAdd;
    }
}
