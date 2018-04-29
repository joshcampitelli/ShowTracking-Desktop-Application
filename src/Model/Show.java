package Model;

import javafx.scene.control.CheckBox;

/**
 * The class Show represents the shows being tracked by each user of the
 * ShowTracker Program, the local variables in the class are the attributes
 * of each show stored in the DB.
 */
public class Show {
    /*Show DataBase Columns (Ordered)*/
    private long ID;
    private String name;
    private String startDate;
    private String genre;
    private String runtime;
    private long seasons;
    private long episodes;
    private double rating;
    private String image;
    private String overview;

    /*User Database Columns (Ordered)*/
    private int season;
    private int episode;

    /*UI Handles for controllers*/
    private CheckBox select;

    /*Used to construct a show without a user's info*/
    public Show(long ID, String name, String startDate, String genre, String runtime, long seasons, long episodes, double rating, String image, String overview) {
        this.ID = ID;
        this.name = name;
        this.startDate = startDate;
        this.genre = genre;
        this.runtime = runtime;
        this.seasons = seasons;
        this.episodes = episodes;
        this.rating = rating;
        this.image = image;
        this.overview = overview;
    }

    /*Used to construct a show with user's info*/
    public Show(long ID, String name, String startDate, String genre, String runtime, long seasons, long episodes, double rating, String image, String overview, int season, int episode) {
        this(ID, name, startDate, genre, runtime, seasons, episodes, rating, image, overview);
        this.episode = episode;
        this.season = season;
    }

    public boolean equals(Object object) {
        if (object instanceof Show) {
            Show show = (Show) object;
            return ID == show.getID();
        } else {
            return false;
        }
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public long getSeasons() {
        return seasons;
    }

    public void setSeasons(long seasons) {
        this.seasons = seasons;
    }

    public long getEpisodes() {
        return episodes;
    }

    public void setEpisodes(long episodes) {
        this.episodes = episodes;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
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
