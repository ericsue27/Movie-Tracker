package ui;

import com.sun.org.apache.xerces.internal.impl.dv.xs.BaseDVFactory;
import exceptions.MovieNotInMovieListException;
import model.Movie;
import model.MovieList;
import persistence.JsonRead;
import persistence.JsonWrite;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// Movie Tracker application
public class MovieTrackerApp {

    private static final String jsonSaveDir = "./data/WatchedMovies.txt";
    private JsonWrite jsonWrite;
    private JsonRead jsonRead;
    private Scanner menuSelect = new Scanner(System.in);
    private Scanner input = new Scanner(System.in);
    private MovieList watchedList = new MovieList();
    private Boolean appStatus = true;

    private final List<String> validRatings = Arrays.asList("0/10", "1/10", "2/10", "3/10", "4/10", "5/10",
            "6/10", "7/10", "8/10", "9/10", "10/10");
    private final int currentYear = 2021;

    // starts the app
    public MovieTrackerApp() {
        System.out.println("Welcome to the Movie Tracker!\n");

        jsonWrite = new JsonWrite(jsonSaveDir);
        jsonRead = new JsonRead(jsonSaveDir);

        while (appStatus) {
            showMenu();
            processInput();
        }

    }

    // EFFECTS: displays the menu and the options in the console
    public void showMenu() {
        System.out.println("Please select one of the options by entering the corresponding number.");
        System.out.println("1. Add movie to watched list");
        System.out.println("2. Display watched list");
        System.out.println("3. Edit movie in watched list");
        System.out.println("4. Shows rating and review for specific watched movie");
        System.out.println("5. Save or load watched list");
        System.out.println("6. Close the program");
    }

    // EFFECTS: processes the user input
    public void processInput() {

        String selection = menuSelect.nextLine();

        if (selection.equals("1")) {
            newMovie();
        } else if (selection.equals("2")) {
            displayWatchedList();
        } else if (selection.equals("3")) {
            editMovieGuard();
        } else if (selection.equals("4")) {
            showMovieGuard();
        } else if (selection.equals("5")) {
            menuSaveOrLoad();
        } else if (selection.equals("6")) {
            appStatus = false;
        } else {
            System.out.println("Error. Input not recognized. Please try again.");
            processInput();
        }
    }

    // MODIFIES: this
    // EFFECTS: if watchedList is empty, return error string; else, allow the user to edit their watchedList
    public void editMovieGuard() {
        if (watchedList.getSize() == 0) {
            System.out.println("Sorry, you don't have any movies to edit.");
        } else {
            editMovieMenu();
        }
    }

    // EFFECTS: if watchedList is empty, return error string; else, allow the user to view a specific movie of choice
    public void showMovieGuard() {
        if (watchedList.getSize() == 0) {
            System.out.println("Sorry, you don't have any movies to view.");
        } else {
            showMovie();
        }
    }

    // EFFECTS: offers a choice of saving or loading a watchedList
    public void menuSaveOrLoad() {
        System.out.println("1. Save current watched list\n2. Load past watched list\n3. Return to main menu");
        String selection = menuSelect.nextLine();

        if (selection.equals("1")) {
            saveMovieList();
        } else if (selection.equals("2")) {
            loadMovieList();
        } else if (selection.equals("3")) {
            // Do nothing to return to main menu
        } else {
            System.out.println("Error. Invalid input. Please try again.");
            menuSaveOrLoad();
        }

    }

