<<<<<<< HEAD
package controllers;

import controllers.decorators.Controller;

@Controller
public class CoursesController {
}
=======
package controllers;

import controllers.configs.Model;
import controllers.decorators.Controller;
import controllers.decorators.RequestParam;
import controllers.decorators.RequestPath;
import model.Course;
import services.CourseService;

@Controller
public class CoursesController {
    private final CourseService courseService;

    public CoursesController(CourseService courseService) {
        this.courseService = courseService;
    }


    @RequestPath("/showCourses")
    public String renderView(Model m){
        m.addParam("courses", courseService.getAllCourses());
        return "courses";
    }

    @RequestPath("/saveCourse")
    public String saveCourse(Model m, @RequestParam("course") Course course){
        courseService.create(course);
        m.addParam("action", "persist");
        return "courseChanged";
    }

    @RequestPath("/updateCourse")
    public String updateCourse(Model m, @RequestParam("course") Course course){
        courseService.update(course);
        m.addParam("action", "update");
        return "courseChanged";
    }

    @RequestPath("/deleteCourse")
    public String deleteCourse(Model m, @RequestParam("id") int courseId){
        courseService.delete(new Course(courseId));
        m.addParam("action", "delete");
        return "courseChanged";
    }
}
>>>>>>> ui
