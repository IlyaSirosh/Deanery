package dao.impl;
import dao.*;
import dao.impl.Config;
import model.Schedule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class JDBCDaoFactory extends DaoFactory {
    Connection connection = null;
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            if(connection == null)
                connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb?useSSL=false", "root", "12345");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public CourseDao createCourseDao() {
        return new CourseDao(getConnection());
    }
    public ClassDao createClassDao(){ return new ClassDao(getConnection()); }
    public SemesterDao createSemesterDao(){ return new SemesterDao(getConnection()); }
    public StudentDao createStudentDao(){ return new StudentDao(getConnection()); }
    public WeekDao createWeekDao(){ return new WeekDao(getConnection()); }
    public DepartmentDao createDepartmentDao(){return new DepartmentDao(getConnection()); }
    public TeacherDao createTeacherDao(){return new TeacherDao(getConnection());}
    public LessonDao createLessonDao(){return new LessonDao(getConnection());}
    public ScheduleDao createScheduleDao(){return new ScheduleDao(getConnection());}
    public ExamGroupDao createExamGroupDao(){return new ExamGroupDao(getConnection());}

}