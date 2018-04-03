package dao.Interfaces;

import model.Semester;

import java.util.List;

/**
 * Created by PANNA on 02.04.2018.
 */
public interface ISemesterDao {
    public List<Semester> findAll();
    public Semester findById(Integer semesterId);
    public boolean create(Semester semester);
    public boolean update(Semester infoForUpdate);
    public boolean delete(Semester semester);
}
