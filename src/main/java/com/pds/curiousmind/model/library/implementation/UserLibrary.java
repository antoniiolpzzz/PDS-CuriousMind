package com.pds.curiousmind.model.library.implementation;

import com.pds.curiousmind.model.library.Library;
import com.pds.curiousmind.model.user.User;
import java.util.ArrayList;
import java.util.List;

public enum UserLibrary implements Library<User> {
    INSTANCE;

    private final List<User> users;

    UserLibrary() {
        this.users = new ArrayList<>();
    }

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public void remove(User user) {
        users.remove(user);
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(users);
    }

    @Override
    public User getById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public User getByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}

