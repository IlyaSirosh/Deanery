package dao.Interfaces;

import model.Teacher;

import java.util.List;

/**
 * Created by PANNA on 02.04.2018.
 */
public interface ITeacherDao {
    public List<Teacher> findAll();
    public Teacher findById(Integer teacherId);
    public List<Teacher> findByDepartment(Integer departmentId);
    public boolean create(Teacher teacher);
    public boolean update(Teacher infoForUpdate);
    public boolean delete(Teacher teacher);
}
