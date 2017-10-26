package DataStorage;

import Core.Show;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by joshc on 2017-09-12.
 */
public class UserDataQueries {
    private String url = "jdbc:mysql://localhost:3306/user_data?autoReconnect=true&useSSL=false";
    private String databaseUserName = "admin";
    private String databasePassWord = "password";

    public ArrayList<Show> getLoggedShows(int userID) throws SQLException {
        ArrayList<Show> showList = new ArrayList<>();
        ArrayList<Integer> showIDs = new ArrayList<>();
        Connection myConn = DriverManager.getConnection(url, databaseUserName, databasePassWord);
        String sql = "SELECT * FROM show_log WHERE user_ID = " + userID;

        PreparedStatement ps = myConn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            showIDs.add(rs.getInt("show_ID"));
        }

        Show show;
        for (Integer i : showIDs) {
            try {
                show = getShowFromID(i);
                showList.add(show);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        myConn.close();
        return showList;
    }

    public Show getShowFromID(int id) throws SQLException {
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
}
