package com.controllers;

import com.controllers.configs.Model;
import com.controllers.decorators.Controller;
import com.controllers.decorators.RequestParam;
import com.controllers.decorators.RequestPath;
import com.model.Department;
import com.model.Lesson;
import com.services.LessonService;

@Controller
public class LessonsController {
    private final LessonService lessonService;

    public LessonsController(LessonService lessonService) {
        this.lessonService = lessonService;
    }


    @RequestPath("/showLessons")
    public String renderView(Model m){
        m.addParam("lessons", lessonService.getList());
        return "lessons";
    }

    @RequestPath("/saveLesson")
    public String saveLesson(Model m, @RequestParam("lesson") Lesson lesson){
        lessonService.create(lesson);
        m.addParam("action", "persist");
        return "lessonChanged";
    }

    @RequestPath("/updateLesson")
    public String updateLesson(Model m, @RequestParam("lesson") Lesson lesson){
        lessonService.update(lesson);
        m.addParam("action", "update");
        return "lessonChanged";
    }

    @RequestPath("/deleteLesson")
    public String deleteLesson(Model m, @RequestParam("id") int lessonId){
        lessonService.delete(new Lesson(lessonId));
        m.addParam("action", "delete");
        return "lessonChanged";
    }

    @RequestPath("/lessonsByFilter")
    public String byFilter(Model m, @RequestParam("department") Department department){
        m.addParam("action", "delete");
        return "lessonChanged";
    }
}
