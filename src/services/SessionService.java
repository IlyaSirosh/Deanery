package services;

import model.Department;
import model.GroupStudent;
import model.SessionSchedule;
import model.Teacher;

import java.util.List;

public interface SessionService {



    List<SessionSchedule> getAllSessionSchedule();
    List<SessionSchedule> getAllSessionScheduleByDepartment(Department department);
    List<SessionSchedule> getAllSessionScheduleByGroup(GroupStudent groupStudent);
    List<SessionSchedule> getAllSessionScheduleByTeacher(Teacher teacher);

}
