package com.pds.curiousmind.controller;

import com.pds.curiousmind.model.contentblock.ContentBlock;
import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.model.gameManager.GameManager;
import com.pds.curiousmind.model.library.implementation.CourseLibrary;
import com.pds.curiousmind.model.library.implementation.UserLibrary;
import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.registeredContentBlock.RegisteredContentBlock;
import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import com.pds.curiousmind.model.stat.Stat;
import com.pds.curiousmind.model.user.User;

import java.io.File;
import java.util.List;

public enum Controller {
    INSTANCE;

    // GLOBAL VARIABLES

    //private User currentUser;
    private final GameManager gameManager = GameManager.INSTANCE;
    //private final CourseLibrary courseLibrary = CourseLibrary.INSTANCE;
    //private final UserLibrary userLibrary = UserLibrary.INSTANCE;


    // TODO: QUITAR LA INFORMACIÓN DE PRUEBA:
    User currentUser = new User("Javi", "Guardiola", "javi@gmail.com", "password123", "Javi44");

    Stat stats = new Stat(currentUser);


    // INITIALIZATION OF THE LIBRARIES AND ADAPTERS


    // INITIALIZATION OF THE CONTROLLER



    // **************************** USER FUNCTIONS **************************** //

    // GET CURRENT USER
    public User getCurrentUser() {
        return currentUser;
    }

    // **************************** STATS FUNCTIONS **************************** //

    // GET USER STATS

    public Stat getUserStats() {
        return currentUser.getStats();
    }

    // ADD EXPERIENCE POINTS TO THE USER

    public void addExperiencePoints(int points) {
        // Add the specified points to the current user's experience
        getUserStats().addExperiencePoints(points);
    }




    // **************************** AUTHENTICATION FUNCTIONS **************************** //

    // CHECK FIELDS FOR THE USER AND LOG IN

    public boolean logIn(String username, String password) {

        //Check in the database if the user exists and the password is correct

        // Register the user entry in the app
        getUserStats().logEntry();

        return true;
    }

    // CHECK FIELDS AND CREATE A NEW USER

    public boolean signUp(String fullName,String username,String email, String password) {

        // Check if the username or email already exists in the database

        // Create new user in the database with the provided information

        return true;
    }



    // **************************** COURSE FUNCTIONS **************************** //



    //GET ALL REGISTERED COURSES OF THE USER

    public List<RegisteredCourse> getRegisteredCourses() {

        return currentUser.getRegisteredCourses();
    }

    //GET ALL COURSES IN THE DATABASE

    public List<Course> getAllCourses() {

        //List<Course> courses = courselibrary.getAll();

        return null;
    }

    // CREATE A JSON FILE FROM A COURSE (SERIALIZATION)

    public File getJsonFromCourse(Course course) {
        // Convert the course object to a JSON file and return it
        // This could involve using a library like Gson or Jackson to serialize the course object
        return null;
    }

    // CREATE COURSE FROM A JSON FILE (DESERIALIZATION)

    public Course createCourseFromJson(File jsonFile) {
        // Read the JSON file and convert it to a Course object
        // This could involve using a library like Gson or Jackson to deserialize the JSON into a Course object
        return null;
    }


    // CREATE REGISTERES COURSE FROM A COURSE AND ITS STRATEGY

    public RegisteredCourse createRegisteredCourse(Course course, String strategy) {
        // Create a new RegisteredCourse object with the provided course and strategy
        // This could involve setting the course, strategy, and any other necessary fields
        return new RegisteredCourse(currentUser, course, strategy);
    }



    // **************************** CONTENT BLOCK FUNCTIONS **************************** //




    // **************************** QUESTION FUNCTIONS **************************** //

    // VALIDATE THE ANSWER OF A QUESTION
    public boolean validateAnswer(Question question, String answer) {
        // Validate the answer of the question
        // This could involve checking if the answer matches the expected answer for the question
        return question.validateAnswer(answer);
    }



    // **************************** GAME MANAGER FUNCTIONS **************************** //


    // INITIALIZE THE GAME MANAGER WITH A COURSE AND A CONTENT BLOCK

    public Question initializeGameManager(RegisteredCourse course, RegisteredContentBlock contentBlock) {

        gameManager.initializeGame(course,contentBlock);

        return gameManager.getNextQuestion();
    }

    // GET THE NEXT QUESTION FROM THE GAME MANAGER

    public Question getNextQuestion() {
        // Get the next question from the game manager
        return gameManager.getNextQuestion();
    }

    // END THE GAME

    public void endGame() {
        // End the game and reset the game manager
        gameManager.deactivateGame();
    }








}
