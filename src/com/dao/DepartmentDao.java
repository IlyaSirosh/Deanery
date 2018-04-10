package com.dao;

import com.dao.Interfaces.IDepartmentDao;
import com.dao.impl.EntityRetriever;
import com.dao.impl.JDBCDaoFactory;
import com.dao.impl.Repository;
import com.model.Department;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PANNA on 03.04.2018.
 */
@Repository
public class DepartmentDao implements IDepartmentDao{
    private static final String SELECT_ALL = "SELECT * FROM department";
    private static final String SELECT_BY_ID = "SELECT * FROM department WHERE department_id = ?";
    private static final String CREATE = "INSERT INTO department (name, building_number)\n" +
            "VALUES (?,?);";
    private static final String UPDATE = "UPDATE department SET " +
            "name = ?,"+
            "building_number = ? " +
            "WHERE department_id = ?";
    private static final String DELETE ="DELETE FROM department WHERE department_id=?";

    @Override
    public List<Department> findAll() {
        List<Department> allDepartments = new ArrayList<>();
        try (PreparedStatement statement
                     = JDBCDaoFactory.getConnection().prepareStatement(SELECT_ALL)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                allDepartments.add(EntityRetriever.retrieveDepartment(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allDepartments;     }

    @Override
    public Department findById(Integer departmentId) {
        Department department = null;
        try (PreparedStatement statement
                     = JDBCDaoFactory.getConnection().prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, departmentId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                department = EntityRetriever.retrieveDepartment(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;      }

    @Override
    public boolean create(Department department) {
        try (PreparedStatement statement
                     = JDBCDaoFactory.getConnection().prepareStatement(CREATE)) {
            statement.setString(1, department.getName());
            statement.setInt(2, department.getBuildingNumber());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;    }

    @Override
    public boolean update(Department infoForUpdate) {
        Department current=infoForUpdate;
        try (PreparedStatement statement
                     = JDBCDaoFactory.getConnection().prepareStatement(UPDATE)){
            statement.setString(1, current.getName());
            statement.setInt(2, current.getBuildingNumber());
            statement.setInt(3, current.getDepartmentId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;    }

    @Override
    public boolean delete(Department department) {
        Department current = findById(department.getDepartmentId());
        try (PreparedStatement statement
                     = JDBCDaoFactory.getConnection().prepareStatement(DELETE)){
            statement.setInt(1, department.getDepartmentId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
