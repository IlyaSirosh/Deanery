package controllers;

import controllers.configs.MainController;
import controllers.configs.Model;
import controllers.decorators.Controller;
import controllers.decorators.RequestParam;
import controllers.decorators.RequestPath;

@Controller
public class RealController {
    @RequestPath(value = "/try", method = MainController.RequestMethods.GET)
    public String renderView(Model m,@RequestParam("testValue") String testValue){
        m.addParam("inputValue",testValue);
        return "index";
    }

    @RequestPath(value = "/anotherOne", method = MainController.RequestMethods.POST)
    public String saveSmth(@RequestParam("param1") String someString, Model m){
        System.out.println("Saving string: "+someString);
        m.addParam("saved", true);
        return "second";
    }
}
