package services.impl;

import dao.Interfaces.ISessionSchedule;
import model.Department;
import model.GroupStudent;
import model.SessionSchedule;
import model.Teacher;
import services.SessionService;

import java.util.List;

public class SessionServiceImpl implements SessionService {

    private ISessionSchedule iSessionSchedule;

    @Override
    public List<SessionSchedule> getAllSessionSchedule() {
        return iSessionSchedule.getAllSessionSchedule();
    }

    @Override
    public List<SessionSchedule> getAllSessionScheduleByDepartment(Department department) {
        return iSessionSchedule.getAllSessionScheduleByDepartment(department);
    }

    @Override
    public List<SessionSchedule> getAllSessionScheduleByGroup(GroupStudent groupStudent) {
        return iSessionSchedule.getAllSessionScheduleByGroup(groupStudent);

    }

    @Override
    public List<SessionSchedule> getAllSessionScheduleByTeacher(Teacher teacher) {
        return iSessionSchedule.getAllSessionScheduleByTeacher(teacher);
    }
}
