package dao.Interfaces;

import model.Department;

import java.util.List;

/**
 * Created by PANNA on 02.04.2018.
 */
public interface IDepartmentDao {
    public List<Department> findAll();
    public Department findById(Integer departmentId);
    public boolean create(Department department);
    public boolean update(Department infoForUpdate);
    public boolean delete(Department department);
}
