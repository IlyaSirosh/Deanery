package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PANNA on 02.04.2018.
 */
public class GroupStudent {

    private Student student;
    private int grade;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupStudent that = (GroupStudent) o;

        if (grade != that.grade) return false;

        return student != null ? student.equals(that.student) : that.student == null;
    }

    @Override
    public int hashCode() {
        int result = student != null ? student.hashCode() : 0;

        result = 31 * result + grade;
        return result;
    }


    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "GroupStudent{" +
                "student=" + student +
                ", grade=" + grade +
                '}';
    }
}
