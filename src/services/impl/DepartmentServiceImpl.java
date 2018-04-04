package services.impl;

import dao.DepartmentDao;
import dao.Interfaces.IDepartmentDao;
import dao.Interfaces.ITeacherDao;
import dao.impl.JDBCDaoFactory;
import model.Department;
import model.Teacher;
import services.DepartmentService;

import java.util.List;

/**
 * Created by anastasia on 04.04.18.
 */
public class DepartmentServiceImpl implements DepartmentService{

    private static IDepartmentDao departmentDao = JDBCDaoFactory.getInstance().createDepartmentDao();

    private static ITeacherDao teacherDao = JDBCDaoFactory.getInstance().createTeacherDao();

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
