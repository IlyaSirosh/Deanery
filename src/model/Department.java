package model;

import java.util.List;

/**
 * Created by PANNA on 02.04.2018.
 */
public class Department {
    private int departmentId;
    private String name;
    private int buildingNumber;
    private List<Teacher> teachers;

    @Override
    public String toString() {
        return "IDepartmentDao{" +
                "departmentId=" + departmentId +
                ", name='" + name + '\'' +
                ", buildingNumber=" + buildingNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        if (departmentId != that.departmentId) return false;
        if (buildingNumber != that.buildingNumber) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = departmentId;
        result = 31 * result + name.hashCode();
        result = 31 * result + buildingNumber;
        return result;
    }

    public int getDepartmentId() {

        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Department(int id){
        this.departmentId = id;
    }

    public Department(){
    }


}
