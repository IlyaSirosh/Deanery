package services;

import model.Semester;
import model.Student;
import model.Week;

import java.util.List;

public interface DeaneryService {

    List<Semester> getAllSemesters();
    boolean createSemester(Semester semester);
    boolean updateSemester(Semester semester);
    boolean deleteSemester(Semester semester);

    List<Week> getAllWeeks();
    List<Week> getWeeks(Semester semester);
    boolean createWeek(Week week);
    boolean updateWeek(Week week);
    boolean deleteWeek(Week week);

    List<Student> getAllStudents();
    boolean createStudent(Student student);
    boolean updateStudent(Student student);
    boolean deleteStudent(Student student);

    List<Class> getAllClasses();
    boolean createClass(Class c);
    boolean updateClass(Class c);
    boolean deleteClass(Class c);



}
