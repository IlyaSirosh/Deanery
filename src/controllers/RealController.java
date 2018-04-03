package controllers;

import controllers.configs.Model;
import controllers.decorators.Controller;
import controllers.decorators.RequestParam;
import controllers.decorators.RequestPath;

@Controller
public class RealController {
    @RequestPath(value = "/index")
    public String renderView(Model m){
        return "index";
    }

    @RequestPath(value = "/courses")
    public String courses(){
        return "courses";
    }

    @RequestPath(value = "/anotherOne")
    public String saveSmth(@RequestParam("param1") String someString, Model m){
        m.addParam("saved", someString);
        m.addParam("testValue", "redirected");
        return "redirect:/try";
    }
}
