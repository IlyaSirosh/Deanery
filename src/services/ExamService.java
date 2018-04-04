package services;

import model.*;

import java.util.List;

public interface ExamService {

    List<GroupExam> getScheduleByCourse(Course course);
    List<GroupExam> getScheduleByDepartment(Department department);
    List<GroupExam> getScheduleByTeacher(Teacher teacher);


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
