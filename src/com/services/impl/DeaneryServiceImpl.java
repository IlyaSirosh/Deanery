package com.services.impl;

import com.dao.Interfaces.IClassDao;
import com.dao.Interfaces.ISemesterDao;
import com.dao.Interfaces.IStudentDao;
import com.dao.Interfaces.IWeekDao;
import com.model.Semester;
import com.model.Student;
import com.model.Week;
import com.model.Class;
import com.services.DeaneryService;
import com.services.configs.Service;

import java.util.List;

@Service
public class DeaneryServiceImpl implements DeaneryService {

    private final ISemesterDao semesterDao;
    private final IWeekDao weekDao;
    private final IStudentDao studentDao;
    private final IClassDao classDao;

    public DeaneryServiceImpl(ISemesterDao semesterDao, IWeekDao weekDao, IStudentDao studentDao, IClassDao classDao) {
        this.semesterDao = semesterDao;
        this.weekDao = weekDao;
        this.studentDao = studentDao;
        this.classDao = classDao;
    }

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
        return classDao.create(c);
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
