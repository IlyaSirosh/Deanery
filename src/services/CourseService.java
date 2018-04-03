package services;

import model.Course;
import model.Department;
import model.Lesson;
import model.Teacher;

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
