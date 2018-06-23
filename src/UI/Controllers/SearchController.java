package UI.Controllers;

import Model.Show;
import Queries.ShowQueries;
import Queries.UserDataQueries;
import UI.ListLayouts.SearchLayout;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchController extends Controller implements Initializable {

    private SearchLayout searchLayout = new SearchLayout();
    private ShowQueries showQueries = new ShowQueries();
    private UserDataQueries userDataQueries = new UserDataQueries();

    @FXML
    public ListView<SearchLayout.HBoxCell> showList;
    public Button addShowsBtn;
    public Button cancelBtn;
    public TextField searchFld;
    public AnchorPane searchWindow;

    private ArrayList<Show> list;
    private ArrayList<Show> userShows;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list = new ArrayList<>();
        try {
            userShows = userDataQueries.getLoggedShows(getCurrentUser().getUserID());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            list = showQueries.getAvailableShows();

            if (!userShows.isEmpty()) {
                list.removeAll(userShows);
            }

        } catch (SQLException e) {}

        ObservableList<SearchLayout.HBoxCell> observableList = searchLayout.createContent(list);
        FilteredList<SearchLayout.HBoxCell> filteredList = new FilteredList<>(observableList);
        showList.setItems(filteredList);

        searchFld.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(element -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                if (element.checkValue(newValue)) {
                    return true; // Filter matches
                }

                //Add your filtering conditions here

                return false; // Does not match
            });
            showList.setItems(filteredList);
        });
    }

    public void addSelectedShows(ActionEvent event) {
        for (Show show : list) {
            if (show.getSelect().isSelected()) {
                getCurrentUser().addShow(show);
            }
        }
    }

    //todo: swap menu arrows each anchor swap
    public void closeWindow(ActionEvent event) {
        openPane(searchWindow, "HomeWindow");
        event.consume();
    }
}
