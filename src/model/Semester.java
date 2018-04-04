package model;


import model.enums.SemesterEnum;

/**
 * Created by PANNA on 02.04.2018.
 */
public class Semester {
    private int semesterId;
    private int year;
    private SemesterEnum semester;


    @Override
    public String toString() {
        return "ISemesterDao{" +
                "semesterId=" + semesterId +
                ", year=" + year +
                ", semester=" + semester +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Semester semester1 = (Semester) o;

        if (semesterId != semester1.semesterId) return false;
        if (year != semester1.year) return false;
        return semester == semester1.semester;
    }

    @Override
    public int hashCode() {
        int result = semesterId;
        result = 31 * result + year;
        result = 31 * result + (semester != null ? semester.hashCode() : 0);
        return result;
    }

    public int getSemesterId() {

        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public SemesterEnum getSemester() {
        return semester;
    }

    public void setSemester(SemesterEnum semester) {
        this.semester = semester;
    }

<<<<<<< HEAD
    public Semester(int id){
        this.semesterId = id;
    }

    public Semester(){
=======
    public Semester() {
    }

    public Semester(int semesterId) {
        this.semesterId = semesterId;
>>>>>>> Oleksii
    }
}
