package controllers;

import controllers.configs.Model;
import controllers.decorators.Controller;
import controllers.decorators.RequestPath;
import services.DeaneryService;

@Controller
public class DeaneryController {
    private final DeaneryService deaneryService;

    public DeaneryController(DeaneryService deaneryService) {
        this.deaneryService = deaneryService;
    }

    @RequestPath("/showSemesters")
    public String showSemesters(Model m){
        m.addParam("semesters", deaneryService.getAllSemesters());
        return "semesters";
    }
}
