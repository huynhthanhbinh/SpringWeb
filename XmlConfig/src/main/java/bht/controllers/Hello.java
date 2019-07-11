package bht.controllers;

import bht.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Hello {

    @Autowired
    @Qualifier("person")
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
}