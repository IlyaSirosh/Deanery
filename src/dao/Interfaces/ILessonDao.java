package dao.Interfaces;

import model.Lesson;

import java.util.List;

/**
 * Created by PANNA on 02.04.2018.
 */
public interface ILessonDao {
    public List<Lesson> findAll();
    public Lesson findById(Integer lessonId);
    public List<Lesson> findByDepartmentId(Integer departmentId);
    public List<Lesson> findByTeacherId(Integer teacherId);
    public List<Lesson> findByCourseId(Integer courseId);
    public List<Lesson> findBySemesterId(Integer semesterId);
    public List<Lesson> findByWeekId(Integer weekId);
    public boolean create(Lesson lesson);
    public boolean update(Lesson infoForUpdate);
    public boolean delete(Lesson lesson);

}
