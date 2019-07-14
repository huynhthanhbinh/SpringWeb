package com.bht.controllers;

import com.bht.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/hello")
public class Hello {

    private static final String helloView = "hello/view";

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
    @GetMapping(value = {"", "/"}) //path is /hello
    public String sayHello(ModelMap map) {
        // Set a new Map with key "msg" value "Steven Vu" (obj)
        // Assign this map to the addAttribute method
        // then return a view with logical name "hello"

        map.addAttribute("person", person);
        return helloView; // a view name hello
    }


    // Using @RequestParam, eg. /say?username=Huynh Thanh Binh
    // Another way to return model and view to view resolver for consulting matching view
    @GetMapping("/say")
    public ModelAndView sayHello(HttpServletRequest request,
                                 @RequestParam(name = "username", required = false,
                                         defaultValue = "abc def") String username) {

        request.setAttribute("person", person);
        request.setAttribute("username", username);
        return new ModelAndView(helloView);
    }


    // Using @PathVariable, eg. /bht/hello/huynhthanhbinh
    // PathVariable is between /value/, can have multi !
    // RequestParam is at the end of path ?param=value, just one !
    @GetMapping("/{username}")
    public String hello(HttpServletRequest request,
                        @PathVariable(name = "username") String username) {

        request.setAttribute("person", person);
        request.setAttribute("username", username);
        return helloView; // viewResolver find view name hello and set model to it
    }
}