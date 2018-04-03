package dao.Interfaces;

import java.util.List;

/**
 * Created by PANNA on 02.04.2018.
 */
public interface ITeacherDao {
    public List<ITeacherDao> findAll();
    public ITeacherDao findById(Integer teacherId);
    public ITeacherDao findByDepartment(Integer departmentId);
    public boolean create(ITeacherDao teacher);
    public boolean update(ITeacherDao infoForUpdate);
}
