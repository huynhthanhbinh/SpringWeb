package com.bht.services.impl;

import com.bht.repositories.UserDao;
import com.bht.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
