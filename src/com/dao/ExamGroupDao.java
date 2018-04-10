package com.dao;

import com.dao.Interfaces.IExamGroupDao;
import com.dao.impl.EntityRetriever;
import com.dao.impl.JDBCDaoFactory;
import com.dao.impl.Repository;
import com.model.*;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ExamGroupDao implements IExamGroupDao{

    private static final String SELECT_BY_COURSE = "SELECT * FROM group_exam WHERE group_id IN (SELECT lesson_id FROM lesson WHERE course_id=?)";
    private static final String SELECT_BY_LESSON = "SELECT * FROM group_exam WHERE group_id=?";
    private static final String SELECT_BY_DEPARTMENT= "SELECT * FROM group_exam WHERE group_id IN (SELECT lesson_id FROM lesson WHERE course_id IN (SELECT course_id FROM course WHERE department_id=?))";
    private static final String SELECT_BY_TEACHER = "SELECT * FROM group_exam WHERE group_id IN (SELECT lesson_id FROM lesson WHERE teacher_id=?)";
    private static final String CREATE_EXAM = "INSERT INTO group_exam (group_id,class_id,date) VALUES (?,?,?)";
    private static final String UPDATE_EXAM = "UPDATE group_exam SET date=? WHERE group_id=? AND class_id=?";
    private static final String DELETE_EXAM = "DELETE group_exam WHERE group_id=? AND class_id=?";
    private static final String SELECT_STUDENT_RESULTS_BY_LESSON = "SELECT * FROM group_has_student WHERE group_id=?";
    private static final String SELECT_STUDENT_RESULTS_BY_TEACHER= "SELECT * FROM group_has_student WHERE group_id IN (SELECT lesson_id FROM lesson WHERE teacher_id=?)";
    private static final String SELECT_STUDENT_RESULTS_BY_DEPARTMENT = "SELECT * FROM group_has_student WHERE group_id IN (SELECT lesson_id FROM lesson WHERE course_id IN (SELECT course_id FROM course WHERE department_id=?))";
    private static final String UPDATE_STUDENT_RESULT = "UPDATE group_has_student SET grade=? WHERE group_id=? AND student_id=?";

    @Override
    public List<GroupExam> findScheduleByCourse(Course course) {
        List<GroupExam> exams = new ArrayList<>();
        try (PreparedStatement statement
                     = JDBCDaoFactory.getConnection().prepareStatement(SELECT_BY_COURSE)) {
            statement.setInt(1, course.getCourseId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                exams.add(EntityRetriever.retrieveGroupExam(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exams;
    }

    @Override
    public GroupExam findScheduleByLesson(Lesson group) {
        GroupExam exam = null;
        try (PreparedStatement statement
                     = JDBCDaoFactory.getConnection().prepareStatement(SELECT_BY_LESSON)) {
            statement.setInt(1, group.getLessonId());
            ResultSet rs = statement.executeQuery();
            exam = EntityRetriever.retrieveGroupExam(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exam;
    }

    @Override
    public List<GroupExam> findScheduleByDepartment(Department department) {
        List<GroupExam> exams = new ArrayList<>();
        try (PreparedStatement statement
                     = JDBCDaoFactory.getConnection().prepareStatement(SELECT_BY_DEPARTMENT)) {
            statement.setInt(1, department.getDepartmentId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                exams.add(EntityRetriever.retrieveGroupExam(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exams;
    }

    @Override
    public List<GroupExam> findScheduleByTeacher(Teacher teacher) {
        List<GroupExam> exams = new ArrayList<>();
        try (PreparedStatement statement
                     = JDBCDaoFactory.getConnection().prepareStatement(SELECT_BY_TEACHER)) {
            statement.setInt(1, teacher.getTeacherId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                exams.add(EntityRetriever.retrieveGroupExam(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exams;
    }

    @Override
    public boolean createGroupExam(GroupExam exam) {
        try (PreparedStatement statement
                     = JDBCDaoFactory.getConnection().prepareStatement(CREATE_EXAM)) {
            statement.setInt(1, exam.getLesson().getLessonId());
            statement.setInt(2, exam.getExamClass().getClassId());
            statement.setDate(3, exam.getDate());

            return statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean updateGroupExam(GroupExam exam) {
        try (PreparedStatement statement
                     = JDBCDaoFactory.getConnection().prepareStatement(UPDATE_EXAM)) {
            statement.setInt(2, exam.getLesson().getLessonId());
            statement.setInt(3, exam.getExamClass().getClassId());
            statement.setDate(1, exam.getDate());

            return statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteGroupExam(GroupExam exam) {
        try (PreparedStatement statement
                     = JDBCDaoFactory.getConnection().prepareStatement(DELETE_EXAM)) {
            statement.setInt(1, exam.getLesson().getLessonId());
            statement.setInt(2, exam.getExamClass().getClassId());

            return statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<GroupStudent> findStudentResults(Lesson lesson) {
        List<GroupStudent> results = new ArrayList<>();
        try (PreparedStatement statement
                     = JDBCDaoFactory.getConnection().prepareStatement(SELECT_STUDENT_RESULTS_BY_LESSON)) {
            statement.setInt(1, lesson.getLessonId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                results.add(EntityRetriever.retrieveGroupStudent(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public List<GroupStudent> findStudentResults(Teacher teacher) {
        List<GroupStudent> results = new ArrayList<>();
        try (PreparedStatement statement
                     = JDBCDaoFactory.getConnection().prepareStatement(SELECT_STUDENT_RESULTS_BY_TEACHER)) {
            statement.setInt(1, teacher.getTeacherId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                results.add(EntityRetriever.retrieveGroupStudent(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public List<GroupStudent> findStudentResults(Department department) {
        List<GroupStudent> results = new ArrayList<>();
        try (PreparedStatement statement
                     = JDBCDaoFactory.getConnection().prepareStatement(SELECT_STUDENT_RESULTS_BY_DEPARTMENT)) {
            statement.setInt(1, department.getDepartmentId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                results.add(EntityRetriever.retrieveGroupStudent(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public boolean updateStudentResult(GroupStudent result) {
        try (PreparedStatement statement
                     = JDBCDaoFactory.getConnection().prepareStatement(UPDATE_STUDENT_RESULT)) {

            statement.setInt(1,result.getGrade());
            statement.setInt(2, result.getLesson().getLessonId());
            statement.setInt(3, result.getStudent().getStudentId());

            return statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteStudentResult(GroupStudent result) {
        try (PreparedStatement statement
                     = JDBCDaoFactory.getConnection().prepareStatement(UPDATE_STUDENT_RESULT)) {

            statement.setInt(1, 0);
            statement.setInt(2, result.getLesson().getLessonId());
            statement.setInt(3, result.getStudent().getStudentId());

            return statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
