package services;

import model.*;

import java.util.List;

public interface ExamService {

    List<Schedule> getScheduleByLesson(Lesson group);
    List<Schedule> getScheduleByDepartment(Department department);
    List<Schedule> getScheduleByTeacher(Teacher teacher);

    boolean createGroupExam(GroupExam exam);
    boolean updateGroupExap(GroupExam exam);
    boolean deleteGroupExam(GroupExam exam);

    List<GroupStudent> getStudentResults(Lesson lesson);
    boolean updateStudentResult(Lesson lesson, GroupStudent result);
    boolean deleteStudentResult(Lesson lesson, GroupStudent result);
}
