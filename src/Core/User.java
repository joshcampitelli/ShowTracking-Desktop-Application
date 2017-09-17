package Core;

import DataStorage.AccountQueries;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class represents a user of the program, once logged in stores info about user, some loaded from database.
 */
public class User {
    private String username;
    private String firstName;
    private String lastName;
    private int userID;
    private ArrayList<Show> loggedShows;
    AccountQueries accountQueries = new AccountQueries();

    public User(String username, String firstName, String lastName) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //In the case of logging in, still need to verify the user
    public User(String username) {
        this.username = username;
    }

    //get the id from the database
    public int getUserID() {
        try {
            return accountQueries.getUserID(username);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
