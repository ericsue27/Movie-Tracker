package persistence;

// JsonRead was modeled from given sample application JsonSerializationDemo. Link below:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

import model.Movie;
import model.MovieList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// reads previously saved Json file in data
public class JsonRead {
    private String file;

    // EFFECTS: starts the reader and directs it to the proper file (constructor)
    public JsonRead(String s) {
        file = s;
    }

    // EFFECTS: reads watched movies from file and returns it and throws IOException if it cant read the file
    public MovieList read() throws IOException {
        String jd = readFile(file);
        JSONObject jo = new JSONObject(jd);
        return parseMovieList(jo);
    }

    // EFFECTS: reads file and returns it as a string
    public String readFile(String src) throws IOException {
        StringBuilder cb = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(src), StandardCharsets.UTF_8)) {
            stream.forEach(s -> cb.append(s));
        }

        return cb.toString();
    }

    // EFFECTS: parses Movie List in given JSON object and returns the parsed value
    public MovieList parseMovieList(JSONObject jo) {
        MovieList ml = new MovieList();
        addMovies(ml, jo);
        return ml;
    }

    // MODIFIES: MovieList
    // EFFECTS: reads the movie information from JSON file and adds it to the MovieList
    public void addMovies(MovieList ml, JSONObject jo) {
        JSONArray ja = jo.getJSONArray("Watched Movies");
        for (Object j: ja) {
            JSONObject nextMovie = (JSONObject) j;
            addMovie(ml, nextMovie);
        }
    }

    // MODIFIES: MovieList
    // EFFECTS: reads the movie information from JSON file and adds it to the MovieList
    public void addMovie(MovieList ml, JSONObject jo) {
        String name = jo.getString("Name");
        int releaseDate = jo.getInt("Release Date");
        String rating = jo.getString("Rating");
        String review = jo.getString("Review");

        Movie m = new Movie(name, releaseDate, rating, review);
        ml.addMovie(m);
    }

}
