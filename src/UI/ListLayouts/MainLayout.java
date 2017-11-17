package UI.ListLayouts;

import java.util.ArrayList;
import java.util.List;
import Model.Show;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


//Todo: Edit window that gets passed the show to edit!!!! Has all buttons handlers, only one button needed in show
public class MainLayout {
    public static class HBoxCell extends HBox {
        Label showName = new Label();
        Label episode = new Label();
        Label season = new Label();
        Show show;

        HBoxCell(Show show) {
            super();
            this.show = show;
            showName.setText(show.getName());
            season.setText(String.valueOf(show.getSeason()));
            episode.setText(String.valueOf(show.getEpisode()));

            VBox nameVBox = new VBox();
            nameVBox.getChildren().add(showName);
            nameVBox.setMinWidth(325);
            nameVBox.setMaxWidth(325);

            season.setPadding(new Insets(0, 30, 0, 10));
            episode.setPadding(new Insets(0, 50, 0, 30));
            this.getChildren().addAll(nameVBox, episode, season);
        }

        public Show getShow() {
            return show;
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