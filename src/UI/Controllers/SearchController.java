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
import java.util.HashMap;
import java.util.ResourceBundle;

public class SearchController extends Controller implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    private SearchWindowList searchWindowList = new SearchWindowList();
    private ShowQueries showQueries = new ShowQueries();

    @FXML
    public ListView<SearchWindowList.HBoxCell> showList;
    public Button searchBtn;
    public Button refreshListBtn;
    public Button addShowsBtn;
    public Button cancelBtn;
    public TextField searchField;

    private ArrayList<Show> list;

    public void getData(ActionEvent event) {
        list = new ArrayList<>();
        try {
            list = showQueries.getAvailableShows();
        } catch (SQLException e) {}

        for (Show show : getCurrentUser().getAllShows()) {
            System.out.println(show.getName());
        }

        //Error here: Need alternate method to remove already tracked shows from search.
        for (Show userShow : getCurrentUser().getAllShows()) {
            for (Show selectedShow : list) {
                if (selectedShow.getID() == userShow.getID()) {
                    list.remove(selectedShow);
                }
            }
        }

        ObservableList<SearchWindowList.HBoxCell> observableList = searchWindowList.createContent(list);

        showList.setItems(observableList);
        event.consume();
    }

    public void addSelectedShows(ActionEvent event) {
        boolean addShow = false;

        for (Show show : list) {
            if (show.getCheckBox().isSelected()) {
                getCurrentUser().addShow(show);
                addShow = true;
            }
        }

        if (addShow) {
            //Only close stage if user has selected a show.
            closeStage(addShowsBtn);
            event.consume();
        }
    }

    public void closeWindow(ActionEvent event) {
        closeStage(cancelBtn);
        event.consume();
    }
}
