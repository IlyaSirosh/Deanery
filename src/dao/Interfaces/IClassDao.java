package dao.Interfaces;

import java.util.List;

/**
 * Created by PANNA on 02.04.2018.
 */
public interface IClassDao {
    public List<Class> findAll();
    public Class findById(Integer classId);
    public boolean create(Class infoForCreate);
    public boolean update(Class infoForUpdate);
}
