package model;

/**
 * Created by PANNA on 02.04.2018.
 */
public class Teacher {
    private int teacherId;
    private String name;
    private String role;
    private Department department;

    @Override
    public String toString() {
        return "ITeacherDao{" +
                "teacherId=" + teacherId +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", department=" + department +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teacher teacher = (Teacher) o;

        if (teacherId != teacher.teacherId) return false;
        if (name != null ? !name.equals(teacher.name) : teacher.name != null) return false;
        if (role != null ? !role.equals(teacher.role) : teacher.role != null) return false;
        return department != null ? department.equals(teacher.department) : teacher.department == null;
    }

    @Override
    public int hashCode() {
        int result = teacherId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        return result;
    }

    public Department getDepartment() {

        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
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


}
