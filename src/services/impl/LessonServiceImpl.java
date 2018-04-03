package services.impl;

import dao.Interfaces.ILessonDao;
import dao.Interfaces.IStudentDao;
import dao.impl.JDBCDaoFactory;
import model.*;
import model.enums.Day;
import model.enums.LessonType;
import services.LessonService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LessonServiceImpl implements LessonService {

    private static ILessonDao lessonDao = JDBCDaoFactory.getInstance().createLessonDao();
    private static IStudentDao studentDao = JDBCDaoFactory.getInstance().createStudentDao();


    @Override
    public boolean create(Lesson lesson) {

        boolean result =  lessonDao.create(lesson);

        if(lesson.getType() == LessonType.LECTURE){
            lessonDao.connectSeminarsToLecture(lesson);
        }else{
            Lesson lecture = lessonDao.getLecture(lesson.getCourse());
            lessonDao.connectSeminarsToLecture(lecture);
        }

        return result;
    }

    @Override
    public boolean update(Lesson lesson) {
        return lessonDao.update(lesson);
    }

    @Override
    public boolean delete(Lesson lesson) {
        return lessonDao.delete(lesson);
    }

    @Override
    public List<Lesson> getList() {
        return lessonDao.findAll();
    }

    @Override
    public List<Lesson> getListBy(Department department) {
        return getListBy(department, null, null, null);
    }

    @Override
    public List<Lesson> getListBy(Department department, Course course) {
        return getListBy(department, course, null, null);
    }

    @Override
    public List<Lesson> getListBy(Department department, Course course, Teacher teacher) {
        return getListBy(department, course, teacher, null);
    }

    @Override
    public List<Lesson> getListBy(Department department, Course course, Teacher teacher, Semester semester) {
        return filterLessons(lessonDao.findAll(), department, course, teacher, semester);
    }


    @Override
    public List<Lesson> getListBy(Department department, Course course, Teacher teacher, Semester semester, Week week) {
        return getListBy(department, course, teacher, semester, week, null, null);
    }

    @Override
    public List<Lesson> getListBy(Department department, Course course, Teacher teacher, Semester semester, Week week, Day day) {
        return getListBy(department, course, teacher, semester, week, day, null);
    }

    @Override
    public List<Lesson> getListBy(Department department, Course course, Teacher teacher, Semester semester, Week week, Day day, Integer lessonNumber) {
        return filterLessons(getListBy(week, day, lessonNumber), department, course, teacher, semester);
    }

    private List<Lesson> filterLessons(List<Lesson> lessons, Department department, Course course, Teacher teacher, Semester semester){
        return lessons.stream().filter(lesson ->   (department == null || lesson.getTeacher().getDepartment().equals(department)) &&
                (course == null || lesson.getCourse().equals(course)) &&
                (teacher == null || lesson.getTeacher().equals(teacher)) &&
                (semester == null || lesson.getSemester().equals(semester))

        ).collect(Collectors.toList());
    }

    private List<Lesson> getListBy(Week week, Day day, Integer lessonNumber){

        if(week==null && day ==null && (lessonNumber==null || lessonNumber==0)){
            return getList();
        }else if(week==null && day ==null) {
            return lessonDao.findyByLessonNumber(lessonNumber);
        }else if(day ==null && (lessonNumber==null || lessonNumber==0)){
            return lessonDao.findByWeek(week.getWeekId());
        }else if(week==null && (lessonNumber==null || lessonNumber==0)){
            return lessonDao.findByDay(day.getValue());
        }else if(week == null){
            return lessonDao.findByDayAndLessonNumber(day.getValue(), lessonNumber);
        }else if(day == null){
            return lessonDao.findByWeekAndLessonNumber(week.getWeekId(), lessonNumber);
        }else if(lessonNumber == null){
            return lessonDao.findByWeekAndDay(week.getWeekId(), day.getValue());
        }

        return lessonDao.findByWeekDayAndLessonNumber(week.getWeekId(), day.getValue(), lessonNumber);
    }

    @Override
    public List<Student> getGroup(Lesson lesson) {
        return studentDao.findStudentByLesson(lesson.getLessonId());
    }

    @Override
    public Map<Lesson, List<Student>> getThread(Lesson lesson) {
        List<Lesson> seminars = lessonDao.getSeminars(lesson);
        Map<Lesson, List<Student>> studentsByGroups = new HashMap<>();

        seminars.stream().forEach( s -> {
            List<Student> studentGroup = getGroup(s);
            studentsByGroups.put(s, studentGroup);
        });

        return studentsByGroups;
    }

    @Override
    public boolean addToGroup(Lesson lesson, Student student) {

        boolean result = studentDao.addToLesson(lesson,student);

        if(result){
            Student s = studentDao.findById(student.getStudentId());

            s.setCredits(s.getCredits() + lesson.getCourse().getCredits());

            result = result && studentDao.update(s);
        }

        return result;
    }

    @Override
    public boolean deleteFromGroup(Lesson lesson, Student student) {

        boolean result = deleteFromGroup(lesson,student);

        if(result){
            Student s = studentDao.findById(student.getStudentId());
            s.setCredits(s.getCredits() - lesson.getCourse().getCredits());

            result = result && studentDao.update(s);
        }

        return result;
    }

}
