package model;

import java.sql.Date;


/**
 * Created by PANNA on 02.04.2018.
 */
public class Student {
    private int studentId;
    private String surname;
    private String speciality;
    private Date startdate;
    private Date enddate;
    private int credits;
    private LeaveReason enddateReason;
    public enum LeaveReason {droppes, excluded,left };


    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", surname='" + surname + '\'' +
                ", speciality='" + speciality + '\'' +
                ", startdate=" + startdate +
                ", enddate=" + enddate +
                ", credits=" + credits +
                ", enddateReason=" + enddateReason +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (studentId != student.studentId) return false;
        if (credits != student.credits) return false;
        if (surname != null ? !surname.equals(student.surname) : student.surname != null) return false;
        if (speciality != null ? !speciality.equals(student.speciality) : student.speciality != null) return false;
        if (startdate != null ? !startdate.equals(student.startdate) : student.startdate != null) return false;
        if (enddate != null ? !enddate.equals(student.enddate) : student.enddate != null) return false;
        if (enddateReason != student.enddateReason) return false;
        return (enddateReason != student.enddateReason);

    }

    @Override
    public int hashCode() {
        int result = studentId;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (speciality != null ? speciality.hashCode() : 0);
        result = 31 * result + (startdate != null ? startdate.hashCode() : 0);
        result = 31 * result + (enddate != null ? enddate.hashCode() : 0);
        result = 31 * result + credits;
        result = 31 * result + (enddateReason != null ? enddateReason.hashCode() : 0);
        return result;
    }


    public LeaveReason getEnddateReason() {
        return enddateReason;
    }

    public void setEnddateReason(LeaveReason enddateReason) {

        this.enddateReason = enddateReason;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Student(int studentId) {
        this.studentId = studentId;
    }

    public Student() {
    }
}
