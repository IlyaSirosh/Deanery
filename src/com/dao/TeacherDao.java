package com.dao;

import com.dao.Interfaces.ITeacherDao;
import com.dao.impl.EntityRetriever;
import com.dao.impl.JDBCDaoFactory;
import com.dao.impl.Repository;
import com.model.Teacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PANNA on 03.04.2018.
 */

@Repository
public class TeacherDao implements ITeacherDao{
    private static final String SELECT_ALL = "SELECT * FROM teacher";
    private static final String SELECT_BY_ID = "SELECT * FROM teacher WHERE teacher_id = ?";
    private static final String SELECT_BY_DEPARTMENT_ID = "SELECT * FROM teacher WHERE department_id =?";
    private static final String CREATE = "INSERT INTO teacher (department_id,name,role)\n" +
            "VALUES (?,?,?);";
    private static final String UPDATE = "UPDATE teacher SET " +
            "department_id = ?,"+
            "name = ? ," +
            "role = ? "+
            "WHERE teacher_id = ?";
    private static final String DELETE ="DELETE FROM teacher WHERE teacher_id=?";

    @Override
    public List<Teacher> findAll() {
        List<Teacher> allTeachers = new ArrayList<>();
        try (PreparedStatement statement
                     = JDBCDaoFactory.getConnection().prepareStatement(SELECT_ALL)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                allTeachers.add(EntityRetriever.retrieveTeacher(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allTeachers;     }

    @Override
    public Teacher findById(Integer teacherId) {
        Teacher teacher = null;
        try (PreparedStatement statement
                     = JDBCDaoFactory.getConnection().prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, teacherId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                teacher = EntityRetriever.retrieveTeacher(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacher;    }

    @Override
    public List<Teacher> findByDepartment(Integer departmentId) {
        List<Teacher> allTeachers = new ArrayList<>();
        try (PreparedStatement statement
                     = JDBCDaoFactory.getConnection().prepareStatement(SELECT_BY_DEPARTMENT_ID)) {
            statement.setInt(1, departmentId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                allTeachers.add(EntityRetriever.retrieveTeacher(rs));
            }        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allTeachers;
    }

    @Override
    public boolean create(Teacher teacher) {
        try (PreparedStatement statement
                     = JDBCDaoFactory.getConnection().prepareStatement(CREATE)) {
            statement.setInt(1, teacher.getDepartment().getDepartmentId());
            statement.setString(2, teacher.getName());
            statement.setString(3, teacher.getRole());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;    }

    @Override
    public boolean update(Teacher infoForUpdate) {
        Teacher current = infoForUpdate;
        try (PreparedStatement statement
                     = JDBCDaoFactory.getConnection().prepareStatement(UPDATE)){
            statement.setInt(1, current.getDepartment().getDepartmentId());
            statement.setString(2, current.getName());
            statement.setString(3, current.getRole());
            statement.setInt(4, current.getTeacherId());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Teacher teacher) {
        Teacher current = findById(teacher.getTeacherId());
        try (PreparedStatement statement
                     = JDBCDaoFactory.getConnection().prepareStatement(DELETE)){
            statement.setInt(1, current.getTeacherId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
