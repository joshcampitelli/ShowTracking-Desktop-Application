package UI.ListLayouts;

import java.util.ArrayList;
import java.util.List;
import Model.Show;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


//Todo: Edit window that gets passed the show to edit!!!! Has all buttons handlers, only one button needed in show
public class MainLayout {
    public static class HBoxCell extends HBox {
        Label showName = new Label();
        Label episode = new Label();
        Label season = new Label();
        Show show;
        ImageView imageView;

        private final Image IMAGE_TWITTER = new Image("http://files.softicons.com/download/social-media-icons/fresh-social-media-icons-by-creative-nerds/png/64x64/twitter-bird.png");

        HBoxCell(Show show) {
            super();
            this.show = show;
            showName.setText(show.getName());
            season.setText(String.valueOf(show.getSeason()));
            episode.setText(String.valueOf(show.getEpisode()));

            VBox nameVBox = new VBox();
            nameVBox.getChildren().add(showName);
            nameVBox.setMinWidth(250);
            nameVBox.setMaxWidth(250);

            Image image;
            if (show.getImage().equals(""))
                image = new Image("http://www.iconsplace.com/download/black-rectangle-512.png", true);
            else
                image = new Image(show.getImage(), true);

            imageView = new ImageView(image);
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(100);
            imageView.setFitHeight(120);

            //Align Text in cells
            showName.setPadding(new Insets(35, 0, 0, 20));
            season.setPadding(new Insets(35, 50, 0, 10));
            episode.setPadding(new Insets(35, 50, 0, 30));

            this.getChildren().addAll(imageView, nameVBox, episode, season);
        }

        public boolean checkValue(String value) {
            return showName.getText().toLowerCase().contains(value.toLowerCase());
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

    public FilteredList<HBoxCell> createContent(ObservableList<HBoxCell> showList) {
        return new FilteredList<>(showList);
    }
}