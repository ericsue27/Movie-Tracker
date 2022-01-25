package persistence;

// TestJsonWrite was modeled from given sample application JsonSerializationDemo. Link below:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

import model.Movie;
import model.MovieList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestJsonWrite {

    Movie m1 = new Movie("The Cremator", 1969, "8/10", "Surreal.");
    Movie m2 = new Movie("Stalker", 1979, "10/10", "Tarkovskys best.");

    @Test
    void testJsonWriteFileNoFile() {
        try {
            MovieList ml = new MovieList();
            JsonWrite write = new JsonWrite("./data/\0illegal:fileName.json");
            write.open();
            write.write(ml);
            write.close();
            fail("Error. IOException expected.");
        } catch (IOException e) {
            // expected exception
        }
    }

    @Test
    void testJsonWriteNormal() {
        try {
            MovieList ml = new MovieList();
            ml.addMovie(m1);
            ml.addMovie(m2);

            JsonWrite write = new JsonWrite("./data/testWatchedMoviesNormalWrite.json");
            write.open();
            write.write(ml);
            write.close();

            JsonRead read = new JsonRead("./data/testWatchedMoviesNormalWrite.json");
            ml = read.read();
            assertEquals(2, ml.getSize());

            assertEquals("The Cremator", ml.getMovie(0).getName());
            assertEquals(1969, ml.getMovie(0).getReleaseDate());
            assertEquals("8/10", ml.getMovie(0).getRating());
            assertEquals("Surreal.", ml.getMovie(0).getReview());

            assertEquals("Stalker", ml.getMovie(1).getName());
            assertEquals(1979, ml.getMovie(1).getReleaseDate());
            assertEquals("10/10", ml.getMovie(1).getRating());
            assertEquals("Tarkovskys best.", ml.getMovie(1).getReview());



        } catch (IOException e) {
            fail("Error. Unexpected IOException.");
        }
    }
}
