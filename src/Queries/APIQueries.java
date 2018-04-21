package Queries;

import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

//TODO: GUI wrapper for this class with text field for entering url, text area for displaying JSON and option to enter
//TODO: into db.
class APIQueries {
    //Random Movie Request Tests
    private static String MOVIE = "https://api.themoviedb.org/3/movie/550?api_key=9ae1f37f4a774f763225557376ad2f71";
    private static String TWD = "https://api.themoviedb.org/3/search/tv?api_key=9ae1f37f4a774f763225557376ad2f71&language=en-US&query=rick%20and%20morty&page=1";
    private static String TV = "https://api.themoviedb.org/3/tv/60625?api_key=9ae1f37f4a774f763225557376ad2f71";
    //use poster path and append with: "https://image.tmdb.org/t/p/original/
    public static void main(String [] args) {
        APIQueries apiQueries = new APIQueries();

        for (int i = 3000; i < 3030; i++) {
            try {
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(apiQueries.getResponse("https://api.themoviedb.org/3/tv/" + i + "?api_key=9ae1f37f4a774f763225557376ad2f71"));

                JSONObject jsonObject = (JSONObject) obj;
                System.out.println(jsonObject);

                System.out.println("ID: " + (long) jsonObject.get("id"));
                System.out.println("Name: " + jsonObject.get("name"));
                System.out.println("Release Date: " + jsonObject.get("first_air_date"));
                System.out.println("Genres: " + jsonObject.get("genres"));
                System.out.println("Runtime: " + jsonObject.get("episode_run_time"));
                System.out.println("Seasons: " + jsonObject.get("number_of_seasons"));
                System.out.println("Episodes: " + jsonObject.get("number_of_episodes"));
                System.out.println("Episodes: " + jsonObject.get("number_of_episodes"));
                System.out.println("Vote Average: " + jsonObject.get("vote_average"));
                System.out.println("Poster: " + jsonObject.get("poster_path"));
                System.out.println("Overview: " + jsonObject.get("overview"));

                ShowQueries showQueries = new ShowQueries();
                try {
                    showQueries.addNewShow((long) jsonObject.get("id"), (String) jsonObject.get("name"), (String) jsonObject.get("first_air_date"), jsonObject.get("genres").toString(),
                            jsonObject.get("episode_run_time").toString(), (long) jsonObject.get("number_of_seasons"), (long) jsonObject.get("number_of_episodes"),
                            (double) jsonObject.get("vote_average"), "https://image.tmdb.org/t/p/original/" + jsonObject.get("poster_path"),
                            (String) jsonObject.get("overview"));
                    System.out.println("Inserted");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                // System.out.println("========= SEARCH =========");
                // System.out.println(apiQueries.searchShow(TWD));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String getResponse(String link) {
        try {
            //inline will store the JSON data streamed in string format
            String inline = "";
            URL url = new URL(link);

            //Parse URL into HttpURLConnection in order to open the connection in order to get the JSON data
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //Set the request to GET or POST as per the requirements
            conn.setRequestMethod("GET");
            //Set Accepted response type to JSON
            conn.addRequestProperty("Accept", "application/json");
            //Use the connect method to create the connection bridge
            conn.connect();
            //Get the response status of the Rest API
            int responsecode = conn.getResponseCode();
            System.out.println("Response code is: " + responsecode);

            //Iterating condition to if response code is not 200 then throw a runtime exception
            //else continue the actual process of getting the JSON data
            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
                //Scanner functionality will read the JSON data from the stream
                Scanner sc = new Scanner(url.openStream());
                while (sc.hasNext()) {
                    inline += sc.nextLine();
                }
                //Close the stream when reading the data has been finished
                sc.close();
            }
            //Disconnect the HttpURLConnection stream
            conn.disconnect();
            return inline;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }

    /**
     *
     * @param query keywords used to search for show. Ex: ("The Walking Dead")
     * @return long id value returned from search results
     */
    private String searchShow(String query) {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(getResponse(query));
            JSONObject jsonObject = (JSONObject)obj;
            System.out.println(jsonObject);
            long id = (long)jsonObject.get("id");
            System.out.println("ID: " + id);
            String showQuery = "https://api.themoviedb.org/3/tv/" + id + "?api_key=9ae1f37f4a774f763225557376ad2f71";
            obj = parser.parse(getResponse(showQuery));
            return (String)obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}