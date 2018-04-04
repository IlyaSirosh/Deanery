package dao.Interfaces;

import model.*;

import java.util.List;

public interface IExamGroupDao {

    List<GroupExam> findScheduleByCourse(Course course);
    GroupExam findScheduleByLesson(Lesson group);
    List<GroupExam> findScheduleByDepartment(Department department);
    List<GroupExam> findScheduleByTeacher(Teacher teacher);


    boolean createGroupExam(GroupExam exam);
    boolean updateGroupExam(GroupExam exam);
    boolean deleteGroupExam(GroupExam exam);

    List<GroupStudent> findStudentResults(Lesson lesson);
    List<GroupStudent> findStudentResults(Teacher teacher);
    List<GroupStudent> findStudentResults(Department department);

    boolean updateStudentResult(GroupStudent result);
    boolean deleteStudentResult(GroupStudent result);

}
