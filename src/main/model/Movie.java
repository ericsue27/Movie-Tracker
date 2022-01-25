package model;

import org.json.JSONObject;

// represents a movie with name, given rating and given review
public class Movie {

    private String name;
    private int releaseDate;
    private String rating;
    private String review;

    // REQUIRES: name has to have a string length of at least 1
    // EFFECTS: creates a movie with associated name, rating and review
    public Movie(String name, int releaseDate, String rating, String review) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.review = review;
    }

    // getters
    public String getName() {
        return name;
    }

    public String getRating() {
        return rating;
    }

    public String getReview() {
        return review;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    // MODIFIES: this
    // EFFECTS: allows the user to edit the name of the movie
    public void editName(String newName) {
        this.name = newName;
    }

    // MODIFIES: this
    // EFFECTS: allows the user to edit the release date of the movie
    public void editReleaseDate(int newReleaseDate) {
        this.releaseDate = newReleaseDate;
    }

    // MODIFIES: this
    // EFFECTS: allows the user to edit the review associated with this movie
    public void editReview(String newReview) {
        this.review = newReview;
    }

    // MODIFIES: this
    // EFFECTS: allows the user to edit their rating associated with this movie
    public void editRating(String newRating) {
        this.rating = newRating;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Name", name);
        json.put("Release Date", releaseDate);
        json.put("Rating", rating);
        json.put("Review", review);
        return json;
    }
}
