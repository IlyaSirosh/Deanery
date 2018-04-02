package model;

import java.util.List;
import java.util.Objects;

public class TeacherSchedule {
    private Teacher teacher;
    private List<Schedule> schedule;


    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Schedule> schedule) {
        this.schedule = schedule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeacherSchedule)) return false;
        TeacherSchedule that = (TeacherSchedule) o;
        return Objects.equals(getTeacher(), that.getTeacher()) &&
                Objects.equals(getSchedule(), that.getSchedule());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getTeacher(), getSchedule());
    }

    @Override
    public String toString() {
        return "TeacherSchedule{" +
                "teacher=" + teacher +
                ", schedule=" + schedule +
                '}';
    }
}
