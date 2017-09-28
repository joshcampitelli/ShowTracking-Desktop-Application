package Core;

import DataStorage.AccountQueries;
import DataStorage.UserDataQueries;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class represents a user of the program, once logged in stores info about user, some loaded from database.
 */
public class User {
    private AccountQueries accountQueries;
    private UserDataQueries userDataQueries;
    private ArrayList<Show> loggedShows;

    private String username;
    private String firstName;
    private String lastName;
    private int userID;

    //Upon Login the user is instantiated, all database info is then pulled locally
    public User(String username) {
        accountQueries = new AccountQueries();
        userDataQueries = new UserDataQueries();
        this.username = username;
        initialize();
    }

    /**
     * Initialize method is used to pull all available data from the database
     */
    private void initialize() {
        try {
            userID = accountQueries.getUserID(username);
            firstName = accountQueries.getFirstName(userID);
            lastName = accountQueries.getLastName(userID);
            loggedShows = userDataQueries.getLoggedShows(userID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getUserID() {
        return userID;
    }

    private String getFirstName() {
        return firstName;
    }

    private String getLastName() {
        return lastName;
    }

    private ArrayList<Show> getAllShows() {
        return loggedShows;
    }
}
