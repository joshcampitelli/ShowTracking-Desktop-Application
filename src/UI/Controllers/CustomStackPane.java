package UI.Controllers;

import Model.Show;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class CustomStackPane extends StackPane {
    private Show show;
    private ImageView imageView;
    private TextArea showInfo;

    public CustomStackPane(Show show) {
        this.show = show;
        imageView = new ImageView(new Image(show.getImage(), true));
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(120);
        imageView.setFitHeight(145);

        showInfo = new TextArea(
            show.getName() + "\n" +
            "Season: " + show.getSeason() + "\n" +
            "Episode: " + show.getEpisode());
        showInfo.setPrefSize(105, 145);
        showInfo.setWrapText(true);
        showInfo.setEditable(false);

        Pane pane = new Pane();
        pane.setPrefSize(105, 145);
        pane.getChildren().addAll(showInfo);
        pane.setVisible(false);

        getChildren().addAll(imageView, pane);
        setAlignment(Pos.CENTER);

        imageView.setOnMouseClicked((MouseEvent e) -> {
            pane.setVisible(true);
            imageView.setVisible(false);
        });

        showInfo.setOnMouseClicked((MouseEvent e) -> {
            pane.setVisible(false);
            imageView.setVisible(true);
        });

    }

    public boolean checkValue(String name) {
        return show.getName().toLowerCase().equals(name.toLowerCase());
    }
}
