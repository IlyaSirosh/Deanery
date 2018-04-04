package controllers;

import controllers.configs.Model;
import controllers.decorators.Controller;
import controllers.decorators.RequestParam;
import controllers.decorators.RequestPath;
import model.Lesson;
import model.Semester;
import model.Week;
import services.DeaneryService;

@Controller
public class WeekController {
    private final DeaneryService deaneryService;

    public WeekController(DeaneryService deaneryService) {
        this.deaneryService = deaneryService;
    }


    @RequestPath("/showWeeks")
    public String renderView(Model m, @RequestParam("semesterId") int semesterId){
        m.addParam("weeks", deaneryService.getWeeks(new Semester(semesterId)));
        return "weeks";
    }

    @RequestPath("/saveWeek")
    public String saveLesson(Model m, @RequestParam("week") Week week){
        deaneryService.createWeek(week);
        m.addParam("action", "persist");
        return "weekChanged";
    }

    @RequestPath("/updateWeek")
    public String updateLesson(Model m, @RequestParam("week") Week week){
        deaneryService.updateWeek(week);
        m.addParam("action", "update");
        return "weekChanged";
    }

    @RequestPath("/deleteWeek")
    public String deleteLesson(Model m, @RequestParam("id") int weekId){
        deaneryService.deleteWeek(new Week(weekId));
        m.addParam("action", "delete");
        return "weekChanged";
    }
}
