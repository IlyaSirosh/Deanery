package services;

import java.util.List;

public interface LessonService {

    boolean create(Object lesson);
    boolean update(Object lesson);
    boolean delete(Object lesson);

    Object get(Object lesson);
    List<Object>  getList();
    List<Object>  getListBy(Object department);
    List<Object>  getListBy(Object department, Object course);
    List<Object>  getListBy(Object department, Object course, Object teacher);
    List<Object>  getListBy(Object department, Object course, Object teacher, Object semester, Object week);
    List<Object>  getListBy(Object department, Object course, Object teacher, Object semester, Object week, Object day);
    List<Object>  getListBy(Object department, Object course, Object teacher, Object semester, Object week, Object day, Object lessonNumber);

}
