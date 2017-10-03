package UI.Controllers;

import Core.Show;
import UI.MainLayout;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController extends Controller implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public ListView<MainLayout.HBoxCell> dataList;
    private MainLayout searchLayout = new MainLayout();

    public void getData(ActionEvent event) {
        ArrayList<Show> list = getCurrentUser().getAllShows();
        ObservableList<MainLayout.HBoxCell> observableList = searchLayout.createContent(list);

        dataList.setItems(observableList);
        event.consume();
    }

    public void startSearch(ActionEvent event) {
        createStage("SearchWindow", "Show Search", searchWidth, searchHeight);
        event.consume();
    }
}
