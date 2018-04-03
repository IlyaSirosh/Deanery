package services;

import model.*;

import java.util.List;

public interface ScheduleService {

    List<Schedule> getAll();
    List<Schedule> getByLesson(Lesson group);
    List<Schedule> getByDepartment(Department department);
    List<Schedule> getByTeacher(Teacher teacher);

    boolean create(Schedule schedule);
    boolean update(Schedule schedule);
    boolean delete(Schedule schedule);

    boolean createLessonSchedule(Schedule schedule, ScheduleUnit unit);
    boolean deleteLessonSchedule(Schedule schedule, ScheduleUnit unit);

}
