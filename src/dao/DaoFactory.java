package dao;

import dao.impl.Config;
import model.Lesson;

/**
 * Created by PANNA on 02.04.2018.
            */
    public abstract class DaoFactory {
    public abstract CourseDao createCourseDao();
    public abstract ClassDao createClassDao();
    public abstract SemesterDao createSemesterDao();
    public abstract  StudentDao createStudentDao();
    public abstract WeekDao createWeekDao();
    public abstract LessonDao createLessonDao();
    public abstract DepartmentDao createDepartmentDao();
    public abstract TeacherDao createTeacherDao();
    public abstract ScheduleDao createScheduleDao();

    public static DaoFactory getInstance() {
        String className = Config.getInstance().getFactoryClassName();
        DaoFactory factory = null;
        try {
            factory = (DaoFactory) Class.forName(className).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return factory;
    }
}
