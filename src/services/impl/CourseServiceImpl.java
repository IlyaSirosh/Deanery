package services.impl;

import dao.CourseDao;
import dao.Interfaces.ICourseDao;
import dao.Interfaces.ILessonDao;
import dao.impl.JDBCDaoFactory;
import model.Course;
import model.Department;
import model.Lesson;
import model.Teacher;
import services.CourseService;

import java.util.List;
import java.util.stream.Collectors;

public class CourseServiceImpl implements CourseService {

    private static ICourseDao courseDao = JDBCDaoFactory.getInstance().createCourseDao();
    private static ILessonDao lessonDao = JDBCDaoFactory.getInstance().createLessonDao();


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

        return courseDao.findByTeacher(teacher.getTeacherId());
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

        return courseDao.delete(course);
    }

    @Override
    public List<Lesson> getLessons(Course course) {
        return lessonDao.getLessons(course);
    }
}
