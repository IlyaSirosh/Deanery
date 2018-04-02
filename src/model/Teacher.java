package model;

/**
 * Created by PANNA on 02.04.2018.
 */
public class Teacher {
    private int teacherId;
    private String name;
    private String role;
    private int departmentId;

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", departmentId=" + departmentId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teacher teacher = (Teacher) o;

        if (teacherId != teacher.teacherId) return false;
        if (departmentId != teacher.departmentId) return false;
        if (name != null ? !name.equals(teacher.name) : teacher.name != null) return false;
        return role != null ? role.equals(teacher.role) : teacher.role == null;
    }

    @Override
    public int hashCode() {
        int result = teacherId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + departmentId;
        return result;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}
