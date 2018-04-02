package dao.Interfaces;

import java.util.List;

/**
 * Created by PANNA on 02.04.2018.
 */
public interface IDepartmentDao {
    public List<IDepartmentDao> findAll();
    public IDepartmentDao findById(Integer departmentId);
    public boolean create(IDepartmentDao department);
    public boolean update(IDepartmentDao infoForUpdate);
}
