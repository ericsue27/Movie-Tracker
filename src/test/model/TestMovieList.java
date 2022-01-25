package model;

import exceptions.MovieNotInMovieListException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestMovieList {

    private MovieList movList;

    private Movie m1 = new Movie("Breathless", 1960, "9/10", "Left me breathless");
    private Movie m2 = new Movie("Suspiria", 2018, "5/10",
            "Didn't like it as much as the original");
    private Movie m3 = new Movie("Tokyo Drifter", 1966, "8/10",
            "Wait that wasn't fast and furious");


    @BeforeEach
    public void setup() {
        movList = new MovieList();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, movList.getSize());
    }

    @Test
    public void testFindMovie() {
        movList.addMovie(m1);
        movList.addMovie(m2);
        movList.addMovie(m3);

        assertEquals(1, movList.findMovie("Suspiria"));
    }

    @Test
    public void testFindMovieEmpty() {
        assertEquals(-1, movList.findMovie("The Face of Another"));
    }

    @Test
    public void testAddMovie() {
        movList.addMovie(m2);
        assertEquals("Suspiria", movList.getMovie(0).getName());
        assertEquals(2018, movList.getMovie(0).getReleaseDate());
        assertEquals("5/10", movList.getMovie(0).getRating());
        assertEquals("Didn't like it as much as the original", movList.getMovie(0).getReview());
    }

    @Test
    public void testRemoveMovieFound() {
        movList.addMovie(m2);
        movList.addMovie(m3);

        try {
            movList.removeMovie(m2);
            assertEquals(1, movList.getSize());
            assertEquals(m3, movList.getMovie(0));
        } catch (MovieNotInMovieListException e) {
            fail("MovieNotInMovieListException not expected.");
        }
    }

    @Test
    public void testRemoveMovieNotFound() {

        try {
            movList.removeMovie(m2);
            fail("MovieNotInMovieListException expected");
        } catch (MovieNotInMovieListException e) {
            // pass
        }
    }

    @Test
    public void testGetMovie() {

        movList.addMovie(m1);
        movList.addMovie(m2);

        assertEquals("Breathless", movList.getMovie(1).getName());
        assertEquals(1960, movList.getMovie(1).getReleaseDate());
        assertEquals("9/10", movList.getMovie(1).getRating());
        assertEquals("Left me breathless", movList.getMovie(1).getReview());

    }

    @Test
    public void testGetSize() {

        movList.addMovie(m1);
        movList.addMovie(m2);
        movList.addMovie(m3);

        assertEquals(3, movList.getSize());

    }

}
