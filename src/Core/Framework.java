package Core;

import Authentication.*;
import DataStorage.MySQLQueries;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Framework Class Provides the functionality to the UI, it encapsulates the MySQLQueries methods.
 * Designed to extract the methods for the GUI Controller class.
 */

public class Framework {
    private MySQLQueries mySQLQueries = new MySQLQueries();
    private String username;

    private String getInput(String message) {
        Scanner input = new Scanner(System.in);
        System.out.print(message + ": ");
        return input.nextLine();
    }

    private int getInt(String message) {
        try {
            return Integer.parseInt(getInput(message));
        } catch (NumberFormatException e) {
            System.out.println("[Important] Incorrect Input!");
            return getInt(message);
        }
    }

    private String getString(ArrayList<Show> list) {
        StringBuffer string = new StringBuffer();
        if (list.isEmpty())
            return "There are no shows currently being tracked. \n";

        string.append("Stored in DataBase: \n");
        for(Show show : list) {
            string.append(show.getName() + "- Season: " + show.getSeason() + ", Episode: " + show.getEpisode() + "\n");
        }
        return string.toString();
    }

    public boolean login(String username, String password) {
        if (!DataValidation.validUser(username) || !DataValidation.validPass(password)) {
            return false;
        } else if (!AccountAccess.login(username, password)) {
            return false;
        } else {
            return true;
        }
    }

    private boolean usernameExists(String username) {
        try {
            return mySQLQueries.usernameExists(username);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void createAccount(String username, String password, String firstName, String lastName) {
        try {
            mySQLQueries.addAccount(firstName, lastName, username, password);
        } catch (SQLIntegrityConstraintViolationException e) {
            // Duplicate entry
            System.out.println("[Important] Account already exists!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
