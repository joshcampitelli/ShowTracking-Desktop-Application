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

    public String getAllShows() {
        ArrayList<Show> list = null;
        StringBuffer sb = new StringBuffer();
        try {
            list = showQueries.getAvailableShows();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (list != null) {
            for (Show show : list) {
                sb.append("Name: " + show.getName() + "\n");
            }
        } else {
            sb.append("No Shows Are Available.");
        }
        return sb.toString();
    }
}
