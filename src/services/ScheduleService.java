package services;

import model.Department;
import model.Lesson;
import model.Schedule;
import model.Teacher;

import java.util.List;

public interface ScheduleService {

    List<Schedule> getAll();
    List<Schedule> getByLesson(Lesson group);
    List<Schedule> getByDepartment(Department department);
    List<Schedule> getByTeacher(Teacher teacher);

    boolean create(Schedule schedule);
    boolean update(Schedule schedule);
    boolean delete(Schedule schedule);

    //void print(List<Schedule> schedule);


}
