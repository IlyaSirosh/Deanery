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


    @Override
    public String toString() {
        return "Class{" +
                "classId=" + classId +
                ", building=" + building +
                ", number=" + number +
                ", capacity=" + capacity +
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
        return true;
    }

    @Override
    public int hashCode() {
        int result = classId;
        result = 31 * result + building;
        result = 31 * result + number;
        result = 31 * result + capacity;
        return result;
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
