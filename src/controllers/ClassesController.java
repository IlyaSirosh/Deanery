package controllers;

import controllers.configs.Model;
import controllers.decorators.Controller;
import controllers.decorators.RequestParam;
import controllers.decorators.RequestPath;
import model.Class;
import model.Course;
import services.CourseService;
import services.DeaneryService;

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
