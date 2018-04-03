package services.impl;

import dao.Interfaces.IDepartmentDao;
import model.Department;
import model.Teacher;
import services.DepartmentService;

import java.util.List;

public class DepartmentServiceImp implements DepartmentService {

    private IDepartmentDao iDepartmentDao;

    @Override
    public List<Department> getAll() {
        return iDepartmentDao.findAll();
    }

    @Override
    public boolean create(Department department) {
       if(iDepartmentDao.create(department))
           return true;
       return false;
    }

    @Override
    public boolean update(Department department) {
        return iDepartmentDao.update(department);
    }

    @Override
    public boolean delete(Department department) {
        return iDepartmentDao.delete(department);
    }

    @Override
    public boolean getTeachers(Department department) {
        //get teacher by department
        return false;
    }

    @Override
    public boolean updateTeachers(Department department, List<Teacher> teachers) {
        return false;
    }

    @Override
    public boolean addTeacher(Department department, Teacher teacher) {
        return false;
    }

    @Override
    public boolean deleteTeacher(Department department, Teacher teacher) {
        return false;
    }
}
