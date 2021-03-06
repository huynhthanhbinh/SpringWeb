package com.bht.controllers;

import com.bht.models.User;
import com.bht.services.UserService;
import com.bht.validators.UserValidator;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


// Controller class User --> to decide which view
// Different from Model class User --> User Object Definition
@Controller
@RequestMapping("/user")
public class UserController {
    private Logger logger = Logger.getRootLogger();


    // UserService: add, delete, update, getOne, getAll
    @Autowired
    private UserService userService;


    // Validator using for validate model user
    @Autowired
    private UserValidator userValidator;

    // Working with form - Spring MVC
    // Auto-assign all fields of form submit
    // To object model through Spring-form binding
    //
    //      1. Create model             eg. User class
    //      2. Create controller get    eg. @GetMapping("/user/add")
    //      3. Create view matching controller view name return !
    //      4. In view, using Spring-form tag to assign / bind together
    //      5. Create controller post   eg. @PostMapping("/user/add")
    //      6. In the controller, binding the submit result to model


    // ViewResolver to return back a view name addUser !
    // URL : /bht/user/add, request method: GET
    @GetMapping("/add")
    public String addUser(HttpServletRequest request) {

        User user = new User();

        user.setId(userService.nextIdValue());

        List<String> hobbies = new ArrayList<>();
        hobbies.add("Coding");
        hobbies.add("Singing");
        hobbies.add("Swimming");
        hobbies.add("Dancing");

        request.setAttribute("user", user);
        request.setAttribute("hobbies", hobbies);

        return "user/add";
    }


    // URL : /bht/user/add, request method: POST
    @PostMapping("/add")
    public String addUser(HttpServletRequest request,
                          @ModelAttribute("user") User user,
                          @RequestParam("fileUpload") MultipartFile file,
                          BindingResult bindingResult) {

        // Using customized-validator
        // 1st argument: object to check error
        // 2nd argument: where to store errors
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            user = new User();

            user.setId(userService.nextIdValue());

            List<String> hobbies = new ArrayList<>();
            hobbies.add("Coding");
            hobbies.add("Singing");
            hobbies.add("Swimming");
            hobbies.add("Dancing");


            request.setAttribute("user", user);
            request.setAttribute("hobbies", hobbies);

            return "user/add";
        }

        // check if has avatar upload or not
        // if not, it will take the unknown.jpg for default
        if (!file.isEmpty()) {

            // Set avatar file name as format <userID>.<extension>
            // For eg. 1653006.jpg
            String fileName = user.getId() + "." +
                    FilenameUtils.getExtension(file.getOriginalFilename());


            // Create new path according to Origin file name
            String path = getClass()
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .getPath()
                    .replace("/classes/",
                            "/resources/users/")
                    + fileName;

            File newFile = new File(path);

            // Save File to web-resources (Server-side)
            // fileOutputStream.close() in finally clause !
            // Using try-with-resources for auto-close stream !
            try (FileOutputStream fileOutputStream =
                         new FileOutputStream(newFile)) {

                fileOutputStream.write(file.getBytes());

            } catch (IOException e) {
                logger.info(e);

            } finally {
                user.setHasAvatar(true);
                user.setAvatarPath(fileName);
            }
        }

        userService.addUser(user);
        return "redirect:/user/list";
    }


    // URL : /user/add, request method: POST
    // Code created means created successfully !
    // REST with POST example, @RequestBody is JSON
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody User user) {

        userService.addUser(user);
    }


    // Get List of users
    @GetMapping("/list")
    public String getListUser(HttpServletRequest request) {

        // Get UserList from UserService
        // UserService will call UserDao
        // UserDao query data from dbs
        // Then return back to UserDao then UserService
        // Assign result to new List<User>
        // Then send to View to show to client
        List<User> users = userService.getAllUsers();
        request.setAttribute("users", users);

        return "user/list";
    }


    // Get List of users by Spring REST @RequestBody @ResponseBody
    @GetMapping("/all")
    public @ResponseBody
    List<User> userList(HttpServletRequest request) {

        // Return a list of User not a view
        return userService.getAllUsers();
    }


    // View info of a specific user
    @GetMapping("/{userId}")
    public String getUser(HttpServletRequest request,
                          @PathVariable("userId") int id) {

        // Get a specific user by id
        // then assign it to request attribute
        // then request is sent to ViewSolver with logical view name
        request.setAttribute("user", userService.getUserById(id));
        return "user/view";
    }


    // Get user by ID by Spring REST @RequestBody @ResponseBody
    @GetMapping("/{userId}/info")
    public @ResponseBody
    User user(HttpServletRequest request,
              @PathVariable("userId") int id) {

        // Return a User not a view
        return userService.getUserById(id);
    }


    // delete a user
    @GetMapping("/{userId}/delete")
    public String deleteUser(HttpServletRequest request,
                             @PathVariable("userId") int id) {

        userService.deleteUser(id);
        return "redirect:/user/list";
    }


    // update a user
    @GetMapping("/{userId}/edit")
    public String updateUser(HttpServletRequest request,
                             @PathVariable("userId") int id) {

        User user = userService.getUserById(id);

        List<String> hobbies = new ArrayList<>();
        hobbies.add("Coding");
        hobbies.add("Singing");
        hobbies.add("Swimming");
        hobbies.add("Dancing");

        request.setAttribute("user", user);
        request.setAttribute("hobbies", hobbies);
        return "user/edit";
    }


    @PostMapping("/{userId}/edit")
    public String viewUpdate(HttpServletRequest request,
                             @ModelAttribute("user") User user,
                             @RequestParam("fileUpload") MultipartFile file,
                             @PathVariable("userId") int id,
                             BindingResult bindingResult) {

        user.setAcceptAgreement(true);
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            user = userService.getUserById(user.getId());

            List<String> hobbies = new ArrayList<>();
            hobbies.add("Coding");
            hobbies.add("Singing");
            hobbies.add("Swimming");
            hobbies.add("Dancing");


            request.setAttribute("user", user);
            request.setAttribute("hobbies", hobbies);

            return "user/edit";
        }

        // check if has avatar upload or not
        // if not, it will take the unknown.jpg for default
        if (!file.isEmpty()) {

            // Set avatar file name as format <userID>.<extension>
            // For eg. 1653006.jpg
            String fileName = user.getId() + "." +
                    FilenameUtils.getExtension(file.getOriginalFilename());


            // Create new path according to Origin file name
            String path = getClass()
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .getPath()
                    .replace("/classes/",
                            "/resources/users/")
                    + fileName;

            File newFile = new File(path);

            // Save File to web-resources (Server-side)
            // fileOutputStream.close() in finally clause !
            // Using try-with-resources for auto-close stream !
            try (FileOutputStream fileOutputStream =
                         new FileOutputStream(newFile)) {

                fileOutputStream.write(file.getBytes());

            } catch (IOException e) {
                logger.info(e);

            } finally {
                user.setHasAvatar(true);
                user.setAvatarPath(fileName);
            }
        }

        userService.updateUser(user);
        return "redirect:/user/list";
    }
}
