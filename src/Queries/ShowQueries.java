package Queries;

import Model.Show;

import java.sql.*;
import java.util.ArrayList;

//todo: when you construct a query class it should connect to DB!
public class ShowQueries {
    private String url = "jdbc:mysql://localhost:3306/user_data?autoReconnect=true&useSSL=false";
    private String databaseUserName = "admin";
    private String databasePassWord = "password";

    /**
     * This class will contain methods such as addNewShow, alterShowName, alterShowImage, addShowImage, only\
     * queries relating to the table: shows.
     *
     *
     * Working Methods: getAvailableShows, addNewShow
     */

    public ArrayList<Show> getAvailableShows() throws SQLException {
        ArrayList<Show> showList = new ArrayList<>();
        Connection myConn = DriverManager.getConnection(url, databaseUserName, databasePassWord);
        String sql = "SELECT * FROM shows";

        PreparedStatement ps = myConn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            showList.add(new Show(rs.getInt("ID"), rs.getString("name"), rs.getInt("year"), rs.getString("genre"), rs.getInt("runtime"), rs.getInt("seasons"), rs.getInt("episodes"), rs.getInt("rating")));
        }

        myConn.close();
        return showList;
    }

    public void addNewShow(String name, int year, String genre, int runtime, int seasons, int episodes, int rating) throws SQLException {
        Connection myConn = DriverManager.getConnection(url, databaseUserName, databasePassWord);

        String sql = "INSERT INTO shows"
                + " (name, year, genre, runtime, seasons, episodes, rating) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = myConn.prepareStatement(sql);
        statement.setString(1, name);
        statement.setInt(2, year);
        statement.setString(3, genre);
        statement.setInt(4, runtime);
        statement.setInt(5, seasons);
        statement.setInt(6, episodes);
        statement.setInt(7, rating);
        statement.executeUpdate();

        myConn.close();
    }

    public void setImage(int showID, String imageURL) throws SQLException {
        Connection myConn = DriverManager.getConnection(url, databaseUserName, databasePassWord);

        String sql = "UPDATE shows SET image_URL = ? WHERE ID = ?";
        PreparedStatement statement = myConn.prepareStatement(sql);
        statement.setString(1, imageURL);
        statement.setInt(2, showID);
        statement.executeUpdate();

        myConn.close();
    }

    public String getImage(int showID) throws SQLException {
        Connection myConn = DriverManager.getConnection(url, databaseUserName, databasePassWord);

        String sql = "SELECT image_URL FROM shows WHERE ID = ?";
        PreparedStatement statement = myConn.prepareStatement(sql);
        statement.setInt(1, showID);
        ResultSet rs = statement.executeQuery();

        rs.next();
        return rs.getString("image_URL");
    }
}
