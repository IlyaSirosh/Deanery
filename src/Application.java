import dao.ClassDao;
import dao.DaoFactory;
import dao.Interfaces.IClassDao;
import model.Class;

import java.sql.Connection;

/**
 * Created by PANNA on 02.04.2018.
 */
public class Application {
    public static void main(String[] args) {
//        System.out.println(PaidSalaryService.getInstance().findByEmployeeId(3));
        IClassDao classDao = DaoFactory.getInstance().createClassDao();
        Class classEx = new Class();
        classEx.setCapacity(1);
        classEx.setBuilding(1);
        classEx.setNumber(2);
        System.out.println(classEx.toString());
        classDao.create(classEx);

    }
}
