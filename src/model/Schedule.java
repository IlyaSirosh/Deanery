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
    private List<ScheduleUnit> lessons;
    @Override
    public String toString() {
        return "Schedule{" +
                "scheduleId=" + scheduleId +
                ", day=" + day +
                ", lessonNumber=" + lessonNumber +
                ", week=" + week +
                ", lessons=" + lessons +
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

        return lessons != null ? lessons.equals(schedule.lessons) : schedule.lessons == null;
    }

    @Override
    public int hashCode() {
        int result = scheduleId;
        result = 31 * result + (day != null ? day.hashCode() : 0);
        result = 31 * result + lessonNumber;
        result = 31 * result + (week != null ? week.hashCode() : 0);
        result = 31 * result + (lessons != null ? lessons.hashCode() : 0);
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


    public List<ScheduleUnit> getLessons() {
        return lessons;
    }

    public void setLessons(List<ScheduleUnit> lessons) {
        this.lessons= lessons;
    }
}
