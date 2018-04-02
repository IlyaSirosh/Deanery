package model;

import java.util.List;

/**
 * Created by PANNA on 02.04.2018.
 */
public class Class {
    private int classId;
    private int building;
    private int number;
    private int capacity;
    private GroupExam groupExam;
    private List<Schedule> scheduleList;
    private List<Class> classList;

    @Override
    public String toString() {
        return "Class{" +
                "classId=" + classId +
                ", building=" + building +
                ", number=" + number +
                ", capacity=" + capacity +
                ", groupExam=" + groupExam +
                ", scheduleList=" + scheduleList +
                ", classList=" + classList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Class aClass = (Class) o;

        if (classId != aClass.classId) return false;
        if (building != aClass.building) return false;
        if (number != aClass.number) return false;
        if (capacity != aClass.capacity) return false;
        if (groupExam != null ? !groupExam.equals(aClass.groupExam) : aClass.groupExam != null) return false;
        if (scheduleList != null ? !scheduleList.equals(aClass.scheduleList) : aClass.scheduleList != null)
            return false;
        return classList != null ? classList.equals(aClass.classList) : aClass.classList == null;
    }

    @Override
    public int hashCode() {
        int result = classId;
        result = 31 * result + building;
        result = 31 * result + number;
        result = 31 * result + capacity;
        result = 31 * result + (groupExam != null ? groupExam.hashCode() : 0);
        result = 31 * result + (scheduleList != null ? scheduleList.hashCode() : 0);
        result = 31 * result + (classList != null ? classList.hashCode() : 0);
        return result;
    }

    public GroupExam getGroupExam() {

        return groupExam;
    }

    public void setGroupExam(GroupExam groupExam) {
        this.groupExam = groupExam;
    }

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    public List<Class> getClassList() {
        return classList;
    }

    public void setClassList(List<Class> classList) {
        this.classList = classList;
    }

    public int getClassId() {

        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getBuilding() {
        return building;
    }

    public void setBuilding(int building) {
        this.building = building;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
