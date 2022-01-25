package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestMovie {

    private Movie m;

    @BeforeEach
    public void setup() {
        m = new Movie("The Tree of Life", 2011, "10/10", "Best movie, would recommend");
    }

    @Test
    public void testConstructor() {
        assertEquals("The Tree of Life", m.getName());
        assertEquals(2011, m.getReleaseDate());
        assertEquals("10/10", m.getRating());
        assertEquals("Best movie, would recommend", m.getReview());
    }

    @Test
    public void testEditName() {
        m.editName("A Brighter Summer Day");

        assertEquals("A Brighter Summer Day", m.getName());

    }

    @Test
    public void testEditReleaseDate() {
        m.editReleaseDate(2027);

        assertEquals(2027, m.getReleaseDate());
    }

    @Test
    public void testEditRating() {
        m.editRating("5/10");

        assertEquals("5/10", m.getRating());
    }

    @Test
    public void testEditReview() {
        m.editReview("5/10");

        assertEquals("5/10", m.getReview());
    }

}
