package Core;

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
    private Button editBtn;

    //Includes reference to the show's Button on the UI
    private CheckBox select; //for adding the show to currentUser directory

    public Show(String show, int season, int episode) {
        this.name = show;
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

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }

    public void setEditBtn(Button editBtn) {
        this.editBtn = editBtn;
    }

    public Button getEditBtn() {
        return editBtn;
    }
}
