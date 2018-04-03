package dao.Interfaces;

import java.util.List;

/**
 * Created by PANNA on 02.04.2018.
 */
public interface ISemesterDao {
    public List<ISemesterDao> findAll();
    public ISemesterDao findById(Integer semesterId);
    public boolean create(ISemesterDao semester);
    public boolean update(ISemesterDao infoForUpdate);
}
