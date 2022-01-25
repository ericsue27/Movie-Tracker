package ui.tools;

import model.Movie;
import model.MovieList;
import persistence.JsonRead;

import javax.swing.*;
import java.io.IOException;

public class LoadMovie {

    private static final String jsonSaveDir = "./data/WatchedMovies.txt";
    private MovieList movieList;
    private MovieTable movieTable = new MovieTable();
    private SaveLoadSuccessSound sound = new SaveLoadSuccessSound();

    JsonRead jsonRead = new JsonRead(jsonSaveDir);

    // MODIFIES: this
    // EFFECTS: loads the watched movieList from file
    public MovieList loadMovieList() {
        try {
            movieList = jsonRead.read();
            sound.playSound();
            return movieList;
        } catch (IOException e) {
            System.out.println("Error. Could not read file.");
        }
        return null;
    }

}
