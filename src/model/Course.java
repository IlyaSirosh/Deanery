package model;

/**
 * Created by PANNA on 02.04.2018.
 */
public class Course {
    private int courseId;
    private int departmentId;
    private String name;
    private int lections;
    private int seminars;
    private String conclusion;
    private int credits;
    private boolean obligatory;

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", departmentId=" + departmentId +
                ", name='" + name + '\'' +
                ", lections=" + lections +
                ", seminars=" + seminars +
                ", conclusion='" + conclusion + '\'' +
                ", credits=" + credits +
                ", obligatory=" + obligatory +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (courseId != course.courseId) return false;
        if (departmentId != course.departmentId) return false;
        if (lections != course.lections) return false;
        if (seminars != course.seminars) return false;
        if (credits != course.credits) return false;
        if (obligatory != course.obligatory) return false;
        if (!name.equals(course.name)) return false;
        return conclusion != null ? conclusion.equals(course.conclusion) : course.conclusion == null;
    }

    @Override
    public int hashCode() {
        int result = courseId;
        result = 31 * result + departmentId;
        result = 31 * result + name.hashCode();
        result = 31 * result + lections;
        result = 31 * result + seminars;
        result = 31 * result + (conclusion != null ? conclusion.hashCode() : 0);
        result = 31 * result + credits;
        result = 31 * result + (obligatory ? 1 : 0);
        return result;
    }

    public int getCourseId() {

        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
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

    public int getLections() {
        return lections;
    }

    public void setLections(int lections) {
        this.lections = lections;
    }

    public int getSeminars() {
        return seminars;
    }

    public void setSeminars(int seminars) {
        this.seminars = seminars;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public boolean isObligatory() {
        return obligatory;
    }

    public void setObligatory(boolean obligatory) {
        this.obligatory = obligatory;
    }
}
