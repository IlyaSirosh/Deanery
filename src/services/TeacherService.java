package services;

import model.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> getAll();

    boolean create(Teacher teacher);
    boolean update(Teacher teacher);
    boolean delete(Teacher teacher);


}
