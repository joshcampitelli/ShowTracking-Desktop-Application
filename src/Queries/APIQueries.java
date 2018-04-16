package Queries;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
//TODO: GUI wrapper for this class with text field for entering url, text area for displaying JSON and option to enter
//TODO: into db.
class APIQueries {
    //Random Movie Request Tests
    private static String URL1 = "https://api.themoviedb.org/3/movie/550?api_key=9ae1f37f4a774f763225557376ad2f71";
    private static String api = "https://api.themoviedb.org/3/tv/{tv_id}?api_key=<<api_key>>&language=en-US";

    public static void main(String [] args) {
        try {
            final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
            executorService.scheduleAtFixedRate(APIQueries::getResponse, 0, 100, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getResponse() {
        try {
            //inline will store the JSON data streamed in string format
            String inline = "";
            URL url = new URL(api);

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
            System.out.println("JSON Response:");
            System.out.println(inline);
            return inline;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }
}