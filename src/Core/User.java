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

    //Upon Login the user is instantiated, all database info is then pulled locally
    public User(String username) {
        this.username = username;
        this.userID = getUserID();
        this.firstName = getFirstName();
        this.lastName = getLastName();
        this.loggedShows = getAllShows();
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

    private String getFirstName() {
        return "";
    }

    private String getLastName() {
        return "";
    }

    private ArrayList<Show> getAllShows() {
        return null;
    }
}
