package services.impl;

import dao.Interfaces.ITeacherDao;
import dao.TeacherDao;
import dao.impl.JDBCDaoFactory;
import model.Teacher;
import services.TeacherService;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {

    private static ITeacherDao teacherDao = JDBCDaoFactory.getInstance().createTeacherDao();

    @Override
    public List<Teacher> getAll() {
        return teacherDao.findAll();
    }

    @Override
    public boolean create(Teacher teacher) {
        return teacherDao.create(teacher);
    }

    @Override
    public boolean update(Teacher teacher) {
        return teacherDao.update(teacher);
    }

    @Override
    public boolean delete(Teacher teacher) {
        return teacherDao.delete(teacher);
    }
}
