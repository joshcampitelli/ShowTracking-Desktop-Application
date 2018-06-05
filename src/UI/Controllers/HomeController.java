package UI.Controllers;

import Model.Show;
import UI.ListLayouts.MainLayout;
import UI.ListLayouts.MainLayout.HBoxCell;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeController extends Controller implements Initializable {
    @FXML
    public Button editShowBtn;
    public ListView<HBoxCell> dataList;
    public TextField searchFld;
    public AnchorPane showWindow;

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

        try {
            Show show = hBoxCell.getShow();

            File file = new File("src/UI/FXML/NewShowEditWindow.fxml");
            URL url = file.toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            AnchorPane editWindow = loader.load();

            ShowController controller = loader.getController();
            controller.initData(show);

            showWindow.getChildren().add(editWindow);
            event.consume();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    /*Broken*/
    public void viewShow(MouseEvent mouseEvent) {
        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
            if(mouseEvent.getClickCount() == 2){
                HBoxCell hBoxCell = dataList.getSelectionModel().getSelectedItem();

                if (hBoxCell == null) {
                    JOptionPane.showMessageDialog(null, "Must select a show!", "View Show Info", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Show show = hBoxCell.getShow();

            }
        }
    }
}
