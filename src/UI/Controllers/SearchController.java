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
//todo: button references should be here not in show class, the reference to shows added is easier
//todo: everyshow just has a variable "selected" the once user clicks "save" or "return" button it checks
//todo: the list of shows for which have been selected, checkbox or radio button may be better
public class SearchController extends Controller implements Initializable {
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

        ObservableList<SearchWindowList.HBoxCell> observableList = searchWindowList.createContent(list);

        showList.setItems(observableList);
        event.consume();
    }

    public void printSelectedShows(ActionEvent event) {
        if (list != null) {
            for (Show show : list) {
                if (show.getCheckBox().isSelected()) {
                    System.out.println(show.getName());
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
