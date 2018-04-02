package model;

import java.util.List;

/**
 * Created by PANNA on 02.04.2018.
 */
public class Schedule {
    private int scheduleId;
    private DayEnum day;
    public static enum DayEnum{Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday};
    private int lessonNumber;
    private Week week;
    private List<Class> classList;
    private List<Lesson> lessonList;
    @Override
    public String toString() {
        return "Schedule{" +
                "scheduleId=" + scheduleId +
                ", day=" + day +
                ", lessonNumber=" + lessonNumber +
                ", week=" + week +
                ", classList=" + classList +
                ", lessonList=" + lessonList +
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
        if (classList != null ? !classList.equals(schedule.classList) : schedule.classList != null) return false;
        return lessonList != null ? lessonList.equals(schedule.lessonList) : schedule.lessonList == null;
    }

    @Override
    public int hashCode() {
        int result = scheduleId;
        result = 31 * result + (day != null ? day.hashCode() : 0);
        result = 31 * result + lessonNumber;
        result = 31 * result + (week != null ? week.hashCode() : 0);
        result = 31 * result + (classList != null ? classList.hashCode() : 0);
        result = 31 * result + (lessonList != null ? lessonList.hashCode() : 0);
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


    public List<Class> getClassList() {
        return classList;
    }

    public void setClassList(List<Class> classList) {
        this.classList = classList;
    }

    public List<Lesson> getLessonList() {
        return lessonList;
    }

    public void setLessonList(List<Lesson> lessonList) {
        this.lessonList = lessonList;
    }
}
