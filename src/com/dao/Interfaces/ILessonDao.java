package com.dao.Interfaces;

import com.model.Course;
import com.model.Lesson;

import java.util.List;

/**
 * Created by PANNA on 02.04.2018.
 */
public interface ILessonDao {
    public List<Lesson> findAll();
    public Lesson findById(Integer lessonId);
    public List<Lesson> findByWeek(Integer weekId);
    public List<Lesson> findByDay(Integer dayId);
    public List<Lesson> findyByLessonNumber(Integer lessonNumber);
    public List<Lesson> findByWeekAndDay(Integer weekId, Integer dayId);
    public List<Lesson> findByWeekAndLessonNumber(Integer weekId, Integer lessonNumber);
    public List<Lesson> findByDayAndLessonNumber(Integer dayId, Integer lessonNumber);
    public List<Lesson> findByWeekDayAndLessonNumber(Integer weekId, Integer dayId, Integer lessonNumber);

    Lesson getLecture(Course course);
    List<Lesson> getSeminars(Course course);
    List<Lesson> getSeminars(Lesson lesson);
    List<Lesson> getLessons(Course course);
    boolean connectSeminarsToLecture(Lesson lecture);


    public boolean create(Lesson lesson);
    public boolean update(Lesson infoForUpdate);
    public boolean delete(Lesson lesson);

}
