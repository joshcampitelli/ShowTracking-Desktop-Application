package UI;

import Core.Show;
import UI.Controllers.EditController;
import javafx.scene.control.Button;

public class EditButton extends Button {
    private Show show;
    private EditController editController;

    public EditButton(String text) {
        super(text);
        editController = new EditController(show);
        this.setOnAction(editController.buttonHandler);
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }
}
