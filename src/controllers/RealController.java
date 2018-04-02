package controllers;

import controllers.configs.MainController;
import controllers.configs.Model;
import controllers.decorators.Controller;
import controllers.decorators.RequestParam;
import controllers.decorators.RequestPath;

@Controller
public class RealController {
    @RequestPath(value = "/try")
    public String renderView(Model m,@RequestParam("testValue") String testValue){
        m.addParam("inputValue",testValue);
        return "index";
    }

    @RequestPath(value = "/anotherOne")
    public String saveSmth(@RequestParam("param1") String someString, Model m){
        m.addParam("saved", someString);
        return "second";
    }
}
