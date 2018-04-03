package dao.impl;

import dao.DaoFactory;
import dao.DepartmentDao;
import dao.Interfaces.*;
import dao.SemesterDao;
import model.*;
import model.Class;
import model.enums.Day;
import model.enums.LessonType;
import model.enums.SemesterEnum;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by PANNA on 02.04.2018.
 */
public class EntityRetriever {

    private static IWeekDao weekDao;
    private static IClassDao classDao;
    private static ILessonDao lessonDao;
    private static IScheduleDao scheduleDao;


    public static Course retrieveCourse(ResultSet rs) throws SQLException {
        Course course = new Course();
        course.setCourseId(rs.getInt("course_id"));
        Department department = new Department();
        IDepartmentDao departmentDao= DaoFactory.getInstance().createDepartmentDao();
        department=departmentDao.findById(rs.getInt("department_id"));
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
    public static Lesson retrieveLesson(ResultSet rs) throws SQLException{
        Lesson lesson = new Lesson();
        lesson.setLessonId(rs.getInt("lesson_id"));
        lesson.setType(LessonType.values()[rs.getInt("type")]);
        Teacher teacher = new Teacher();
        teacher.setTeacherId(rs.getInt("teacher_id"));
        ITeacherDao teacherDao= DaoFactory.getInstance().createTeacherDao();
        teacher= teacherDao.findById(teacher.getTeacherId());
        lesson.setTeacher(teacher);
        Course course = new Course();
        course.setCourseId(rs.getInt("course_id"));
        ICourseDao courseDao= DaoFactory.getInstance().createCourseDao();
        course = courseDao.findById(course.getCourseId());
        lesson.setCourse(course);
        lesson.setThreadName(rs.getString("thread_name"));
        lesson.setThreadId(rs.getInt("thread_id"));
        lesson.setGroupNumber(rs.getInt("group_number"));
        Semester semester = new Semester();
        semester.setSemesterId(rs.getInt("semester_id"));
        ISemesterDao semesterDao= DaoFactory.getInstance().createSemesterDao();
        semester=semesterDao.findById(semester.getSemesterId());
        lesson.setSemester(semester);
        return lesson;
    }

    public static Week retrieveWeek(ResultSet rs) throws  SQLException{
        Week week = new Week();
        Semester semester = new Semester();
        week.setWeekId(rs.getInt("week_id"));
        week.setNumber(rs.getInt("number"));
        week.setStart(rs.getDate("start"));
        week.setEnd(rs.getDate("end"));
        semester.setSemesterId(rs.getInt("semester_id"));
        ISemesterDao semesterDao= DaoFactory.getInstance().createSemesterDao();

        semester = semesterDao.findById(semester.getSemesterId());
        week.setSemester(semester);
        return week;
    }

    public static Class retrieveClass(ResultSet rs) throws SQLException{
        Class classEx = new Class();
        classEx.setClassId(rs.getInt("class_id"));
        classEx.setBuilding(rs.getInt("building"));
        classEx.setNumber(rs.getInt("number"));
        classEx.setCapacity(rs.getInt("capacity"));
        return classEx;
    }

    public static Semester retrieveSemester(ResultSet rs) throws SQLException{
        Semester semester=new Semester();
        semester.setSemesterId(rs.getInt("semester_id"));
        semester.setYear(rs.getInt("year"));
        semester.setSemester(((SemesterEnum.values()[rs.getInt("semester")])));
        return semester;
    }

    public static Department retrieveDepartment(ResultSet rs) throws SQLException{
        Department department = new Department();
        department.setDepartmentId(rs.getInt("department_id"));
        department.setName(rs.getString("name"));
        department.setBuildingNumber(rs.getInt("building_number"));
        return department;
    }

    public static Teacher retrieveTeacher(ResultSet rs) throws SQLException{
        Teacher teacher = new Teacher();
        teacher.setTeacherId(rs.getInt("teacher_id"));
        Department department = new Department();
        department.setDepartmentId(rs.getInt("department_id"));
        int departmentId=department.getDepartmentId();
        IDepartmentDao departmentDao=DaoFactory.getInstance().createDepartmentDao();
        department = departmentDao.findById(departmentId);
        teacher.setDepartment(department);
        teacher.setName(rs.getString("name"));
        teacher.setRole(rs.getString("role"));
        return teacher;
    }

    public static Schedule retrieveSchedule(ResultSet rs) throws SQLException{
        Schedule schedule = new Schedule();
        schedule.setScheduleId(rs.getInt("schedule_id"));
        schedule.setDay(((Day.values()[rs.getInt("day")])));
        schedule.setLessonNumber((rs.getInt("lesson_number")));
        Week week = new Week();
        week.setWeekId(rs.getInt("week_id"));
        IWeekDao weekDao = DaoFactory.getInstance().createWeekDao();
        week = weekDao.findById(week.getWeekId());
        schedule.setWeek(week);
        return schedule;
    }


    public static Schedule retrieveTeacherSchedule(ResultSet rs, Teacher teacher) throws SQLException{
        Schedule schedule = retrieveSchedule(rs);
        schedule.setLessons(scheduleDao.findUnitsByIdAndTeacher(schedule.getScheduleId(), teacher));
        return schedule;
    }

    public static Schedule retrieveDepartmentSchedule(ResultSet rs, Department department) throws SQLException{
        Schedule schedule = retrieveSchedule(rs);
        schedule.setLessons(scheduleDao.findUnitsByIdAndDepartment(schedule.getScheduleId(),department));
        return schedule;
    }

    public static Schedule retrieveLessonSchedule(ResultSet rs, Lesson lesson) throws SQLException{
        Schedule schedule = retrieveSchedule(rs);
        schedule.setLessons(scheduleDao.findUnitsByIdAndLesson(schedule.getScheduleId(), lesson));
        return schedule;
    }

    public static ScheduleUnit retrieveScheduleUnit(ResultSet rs) throws SQLException{
        ScheduleUnit scheduleUnit = new ScheduleUnit();
        scheduleUnit.setLesson(lessonDao.findById(rs.getInt("lesson_id")));
        scheduleUnit.setLessonClass(classDao.findById(rs.getInt("class_id")));
        return scheduleUnit;
    }

}
