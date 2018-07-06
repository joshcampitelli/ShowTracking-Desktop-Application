package UI.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController extends Controller implements Initializable {
    @FXML /* TopBar Menu Tabs*/
    public ImageView showTab, searchTab, accountTab, settingsTab, exitTab;
    public ImageView arr1, arr2, arr3, arr4; //Arrows

    @FXML /* Windows Linked to Tabs */
    public AnchorPane content;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setArrows(arr1, arr2, arr3, arr4);
        toggleArrows(0);
        openPane(content, "LoginWindow");
    }

    @FXML
    private void handleButtonAction (MouseEvent event) {
        if (event.getTarget() == showTab && getCurrentUser() != null) {
            openPane(content, "HomeWindow");
            toggleArrows(1);
        } else if (event.getTarget() == searchTab && getCurrentUser() != null) {
            toggleArrows(2);
            openPane(content, "SearchWindow");
        } else if (event.getTarget() == accountTab && getCurrentUser() != null) {
            openPane(content, "AccountWindow");
            toggleArrows(3);
        } else if (event.getTarget() == settingsTab) {
            //Dark/Light/Blue Theme changes through CSS
        } else if (event.getTarget() == exitTab) {
            closeImageView(exitTab);
        }
    }
}
