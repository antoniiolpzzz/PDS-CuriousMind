package com.pds.curiousmind.controller;

import com.pds.curiousmind.model.contentblock.ContentBlock;
import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import com.pds.curiousmind.model.stat.Stat;
import com.pds.curiousmind.model.user.User;

import java.io.File;
import java.util.List;

public class Controller {


    // GLOBAL VARIABLES

    private User currentUser;

    // INITIALIZATION OF THE LIBRARIES AND ADAPTERS




    // INITIALIZATION OF THE CONTROLLER



    // **************************** USER FUNCTIONS **************************** //



    // **************************** STATS FUNCTIONS **************************** //

    // GET USER STATS
    public Stat getUserStats(User user) {
        return user.getStats();
    }






    // **************************** AUTHENTICATION FUNCTIONS **************************** //

    // CHECK FIELDS FOR THE USER TO LOG IN

    public boolean checkFields(String username, String password) {

        //Check in the database if the user exists and the password is correct
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

    public List<RegisteredCourse> getRegisteredCourses(User user) {

        List<RegisteredCourse> registeredCourses = null;

        user.getRegisteredCourses().forEach(registeredCourse -> {
            registeredCourses.add(registeredCourse);
        });

        return registeredCourses;
    }

    //GET ALL COURSES IN THE DATABASE

    public List<Course> getAllCourses() {

        List<Course> courses = null;

        //List<Course> courses = courselibrary.getAll();

        return courses;
    }

    // CREATE A JSON FILE FROM A COURSE

    public File getJsonFromCourse(Course course) {
        // Convert the course object to a JSON file and return it
        // This could involve using a library like Gson or Jackson to serialize the course object
        return null;
    }

    // CREATE COURSE FROM A JSON FILE

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


    // **************************** JSON FUNCTIONS **************************** //



    // **************************** CONTENT BLOCK FUNCTIONS **************************** //



    // **************************** QUESTION FUNCTIONS **************************** //



    // **************************** GAME MANAGER FUNCTIONS **************************** //












}
