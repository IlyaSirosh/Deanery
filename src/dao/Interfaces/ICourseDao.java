package dao.Interfaces;

import model.Course;
import model.Lesson;

import java.util.List;

/**
 * Created by PANNA on 02.04.2018.
 */
public interface ICourseDao {
    public List<Course> findAll();
    public Course  findById(Integer courseId);
    public List<Course> findByDepartment(Integer departmentId);
    public List<Course> findByTeacher(Integer teacherId);
    public boolean create(Course course);
    public boolean update(Course infoForUpdate);
    public boolean delete(Course course);
    public List<Lesson> getLessons(Course course);
}
