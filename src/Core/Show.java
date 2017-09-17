package Core;

import javafx.scene.image.Image;

public class Show {
    private int season;
    private int episode;
    private String name;
    private String genre;
    private int rating;
    private int ID;
    private String image;

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

    public Image getImage() {
        return new Image("https://upload.wikimedia.org/wikipedia/commons/f/f1/Ruby_logo_64x64.png");
    }

    public int getSeason() {
        return season;
    }

    public int getEpisode() {
        return episode;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public int getRating() {
        return rating;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public void setGenre(String genre) {this.genre = genre;}

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
