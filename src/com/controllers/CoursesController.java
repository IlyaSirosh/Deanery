package com.controllers;

import com.controllers.configs.Model;
import com.controllers.decorators.Controller;
import com.controllers.decorators.RequestParam;
import com.controllers.decorators.RequestPath;
import com.model.Course;
import com.services.CourseService;

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

    @RequestPath("/showCourse")
    public String showCourse(Model m, @RequestParam("id") int id, @RequestParam("name") String name){
        Course course = new Course();
        course.setCourseId(id);
        course.setName(name);
        m.addParam("course", course);
        return "course";
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
