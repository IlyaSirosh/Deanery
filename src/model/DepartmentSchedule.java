package model;

import java.util.List;
import java.util.Objects;

public class DepartmentSchedule {

    private Department department;
    private List<TeacherSchedule> scheduleList;


    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<TeacherSchedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<TeacherSchedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DepartmentSchedule)) return false;
        DepartmentSchedule that = (DepartmentSchedule) o;
        return Objects.equals(getDepartment(), that.getDepartment()) &&
                Objects.equals(getScheduleList(), that.getScheduleList());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getDepartment(), getScheduleList());
    }

    @Override
    public String toString() {
        return "DepartmentSchedule{" +
                "department=" + department +
                ", scheduleList=" + scheduleList +
                '}';
    }
}
