package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PANNA on 02.04.2018.
 */
public class GroupStudent {
    private List<Lesson> lessonList;
    private List<Class> classList;
    private int grade;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupStudent that = (GroupStudent) o;

        if (grade != that.grade) return false;
        if (lessonList != null ? !lessonList.equals(that.lessonList) : that.lessonList != null) return false;
        return classList != null ? classList.equals(that.classList) : that.classList == null;
    }

    @Override
    public int hashCode() {
        int result = lessonList != null ? lessonList.hashCode() : 0;
        result = 31 * result + (classList != null ? classList.hashCode() : 0);
        result = 31 * result + grade;
        return result;
    }

    public List<Lesson> getLessonList() {

        return lessonList;
    }

    public void setLessonList(List<Lesson> lessonList) {
        this.lessonList = lessonList;
    }

    public List<Class> getClassList() {
        return classList;
    }

    public void setClassList(List<Class> classList) {
        this.classList = classList;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "GroupStudent{" +
                "lessonList=" + lessonList +
                ", classList=" + classList +
                ", grade=" + grade +
                '}';
    }
}
