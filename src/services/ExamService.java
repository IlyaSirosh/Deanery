package services;

import model.*;

import java.util.List;

public interface ExamService {

    List<Schedule> getScheduleByLesson(Lesson group);
    List<Schedule> getScheduleByDepartment(Department department);
    List<Schedule> getScheduleByTeacher(Teacher teacher);

    void print(List<Schedule> schedule);

    GroupExam getGroupExam(Lesson lesson);
    boolean createGroupExam(GroupExam exam);
    boolean updateGroupExap(GroupExam exam);
    boolean deleteGroupExam(GroupExam exam);

    List<GroupStudent> getStudentResults(Lesson lesson);
    List<GroupStudent> getStudentResults(Teacher teacher);
    List<GroupStudent> getStudentResults(Department department);
    boolean updateStudentResult(GroupStudent result);
    boolean deleteStudentResult(GroupStudent result);
}
