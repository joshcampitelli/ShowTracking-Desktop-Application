package UI.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.ComboBoxListCell;

import java.net.URL;
import java.util.ResourceBundle;


public class MainWindowController extends Controller implements Initializable {
    public static final ObservableList names = FXCollections.observableArrayList();
    public ListView dataList;


    public void getData(ActionEvent event) {
        //names.setEditable(true);

        names.addAll(
                "Adam", "Alex", "Alfred", "Albert",
                "Brenda", "Connie", "Derek", "Donny",
                "Lynne", "Myrtle", "Rose", "Rudolph",
                "Tony", "Trudy", "Williams", "Zach"
        );

        dataList.setCellFactory(ComboBoxListCell.forListView(names));

    }

    public void initialize(URL location, ResourceBundle resources) {}

}
