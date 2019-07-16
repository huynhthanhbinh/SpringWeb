package com.bht.services.impl;

import com.bht.models.User;
import com.bht.repositories.UserDao;
import com.bht.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
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
    public boolean addUser(User userDTO) {
        com.bht.entities.User user = new com.bht.entities.User();

        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setGender(userDTO.getGender());
        user.setHasAvatar(userDTO.getHasAvatar());

        return userDao.addUser(user);
    }


    @Override
    public boolean updateUser(User userDTO) {
        com.bht.entities.User user =
                userDao.getUserById(userDTO.getId());

        if (user != null) {

            user.setPassword(userDTO.getPassword());
            user.setEmail(userDTO.getEmail());
            user.setGender(userDTO.getGender());
            user.setHasAvatar(userDTO.getHasAvatar());

            return userDao.updateUser(user);
        }
        return false;
    }


    @Override
    public boolean deleteUser(int id) {
        com.bht.entities.User user =
                userDao.getUserById(id);

        if (user != null) {
            return userDao.deleteUser(id);
        }
        return false;
    }


    @Override
    public User getUserById(int id) {
        com.bht.entities.User user =
                userDao.getUserById(id);

        if (user != null) {
            User userDTO = new User();

            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setPassword(user.getPassword());
            userDTO.setEmail(user.getEmail());
            userDTO.setGender(user.getGender());
            userDTO.setHasAvatar(user.getHasAvatar());

            return userDTO;
        }
        return null;
    }


    @Override
    public List<User> getAllUsers() {
        List<com.bht.entities.User> users = userDao.getAllUsers();
        List<User> usersDTO = new ArrayList<>();

        for (com.bht.entities.User user : users) {
            User userDTO = new User();

            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setPassword(user.getPassword());
            userDTO.setEmail(user.getEmail());
            userDTO.setGender(user.getGender());
            userDTO.setHasAvatar(user.getHasAvatar());

            usersDTO.add(userDTO);
        }

        return usersDTO;
    }
}
