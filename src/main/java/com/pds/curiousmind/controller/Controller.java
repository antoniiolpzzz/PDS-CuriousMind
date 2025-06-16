package com.pds.curiousmind.controller;

import com.pds.curiousmind.model.contentblock.ContentBlock;
import com.pds.curiousmind.model.contentblock.Difficulty;
import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.model.gameManager.GameManager;
import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.question.implementation.FillTheGap;
import com.pds.curiousmind.model.question.implementation.FlashCard;
import com.pds.curiousmind.model.question.implementation.Test;
import com.pds.curiousmind.model.question.implementation.Translate;
import com.pds.curiousmind.model.question.option.ImageOption;
import com.pds.curiousmind.model.question.option.Option;
import com.pds.curiousmind.model.registeredContentBlock.RegisteredContentBlock;
import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import com.pds.curiousmind.model.stat.Stat;
import com.pds.curiousmind.model.user.User;
import com.pds.curiousmind.view.playview.question.FillTheGaps;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public enum Controller {
    INSTANCE;

    // GLOBAL VARIABLES

    //private User currentUser;
    private final GameManager gameManager = GameManager.INSTANCE;
    //private final CourseLibrary courseLibrary = CourseLibrary.INSTANCE;
    //private final UserLibrary userLibrary = UserLibrary.INSTANCE;


//  ********************** TODO: QUITAR LA INFORMACIÓN DE PRUEBA:  **********************
User currentUser;
Course course;
RegisteredCourse registeredCourse;
ContentBlock contentBlock;
RegisteredContentBlock RegisteredContentBlock;
List<RegisteredCourse> registeredCourses = new java.util.ArrayList<>();
List<RegisteredContentBlock> registeredContentBlocks = new ArrayList<>();
List<Course> allCourses = new java.util.ArrayList<>();

static {
    // Crear usuario y cursos
    INSTANCE.currentUser = new User("JaviGuardiola", "javi@gmail.com", "password123", "Javi44");
    INSTANCE.course = new Course("Java Basics", "Learn the basics of Java programming.", "icons/course/js.png", new ArrayList<>(), new ArrayList<>());
    Course course2 = new Course("Music", "Learn the basics of music", "icons/course/history.png", new ArrayList<>(), new ArrayList<>());
    Course course3 = new Course("German", "Introduction to German.", "icons/course/german.png", new ArrayList<>(), new ArrayList<>());

    List<Question> questions = new ArrayList<>();

    List<Option> testOptions = new ArrayList<>();
    testOptions.add(new Option("Madrid"));
    testOptions.add(new Option("Barcelona"));
    testOptions.add(new Option("Valencia"));
    testOptions.add(new Option("Seville"));

    List<Option> flashCardOptions = new ArrayList<>();
    flashCardOptions.add(new ImageOption("Apfel", "icons/course/comida-sana.png"));
    flashCardOptions.add(new ImageOption("Karotte", "icons/course/zanahoria.png"));
    flashCardOptions.add(new ImageOption("Zwiebel", "icons/course/cebolla.png"));

    List<Option> translateOptions = new ArrayList<>();
    translateOptions.add(new Option("New"));
    translateOptions.add(new Option("green"));
    translateOptions.add(new Option("sheep"));
    translateOptions.add(new Option("iron"));
    translateOptions.add(new Option("table"));
    translateOptions.add(new Option("are"));
    translateOptions.add(new Option("pencil"));
    translateOptions.add(new Option("cat"));

    Question fillTheGaps = new FillTheGap(
            "Complete the sentence:",
            "The ___ is the satellite of the Earth.",
            "moon"
    );

    Question test = new Test(
            "Select the capital of Spain:",
            "What is the capital of Spain?",
            "Madrid",
            testOptions
    );

    Question translation = new Translate(
            "Translate to English:",
            "El perro es azul",
            "The dog is blue",
            translateOptions
    );

    Question flashcard = new FlashCard(
            "Choose the correct answer:",
            "Where is the onion?",
            "Zwiebel",
            flashCardOptions
    );

    questions.add(fillTheGaps);
    questions.add(test);
    questions.add(translation);
    questions.add(flashcard);

    Difficulty difficulty = Difficulty.EASY;

    INSTANCE.contentBlock = new ContentBlock(
            "Basic Vocabulary",
            questions,
            difficulty,
            INSTANCE.course
    );

    INSTANCE.RegisteredContentBlock = new RegisteredContentBlock(
            INSTANCE.contentBlock
    );
    // Añadir cursos a la lista de todos los cursos
    INSTANCE.allCourses.add(INSTANCE.course);
    INSTANCE.allCourses.add(course2);
    INSTANCE.allCourses.add(course3);

    // Crear cursos registrados
    INSTANCE.registeredCourse = new RegisteredCourse(INSTANCE.currentUser, INSTANCE.course, "SHUFFLED");

    // Añadir cursos registrados a la lista
    INSTANCE.registeredCourses.add(INSTANCE.registeredCourse);

    // Asignar cursos registrados al usuario
    INSTANCE.currentUser.setRegisteredCourses(INSTANCE.registeredCourses);

    // Asignar contenido bloque registrado al curso registrado
    List<RegisteredContentBlock> registeredContentBlocks = new ArrayList<>();
    registeredContentBlocks.add(INSTANCE.RegisteredContentBlock);
    INSTANCE.registeredCourses.get(0).setRegisteredContentBlocks(registeredContentBlocks);
}


    //GET CURRENT REGISTERED COURSE
    public RegisteredCourse getCurrentRegisteredCourse() {
        // Return the first registered course for simplicity
        // In a real application, you might want to handle multiple registered courses differently
        return currentUser.getRegisteredCourses().isEmpty() ? null : currentUser.getRegisteredCourses().get(0);
    }

    //  ********************** TODO: QUITAR LA INFORMACIÓN DE PRUEBA:  **********************


    // INITIALIZATION OF THE LIBRARIES AND ADAPTERS


    // INITIALIZATION OF THE CONTROLLER



//***********************************************  USER FUNCTIONS *********************************************** //


    // GET USER PHOTO BY API

    public String getUserPhoto() {
        // Return the URL or path to the user's photo
        // This could involve fetching from a database or an API
        //TODO: User photo ??  -> return currentUser.getPhotoUrl();
        return "icons/button/user.png";
    }


    // GET CURRENT USER
    public User getCurrentUser() {
        return currentUser;
    }




//***********************************************  STATS FUNCTIONS *********************************************** //

    // GET USER STATS

    public Stat getUserStats() {
        return currentUser.getStats();
    }

    // ADD EXPERIENCE POINTS TO THE USER

    public void addExperiencePoints(Difficulty difficulty) {
        // Add the specified points to the current user's experience
        int points = switch (difficulty) {
            case EASY -> 100;
            case MEDIUM -> 200;
            case HARD -> 300;
        };
        getUserStats().addExperiencePoints(points);
    }

    // GET LEVEL OF THE USER

    public int getUserLevel() {
        return getUserStats().getLevel();
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

        // TODO: List<Course> courses = courselibrary.getAll();

        return allCourses;
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
        // Add the new course to the course library or database
        // This could involve using a library like Gson or Jackson to deserialize the JSON into a Course object
        return null;
    }


    // CREATE REGISTERES COURSE FROM A COURSE AND ITS STRATEGY

    public void createRegisteredCourse(Course course, String strategy) {
        // Create a new RegisteredCourse object with the provided course and strategy
        // This could involve setting the course, strategy, and any other necessary fields
        new RegisteredCourse(currentUser, course, strategy);
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

    // GET THE NUMBER OF QUESTIONS ANSWERED IN A THE GAME

    public int getBlockProgress() {
        // Get the number of questions answered in the game
        // This could involve checking the game manager or user stats for the number of questions answered
        //return (gameManager.totalQuestions() - gameManager.questionsLeft()) / gameManager.totalQuestions() * 100;
        return 30;
    }

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

    // ADD A FAILED QUESTION TO THE GAME MANAGER
    public void addFailedQuestion(Question question) {
        // Add a failed question to the game manager
        gameManager.addFailedQuestion(question);
        //TODO: This should handle the lives number of the user
    }







}
