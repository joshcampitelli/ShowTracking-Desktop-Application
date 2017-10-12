package UI;

import Core.Show;
import javafx.scene.control.Button;

public class EditButton extends Button {
    private Show show;

    public EditButton(String text) {
        super(text);
        this.setOnAction((event) -> {
            System.out.println("Show: " + show.getName());
        });
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }
}
