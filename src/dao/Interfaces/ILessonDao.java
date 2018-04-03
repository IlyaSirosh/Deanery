package dao.Interfaces;

import model.Lesson;
import model.Student;

import java.util.List;

/**
 * Created by PANNA on 02.04.2018.
 */
public interface ILessonDao {
    public List<Lesson> findAll();
    public Lesson findById(Integer lessonId);
    public List<Lesson> findByWeek(Integer weekId);
    public List<Lesson> findByDay(Integer dayId);
    public List<Lesson> findyByLessonNumber(Integer lessonNumber);
    public List<Lesson> findByWeekAndDay(Integer weekId, Integer dayId);
    public List<Lesson> findByWeekAndLessonNumber(Integer weekId, Integer lessonNumber);
    public List<Lesson> findByDayAndLessonNumber(Integer dayId, Integer lessonNumber);
    public List<Lesson> findByWeekDayAndLessonNumber(Integer weekId, Integer dayId, Integer lessonNumber);

    public List<Student> getGroup(Lesson lesson);
    public boolean addToGroup(Lesson lesson, Student student);
    public boolean deleteFromGroup(Lesson lesson, Student student);
    public boolean addGroup(Lesson lesson, List<Student> group);
    public boolean updateGroup(Lesson lesson, List<Student> group);

    public boolean create(Lesson lesson);
    public boolean update(Lesson infoForUpdate);
    public boolean delete(Lesson lesson);

}
