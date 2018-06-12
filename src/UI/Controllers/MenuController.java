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
        arr1.setVisible(false);
        arr2.setVisible(false);
        arr3.setVisible(false);
        arr4.setVisible(false);
        openPane(content, "LoginWindow");
    }

    @FXML
    private void handleButtonAction (MouseEvent event) {
        if (event.getTarget() == showTab) {
            openPane(content, "ShowsWindow");
            arr1.setVisible(true);
            arr2.setVisible(false);
            arr3.setVisible(false);
            arr4.setVisible(false);
        } else if (event.getTarget() == searchTab) {
            openPane(content, "SearchWindow");
            arr1.setVisible(false);
            arr2.setVisible(true);
            arr3.setVisible(false);
            arr4.setVisible(false);
        } else if (event.getTarget() == accountTab) {

            try { /*Changes controller improperly*/
                File file = new File("src/UI/FXML/AccountWindow.fxml");
                URL url = file.toURI().toURL();
                FXMLLoader loader = new FXMLLoader(url);
               // AccountController accountController = loader.getController();
               // if (getCurrentUser() != null) {
               //     accountController.initData(getCurrentUser());
               // }
                AnchorPane newPane = loader.load();
                content.getChildren().removeAll();
                content.getChildren().add(newPane);
            } catch (IOException e) {
                e.printStackTrace();
            }

            arr1.setVisible(false);
            arr2.setVisible(false);
            arr3.setVisible(true);
            arr4.setVisible(false);
        } else if (event.getTarget() == settingsTab) {
            //Dark/Light/Blue Theme changes through CSS
        } else if (event.getTarget() == exitTab) {
            closeImageView(exitTab);
        }
    }
}
