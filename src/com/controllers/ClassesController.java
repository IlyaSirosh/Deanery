package com.controllers;

import com.controllers.configs.Model;
import com.controllers.decorators.Controller;
import com.controllers.decorators.RequestParam;
import com.controllers.decorators.RequestPath;
import com.model.Class;
import com.services.DeaneryService;

@Controller
public class ClassesController {
    private final DeaneryService deaneryService;

    public ClassesController(DeaneryService deaneryService) {
        this.deaneryService = deaneryService;
    }


    @RequestPath("/showClasses")
    public String renderView(Model m){
        m.addParam("classes", deaneryService.getAllClasses());
        return "classes";
    }

    @RequestPath("/saveClass")
    public String saveCourse(Model m, @RequestParam("class") Class classs){
        deaneryService.createClass(classs);
        m.addParam("action", "persist");
        return "classChanged";
    }

    @RequestPath("/updateClass")
    public String updateCourse(Model m, @RequestParam("class") Class classs){
        deaneryService.updateClass(classs);
        m.addParam("action", "update");
        return "classChanged";
    }

    @RequestPath("/deleteClass")
    public String deleteCourse(Model m, @RequestParam("id") int classId){
        deaneryService.deleteClass(new Class(classId));
        m.addParam("action", "delete");
        return "classChanged";
    }
}
