
package dao.impl;
import dao.*;
import dao.impl.Config;
import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.Connection;
import java.sql.SQLException;



public class JDBCDaoFactory extends DaoFactory {

    static Connection getConnection(){
        Config config = Config.getInstance();

        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl(config.getUrl());
        ds.setPassword(config.getPass());
        ds.setUsername(config.getUser());

        Connection connection;
        try {
            connection = ds.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
}