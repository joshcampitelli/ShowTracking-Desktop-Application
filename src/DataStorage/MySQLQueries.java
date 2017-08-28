package DataStorage;

import Authentication.Encryption;
import Core.Show;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MySQLQueries {
    private String url = "jdbc:mysql://localhost:3306/user_data?autoReconnect=true&useSSL=false";
    private String databaseUsername = "admin";
    private String databasePassword = "password";
    /**
     * This method assumes the data being given is in the correct format, and valid although will
     * catch duplicate user names.
     */
    public void addAccount(String firstName, String lastName, String username, String password) {
        String encodedPass = Encryption.MD5(password);
        password = "";

        try {
            // 1. Get connection to database
            Connection myConn = DriverManager.getConnection(url, databaseUsername, databasePassword);

            //2. Create Query
            String sql = "INSERT INTO user_accounts"
                    + " (username, password, firstname, lastname, date_joined)"
                    + " VALUES (?, ?, ?, ?, ?)";

            //3. Create Statement
            PreparedStatement statement = myConn.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, encodedPass);
            statement.setString(3, firstName);
            statement.setString(4, lastName);
            statement.setString(5, new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()));
            statement.executeUpdate();

            sql = "CREATE TABLE " + username + "shows (name VARCHAR(20), season INT(6), episode INT(6))";
            statement = myConn.prepareStatement(sql);
            statement.executeUpdate();
            myConn.close();
            System.out.println("[Important] Successfully Created Account!");
        } catch (SQLIntegrityConstraintViolationException e) {
            // Duplicate entry
            System.out.println("[Important] Account already exists!");
        } catch (SQLException e) {
            // Other SQL Exception
            e.printStackTrace();
        }
    }

    /**
     * This method assumes the data being given is in the correct format, and valid although will
     * catch duplicate user names.
     */
    public void addShow(String showName, int season, int episode, String username) {
        try {
            // 1. Get connection to database
            Connection myConn = DriverManager.getConnection(url, databaseUsername, databasePassword);

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
            Connection myConn = DriverManager.getConnection(url, databaseUsername, databasePassword);

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
            Connection myConn = DriverManager.getConnection(url, databaseUsername, databasePassword);

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
            Connection myConn = DriverManager.getConnection(url, databaseUsername, databasePassword);
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

    public ArrayList<Show> getAllShows(String username) {
        ArrayList<Show> showList = new ArrayList<>();
        try {
            Connection myConn = DriverManager.getConnection(url, databaseUsername, databasePassword);
            String sql = "Select * From " + username + "shows";
            PreparedStatement ps = myConn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                showList.add(new Show(rs.getString("name"), rs.getInt("season"), rs.getInt("episode")));
            }
            myConn.close();
        } catch (SQLException e) {
            //SQL Exception
            e.printStackTrace();
        }

        return showList;
    }
}
