package controllers;

import controllers.configs.Model;
import controllers.decorators.Controller;
import controllers.decorators.RequestParam;
import controllers.decorators.RequestPath;
import model.Department;
import model.Lesson;
import model.Teacher;
import services.ScheduleService;

@Controller
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @RequestPath("/showSchedule")
    public String renderView(Model m, @RequestParam("orderObj") Object orderItem){
        if(orderItem instanceof Department)
                m.addParam("schedule", scheduleService.getByDepartment((Department) orderItem));
        else if(orderItem instanceof Lesson)
                m.addParam("schedule", scheduleService.getByLesson((Lesson) orderItem));
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
