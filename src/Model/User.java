package Model;

import Queries.AccountQueries;
import Queries.ShowQueries;
import Queries.UserDataQueries;

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
            email = accountQueries.getEmail(userID);
            age = accountQueries.getAge(userID);
            loggedShows = userDataQueries.getLoggedShows(userID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAccount() {
        try {
            firstName = accountQueries.getFirstName(userID);
            lastName = accountQueries.getLastName(userID);
            email = accountQueries.getEmail(userID);
            age = accountQueries.getAge(userID);
        } catch (SQLException e) {}
    }

    /*Adds a show to the user's logged shows*/
    public void addShow(Show show) {
        try {
            userDataQueries.addShow(userID, show.getID());
            loggedShows.add(show);
        //} catch (MySQLIntegrityConstraintViolationException exception) {
         //   System.out.println("[Duplicate Entry] Show Already being Tracked.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeShow(Show show) {
        try {
            userDataQueries.removeShow(userID, show.getID());
            loggedShows.remove(show);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getUserID() {
        return userID;
    }

    public ArrayList<Show> getAllShows() {
        return loggedShows;
    }

    /**Generated Accessors**/

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
