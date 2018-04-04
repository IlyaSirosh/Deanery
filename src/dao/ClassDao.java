package dao;

import dao.Interfaces.IClassDao;
import dao.impl.EntityRetriever;
import model.Class;
import model.Week;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PANNA on 03.04.2018.
 */
public class ClassDao implements IClassDao, AutoCloseable {
    private static final String SELECT_ALL = "SELECT * FROM class";
    private static final String SELECT_BY_ID = "SELECT * FROM class WHERE class_id = ?";
    private static final String CREATE = "INSERT INTO class (building, number, capacity)\n" +
            "VALUES (?,?,?);";
    private static final String UPDATE = "UPDATE class SET " +
            "building = ?, "+
            "number = ? ," +
            "capacity = ? "+
            "WHERE class_id = ?";
    private static final String DELETE ="DELETE FROM class WHERE class_id=?";
    private Connection connection;

    public ClassDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Class> findAll() {
        List<Class> allClasses = new ArrayList<>();
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_ALL)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                allClasses.add(EntityRetriever.retrieveClass(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allClasses;     }

    @Override
    public Class findById(Integer classId) {
        Class classEx = null;
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, classId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                classEx = EntityRetriever.retrieveClass(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return classEx;     }

    @Override
    public boolean create(Class infoForCreate) {
        try (PreparedStatement statement
                     = connection.prepareStatement(CREATE)) {
            statement.setInt(1, infoForCreate.getBuilding());
            statement.setInt(2, infoForCreate.getNumber());
            statement.setInt(3, infoForCreate.getCapacity());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Class infoForUpdate) {
        Class current = findById(infoForUpdate.getClassId());
        try (PreparedStatement statement
                     = connection.prepareStatement(UPDATE)){
            statement.setInt(1, infoForUpdate.getBuilding());
            statement.setInt(2, infoForUpdate.getNumber());
            statement.setInt(3, infoForUpdate.getCapacity());
            statement.setInt(4, infoForUpdate.getClassId());

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Class infoForDelete) {
        Class current = findById(infoForDelete.getClassId());
        try (PreparedStatement statement
                     = connection.prepareStatement(DELETE)){
            statement.setInt(1, current.getClassId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
