package dao.Interfaces;

import model.Lesson;
import model.Student;

import java.util.List;

/**
 * Created by PANNA on 02.04.2018.
 */
public interface IStudentDao {
    public List<Student> findAll();
    public List<Student> findStudentByLesson(Integer lessonId);

    public boolean addToLesson(Lesson lesson, Student student);
    public boolean deleteFromLesson(Lesson lesson, Student student);


    public Student findById(Integer studentId);
    public boolean create(Student student);
    public boolean update(Student infoForUpdate);
    public boolean delete(Student student);

}
