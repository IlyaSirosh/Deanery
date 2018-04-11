package com.controllers;

import com.controllers.configs.Model;
import com.controllers.decorators.Controller;
import com.controllers.decorators.RequestParam;
import com.controllers.decorators.RequestPath;
import com.model.Department;
import com.model.Lesson;
import com.model.Schedule;
import com.model.Teacher;
import com.services.ScheduleService;

@Controller
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @RequestPath("/showScheduleUnits")
    public String renderView(Model m, @RequestParam("orderObj") Object orderItem){
        if(orderItem instanceof Department)

                m.addParam("schedule", scheduleService.getByDepartment((Department) orderItem).get(0));
        else if(orderItem instanceof Lesson)
                m.addParam("schedule", scheduleService.getByLesson((Lesson) orderItem).get(0));

        else if(orderItem instanceof Teacher)
                m.addParam("schedule", scheduleService.getByTeacher((Teacher) orderItem).get(0));
        return "scheduleUnit";
    }

    @RequestPath("/showSchedule")
    public String saveSchedule(Model m){
        m.addParam("schedule", scheduleService.getAll());
        return "schedule";
    }

    @RequestPath("/saveSchedule")
    public String saveSchedule(Model m, @RequestParam("schedule") Schedule schedule){
        scheduleService.create(schedule);
        m.addParam("action", "persist");
        return "scheduleChanged";
    }

    @RequestPath("/updateSchedule")
    public String updateSchedule(Model m, @RequestParam("schedule") Schedule schedule){
        scheduleService.update(schedule);
        m.addParam("action", "update");
        return "scheduleChanged";
    }

    @RequestPath("/deleteSchedule")
    public String deleteSchedule(Model m, @RequestParam("id") int scheduleId){
        scheduleService.delete(new Schedule(scheduleId));
        m.addParam("action", "delete");
        return "scheduleChanged";
    }

    public enum Filters {
        DEPARTMENT, LESSON, TEACHER, NONE
    }
}
