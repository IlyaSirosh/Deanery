package services.impl;

import dao.Interfaces.IClassDao;
import dao.Interfaces.ISemesterDao;
import dao.Interfaces.IStudentDao;
import dao.Interfaces.IWeekDao;
import dao.impl.JDBCDaoFactory;
import model.Semester;
import model.Student;
import model.Week;
import model.Class;
import services.DeaneryService;

import java.util.List;

public class DeaneryServiceImpl implements DeaneryService {

    private ISemesterDao semesterDao = JDBCDaoFactory.getInstance().createSemesterDao();
    private IWeekDao weekDao = JDBCDaoFactory.getInstance().createWeekDao();
    private IStudentDao studentDao = JDBCDaoFactory.getInstance().createStudentDao();
    private IClassDao classDao = JDBCDaoFactory.getInstance().createClassDao();

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

        return semesterDao.delete(semester);
    }

    @Override
    public List<Week> getAllWeeks() {

        return weekDao.findAll();
    }

    @Override
    public List<Week> getWeeks(Semester semester) {

        return weekDao.findBySemester(semester.getSemesterId());
    }

    @Override
    public boolean createWeek(Week week) {

        return weekDao.create(week);
    }

    @Override
    public boolean updateWeek(Week week) {
        return weekDao.update(week);
    }

    @Override
    public boolean deleteWeek(Week week) {

        return weekDao.delete(week);
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

        return studentDao.delete(student);
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
        return classDao.delete(c);
    }
}
