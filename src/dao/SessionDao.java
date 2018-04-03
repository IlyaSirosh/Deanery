package dao;

import dao.Interfaces.ISessionSchedule;
import model.*;
import services.SessionService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SessionDao implements ISessionSchedule {
    private ScheduleDao scheduleDao;
    private StudentDao studentDao;
    private LessonDao lessonDao;



    private String getSessionScheduleBy = " \"SELECT mydb.department.name as department_name\\n\" +\n" +
            "            \", mydb.teacher.name as teacher_name\\n\" +\n" +
            "            \", mydb.lesson.group_id,\\n\" +\n" +
            "            \" mydb.course.name as course_name\\n\" +\n" +
            "            \" , mydb.class.building,testschema.class.number,\\n\" +\n" +
            "            \"mydb.group_exam.date, \\n\" +\n" +
            "            \"mydb.course.conclusion\\n\" +\n" +
            "            \"FROM ((((((((mydb.week Inner join mydb.schedule On mydb.week.week_id = mydb.schedule.week_id) \\n\" +\n" +
            "            \"Inner join mydb.lesson_has_schedule On mydb.schedule.schedule_id = mydb.lesson_has_schedule.schedule_id) \\n\" +\n" +
            "            \"Inner join mydb.lesson On mydb.lesson_has_schedule.lesson_id = mydb.lesson.lesson_id)\\n\" +\n" +
            "            \"inner join mydb.teacher On mydb.lesson.teacher_id = mydb.teacher.teacher_id)\\n\" +\n" +
            "            \"inner join mydb.department on mydb.department.department_id = mydb.teacher.department_id)\\n\" +\n" +
            "            \"inner join mydb.course On mydb.lesson.course_id = mydb.course.course_id)\\n\" +\n" +
            "            \"inner join mydb.group_exam On mydb.group_exam.group_id = mydb.lesson.group_id)\\n\" +\n" +
            "            \"inner join mydb.class On mydb.group_exam.class_id = mydb.class.class_id)\\n\" +\n" +
            "            \"where mydb.week.number > 12 \"";


    private Connection connection;
    public SessionDao(Connection connection){
        this.connection = connection;
        this.scheduleDao = new ScheduleDao(connection);
        this.studentDao = new StudentDao(connection);
        this.lessonDao = new LessonDao(connection);
    }


    public ArrayList<SessionSchedule> getAllSessionSchedule(){
        ArrayList<SessionSchedule> sessionSchedules = new ArrayList<>();
        String query = getSessionScheduleBy+");";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                SessionSchedule sessionSchedule = new SessionSchedule();
                sessionSchedule.setDepartment_name(resultSet.getString("department_name"));
                sessionSchedule.setTeacher_name(resultSet.getString("teacher_name"));
                sessionSchedule.setGroup_id(resultSet.getInt("group_id"));
                sessionSchedule.setCourse_name(resultSet.getString("course_name"));
                sessionSchedule.setBuilding(resultSet.getInt("building"));
                sessionSchedule.setClass_number(resultSet.getInt("number"));
                sessionSchedule.setDate(resultSet.getString("date"));
                sessionSchedule.setConclusion(resultSet.getString("conclusion"));
                sessionSchedules.add(sessionSchedule);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return sessionSchedules;
    }

    @Override
    public boolean addMark(Student transfrerStudent, Lesson lesson,Integer grade) {
       List<Lesson> lessonsMoreThan12week = lessonDao.findByWeek(12);


       List<Student> students = studentDao.findStudentByLesson(lesson.getLessonId());



        return false;
    }


    @Override
    public ArrayList<SessionSchedule> getAllSessionScheduleByDepartment(Department department) {
       ArrayList<SessionSchedule> sessionSchedules = new ArrayList<>();
       String query = getSessionScheduleBy+"AND mydb.department.department_id="+department.getDepartmentId()+");";
       try {
           Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery(query);
           while (resultSet.next()){
               SessionSchedule sessionSchedule = new SessionSchedule();
                    sessionSchedule.setDepartment_name(resultSet.getString("department_name"));
                    sessionSchedule.setTeacher_name(resultSet.getString("teacher_name"));
                    sessionSchedule.setGroup_id(resultSet.getInt("group_id"));
                    sessionSchedule.setCourse_name(resultSet.getString("course_name"));
                    sessionSchedule.setBuilding(resultSet.getInt("building"));
                    sessionSchedule.setClass_number(resultSet.getInt("number"));
                    sessionSchedule.setDate(resultSet.getString("date"));
                   sessionSchedule.setConclusion(resultSet.getString("conclusion"));
                   sessionSchedules.add(sessionSchedule);
           }
       }catch (Exception e){
           e.printStackTrace();
       }
      return sessionSchedules;
    }

    @Override
    public ArrayList<SessionSchedule> getAllSessionScheduleByGroup(GroupStudent groupStudent) {
        ArrayList<SessionSchedule> sessionSchedules = new ArrayList<>();
        /*String query = getSessionScheduleBy+"AND mydb.department.department_id="+groupS+");";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                SessionSchedule sessionSchedule = new SessionSchedule();
                sessionSchedule.setDepartment_name(resultSet.getString("department_name"));
                sessionSchedule.setTeacher_name(resultSet.getString("teacher_name"));
                sessionSchedule.setGroup_id(resultSet.getInt("group_id"));
                sessionSchedule.setCourse_name(resultSet.getString("course_name"));
                sessionSchedule.setBuilding(resultSet.getInt("building"));
                sessionSchedule.setClass_number(resultSet.getInt("number"));
                sessionSchedule.setDate(resultSet.getString("date"));
                sessionSchedule.setConclusion(resultSet.getString("conclusion"));
                sessionSchedules.add(sessionSchedule);
            }
        }catch (Exception e){
            e.printStackTrace();
        }*/
        return sessionSchedules;
    }

    @Override
    public List<SessionSchedule> getAllSessionScheduleByTeacher(Teacher teacher) {
        ArrayList<SessionSchedule> sessionSchedules = new ArrayList<>();
        String query = getSessionScheduleBy+"AND mydb.department.department_id="+teacher.getTeacherId()+");";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                SessionSchedule sessionSchedule = new SessionSchedule();
                sessionSchedule.setDepartment_name(resultSet.getString("department_name"));
                sessionSchedule.setTeacher_name(resultSet.getString("teacher_name"));
                sessionSchedule.setGroup_id(resultSet.getInt("group_id"));
                sessionSchedule.setCourse_name(resultSet.getString("course_name"));
                sessionSchedule.setBuilding(resultSet.getInt("building"));
                sessionSchedule.setClass_number(resultSet.getInt("number"));
                sessionSchedule.setDate(resultSet.getString("date"));
                sessionSchedule.setConclusion(resultSet.getString("conclusion"));
                sessionSchedules.add(sessionSchedule);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return sessionSchedules;
    }


}
