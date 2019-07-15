package com.bht.services.impl;

import com.bht.models.User;
import com.bht.repositories.UserDao;
import com.bht.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    // Autowired into new UserDaoImpl object
    // If we have more than 1 implement for UserDao Interface
    // We have to use @Qualifier( <exact impl of UserDao> )
    // For eg. Autowired UserDaoImpl userDao
    // As polymorphism of Object Oriented Programming
    // We can use interface to reference instead of implementation
    // Therefore, we can rewrite as: Autowired UserDao userDao
    // That is the best-practise, common coding style !
    @Autowired
    UserDao userDao;


    @Override
    public int nextIdValue() {
        return userDao.nextIdValue();
    }


    @Override
    public boolean addUser(User user) {
        return userDao.addUser(user);
    }


    @Override
    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }


    @Override
    public boolean removeUser(int id) {
        return userDao.removeUser(id);
    }


    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }


    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}
