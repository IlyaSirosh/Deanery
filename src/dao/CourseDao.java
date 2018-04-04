<<<<<<< HEAD
package dao;

import dao.Interfaces.ICourseDao;
import dao.impl.EntityRetriever;
import model.Course;
import model.Lesson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PANNA on 02.04.2018.
 */
public class CourseDao implements ICourseDao {

    private static final String SELECT_ALL = "SELECT * FROM course";
    private static final String SELECT_BY_ID = "SELECT * FROM course WHERE course_id = ?";
    private static final String SELECT_BY_DEPARTMENT_ID = "SELECT * FROM course WHERE department_id = ?";
    private static final String SELECT_BY_TEACHER_ID =" SELECT * FROM course WHERE department_id IN (SELECT department_id FROM teacher WHERE teacher_id=?) AND course_id IN (SELECT course_id FROM lesson WHERE teacher_id=?)";
    private static final String SELECT_LESSON_BY_COURSE = "SELECT * FROM lesson WHERE course_id=?";
    private static final String CREATE = "INSERT INTO course (department_id, name, lections, seminars,conclusion, credits, obligatory)\n" +
            "VALUES (?,?,?,?,?,?,?);";
    private static final String UPDATE = "UPDATE course SET " +

            "department_id = ?,"+
            "name = ? ," +
            "lections = ?, "+
            "seminars = ?, " +
            "conclusion = ?, " +
            "credits = ?, " +
            "obligatory = ? " +
            "WHERE course_id = ?";
    private static final String DELETE ="DELETE FROM course WHERE course_id=?";
    private Connection connection;

    public CourseDao(Connection connection) {
        this.connection = connection;
    }
    public List<Course> findAll() {
        List<Course> allCourses = new ArrayList<>();
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_ALL)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                allCourses.add(EntityRetriever.retrieveCourse(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCourses;
    }
    public Course findById(Integer id) {
        Course course = null;
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                course = EntityRetriever.retrieveCourse(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }
    public List<Course> findByDepartment(Integer id) {
        List<Course> allCourses = new ArrayList<>();
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_BY_DEPARTMENT_ID)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                allCourses.add(EntityRetriever.retrieveCourse(rs));
            }        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCourses;
    }

