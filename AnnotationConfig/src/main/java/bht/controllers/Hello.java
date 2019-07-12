package bht.controllers;

import bht.models.Person;
import bht.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/bht")
@Controller
public class Hello {

    @Autowired
    @Qualifier("person2")
    private Person person;


    //path is /hello
    //when user send /hello to URL
    //it invoke method sayHello
    //assign a model to this method
    //then return back a logical view name
    //view resolver will find this view name
    //which match this will be implement the view
    //finally the view is return back to the browser
    //user now can see the new view !
    @RequestMapping(value = {"/", "/hello"}, method = RequestMethod.GET) //path is /hello
    public String sayHello(ModelMap map) {
        // Set a new Map with key "msg" value "Steven Vu" (obj)
        // Assign this map to the addAttribute method
        // then return a view with logical name "hello"

        map.addAttribute("person", person);
        return "hello"; // a view name hello
    }


    // Using @RequestParam, eg. /bht/say-hello?username=Huynh Thanh Binh
    // Another way to return model and view to view resolver for consulting matching view
    @GetMapping("/say-hello")
    public ModelAndView sayHello(HttpServletRequest request,
                                 @RequestParam(name = "username", required = false,
                                         defaultValue = "abc def") String username) {

        request.setAttribute("person", person);
        request.setAttribute("username", username);
        return new ModelAndView("hello");
    }


    // Using @PathVariable, eg. /bht/hello/huynhthanhbinh
    // PathVariable is between /value/, can have multi !
    // RequestParam is at the end of path ?param=value, just one !
    @GetMapping("/hello/{username}")
    public String hello(HttpServletRequest request,
                        @PathVariable(name = "username") String username) {

        request.setAttribute("person", person);
        request.setAttribute("username", username);
        return "hello"; // viewResolver find view name hello and set model to it
    }


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
    @GetMapping("/user/add")
    public String addUser(HttpServletRequest request) {

        User user = new User();

        List<String> hobbies = new ArrayList<>();
        hobbies.add("Coding");
        hobbies.add("Singing");
        hobbies.add("Swimming");
        hobbies.add("Dancing");

        request.setAttribute("user", user);
        request.setAttribute("hobbies", hobbies);

        return "user/add";
    }


    @PostMapping("/user/add")
    public String viewUser(HttpServletRequest request,
                           @ModelAttribute("user") @Valid User user,
                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
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