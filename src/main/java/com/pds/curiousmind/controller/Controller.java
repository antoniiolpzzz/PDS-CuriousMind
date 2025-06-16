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

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public enum Controller {
    INSTANCE;

    // GLOBAL VARIABLES

    private User currentUser;
    private final GameManager gameManager = GameManager.INSTANCE;
    //private final CourseLibrary courseLibrary = CourseLibrary.INSTANCE;
    //private final UserLibrary userLibrary = UserLibrary.INSTANCE;


    // INITIALIZATION OF THE LIBRARIES AND ADAPTERS

    // INITIALIZATION OF THE CONTROLLER



    //  ********************** TODO: QUITAR LOS CASOS DE PRUEBA  **********************
    Course course;
    RegisteredCourse registeredCourse;
    ContentBlock contentBlock;
    ContentBlock contentBlock2;
    RegisteredContentBlock RegisteredContentBlock;
    RegisteredContentBlock RegisteredContentBlock2;
    List<RegisteredCourse> registeredCourses = new java.util.ArrayList<>();
    List<RegisteredContentBlock> registeredContentBlocks = new ArrayList<>();
    List<Course> allCourses = new java.util.ArrayList<>();



    static {


        // Crear usuario y cursos
        INSTANCE.currentUser = new User("JaviGuardiola", "javi@gmail.com", "password123", "Javi44");
        INSTANCE.course = new Course("German", "Introduction to German.", "icons/course/german.png", new ArrayList<>(), new ArrayList<>());
        Course course2 = new Course("Music", "Learn the basics of music", "icons/course/music.png", new ArrayList<>(), new ArrayList<>());
        Course course3 = new Course("Java", "Learn the basics of the Java programming", "icons/course/js.png", new ArrayList<>(), new ArrayList<>());

        List<Question> questions = new ArrayList<>();

        List<Option> testOptions = new ArrayList<>();
        testOptions.add(new Option("Madrid"));
        testOptions.add(new Option("Berlin"));
        testOptions.add(new Option("Paris"));
        testOptions.add(new Option("Lisboa"));

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
                "Ich esse _____ und Kaffee",
                "Milch"
        );

        Question test = new Test(
                "Select the correct answer",
                "What is the capital of German?",
                "Munich",
                testOptions
        );

        Question translation = new Translate(
                "Translate to English:",
                "Der Hund ist blau",
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
                "Your first words",
                questions,
                difficulty,
                INSTANCE.course
        );

        INSTANCE.contentBlock2 = new ContentBlock(
                "Basic Vocabulary",
                questions,
                difficulty,
                INSTANCE.course
        );

        INSTANCE.RegisteredContentBlock = new RegisteredContentBlock(
                INSTANCE.contentBlock
        );
        INSTANCE.RegisteredContentBlock2 = new RegisteredContentBlock(
                INSTANCE.contentBlock2
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
        INSTANCE.RegisteredContentBlock.setCompleted(true);
        // Asignar contenido bloque registrado al curso registrado
        List<RegisteredContentBlock> registeredContentBlocks = new ArrayList<>();
        registeredContentBlocks.add(INSTANCE.RegisteredContentBlock);
        registeredContentBlocks.add(INSTANCE.RegisteredContentBlock2);
        INSTANCE.registeredCourses.get(0).setRegisteredContentBlocks(registeredContentBlocks);

        Stat stats = new Stat(INSTANCE.currentUser);

      // Haz una lista de tipo LocalDate con una simulación de fechas en las que el usuario ha entrado
      List<LocalDate> entries = new ArrayList<>();
      entries.add(LocalDate.parse("2023-10-01"));
      entries.add(LocalDate.parse("2023-10-02"));
      entries.add(LocalDate.parse("2023-10-03"));
      entries.add(LocalDate.parse("2023-10-14"));
      entries.add(LocalDate.parse("2023-10-25"));
      stats.setEntries(new HashSet<>(entries));

        stats.setExperiencePoints(6300);

        INSTANCE.currentUser.setStats(stats);
    }
    //**** TODO: QUITAR LOS CASOS DE PRUEBA  ******************************************************




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
    }




    // *****************************************************************************************
    // **************************** STATS FUNCTIONS ********************************************
    // *****************************************************************************************

    // GET USER STATS
    public Stat getUserStats() {
        return currentUser.getStats();
    }

    // ADD EXPERIENCE POINTS TO THE USER
    public void addExperiencePoints(Difficulty difficulty) {

        int points = switch (difficulty) {
            case EASY -> 100;
            case MEDIUM -> 300;
            case HARD -> 500;
        };
        getUserStats().addExperiencePoints(points);
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

        //TODO: Check if this implementation is correct
//        if(userlibrary.getByUsername(username) != null)
//        {
//            if(userlibrary.getByUsername(username).getPassword().equals(password)) {
//                currentUser = userlibrary.getByUsername(username);
//            } else {
//                return false;
//            }
//        }

        // Register the user entry in the app
        getUserStats().logEntry();

        return true;
    }

    // CHECK FIELDS AND CREATE A NEW USER

    public boolean signUp(String fullName, String username, String email, String password) {

        //TODO: Check if this implementation is correct
//        if(userlibrary.getByUsername(username) == null)
//        {
//            User user = new User(fullName, email, password, username);
//            userlibrary.add(user);
//        } else {
//            return false;
//        }

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
        // TODO: List<Course> allCourses = courselibrary.getAll();
//        List<Course> allCourses = new ArrayList<>();
//        for (Course course : this.allCourses) {
//            if (!(course instanceof RegisteredCourse)) {
//                allCourses.add(course);
//            }
//        }
        //TODO: las dos siguientes lineas son para la prueba, quitarlas
        List<Course> allCourses = new ArrayList<>(this.allCourses);
        allCourses.removeIf(course -> course.getName().equals("German"));
        return allCourses ;
    }

    // TODO: CREATE A JSON FILE FROM A COURSE (SERIALIZATION)

    public File getJsonFromCourse(Course course) {
        return null;
    }

    // TODO: CREATE COURSE FROM A JSON FILE (DESERIALIZATION)

    public Course createCourseFromJson(File jsonFile) {
        return null;
    }

    // CREATE REGISTERED COURSE FROM A COURSE AND ITS STRATEGY

    public void createRegisteredCourse(Course course, String strategy) {
        new RegisteredCourse(currentUser, course, strategy);
    }

    // *****************************************************************************************
    // **************************** CONTENT BLOCK FUNCTIONS ************************************
    // *****************************************************************************************


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
        //TODO: return (gameManager.totalQuestions() - gameManager.questionsLeft()) / gameManager.totalQuestions() * 100;
        return 0;
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
        // Add a failed question to the game manager
        gameManager.addFailedQuestion(question);
        //TODO: This should handle the lives number of the user
    }
}