package com.pds.curiousmind.model.library.implementation;

import com.pds.curiousmind.model.library.Library;
import com.pds.curiousmind.model.user.User;
import com.pds.curiousmind.persistence.adapter.interfaces.IUserAdapter;
import com.pds.curiousmind.persistence.provider.AdapterProvider;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public enum UserLibrary implements Library<User> {
    INSTANCE;

    private final IUserAdapter userAdapter;
    private final List<User> users;

    UserLibrary() {
        this.userAdapter = AdapterProvider.INSTANCE().getUserAdapter();
        this.users = new CopyOnWriteArrayList<>(userAdapter.findAll());
    }

    public void reload() {
        users.clear();
        users.addAll(userAdapter.findAll());
    }

    @Override
    public User add(User user) {
        User saved = userAdapter.save(user);
        users.add(saved);
        return saved;
    }

    @Override
    public User update(User user) {
        User updated = userAdapter.update(user);
        if (updated != null) {
            users.replaceAll(u -> u.getId().equals(updated.getId()) ? updated : u);
        }
        return updated;
    }

    @Override
    public boolean remove(User user) {
        boolean deleted = userAdapter.delete(user);
        if (deleted) {
            users.remove(user);
        }
        return deleted;
    }

    @Override
    public List<User> getAll() {
        return Collections.unmodifiableList(users);
    }

    @Override
    public User getById(Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public User getByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}
