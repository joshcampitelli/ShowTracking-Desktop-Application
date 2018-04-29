package UI.Controllers;

import Model.Show;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class GridWindowController extends Controller implements Initializable {
    @FXML
    public GridPane gridpane;
    public TextField searchFld;
    public ScrollPane scrollPane;
    public Button findBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCurrentUser("joshcamp");
        ArrayList<Show> list = getCurrentUser().getAllShows();
        ArrayList<CustomStackPane> list2 = new ArrayList<>();
        for (Show show : list) {
            list2.add(new CustomStackPane(show));
        }

        ObservableList<CustomStackPane> observableList = FXCollections.observableList(list2);
        FilteredList<CustomStackPane> filteredList = new FilteredList(observableList);

        gridpane.setHgap(10);
        gridpane.setVgap(10);
        gridpane.setPadding(new Insets(0, 10, 0, 10));

        scrollPane.setFitToWidth(true);
        gridpane.setHgrow(scrollPane, Priority.ALWAYS);
        scrollPane.setContent(gridpane);

        gridpane.setStyle("-fx-background-color: #1d1d1d;\n");

        int i = 0, j = 0;
        for (Show show : list) {
            CustomStackPane customStackPane = new CustomStackPane(show);
            gridpane.add(customStackPane, i, j);
            if (i == 4) {
                i = 0;
                j++;
            } else {
                i++;
            }
        }

        searchFld.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(element -> {
                gridpane.getChildren().removeAll();
                if (newValue == null || newValue.isEmpty())
                    return true;
                if (element.checkValue(newValue))
                    return true; // Filter matches
                return false;
            });

            int k = 0, l = 0;
            for (CustomStackPane customStackPane : filteredList) {
                gridpane.add(customStackPane, k, l);
                if (k == 4) {
                    k = 0;
                    l++;
                } else {
                    k++;
                }
            }
        });
    }

    public void startSearch(ActionEvent event) {
        createStage("SearchWindow", "Show Search", searchWidth, searchHeight);
        closeStage(findBtn);
        event.consume();
    }
}
