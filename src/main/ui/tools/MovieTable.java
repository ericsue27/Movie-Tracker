package ui.tools;

import exceptions.MovieNotInMovieListException;
import model.Movie;
import model.MovieList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.lang.reflect.Array;

// creates the table that displays the users watched movies so far
public class MovieTable {

    private JTable table;
    private JScrollPane scrollPane = new JScrollPane();
    private String[] tableTitles = {"Name", "Release Date", "Rating"};
    private String[][] movieInfo;
    private JLabel title = new JLabel("Your Watched Movie List");
    private JTextArea reviewBox = new JTextArea();
    private DefaultTableModel model;

    // MODIFIES: this
    // EFFECTS: updates the movie table with the most up to date movieList
    public void updateMovieTable(MovieList movieList, JFrame frame) {

        movieInfo = new String[movieList.getSize()][3];

        title.setBounds(30, 250, 500, 30);
        frame.add(title);

        for (int row = 0; row < movieList.getSize(); row++) {
            movieInfo[row][0] = movieList.getMovie(row).getName();
            movieInfo[row][1] = String.valueOf(movieList.getMovie(row).getReleaseDate());
            movieInfo[row][2] = movieList.getMovie(row).getRating();
        }

        table = new JTable(movieInfo, tableTitles);

        scrollPane.setViewportView(table);
        scrollPane.setBounds(30, 285, 500, 300);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.add(scrollPane);
    }

    // MODIFIES: this
    // EFFECTS: removes selected row from movie table
    public void removeMovie(MovieList movieList, JFrame frame) {

        try {
            String name = String.valueOf(table.getValueAt(table.getSelectedRow(), 0));

            int index = movieList.findMovie(name);
            Movie m = movieList.getMovie(index);

            try {
                movieList.removeMovie(m);
                updateMovieTable(movieList, frame);
            } catch (MovieNotInMovieListException e) {
                // do nothing due to movie not in movie list
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            // do nothing since nothing is selected
        }
    }

    // EFFECTS: setups the reviewBox where you can view your review
    public void setupReviewBox() {
        reviewBox.setLineWrap(true);
        reviewBox.setBackground(Color.LIGHT_GRAY);
        reviewBox.setBounds(770, 280, 280, 300);
    }

    // EFFECTS: displays the review associated with selected movie; displays empty string if nothing selected
    public void viewReview(MovieList movieList, JFrame frame) {
        String review;

        try {
            String name = String.valueOf(table.getValueAt(table.getSelectedRow(), 0));

            int index = movieList.findMovie(name);
            Movie m = movieList.getMovie(index);

            review = m.getReview();

            reviewBox.setText(review);
            reviewBox.setText(review);

        } catch (ArrayIndexOutOfBoundsException e) {
            reviewBox.setText("");
        }

        frame.add(reviewBox);
    }

}