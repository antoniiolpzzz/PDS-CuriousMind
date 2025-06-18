package com.pds.curiousmind.model.library.implementation.user;

import com.pds.curiousmind.model.library.Library;
import com.pds.curiousmind.model.user.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DummyUserLibrary implements Library<User> {

    private final List<User> users = new ArrayList<>();

    @Override
    public User add(User user) {
        users.add(user);
        return user;
    }

    @Override
    public User update(User user) {
        int index = users.indexOf(user);
        if (index >= 0) {
            users.set(index, user);
            return user;
        }
        return null;
    }

    @Override
    public boolean remove(User user) {
        return users.remove(user);
    }

    @Override
    public List<User> getAll() {
        return Collections.unmodifiableList(users);
    }

    @Override
    public User getById(Long id) {
        // Como User no tiene id en test → devolvemos null (o puedes usar username como id)
        return null;
    }

    public User getByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}
