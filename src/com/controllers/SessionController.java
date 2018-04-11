package com.controllers;

import com.controllers.configs.Model;
import com.controllers.decorators.Controller;
import com.controllers.decorators.RequestParam;
import com.controllers.decorators.RequestPath;
import com.model.*;
import com.services.ExamService;

@Controller
public class SessionController {
    private final ExamService examService;

    public SessionController(ExamService examService){
        this.examService = examService;
    }

    @RequestPath("/showSession")
    public String renderView(Model m, @RequestParam("orderObj") Object orderItem){
        if(orderItem instanceof Department)
            m.addParam("session", examService.getScheduleByDepartment((Department) orderItem));
        else if(orderItem instanceof Course)
            m.addParam("session", examService.getScheduleByCourse((Course) orderItem));
        else if(orderItem instanceof Teacher)
            m.addParam("session", examService.getScheduleByTeacher((Teacher) orderItem));
        else return "sessionIndex";
        return "sessionSchedule";
    }

    @RequestPath("/showResults")
    public String renderResults(Model m, @RequestParam("orderObj") Object orderItem){
        if(orderItem instanceof Department)
            m.addParam("session", examService.getStudentResults((Department) orderItem));
        else if(orderItem instanceof Course)
            m.addParam("session", examService.getStudentResults((Lesson) orderItem));
        else if(orderItem instanceof Teacher)
            m.addParam("session", examService.getStudentResults((Teacher) orderItem));
        else return "sessionResultIndex";
        return "sessionResult";
    }

    @RequestPath("/saveSession")
    public String saveSchedule(Model m, @RequestParam("schedule") GroupExam exam){
        examService.createGroupExam(exam);
        m.addParam("action", "persist");
        return "sessionChanged";
    }

//    @RequestPath("/deleteSession")
//    public String saveSchedule(Model m, @RequestParam("id") int id){
//        examService.deleteGroupExam(new GroupExam(id));
//        m.addParam("action", "delete");
//        return "sessionChanged";
//    }

    @RequestPath("/updateResult")
    public String saveSchedule(Model m, @RequestParam("exam") GroupStudent exam){
        examService.updateStudentResult(exam);
        m.addParam("action", "updated");
        return "resultChanged";
    }

}
