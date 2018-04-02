package services;

public interface ScheduleService {

    Object getByGroup(Object group);
    Object getByDepartment(Object group);
    Object getByTeacher(Object group);

    void print(Object schedule);

}
