package dao;

import dao.Interfaces.IWeekDao;
import dao.impl.EntityRetriever;
import model.Course;
import model.Student;
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
public class WeekDao implements IWeekDao {
    private static final String SELECT_ALL = "SELECT * FROM week";
    private static final String SELECT_BY_ID = "SELECT * FROM week WHERE week_id = ?";
    private static final String SELECT_BY_SEMESTER_ID = "SELECT * FROM week WHERE semester_id = ?";

    private static final String CREATE = "INSERT INTO week (number, start, end, semester_id)\n" +
            "VALUES (?,?,?,?);";
    private static final String UPDATE = "UPDATE week SET " +
            "number = ?,"+
            "start = ? ," +
            "end = ?, "+
            "semester_id = ? " +
            "WHERE week_id = ?";
    private static final String DELETE ="DELETE FROM week WHERE week_id=?";


    private Connection connection;

    public WeekDao(Connection connection) {
        this.connection = connection;
    }
    @Override
    public List<Week> findAll() {
        List<Week> allWeeks = new ArrayList<>();
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_ALL)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                allWeeks.add(EntityRetriever.retrieveWeek(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allWeeks;    }

    @Override
    public Week findById(Integer weekId) {
        Week week = null;
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, weekId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                week = EntityRetriever.retrieveWeek(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return week;    }

    @Override
    public List<Week> findBySemester(Integer semesterId) {
        List<Week> allWeeks = new ArrayList<>();
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_BY_SEMESTER_ID)) {
            statement.setInt(1, semesterId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                allWeeks.add(EntityRetriever.retrieveWeek(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allWeeks;    }

    @Override
    public boolean create(Week week) {
        try (PreparedStatement statement
                     = connection.prepareStatement(CREATE)) {
            statement.setInt(1, week.getNumber());
            statement.setDate(2, week.getStart());
            statement.setDate(3, week.getEnd());
            statement.setInt(4, week.getSemester().getSemesterId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Week infoForUpdate) {
        Week current =infoForUpdate;
        try (PreparedStatement statement
                     = connection.prepareStatement(UPDATE)){
            statement.setInt(1, current.getNumber());
            statement.setDate(2, current.getStart());
            statement.setDate(3, current.getEnd());
            statement.setInt(4, current.getSemester().getSemesterId());
            statement.setInt(5, current.getWeekId());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;    }

    @Override
    public boolean delete(Week week) {
        Week current = findById(week.getWeekId());
        try (PreparedStatement statement
                     = connection.prepareStatement(DELETE)){
            statement.setInt(1, week.getWeekId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
