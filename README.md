# Eric Sue's Personal Project 

## Movie Tracking Program

The application I plan to make is a movie tracking program. This application will allow the user to keep track of the 
movies they have seen, write reviews for the movie and give a rating to the movie. The target audience of my program is 
anyone who enjoys watching movies and would like a way to track, rate and review the movies they have seen. This 
program could also be used to track books you have read or games you have played as the user will be allowed to title 
the object anything. This project is of interest to me as I enjoy watching movies, and I would find it very useful to 
have a way to track the movies I have seen.
  
## User Stories
- As a user, I want to add a movie I have seen to a "watched" list
- As a user, I want to give the movie a release date, a rating out of 10, and a review
- As a user, I want to be able to edit the title, release date, rating and review
- As a user, I want to view the list of movies I have seen, and the associated rating and review.
- As a user, I want to be able to delete a movie from the "watched" list
- As a user, I want to be able to save my watched list to a file
- As a user, I want to be able to load my watched list from a file

## Phase 4: Task 2
I chose to test and design a class in my model package that is robust. The class I chose was MovieList in the model 
package. The method I chose to throw an exception was removeMovie, as it had an expects clause which expected the Movie
passed as a parameter to belong to the current MovieList. This method now throws a MovieNotInMovieListException whenever
the given Movie is not present in MovieList.

## Phase 4: Task 3
If I had more time to work on my project, I would have:
- Added more exceptions for methods which have an expects clause. An example of this is seen in the MovieList class in 
  the getMovie method. This method requires that the index given as a parameter is within the index of the current 
  MovieList.
- Create a single class called setupText which could have set up all the text displayed on screen rather than putting 
  all the text on screen using the MovieTrackerAppGUI class.
  
- Create a single class called setupButtons which setup all the buttons seen instead of creating the buttons in the 
  MovieTrackerAppGUI.
