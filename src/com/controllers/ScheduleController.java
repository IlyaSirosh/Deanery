package com.controllers;

import com.controllers.configs.Model;
import com.controllers.decorators.Controller;
import com.controllers.decorators.RequestParam;
import com.controllers.decorators.RequestPath;
import com.model.Department;
import com.model.Lesson;
import com.model.Teacher;
import com.services.ScheduleService;

@Controller
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @RequestPath("/showSchedule")
    public String renderView(Model m, @RequestParam("orderObj") Object orderItem){
        if(orderItem instanceof Department)
                m.addParam("schedule", scheduleService.getByDepartment((Department) orderItem).get(0).getLessons());
        else if(orderItem instanceof Lesson)
                m.addParam("schedule", scheduleService.getByLesson((Lesson) orderItem).get(0).getLessons());
        else if(orderItem instanceof Teacher)
                m.addParam("schedule", scheduleService.getByTeacher((Teacher) orderItem));
        else
                m.addParam("schedule", scheduleService.getAll());
        return "schedule";
    }

    public enum Filters {
        DEPARTMENT, LESSON, TEACHER, NONE
    }
}
