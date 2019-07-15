package com.bht.services;

import com.bht.models.User;

import java.util.List;

// Service for model User
// Such as CRUD a user
// Create, Read, Update, Delete
public interface UserService {

    int nextIdValue();

    boolean addUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(int id);

    User getUserById(int id);

    List<User> getAllUsers();
}
