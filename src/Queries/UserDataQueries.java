package Queries;

import Model.Show;
import Model.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDataQueries {
    private String url = "jdbc:mysql://localhost:3306/user_data?autoReconnect=true&useSSL=false";
    private String databaseUserName = "admin";
    private String databasePassWord = "password";

    public ArrayList<Show> getLoggedShows(int userID) throws SQLException {
        ArrayList<Show> showList = new ArrayList<>();
        Connection myConn = DriverManager.getConnection(url, databaseUserName, databasePassWord);
        String sql = "SELECT * FROM show_log WHERE user_ID = " + userID;

        PreparedStatement ps = myConn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Show show = getShowFromID(rs.getInt("show_ID"));
            show.setSeason(rs.getInt("season"));
            show.setEpisode(rs.getInt("episode"));
            showList.add(show);
        }
        
        myConn.close();
        return showList;
    }

    private Show getShowFromID(int id) throws SQLException {
        Show show = null;
        Connection myConn = DriverManager.getConnection(url, databaseUserName, databasePassWord);
        String sql = "SELECT * FROM shows WHERE ID = " + id;

        PreparedStatement ps = myConn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            show = new Show(id, rs.getString("name"), rs.getInt("year"), rs.getString("genre"), rs.getInt("runtime"), rs.getInt("seasons"), rs.getInt("episodes"), rs.getInt("rating"));
        }

        myConn.close();
        return show;
    }

    public void updateShow(Show show, User user) throws SQLException {
        Connection connection = DriverManager.getConnection(url, databaseUserName, databasePassWord);
        String sql = "UPDATE show_log SET season = " + show.getSeason() + ", episode = " + show.getEpisode() + " WHERE user_ID = " + user.getUserID() + " AND show_ID = " + show.getID();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
    }

}
