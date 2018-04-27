package Queries;

import Model.Show;

import java.sql.*;
import java.util.ArrayList;

public class ShowQueries {
    private String url = "jdbc:mysql://localhost:3306/user_data?autoReconnect=true&useSSL=false";
    private String databaseUserName = "admin";
    private String databasePassWord = "password";


    public ArrayList<Show> getAvailableShows() throws SQLException {
        ArrayList<Show> showList = new ArrayList<>();
        Connection myConn = DriverManager.getConnection(url, databaseUserName, databasePassWord);
        String sql = "SELECT * FROM show_data";

        PreparedStatement ps = myConn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            showList.add(new Show(rs.getLong("ID"), rs.getString("name"), rs.getString("start_date"), rs.getString("genre"), rs.getString("runtime"), rs.getLong("seasons"), rs.getLong("episodes"), rs.getDouble("rating"), rs.getString("image_URL"), rs.getString("overview")));
        }

        myConn.close();
        return showList;
    }

    public void addNewShow(long ID, String name, String year, String genre, String runtime, long seasons, long episodes, double rating, String imageURL, String overview) throws SQLException {
        Connection myConn = DriverManager.getConnection(url, databaseUserName, databasePassWord);

        String sql = "INSERT INTO show_data"
                + " (ID, name, start_date, genre, runtime, seasons, episodes, rating, image_URL, overview) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = myConn.prepareStatement(sql);
        statement.setLong(1, ID);
        statement.setString(2, name);
        statement.setString(3, year);
        statement.setString(4, genre);
        statement.setString(5, runtime);
        statement.setLong(6, seasons);
        statement.setLong(7, episodes);
        statement.setDouble(8, rating);
        statement.setString(9, imageURL);
        statement.setString(10, overview);

        statement.executeUpdate();

        myConn.close();
    }

    /**
     * Method getShowFromID retrieves a specific show identified by it's ID from the show_data table.
     * @param id requested id of the show
     * @return Show 
     * @throws SQLException
     */
    public Show getShowFromID(long id) throws SQLException {
        Show show = null;
        Connection myConn = DriverManager.getConnection(url, databaseUserName, databasePassWord);
        String sql = "SELECT * FROM show_data WHERE ID = " + id;

        PreparedStatement ps = myConn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            show = new Show(id, rs.getString("name"), rs.getString("start_date"), rs.getString("genre"), rs.getString("runtime"), rs.getLong("seasons"), rs.getLong("episodes"), rs.getDouble("rating"), rs.getString("image_URL"), rs.getString("overview"));
        }

        myConn.close();
        return show;
    }
}
