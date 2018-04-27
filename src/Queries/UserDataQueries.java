package Queries;

import Model.Show;
import Model.User;

import java.sql.*;
import java.util.ArrayList;

//Class interacts wit the show_log table in the db
public class UserDataQueries {
    private String url = "jdbc:mysql://localhost:3306/user_data?autoReconnect=true&useSSL=false";
    private String databaseUserName = "admin";
    private String databasePassWord = "password";

    /**
     * Method getLoggedShows is used to get all shows that a specific user is tracking on their account.
     * @param userID unique ID of the desired user
     * @return ArrayList of All Shows tracked by specified user
     * @throws SQLException
     */
    public ArrayList<Show> getLoggedShows(int userID) throws SQLException {
        ShowQueries showQueries = new ShowQueries();
        ArrayList<Show> showList = new ArrayList<>();
        Connection myConn = DriverManager.getConnection(url, databaseUserName, databasePassWord);
        String sql = "SELECT * FROM show_log WHERE user_ID = " + userID;

        PreparedStatement ps = myConn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Show show = showQueries.getShowFromID(rs.getInt("show_ID"));
            if (show == null)
                continue;
            show.setSeason(rs.getInt("season"));
            show.setEpisode(rs.getInt("episode"));
            showList.add(show);
        }

        myConn.close();
        return showList;
    }

    public void updateShow(Show show, User user) throws SQLException {
        Connection connection = DriverManager.getConnection(url, databaseUserName, databasePassWord);
        String sql = "UPDATE show_log SET season = " + show.getSeason() + ", episode = " + show.getEpisode() + " WHERE user_ID = " + user.getUserID() + " AND show_ID = " + show.getID();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
    }

    /**
     * Automatically sets the episode and season to 1 and 1, which will then be modified by user.
     * @param userID
     * @param showID
     * @throws SQLException
     */
    public void addShow(int userID, long showID) throws SQLException {
        Connection myConn = DriverManager.getConnection(url, databaseUserName, databasePassWord);

        String sql = "INSERT INTO show_log"
                + " (user_ID, show_ID, season, episode)"
                + " VALUES (?, ?, ?, ?)";

        PreparedStatement statement = myConn.prepareStatement(sql);
        statement.setInt(1,userID);
        statement.setLong(2,showID);
        statement.setInt(3,1);
        statement.setInt(4,1);
        statement.executeUpdate();

        myConn.close();
        System.out.println("[Important] Successfully Added Show!");
    }

    public void removeShow(int userID, long showID) throws SQLException {
        Connection myConn = DriverManager.getConnection(url, databaseUserName, databasePassWord);

        String sql = "DELETE FROM show_log WHERE user_ID = ? AND show_ID = ?";
        PreparedStatement statement = myConn.prepareStatement(sql);
        statement.setInt(1, userID);
        statement.setLong(2, showID);
        statement.executeUpdate();

        myConn.close();
        System.out.println("[Important] Successfully Removed Show!");
    }
}
