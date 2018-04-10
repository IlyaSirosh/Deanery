package com.services.impl;

import com.dao.Interfaces.IExamGroupDao;
import com.model.*;
import com.services.ExamService;
import com.services.configs.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    private final IExamGroupDao examGroupDao;

    public ExamServiceImpl(IExamGroupDao examGroupDao) {
        this.examGroupDao = examGroupDao;
    }

    @Override
    public List<GroupExam> getScheduleByCourse(Course course) {
        return examGroupDao.findScheduleByCourse(course);
    }

    @Override
    public List<GroupExam> getScheduleByDepartment(Department department) {
        return examGroupDao.findScheduleByDepartment(department);
    }

    @Override
    public List<GroupExam> getScheduleByTeacher(Teacher teacher) {
        return examGroupDao.findScheduleByTeacher(teacher);
    }

    @Override
    public GroupExam getGroupExam(Lesson lesson) {
        return examGroupDao.findScheduleByLesson(lesson);
    }

    @Override
    public boolean createGroupExam(GroupExam exam) {
        return examGroupDao.createGroupExam(exam);
    }

    @Override
    public boolean updateGroupExap(GroupExam exam) {
        return examGroupDao.updateGroupExam(exam);
    }

    @Override
    public boolean deleteGroupExam(GroupExam exam) {
        return examGroupDao.deleteGroupExam(exam);
    }

    @Override
    public List<GroupStudent> getStudentResults(Lesson lesson) {
        return examGroupDao.findStudentResults(lesson);
    }

    @Override
    public List<GroupStudent> getStudentResults(Teacher teacher) {
        return examGroupDao.findStudentResults(teacher);
    }

    @Override
    public List<GroupStudent> getStudentResults(Department department) {
        return examGroupDao.findStudentResults(department);
    }

    @Override
    public boolean updateStudentResult(GroupStudent result) {
        return examGroupDao.updateStudentResult(result);
    }

    @Override
    public boolean deleteStudentResult(GroupStudent result) {
        return examGroupDao.deleteStudentResult(result);
    }
}
