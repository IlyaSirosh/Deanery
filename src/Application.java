import dao.ClassDao;
import dao.DaoFactory;
import dao.Interfaces.*;
import model.*;
import model.Class;
import model.enums.SemesterEnum;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static model.enums.SemesterEnum.Autumn;

/**
 * Created by PANNA on 02.04.2018.
 */
public class Application {
    public static void main(String[] args) {
          ISemesterDao semesterDao = DaoFactory.getInstance().createSemesterDao();
          Semester semester = new Semester();
          semester=semesterDao.findById(1);
          semester.setYear(2018);
          semesterDao.update(semester);
    }
}
