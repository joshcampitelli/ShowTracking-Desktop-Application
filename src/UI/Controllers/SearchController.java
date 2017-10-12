package UI.Controllers;

import Core.Show;
import DataStorage.ShowQueries;
import UI.SearchLayout;
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
    public void initialize(URL location, ResourceBundle resources) {
        list = new ArrayList<>();
        try {
            list = showQueries.getAvailableShows();
        } catch (SQLException e) {}

        //Todo: Remove already tracked shows from the search window before displaying.
        ObservableList<SearchLayout.HBoxCell> observableList = searchLayout.createContent(list);

        showList.setItems(observableList);
    }

    private SearchLayout searchLayout = new SearchLayout();
    private ShowQueries showQueries = new ShowQueries();

    @FXML
    public ListView<SearchLayout.HBoxCell> showList;
    public Button searchBtn;
    public Button addShowsBtn;
    public Button cancelBtn;
    public TextField searchField;

    private ArrayList<Show> list;

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
            createStage("MainWindow", "Show Tracker", mainWidth, mainHeight);
            event.consume();
        }
    }

    public void closeWindow(ActionEvent event) {
        closeStage(cancelBtn);
        createStage("MainWindow", "Show Tracker", mainWidth, mainHeight);
        event.consume();
    }
}
