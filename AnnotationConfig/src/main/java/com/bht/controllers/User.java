package com.bht.controllers;

import com.bht.validators.UserValidator;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
public class User {
    private Logger logger = Logger.getRootLogger();

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

        com.bht.models.User user = new com.bht.models.User();

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
    public String viewUser(HttpServletRequest request,
                           @ModelAttribute("user") com.bht.models.User user,
                           @RequestParam("fileUpload") MultipartFile file,
                           BindingResult bindingResult) {

        // Using customized-validator
        // 1st argument: object to check error
        // 2nd argument: where to store errors
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            user = new com.bht.models.User();

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

        request.setAttribute("user", user);
        return "user/view";
    }
}
