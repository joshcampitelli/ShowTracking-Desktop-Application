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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController extends Controller implements Initializable {
    @FXML /* TopBar Menu Tabs*/
    public ImageView showTab, searchTab, accountTab, settingsTab, exitTab;
    public ImageView arr1, arr2, arr3, arr4;

    @FXML /* Windows Linked to Tabs */
    public AnchorPane content;

    @FXML
    public Button editShowBtn;
    public ListView<HBoxCell> dataList;
    public MenuBar myMenuBar;
    public TextField searchFld;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        arr1.setVisible(false);
        arr2.setVisible(false);
        arr3.setVisible(false);
        arr4.setVisible(false);
        try {
            File file = new File("src\\UI\\FXML\\NewLoginWindow.fxml");
            URL url = file.toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            AnchorPane loginWindow = loader.load();
            content.getChildren().add(loginWindow);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleButtonAction (MouseEvent event) {
        try {
            if (event.getTarget() == showTab) {
                File file = new File("src\\UI\\FXML\\NewShowsWindow.fxml");
                URL url = file.toURI().toURL();
                FXMLLoader loader = new FXMLLoader(url);
                AnchorPane homeWindow = loader.load();
                content.getChildren().add(homeWindow);
                arr1.setVisible(true);
                arr2.setVisible(false);
                arr3.setVisible(false);
                arr4.setVisible(false);
            } else if (event.getTarget() == searchTab) {
                File file = new File("src\\UI\\FXML\\NewSearchWindow.fxml");
                URL url = file.toURI().toURL();
                FXMLLoader loader = new FXMLLoader(url);
                AnchorPane searchWindow = loader.load();
                content.getChildren().add(searchWindow);
                arr1.setVisible(false);
                arr2.setVisible(true);
                arr3.setVisible(false);
                arr4.setVisible(false);
            } else if (event.getTarget() == accountTab) {
                File file = new File("src\\UI\\FXML\\NewAccountWindow.fxml");
                URL url = file.toURI().toURL();
                FXMLLoader loader = new FXMLLoader(url);
                AnchorPane accountWindow = loader.load();
                content.getChildren().add(accountWindow);
                arr1.setVisible(false);
                arr2.setVisible(false);
                arr3.setVisible(true);
                arr4.setVisible(false);
            } else if (event.getTarget() == settingsTab) {

            } else if (event.getTarget() == exitTab) {
                closeImageView(exitTab);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
