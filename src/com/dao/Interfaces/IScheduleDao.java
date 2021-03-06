package com.dao.Interfaces;

import com.model.*;

import java.util.List;

/**
 * Created by PANNA on 02.04.2018.
 */
public interface IScheduleDao {
    public List<Schedule> findAll();
    public Schedule findById(Integer scheduleId);
    List<ScheduleUnit> findUnitsByIdAndDepartment(Integer scheduleId, Department department);
    List<ScheduleUnit> findUnitsByIdAndLesson(Integer scheduleId, Lesson lesson);
    List<ScheduleUnit> findUnitsByIdAndTeacher(Integer scheduleId, Teacher teacher);
    public List<Schedule> getByLesson(Lesson lesson);
    public List<Schedule> getByDepartment(Department department);
    public List<Schedule> getByTeacher(Teacher teacher);
    public boolean create(Schedule schedule);
    public boolean update(Schedule infoForUpdate);
    public boolean delete(Schedule schedule);

    boolean createUnit(Integer scheduleId, Integer lessonId, Integer classId);
    boolean deleteUnit(Integer scheduleId, Integer lessonId, Integer classId);
}
