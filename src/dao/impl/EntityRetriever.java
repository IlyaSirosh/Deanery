package dao.impl;

import dao.DepartmentDao;
import dao.Interfaces.ICourseDao;
import dao.Interfaces.IDepartmentDao;
import dao.Interfaces.ISemesterDao;
import dao.Interfaces.ITeacherDao;
import dao.SemesterDao;
import model.*;
import model.Class;
import model.enums.SemesterEnum;

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
        IDepartmentDao departmentDao=null;
        department = departmentDao.findById(department.getDepartmentId());
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
        lesson.setType(rs.getString("type"));
        Teacher teacher = new Teacher();
        teacher.setTeacherId(rs.getInt("teacher_id"));
        ITeacherDao teacherDao = null;
        teacher= teacherDao.findById(teacher.getTeacherId());
        lesson.setTeacher(teacher);
        Course course = new Course();
        course.setCourseId(rs.getInt("course_id"));
        ICourseDao courseDao =null;
        course = courseDao.findById(course.getCourseId());
        lesson.setCourse(course);
        lesson.setThreadName(rs.getString("thread_name"));
        lesson.setThreadId(rs.getInt("thread_id"));
        lesson.setGroupNumber(rs.getInt("group_number"));
        Semester semester = new Semester();
        semester.setSemesterId(rs.getInt("semester_id"));
        ISemesterDao semesterDao = null;
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
        ISemesterDao semesterDao = null;
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
        department.setDepartmentId(rs.getInt("semester_id"));
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
        IDepartmentDao departmentDao = null;
        department = departmentDao.findById(departmentId);
        teacher.setDepartment(department);
        teacher.setName(rs.getString("name"));
        teacher.setRole(rs.getString("role"));
        return teacher;
    }

}
