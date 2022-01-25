package model;

import exceptions.MovieNotInMovieListException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// represents a list of movies the user has seen
public class MovieList {

    private List<Movie> movL;

    // EFFECTS: creates a new empty MovieList
    public MovieList() {
        movL = new ArrayList<Movie>();
    }

    // EFFECTS: finds the index of the given movie name in MovieList, -1 if not found
    public int findMovie(String name) {
        for (int i = 0; i < movL.size(); i++) {
            if (name.equals(movL.get(i).getName())) {
                return i;
            }
        }
        return -1;
    }

    // MODIFIES: this
    // EFFECTS: adds given movie to beginning of MovieList
    public void addMovie(Movie movie) {
        movL.add(0, movie);
    }

    // MODIFIES: this
    // EFFECTS: removes given movie from in MovieList
    public void removeMovie(Movie m) throws MovieNotInMovieListException {
        if (findMovie(m.getName()) == -1) {
            throw new MovieNotInMovieListException();
        }
        int index = findMovie(m.getName());
        movL.remove(index);
    }

    // EXPECTS: Index is within range
    // EFFECTS: returns the movie at given index in MovieList
    public Movie getMovie(int i) {
        return movL.get(i);
    }

    // EFFECTS: returns the number of movies in MovieList
    public int getSize() {
        return movL.size();
    }

    public JSONObject toJson() {
        JSONObject js = new JSONObject();
        js.put("Watched Movies", movieListToJson());
        return js;
    }

    // EFFECTS: returns movies as a Json array
    public JSONArray movieListToJson() {
        JSONArray ja = new JSONArray();

        for (Movie m: movL) {
            ja.put(m.toJson());
        }

        return ja;
    }

}
