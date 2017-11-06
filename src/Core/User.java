package Core;

import Queries.AccountQueries;
import Queries.ShowQueries;
import Queries.UserDataQueries;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class represents a currentUser of the program, once logged in stores info about currentUser, some loaded from database.
 */
public class User {
    /*DataBase Query Variables*/
    private AccountQueries accountQueries;
    private UserDataQueries userDataQueries;
    private ShowQueries showQueries;

    /*User's Logged Shows*/
    private ArrayList<Show> loggedShows;

    /*User Database Columns (Ordered)*/
    private int userID;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private int age;

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

    /*Adds a show to the user's logged shows*/
    public void addShow(Show show) {
        try {
            showQueries.addNewShow(userID, show.getID());
            loggedShows.add(show);
        } catch (MySQLIntegrityConstraintViolationException exception) {
            System.out.println("[Duplicate Entry] Show Already being Tracked.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Show> getAllShows() {
        return loggedShows;
    }
}
