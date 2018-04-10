package com.services;

import com.model.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> getAll();

    boolean create(Teacher teacher);
    boolean update(Teacher teacher);
    boolean delete(Teacher teacher);


}
