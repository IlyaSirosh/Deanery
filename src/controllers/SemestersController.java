package controllers;

import controllers.configs.Model;
import controllers.decorators.Controller;
import controllers.decorators.RequestParam;
import controllers.decorators.RequestPath;
import model.Lesson;
import model.Semester;
import services.DeaneryService;

@Controller
public class SemestersController {
    private final DeaneryService deaneryService;

    public SemestersController(DeaneryService deaneryService) {
        this.deaneryService = deaneryService;
    }


    @RequestPath("/showSemesters")
    public String renderView(Model m){
        m.addParam("semesters", deaneryService.getAllSemesters());
        return "semesters";
    }

    @RequestPath("/saveSemester")
    public String saveLesson(Model m, @RequestParam("semester") Semester semester){
        deaneryService.createSemester(semester);
        m.addParam("action", "persist");
        return "semesterChanged";
    }

    @RequestPath("/updateSemester")
    public String updateLesson(Model m, @RequestParam("semester") Semester semester){
        deaneryService.updateSemester(semester);
        m.addParam("action", "update");
        return "semesterChanged";
    }

    @RequestPath("/deleteSemester")
    public String deleteSemester(Model m, @RequestParam("id") int semesterId){
        deaneryService.deleteSemester(new Semester(semesterId));
        m.addParam("action", "delete");
        return "semesterChanged";
    }
}
