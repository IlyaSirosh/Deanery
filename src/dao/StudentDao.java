package dao;
import dao.Interfaces.IStudentDao;
import dao.impl.Config;
import dao.impl.EntityRetriever;
import model.Course;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PANNA on 02.04.2018.
 */
public  class StudentDao implements IStudentDao{
    private static final String SELECT_ALL = "SELECT * FROM student";
    private static final String SELECT_BY_ID = "SELECT * FROM student WHERE studen_id = ?";
    private static final String SELECT_BY_LESSON_ID = "SELECT * FROM student WHERE student_id IN (SELECT student_id from lesson where lesson_id=?);";

    private static final String CREATE = "INSERT INTO student ( surname, speciality, startdate, enddate, enddate_reason, credits)\n" +
            "VALUES (?,?,?,?,?,?);";
    private static final String UPDATE = "UPDATE student SET " +
            "surname = ?"+
            "speciality = ? ," +
            "startdate = ?, "+
            "enddate = ?, " +
            "enddate_reason = ?, " +
            "credits = ? " +
            "WHERE student_id = ?";
    private Connection connection;
    @Override
    public List<Student> findAll() {
        List<Student> allStudents = new ArrayList<>();
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_ALL)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                allStudents.add(EntityRetriever.retrieveStudent(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allStudents;    }

    @Override
    public List<Student> findStudentByLesson(Integer lessonId) {
        List<Student> allStudents = new ArrayList<>();
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_BY_LESSON_ID)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                allStudents.add(EntityRetriever.retrieveStudent(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allStudents;    }
    }

    @Override
    public Student findById(Integer studentId) {
        Student student = null;
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, studentId);
            ResultSet rs = statement.executeQuery();
            student = EntityRetriever.retrieveStudent(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;    }

    @Override
    public boolean create(Student student) {
        try (PreparedStatement statement
                     = connection.prepareStatement(CREATE)) {
            statement.setString(1, student.getSurname());
            statement.setString(2, student.getSpeciality());
            statement.setDate(3, student.getStartdate());
            statement.setDate(4, student.getEnddate());
            statement.setInt(5, student.getEnddateReason());
            statement.setInt(6, student.getCredits());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;    }

    @Override
    public boolean update(Student infoForUpdate) {
        Student current = findById(infoForUpdate.getStudentId());
        try (PreparedStatement statement
                     = connection.prepareStatement(UPDATE)){
            statement.setString(2, infoForUpdate.getSurname());
            statement.setString(3, infoForUpdate.getSpeciality());
            statement.setDate(4,infoForUpdate.getStartdate());
            statement.setDate(5, infoForUpdate.getEnddate());
            statement.setString(6, infoForUpdate.getEnddateReason());
            statement.setInt(7, infoForUpdate.getCredits());

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;    }
}
