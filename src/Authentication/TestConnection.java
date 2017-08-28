package Authentication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * TestConnection is a Test class to test the connection to the MySQL database for this project.
 */
public class TestConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/user_accounts?autoReconnect=true&useSSL=false";
        String user = "admin";
        String password = "JoshCamp123";

        try {
            Class.forName("com.mysql.jdbc.Driver");

            // 1. Get connection to database
            Connection myConn = DriverManager.getConnection(url, user, password);

            // 2. Create a statement
            Statement myStmnt = myConn.createStatement();

            // 3. Execute SQL query
            String sql = "INSERT INTO user_info"
                    + " (firstName, lastName, chips, userName, password)"
                    + " VALUES ('Josssssh', 'Campitesssslli', 1000, 'asssssmin', 'passsssssword')";

            myStmnt.executeUpdate(sql);

            System.out.println("Insert Complete.");

        } catch (SQLException | ClassNotFoundException exc) {
            exc.printStackTrace();
        }
    }
}
