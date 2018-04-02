package services;

import model.Department;
import model.Lesson;
import model.Schedule;
import model.Teacher;

import java.util.List;

public interface ScheduleService {

    List<Schedule> getAdd();
    List<Schedule> getByLesson(Lesson group);
    List<Schedule> getByDepartment(Department department);
    List<Schedule> getByTeacher(Teacher teacher);

    boolean cread(Schedule schedule);
    boolean update(Schedule schedule);

    void print(List<Schedule> schedule);


}
