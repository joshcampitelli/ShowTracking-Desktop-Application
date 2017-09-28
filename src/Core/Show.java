package Core;

import UI.Controllers.SearchController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class Show {
    private int season;
    private int episode;
    private String name;
    private String genre;
    private int rating;
    private int ID;
    private String image;

    //Includes reference to the show's Button on the UI
    private CheckBox select; //for adding the show to user directory

    public Show(String show, int season, int episode) {
        this.name = show;
        this.season = season;
        this.episode = episode;
    }

    public Show(String show, String genre, int season, int episode) {
        this.name = show;
        this.genre = genre;
        this.season = season;
        this.episode = episode;
    }

    public Show(String name, String image, int ID) {
        this.name = name;
        this.image = image;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public CheckBox getCheckBox() {
        return select;
    }

    public void setCheckBox(CheckBox select) {
        this.select = select;
    }
/*
    private EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            addButton.setDisable(true);
            System.out.println(name);
            SearchController searchController = new SearchController();
            searchController.addShowToList(new Show(name, season, episode));
            event.consume();
        }
    };*/
}
