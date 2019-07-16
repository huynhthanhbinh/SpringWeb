package com.bht.repositories;

// Use entity User instead of model User
// Remind: entity User != model User
//  + User Model  : for presentation layer (view)
//  + User Entity : for data access layer (repo)

import com.bht.entities.User;

import java.util.List;

// For accessing database
public interface UserDao {

    int nextIdValue();

    boolean addUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(int id);

    User getUserById(int id);

    List<User> getAllUsers();
}
