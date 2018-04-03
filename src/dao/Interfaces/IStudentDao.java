package dao.Interfaces;

import model.Student;

import java.util.List;

/**
 * Created by PANNA on 02.04.2018.
 */
public interface IStudentDao {
    public List<Student> findAll();
    public List<Student> findStudentByLesson(Integer lessonId);
    public Student findById(Integer studentId);
    public boolean create(Student student);
    public boolean update(Student infoForUpdate);

}
