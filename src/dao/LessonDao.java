package dao;

import dao.Interfaces.ILessonDao;
import dao.impl.EntityRetriever;
import model.Course;
import model.Lesson;
import model.Student;
import model.enums.LessonType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PANNA on 03.04.2018.
 */
public class LessonDao implements ILessonDao {

    private static final String SELECT_ALL = "SELECT * FROM lesson";
    private static final String SELECT_BY_ID = "SELECT * FROM lesson WHERE lesson_id = ?";
    private static final String SELECT_BY_WEEK_ID = "SELECT * FROM lesson WHERE lesson_id IN (SELECT lesson_id FROM lesson_has_schedule WHERE schedule_id IN (SELECT schedule_id FROM schedule WHERE week_id=?));";
    private static final String SELECT_BY_DAY = "SELECT * FROM lesson WHERE lesson_id IN (SELECT lesson_id FROM lesson_has_schedule WHERE schedule_id IN (SELECT schedule_id FROM schedule WHERE day=?));";
    private static final String SELECT_BY_LESSONNUMBER = "SELECT * FROM lesson WHERE lesson_id IN (SELECT lesson_id FROM lesson_has_schedule WHERE schedule_id IN (SELECT schedule_id FROM schedule WHERE lesson_number=?));";
    private static final String SELECT_BY_WEEK_ID_AND_DAY = "SELECT * FROM lesson WHERE lesson_id IN (SELECT lesson_id FROM lesson_has_schedule WHERE schedule_id IN (SELECT schedule_id FROM schedule WHERE week_id=? AND day=?));";
    private static final String SELECT_BY_WEEK_ID_AND_LESSONNUMBER = "SELECT * FROM lesson WHERE lesson_id IN (SELECT lesson_id FROM lesson_has_schedule WHERE schedule_id IN (SELECT schedule_id FROM schedule WHERE week_id=? AND lesson_number=?));";
    private static final String SELECT_BY_DAY_AND_LESSONNUMBER= "SELECT * FROM lesson WHERE lesson_id IN (SELECT lesson_id FROM lesson_has_schedule WHERE schedule_id IN (SELECT schedule_id FROM schedule WHERE day=? AND lesson_number=?));";
    private static final String SELECT_BY_DAY_WEEK_ID_AND_LESSONNUMBER = "SELECT * FROM lesson WHERE lesson_id IN (SELECT lesson_id FROM lesson_has_schedule WHERE schedule_id IN (SELECT schedule_id FROM schedule WHERE lesson_number=? AND day=? AND week_id=?));";
    private static final String SELECT_BY_COURSE_AND_TYPE = "SELECT * FROM lesson WHERE course_id=? AND type=?";
    private static final String SELECT_SEMINARS_BY_LECTURE = "SELECT * FROM lesson WHERE lecture_id=?";
    private static final String UPDATE_SEMINARS = "UPDATE lesson SET lesson_id=? WHERE course_id=? AND type=?";

   // private static final String GET_GROUP="SELECT * FROM student WHERE student_id IN (SELECT student_id FROM group_has_student WHERE group_id IN (SELECT group_id FROM lesson WHERE lesson_id =? ";
    //private static final String ADD_TO_GROUP="INSERT INTO group_has_student(group_id) SELECT ? FROM group_has_student WHERE group_id=?";

    private static final String CREATE = "INSERT INTO lesson (type, teacher_id, course_id, thread_name, thread_id, group_number, semester_id)\n" +
            "VALUES (?,?,?,?,?,?,?);";
    private static final String UPDATE = "UPDATE lesson SET " +
            "type = ?"+
            "teacher_id = ? ," +
            "course_id = ?, "+
            "thread_name = ?, " +
            "thread_id = ?, " +
            "group_number = ?, " +
            "semester_id = ? " +
            "WHERE lesson_id = ?";
    private static final String DELETE ="DELETE FROM lesson WHERE lesson_id=?";
    private Connection connection;

