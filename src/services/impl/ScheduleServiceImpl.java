package services.impl;

import dao.Interfaces.IScheduleDao;
import dao.impl.JDBCDaoFactory;
import model.*;
import services.ScheduleService;

import java.util.List;

public class ScheduleServiceImpl implements ScheduleService {

    private static IScheduleDao scheduleDao = JDBCDaoFactory.getInstance().createScheduleDao();

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

        boolean result = scheduleDao.create(schedule);

        if(result && schedule.getLessons()!=null && !schedule.getLessons().isEmpty()){
            schedule.getLessons().stream().forEach( item -> createLessonSchedule(schedule, item));
        }

        return result;
    }

    @Override
    public boolean update(Schedule schedule) {
        return scheduleDao.update(schedule);
    }

    @Override
    public boolean delete(Schedule schedule) {

        if(schedule.getLessons()!=null && !schedule.getLessons().isEmpty()){
            schedule.getLessons().stream().forEach( item -> deleteLessonSchedule(schedule, item));
        }

        return scheduleDao.delete(schedule);
    }

    @Override
    public boolean createLessonSchedule(Schedule schedule, ScheduleUnit unit) {
        return scheduleDao.createUnit(schedule.getScheduleId(), unit.getLesson().getLessonId(), unit.getLessonClass().getClassId());
    }

    @Override
    public boolean deleteLessonSchedule(Schedule schedule, ScheduleUnit unit) {
        return scheduleDao.deleteUnit(schedule.getScheduleId(), unit.getLesson().getLessonId(), unit.getLessonClass().getClassId());
    }


}
