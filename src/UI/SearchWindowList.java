package UI;

import java.util.ArrayList;
import java.util.List;
import Core.Show;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class SearchWindowList {
    public static class HBoxCell extends HBox {
        Label showName = new Label();
        Button addButton = new Button();

        HBoxCell(String labelText, String buttonText) {
            super();

            showName.setText(labelText);
            showName.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(showName, Priority.ALWAYS);

            addButton.setText(buttonText);
            this.getChildren().addAll(showName, addButton);
        }
    }

    public ObservableList<HBoxCell> createContent(ListView listView, ArrayList<Show> showList) {
        List<HBoxCell> list = new ArrayList<>();

        for (Show show : showList) {
            list.add(new HBoxCell(show.getName(), "AddShow"));
        }

        return FXCollections.observableList(list);
    }
}