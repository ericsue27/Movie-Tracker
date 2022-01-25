package ui.tools;

import javax.swing.*;

// creates the sections where the user enters a new movie
public class AddMovie {

    private JTextField entermovieName;
    private JTextField enterReleaseDate;
    private JTextField enterRating;
    private JTextField enterReview;
    private JLabel titleLabel = new JLabel("Name:");
    private JLabel releaseDateLabel = new JLabel("Release Date:");
    private JLabel ratingLabel = new JLabel("Rating (ex. 7/10):");
    private JLabel reviewLabel = new JLabel("Review:");

    // MODIFIES: MovieTrackerAppGUI
    // EFFECTS: creates text boxes for inputting movie information
    public void createLabels(JFrame frame) {

        titleLabel.setBounds(30, 45, 100, 100);
        frame.add(titleLabel);

        releaseDateLabel.setBounds(170, 45, 100, 100);
        frame.add(releaseDateLabel);

        ratingLabel.setBounds(310, 45, 100, 100);
        frame.add(ratingLabel);

        reviewLabel.setBounds(30, 105, 100, 100);
        frame.add(reviewLabel);


        entermovieName = new JTextField();
        entermovieName.setBounds(30, 110, 100, 22);
        frame.add(entermovieName);

        enterReleaseDate = new JTextField();
        enterReleaseDate.setBounds(170, 110, 100, 22);
        frame.add(enterReleaseDate);

        enterRating = new JTextField();
        enterRating.setBounds(310, 110, 100, 22);
        frame.add(enterRating);

        enterReview = new JTextField();
        enterReview.setBounds(30, 170, 380, 22);
        frame.add(enterReview);
    }

    // EFFECTS: returns the name written in the name text box
    public String getName() {
        return entermovieName.getText();
    }

    // EFFECTS: returns the release date written in the release date text box
    public int getReleaseDate() {
        return Integer.valueOf(enterReleaseDate.getText());
    }

    // EFFECTS: returns the rating written in the rating text box
    public String getRating() {
        return enterRating.getText();
    }

    // EFFECTS: returns the review written in the review text box
    public String getReview() {
        return enterReview.getText();
    }

}
