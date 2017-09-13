package Core;

import DataOperations.*;
import DataStorage.AccountQueries;
import DataStorage.ShowQueries;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Framework Class Provides the functionality to the UI, it encapsulates the ShowQueries methods.
 * Designed to extract the methods for the GUI Controller class.
 */

public class Framework {
    private ShowQueries showQueries = new ShowQueries();
    private AccountQueries accountQueries = new AccountQueries();
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
        } else if (!AccountQueries.login(username, password)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean usernameExists(String username) {
        try {
            return accountQueries.usernameExists(username);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void createAccount(String username, String password, String firstName, String lastName) {
        try {
            accountQueries.addAccount(firstName, lastName, username, password);
        } catch (SQLIntegrityConstraintViolationException e) {
            // Duplicate entry
            System.out.println("[Important] Account already exists!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
