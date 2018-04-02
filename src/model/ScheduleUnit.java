package model;

import java.util.Objects;

public class ScheduleUnit {
    private Lesson lesson;
    private Class lessonClass;


    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Class getLessonClass() {
        return lessonClass;
    }

    public void setLessonClass(Class lessonClass) {
        this.lessonClass = lessonClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScheduleUnit)) return false;
        ScheduleUnit that = (ScheduleUnit) o;
        return Objects.equals(getLesson(), that.getLesson()) &&
                Objects.equals(getLessonClass(), that.getLessonClass());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getLesson(), getLessonClass());
    }


    @Override
    public String toString() {
        return "ScheduleUnit{" +
                "lesson=" + lesson +
                ", lessonClass=" + lessonClass +
                '}';
    }
}
