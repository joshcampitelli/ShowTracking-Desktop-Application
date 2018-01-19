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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
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
        closeStage(editShowBtn);
        event.consume();
    }

    public void editShow(ActionEvent event) {
        MainLayout.HBoxCell hBoxCell = dataList.getSelectionModel().getSelectedItem();

        if (hBoxCell == null) {
            JOptionPane.showMessageDialog(null, "Must select a show!", "Edit", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Show show = hBoxCell.getShow();
        openEditWindow(show);
        closeStage(editShowBtn);
        event.consume();
    }

    public void deleteShow(ActionEvent event) {

        MainLayout.HBoxCell hBoxCell = dataList.getSelectionModel().getSelectedItem();

        if (hBoxCell == null) {
            JOptionPane.showMessageDialog(null, "Must select a show!", "Remove", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Show show = hBoxCell.getShow();
        getCurrentUser().removeShow(show);
        event.consume();
    }

    public void logout(ActionEvent event) {
        closeStage(editShowBtn);
        createStage("LoginWindow", "ShowTracker", loginWidth, loginHeight);
        event.consume();
    }

    public void editAccount(ActionEvent event) {
        closeStage(editShowBtn);
        openAccountWindow();
    }

    public void handle(MouseEvent mouseEvent) {
        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
            if(mouseEvent.getClickCount() == 2){
                MainLayout.HBoxCell hBoxCell = dataList.getSelectionModel().getSelectedItem();

                if (hBoxCell == null) {
                    JOptionPane.showMessageDialog(null, "Must select a show!", "View Show Info", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Show show = hBoxCell.getShow();
                openEditWindow(show);
                closeStage(editShowBtn);
            }
        }
    }

}
