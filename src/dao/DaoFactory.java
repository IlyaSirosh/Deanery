package dao;

import dao.impl.Config;
<<<<<<< HEAD
import model.Lesson;
=======
import model.Department;
import model.Schedule;
>>>>>>> 3f6d91e52d79fceb7401cd609d6a3bcc42e6be96

/**
 * Created by PANNA on 02.04.2018.
            */
    public abstract class DaoFactory {
        public abstract CourseDao createCourseDao();
        public abstract ClassDao createClassDao();
        public abstract SemesterDao createSemesterDao();
        public abstract  StudentDao createStudentDao();
        public abstract WeekDao createWeekDao();
        public abstract DepartmentDao createDepartmentDao();
        public abstract TeacherDao createTeacherDao();
        public abstract LessonDao createLessonDao();
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
