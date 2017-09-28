package Core;

import DataStorage.AccountQueries;
import DataStorage.ShowQueries;
import DataStorage.UserDataQueries;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class represents a currentUser of the program, once logged in stores info about currentUser, some loaded from database.
 */
public class User {
    private AccountQueries accountQueries;
    private UserDataQueries userDataQueries;
    private ShowQueries showQueries;
    private ArrayList<Show> loggedShows;

    private String username;
    private String firstName;
    private String lastName;
    private int userID;

    //Upon Login the currentUser is instantiated, all database info is then pulled locally
    public User(String username) {
        accountQueries = new AccountQueries();
        userDataQueries = new UserDataQueries();
        showQueries = new ShowQueries();
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

    public void addShow(Show show) {
        loggedShows.add(show);
        try {
            showQueries.addNewShow(userID, show.getID());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getUserID() {
        return userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public ArrayList<Show> getAllShows() {
        return loggedShows;
    }
}
