package ui;

import model.Movie;
import model.MovieList;
import ui.tools.AddMovie;
import ui.tools.LoadMovie;
import ui.tools.MovieTable;
import ui.tools.SaveMovie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Movie tracker GUI application
public class MovieTrackerAppGUI implements ActionListener {

    public static final int WIDTH = 1100;
    public static final int HEIGHT = 700;
    public static final String title = "Movie Tracker";
    public static final Color color = new Color(0xababab);
    public static final Font titleFont = new Font("Times New Roman", Font.BOLD, 30);
    public static final Font subtitleFont = new Font("Times New Roman", Font.BOLD, 15);
    public static final Font font = new Font("Times New Roman", Font.PLAIN, 14);

    protected JFrame frame;
    private MovieList movieList;

    private AddMovie addMovie;
    private MovieTable movieTable = new MovieTable();
    private SaveMovie saveMovie = new SaveMovie();
    private LoadMovie loadMovie = new LoadMovie();
    private JButton buttonAddMovie;
    private JButton buttonRemoveMovie;
    private JButton buttonViewReview;
    private JButton buttonSave;
    private JButton buttonLoad;

    // MODIFIES: this
    // EFFECTS: initializes the movietrackerapp GUI
    public MovieTrackerAppGUI() {

        movieList = new MovieList();

        frame = new JFrame();
        frame.setLayout(null);
        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.getContentPane().setBackground(color);

        createAddMovie();
        movieTable.updateMovieTable(movieList, frame);

        movieTable.setupReviewBox();
        selectMovie();
        setupText();
        movieSaveOrLoad();

        frame.setResizable(true);
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: adds the title to the frame
    private void setupText() {
        JLabel title = new JLabel("Movie Tracker");

        title.setFont(titleFont);
        title.setBounds(WIDTH / 2 - 184 / 2, 5, 500, 20);
        frame.add(title);
    }

    // MODIFIES: MovieList
    // EFFECTS: creates the option to add a seen movie to the movie list
    private void createAddMovie() {

        JLabel label = new JLabel("Please enter the movie information below to add it to your watched list.");
        label.setFont(subtitleFont);
        label.setBounds(30, 20, 500, 100);
        frame.add(label);

        addMovie = new AddMovie();

        buttonAddMovie = new JButton("Enter movie");

        buttonAddMovie.setBounds(300, 210, 110, 20);
        frame.add(buttonAddMovie);
        buttonAddMovie.addActionListener(this);

        addMovie.createLabels(frame);

    }

    // EFFECTS: displays a table with movies watched
    private void displayTable() {
        movieTable.updateMovieTable(movieList, frame);
    }

    // MODIFIES: this
    // EFFECTS: creates two buttons which allow the user to delete a selected row or view selected movie's review
    private void selectMovie() {
        JLabel reviewTitle = new JLabel("Review:");

        buttonRemoveMovie = new JButton("Remove Selected");
        buttonViewReview = new JButton("View Selected Review");

        buttonRemoveMovie.setBounds(563, 350, 140, 30);
        frame.add(buttonRemoveMovie);
        buttonRemoveMovie.addActionListener(this);

        buttonViewReview.setBounds(540, 450, 190, 30);
        frame.add(buttonViewReview);
        buttonViewReview.addActionListener(this);

        reviewTitle.setBounds(770, 250, 200, 30);
        movieTable.viewReview(movieList, frame);
        frame.add(reviewTitle);
    }

    // MODIFIES: this, MovieList
    // EFFECTS: sets up buttons which allows the user to save or load a movie watched list
    public void movieSaveOrLoad() {
        buttonSave = new JButton("Save Movie List");
        buttonLoad = new JButton("Load Movie List");

        buttonSave.setBounds(600, 150, 150, 30);
        frame.add(buttonSave);
        buttonSave.addActionListener(this);

        buttonLoad.setBounds(800, 150, 150, 30);
        frame.add(buttonLoad);
        buttonLoad.addActionListener(this);

    }

    // EFFECTS: checks to see which button was pressed and performs the right action
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonAddMovie) {
            try {
                movieList.addMovie(new Movie(addMovie.getName(), addMovie.getReleaseDate(),
                        addMovie.getRating(), addMovie.getReview()));
                displayTable();
            } catch (NumberFormatException exception) {
                // do nothing since date is not entered properly
            }
        } else if (e.getSource() == buttonRemoveMovie) {
            movieTable.removeMovie(movieList, frame);
        } else if (e.getSource() == buttonViewReview) {
            movieTable.viewReview(movieList, frame);
        } else if (e.getSource() == buttonSave) {
            saveMovie.saveMovieList(movieList);
        } else if (e.getSource() == buttonLoad) {
            movieList = loadMovie.loadMovieList();
            movieTable.updateMovieTable(movieList, frame);
        }
    }
}
