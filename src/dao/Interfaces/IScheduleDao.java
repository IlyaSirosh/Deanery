package dao.Interfaces;

import model.Department;
import model.Lesson;
import model.Schedule;
import model.Teacher;

import java.util.List;

/**
 * Created by PANNA on 02.04.2018.
 */
public interface IScheduleDao {
    public List<Schedule> findAll();
    public Schedule findById(Integer scheduleId);
    public List<Schedule> getByLesson(Lesson lesson);
    public List<Schedule> getByDepartment(Department department);
    public List<Schedule> getByTeacher(Teacher teacher);
    public boolean create(Schedule schedule);
    public boolean update(Schedule infoForUpdate);
}
