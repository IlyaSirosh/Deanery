package dao.Interfaces;

import model.Course;

import java.util.List;

/**
 * Created by PANNA on 02.04.2018.
 */
public interface ICourseDao {
    public List<Course> findAll();
    public Course  findById(Integer courseId);
    public List<Course> findByDepartment(Integer id);
    public boolean create(Course course);
    public boolean update(Course infoForUpdate);
}
