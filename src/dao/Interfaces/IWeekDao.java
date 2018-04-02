package dao.Interfaces;

import java.util.List;

/**
 * Created by PANNA on 02.04.2018.
 */
public interface IWeekDao {
    public List<IWeekDao> findAll();
    public IWeekDao findById(Integer weekId);
    public boolean create(IWeekDao week);
    public boolean update(IWeekDao infoForUpdate);
}
