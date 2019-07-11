package bht.controllers;

import bht.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/bht")
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
}