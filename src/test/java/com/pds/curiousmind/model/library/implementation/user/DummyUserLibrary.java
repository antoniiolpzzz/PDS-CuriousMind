package com.pds.curiousmind.model.library.implementation.user;

import com.pds.curiousmind.model.library.Library;
import com.pds.curiousmind.model.user.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DummyUserLibrary implements Library<User> {

    private final List<User> users = new ArrayList<>();
    private long nextId = 1L; // contador interno de IDs

    @Override
    public User add(User user) {
        User userCopy = copyUser(user); // copiamos el user
        setId(userCopy, nextId++);
        users.add(userCopy);
        return userCopy;
    }

    @Override
    public User update(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (getId(users.get(i)).equals(getId(user))) {
                users.set(i, copyUser(user));
                return users.get(i);
            }
        }
        return null;
    }

    @Override
    public boolean remove(User user) {
        return users.removeIf(u -> getId(u).equals(getId(user)));
    }

    @Override
    public List<User> getAll() {
        return Collections.unmodifiableList(users);
    }

    @Override
    public User getById(Long id) {
        return users.stream()
                .filter(u -> getId(u).equals(id))
                .findFirst()
                .orElse(null);
    }

    public User getByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    // Métodos auxiliares

    private Long getId(User user) {
        try {
            var field = User.class.getDeclaredField("id");
            field.setAccessible(true);
            return (Long) field.get(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setId(User user, Long id) {
        try {
            var field = User.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(user, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private User copyUser(User original) {
        User copy = new User();
        copy.setFullName(original.getFullName());
        copy.setUsername(original.getUsername());
        copy.setEmail(original.getEmail());
        copy.setPassword(original.getPassword());
        setId(copy, getId(original)); // si ya tiene ID
        return copy;
    }
}
