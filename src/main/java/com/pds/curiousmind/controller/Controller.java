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
import com.pds.curiousmind.util.AppConfig;
import com.pds.curiousmind.util.ImageUtils;
import com.pds.curiousmind.util.mapper.service.MapperFormat;
import com.pds.curiousmind.util.mapper.service.CourseMapperService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public enum Controller {
    INSTANCE;

    // CONTROLLER VARIABLES
    private User currentUser;
    private final MapperFormat mapperFormat = MapperFormat.valueOf(AppConfig.get("mapping.file.format"));
    private static final String USER_PHOTO_API = "https://api.dicebear.com/9.x/dylan/png?size=128&seed=";

    // INITIALIZATION OF THE LIBRARIES AND ADAPTERS AND SERVICES
    private final CourseLibrary courseLibrary = CourseLibrary.INSTANCE;
    private final UserLibrary userLibrary = UserLibrary.INSTANCE;
    private final GameManager gameManager = GameManager.INSTANCE;
    private final CourseMapperService courseMapperService = CourseMapperService.INSTANCE;


    // *****************************************************************************************
    // **************************** USER FUNCTIONS *********************************************
    // *****************************************************************************************

    // GET CURRENT USER
    public User getCurrentUser() {
        return currentUser;
    }


    // GET USER PHOTO BY API
    public String getUserPhoto() {
        String urlString = USER_PHOTO_API + (currentUser != null ? currentUser.getUsername() : "user");
        String imagePath = ImageUtils.downloadImage(urlString, ".png");
        return imagePath != null ? imagePath : "icons/button/user.png";
    }

    public String downloadImageFromUrl(String urlString) {
        String imagePath = ImageUtils.downloadImage(urlString, ".png");
        return imagePath != null ? imagePath : "icons/stat/time.jpg";
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

        if(userLibrary.getByUsername(username) != null)
        {
            if(userLibrary.getByUsername(username).getPassword().equals(password)) {
                currentUser = userLibrary.getByUsername(username);

                currentUser.logEntry();
                userLibrary.update(currentUser);
                return true;
            }
        }
        return false;
    }

    // CHECK FIELDS AND CREATE A NEW USER

    public boolean signUp(String fullName, String username, String email, String password) {
        if (userLibrary.getByUsername(username) == null) {
            User user = new User(fullName, email, password, username);
            userLibrary.add(user);
            return true;
        } else {
            return false;
        }
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
        List<Course> allCourses = new ArrayList<>(courseLibrary.getAll());
        getRegisteredCourses().forEach(registeredCourse -> {
            allCourses.removeIf(
                    course -> course.getId().equals(registeredCourse.getCourse().getId())
                                    && !registeredCourse.isCompleted());
        });
        //TODO: Filter allCourses so that Registered courses dont appear
        // CREO QUE LO HE SOLUCIOANDO CON EL FOR EACH
        return allCourses ;
    }

    // CREATE A JSON FILE FROM A COURSE (SERIALIZATION)

    public File getJsonFromCourse(Course course) {
        return courseMapperService.fromEntity(course, mapperFormat);
    }

    // CREATE COURSE FROM A JSON FILE (DESERIALIZATION)

    public Course createCourseFromJson(File jsonFile) {
        Course mappedCourse = courseMapperService.toEntity(jsonFile);
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

    // GET THE NUMBER OF QUESTIONS ANSWERED IN THE GAME
    public int getLivesLeft() {

        return gameManager.getLives();
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