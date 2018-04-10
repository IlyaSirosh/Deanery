package com.dao;
import com.dao.Interfaces.IStudentDao;
import com.dao.impl.EntityRetriever;
import com.dao.impl.JDBCDaoFactory;
import com.dao.impl.Repository;
import com.model.Lesson;
import com.model.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PANNA on 02.04.2018.
 */
@Repository
public  class StudentDao implements IStudentDao{
    private static final String SELECT_ALL = "SELECT * FROM student";
    private static final String SELECT_BY_ID = "SELECT * FROM student WHERE student_id = ?";
    private static final String SELECT_BY_LESSON_ID = "SELECT * FROM student WHERE student_id IN (SELECT student_id from group_has_student where group_id=?)";

    private static final String CREATE = "INSERT INTO student ( surname, speciality, startdate, enddate, enddate_reason, credits)\n" +
            "VALUES (?,?,?,?,?,?);";
    private static final String UPDATE = "UPDATE student SET " +
            "surname = ?,"+
            "speciality = ? ," +
            "startdate = ?, "+
            "enddate = ?, " +
            "enddate_reason = ?, " +
            "credits = ? " +
            "WHERE student_id = ?";
    private static final String DELETE = "DELETE FROM student WHERE student_id=?";
    private static final String ADD_STUDENT_TO_LESSON = "INSERT INTO group_has_student (group_id, student_id) VALUES (?,?)";
    private static final String DELETE_STUDENT_FROM_LESSON = "DELETE FROM group_has_student WHERE student_id=? AND group_id=?";

    @Override
    public List<Student> findAll() {
        List<Student> allStudents = new ArrayList<>();
        try (PreparedStatement statement
                     = JDBCDaoFactory.getConnection().prepareStatement(SELECT_ALL)) {
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
                     = JDBCDaoFactory.getConnection().prepareStatement(SELECT_BY_LESSON_ID)) {
            statement.setInt(1, lessonId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                allStudents.add(EntityRetriever.retrieveStudent(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allStudents;
    }

    @Override
    public boolean addToLesson(Lesson lesson, Student student) {
        try (PreparedStatement statement
                     = JDBCDaoFactory.getConnection().prepareStatement(ADD_STUDENT_TO_LESSON)) {

            statement.setInt(1, lesson.getLessonId());
            statement.setInt(2, student.getStudentId());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteFromLesson(Lesson lesson, Student student) {
        try (PreparedStatement statement
                     = JDBCDaoFactory.getConnection().prepareStatement(DELETE_STUDENT_FROM_LESSON)){
            statement.setInt(1, student.getStudentId());
            statement.setInt(2, lesson.getLessonId());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    public Student findById(Integer studentId) {
        Student student = null;
        try (PreparedStatement statement
                     = JDBCDaoFactory.getConnection().prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, studentId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                student = EntityRetriever.retrieveStudent(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public boolean create(Student student) {
        try (PreparedStatement statement
                     = JDBCDaoFactory.getConnection().prepareStatement(CREATE)) {
            statement.setString(1, student.getSurname());
            statement.setString(2, student.getSpeciality());
            statement.setDate(3, student.getStartdate());
            statement.setDate(4, student.getEnddate());
            statement.setInt(5, ((Student.LeaveReason.valueOf(student.getEnddateReason().toString()).ordinal())));
            statement.setInt(6, student.getCredits());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;    }

    @Override
    public boolean update(Student infoForUpdate) {
        //Student current = findById(infoForUpdate.getStudentId());
        try (PreparedStatement statement
                     = JDBCDaoFactory.getConnection().prepareStatement(UPDATE)){
            statement.setString(1, infoForUpdate.getSurname());
            statement.setString(2, infoForUpdate.getSpeciality());
            statement.setDate(3,infoForUpdate.getStartdate());
            statement.setDate(4, infoForUpdate.getEnddate());
            statement.setInt(5, ((Student.LeaveReason.valueOf(infoForUpdate.getEnddateReason().toString()).ordinal())));
            statement.setInt(6, infoForUpdate.getCredits());
            statement.setInt(7, infoForUpdate.getStudentId());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;    }

    @Override
    public boolean delete(Student student) {
        //Student current = findById(student.getStudentId());
        try (PreparedStatement statement
                     = JDBCDaoFactory.getConnection().prepareStatement(DELETE)){
            statement.setInt(1, student.getStudentId());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
