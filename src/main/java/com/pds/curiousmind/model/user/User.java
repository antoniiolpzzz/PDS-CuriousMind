package com.pds.curiousmind.model.user;

import com.pds.curiousmind.model.stat.Stat;

public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String username;
    private Stat stats;

    private List<RegisteredCourse> registeredCourses = new ArrayList<RegisteredCourse>();

    //BIBLIOTECA DE CURSOS DE UN USUARIO?????
    //FOTO DE USUARIO?????



    public User(String firstName, String lastName, String email, String password, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.username = username;
        this.stats = new Stat(0, 0, 0, 0, 0); // Inicializamos las estadísticas del usuario
    }

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

    public void registerCourse(Course course) {
        RegisteredCouse registeredCourse = new RegisteredCourse(course);
        registeredCourses.add(registeredCourse);
    }

    public void shareCourse(Course course) {

        // Lógica para compartir un curso:
        // Le das a un botón de al lado del curso y eso te descarga el json del curso para poder enviarlo.
        // Ese json en realidad se manda por correo no dentro de la aplicaicón.
    }

    public void createCourse(Course course) {

        // Lógica para crear un curso:
        // Le das a un botón de crear curso y te lleva a una pantalla donde seleccionas el json de tu curso.
        // Una vez introducido el json, se crea el curso y se añade a la lista de cursos del usuario.
    }






}
