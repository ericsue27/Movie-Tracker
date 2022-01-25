package persistence;

import model.MovieList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// TestJsonRead was modeled from given sample application JsonSerializationDemo. Link below:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class TestJsonRead {

    @Test
    void testReadNoFile() {
        JsonRead r = new JsonRead("./data/randomnonexistingfile.json");

        try {
            MovieList ml = r.read();
        } catch (IOException e) {
            // want this to happen
        }
    }

    @Test
    void testReadEmptyMovieList() {
        JsonRead r = new JsonRead("./data/testWatchedMoviesEmpty.json");

        try {
            MovieList ml = r.read();
            assertEquals(0, ml.getSize());
        } catch (IOException e) {
            fail("Did not expect exception");
        }
    }

    @Test
    void testReadMovieListNormal() {
        JsonRead r = new JsonRead("./data/testWatchedMoviesNormal.json");

        try {
            MovieList ml = r.read();

            assertEquals(2, ml.getSize());

            assertEquals("La Haine", ml.getMovie(0).getName());
            assertEquals(1995, ml.getMovie(0).getReleaseDate());
            assertEquals("9/10", ml.getMovie(0).getRating());
            assertEquals("Powerful", ml.getMovie(0).getReview());

            assertEquals("The Tree of Life", ml.getMovie(1).getName());
            assertEquals(2011, ml.getMovie(1).getReleaseDate());
            assertEquals("10/10", ml.getMovie(1).getRating());
            assertEquals("Great movie!", ml.getMovie(1).getReview());

        } catch (IOException e) {
            fail("Did not expect exception");
        }
    }
}
