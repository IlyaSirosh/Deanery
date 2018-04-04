package dao;

import dao.Interfaces.ISemesterDao;
import dao.impl.EntityRetriever;
import model.Semester;
import model.Week;
import model.enums.SemesterEnum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PANNA on 03.04.2018.
 */
public class SemesterDao implements ISemesterDao {
    private static final String SELECT_ALL = "SELECT * FROM semester";
    private static final String SELECT_BY_ID = "SELECT * FROM semester WHERE semester_id = ?";
    private static final String CREATE = "INSERT INTO semester (year, semester)\n" +
            "VALUES (?,?);";
    private static final String UPDATE = "UPDATE semester SET " +
            "year = ?,"+
            "semester = ? " +
            "WHERE semester_id = ?";
    private static final String DELETE ="DELETE FROM semester WHERE semester_id=?";


    private Connection connection;

    public SemesterDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Semester> findAll() {
        List<Semester> allSemesters = new ArrayList<>();
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_ALL)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                allSemesters.add(EntityRetriever.retrieveSemester(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allSemesters;     }

    @Override
    public Semester findById(Integer semesterId) {
        Semester semester = null;
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, semesterId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                semester = EntityRetriever.retrieveSemester(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return semester;    }

    @Override
    public boolean create(Semester semester) {
        try (PreparedStatement statement
                     = connection.prepareStatement(CREATE)) {
            statement.setInt(1, semester.getYear());
            statement.setInt(2, (SemesterEnum.valueOf(semester.getSemester().toString()).ordinal()));
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;    }

    @Override
    public boolean update(Semester infoForUpdate) {
        Semester current = infoForUpdate;
        try (PreparedStatement statement
                     = connection.prepareStatement(UPDATE)){
            statement.setInt(1, current.getYear());
            statement.setInt(2, (SemesterEnum.valueOf(current.getSemester().toString()).ordinal()));
            statement.setInt(3,current.getSemesterId());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;     }

    @Override
    public boolean delete(Semester semester) {
        Semester current = findById(semester.getSemesterId());
        try (PreparedStatement statement
                     = connection.prepareStatement(DELETE)){
            statement.setInt(1, semester.getSemesterId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;    }
}
