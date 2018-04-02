package model;

import java.sql.Date;

/**
 * Created by PANNA on 02.04.2018.
 */
public class Week {
    private int weekId;
    private int number;
    private Date start;
    private Date end;
    private int semesterId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Week week = (Week) o;

        if (weekId != week.weekId) return false;
        if (number != week.number) return false;
        if (semesterId != week.semesterId) return false;
        if (start != null ? !start.equals(week.start) : week.start != null) return false;
        return end != null ? end.equals(week.end) : week.end == null;
    }

    @Override
    public int hashCode() {
        int result = weekId;
        result = 31 * result + number;
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        result = 31 * result + semesterId;
        return result;
    }

    @Override
    public String toString() {
        return "Week{" +
                "weekId=" + weekId +
                ", number=" + number +
                ", start=" + start +
                ", end=" + end +
                ", semesterId=" + semesterId +
                '}';
    }

    public int getWeekId() {
        return weekId;
    }

    public void setWeekId(int weekId) {
        this.weekId = weekId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }
}
