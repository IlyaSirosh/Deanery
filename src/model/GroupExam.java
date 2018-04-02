package model;

import java.sql.Date;
import java.util.List;

/**
 * Created by PANNA on 02.04.2018.
 */
public class GroupExam {

    private Class examClass;
    private Date date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupExam groupExam = (GroupExam) o;

        if (examClass != null ? !examClass.equals(groupExam.examClass) : groupExam.examClass != null) return false;

        return date != null ? date.equals(groupExam.date) : groupExam.date == null;
    }

    @Override
    public int hashCode() {

        int result =  (examClass != null ? examClass.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }



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

    @Override
    public String
    toString() {
        return "GroupExam{" +
                ", class=" + examClass +
                ", date=" + date +
                '}';
    }
}
