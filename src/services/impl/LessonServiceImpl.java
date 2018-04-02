package services.impl;

import dao.Interfaces.ILessonDao;
import dao.Interfaces.IStudentDao;
import model.*;
import model.enums.Day;
import services.LessonService;

import java.util.List;
import java.util.stream.Collectors;

public class LessonServiceImpl implements LessonService {

    private ILessonDao lessonDao;
    private IStudentDao studentDao;


    @Override
    public boolean create(Lesson lesson) {
        return lessonDao.create(lesson);
    }

    @Override
    public boolean update(Lesson lesson) {
        return lessonDao.update(lesson);
    }

    @Override
    public boolean delete(Lesson lesson) {
        return false;
    }

    @Override
    public List<Lesson> getList() {
        return lessonDao.findAll();
    }

    @Override
    public List<Lesson> getListBy(Department department) {
        return getListBy(department, null, null, null);
    }

    @Override
    public List<Lesson> getListBy(Department department, Course course) {
        return getListBy(department, course, null, null);
    }

    @Override
    public List<Lesson> getListBy(Department department, Course course, Teacher teacher) {
        return getListBy(department, course, teacher, null);
    }

    @Override
    public List<Lesson> getListBy(Department department, Course course, Teacher teacher, Semester semester) {
        return lessonDao.findAll().stream().filter(lesson ->   (department == null || lesson.getTeacher().getDepartment().equals(department)) &&
                (course == null || lesson.getCourse().equals(course)) &&
                (teacher == null || lesson.getTeacher().equals(teacher)) &&
                (semester == null || lesson.getSemester().equals(semester))

        ).collect(Collectors.toList());
    }


    @Override
    public List<Lesson> getListBy(Department department, Course course, Teacher teacher, Semester semester, Week week) {
        return getListBy(department, course, teacher, semester, week, null, null);
    }

    @Override
    public List<Lesson> getListBy(Department department, Course course, Teacher teacher, Semester semester, Week week, Day day) {
        return getListBy(department, course, teacher, semester, week, day, null);
    }

    @Override
    public List<Lesson> getListBy(Department department, Course course, Teacher teacher, Semester semester, Week week, Day day, Integer lessonNumber) {
        return getListBy(week, day, lessonNumber).stream().filter(lesson ->
                    (department == null || lesson.getTeacher().getDepartment().equals(department)) &&
                    (course == null || lesson.getCourse().equals(course)) &&
                    (teacher == null || lesson.getTeacher().equals(teacher)) &&
                    (semester == null || lesson.getSemester().equals(semester))

        ).collect(Collectors.toList());
    }

    private List<Lesson> getListBy(Week week, Day day, Integer lessonNumber){
        //TODO find lessons by week, day, lessonNumber - all params are optional
        return null;
    }

    @Override
    public List<Student> getGroup(Lesson lesson) {
        return studentDao.findStudentByLesson(lesson.getLessonId());
    }

    @Override
    public boolean addToGroup(Lesson lesson, Student student) {
        //TODO add student to group
        return false;
    }

    @Override
    public boolean deleteFromGroup(Lesson lesson, Student student) {
        //TODO delete student from group
        return false;
    }

    @Override
    public boolean addGroup(Lesson lesson, List<Student> group) {
        //TODO add group of students
        return false;
    }

    @Override
    public boolean updateGroup(Lesson lesson, List<Student> group) {
        //TODO update group of students - add new, delete redundant
        return false;
    }
}
