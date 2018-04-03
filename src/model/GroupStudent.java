package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by PANNA on 02.04.2018.
 */
public class GroupStudent {
    private Lesson lesson;
    private Student student;
    private int grade;

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupStudent)) return false;
        GroupStudent that = (GroupStudent) o;
        return getGrade() == that.getGrade() &&
                Objects.equals(getLesson(), that.getLesson()) &&
                Objects.equals(getStudent(), that.getStudent());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getLesson(), getStudent(), getGrade());
    }

    @Override
    public String toString() {
        return "GroupStudent{" +
                "lesson=" + lesson +
                ", student=" + student +
                ", grade=" + grade +
                '}';
    }
}
