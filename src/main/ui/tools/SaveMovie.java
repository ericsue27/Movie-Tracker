package ui.tools;

import model.MovieList;
import persistence.JsonWrite;

import java.io.FileNotFoundException;

public class SaveMovie {

    private static final String jsonSaveDir = "./data/WatchedMovies.txt";
    private SaveLoadSuccessSound sound = new SaveLoadSuccessSound();

    JsonWrite jsonWrite = new JsonWrite(jsonSaveDir);

    // EFFECTS: Saves current watched list
    public void saveMovieList(MovieList movieList) {
        try {
            jsonWrite.open();
            jsonWrite.write(movieList);
            jsonWrite.close();
            sound.playSound();
        } catch (FileNotFoundException e) {
            System.out.println("Error. Cannot find file.");
        }
    }

}
