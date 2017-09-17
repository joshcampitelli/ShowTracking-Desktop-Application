package DataStorage;

import Core.Show;
import java.sql.*;
import java.util.ArrayList;

public class ShowQueries {
    private String url = "jdbc:mysql://localhost:3306/user_data?autoReconnect=true&useSSL=false";
    private String databaseUserName = "admin";
    private String databasePassWord = "password";

    public ArrayList<Show> getAvailableShows() throws SQLException {
        ArrayList<Show> showList = new ArrayList<>();
        Connection myConn = DriverManager.getConnection(url, databaseUserName, databasePassWord);
        String sql = "SELECT * FROM shows";

        PreparedStatement ps = myConn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            showList.add(new Show(rs.getString("name"), rs.getString("image_url"), rs.getInt("ID")));
        }

        myConn.close();
        return showList;
    }

    //TODO: ====================== NEED UPDATE TO NEW FORMAT =========================
    //TODO: Throw not Try!
    /*
     * This class will contain methods such as addNewShow, alterShowName, alterShowImage, addShowImage, only\
     * queries relating to the table: shows.
     */
    /**
     * This method assumes the Data being given is in the correct format, and valid although will
     * catch duplicate user names.
     */
    public void addShow(String showName, int season, int episode, String username) {
        try {
            // 1. Get connection to database
            Connection myConn = DriverManager.getConnection(url, databaseUserName, databasePassWord);

            String sql = "INSERT INTO " + username + "shows"
                    + " (name, season, episode)"
                    + " VALUES (?, ?, ?)";

            PreparedStatement statement = myConn.prepareStatement(sql);
            statement.setString(1, showName);
            statement.setInt(2, season);
            statement.setInt(3, episode);
            statement.executeUpdate();
            myConn.close();
            System.out.println("[Important] Successfully Added Show!");
        } catch (SQLException e) {
            //SQL Exception
            e.printStackTrace();
        }
    }


    /**
     * The alter season method Queries the MySQL database to update the current season which is
     * being tracked by the user. The String username field is the username of the current user,
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
     * being tracked by the user. The String username field is the username of the current user,
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
