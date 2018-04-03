package controllers;

import controllers.configs.Model;
import controllers.decorators.Controller;
import controllers.decorators.RequestParam;
import controllers.decorators.RequestPath;
import model.Course;
import model.Teacher;
import services.CourseService;
import services.TeacherService;

@Controller
public class TeachersController {
    private final TeacherService teacherService;

    public TeachersController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }


    @RequestPath("/showTeachers")
    public String renderView(Model m){
        m.addParam("teachers", teacherService.getAll());
        return "teachers";
    }

    @RequestPath("/saveTeacher")
    public String saveTeacher(Model m, @RequestParam("teacher") Teacher teacher){
        teacherService.create(teacher);
        m.addParam("action", "persist");
        return "teacherChanged";
    }

    @RequestPath("/updateTeacher")
    public String updateTeacher(Model m, @RequestParam("teacher") Teacher teacher){
        teacherService.update(teacher);
        m.addParam("action", "update");
        return "teacherChanged";
    }

    @RequestPath("/deleteTeacher")
    public String deleteTeacher(Model m, @RequestParam("id") int teacherId){
        teacherService.delete(new Teacher(teacherId));
        m.addParam("action", "delete");
        return "teacherChanged";
    }
}
