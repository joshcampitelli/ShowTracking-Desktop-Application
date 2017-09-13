package DataStorage;

import DataOperations.DataEncryption;

import java.sql.*;
public class AccountQueries {

    private static String databaseUserName = "admin";
    private static String databasePassWord = "password";
    private static String url = "jdbc:mysql://localhost:3306/user_data?autoReconnect=true&useSSL=false";

    public static Boolean login(String username, String password) {
        String encodedPassword = DataEncryption.MD5(password);

        try {
            Connection myConn = DriverManager.getConnection(url, databaseUserName, databasePassWord);
            Statement stmt = myConn.createStatement();

            // Create SQL Query
            String sql = "SELECT * FROM user_accounts WHERE username='" + username + "' && password='" + encodedPassword+ "'";

            ResultSet rs = stmt.executeQuery(sql);
            // Check Username and Password
            while (rs.next()) {
                databaseUserName = rs.getString("username");
                databasePassWord = rs.getString("password");
            }

            if (databaseUserName.equals(username) && databasePassWord.equals(encodedPassword)) {
                myConn.close();
                System.out.println("[Important] Successfully Logged in!");
                return true;
            } else {
                System.out.println("[Important] Incorrect username or password!");
                myConn.close();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("[Important] SQLException Thrown!");
            return false;
        }
    }

    /**
     * This method assumes the Data being given is in the correct format, and valid although will
     * catch duplicate user names.
     */
    public void addAccount(String firstName, String lastName, String username, String password) throws SQLException {
        String encodedPass = DataEncryption.MD5(password);
        password = "";


        // 1. Get connection to database
        Connection myConn = DriverManager.getConnection(url, databaseUserName, databasePassWord);

        //2. Create Query
        String sql = "INSERT INTO user_accounts"
                + " (username, password, firstname, lastname)"
                + " VALUES (?, ?, ?, ?)";

        //3. Create Statement
        PreparedStatement statement = myConn.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, encodedPass);
        statement.setString(3, firstName);
        statement.setString(4, lastName);
        //statement.setString(5, new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date())); //Gets the current Date.
        statement.executeUpdate();

        //sql = "CREATE TABLE " + username + "shows (name VARCHAR(20), season INT(6), episode INT(6))";
        //statement = myConn.prepareStatement(sql);
        //statement.executeUpdate();
        myConn.close();
        System.out.println("[Important] Successfully Created Account!");
    }


    public boolean usernameExists(String username) throws SQLException {
        PreparedStatement statement = null;
        ResultSet rs = null;

        String query = "SELECT username FROM user_accounts where username=?";
        Connection myConn = DriverManager.getConnection(url, databaseUserName, databasePassWord);

        statement = myConn.prepareStatement(query);
        statement.setString(1, username);

        rs = statement.executeQuery();

        if(rs.next()) {
            System.out.println("Username " + username + " already exists.");
            return true;
        }

        statement.close();
        rs.close();
        return false;
    }
}
