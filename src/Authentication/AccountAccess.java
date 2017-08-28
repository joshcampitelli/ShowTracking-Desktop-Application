package Authentication;

import java.sql.*;

public class AccountAccess {
    public static Boolean login(String username, String password) {
        String databaseUserName = "admin";
        String databasePassWord = "password";
        String usernameInDatabase = "";
        String passwordInDatabase = "";
        String encodedPassword = Encryption.MD5(password);
        String url = "jdbc:mysql://localhost:3306/user_data?autoReconnect=true&useSSL=false";

        try {
            Connection myConn = DriverManager.getConnection(url, databaseUserName, databasePassWord);
            Statement stmt = myConn.createStatement();

            // Create SQL Query
            String sql = "SELECT * FROM user_accounts WHERE username='" + username + "' && password='" + encodedPassword+ "'";

            ResultSet rs = stmt.executeQuery(sql);
            // Check Username and Password
            while (rs.next()) {
                usernameInDatabase = rs.getString("userName");
                passwordInDatabase = rs.getString("password");
            }

            if (usernameInDatabase.equals(username) && passwordInDatabase.equals(encodedPassword)) {
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
}
