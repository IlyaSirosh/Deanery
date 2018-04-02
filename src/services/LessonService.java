package services;

import model.*;
import model.enums.Day;

import java.util.List;

public interface LessonService {

    boolean create(Lesson lesson);
    boolean update(Lesson lesson);
    boolean delete(Lesson lesson);

    List<Lesson>  getList();
    List<Lesson>  getListBy(Department department);
    List<Lesson>  getListBy(Department department, Course course);
    List<Lesson>  getListBy(Department department, Course course, Teacher teacher);
    List<Lesson>  getListBy(Department department, Course course, Teacher teacher, Semester semester, Week week);
    List<Lesson>  getListBy(Department department, Course course, Teacher teacher, Semester semester, Week week, Day day);
    List<Lesson>  getListBy(Department department, Course course, Teacher teacher, Semester semester, Week week, Day day, int lessonNumber);

    List<Student> getGroup(Lesson lesson);
    boolean addGroup(Lesson lesson, List<Student> group);
    boolean updateGroup(Lesson lesson, List<Student> group);
    boolean deleteGroup(Lesson lesson, List<Student> group);

}
