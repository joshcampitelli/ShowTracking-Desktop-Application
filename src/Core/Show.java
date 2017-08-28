package Core;

public class Show {
    private int season;
    private int episode;
    private String name;
    private String genre;
    private int rating;

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
}
