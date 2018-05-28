package UI.Controllers;

import Model.Show;
import UI.ListLayouts.MainLayout;
import UI.ListLayouts.MainLayout.HBoxCell;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeController extends Controller implements Initializable {
    @FXML
    public Button editShowBtn;
    public ListView<HBoxCell> dataList;
    public TextField searchFld;

    private MainLayout mainLayout = new MainLayout();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Show> list = getCurrentUser().getAllShows();
        ObservableList<HBoxCell> observableList = mainLayout.createContent(list);
        FilteredList<HBoxCell> filteredList = mainLayout.createContent(observableList);
        dataList.setItems(filteredList);

        searchFld.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(element -> {
                if (newValue == null || newValue.isEmpty())
                    return true;

                if (element.checkValue(newValue))
                    return true; // Filter matches

                return false;
            });
            dataList.setItems(filteredList);
        });
    }

    public void editShow(ActionEvent event) {
        HBoxCell hBoxCell = dataList.getSelectionModel().getSelectedItem();

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

        HBoxCell hBoxCell = dataList.getSelectionModel().getSelectedItem();

        if (hBoxCell == null) {
            JOptionPane.showMessageDialog(null, "Must select a show!", "Remove", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Show show = hBoxCell.getShow();
        getCurrentUser().removeShow(show);
        event.consume();
        closeStage(editShowBtn);
        createStage("MainWindow", "Show Tracking", mainWidth, mainHeight);
    }

    public void viewShow(MouseEvent mouseEvent) {
        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
            if(mouseEvent.getClickCount() == 2){
                HBoxCell hBoxCell = dataList.getSelectionModel().getSelectedItem();

                if (hBoxCell == null) {
                    JOptionPane.showMessageDialog(null, "Must select a show!", "View Show Info", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Show show = hBoxCell.getShow();

                openShowWindow(show);
            }
        }
    }
}
