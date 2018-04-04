package services;

import model.Department;
import model.Teacher;

import java.util.List;

public interface DepartmentService {

    List<Department> getAll();
    boolean create(Department department);
    boolean update(Department department);
    boolean delete(Department department);

    List<Teacher> getTeachers(Department department);
    boolean updateTeachers(Department department, List<Teacher> teachers);

    boolean addTeacher(Department department, Teacher teacher);

}
