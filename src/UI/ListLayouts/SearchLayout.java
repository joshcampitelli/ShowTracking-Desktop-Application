package UI.ListLayouts;

import java.util.ArrayList;
import java.util.List;
import Model.Show;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class SearchLayout {
    public static class HBoxCell extends HBox {
        Label showName = new Label();

        HBoxCell(String labelText, CheckBox showSelect) {
            super();

            showName.setText(labelText);
            showName.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(showName, Priority.ALWAYS);
            this.getChildren().addAll(showName, showSelect);
        }

        public boolean checkValue(String value) {
            return showName.getText().toLowerCase().contains(value.toLowerCase());
        }
    }

    public ObservableList<HBoxCell> createContent(ArrayList<Show> showList) {
        List<HBoxCell> list = new ArrayList<>();

        CheckBox showSelect;

        for (Show show : showList) {
            showSelect = new CheckBox();
            show.setSelect(showSelect);
            list.add(new HBoxCell(show.getName(), showSelect));
        }

        return FXCollections.observableList(list);
    }
}