    public LessonDao(Connection connection) {
        this.connection = connection;
    }
    @Override
    public List<Lesson> findAll() {
        List<Lesson> allLessons = new ArrayList<>();
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_ALL)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                allLessons.add(EntityRetriever.retrieveLesson(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allLessons;    }

    @Override
    public Lesson findById(Integer lessonId) {
        Lesson lesson = null;
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, lessonId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                lesson = EntityRetriever.retrieveLesson(rs);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lesson;    }

    @Override
    public List<Lesson> findByWeek(Integer weekId) {
        List<Lesson> allLessons = new ArrayList<>();
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_BY_WEEK_ID)) {
            statement.setInt(1, weekId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                allLessons.add(EntityRetriever.retrieveLesson(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allLessons;    }

    @Override
    public List<Lesson> findByDay(Integer dayId) {
        List<Lesson> allLessons = new ArrayList<>();
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_BY_DAY)) {
            statement.setInt(1, dayId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                allLessons.add(EntityRetriever.retrieveLesson(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allLessons;       }

    @Override
    public List<Lesson> findyByLessonNumber(Integer lessonNumber) {
        List<Lesson> allLessons = new ArrayList<>();
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_BY_LESSONNUMBER)) {
            statement.setInt(1, lessonNumber);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                allLessons.add(EntityRetriever.retrieveLesson(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allLessons;     }

    @Override
    public List<Lesson> findByWeekAndDay(Integer weekId, Integer dayId) {
        List<Lesson> allLessons = new ArrayList<>();
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_BY_WEEK_ID_AND_DAY)) {
            statement.setInt(1, weekId);
            statement.setInt(2, dayId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                allLessons.add(EntityRetriever.retrieveLesson(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allLessons;
    }

    @Override
    public List<Lesson> findByWeekAndLessonNumber(Integer weekId, Integer lessonNumber) {
        List<Lesson> allLessons = new ArrayList<>();
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_BY_WEEK_ID_AND_LESSONNUMBER)) {
            statement.setInt(1, weekId);
            statement.setInt(2, lessonNumber);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                allLessons.add(EntityRetriever.retrieveLesson(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allLessons;    }

    @Override
    public List<Lesson> findByDayAndLessonNumber(Integer dayId, Integer lessonNumber) {
        List<Lesson> allLessons = new ArrayList<>();
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_BY_DAY_AND_LESSONNUMBER)) {
            statement.setInt(1, dayId);
            statement.setInt(2, lessonNumber);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                allLessons.add(EntityRetriever.retrieveLesson(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allLessons;      }

    @Override
    public List<Lesson> findByWeekDayAndLessonNumber(Integer weekId, Integer dayId, Integer lessonNumber) {
        List<Lesson> allLessons = new ArrayList<>();
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_BY_DAY_WEEK_ID_AND_LESSONNUMBER)) {
            statement.setInt(1, dayId);
            statement.setInt(2, weekId);
            statement.setInt(3, lessonNumber);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                allLessons.add(EntityRetriever.retrieveLesson(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allLessons;      }

    @Override
    public Lesson getLecture(Course course) {
        Lesson lesson = null;
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_BY_COURSE_AND_TYPE)) {
            statement.setInt(1, course.getCourseId());
            statement.setInt(2, LessonType.LECTURE.getValue());
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                lesson = EntityRetriever.retrieveLesson(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lesson;    
    }

    @Override
    public List<Lesson> getSeminars(Course course) {
        List<Lesson> seminars = new ArrayList<>();
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_BY_COURSE_AND_TYPE)) {
            statement.setInt(1, course.getCourseId());
            statement.setInt(2, LessonType.SEMINAR.getValue());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                seminars.add(EntityRetriever.retrieveLesson(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seminars;
    }

    @Override
    public List<Lesson> getSeminars(Lesson lesson) {
        List<Lesson> seminars = new ArrayList<>();
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_SEMINARS_BY_LECTURE)) {
            statement.setInt(1, lesson.getLessonId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                seminars.add(EntityRetriever.retrieveLesson(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seminars;
    }

    @Override
    public boolean connectSeminarsToLecture(Lesson lesson) {

        try (PreparedStatement statement
                     = connection.prepareStatement(UPDATE_SEMINARS)){
            statement.setInt(1, lesson.getLessonId());
            statement.setInt(2, lesson.getCourse().getCourseId());
            statement.setInt(3, lesson.getType().getValue());

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }



    @Override
    public boolean create(Lesson lesson) {
        try (PreparedStatement statement
                     = connection.prepareStatement(CREATE)) {
            statement.setInt(1, lesson.getType().getValue());
            statement.setInt(2, lesson.getTeacher().getTeacherId());
            statement.setInt(3, lesson.getCourse().getCourseId());
            statement.setString(4, lesson.getThreadName());
            statement.setInt(5, lesson.getThreadId());
            statement.setInt(6, lesson.getGroupNumber());
            statement.setInt(7, lesson.getSemester().getSemesterId());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;    }

    @Override
    public boolean update(Lesson infoForUpdate) {
        Lesson lesson = findById(infoForUpdate.getLessonId());
        try (PreparedStatement statement
                     = connection.prepareStatement(UPDATE)){
            statement.setInt(1, lesson.getType().getValue());
            statement.setInt(2, lesson.getTeacher().getTeacherId());
            statement.setInt(3, lesson.getCourse().getCourseId());
            statement.setString(4, lesson.getThreadName());
            statement.setInt(5, lesson.getThreadId());
            statement.setInt(6, lesson.getGroupNumber());
            statement.setInt(7, lesson.getSemester().getSemesterId());
            statement.setInt(8, lesson.getLessonId());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;     }

    @Override
    public boolean delete(Lesson lesson) {
        Lesson current = findById(lesson.getLessonId());
        try (PreparedStatement statement
                     = connection.prepareStatement(DELETE)){
            statement.setInt(1, current.getLessonId());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;    }
}
