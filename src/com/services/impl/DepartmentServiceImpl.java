package com.services.impl;

import com.dao.Interfaces.IDepartmentDao;
import com.dao.Interfaces.ITeacherDao;
import com.model.Department;
import com.model.Teacher;
import com.services.DepartmentService;
import com.services.configs.Service;

import java.util.List;

/**
 * Created by anastasia on 04.04.18.
 */
@Service
public class DepartmentServiceImpl implements DepartmentService{

    private final IDepartmentDao departmentDao;
    private final ITeacherDao teacherDao;

    public DepartmentServiceImpl(IDepartmentDao departmentDao, ITeacherDao teacherDao) {
        this.departmentDao = departmentDao;
        this.teacherDao = teacherDao;
    }

    @Override
    public List<Department> getAll() {
        return departmentDao.findAll();
    }

    @Override
    public boolean create(Department department) {
        return departmentDao.create(department);
    }

    @Override
    public boolean update(Department department) {
        return departmentDao.update(department);
    }

    @Override
    public boolean delete(Department department) {
        return departmentDao.delete(department);
    }

    @Override
    public List<Teacher> getTeachers(Department department) {
        return teacherDao.findByDepartment(department.getDepartmentId());
    }

    @Override
    public boolean updateTeachers(Department department, List<Teacher> teachers) {


            for (Teacher teacher : teachers)
                addTeacher(department, teacher);


        return true;
    }

    @Override
    public boolean addTeacher(Department department, Teacher teacher) {
        teacher.setDepartment(department);
        return teacherDao.update(teacher);

    }

    @Override
    public Department findById(Department department){
        return departmentDao.findById(department.getDepartmentId());
    }


}
