package Model;

import javafx.scene.control.CheckBox;

/**
 * The class Show represents the shows being tracked by each user of the
 * ShowTracker Program, the local variables in the class are the attributes
 * of each show stored in the DB.
 * Todo: Add Show Images to UI (URLs for each show)
 */
public class Show {
    /*Show DataBase Columns (Ordered)*/
    private int ID;
    private String name;
    private int year;
    private String genre;
    private int runtime;
    private int seasons;
    private int episodes;
    private int rating;

    /*User Database Columns (Ordered)*/
    private int season;
    private int episode;

    /*UI Handles for controllers*/
    private CheckBox select;

    /*Used to construct a show without a user's info*/
    public Show(int ID, String name, int year, String genre, int runtime, int seasons, int episodes, int rating) {
        this.ID = ID;
        this.name = name;
        this.year = year;
        this.genre = genre;
        this.runtime = runtime;
        this.seasons = seasons;
        this.episodes = episodes;
        this.rating = rating;
    }

    /*Used to construct a show with user's info*/
    public Show(int ID, String name, int year, String genre, int runtime, int seasons, int episodes, int rating, int season, int episode) {
        this(ID, name, year, genre, runtime, seasons, episodes, rating);
        this.episode = episode;
        this.season = season;
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

    public int getEpisode() {
        return episode;
    }
}
