package dao.impl;

import model.Course;
import model.Department;
import model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by PANNA on 02.04.2018.
 */
public class EntityRetriever {
    public static Course retrieveCourse(ResultSet rs) throws SQLException {
        Course course = new Course();
        course.setCourseId(rs.getInt("course_id"));
        Department department = new Department();
        department.setDepartmentId(rs.getInt("department_id"));
        course.setDepartment(department);
        course.setName(rs.getString("name"));
        course.setLections(rs.getInt("lections"));
        course.setSeminars(rs.getInt("seminars"));
        course.setConclusion(rs.getString("conclusion"));
        course.setCredits(rs.getInt("credits"));
        course.setObligatory(rs.getBoolean("obligatory"));
        return course;
    }

    public static Student retrieveStudent(ResultSet rs) throws SQLException{
        Student student = new Student();
        student.setStudentId(rs.getInt("student_id"));
        student.setSurname(rs.getString("surname"));
        student.setSpeciality(rs.getString("speciality"));
        student.setStartdate(rs.getDate("startdate"));
        student.setEnddate(rs.getDate("enddate"));
        student.setEnddateReason(((Student.LeaveReason.values()[rs.getInt("enddate_reason")])));
        return student;
    }

}
