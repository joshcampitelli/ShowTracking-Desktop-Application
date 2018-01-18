package UI.Controllers;

import Model.Show;
import UI.ListLayouts.MainLayout;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController extends Controller implements Initializable {
    @FXML
    public Button editShowBtn;
    public ListView<MainLayout.HBoxCell> dataList;
    private MainLayout searchLayout = new MainLayout();

    @FXML
    public MenuBar myMenuBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Show> list = getCurrentUser().getAllShows();
        ObservableList<MainLayout.HBoxCell> observableList = searchLayout.createContent(list);
        dataList.setItems(observableList);
    }

    public void startSearch(ActionEvent event) {
        createStage("SearchWindow", "Show Search", searchWidth, searchHeight);
        this.closeStage(editShowBtn);
        event.consume();
    }

    public void editShow(ActionEvent event) {
        Show show = dataList.getSelectionModel().getSelectedItem().getShow();
        openEditWindow(show);
        this.closeStage(editShowBtn);
        event.consume();
    }

    /**
     * Closes the MainWindow
     * @param event
     */
    public void closeMain(ActionEvent event) {
        Stage stage = (Stage) myMenuBar.getScene().getWindow();
        stage.close();
        event.consume();
    }

    public void logout(ActionEvent event) {
        closeMain(event);
        createStage("LoginWindow", "ShowTracker", loginWidth, loginHeight);
        event.consume();
    }

    public void editAccount(ActionEvent event) {
        closeMain(event);
        openAccountWindow();
    }
}
