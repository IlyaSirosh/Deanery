package controllers;

import controllers.configs.Model;
import controllers.decorators.Controller;
import controllers.decorators.RequestParam;
import controllers.decorators.RequestPath;
import model.Lesson;
import model.Student;
import services.DeaneryService;

@Controller
public class StudentsController {
    private final DeaneryService deaneryService;

    public StudentsController(DeaneryService deaneryService) {
        this.deaneryService = deaneryService;
    }


    @RequestPath("/showStudents")
    public String renderView(Model m){
        m.addParam("students", deaneryService.getAllStudents());
        return "students";
    }

    @RequestPath("/saveStudent")
    public String saveLesson(Model m, @RequestParam("student") Student student){
        deaneryService.createStudent(student);
        m.addParam("action", "persist");
        return "studentChanged";
    }

    @RequestPath("/updateStudent")
    public String updateLesson(Model m, @RequestParam("student") Student student){
        deaneryService.updateStudent(student);
        m.addParam("action", "update");
        return "studentChanged";
    }

    @RequestPath("/deleteStudent")
    public String deleteLesson(Model m, @RequestParam("id") int studentId){
        deaneryService.deleteStudent(new Student(studentId));
        m.addParam("action", "delete");
        return "studentChanged";
    }
}
