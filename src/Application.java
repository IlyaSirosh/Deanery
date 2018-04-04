import dao.ClassDao;
import dao.DaoFactory;
import dao.Interfaces.*;
import model.*;
import model.Class;
import model.enums.SemesterEnum;

import java.sql.Connection;
import java.util.List;

import static model.enums.SemesterEnum.Autumn;

/**
 * Created by PANNA on 02.04.2018.
 */
public class Application {
    public static void main(String[] args) {
            ICourseDao courseDao = DaoFactory.getInstance().createCourseDao();
            Course course= new Course();
            course=courseDao.findById(1);
            courseDao.create(course);
    }
}
