package model;

import java.util.List;

public class Lesson {
    private int lessonId;
    private String type;
    private int teacherId;
    private int courseId;
    private int semesterId;
    private String threadName;
    private int groupNumber;
    private GroupExam groupExam;
    private GroupStudent groupStudent;
    private List<Class> classList;
    private List<Schedule> scheduleList;

    @Override
    public String toString() {
        return "Lesson{" +
                "lessonId=" + lessonId +
                ", type='" + type + '\'' +
                ", teacherId=" + teacherId +
                ", courseId=" + courseId +
                ", semesterId=" + semesterId +
                ", threadName='" + threadName + '\'' +
                ", groupNumber=" + groupNumber +
                ", groupExam=" + groupExam +
                ", groupStudent=" + groupStudent +
                ", classList=" + classList +
                ", scheduleList=" + scheduleList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lesson lesson = (Lesson) o;

        if (lessonId != lesson.lessonId) return false;
        if (teacherId != lesson.teacherId) return false;
        if (courseId != lesson.courseId) return false;
        if (semesterId != lesson.semesterId) return false;
        if (groupNumber != lesson.groupNumber) return false;
        if (type != null ? !type.equals(lesson.type) : lesson.type != null) return false;
        if (threadName != null ? !threadName.equals(lesson.threadName) : lesson.threadName != null) return false;
        if (groupExam != null ? !groupExam.equals(lesson.groupExam) : lesson.groupExam != null) return false;
        if (groupStudent != null ? !groupStudent.equals(lesson.groupStudent) : lesson.groupStudent != null)
            return false;
        if (classList != null ? !classList.equals(lesson.classList) : lesson.classList != null) return false;
        return scheduleList != null ? scheduleList.equals(lesson.scheduleList) : lesson.scheduleList == null;
    }

    @Override
    public int hashCode() {
        int result = lessonId;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + teacherId;
        result = 31 * result + courseId;
        result = 31 * result + semesterId;
        result = 31 * result + (threadName != null ? threadName.hashCode() : 0);
        result = 31 * result + groupNumber;
        result = 31 * result + (groupExam != null ? groupExam.hashCode() : 0);
        result = 31 * result + (groupStudent != null ? groupStudent.hashCode() : 0);
        result = 31 * result + (classList != null ? classList.hashCode() : 0);
        result = 31 * result + (scheduleList != null ? scheduleList.hashCode() : 0);
        return result;
    }

    public GroupExam getGroupExam() {

        return groupExam;
    }

    public void setGroupExam(GroupExam groupExam) {
        this.groupExam = groupExam;
    }

    public GroupStudent getGroupStudent() {
        return groupStudent;
    }

    public void setGroupStudent(GroupStudent groupStudent) {
        this.groupStudent = groupStudent;
    }

    public List<Class> getClassList() {
        return classList;
    }

    public void setClassList(List<Class> classList) {
        this.classList = classList;
    }

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    public int getLessonId() {

        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }
}
