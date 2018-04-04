package services;

import model.*;
import model.enums.Day;

import java.util.List;
import java.util.Map;

public interface LessonService {

    boolean create(Lesson lesson);
    boolean update(Lesson lesson);
    boolean delete(Lesson lesson);

    List<Lesson>  getList();
    List<Lesson>  getListBy(Department department);
    List<Lesson>  getListBy(Department department, Course course);
    List<Lesson>  getListBy(Department department, Course course, Teacher teacher);
    List<Lesson>  getListBy(Department department, Course course, Teacher teacher, Semester semester);
    List<Lesson>  getListBy(Department department, Course course, Teacher teacher, Semester semester, Week week);
    List<Lesson>  getListBy(Department department, Course course, Teacher teacher, Semester semester, Week week, Day day);
    List<Lesson>  getListBy(Department department, Course course, Teacher teacher, Semester semester, Week week, Day day, Integer lessonNumber);

    List<Student> getGroup(Lesson lesson);
    Map<Lesson, List<Student>> getThread(Lesson lesson);

    boolean addToGroup(Lesson lesson, Student student);
    boolean deleteFromGroup(Lesson lesson, Student student);


}
