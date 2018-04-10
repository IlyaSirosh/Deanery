package com.services;

import com.model.Course;
import com.model.Department;
import com.model.Lesson;
import com.model.Teacher;

import java.util.List;

public interface CourseService {

    List<Course> getAllCourses();
    List<Course> getByDepartment(Department department);
    List<Course> getByTeacher(Teacher teacher);

    boolean create(Course course);
    boolean update(Course course);
    boolean delete(Course course);

    List<Lesson> getLessons(Course course);
}
