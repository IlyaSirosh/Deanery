package com.services.impl;

import com.dao.Interfaces.ICourseDao;
import com.dao.Interfaces.ILessonDao;
import com.model.Course;
import com.model.Department;
import com.model.Lesson;
import com.model.Teacher;
import com.services.CourseService;
import com.services.configs.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final ICourseDao courseDao;
    private final ILessonDao lessonDao;

    public CourseServiceImpl(ICourseDao courseDao, ILessonDao lessonDao) {
        this.courseDao = courseDao;
        this.lessonDao = lessonDao;
    }


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
