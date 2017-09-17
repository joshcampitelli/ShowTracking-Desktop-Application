package UI;

import java.util.ArrayList;
import java.util.List;
import Core.Show;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class SearchWindowList {
    public static class HBoxCell extends HBox {
        Label showName = new Label();

        HBoxCell(String labelText, Button addButton) {
            super();

            showName.setText(labelText);
            showName.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(showName, Priority.ALWAYS);

            addButton.setText("AddShow");
            this.getChildren().addAll(showName, addButton);
        }
    }

    public ObservableList<HBoxCell> createContent(ArrayList<Show> showList) {
        List<HBoxCell> list = new ArrayList<>();

        Button addButton;
        for (Show show : showList) {
            addButton = new Button();
            show.setAddButton(addButton);
            list.add(new HBoxCell(show.getName(), addButton));
        }

        return FXCollections.observableList(list);
    }
}