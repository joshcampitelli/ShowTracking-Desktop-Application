package UI;

import Queries.ShowQueries;

import java.sql.SQLException;

public class DatabaseUpdate {
    public static void main(String[] args) {
        ShowQueries showQueries = new ShowQueries();
        try {
            showQueries.setImage(1, "https://images-na.ssl-images-amazon.com/images/M/MV5BMjRiNDRhNGUtMzRkZi00MThlLTg0ZDMtNjc5YzFjYmFjMmM4XkEyXkFqcGdeQXVyNzQ1ODk3MTQ@._V1_.jpg");
            showQueries.setImage(2, "https://upload.wikimedia.org/wikipedia/en/0/0e/TheWalkingDeadPoster.jpg");
            showQueries.setImage(3, "https://vignette.wikia.nocookie.net/gameofthrones/images/2/2c/Season_1_Poster.jpg/revision/latest/scale-to-width-down/2000?cb=20110406150536");
            showQueries.setImage(4, "https://vignette.wikia.nocookie.net/silicon-valley/images/1/18/Silicon_Valley_cover_photo.jpg/revision/latest?cb=20141214070152");

            System.out.println("Success");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
