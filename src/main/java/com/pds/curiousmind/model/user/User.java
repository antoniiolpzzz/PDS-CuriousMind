package com.pds.curiousmind.model.user;

import com.pds.curiousmind.model.stat.Stat;
import jdk.jfr.Registered;

import java.util.ArrayList;

public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String username;
    private final Stat stats; //TODO: consider final to ensure it is initialized once and not changed (look for others) (not sure if compatible with JPA/Hibernate or minimal constructors)

    private final List<RegisteredCourse> registeredCourses;

    //FOTO DE USUARIO????? //TODO: id rather use an API to get some cute avatars instead of storing images in the database
    // TODO: consider this API as an option: https://www.dicebear.com

    // CONSTRUCTORS
    public User(String firstName, String lastName, String email, String password, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.username = username;
        this.registeredCourses = new ArrayList<RegisteredCourse>(); // Inicializamos la lista de cursos registrados
        this.stats = new Stat(this); // Inicializamos las estadísticas del usuario
        // TODO: reconsider the minimal Constructors, so we skip this dependence on objects creation (is not clean this way)
    }

    // GETTERS AND SETTERS
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public Stat getStats() {
        return stats;
    }

    public List<RegisteredCourse> getRegisteredCourses() {
        return registeredCourses;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //METHODS:
    public void registerCourse(RegisteredCourse course) {
        registeredCourses.add(course);
    }


}
