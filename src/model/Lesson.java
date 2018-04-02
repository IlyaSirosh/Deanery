package model;

import java.util.List;

public class Lesson {
    private int lessonId;
    private String type;
    private Teacher teacher;
    private Course course;
    private Semester semester;
    private String threadName;
    private int groupNumber;
    private List<GroupStudent> studentGroup;
    private List<Schedule> schedule;

    @Override
    public String toString() {
        return "Lesson{" +
                "lessonId=" + lessonId +
                ", type='" + type + '\'' +
                ", teacher=" + teacher +
                ", course=" + course +
                ", semester=" + semester +
                ", threadName='" + threadName + '\'' +
                ", groupNumber=" + groupNumber +
                ", groupStudent=" + studentGroup +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lesson lesson = (Lesson) o;

        if (lessonId != lesson.lessonId) return false;
        if (groupNumber != lesson.groupNumber) return false;
        if (type != null ? !type.equals(lesson.type) : lesson.type != null) return false;
        if (teacher != null ? !teacher.equals(lesson.teacher) : lesson.teacher != null) return false;
        if (course != null ? !course.equals(lesson.course) : lesson.course != null) return false;
        if (semester != null ? !semester.equals(lesson.semester) : lesson.semester != null) return false;
        if (threadName != null ? !threadName.equals(lesson.threadName) : lesson.threadName != null) return false;


        return studentGroup != null ? !studentGroup.equals(lesson.studentGroup) : lesson.studentGroup != null;
    }

    @Override
    public int hashCode() {
        int result = lessonId;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
        result = 31 * result + (course != null ? course.hashCode() : 0);
        result = 31 * result + (semester != null ? semester.hashCode() : 0);
        result = 31 * result + (threadName != null ? threadName.hashCode() : 0);
        result = 31 * result + groupNumber;
        result = 31 * result + (studentGroup != null ? studentGroup.hashCode() : 0);

        return result;
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
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


    public List<GroupStudent> getStudentGroup() {
        return studentGroup;
    }

    public void setGroupStudent(List<GroupStudent> group) {
        this.studentGroup = group;
    }

    public List<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Schedule> schedule) {
        this.schedule = schedule;
    }
}
