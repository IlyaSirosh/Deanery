package services.impl;

import dao.Interfaces.IScheduleDao;
import model.Department;
import model.Lesson;
import model.Schedule;
import model.Teacher;
import services.ScheduleService;

import java.util.List;

public class ScheduleServiceImpl implements ScheduleService {

    private IScheduleDao scheduleDao;

    @Override
    public List<Schedule> getAll() {
        return scheduleDao.findAll();
    }

    @Override
    public List<Schedule> getByLesson(Lesson lesson) {
        return scheduleDao.getByLesson(lesson);
    }

    @Override
    public List<Schedule> getByDepartment(Department department) {
        return scheduleDao.getByDepartment(department);
    }

    @Override
    public List<Schedule> getByTeacher(Teacher teacher) {
        return scheduleDao.getByTeacher(teacher);
    }

    @Override
    public boolean create(Schedule schedule) {
        return scheduleDao.create(schedule);
    }

    @Override
    public boolean update(Schedule schedule) {
        return scheduleDao.update(schedule);
    }

    @Override
    public void print(List<Schedule> schedule) {
        //TODO
    }
}
