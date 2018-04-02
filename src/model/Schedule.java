package model;

import java.util.List;

/**
 * Created by PANNA on 02.04.2018.
 */
public class Schedule {
    private int scheduleId;
    private DayEnum day;
    public enum DayEnum{Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday};
    private int lessonNumber;
    private Week week;
    private Class lessonClass;
    private Lesson lesson;
    @Override
    public String toString() {
        return "Schedule{" +
                "scheduleId=" + scheduleId +
                ", day=" + day +
                ", lessonNumber=" + lessonNumber +
                ", week=" + week +
                ", class=" + lessonClass +
                ", lesson=" + lesson +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Schedule schedule = (Schedule) o;

        if (scheduleId != schedule.scheduleId) return false;
        if (lessonNumber != schedule.lessonNumber) return false;
        if (day != schedule.day) return false;
        if (week != null ? !week.equals(schedule.week) : schedule.week != null) return false;
        if (lessonClass != null ? !lessonClass.equals(schedule.lessonClass) : schedule.lessonClass != null) return false;
        return lesson != null ? lesson.equals(schedule.lesson) : schedule.lesson == null;
    }

    @Override
    public int hashCode() {
        int result = scheduleId;
        result = 31 * result + (day != null ? day.hashCode() : 0);
        result = 31 * result + lessonNumber;
        result = 31 * result + (week != null ? week.hashCode() : 0);
        result = 31 * result + (lessonClass != null ? lessonClass.hashCode() : 0);
        result = 31 * result + (lesson != null ? lesson.hashCode() : 0);
        return result;
    }

    public Week getWeek() {

        return week;
    }

    public void setWeek(Week week) {
        this.week = week;
    }



    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public DayEnum getDay() {
        return day;
    }

    public void setDay(DayEnum day) {
        this.day = day;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(int lessonNumber) {
        this.lessonNumber = lessonNumber;
    }


    public Class getLessonClass() {
        return lessonClass;
    }

    public void setLessonClass(Class lessonClass) {
        this.lessonClass = lessonClass;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson= lesson;
    }
}
