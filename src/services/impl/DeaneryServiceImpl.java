package services.impl;

import dao.Interfaces.IClassDao;
import dao.Interfaces.ISemesterDao;
import dao.Interfaces.IStudentDao;
import dao.Interfaces.IWeekDao;
import model.Semester;
import model.Student;
import model.Week;
import services.DeaneryService;

import java.util.List;

public class DeaneryServiceImpl implements DeaneryService {

    private ISemesterDao semesterDao;
    private IWeekDao weekDao;
    private IStudentDao studentDao;
    private IClassDao classDao;

    @Override
    public List<Semester> getAllSemesters() {
        return semesterDao.findAll();
    }

    @Override
    public boolean createSemester(Semester semester) {
        return semesterDao.create(semester);
    }

    @Override
    public boolean updateSemester(Semester semester) {
        return semesterDao.update(semester);
    }

    @Override
    public boolean deleteSemester(Semester semester) {
        //TODO delete semester
        return false;
    }

    @Override
    public List<Week> getAllWeeks() {
        return weekDao.findAll();
    }

    @Override
    public List<Week> getWeeks(Semester semester) {
        //TODO get weeks by semester
        return null;
    }

    @Override
    public boolean createWeek(Week week) {
        return weekDao.create(week);
    }

    @Override
    public boolean updateWeek(Week week) {
        return weekDao.update(weekDao);
    }

    @Override
    public boolean deleteWeek(Week week) {
        //TODO implement week delete
        return false;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDao.findAll();
    }

    @Override
    public boolean createStudent(Student student) {
        return studentDao.create(student);
    }

    @Override
    public boolean updateStudent(Student student) {
        return studentDao.update(student);
    }

    @Override
    public boolean deleteStudent(Student student) {
        //TODO implement student delete
        return false;
    }

    @Override
    public List<Class> getAllClasses() {
        return classDao.findAll();
    }

    @Override
    public boolean createClass(Class c) {
        return classDao.update(c);
    }

    @Override
    public boolean updateClass(Class c) {
        return classDao.update(c);
    }

    @Override
    public boolean deleteClass(Class c) {
        //TODO implement class delete
        return false;
    }
}
