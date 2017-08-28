package Core;

import Authentication.*;
import DataStorage.MySQLQueries;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    private MySQLQueries mySQLQueries;
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

    private void addShow() {
        String name = getInput("Enter the name of the show");
        int season = getInt("Enter the Season which you are currently on");
        int episode = getInt("Enter the Episode which you are currently on");

        if (mySQLQueries.showTracked(username, name)) {
            System.out.println("[Important] Already Tracking this show!");
            modifyShow();
        } else {
            mySQLQueries.addShow(name.toLowerCase(), season, episode, username);
            System.out.println("[Important] Successfully Added Show!");
        }
    }

    private void printShows(ArrayList<Show> list) {
        System.out.print(getString(list));
    }

    private void login() {
        String password;
        do {
            do {
                username = getInput("Enter your username");
                password = getInput("Enter your password");
            } while (!DataValidation.validUser(username) || !DataValidation.validPass(password));
        } while (!AccountAccess.login(username, password));
    }

    private void createAccount() {
        String username;
        String password;
        String firstName;
        String lastName;
        System.out.println("[Important] To gain begin you must create an Account:");
        do {
            firstName = getInput("Enter your first name");
            lastName = getInput("Enter your last name");
            username = getInput("Create a username");
            password = getInput("Create a password");
        } while (!DataValidation.validUser(username) || !DataValidation.validPass(password));
        mySQLQueries.addAccount(firstName, lastName, username, password);
        login();
    }

    private void modifyShow() {
        System.out.println("Current Shows being Tracked:");
        printShows(mySQLQueries.getAllShows(username));

        if (mySQLQueries.getAllShows(username).isEmpty()) {
            return;
        }

        String name;
        do {
            name = getInput("Enter the name of the show you wish to update");
        } while (!mySQLQueries.showTracked(username, name));

        //Todo: Include the current state of the show, MySQL Query
        int season = getInt("Enter your current Season");
        int episode = getInt("Enter your current Episode");

        mySQLQueries.alterSeason(username, name, season);
        mySQLQueries.alterEpisode(username, name, episode);
        System.out.println("[Important] Successfully Updated Progress!");
    }

    public void begin() {
      mySQLQueries = new MySQLQueries();
      System.out.println("Welcome to Version 1.0 [Alpha] of ShowTracking");
      System.out.println("Login to gain access to the ShowTracking Features!");
      String accStatus = getInput("Have you previously made an account? (y/n)");
      if (accStatus.toLowerCase().equals("y"))
          login();
      else
          createAccount();
      String operation;
      do {
          System.out.println();
          System.out.println("[Options] 1) Start Tracking a new Show");
          System.out.println("          2) Update Show History (ex. Increment Current Episode)");
          System.out.println("          3) Show all previously tracked shows");
          System.out.println("          4) Logout");
          operation = getInput("Enter one of the Options listed above (1, 2, 3, 4)");

          if (operation.equals("1"))
              addShow();
          else if (operation.equals("2"))
              modifyShow();
          else if (operation.equals("3"))
              printShows(mySQLQueries.getAllShows(username));
          else if (operation.equals("4"))
              break;

      } while(!operation.toLowerCase().equals("q"));
    }
}
