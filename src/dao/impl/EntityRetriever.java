package dao.impl;

import model.Course;
import model.Department;

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

}
