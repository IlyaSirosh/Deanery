package dao.Interfaces;

import model.Lesson;

import java.util.List;

/**
 * Created by PANNA on 02.04.2018.
 */
public interface ILessonDao {
    public List<Lesson> findAll();
    public Lesson findById(Integer lessonId);
    public boolean create(Lesson lesson);
    public boolean update(Lesson infoForUpdate);

}
