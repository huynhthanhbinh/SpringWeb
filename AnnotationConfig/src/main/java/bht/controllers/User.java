package bht.controllers;

import bht.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


// Controller class User --> to decide which view
// Different from Model class User --> User Object Definition
@RequestMapping("/bht/user")
@Controller
public class User {

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

        bht.models.User user = new bht.models.User();

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
                           @ModelAttribute("user") bht.models.User user,
                           BindingResult bindingResult) {

        // Using customized-validator
        // 1st argument: object to check error
        // 2nd argument: where to store errors
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            user = new bht.models.User();

            List<String> hobbies = new ArrayList<>();
            hobbies.add("Coding");
            hobbies.add("Singing");
            hobbies.add("Swimming");
            hobbies.add("Dancing");


            request.setAttribute("user", user);
            request.setAttribute("hobbies", hobbies);

            return "user/add";
        }

        request.setAttribute("user", user);
        return "user/view";
    }
}
