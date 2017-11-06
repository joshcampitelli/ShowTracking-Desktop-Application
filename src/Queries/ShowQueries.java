package Queries;

import Core.Show;

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

    /**
     * Automatically sets the episode and season to 1 and 1, which will then be modified by user.
     * @param userID
     * @param showID
     * @throws SQLException
     * todo: Should be in a user dataqueries class, each class should operate on an individual table in DB.
     * todo: rename to logNewShow for user, and fix occurrences
     */
    public void addNewShow(int userID, int showID) throws SQLException {
        Connection myConn = DriverManager.getConnection(url, databaseUserName, databasePassWord);

        String sql = "INSERT INTO show_log"
                + " (user_ID, show_ID, season, episode)"
                + " VALUES (?, ?, ?, ?)";

        PreparedStatement statement = myConn.prepareStatement(sql);
        statement.setInt(1,userID);
        statement.setInt(2,showID);
        statement.setInt(3,1);
        statement.setInt(4,1);
        statement.executeUpdate();

        myConn.close();
        System.out.println("[Important] Successfully Added Show!");
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
        System.out.println("[Important] Successfully Added Show!");
    }

    //TODO: ====================== NEED UPDATE TO NEW FORMAT =========================
    //TODO: Throw not Try!
    /**
     * The alter season method Queries the MySQL database to update the current season which is
     * being tracked by the currentUser. The String username field is the username of the current currentUser,
     * String show is the show which is to be updated, and int season is the updated season number.
     */
    public void alterSeason(String username, String show, int season) {
        try {
            // 1. Get connection to database
            Connection myConn = DriverManager.getConnection(url, databaseUserName, databasePassWord);

            String sql = "UPDATE " + username + "shows"
                    + " SET season = " + season
                    + " WHERE name = " + "'" +show + "'";

            PreparedStatement statement = myConn.prepareStatement(sql);
            statement.executeUpdate();
            myConn.close();
            System.out.println("[Important] Successfully Updated Season!");
        } catch (SQLException e) {
            //SQL Exception
            e.printStackTrace();
        }
    }

    /**
     * The alter season method Queries the MySQL database to update the current season which is
     * being tracked by the currentUser. The String username field is the username of the current currentUser,
     * String show is the show which is to be updated, and int season is the updated season number.
     */
    public void alterEpisode(String username, String show, int episode) {
        try {
            // 1. Get connection to database
            Connection myConn = DriverManager.getConnection(url, databaseUserName, databasePassWord);

            String sql = "UPDATE " + username + "shows"
                    + " SET episode = " + episode
                    + " WHERE name = " + "'" +show + "'";

            PreparedStatement statement = myConn.prepareStatement(sql);
            statement.executeUpdate();
            myConn.close();
            System.out.println("[Important] Successfully Updated Episode!");
        } catch (SQLException e) {
            //SQL Exception
            e.printStackTrace();
        }
    }

    public boolean showTracked(String username, String name) {
        try {
            Connection myConn = DriverManager.getConnection(url, databaseUserName, databasePassWord);
            String sql = "SELECT name FROM " + username + "shows" + " WHERE name = ?";
            PreparedStatement ps = myConn.prepareStatement(sql);
            ps.setString (1, name);
            ResultSet rs = ps.executeQuery();
            myConn.close();
            return rs.next();
        } catch (SQLException e) {
            //SQL Exception
            e.printStackTrace();
        }
        return false;
    }
}