    // EFFECTS: Saves current watched list
    public void saveMovieList() {
        try {
            jsonWrite.open();
            jsonWrite.write(watchedList);
            jsonWrite.close();
            System.out.println("Saved!");
        } catch (FileNotFoundException e) {
            System.out.println("Error. Cannot find file.");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the watched movieList from file
    public void loadMovieList() {
        try {
            watchedList = jsonRead.read();
            System.out.println("Loaded");
        } catch (IOException e) {
            System.out.println("Error. Could not read file.");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a given movie to watched movieList
    public void newMovie() {
        Movie m;
        int year;
        String rating;
        String review;

        System.out.println("Please enter the name of the movie:");
        String name = input.nextLine();

        System.out.println("Please enter the year this movie was released:");
        year = input.nextInt();
        while (year < 1800 || year > currentYear) {
            System.out.println("Error. Please enter a valid year:");
            year = input.nextInt();
        }

        input.nextLine();

        System.out.println("Please enter your rating of the movie out of 10 (ex. 7/10):");
        rating = input.nextLine();
        while (!validRatings.contains(rating)) {
            System.out.println("Error. Please enter a valid rating.");
            rating = input.nextLine();
        }

        System.out.println("Please enter your review of the movie:");
        review = input.nextLine();

        m = new Movie(name, year, rating, review);
        watchedList.addMovie(m);
    }

    // EFFECTS: displays all movies, ratings and reviews on watchedList
    public void displayWatchedList() {
        Movie m;

        for (int i = 0; i < watchedList.getSize(); i++) {
            m = watchedList.getMovie(i);
            System.out.println("\n" + m.getName() + " (" + m.getReleaseDate() + ") " + "\n"
                    + m.getRating() + " - " + m.getReview() + "\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: allows user to edit movie entry they made previously or delete a selected movie
    public void editMovieMenu() {
        Boolean keepEditing = true;

        System.out.println("Please enter the name of the movie you want to edit:");
        int index = watchedList.findMovie(input.nextLine());

        if (index == -1) {
            System.out.println("Error. Movie can not be found.");
            keepEditing = false;
        }

        while (keepEditing) {
            Movie m = watchedList.getMovie(index);

            System.out.println("Please select an edit option by entering the number:\n1. Edit name \n"
                    + "2. Edit release date \n3. Edit rating \n4. Edit review\n5. Delete movie \n6. Back to menu");

            String choice = input.nextLine();

            if (choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4")) {
                editMovie(m, choice);
            } else if (choice.equals("5")) {
                keepEditing = tryRemoveMovie(m);
            } else if (choice.equals("6")) {
                keepEditing = false;
            } else {
                System.out.println("Error. Please only enter a number from 1-5.");
            }
        }
    }

    // MODIFIES: this
    // attempts to remove given movie from movieList and returns the keepEditing boolean value
    public Boolean tryRemoveMovie(Movie m) {
        try {
            watchedList.removeMovie(m);
            return false;
        } catch (MovieNotInMovieListException e) {
            System.out.println("Error. Could not find movie to remove.");
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: edits a given movies name, release date, rating or review
    public void editMovie(Movie m, String i) {
        if (i.equals("1")) {
            System.out.println("Please enter the new name:");
            m.editName(input.nextLine());

        } else if (i.equals("2")) {
            System.out.println("Please enter the new release date:");
            int year = input.nextInt();

            while (year < 1800 || year > currentYear) {
                System.out.println("Error. Please enter a valid year:");
                year = input.nextInt();
            }
            m.editReleaseDate(year);

        } else if (i.equals("3")) {
            System.out.println("Please enter the new rating:");

            String rating = input.nextLine();
            while (!validRatings.contains(rating)) {
                System.out.println("Error. Please enter a valid rating.");
                rating = input.nextLine();
            }
            m.editRating(rating);

        } else if (i.equals("4")) {
            System.out.println("Please enter the new review:");
            m.editReview(input.nextLine());
        }

    }

    // EFFECTS: shows name, release date, rating and review of chosen movie in watchedList
    public void showMovie() {
        Movie m;

        System.out.println("Please enter the name of the movie you would like to see your rating and review for.");

        String name = input.nextLine();

        if (watchedList.findMovie(name) == -1) {
            System.out.println("Error. Could not find given movie in your watched list.");
        } else {
            m = watchedList.getMovie(watchedList.findMovie(name));

            System.out.println("\n" + m.getName() + " (" + m.getReleaseDate() + ")");
            System.out.println(m.getRating() + " - " + m.getReview() + "\n");
        }
    }
}
