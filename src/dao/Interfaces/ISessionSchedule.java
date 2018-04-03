package dao.Interfaces;

import model.*;
import services.SessionService;

import java.util.ArrayList;
import java.util.List;

public interface ISessionSchedule {

    public ArrayList<SessionSchedule> getAllSessionScheduleByDepartment(Department department);
    public ArrayList<SessionSchedule> getAllSessionScheduleByGroup(GroupStudent groupStudent);
    public List<SessionSchedule> getAllSessionScheduleByTeacher(Teacher teacher);
    public ArrayList<SessionSchedule> getAllSessionSchedule();

    public boolean addMark(Student student,Lesson lesson,Integer grade);

}
