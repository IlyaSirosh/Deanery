package com.services.impl;

import com.dao.Interfaces.ITeacherDao;
import com.model.Teacher;
import com.services.TeacherService;
import com.services.configs.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final ITeacherDao teacherDao;

    public TeacherServiceImpl(ITeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

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
