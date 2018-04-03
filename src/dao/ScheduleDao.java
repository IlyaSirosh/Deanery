package dao;

import dao.Interfaces.IScheduleDao;
import dao.impl.EntityRetriever;
import model.*;
import model.enums.Day;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PANNA on 03.04.2018.
 */
public class ScheduleDao implements IScheduleDao {
    private static final String SELECT_ALL = "SELECT * FROM schedule";
    private static final String SELECT_BY_ID = "SELECT * FROM schedule WHERE schedule_id = ?";
    private static final String SELECT_BY_LESSON ="SELECT * FROM schedule WHERE schedule_id IN (SELECT schedule_id FROM lesson_has_schedule WHERE lesson_id=?";
    private static final String SELECT_BY_DEPARTMENT =" SELECT * FROM schedule WHERE schedule_id IN (SELECT schedule_id FROM lesson_has_schedule WHERE lesson_id IN (SELECT lesson_id FROM lesson WHERE course_ID IN (SELECT course_id FROM course WHERE department_id=?)))";
    private static final String SELECT_BY_TEACHER = "SELECT * FROM schedule WHERE schedule_id IN (SELECT schedule_id FROM lesson_has_schedule WHERE lesson_id IN (SELECT lesson_id FROM lesson WHERE teacher_id=?))";
    private static final String CREATE = "INSERT INTO schedule ( day, lesson_number, week_id)\n" +
            "VALUES (?,?,?);";
    private static final String UPDATE = "UPDATE schedule SET " +
            "day = ?"+
            "lesson_number = ? ," +
            "week_id = ? "+
            "WHERE schedule_id = ?";
    private static final String DELETE = "DELETE FROM schedule WHERE schedule_id=?";
    private static final String SELECT_DEPARTMENT_SCHEDULE_UNITS = "SELECT lesson_id, class_id FROM lesson_has_schedule WHERE lesson_id IN (SELECT lesson_id FROM lesson WHERE course_ID IN (SELECT course_id FROM course WHERE department_id=?))))";
    private static final String SELECT_LESSON_SCHEDULE_UNITS = "SELECT lesson_id, class_id FROM lesson_has_schedule WHERE lesson_id=?";
    private static final String SELECT_TEACHER_SCHEDULE_UNITS = "SELECT lesson_id, class_id FROM lesson_has_schedule WHERE lesson_id IN (SELECT lesson_id FROM lesson WHERE teacher_id=?))";
    private Connection connection;
    public ScheduleDao(Connection connection) {
        this.connection = connection;
    }
    @Override
    public List<Schedule> findAll() {
        List<Schedule> allSchedule = new ArrayList<>();
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_ALL)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                allSchedule.add(EntityRetriever.retrieveSchedule(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allSchedule;    }

    @Override
    public Schedule findById(Integer scheduleId) {
        Schedule schedule = null;
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, scheduleId);
            ResultSet rs = statement.executeQuery();
            schedule = EntityRetriever.retrieveSchedule(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedule;    }

    @Override
    public List<ScheduleUnit> findUnitsByIdAndDepartment(Integer scheduleId, Department department) {
        List<ScheduleUnit> units = new ArrayList<>();
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_DEPARTMENT_SCHEDULE_UNITS)) {
            statement.setInt(1, department.getDepartmentId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                units.add(EntityRetriever.retrieveScheduleUnit(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return units;
    }

    @Override
    public List<ScheduleUnit> findUnitsByIdAndLesson(Integer scheduleId, Lesson lesson) {
        List<ScheduleUnit> units = new ArrayList<>();
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_LESSON_SCHEDULE_UNITS)) {
            statement.setInt(1, lesson.getLessonId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                units.add(EntityRetriever.retrieveScheduleUnit(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return units;
    }

    @Override
    public List<ScheduleUnit> findUnitsByIdAndTeacher(Integer scheduleId, Teacher teacher) {
        List<ScheduleUnit> units = new ArrayList<>();
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_TEACHER_SCHEDULE_UNITS)) {
            statement.setInt(1, teacher.getTeacherId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                units.add(EntityRetriever.retrieveScheduleUnit(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return units;
    }


    @Override
    public List<Schedule> getByLesson(Lesson lesson) {
        List<Schedule> allSchedule = new ArrayList<>();
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_BY_LESSON)) {
            statement.setInt(1, lesson.getLessonId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                allSchedule.add(EntityRetriever.retrieveLessonSchedule(rs,lesson));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allSchedule;    }

    @Override
    public List<Schedule> getByDepartment(Department department) {
        List<Schedule> allSchedule = new ArrayList<>();
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_BY_DEPARTMENT)) {
            statement.setInt(1, department.getDepartmentId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                allSchedule.add(EntityRetriever.retrieveDepartmentSchedule(rs,department));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allSchedule;        }

    @Override
    public List<Schedule> getByTeacher(Teacher teacher) {
        List<Schedule> allSchedule = new ArrayList<>();
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_BY_TEACHER)) {
            statement.setInt(1, teacher.getTeacherId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                allSchedule.add(EntityRetriever.retrieveTeacherSchedule(rs,teacher));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allSchedule;        }

    @Override
    public boolean create(Schedule schedule) {
        try (PreparedStatement statement
                     = connection.prepareStatement(CREATE)) {
            statement.setInt(1, ((Day.valueOf(schedule.getDay().toString()).ordinal())));
            statement.setInt(2, schedule.getLessonNumber());
            statement.setInt(3, schedule.getWeek().getWeekId());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Schedule schedule) {
        try (PreparedStatement statement
                     = connection.prepareStatement(UPDATE)) {
            statement.setInt(1, ((Day.valueOf(schedule.getDay().toString()).ordinal())));
            statement.setInt(2, schedule.getLessonNumber());
            statement.setInt(3, schedule.getWeek().getWeekId());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;    }

    @Override
    public boolean delete(Schedule schedule) {
        Schedule current = findById(schedule.getScheduleId());
        try (PreparedStatement statement
                     = connection.prepareStatement(DELETE)){
            statement.setInt(1, current.getScheduleId());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;    }
}
