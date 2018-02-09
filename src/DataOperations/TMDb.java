package DataOperations;

import com.omertron.themoviedbapi.MovieDbException;
import com.omertron.themoviedbapi.TheMovieDbApi;
import org.json.simple.JSONObject;

public class TMDb {

    private TheMovieDbApi theMovieDbApi;

    public TMDb() {
        try {
            theMovieDbApi = new TheMovieDbApi("9ae1f37f4a774f763225557376ad2f71");
        } catch (MovieDbException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getShow(String name) {

        return new JSONObject();
    }
}
