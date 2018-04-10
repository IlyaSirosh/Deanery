package com.controllers;

import com.controllers.configs.Model;
import com.controllers.decorators.Controller;
import com.controllers.decorators.RequestParam;
import com.controllers.decorators.RequestPath;

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
