package model;

import java.sql.Date;
import java.util.List;

/**
 * Created by PANNA on 02.04.2018.
 */
public class GroupExam {
    private List<Lesson> groupList;
    private List<Class> classList;
    private Date date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupExam groupExam = (GroupExam) o;

        if (groupList != null ? !groupList.equals(groupExam.groupList) : groupExam.groupList != null) return false;
        if (classList != null ? !classList.equals(groupExam.classList) : groupExam.classList != null) return false;
        return date != null ? date.equals(groupExam.date) : groupExam.date == null;
    }

    @Override
    public int hashCode() {
        int result = groupList != null ? groupList.hashCode() : 0;
        result = 31 * result + (classList != null ? classList.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    public List<Lesson> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Lesson> groupList) {
        this.groupList = groupList;
    }

    public List<Class> getClassList() {
        return classList;
    }

    public void setClassList(List<Class> classList) {
        this.classList = classList;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String
    toString() {
        return "GroupExam{" +
                "groupList=" + groupList +
                ", classList=" + classList +
                ", date=" + date +
                '}';
    }
}
