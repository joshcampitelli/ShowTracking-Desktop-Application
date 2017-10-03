package UI;

import java.util.ArrayList;
import java.util.List;
import Core.Show;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


//Todo: Edit window that gets passed the show to edit!!!! Has all buttons handlers, only one button needed in show
public class MainLayout {
    public static class HBoxCell extends HBox {
        Label showName = new Label();
        Label episode = new Label();
        Label season = new Label();
        Button editBtn = new Button("Edit");

        HBoxCell(Show show) {
            super();
            show.setEditBtn(editBtn);
            showName.setText(show.getName());
            season.setText(String.valueOf(show.getSeason()));
            episode.setText(String.valueOf(show.getEpisode()));

            VBox nameVBox = new VBox();
            nameVBox.getChildren().add(showName);
            nameVBox.setMinWidth(325);
            nameVBox.setMaxWidth(325);

            season.setPadding(new Insets(0, 30, 0, 10));
            episode.setPadding(new Insets(0, 50, 0, 30));
            editBtn.setMinWidth(20);

            this.getChildren().addAll(nameVBox, season, episode, editBtn);
        }
    }

    public ObservableList<HBoxCell> createContent(ArrayList<Show> showList) {
        List<HBoxCell> list = new ArrayList<>();
        for (Show show : showList) {
            list.add(new HBoxCell(show));
        }

        return FXCollections.observableList(list);
    }
}