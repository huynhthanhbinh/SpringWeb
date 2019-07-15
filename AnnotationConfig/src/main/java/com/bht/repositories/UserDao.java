package com.bht.repositories;

import com.bht.models.User;

import java.util.List;

// For accessing database
public interface UserDao {

    boolean addUser(User user);

    boolean updateUser(User user);

    boolean removeUser(int id);

    User getUserById(int id);

    List<User> getAllUsers();
}
