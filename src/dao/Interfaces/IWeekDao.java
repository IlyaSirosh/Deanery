package dao.Interfaces;

import model.Week;

import java.util.List;

/**
 * Created by PANNA on 02.04.2018.
 */
public interface IWeekDao {
    public List<Week> findAll();
    public Week findById(Integer weekId);
    public boolean create(Week week);
    public boolean update(Week infoForUpdate);
    public boolean delete(Week week);
}
