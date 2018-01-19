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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public int getSeasons() {
        return seasons;
    }

    public void setSeasons(int seasons) {
        this.seasons = seasons;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
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

    public CheckBox getSelect() {
        return select;
    }

    public void setSelect(CheckBox select) {
        this.select = select;
    }
}
