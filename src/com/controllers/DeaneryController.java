package com.controllers;

import com.controllers.configs.Model;
import com.controllers.decorators.Controller;
import com.controllers.decorators.RequestPath;
import com.services.DeaneryService;

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