    @Override
    public List<Course> findByTeacher(Integer teacherId) {
        List<Course> allCourses = new ArrayList<>();
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_BY_TEACHER_ID)) {
            statement.setInt(1, teacherId);
            statement.setInt(2,teacherId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                allCourses.add(EntityRetriever.retrieveCourse(rs));
            }        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCourses;    }

    public boolean create(Course course) {
        try (PreparedStatement statement
                     = connection.prepareStatement(CREATE)) {
            statement.setInt(1, course.getDepartment().getDepartmentId());
            statement.setString(2, course.getName());
            statement.setInt(3, course.getLections());
            statement.setInt(4, course.getSeminars());
            statement.setString(5, course.getConclusion());
            statement.setInt(6, course.getCredits());
            statement.setBoolean(7, course.isObligatory());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean update(Course infoForUpdate) {
        Course current = findById(infoForUpdate.getCourseId());
        try (PreparedStatement statement
                     = connection.prepareStatement(UPDATE)){
            statement.setInt(1, infoForUpdate.getDepartment().getDepartmentId());
            statement.setString(2, infoForUpdate.getName());
            statement.setInt(3,infoForUpdate.getLections());
            statement.setInt(4, infoForUpdate.getSeminars());
            statement.setString(5, infoForUpdate.getConclusion());
            statement.setInt(6, infoForUpdate.getCredits());
            statement.setBoolean(7, infoForUpdate.isObligatory());
            statement.setInt(8, infoForUpdate.getCourseId());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Course course) {
        Course current = findById(course.getCourseId());
        try (PreparedStatement statement
                     = connection.prepareStatement(DELETE)){
            statement.setInt(1, course.getCourseId());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Lesson> getLessons(Course course) {
        Course current = findById(course.getCourseId());
        List<Lesson> allLessons = new ArrayList<>();
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_LESSON_BY_COURSE)) {
            statement.setInt(1, current.getCourseId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                allLessons.add(EntityRetriever.retrieveLesson(rs));
            }        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allLessons;
    }

}
=======
package dao;

import dao.Interfaces.ICourseDao;
import dao.impl.EntityRetriever;
import model.Course;
import model.Lesson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PANNA on 02.04.2018.
 */
public class CourseDao implements ICourseDao {

    private static final String SELECT_ALL = "SELECT * FROM course";
    private static final String SELECT_BY_ID = "SELECT * FROM course WHERE course_id = ?";
    private static final String SELECT_BY_DEPARTMENT_ID = "SELECT * FROM course WHERE department_id = ?";
    private static final String SELECT_BY_TEACHER_ID =" SELECT * FROM course WHERE department_id IN (SELECT department_id FROM teacher WHERE teacher_id=?) AND course_id IN (SELECT course_id FROM lesson WHERE teacher_id=?)";
    private static final String SELECT_LESSON_BY_COURSE = "SELECT * FROM lesson WHERE course_id=?";
    private static final String CREATE = "INSERT INTO course (department_id, name, lections, seminars,conclusion, credits, obligatory)\n" +
            "VALUES (?,?,?,?,?,?,?);";
    private static final String UPDATE = "UPDATE course SET " +

            "department_id = ?,"+
            "name = ? ," +
            "lections = ?, "+
            "seminars = ?, " +
            "conclusion = ?, " +
            "credits = ?, " +
            "obligatory = ? " +
            "WHERE course_id = ?";
    private static final String DELETE ="DELETE FROM course WHERE course_id=?";
    private Connection connection;

    public CourseDao(Connection connection) {
        this.connection = connection;
    }
    public List<Course> findAll() {
        List<Course> allCourses = new ArrayList<>();
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_ALL)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                allCourses.add(EntityRetriever.retrieveCourse(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCourses;
    }
    public Course findById(Integer id) {
        Course course = null;
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                course = EntityRetriever.retrieveCourse(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }
    public List<Course> findByDepartment(Integer id) {
        List<Course> allCourses = new ArrayList<>();
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_BY_DEPARTMENT_ID)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                allCourses.add(EntityRetriever.retrieveCourse(rs));
            }        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCourses;
    }

    @Override
    public List<Course> findByTeacher(Integer teacherId) {
        List<Course> allCourses = new ArrayList<>();
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_BY_TEACHER_ID)) {
            statement.setInt(1, teacherId);
            statement.setInt(2,teacherId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                allCourses.add(EntityRetriever.retrieveCourse(rs));
            }        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCourses;    }

    public boolean create(Course course) {
        try (PreparedStatement statement
                     = connection.prepareStatement(CREATE)) {
            statement.setInt(1, course.getDepartment().getDepartmentId());
            statement.setString(2, course.getName());
            statement.setInt(3, course.getLections());
            statement.setInt(4, course.getSeminars());
            statement.setInt(5, course.getConclusion().getValue());
            statement.setInt(6, course.getCredits());
            statement.setBoolean(7, course.isObligatory());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean update(Course infoForUpdate) {
        Course current = findById(infoForUpdate.getCourseId());
        try (PreparedStatement statement
                     = connection.prepareStatement(UPDATE)){
            statement.setInt(1, infoForUpdate.getDepartment().getDepartmentId());
            statement.setString(2, infoForUpdate.getName());
            statement.setInt(3,infoForUpdate.getLections());
            statement.setInt(4, infoForUpdate.getSeminars());
            statement.setInt(5, infoForUpdate.getConclusion().getValue());
            statement.setInt(6, infoForUpdate.getCredits());
            statement.setBoolean(7, infoForUpdate.isObligatory());
            statement.setInt(8, infoForUpdate.getCourseId());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Course course) {
        Course current = findById(course.getCourseId());
        try (PreparedStatement statement
                     = connection.prepareStatement(DELETE)){
            statement.setInt(1, course.getCourseId());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Lesson> getLessons(Course course) {
        Course current = findById(course.getCourseId());
        List<Lesson> allLessons = new ArrayList<>();
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_LESSON_BY_COURSE)) {
            statement.setInt(1, current.getCourseId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                allLessons.add(EntityRetriever.retrieveLesson(rs));
            }        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allLessons;
    }

}
>>>>>>> master
