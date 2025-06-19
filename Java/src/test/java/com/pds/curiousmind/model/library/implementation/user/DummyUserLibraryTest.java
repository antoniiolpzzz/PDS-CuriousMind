package com.pds.curiousmind.model.library.implementation.user;

import com.pds.curiousmind.model.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DummyUserLibraryTest {

    private DummyUserLibrary userLibrary;

    @BeforeEach
    void setUp() {
        userLibrary = new DummyUserLibrary();
    }

    @Test
    void testAddUser() {
        User user = new User("Ana López", "ana@example.com", "pass123", "ana123");

        User saved = userLibrary.add(user);

        assertEquals(1, userLibrary.getAll().size());
        assertEquals("Ana López", saved.getFullName());
        assertEquals("ana123", saved.getUsername());
    }

    @Test
    void testUpdateUser() {
        User user = new User("Ana López", "ana@example.com", "pass123", "ana123");
        User saved = userLibrary.add(user);

        saved.setFullName("Ana Modificada");
        User updated = userLibrary.update(saved);

        assertNotNull(updated);
        assertEquals("Ana Modificada", updated.getFullName());
    }

    @Test
    void testRemoveUser() {
        User user = new User("Ana López", "ana@example.com", "pass123", "ana123");
        userLibrary.add(user);

        assertEquals(1, userLibrary.getAll().size());

        boolean removed = userLibrary.remove(user);

        assertTrue(removed);
        assertEquals(0, userLibrary.getAll().size());
    }

    @Test
    void testGetByUsername() {
        User user = new User("Ana López", "ana@example.com", "pass123", "ana123");
        userLibrary.add(user);

        User found = userLibrary.getByUsername("ana123");

        assertNotNull(found);
        assertEquals("ana123", found.getUsername());
    }
}
