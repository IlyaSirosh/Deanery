package services.impl;

import dao.CourseDao;
import dao.Interfaces.ICourseDao;
import dao.Interfaces.ILessonDao;
import model.Course;
import model.Department;
import model.Lesson;
import model.Teacher;
import services.CourseService;

import java.util.List;

public class CourseServiceImpl implements CourseService {

    private ICourseDao courseDao;
    private ILessonDao lessonDao;


    @Override
    public List<Course> getAllCourses() {
        return courseDao.findAll();
    }

    @Override
    public List<Course> getByDepartment(Department department) {
        return courseDao.findByDepartment(department.getDepartmentId());
    }

    @Override
    public List<Course> getByTeacher(Teacher teacher) {
        //TODO find by teacher
        return null;
    }

    @Override
    public boolean create(Course course) {
        return courseDao.create(course);
    }

    @Override
    public boolean update(Course course) {
        return courseDao.update(course);
    }

    @Override
    public boolean delete(Course course) {
        //TODO delete course
        return false;
    }

    @Override
    public List<Lesson> getLessons(Course course) {
        //TODO find lesson by course
        //return lessonDao.findLessons(course.id);
        return null;
    }
}
