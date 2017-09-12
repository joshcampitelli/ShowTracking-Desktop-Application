package Authentication;

import java.sql.*;

/**
 * Created by Josh on 2017-06-26.
 */
public class AccountAccess {
    public static Boolean login(String username, String password) {
        String databaseUserName = "admin";
        String databasePassWord = "JoshCamp123";
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
}
