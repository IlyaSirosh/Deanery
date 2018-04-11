package com.model;

import java.sql.Date;
import java.util.Objects;

/**
 * Created by PANNA on 02.04.2018.
 */
public class GroupExam {
    private Lesson lesson;
    private Class examClass;
    private Date date;


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Class getExamClass() {
        return examClass;
    }

    public void setExamClass(Class examClass) {
        this.examClass = examClass;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupExam)) return false;
        GroupExam groupExam = (GroupExam) o;
        return Objects.equals(getLesson(), groupExam.getLesson()) &&
                Objects.equals(getExamClass(), groupExam.getExamClass()) &&
                Objects.equals(getDate(), groupExam.getDate());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getLesson(), getExamClass(), getDate());
    }

    @Override
    public String toString() {
        return "GroupExam{" +
                "lesson=" + lesson +
                ", examClass=" + examClass +
                ", date=" + date +
                '}';
    }

    public GroupExam(){}
}
