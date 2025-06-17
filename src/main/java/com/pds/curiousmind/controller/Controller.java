package com.pds.curiousmind.controller;

import com.pds.curiousmind.model.contentblock.Difficulty;
import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.model.gameManager.GameManager;
import com.pds.curiousmind.model.library.implementation.CourseLibrary;
import com.pds.curiousmind.model.library.implementation.UserLibrary;
import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.registeredContentBlock.RegisteredContentBlock;
import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import com.pds.curiousmind.model.stat.Stat;
import com.pds.curiousmind.model.strategy.StrategyType;
import com.pds.curiousmind.model.user.User;
import com.pds.curiousmind.util.mapper.service.CourseFormat;
import com.pds.curiousmind.util.mapper.service.CourseMapperService;

import java.io.File;
import java.util.List;

public enum Controller {
    INSTANCE;

    // GLOBAL VARIABLES

    private User currentUser;
    private final GameManager gameManager = GameManager.INSTANCE;

    // INITIALIZATION OF THE LIBRARIES AND ADAPTERS

    private final CourseLibrary courseLibrary = CourseLibrary.INSTANCE;
    private final UserLibrary userLibrary = UserLibrary.INSTANCE;

    // INITIALIZATION OF THE CONTROLLER


    // *****************************************************************************************
    // **************************** USER FUNCTIONS *********************************************
    // *****************************************************************************************

    // GET CURRENT USER
    public User getCurrentUser() {
        return currentUser;
    }


    // GET USER PHOTO BY API
    public String getUserPhoto() {
        // Return the URL or path to the user's photo
        //TODO: User photo ??  -> return currentUser.getPhotoUrl();
        return "icons/button/user.png";
        //return "https://api.dicebear.com/9.x/dylan/svg?seed=" + (currentUser != null ? currentUser.getUsername() : "default");
    }




    // *****************************************************************************************
    // **************************** STATS FUNCTIONS ********************************************
    // *****************************************************************************************

    // GET USER STATS
    public Stat getUserStats() {
        return currentUser.getStats();
    }

    // GET LEVEL OF THE USER
    public int getUserLevel() {
        return getUserStats().getLevel();
    }



    // *****************************************************************************************
    // **************************** AUTHENTICATION FUNCTIONS ***********************************
    // *****************************************************************************************

    // LOG IN THE USER

    public boolean logIn(String username, String password) {
        //Check in the database if the user exists and the password is correct

        if(userLibrary.getByUsername(username) != null)
        {
            if(userLibrary.getByUsername(username).getPassword().equals(password)) {
                currentUser = userLibrary.getByUsername(username);
                // Register the user entry in the app
                // TODO: Give a look for a better implementation of this on the user directly
                getUserStats().logEntry();
                userLibrary.update(currentUser);
                return true;
            } else {
                return false;
            }
        }
        return false;

    }

    // CHECK FIELDS AND CREATE A NEW USER

    public boolean signUp(String fullName, String username, String email, String password) {

        //TODO: Check if this implementation is correct
        if(userLibrary.getByUsername(username) == null)
        {
            User user = new User(fullName, email, password, username);
            userLibrary.add(user);
        } else {
            return false;
        }

        return true;
    }



    // *****************************************************************************************
    // **************************** COURSE FUNCTIONS *******************************************
    // *****************************************************************************************

    // GET ALL REGISTERED COURSES OF THE USER

    public List<RegisteredCourse> getRegisteredCourses() {
        return currentUser.getRegisteredCourses();
    }

    // GET ALL COURSES IN THE DATABASE

    public List<Course> getAllCourses() {
        return courseLibrary.getAll();
    }

    // TODO: CREATE A JSON FILE FROM A COURSE (SERIALIZATION)

    public File getJsonFromCourse(Course course) {
        // Use the CourseMapperService to convert the Course object to a JSON file
        //TODO: DO THIS CORRECTLY
        return CourseMapperService.INSTANCE.fromEntity(course, CourseFormat.YAML);
    }

    // TODO: CREATE COURSE FROM A JSON FILE (DESERIALIZATION)

    public Course createCourseFromJson(File jsonFile) {
        // Use the CourseMapperService to convert the JSON file to a Course object
        Course mappedCourse = CourseMapperService.INSTANCE.toEntity(jsonFile);
        return courseLibrary.add(mappedCourse);
    }

    // CREATE REGISTERED COURSE FROM A COURSE AND ITS STRATEGY

    public void createRegisteredCourse(Course course, StrategyType strategy) {
        currentUser.registerInCourse(course, strategy);
        userLibrary.update(currentUser);
    }



    // *****************************************************************************************
    // **************************** QUESTION FUNCTIONS *****************************************
    // *****************************************************************************************

    // VALIDATE THE ANSWER OF A QUESTION

    public boolean validateAnswer(Question question, String answer) {
        return question.validateAnswer(answer);
    }

    // *****************************************************************************************
    // **************************** GAME MANAGER FUNCTIONS *************************************
    // *****************************************************************************************

    // GET THE NUMBER OF QUESTIONS ANSWERED IN THE GAME
    public int getBlockProgress() {
        return gameManager.getCurrentProgress();
    }

    // INITIALIZE THE GAME MANAGER WITH A COURSE AND A CONTENT BLOCK

    public Question initializeGameManager(RegisteredCourse course, RegisteredContentBlock contentBlock) {
        gameManager.initializeGame(course, contentBlock);
        return gameManager.getNextQuestion();
    }

    // GET THE NEXT QUESTION FROM THE GAME MANAGER

    public Question getNextQuestion() {
        return gameManager.getNextQuestion();
    }

    // END THE GAME

    public void endGame() {
        gameManager.deactivateGame();
    }

    // ADD A FAILED QUESTION TO THE GAME MANAGER

    public void addFailedQuestion(Question question) {
        gameManager.addFailedQuestion(question);
        //TODO: This should handle the lives number of the user
    }

    // ADD EXPERIENCE POINTS TO THE USER
    public void completeContentBlock(Difficulty difficulty) {
        gameManager.markBlockAsCompleted();
        currentUser.addExperiencePoints(getPointsForDifficulty(difficulty));
        currentUser.addTimeSpent(gameManager.getGameDuration());
        userLibrary.update(currentUser);
    }

    public int getPointsForDifficulty(Difficulty difficulty) {
        return switch (difficulty) {
            case EASY -> 100;
            case MEDIUM -> 200;
            case HARD -> 300;
        };
    }

}